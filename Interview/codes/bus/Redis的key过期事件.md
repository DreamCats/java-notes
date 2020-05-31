# 订单自动取消

> 如果系统的并发量不是特别高的，或者日访问量不是特别高的话，可以使用Redis的key过期事件，如果访问量过高，还是采用延迟队列吧。

## Redis的配置
```
#
#  notify-keyspace-events Ex
#
#  By default all notifications are disabled because most users don't need
#  this feature and the feature has some overhead. Note that if you don't
#  specify at least one of K or E, no events will be delivered.
notify-keyspace-events "Ex"
```

## RedisListenerConfig配置
```java
@Configuration
public class RedisListenerConfig {
    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        return container;
    }
}
```

## RedisKeyExpirationListener监听事件

```java
@Component
@Slf4j
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {
    public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    @Autowired
    private IOrderService orderService;

    /**
     * 监听key过期事件
     * @param message
     * @param pattern
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        super.onMessage(message, pattern);
        String expiredKey = message.toString();
        log.info("redis key过期：{}",expiredKey);
        String orderCancleKey = RedisConstants.ORDER_CANCLE_EXPIRE.getKey();
        if (expiredKey.startsWith(orderCancleKey)) {
            // 获取订单id
            String[] strings = expiredKey.split(orderCancleKey);
            String orderId = strings[1];
            log.warn("过期订单ID：" + orderId);
            // 得到过期订单。
            // 1。 更改订单状态就完事了
            OrderRequest request = new OrderRequest();
            request.setOrderStatus("2"); // 关闭订单
            request.setUuid(Convert.toLong(orderId));
            orderService.updateOrderStatus(request);
            log.warn("过期订单已处理：" + orderId);
        }
    }
}
```