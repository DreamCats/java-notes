## 美团一面
### 自我介绍 + 项目简单介绍
略

### 集合有哪些
- Set
    - TreeSet
    - HashSet
    - LinkedHashSet
- List
    - ArrayList
    - Vector
    - LinkedList
- Map
    - TreeMap
    - HashMap
    - HashTable

### ArrayList和LinkedList区别
- ArrayList实现了List接口，是顺序容器，即元素存放的数据与放进去的顺序相同，允许放入null元素，底层通过数组实现。
- LinkedList同时实现了List接口和Deque接口，也就是说它既可以看作一个顺序容器，又可以看作一个队列（Queue），同时又可以看作一个栈（Stack）。LinkedList底层通过双向链表实现。

### hashmap怎么扩容（多线程扩容为什么会死循环），put过程
#### 扩容
> resize()

1. 创建一个新的Entry空数组，长度是原来的2倍。
2. 遍历原Entry数组，把所有的Entry重新Hash到新数组里。为什么要重新Hash呢？因为长度扩大以后，Hash的规则也随之改变了。

**多线程问题**：
> 多个线程并发进行时，因为一个线程先期完成了扩容，将原Map的链表重新散列到自己的表中，并且链表变成了倒序，后一个线程再扩容时，又进行自己的散列，再次将倒序链表变为正序链表。于是形成了一个环形链表，当get表中不存在的元素时，造成死循环。

#### put
1. 1.7
- 判断当前数组是否需要初始化
- 如果key为空，则put一个空值进去
- 根据key计算hashcode
- 根据计算的hashcode定位index的桶
- 如果桶是一个链表，则需要遍历判断里面的hashcode、key是否和传入的key相等，如果相等则进行覆盖，并返回原来的值
- 如果桶是空的，说明当前位置没有数据存入，此时新增一个Entry对象写入当前位置。

2. 1.8
- 判断当前桶是否为空，空的就需要初始化（resize中会判断是否进行初始化）。
- 根据当前key的hashcode定位到具体的桶中并判断是否为空，为空则表明没有Hash冲突，就直接在当前位置创建一个新桶。
- 如果当前桶有值（Hash冲突），那么就要比较当前桶中的key、key的hashcode与写入的key是否相等，相等就赋值给e，在第8步的时候会统一进行赋值及返回。
- 如果当前桶为「红黑树」，那就要按照红黑树的方式写入数据。
- 如果是个链表，就需要将当前的key、value封装称一个新节点写入到当前桶的后面形成链表。
- 接着判断当前链表的大小是否「大于预设的阈值」，大于就要转换成为「红黑树」
- 如果在遍历过程中找到key相同时直接退出遍历。
- 如果e != null就相当于存在相同的key，那就需要将值覆盖。
- 最后判断是否需要进行扩容。

### concurrentHashMap 1.7和1.8
1. 1.7
- 唯一的区别就是其中的核心数据如 value ，以及链表都是 volatile 修饰的，保证了获取时的可见性。
- ConcurrentHashMap 采用了「分段锁」技术，其中 Segment 继承于 ReentrantLock。
- 不会像HashTable那样不管是put还是get操作都需要做同步处理，理论上 ConcurrentHashMap 支持 CurrencyLevel (Segment 数组数量)的线程并发。
- 每当一个线程占用锁访问一个 Segment 时，不会影响到其他的 Segment。

put:
- 将当前的Segment中的table通过key的hashcode定位到HashEntry
- 遍历该HashEntry，如果不为空则判断传入的key和当前遍历的key是否相等，相等则覆盖旧的value
- 不为空则需要新建一个HashEntry并加入到Segment中，同时会先判断是否需要扩容
- 最后会解除在1中所获取当前Segment的锁。

2. 1.8
- 其中抛弃了原有的 Segment 分段锁，而采用了 CAS + synchronized 来保证并发安全性

put:
- 根据key计算出hashcode
- 判断是否需要进行初始化
- f即为当前key定位出的Node，如果为空表示当前位置可以写入数据，利用CAS尝试写入，失败则自旋保证成功。
- 如果当前位置的hashcode == MOVED == -1，则需要进行扩容。
- 如果都不满足，则利用synchronized锁写入数据
- 如果数量大于TREEIFY_THRESHOLD 则要转换为红黑树。

### 接口和抽象类区别
- 接口的方法默认是 public，所有方法在接口中不能有实现(Java 8 开始接口方法可以有默认实现），而抽象类可以有非抽象的方法。
- 接口中除了static、final变量，不能有其他变量，而抽象类中则不一定。
- 一个类可以实现多个接口，但只能实现一个抽象类。接口自己本身可以通过implement关键字扩展多个接口。
- 接口方法默认修饰符是public，抽象方法可以有public、protected和default这些修饰符（抽象方法就是为了被重写所以不能使用private关键字修饰！）。
- 从设计层面来说，抽象是对类的抽象，是一种模板设计，而接口是对行为的抽象，是一种行为的规范。

### JVM内存分区
两张图：
![](https://user-gold-cdn.xitu.io/2020/3/31/17130c2c0a57c0f7?imageView2/0/w/1280/h/960/format/webp/ignore-error/1)
![](https://user-gold-cdn.xitu.io/2020/3/31/17130c2c0bed60c9?imageView2/0/w/1280/h/960/format/webp/ignore-error/1)






