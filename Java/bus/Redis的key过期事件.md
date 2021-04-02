# RocketMQ

> 该项目主要在添加订单，支付和回退的时候采用了RocketMQ消息中间件。 目的是系统发生异常时，保持系统的最终一致性。

## MQTags
> 异常种类
```java
public enum MqTags {
    ORDER_CANCEL("order_cancel", "订单取消异常"),
    ORDER_SEATS_CANCEL("order_seats_cancel", "判断座位异常"),
    ORDER_ADD_SEATS_CANCLE("order_add_seats_cancle", "更新座位异常"),
    ORDER_CALC_MONEY_CANCLE("order_calc_money_cancle", "计算总金额异常"),
    ORDER_ADD_CANCLE("order_add_cancle", "添加订单异常"),
    PAY_CANCLE("pay_cancle", "支付异常"),
    PAY_CHECK_CANCLE("pay_check_cancle", "校验支付密码和余额"),
    PAY_MONEY_CANCLE("pay_money_cancle", "支付余额写入异常"),
    ;
    private String tag;
    private String message;


    MqTags(String tag, String message) {
        this.tag = tag;
        this.message = message;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
```

## 添加订单
### addOrder
```java
    /**
     * 添加订单，这里比较重要
     * @param request
     * @return
     */
    @Override
    public AddOrderResponse addOrder(AddOrderRequest request) {
        // 判断座位，如果重复，直接退出，否则更新场次的座位信息
        AddOrderResponse response = new AddOrderResponse();
        // 全局orderId
        Long orderId = UUIDUtils.flakesUUID();
        // 1。 判断座位，如果重复，直接退出，否则下一步
        // 2。 更新座位，如果没有异常，这是写操作
        // 3。 计算总金额，如果没有异常
        // 4。 添加订单，如果异常，这是写操作
        try {
            // 1。 判断座位，如果重复，直接退出，否则下一步
            tag = MqTags.ORDER_SEATS_CANCEL.getTag();
            boolean repeatSeats = busService.repeatSeats(request.getSeatsIds(), request.getCountId());
            if (repeatSeats) {
                // b:true 说明重复
                response.setCode(SbCode.SELECTED_SEATS.getCode());
                response.setMsg(SbCode.SELECTED_SEATS.getMessage());
                return response;
            }
//            CastException.cast(SbCode.SYSTEM_ERROR);
            // 2。 更新座位，如果没有异常，这是写操作
            // 用tags来过滤消息
            tag = MqTags.ORDER_ADD_SEATS_CANCLE.getTag();
            boolean addSeats = busService.addSeats(request.getSeatsIds(), request.getCountId());
            if (!addSeats) {
                response.setCode(SbCode.DB_EXCEPTION.getCode());
                response.setMsg(SbCode.DB_EXCEPTION.getMessage());
                return response;
            }
            // 模拟系统异常
//            CastException.cast(SbCode.SYSTEM_ERROR);
            // 3。 计算总金额，如果没有异常
            tag = MqTags.ORDER_CALC_MONEY_CANCLE.getTag();
            String seatIds = request.getSeatsIds();
            Integer seatNumber = seatIds.split(",").length;
            Double countPrice = request.getCountPrice();
            Double totalPrice = getTotalPrice(seatNumber, countPrice);

//            CastException.cast(SbCode.SYSTEM_ERROR);
            // 4。 添加订单，如果异常，这是写操作
            Order order = orderConvertver.res2Order(request);
            order.setOrderPrice(totalPrice);
            order.setEvaluateStatus("0"); // 未评价
            order.setOrderStatus("0"); // 未支付
            order.setUuid(orderId); // 唯一id
            tag = MqTags.ORDER_ADD_CANCLE.getTag();
            int insert = orderMapper.insert(order);// 插入 不判断了
//            CastException.cast(SbCode.SYSTEM_ERROR);
            // 这里就不读了，耗时
//            QueryWrapper<OrderDto> wrapper = new QueryWrapper<>();
//            wrapper.eq("so.uuid", order.getUuid());
//            OrderDto orderDto = orderMapper.selectOrderById(wrapper);
            response.setCode(SbCode.SUCCESS.getCode());
            response.setMsg(SbCode.SUCCESS.getMessage());
            response.setOrderId(orderId);
//            response.setOrderDto(orderDto);
            // 这里放redis 未支付缓存，时间前端给定
            redisUtils.set(RedisConstants.ORDER_CANCLE_EXPIRE.getKey() + orderId, orderId, request.getExpireTime());
            return response;
        } catch (Exception e) {
            // 以上操作如果程序都不发生异常的话， 是不会执行这里的代码的
            // 也就是说不会发送回退消息的。
            // 目的是在高并发的情况下，程序内部发生异常，依然高可用
//            e.printStackTrace();
            log.error("订单业务发生异常");
            // 发消息，将座位退回，将订单退回
            MQDto mqDto = new MQDto();
            mqDto.setOrderId(orderId);
            mqDto.setCountId(request.getCountId());
            mqDto.setSeatsIds(request.getSeatsIds());
            try {
                String key = RedisConstants.ORDER_EXCEPTION_CANCLE_EXPIRE.getKey() + Convert.toStr(orderId);
                sendCancelOrder(topic,tag, key, JSON.toJSONString(mqDto));
                log.warn("订单回退消息发送成功..." + mqDto);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            response.setCode(SbCode.SYSTEM_ERROR.getCode());
            response.setMsg(SbCode.SYSTEM_ERROR.getMessage());
            return response;
        }
    }
```

