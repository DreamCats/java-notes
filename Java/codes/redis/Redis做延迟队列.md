# Redis做延迟队列

## 引言
> 班车预约平台，订单自动取消任务，采用的是监听Redis的key键消失的策略，属于被动轮询。而用Redis做延迟队列，需要主动轮询。班车平台的github：[https://github.com/DreamCats/school-bus](https://github.com/DreamCats/school-bus)

## 实现思路
1. 将整个Redis当做消息池，以kv形式存储消息，key为id，value为具体的消息body
2. 使用ZSET做优先队列，按照score维持优先级（用当前时间+需要延时的时间作为score）
3. 轮询ZSET，拿出score比当前时间戳大的数据（已过期的）
4. 根据id拿到消息池的具体消息进行消费
5. 消费成功，删除改队列和消息
6. 消费失败，让该消息重新回到队列


## 代码实现

### RedisMessage

```java
public class RedisMessage {

    /**
     * 消息id
     */
    private String id;

    /**
     * 消息延迟/毫秒
     */
    private long delay;

    /**
     * 消息存活时间
     */
    private int ttl;

    /**
     * 消息体，对应业务内容
     */
    private String body;

    /**
     * 创建时间，如果只有优先级没有延迟，可以设置创建时间为0
     * 用来消除时间的影响
     */
    private long createTime;

}
```

### RedisMQ队列
```java
@Component
public class RedisMQ {

    /**
     * 消息池前缀，以此前缀加上传递的消息id作为key，以消息{@link RedisMessage}
     * 的消息体body作为值存储
     */
    public static final String MSG_POOL = "Message:Pool:";
    /**
     * zset队列 名称 queue
     */
    public static final String QUEUE_NAME = "Message:Queue:";

    private static final int SEMIH = 30*60;

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 存入消息池
     * @param message
     * @return
     */
    public boolean addMsgPool(RedisMessage message) {

        if (null != message) {
            return redisUtils.set(MSG_POOL + message.getId(), message.getBody(), Long.valueOf(message.getTtl() + SEMIH));
        }
        return false;
    }

    /**
     * 从消息池中删除消息
     * @param id
     */
    public void deMsgPool(String id) {
        redisUtils.del(MSG_POOL + id);
    }

    /**
     * 向队列中添加消息
     * @param key
     * @param score
     * @param val
     */
    public void enMessage(String key, long  score, String val) {
        redisUtils.zsset(key, val, score);
    }

    /**
     * 从队列额删除消息
     * @param key
     * @param id
     * @return
     */
    public boolean deMessage(String key, String id) {
        return redisUtils.zdel(key, id);
    }
}
```

### Redis的工具类
可以去[github地址](https://github.com/DreamCats/school-bus/blob/master/school-bus/guns-order/src/test/java/com/stylefeng/guns/rest/RedisUtils.java)

### 生产者(用户)
```java
@Component
public class MessageProvider {

    private static int delay = 30;//30秒，可自己动态传入

    @Resource
    private RedisMQ redisMQ;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    //改造成redis
    public void sendMessage(String messageContent) {
        try {
            if (messageContent != null){
                String seqId = UUID.randomUUID().toString();
                // 将有效信息放入消息队列和消息池中
                RedisMessage message = new RedisMessage();
                // 可以添加延迟配置
                message.setDelay(delay*1000);
                message.setCreateTime(System.currentTimeMillis());
                message.setBody(messageContent);
                message.setId(seqId);
                // 设置消息池ttl，防止长期占用
                message.setTtl(delay + 360);
                redisMQ.addMsgPool(message);
                //当前时间加上延时的时间，作为score
                Long delayTime = message.getCreateTime() + message.getDelay();
                String d = sdf.format(message.getCreateTime());
                System.out.println("当前时间：" + d+",消费的时间：" + sdf.format(delayTime));
                redisMQ.enMessage(RedisMQ.QUEUE_NAME,delayTime, message.getId());
            }else {
                System.out.println("消息内容为空");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
```

### 消费者
```java
@Component
public class RedisMQConsumer {

    @Resource
    private RedisMQ redisMQ;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private MessageProvider provider;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    /**
     * 消息队列监听器
     * 也可以开个线程池
     * 主动轮询
     */
    @Scheduled(cron = "*/1 * * * * *")
    public void monitor() {
        // 取出0到当前时间的权重值
        Set<Object> set = redisUtils.rangeByScore(RedisMQ.QUEUE_NAME, 0, System.currentTimeMillis());
        if (null != set) {
            // 如果不为空
            // 获取当前时间
            long current = System.currentTimeMillis();
            for (Object id : set) {
                long  score = redisUtils.getScore(RedisMQ.QUEUE_NAME, (String) id).longValue();
                if (current >= score) {
                    // 已超时的消息拿出来消费
                    String str = "";
                    try {
                        // 根据id取出消息
                        str = (String) redisUtils.get(RedisMQ.MSG_POOL + id);
                        System.out.println("消费了:" + str+ ",消费的时间：" + sdf.format(System.currentTimeMillis()));
                    } catch (Exception e) {
                        e.printStackTrace();
                        //如果取消息发生了异常，则将消息重新放回队列
                        System.out.println("消费异常，重新回到队列");
                        provider.sendMessage(str);
                    } finally {
                        // 不管消费成功与非，都要删除当前消息
                        redisMQ.deMessage(RedisMQ.QUEUE_NAME, (String) id);
                        redisMQ.deMsgPool((String) id);
                    }
                }
            }
        }
    }
}
```

### 结合班车预约平台思路
1. 当前端发一个带延迟时间戳的请求，我们后端可以在zset当做延迟队列，订单ID为元素的key，时间戳为当前时间+延迟时间戳当做score
2. 可以使用Springboot定时器或者线程池延迟主动轮询拉取队列中符合条件的score，根据符合条件score的订单id
3. 根据符合条件的订单id，进行回退座位。