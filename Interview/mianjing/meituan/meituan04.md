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
![hammingWeight](http://media.dreamcat.ink/uPic/hammingWeight.png)

## 美团成都一面
### 自我介绍
> 准备个模板

### 数据库如何优化
#### 索引优化
- 通过创建**唯一性索引**，可以保证数据库表中每一行数据的唯一性。
- 可以大大**加快数据的检索速度**，这也是创建索引的最主要的原因。
- 帮助服务器**避免排序和临时表**。
- 将**随机IO变为顺序IO**。
- 可以加速**表和表之间的连接**，特别是在实现数据的参考完整性方面特别有意义。

#### 数据库结构优化
- **范式优化**： 比如消除冗余（节省空间。。）
- **反范式优化**：比如适当加冗余等（减少join）
- **限定数据的范围**： 务必禁止不带任何限制数据范围条件的查询语句。比如：我们当用户在查询订单历史的时候，我们可以控制在一个月的范围内。
- **读/写分离**： 经典的数据库拆分方案，主库负责写，从库负责读；
- **拆分表**：分区将数据在物理上分隔开，不同分区的数据可以制定保存在处于不同磁盘上的数据文件里。这样，当对这个表进行查询时，只需要在表分区中进行扫描，而不必进行全表扫描，明显缩短了查询时间，另外处于不同磁盘的分区也将对这个表的数据传输分散在不同的磁盘I/O，一个精心设置的分区可以将数据传输对磁盘I/O竞争均匀地分散开。对数据量大的时时表可采取此方法。可按月自动建表分区。

### 数据库有什么存储引擎
- MyISAM
- Memory
- InnoDB
- Archive

#### InnoDB
- 是 MySQL 默认的**事务型**存储引擎，只有在需要它不支持的特性时，才考虑使用其它存储引擎。
- 实现了四个标准的隔离级别，默认级别是**可重复读**(REPEATABLE READ)。在可重复读隔离级别下，**通过多版本并发控制(MVCC)+ 间隙锁(Next-Key Locking)防止幻影读。**
- 主索引是**聚簇索引**，在索引中保存了数据，从而避免直接读取磁盘，因此对查询性能有很大的提升。
- 内部做了很多优化，包括从磁盘读取数据时采用的**可预测性读**、能够加快读操作并且自动创建的**自适应哈希索引**、能够加速插入操作的插入缓冲区等。
- 支持真正的**在线热备份**。其它存储引擎不支持在线热备份，要获取一致性视图需要停止对所有表的写入，而在读写混合场景中，停止写入可能也意味着停止读取。

#### MyISAM
- 设计简单，数据以**紧密格式存储**。对于只读数据，或者表比较小、可以容忍修复操作，则依然可以使用它。
- 提供了大量的特性，包括**压缩表**、**空间数据索引**等。
- 不支持事务
- 不支持行级锁
- 可以**手工或者自动执行检查和修复操作**，但是和事务恢复以及崩溃恢复不同，**可能导致一些数据丢失，而且修复操作是非常慢的**。

### innodb是索引和数据一体的放在一个文件，那么如果我新建一个索引，那么会放在哪个文件呢
- [参考-掘金](https://juejin.im/post/5a6873fbf265da3e393a97fa)
- [参考-知乎](https://zhuanlan.zhihu.com/p/85553838)
- [参考](https://database.51cto.com/art/202002/610953.htm)

### 数据库的MVCC的缺点
> MVCC在大多数情况下代替了行锁，实现了对读的非阻塞，读不加锁，读写不冲突。缺点是每行记录都需要额外的存储空间，需要做更多的行维护和检查工作。
> 要知道的，MVCC机制下，会在更新前建立undo log，根据各种策略读取时非阻塞就是MVCC，undo log中的行就是MVCC中的多版本。 而undo log这个关键的东西，记载的内容是串行化的结果，记录了多个事务的过程，不属于多版本共存。
> 这么一看，似乎mysql的mvcc也并没有所谓的多版本共存

InnoDB的实现方式是：
1. 事务以排他锁的形式修改原始数据
2. 把修改前的数据存放于undo log，通过回滚指针与主数据关联
3. 修改成功（commit），数据放到redo log中，失败则恢复undo log中的数据（rollback）

### 事务的四大特性
- 原子性
> 根据定义，原子性是指一个事务是一个不可分割的工作单位，其中的操作要么都做，要么都不做。即要么转账成功，要么转账失败，是不存在中间的状态！

- 隔离性
> 根据定义，隔离性是指多个事务并发执行的时候，事务内部的操作与其他事务是隔离的，并发执行的各个事务之间不能互相干扰。

- 持久性
> 根据定义，持久性是指事务一旦提交，它对数据库的改变就应该是永久性的。接下来的其他操作或故障不应该对其有任何影响。

- 一致性
> 根据定义，一致性是指事务执行前后，数据处于一种合法的状态，这种状态是语义上的而不是语法上的。 那什么是合法的数据状态呢？ oK，这个状态是满足预定的约束就叫做合法的状态，再通俗一点，这状态是由你自己来定义的。满足这个状态，数据就是一致的，不满足这个状态，数据就是不一致的！

### (a,b,c)是一个组合索引，问where b=10 and a=10是否用了索引，那么where a=10 and b>3 and b<10呢
> 以下参考文章介绍了联合索引的最左原则
- [参考-掘金](https://juejin.im/post/5a6873fbf265da3e393a97fa)
- [参考-知乎](https://zhuanlan.zhihu.com/p/85553838)
- [参考](https://database.51cto.com/art/202002/610953.htm)

### 解释线程池的三个主要参数
- corePoolSize: 核心线程数线程数定义了最小可以同时运行的线程数量。
- maximumPoolSize: 当队列中存放的任务达到队列容量的时候，当前可以同时运行的线程数量变为最大线程数。
- workQueue: 当新任务来的时候会先判断当前运行的线程数量是否达到核心线程数，如果达到的话，信任就会被存放在队列中。

### 线程池都有哪几种工作队列
- ArrayBlockingQueue
> 是一个基于数组结构的有界阻塞队列，此队列按 FIFO（先进先出）原则对元素进行排序。

- LinkedBlockingQueue
> 一个基于链表结构的阻塞队列，此队列按FIFO （先进先出） 排序元素，吞吐量通常要高于ArrayBlockingQueue。静态工厂方法Executors.newFixedThreadPool()使用了这个队列

- SynchronousQueue
> 一个不存储元素的阻塞队列。每个插入操作必须等到另一个线程调用移除操作，否则插入操作一直处于阻塞状态，吞吐量通常要高于LinkedBlockingQueue，静态工厂方法Executors.newCachedThreadPool（5）使用了这个队列。

- PriorityBlockingQueue
> 一个具有优先级的无限阻塞队列。

### Synchronize和ReentrantLock的区别
- 两者都是可重入锁:两者都是可重入锁。“可重入锁”概念是：自己可以再次获取自己的内部锁。比如一个线程获得了某个对象的锁，此时这个对象锁还没有释放，当其再次想要获取这个对象的锁的时候还是可以获取的，如果不可锁重入的话，就会造成死锁。同一个线程每次获取锁，锁的计数器都自增1，所以要等到锁的计数器下降为0时才能释放锁。
- Synchronized 依赖于 JVM 而 ReenTrantLock 依赖于 API」:synchronized 是依赖于 JVM 实现的，前面我们也讲到了 虚拟机团队在 JDK1.6 为 synchronized 关键字进行了很多优化，但是这些优化都是在虚拟机层面实现的，并没有直接暴露给我们。ReenTrantLock 是 JDK 层面实现的（也就是 API 层面，需要 lock() 和 unlock 方法配合 try/finally 语句块来完成），所以我们可以通过查看它的源代码，来看它是如何实现的。
- ReenTrantLock 比 Synchronized 增加了一些高级功能
    - 等待可中断：过lock.lockInterruptibly()来实现这个机制。也就是说正在等待的线程可以选择放弃等待，改为处理其他事情。
    - 可实现公平锁
    - 可实现选择性通知（锁可以绑定多个条件）：线程对象可以注册在指定的Condition中，从而可以有选择性的进行线程通知，在调度线程上更加灵活。 在使用notify/notifyAll()方法进行通知时，被通知的线程是由 JVM 选择的，用ReentrantLock类结合Condition实例可以实现“选择性通知”
    - 性能已不是选择标准：在jdk1.6之前synchronized 关键字吞吐量随线程数的增加，下降得非常严重。1.6之后，synchronized 和 ReenTrantLock 的性能基本是持平了。

### 公平锁和非公平锁是如何实现的
> ReentrantLock的公平锁和非公平锁
> 学习AQS的时候，了解到AQS依赖于内部的FIFO同步队列来完成同步状态的管理，当前线程获取同步状态失败时，同步器会将当前线程以及等待状态等信息构造成一个Node对象并将其加入到同步队列，同时会阻塞当前线程，当同步状态释放时，会把首节点中的线程唤醒，使其再次尝试获取同步状态。

- ReentrantLock 默认采用非公平锁，除非在构造方法中传入参数 true 。
```java
//默认
public ReentrantLock() {
    sync = new NonfairSync();
}
//传入true or false
public ReentrantLock(boolean fair) {
    sync = fair ? new FairSync() : new NonfairSync();
}
```
#### 公平锁的lock方法
```java
static final class FairSync extends Sync {
    final void lock() {
        acquire(1);
    }
    // AbstractQueuedSynchronizer.acquire(int arg)
    public final void acquire(int arg) {
        if (!tryAcquire(arg) &&
            acquireQueued(addWaiter(Node.EXCLUSIVE), arg))
            selfInterrupt();
    }
    protected final boolean tryAcquire(int acquires) {
        final Thread current = Thread.currentThread();
        int c = getState();
        if (c == 0) {
            // 1. 和非公平锁相比，这里多了一个判断：是否有线程在等待
            if (!hasQueuedPredecessors() &&
                compareAndSetState(0, acquires)) {
                setExclusiveOwnerThread(current);
                return true;
            }
        }
        else if (current == getExclusiveOwnerThread()) {
            int nextc = c + acquires;
            if (nextc < 0)
                throw new Error("Maximum lock count exceeded");
            setState(nextc);
            return true;
        }
        return false;
    }
}
```
> 我们可以看到，在注释1的位置，有个!hasQueuedPredecessors()条件，意思是说当前同步队列没有前驱节点（也就是没有线程在等待）时才会去compareAndSetState(0, acquires)使用CAS修改同步状态变量。所以就实现了公平锁，根据线程发出请求的顺序获取锁。

#### 非公平锁的lock方法
```java
static final class NonfairSync extends Sync {
    final void lock() {
        // 2. 和公平锁相比，这里会直接先进行一次CAS，成功就返回了
        if (compareAndSetState(0, 1))
            setExclusiveOwnerThread(Thread.currentThread());
        else
            acquire(1);
    }
    // AbstractQueuedSynchronizer.acquire(int arg)
    public final void acquire(int arg) {
        if (!tryAcquire(arg) &&
            acquireQueued(addWaiter(Node.EXCLUSIVE), arg))
            selfInterrupt();
    }
    protected final boolean tryAcquire(int acquires) {
        return nonfairTryAcquire(acquires);
    }
}
/**
 * Performs non-fair tryLock.  tryAcquire is implemented in
 * subclasses, but both need nonfair try for trylock method.
 */
final boolean nonfairTryAcquire(int acquires) {
    final Thread current = Thread.currentThread();
    int c = getState();
    if (c == 0) {
        //3.这里也是直接CAS，没有判断前面是否还有节点。
        if (compareAndSetState(0, acquires)) {
            setExclusiveOwnerThread(current);
            return true;
        }
    }
    else if (current == getExclusiveOwnerThread()) {
        int nextc = c + acquires;
        if (nextc < 0) // overflow
            throw new Error("Maximum lock count exceeded");
        setState(nextc);
        return true;
    }
    return false;
}
```
> 非公平锁的实现在刚进入lock方法时会直接使用一次CAS去尝试获取锁，不成功才会到acquire方法中，如注释2。而在nonfairTryAcquire方法中并没有判断是否有前驱节点在等待，直接CAS尝试获取锁，如注释3。由此实现了非公平锁。

#### 总结
> 非公平锁和公平锁的两处不同： 1. 非公平锁在调用 lock 后，首先就会调用 CAS 进行一次抢锁，如果这个时候恰巧锁没有被占用，那么直接就获取到锁返回了。

### AQS都有什么公共方法

- isHeldExclusively()//该线程是否正在独占资源。只有用到condition才需要去实现它。
- tryAcquire(int)//独占方式。尝试获取资源，成功则返回true，失败则返回false。
- tryRelease(int)//独占方式。尝试释放资源，成功则返回true，失败则返回false。
- tryAcquireShared(int)//共享方式。尝试获取资源。负数表示失败；0表示成功，但没有剩余可用资源；正数表示成功，且有剩余资源。
- tryReleaseShared(int)//共享方式。尝试释放资源，成功则返回true，失败则返回false。

### Spring框架Bean的生命周期
- ![bean的生命周期](https://user-gold-cdn.xitu.io/2020/4/1/171353f25c01690b?imageView2/0/w/1280/h/960/format/webp/ignore-error/1)

### 那单例模式和prototype模式，spring都是怎么实现的
- Spring为实现单例类可继承，使用的是单例注册表的方式
    - 使用map实现注册表；
    - 使用protect修饰构造方法；
- Spring中，如果一个类被标记为”prototype”,每一次请求（将其注入到另一个bean中，或者以程序的方式调用容器的getBean()方法）都会产生一个新的bean实例。
> 但是,Spring不能对一个prototype Bean的整个生命周期负责，容器在初始化、配置、装饰或者是装配完一个prototype实例后，将它交给客户端，随后就对该prototype实例不闻不问了。不管何种作用域，容器都会调用所有对象的初始化生命周期回调方法，而对prototype而言，任何配置好的析构生命周期回调方法都将不会被调用。清除prototype作用域的对象并释放任何prototype bean所持有的昂贵资源，都是客户端代码的职责。


### session和cookie的区别
#### cookie
Cookie 是服务器发送到用户浏览器并保存在本地的一小块数据，它会在浏览器之后向同一服务器再次发起请求时被携带上，用于告知服务端两个请求是否来自同一浏览器。由于之后每次请求都会需要携带 Cookie 数据，因此会带来额外的性能开销（尤其是在移动环境下）。
- 会话状态管理（如用户登录状态、购物车、游戏分数或其它需要记录的信息）
- 个性化设置（如用户自定义设置、主题等）
- 浏览器行为跟踪（如跟踪分析用户行为等）

#### session
Session 可以存储在服务器上的文件、数据库或者内存中。也可以将 Session 存储在 Redis 这种内存型数据库中，效率会更高。
- 用户进行登录时，用户提交包含用户名和密码的表单，放入 HTTP 请求报文中；
- 服务器验证该用户名和密码，如果正确则把用户信息存储到 Redis 中，它在 Redis 中的 Key 称为 Session ID；
- 服务器返回的响应报文的 Set-Cookie 首部字段包含了这个 Session ID，客户端收到响应报文之后将该 Cookie 值存入浏览器中；
- 客户端之后对同一个服务器进行请求时会包含该 Cookie 值，服务器收到之后提取出 Session ID，从 Redis 中取出用户信息，继续之前的业务操作。

注意：Session ID 的安全性问题，不能让它被恶意攻击者轻易获取，那么就不能产生一个容易被猜到的 Session ID 值。此外，还需要经常重新生成 Session ID。在对安全性要求极高的场景下，例如转账等操作，除了使用 Session 管理用户状态之外，还需要对用户进行重新验证，比如重新输入密码，或者使用短信验证码等方式 Cookie和Session的选择

### get请求和post请求的区别
1. GET使用URL或Cookie传参，而POST将数据放在BODY中
2. GET方式提交的数据有长度限制，则POST的数据则可以非常大
3. POST比GET安全，因为数据在地址栏上不可见，没毛病
4. **本质区别**：GET请求是幂等性的，POST请求不是。
> 这里的幂等性：幂等性是指一次和多次请求某一个资源应该具有同样的副作用。简单来说意味着对同一URL的多个请求应该返回同样的结果。

### 线程和进程

#### 进程
- **进程**是**程序的一次执行过程**，是**系统运行程序的基本单位**，因此进程是动态的。
- 系统运行一个程序即是一个进程从创建，运行到消亡的过程。简单来说，**一个进程就是一个执行中的程序**，
- 它在计算机中一个指令接着一个指令地执行着，同时，每个进程还占有某些系统资源如CPU时间，内存空间，文件，输入输出设备的使用权等等。
- 换句话说，当程序在执行时，将会被操作系统载入内存中。 
- **线程是进程划分成的更小的运行单位**。
- 线程和进程最大的不同在于基本上各进程是独立的，而各线程则不一定，因为同一进程中的线程极有可能会相互影响。
- 从另一角度来说，进程属于操作系统的范畴，主要是同一段时间内，可以同时执行一个以上的程序，而线程则是在同一程序内几乎同时执行一个以上的程序段。

#### 线程
- **线程**与进程相似，但**线程是一个比进程更小的执行单位**。
- 一个进程在其执行的过程中可以产生**多个线程**。
- 与进程不同的是同类的**多个线程共享同一块内存空间和一组系统资源**，所以系统在产生一个线程，或是在各个线程之间作切换工作时，负担要比进程小得多，也正因为如此，**线程也被称为轻量级进程**。

### 搜索关键字在日志文件中哪一行是什么命令，看最后1000行数据的命令

- 搜索关键字
> cat 文件名 | grep "关键词"
> grep -i "关键词" 文件名

- 查看前、后n行的数据的命令
> `head -n 10 /etc/profile`
> `tail  -n 5 /etc/profile`

### 计算机网络应用层都有什么协议
![协议端口号](http://media.dreamcat.ink/uPic/EuHz2s.png)

### tcp的滑动窗口是怎么工作的
> TCP滑动窗口技术通过动态改变窗口大小来调节两台主机间数据传输

> TCP 中采用滑动窗口来进行传输控制，滑动窗口的大小意味着接收方还有多大的缓冲区可以用于接收数据。
> 发送方可以通过滑动窗口的大小来确定应该发送多少字节的数据。当滑动窗口为 0 时，发送方一般不能再发送数据报，但有两种情况除外，一种情况是可以发送紧急数据，
> 例如，允许用户终止在远端机上的运行进程。另一种情况是发送方可以发送一个 1 字节的数据报来通知接收方重新声明它希望接收的下一字节及发送方的滑动窗口大小。

### GC的CMS回收器的步骤
- 初始标记：暂停所有的其他线程，并记录下直接与 root 相连的对象，速度很快 ；
- 并发标记： 同时开启 GC 和用户线程，用一个闭包结构去记录可达对象。但在这个阶段结束，这个闭包结构并不能保证包含当前所有的可达对象。因为用户线程可能会不断的更新引用域，所以 GC 线程无法保证可达性分析的实时性。所以这个算法里会跟踪记录这些发生引用更新的地方。
- 重新标记： 重新标记阶段就是为了修正并发标记期间因为用户程序继续运行而导致标记产生变动的那一部分对象的标记记录，这个阶段的停顿时间一般会比初始标记阶段的时间稍长，远远比并发标记阶段时间短
- 并发清除：开启用户线程，同时 GC 线程开始对为标记的区域做清扫。

## 美团成都二面
### 数据库索引的实现方式（hash，B+）
#### 哈希索引
哈希索引就是采用一定的哈希算法，把键值换算成新的哈希值，检索时不需要类似B+树那样从根节点到叶子节点逐级查找，只需一次哈希算法即可立刻定位到相应的位置，速度非常快。

#### B+
- MyISAM
MyISAM，**B+Tree叶节点的data域存放的是数据记录的地址**，在索引检索的时候，首先按照B+Tree搜索算法搜索索引，如果指定的key存在，则取出其data域的值，然后以data域的值为地址读区相应的数据记录，这被称为“非聚簇索引”
- InnoDB
InnoDB，其数据文件本身就是索引文件，相比MyISAM，**索引文件和数据文件是分离的**，**其表数据文件本身就是按B+Tree组织的一个索引结构，树的节点data域保存了完整的数据记录**，这个索引的key是数据表的主键，因此InnoDB表数据文件本身就是主索引。
这被称为“聚簇索引”或者聚集索引，而其余的索引都作为辅助索引，辅助索引的data域存储相应记录主键的值而不是地址，这也是和MyISAM不同的地方，在根据主索引搜索时，直接找到key所在的节点即可取出数据；在根据辅助索引查找时，则需要先取出主键的值，在走一遍主索引。
因此，在设计表的时候，不建议使用过长的字段为主键，也不建议使用非单调的字段作为主键，这样会造成主索引频繁分裂。

#### 二者区别
- 如果是等值查询，那么哈希索引明显有绝对优势，因为只需要经过一次算法即可找到相应的键值；当然了，这个前提是，键值都是唯一的。如果键值不是唯一的，就需要先找到该键所在位置，然后再根据链表往后扫描，直到找到相应的数据；
- 如果是范围查询检索，这时候哈希索引就毫无用武之地了，因为原先是有序的键值，经过哈希算法后，有可能变成不连续的了，就没办法再利用索引完成范围查询检索；
- 同理，哈希索引也没办法利用索引完成排序，以及like ‘xxx%’ 这样的部分模糊查询（这种部分模糊查询，其实本质上也是范围查询）；
- 哈希索引也不支持多列联合索引的最左匹配规则；
- B+树索引的关键字检索效率比较平均，不像B树那样波动幅度大，在有大量重复键值情况下，哈希索引的效率也是极低的，因为存在所谓的哈希碰撞问题。

### MySQL的的默认隔离级别、防止了什么读？
**脏读和可重复读**

### 可重复读级别下的MySQL可以防止幻读吗，如何实现的？
用next-key lock可以防止，快照读不可以

### 为什么数据库用B+树而不用B树
> B树（英语: B-tree）是一种自平衡的树，能够保持数据有序。这种数据结构能够让查找数据、顺序访问、插入数据及删除的动作，都在对数时间内完成。B树，概括来说是一个一般化的二叉查找树（binary search tree），可以拥有最多2个子节点。与自平衡二叉查找树不同，B树适用于读写相对大的数据块的存储系统，例如磁盘。
> B+ 树是一种树数据结构，通常用于关系型数据库（如Mysql）和操作系统的文件系统中。B+ 树的特点是能够保持数据稳定有序，其插入与修改拥有较稳定的对数时间复杂度。B+ 树元素自底向上插入，这与二叉树恰好相反。
> 在B树基础上，为叶子结点增加链表指针（B树+叶子有序链表），所有关键字都在叶子结点 中出现，非叶子结点作为叶子结点的索引；B+树总是到叶子结点才命中。
> b+树的非叶子节点不保存数据，只保存子树的临界值（最大或者最小），所以同样大小的节点，b+树相对于b树能够有更多的分支，使得这棵树更加矮胖，查询时做的IO操作次数也更少。

### Object类里面有什么方法
- clone方法
> 保护方法，实现对象的浅复制，只有实现了Cloneable接口才可以调用该方法，否则抛出CloneNotSupportedException异常。

- getClass方法
> final方法，获得运行时类型。

- toString方法
> 该方法用得比较多，一般子类都有覆盖。

- finalize方法
> 该方法用于释放资源。因为无法确定该方法什么时候被调用，很少使用。

- equals方法
> 该方法是非常重要的一个方法。一般equals和==是不一样的，但是在Object中两者是一样的。子类一般都要重写这个方法。

- hashCode方法
> 该方法用于哈希查找，重写了equals方法一般都要重写hashCode方法。这个方法在一些具有哈希功能的Collection中用到。

- wait方法
> wait方法就是使当前线程等待该对象的锁，当前线程必须是该对象的拥有者，也就是具有该对象的锁。wait()方法一直等待，直到获得锁或者被中断。wait(long timeout)设定一个超时间隔，如果在规定时间内没有获得锁就返回。
> 调用该方法后当前线程进入睡眠状态，直到以下事件发生。
> - 其他线程调用了该对象的notify方法。
> - 其他线程调用了该对象的notifyAll方法。
> - 其他线程调用了interrupt中断该线程。
> - 时间间隔到了。

- notify方法
> 该方法唤醒在该对象上等待的某个线程。

- notifyAll方法
> 该方法唤醒在该对象上等待的所有线程。


### 四次挥手的过程
- 客户端-发送一个 FIN，用来关闭客户端到服务器的数据传送
- 服务器-收到这个 FIN，它发回一 个 ACK，确认序号为收到的序号加1 。和 SYN 一样，一个 FIN 将占用一个序号
- 服务器-关闭与客户端的连接，发送一个FIN给客户端
- 客户端-发回 ACK 报文确认，并将确认序号设置为收到序号加1

> 任何一方都可以在数据传送结束后发出连接释放的通知，待对方确认后进入半关闭状态。当另一方也没有数据再发送的时候，则发出连接释放通知，对方确认后就完全关闭了TCP连接。举个例子：A 和 B 打电话，通话即将结束后，A 说“我没啥要说的了”，B回答“我知道了”，但是 B 可能还会有要说的话，A 不能要求 B 跟着自己的节奏结束通话，于是 B 可能又巴拉巴拉说了一通，最后 B 说“我说完了”，A 回答“知道了”，这样通话才算结束。

### Linux命令，如何查看所有端口信息
- lsof
> lsof -i:端口号

- netstat
> netstat -tunlp | grep 端口号
> netstat -ntlp   //查看当前所有tcp端口

### 跳台阶的那道经典dp题目，口述如何解决
```java
class Solution {
    public int climbStairs(int n) {
        if(n <= 2) return n;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }
}
```
![climbStairs](http://media.dreamcat.ink/uPic/climbStairs.png)

### 写代码：写双重验证的单例模式
```java
public class Singleton {

    private volatile static Singleton uniqueInstance; // 第一步

    private Singleton() { // 第二步，私有
    }

    public static Singleton getUniqueInstance() {
       //先判断对象是否已经实例过，没有实例化过才进入加锁代码
        if (uniqueInstance == null) { // 双重校验
            //类对象加锁
            synchronized (Singleton.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new Singleton();
                }
            }
        }
        return uniqueInstance;
    }
}
```
![Singleton](http://media.dreamcat.ink/uPic/Singleton.png)

### 单链表反转
```java
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
```
![reverseList](http://media.dreamcat.ink/uPic/reverseList.png)

## 美团成都三面
### redis如何保持和mysql的数据一致性

一般来说，就是如果你的系统不是严格要求缓存+数据库必须一致性的话，缓存可以稍微的跟数据库偶尔有不一致的情况，最好不要做这个方案，**读请求和写请求串行化，串到一个内存队列里去**，这样就可以保证一定不会出现不一致的情况

### 说一下都有什么类型二叉树
- 二叉查找树-BST
- 平衡二叉树-AVL
- 红黑树
- B-树
- B+树

### 构建一个二叉树，做前序的非递归遍历
```java
	public static ArrayList preOrder1(TreeNode root){
		Stack<TreeNode> stack = new Stack<TreeNode>();
		ArrayList alist = new ArrayList();
		TreeNode p = root;
		while(p != null || !stack.empty()){
			while(p != null){
				alist.add(p.val);
				stack.push(p);
				p = p.left;
			}
			if(!stack.empty()){
				TreeNode temp = stack.pop();
				p = temp.right;
			}
		}
		return alist;
	}
```
![二叉树-非递归-preOrder1](http://media.dreamcat.ink/uPic/二叉树-非递归-preOrder1.png)


