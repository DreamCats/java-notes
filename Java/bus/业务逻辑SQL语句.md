## Explain

> 分别对应：
>
> id  select_type  table  type  possible_keys  key  key_len  ref  rows  Extra
>
> 参考：[https://juejin.im/post/5ec4e4a5e51d45786973b357](https://juejin.im/post/5ec4e4a5e51d45786973b357)

##  用户服务

### checkUsername

```sql
SELECT
	*
FROM
	sb_user su
WHERE
	user_name = 'mai';
```

- 唯一索引

```
1	"SIMPLE"	"su"	"const"	"user_name"	"user_name"	"152"	"const"	1	""
```

- 普通索引

```sql
1	"SIMPLE"	"su"	"ref"	"user_name"	"user_name"	"152"	"const"	1	"Using index condition"
```

- 不加索引

```sql
1	"SIMPLE"	"su"	"ALL"	NULL	NULL	NULL	NULL	11	"Using where"
```

### register省略

### login省略

### getUserById省略

### updateUserInfo省略

## 班车服务

### getBus省略

### getCount

```sql
SELECT
	sc.uuid,
	sc.begin_date,
	sc.begin_time,
	sc.bus_id,
	sc.bus_status,
	sc.seat_status
FROM
	sb_count sc
WHERE
	sc.begin_date = '2020-06-03'
	AND sc.begin_time >= '14:00'
	AND sc.bus_status = '1';
```

- 不加索引

```
1	"SIMPLE"	"sc"	"ALL"	NULL	NULL	NULL	NULL	581	"Using where"

53ms
```

- 给begin_date加普通索引

```
1	"SIMPLE"	"sc"	"ref"	"begin_date"	"begin_date"	"402"	"const"	17	"Using index condition; Using where"

53ms // 数据太少，体现不出来...
```

- 给begin_time加普通索引

```
1	"SIMPLE"	"sc"	"ref"	"begin_date,begin_time"	"begin_date"	"402"	"const"	17	"Using index condition; Using where"

53ms
```

- 给bus_status加普通索引

```
1	"SIMPLE"	"sc"	"index_merge"	"begin_date,begin_time,bus_status"	"begin_date,bus_status"	"402,152"	NULL	7	"Using intersect(begin_date,bus_status); Using where"

53ms
```

### getCountDetailById省略

## 订单服务

### getNoTakeOrdersById

```sql
SELECT
	so.uuid,
	so.bus_status,
	so.seats_ids,
	so.order_user,
	so.order_status,
	so.order_time,
	sc.bus_id,
	CONCAT(sc.begin_date, ' ', sc.begin_time) begin_time
FROM
	sb_order so
	LEFT JOIN sb_count sc ON so.count_id = sc.uuid
WHERE
	so.user_id = '4'
	AND so.order_status = '1'
	AND sc.begin_date >= '2020-06-03'
	AND sc.begin_time >= '15:00';
```

- 不加普通索引

```
1	"SIMPLE"	"so"	"ALL"	NULL	NULL	NULL	NULL	45	"Using where"
1	"SIMPLE"	"sc"	"eq_ref"	"PRIMARY,begin_date,begin_time"	"PRIMARY"	"8"	"school_bus.so.count_id"	1	"Using where"
```

- user_id+order_status加普通索引

```
1	"SIMPLE"	"so"	"ref"	"order_status,user_id"	"order_status"	"152"	"const"	10	"Using index condition; Using where"
1	"SIMPLE"	"sc"	"eq_ref"	"PRIMARY,begin_date,begin_time"	"PRIMARY"	"8"	"school_bus.so.count_id"	1	"Using where"
```

### getEvaluateOrdersById

```sql
SELECT
    so.uuid,
    so.bus_status,
    so.seats_ids,
    so.order_user,
    so.order_time,
    sc.bus_id,
    so.evaluate_status,
    so.comment,
    CONCAT(sc.begin_date, ' ', sc.begin_time) begin_time
FROM
    sb_order so
    LEFT JOIN sb_count sc ON so.count_id = sc.uuid
WHERE
    so.user_id = 4
    AND so.evaluate_status = "1"
    AND so.order_status = "1"
    AND (sc.begin_date = "2020-05-30" AND sc.begin_time < "21:00" OR sc.begin_date < "2020-05-30")

```

- 带了普通索引

```
1	"SIMPLE"	"so"	"ref"	"user_id_order_eval_status"	"user_id_order_eval_status"	"312"	"const,const,const"	1	"Using index condition"
1	"SIMPLE"	"sc"	"eq_ref"	"PRIMARY"	"PRIMARY"	"8"	"school_bus.so.count_id"	1	"Using where"
```

### getNoPayOrdersById

```sql
SELECT
	so.uuid,
	so.bus_status,
	so.seats_ids,
	so.order_user,
	so.order_status,
	so.order_time,
	sc.bus_id,
	CONCAT(sc.begin_date, ' ', sc.begin_time) begin_time
FROM
	sb_order so
	LEFT JOIN sb_count sc ON so.count_id = sc.uuid
WHERE
	so.user_id = '4'
	AND so.order_status = '0'
	AND sc.begin_date >= '2020-06-03'
	AND sc.begin_time >= '20:00';
```

- 带了普通索引

```
1	"SIMPLE"	"so"	"ref"	"order_status,user_id"	"order_status"	"152"	"const"	8	"Using index condition; Using where"
1	"SIMPLE"	"sc"	"eq_ref"	"PRIMARY,begin_date,begin_time"	"PRIMARY"	"8"	"school_bus.so.count_id"	1	"Using where"
```

### addOrder省略

### selectOrderById省略

### updateOrderStatus省略

### deleteOrderById省略

## 支付服务省略