### sendCancelOrder
```java
    /**
     * 发送订单回退消息
     * @param topic
     * @param tag
     * @param keys
     * @param body
     * @throws Exception
     */
    private void sendCancelOrder(String topic, String tag, String keys, String body) throws Exception{
        // 封装消息
        Message message = new Message(topic,tag,keys,body.getBytes());
        // 消息生产者发送消息，默认用自带的消息生产者
        rocketMQTemplate.getProducer().send(message);
    }
```

### OrderSeatsCancleListener(座位异常)
```java
    /**
     * 回退座位
     * @param messageExt
     */
    @Override
    public void onMessage(MessageExt messageExt) {
        try {
            // 1. 解析消息
            String tags = messageExt.getTags();
            String orderTag = MqTags.ORDER_SEATS_CANCEL.getTag();
            // 过滤标签
            if (tags.equals(orderTag)) {
                return;
            }
            String key = messageExt.getKeys();
            System.out.println("取消订单消息：" + key);
            // Redis保持消费幂等性
            if (!redisUtils.hasKey(key)) {
                String body = new String(messageExt.getBody(), "UTF-8");
                log.warn("收到订单服务异常：" + body);
                MQDto mqDto = JSON.parseObject(body, MQDto.class);
                // 判断需要的值在不在
                if (mqDto.getCountId() != null && mqDto.getSeatsIds() != null) {
                    // 2. 调用业务，回退座位
                    boolean b = busService.filterRepeatSeats(mqDto.getSeatsIds(), mqDto.getCountId());
                    if (b) {
                        log.warn("回退座位成功");
                        redisUtils.set(key, mqDto.getOrderId(), RedisConstants.ORDER_EXCEPTION_CANCLE_EXPIRE.getTime());
                    }
                }
            }
        } catch (UnsupportedEncodingException e) {
            log.error("座位回退程序崩了...好好检查程序吧", e);
        }
    }
```

### OrderAddCancleListener(订单异常)
```java
    /**
     * 取消订单
     * @param messageExt
     */
    @Override
    public void onMessage(MessageExt messageExt) {
        try {
            // 1. 解析消息
            String tags = messageExt.getTags();
            String orderAddTag = MqTags.ORDER_ADD_CANCLE.getTag();
            // 过滤标签
            if (!tags.equals(orderAddTag)) {
                return;
            }
            String key = messageExt.getKeys();
            // Redis保持消费幂等性
            if (!redisUtils.hasKey(key)) {
                String body = new String(messageExt.getBody(), "UTF-8");
                log.warn("收到订单服务异常：" + body);
                MQDto mqDto = JSON.parseObject(body, MQDto.class);
                if (mqDto.getOrderId() != null) {
                    // 2. 程序异常或者系统内部异常导致的订单，因此我认为删除该订单。
                    // 该订单有可能没有插入成功程序就异常了。
                    orderService.deleteOrderById(mqDto.getOrderId());
                    log.warn("异常订单已删除");
                    redisUtils.set(key, mqDto.getOrderId(), RedisConstants.ORDER_EXCEPTION_CANCLE_EXPIRE.getTime());
                }
            }
        } catch (UnsupportedEncodingException e) {
            log.error("订单消费信息程序崩...", e);
        }
    }
```

