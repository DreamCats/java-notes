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