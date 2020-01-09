## 多线程总结



## 容器
### Map/Set
1. 不需要同步的时候可以选择
HashMap
2. 不需要同步，需要排序可以选择
TreeMap
3. 不需要同步，且需要双向队列或者栈的可选择
LinkedHashMap
4. 同步，并发量小可以选择
HashTable Collections.sychronizedXXX
5. 并发量大，可选择
ConcurrentHashMap
6. 在5的前提下，需要排序
ConcurrentSkipListMap

### 队列
1. 不需要同步，可以选择
ArrayList LinkedList
2. 并发量小，可选择
Collections.synchroizedXXX
CopyOnWriteList(适合大量读，少量写)
3. 并发量大，可选择
ConcurrentLinkedQueue
BlockingQueue(无界阻塞式队列) 
ArrayBQ(有界) 
TransferBQ(直接转给消费者)
DelayQueue(执行定时任务)

## 线程池
1. Executor
2. ExecutorService
3. Callable
4. Future
5. 6种线程池
- newFixedThreadPool(固定线程池)
- newCachedThreadPool(带有缓存线程池，默认空闲线程60s)
- newSingleThreadExecutor(单个线程)
- newScheduledThreadPoold(定时线程池)
- newWorkStealingPool(空闲线程去抢占其他线程的任务队列的任务)
- ForkJoinPool(适合大规模计算)

## 索引
- [synchronized 加锁](./src/T1.java)
- [加锁与不加锁的区别](./src/T2.java)
- [产生脏读问题](./src/Account.java)
- [同步和非同步方法是否可以同时使用](./src/T3.java)
- [synchronized是可重入锁](./src/T4.java)
- [异常释放锁](./src/T5.java)
- [volatile](./src/T6.java)
- [volatile并不能保证的多线程的一致性](./src/T7.java)
- [锁粒度](./src/T8.java)
- [锁对象变了，锁就被释放了](./src/T9.java)
- [不要以字符串常量作为锁定对象](./src/T10.java)
- [线程通信](./src/T11.java)
- [ReentrantLock](./src/T12.java)
- [trylock](./src/T13.java)
- [ReentrantLock的公平锁](./src/T14.java)
- [生产者消费者](./src/T15.java)
- [用lock和condition进行生产者和消费者](./src/T16.java)
- [ThreadLocal](./src/T17.java)
- [单例，内部类方式，不需要加锁](./src/T18.java)
- [售票程序](./src/T19.java)
- [ConcurrentMap](./src/T20.java)
- [利用容器LinkedBlockingQueue生产者消费者](./src/T21.java)
- [线程池-newFixedThreadPool](./src/T22.java)
- [future](./src/T23.java)
- [并行计算的小例子](./src/T24.java)
- [newCachedThreadPool](./src/T25.java)
- [newSingleThreadExecutor](./src/T26.java)
- [newScheduledThreadPool](./src/T27.java)
- [WorkStealingPool](./src/T28.java)
- [ForkJoinPool](./src/T29.java)
- [parallel的小例子](./src/T30.java)
- [死锁的模拟](./src/T31.java)
- [Semaphore例子](./src/T32.java)