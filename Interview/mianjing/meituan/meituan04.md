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

