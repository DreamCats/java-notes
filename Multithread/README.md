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
- [synchronized 加锁](./src/com/basic/T1.java)
- [加锁与不加锁的区别](./src/com/basic/T2.java)
- [产生脏读问题](./src/com/basic/Account.java)
- [同步和非同步方法是否可以同时使用](./src/com/basic/T3.java)
- [synchronized是可重入锁](./src/com/basic/T4.java)
- [异常释放锁](./src/com/basic/T5.java)
- [volatile](./src/com/basic/T6.java)
- [volatile并不能保证的多线程的一致性](./src/com/basic/T7.java)
- [锁粒度](./src/com/basic/T8.java)
- [锁对象变了，锁就被释放了](./src/com/basic/T9.java)
- [不要以字符串常量作为锁定对象](./src/com/basic/T10.java)
- [线程通信](./src/com/basic/T11.java)
- [ReentrantLock](./src/com/basic/T12.java)
- [trylock](./src/com/basic/T13.java)
- [ReentrantLock的公平锁](./src/com/basic/T14.java)
- [生产者消费者](./src/com/basic/T15.java)
- [用lock和condition进行生产者和消费者](./src/com/basic/T16.java)
- [ThreadLocal](./src/com/basic/T17.java)
- [单例，内部类方式，不需要加锁](./src/com/basic/T18.java)
- [售票程序](./src/com/basic/T19.java)
- [ConcurrentMap](./src/com/basic/T20.java)
- [利用容器LinkedBlockingQueue生产者消费者](./src/com/basic/T21.java)
- [线程池-newFixedThreadPool](./src/com/basic/T22.java)
- [future](./src/com/basic/T23.java)
- [并行计算的小例子](./src/com/basic/T24.java)
- [newCachedThreadPool](./src/com/basic/T25.java)
- [newSingleThreadExecutor](./src/com/basic/T26.java)
- [newScheduledThreadPool](./src/com/basic/T27.java)
- [WorkStealingPool](./src/com/basic/T28.java)
- [ForkJoinPool](./src/com/basic/T29.java)
- [parallel的小例子](./src/com/basic/T30.java)
- [死锁的模拟](./src/com/basic/T31.java)
- [Semaphore例子](./src/com/basic/T32.java)
- [CountDownLatch例子](./src/com/basic/T33.java)
- [CyclicBarrier的使用示例](./src/com/basic/T34.java)