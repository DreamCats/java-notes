# MySQL的锁

> MySQL的锁，其实跟Java差不了，一个思想。

面试官：MySQL的锁，介绍一下

我：

MyISAM：MyISAM只有表锁，其中又分为共享读锁和独占写锁。

- MyISAM表的读操作，不会阻塞其他用户对同一个表的读请求，但会阻塞对同一个表的写请求。
- MyISAM表的写操作，会阻塞其他用户对同一个表的读和写操作。
- MyISAM表的读、写操作之间、以及写操作之间是串行的。

Innodb行锁：共享锁，排他锁

- 对于UPDATE、DELETE、INSERT语句，Innodb会自动给涉及的数据集加排他锁（X）；对于普通SELECT语句，Innodb不会加任何锁。

```sql
//显示共享锁（S） ：
 SELECT * FROM table_name WHERE .... LOCK IN SHARE MODE
 //显示排他锁（X）：
 SELECT * FROM table_name WHERE .... FOR UPDATE.
```

- 记录锁（Record Locks）：记录锁是封锁记录，记录锁也叫行锁，注意：行锁是针对索引的，如果表中没有索引，那么就会锁整张表
- 间隙锁（GAP）对于键值在条件范围内但并不存在的记录，InnoDB也会对这个“间隙”加锁，这种锁机制就是所谓的间隙锁。
- 临键锁（Next-Key Lock）：（Record Locks+GAP），锁定一个范围，并且锁定记录本身。对于行的查询，都是采用该方法，主要目的是解决幻读的问题。

面试官：给你张表怎么用cas实现高并发下的update操作

我：

第一种：

```xml
// cas, 期望值和数据表中的旧值一致，才更新。
# newStock = oldStock-desStock;
<update id="desStockByCas">
     UPDATE t_order SET stock=#{newStock} WHERE id=#{orderId} AND stock=#{oldStock}
</update>
```

```java
// orderId：订单id
// getStock:库存：旧值
// desStock：可以是期望值，但这里预减值
int result = orderManager.desStockByCas(orderId, orderDo.getStock(), desStock);
```

第二种：

```xml
// 用版本号
// 我期望的版本号， 和旧版本号一致才更新，并且版本号累加...
<update id="desStockByOptimistic">
    UPDATE t_order SET stock=stock-#{desStock}, version=version+1 
    		WHERE id=#{orderId} AND version=#{oldVersion}
</update>
```

如

```java
// orderId：订单id
// getVersion：获取数据库版本号，旧版本
// desStock：可以是期望值，但这里预减值
int result = orderManager.desStockByOptimistic(orderId, orderDo.getVersion(), desStock);
```