## 支付
### pay
```java
    /**
     * 支付业务逻辑
     * @param requset
     * @return
     */
    @Override
    public PayResponse pay(PayRequset requset) {
        PayResponse payResponse = new PayResponse();
        Long userId = requset.getUserId();
        Double userMoney = null;
        try {
            // 1. 先核对支付密码是否正确
            tag = MqTags.PAY_CHECK_CANCLE.getTag();
            String key = RedisConstants.USER_INFO_EXPIRE.getKey() + userId;
            UserResponse userResponse = new UserResponse();
            if (redisUtils.hasKey(key)) {
                userResponse = (UserResponse) redisUtils.get(key);
            } else {
                UserRequest request = new UserRequest();
                request.setId(userId);
                // 获取用户信息
                userResponse = userService.getUserById(request);
            }

            // 支付密码不对
            if (!userResponse.getUserDto().getPayPassword().equals(requset.getPayPassword())) {
                payResponse.setCode(SbCode.PAY_PASSWORD_ERROR.getCode());
                payResponse.setMsg(SbCode.PAY_PASSWORD_ERROR.getMessage());
                return payResponse;
            }
            // 2。 核对余额是否够
            userMoney = userResponse.getUserDto().getMoney();
            Double subMoney = NumberUtil.sub(userMoney, requset.getTotalMoney());
            BigDecimal round = NumberUtil.round(subMoney, 2);
            if (round.doubleValue() < 0) {
                payResponse.setCode(SbCode.MONEY_ERROR.getCode());
                payResponse.setMsg(SbCode.MONEY_ERROR.getMessage());
                return payResponse;
            }
            // 3。 够，就写入
            UserUpdateInfoRequest request = new UserUpdateInfoRequest();
            request.setId(userId);
            request.setMoney(round.doubleValue());
            tag = MqTags.PAY_MONEY_CANCLE.getTag();
            userService.updateUserInfo(request); // 暂时先不接受返回信息
            // 模拟异常
//            CastException.cast(SbCode.SYSTEM_ERROR);
            payResponse.setCode(SbCode.SUCCESS.getCode());
            payResponse.setMsg(SbCode.SUCCESS.getMessage());
            // 4. 按道理讲，这边更改订单状态......
            return payResponse;
        } catch (Exception e) {
            log.error("支付业务发生异常");
            MQDto mqDto = new MQDto();
            mqDto.setUserId(userId);
            mqDto.setUserMoney(userMoney);
            // 发送消息
            try {
                String key = RedisConstants.PAY_EXCEPTION_CANCLE_EXPIRE.getKey() + Convert.toStr(userId);
                sendCancelPay(topic,tag,key, JSON.toJSONString(mqDto));
                log.warn("支付回退消息已发送");
            } catch (Exception ex) {
                ex.printStackTrace();
                log.error("支付消息都崩的话...");
            }
            payResponse.setCode(SbCode.SYSTEM_ERROR.getCode());
            payResponse.setMsg(SbCode.SYSTEM_ERROR.getMessage());
            return payResponse;
        }

    }
```
### sendCancelPay
```java
    /**
     * 发送支付消息
     * @param topic
     * @param tag
     * @param keys
     * @param body
     * @throws Exception
     */
    private void sendCancelPay(String topic, String tag, String keys, String body) throws Exception {
        Message message = new Message(topic,tag,keys,body.getBytes());
        rocketMQTemplate.getProducer().send(message);
    }
```

### PayMoneyCancleListener
```java
    /**
     * 支付金额异常
     * @param messageExt
     */
    @Override
    public void onMessage(MessageExt messageExt) {
        try {
            // 1. 解析消息
            String tags = messageExt.getTags();
            String payCancleTag = MqTags.PAY_MONEY_CANCLE.getTag();
            if (!tags.equals(payCancleTag)) {
                return;
            }
            // 2. 拿到key
            String key = messageExt.getKeys();
            if (!redisUtils.hasKey(key)) {
                String body = new String(messageExt.getBody(), "UTF-8");
                log.warn("收到订单服务异常：" + body);
                MQDto mqDto = JSON.parseObject(body, MQDto.class);
                if (mqDto.getUserId() != null && mqDto.getUserMoney() != null) {
                    UserUpdateInfoRequest request = new UserUpdateInfoRequest();
                    request.setId(mqDto.getUserId());
                    request.setMoney(mqDto.getUserMoney());
                    userService.updateUserInfo(request);
                    log.warn("余额已恢复");
                    redisUtils.set(key, mqDto.getUserId(), RedisConstants.PAY_EXCEPTION_CANCLE_EXPIRE.getTime());
                }
            }
        } catch (Exception e) {
            log.error("支付消费信息程序崩...\n", e);
        }
    }
```

## 回退
### payBack
> 这里可以写的，我省了...

## 再谈
关于项目中只是采用了RocketMQ维持了系统的最终一致性，其他的优点，限流等都没有用上，也可以用上的。以上流程图我也没时间画，来不及。