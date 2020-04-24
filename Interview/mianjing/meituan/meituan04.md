## 美团北京 一面  3月24日
- [原链接](https://www.nowcoder.com/discuss/411183)

### LinkedList和ArrayList
- [ArrayList](http://dreamcat.ink/2019/10/29/java-ji-he-arraylist-yuan-ma-jie-xi/)
- [LinkedList](http://dreamcat.ink/2019/10/30/java-ji-he-linkedlist-yuan-ma-jie-xi/)

### Set的一些种类的不同
- TreeSet
> 基于红黑树实现，支持有序性操作，例如根据一个范围查找元素的操作。但是查找效率不如HashSet，HashSet查找的时间复杂为O(1)，TreeSet则为O(logN)。

- HashSet
> 基于哈希表实现，支持快速查找，但不支持有序性操作。并且失去了元素的插入顺序信息，也就是说使用Iterator遍历HashSet得到结果是不确定的。

- LinkedHashSet
> 具有HashSet的查找效率，且内部使用双向链表维护元素的插入顺序。

### Hashmap的特点和扩容
- [一个 HashMap 跟面试官扯了半个小时](https://gitbook.cn/books/5e8cb23a0e53912fafc48fd9/index.html)

### ConcurrentHashMap
- [ConcurrentHashMap](https://juejin.im/post/5e801e29e51d45470b4fce1c#heading-33)

### ==和equals的区别
- [==和equals的区别](https://juejin.im/post/5e7e0615f265da795568754b#heading-4)

### 线程池的三个主要参数的意思
- corePoolSize: 核心线程数线程数定义了最小可以同时运行的线程数量。
- maximumPoolSize: 当队列中存放的任务达到队列容量的时候，当前可以同时运行的线程数量变为最大线程数。
- workQueue: 当新任务来的时候会先判断当前运行的线程数量是否达到核心线程数，如果达到的话，信任就会被存放在队列中。

### Synchronize的底层实现和优化
- [Synchronize的底层实现和优化](https://juejin.im/post/5e7e0e4ce51d4546cd2fcc7c#heading-12)

### 可重入锁是如何实现的
> 可重入锁又名递归锁，是指在同一个线程在外层方法获取锁的时候，在进入内层方法会自动获取锁，典型的synchronized，了解一下

- [参考文章](https://blog.csdn.net/xzp_12345/article/details/79431404)

### AQS和synchronize的区别
- [Synchronized 和 ReenTrantLock 的对比](https://juejin.im/post/5e7e0e4ce51d4546cd2fcc7c#heading-15)

### Threadlocal
- [Threadlocal](https://juejin.im/post/5e7e0e4ce51d4546cd2fcc7c#heading-25)

### 介绍GC的各个收集器，以及如何选择
- [GC各个收集器](https://juejin.im/post/5e8344486fb9a03c786ef885#heading-52)

### 数据库组合索引
- 超过3个列的联合索引不合适，否则虽然减少了回表动作，但索引块过多，查询时就要遍历更多的索引块了；
- 建索引动作应谨慎，因为建索引的过程会产生锁，不是行级锁，而是锁住整个表，任何该表的DML操作都将被阻止，在生产环境中的繁忙时段建索引是一件非常危险的事情；
- 对于某段时间内，海量数据表有频繁的更新，这时可以先删除索引，插入数据，再重新建立索引来达到高效的目的。
  
### 数据库事务
- [事务](https://juejin.im/post/5e94116551882573b86f970f#heading-14)

### Spring Ioc和AOP的动态代理
> IOC负责将对象动态的注入到容器，从而达到一种需要谁就注入谁，什么时候需要就什么时候注入的效果，可谓是招之则来，挥之则去。
> Spring两大核心，IOC和AOP，其中AOP用到了动态代理，常用于日志记录，性能统计，安全控制，事物处理，异常处理等，主要将与业务无关的逻辑从代码中抽离出来复用，实现解耦。
> 动态代理是在静态代理的基础上产生的, 态代理模式对弊端是被代理对象需要实现其公共接口以及接口的所有方法。试想若在一个系统中要给所有对象的所有方法都加上日志记录，让所有代理对象都去实现其接口是不妥的，不能体现其复用性，耦合度也较高。
> 动态代理通过反射技术，在调用被代理的对象的方法时，动态增加一些操作。

### Redis有什么基本类型，你的项目用到了什么基本类型
- String
> 常用命令: set,get,decr,incr,mget 等。

String数据结构是简单的key-value类型，value其实不仅可以是String，也可以是数字。 常规key-value缓存应用； 常规计数：微博数，粉丝数等。
- Hash
> 常用命令： hget,hset,hgetall 等。

Hash 是一个 string 类型的 ﬁeld 和 value 的映射表，hash 特别适合用于存储对象，后续操作的时候，你可以直接仅 仅修改这个对象中的某个字段的值。 比如我们可以Hash数据结构来存储用户信息，商品信息等等。

- List
> 常用命令: lpush,rpush,lpop,rpop,lrange等

list 就是链表，Redis list 的应用场景非常多，也是Redis最重要的数据结构之一，比如微博的关注列表，粉丝列表， 消息列表等功能都可以用Redis的 list 结构来实现。
Redis list 的实现为一个双向链表，即可以支持反向查找和遍历，更方便操作，不过带来了部分额外的内存开销。
另外可以通过 lrange 命令，就是从某个元素开始读取多少个元素，可以基于 list 实现分页查询，这个很棒的一个功 能，基于 redis 实现简单的高性能分页，可以做类似微博那种下拉不断分页的东西（一页一页的往下走），性能高。

- Set
> 常用命令： sadd,spop,smembers,sunion 等

set 对外提供的功能与list类似是一个列表的功能，特殊之处在于 set 是可以自动排重的。
当你需要存储一个列表数据，又不希望出现重复数据时，set是一个很好的选择，并且set提供了判断某个成员是否在 一个set集合内的重要接口，这个也是list所不能提供的。可以基于 set 轻易实现交集、并集、差集的操作。
比如：在微博应用中，可以将一个用户所有的关注人存在一个集合中，将其所有粉丝存在一个集合。Redis可以非常 方便的实现如共同关注、共同粉丝、共同喜好等功能。这个过程也就是求交集的过程，具体命令如下：`sinterstore key1 key2 key3`将交集存在key1内

- Sorted Set
> 常用命令： zadd,zrange,zrem,zcard等

和set相比，sorted set增加了一个权重参数score，使得集合中的元素能够按score进行有序排列。
举例： 在直播系统中，实时排行信息包含直播间在线用户列表，各种礼物排行榜，弹幕消息（可以理解为按消息维 度的消息排行榜）等信息，适合使用 Redis 中的 SortedSet 结构进行存储。

### 只出现一次的数字
```java
class Solution {
    public int singleNumber(int[] nums) {
        if(nums.length == 1) return nums[0];
        int ans = nums[0];
        for(int i = 1; i < nums.length; i++) {
            ans ^= nums[i];
        }
        return ans;
    }
}
```