## 引言

**HashMap 和 ConcurrentHashMap面试常问，务必理解和掌握**

## HashMap

众所周知，HashMap的底层结构是**数组和链表**组成的，不过在jdk1.7和jdk1.8中具体实现略有不同。

<!-- more -->

### jdk1.7

先看图![](https://i.loli.net/2019/05/08/5cd1d2be77958.jpg)

再看看1.7的实现![](https://i.loli.net/2019/05/08/5cd1d2bfd6aba.jpg)

介绍成员变量：

- 初始化桶大小，因为底层是数组，所以这是数组默认的大小。
- 桶最大值。
- 默认的负载因子（0.75）
- table真正存放数据的数组。
- map存放数量的大小
- 桶大小，可在构造函数时显式指定。
- 负载因子，可在构造函数时显式指定。

#### 负载因子

源代码

```java
public HashMap() {
    this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR); // 桶和负载因子
}
public HashMap(int initialCapacity, float loadFactor) {
    if (initialCapacity < 0)
        throw new IllegalArgumentException("Illegal initial capacity: " +
                                           initialCapacity);
    if (initialCapacity > MAXIMUM_CAPACITY)
        initialCapacity = MAXIMUM_CAPACITY;
    if (loadFactor <= 0 || Float.isNaN(loadFactor))
        throw new IllegalArgumentException("Illegal load factor: " +
                                           loadFactor);
    this.loadFactor = loadFactor;
    threshold = initialCapacity;
    init();
}
```

- 给定的默认容量为16，负载因子为0.75.
- Map在使用过程中不断的往里面存放数据，当数量达到了`16 * 0.75 = 12`就需要将当前16的容量进行扩容，而扩容这个过程涉及到rehash（重新哈希）、复制数据等操作，所有非常消耗性能。
- 因此通常建议能提前预估HashMap的大小最好，尽量的减少扩容带来的额外性能损耗。

#### Entry

`transient Entry<K,V>[] table = (Entry<K,V>[]) EMPTY_TABLE;`

如何定义呢？![](https://i.loli.net/2019/05/08/5cd1d2c08e693.jpg)

Entry是Hashmap中的一个内部类，从他的成员变量很容易看出：

- key就是写入时的键
- value自然就是值
- 开始的时候就提到HashMap是由数组和链表组成，所以这个next就是用于实现链表结构
- hash存放的是当前key的hashcode

#### put

```java
public V put(K key, V value) {
    if (table == EMPTY_TABLE) {
        inflateTable(threshold); // 判断数组是否需要初始化
    }
    if (key == null)
        return putForNullKey(value); // 判断key是否为空
    int hash = hash(key); // 计算hashcode
    int i = indexFor(hash, table.length); // 计算桶
    for (Entry<K,V> e = table[i]; e != null; e = e.next) {
        Object k;
        if (e.hash == hash && ((k = e.key) == key || key.equals(k))) { // 遍历判断链表中的key和hashcode是否相等，等就替换
            V oldValue = e.value;
            e.value = value;
            e.recordAccess(this);
            return oldValue;
        }
    }
    modCount++;
    addEntry(hash, key, value, i); // 没有就添加新的呗
    return null;
}
```

- 判断当前数组是否需要初始化
- 如果key为空，则put一个空值进去
- 根据key计算hashcode
- 根据计算的hashcode定位index的桶
- 如果桶是一个链表，则需要遍历判断里面的hashcode、key是否和传入的key相等，如果相等则进行覆盖，并返回原来的值
- 如果桶是空的，说明当前位置没有数据存入，此时新增一个Entry对象写入当前位置。

```java
void addEntry(int hash, K key, V value, int bucketIndex) {
    if ((size >= threshold) && (null != table[bucketIndex])) {// 是否扩容
        resize(2 * table.length);
        hash = (null != key) ? hash(key) : 0;
        bucketIndex = indexFor(hash, table.length);
    }
    createEntry(hash, key, value, bucketIndex);
}
void createEntry(int hash, K key, V value, int bucketIndex) {
    Entry<K,V> e = table[bucketIndex];
    table[bucketIndex] = new Entry<>(hash, key, value, e);
    size++;
}
```

- 当调用addEntry写入Entry时需要判断是否需要扩容
- 如果需要就进行两倍扩充，并将当前的key重新hash并定位。
- 而在createEntry中会将当前位置的桶传入到新建的桶中，如果当前桶油值就会在位置形成链表。

#### get

```java
public V get(Object key) {
    if (key == null) // 判断key是否为空
        return getForNullKey(); // 为空，就返回空值
    Entry<K,V> entry = getEntry(key); // get entry
    return null == entry ? null : entry.getValue();
}
final Entry<K,V> getEntry(Object key) {
    if (size == 0) {
        return null;
    }
    int hash = (key == null) ? 0 : hash(key); //根据key和hashcode
    for (Entry<K,V> e = table[indexFor(hash, table.length)];
         e != null;
         e = e.next) {
        Object k;
        if (e.hash == hash &&
            ((k = e.key) == key || (key != null && key.equals(k))))
            return e;
    }
    return null;
}
```

- 首先根据key计算hashcode，然后定位具体的桶
- 判断该位置是否为链表
- 不是链接就根据key和hashcode是否相等来返回值
- 为链表则需要遍历直到key和hashcode相等就返回值
- 啥都没得，就返回null

### jdk1.8

不知道 1.7 的实现大家看出需要优化的点没有？

其实一个很明显的地方就是链表

**当 Hash 冲突严重时，在桶上形成的链表会变的越来越长，这样在查询时的效率就会越来越低；时间复杂度为 `O(N)`。**

![](https://i.loli.net/2019/05/08/5cd1d2c1c1cd7.jpg)

看看成员变量

```java
static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16
/**
 * The maximum capacity, used if a higher value is implicitly specified
 * by either of the constructors with arguments.
 * MUST be a power of two <= 1<<30.
 */
static final int MAXIMUM_CAPACITY = 1 << 30;
/**
 * The load factor used when none specified in constructor.
 */
static final float DEFAULT_LOAD_FACTOR = 0.75f;
static final int TREEIFY_THRESHOLD = 8;
transient Node<K,V>[] table;
/**
 * Holds cached entrySet(). Note that AbstractMap fields are used
 * for keySet() and values().
 */
transient Set<Map.Entry<K,V>> entrySet;
/**
 * The number of key-value mappings contained in this map.
 */
transient int size;
```

- `TREEIFY_THRESHOLD` 用于判断是否需要将链表转换为红黑树的阈值。
- HashEntry 修改为 Node。
- Node 的核心组成其实也是和 1.7 中的 HashEntry 一样，存放的都是 `key value hashcode next` 等数据。

#### put

![](https://i.loli.net/2019/05/08/5cd1d2c378090.jpg)

- 判断当前桶是否为空，空的就需要初始化（resize中会判断是否进行初始化）
- 根据当前key的hashcode定位到具体的桶中并判断是否为空，为空则表明没有Hash冲突，就直接在当前位置创建一个新桶
- 如果当前桶油值（Hash冲突），那么就要比较当前桶中的key、key的hashcode与写入的key是否相等，相等就赋值给e，在第8步的时候会统一进行赋值及返回
- 如果当前桶为红黑树，那就要按照红黑树的方式写入数据
- 如果是个链表，就需要将当前的key、value封装称一个新节点写入到当前桶的后面形成链表。
- 接着判断当前链表的大小是否大于预设的阈值，大于就要转换成为红黑树
- 如果在遍历过程中找到key相同时直接退出遍历。
- 如果`e != null`就相当于存在相同的key，那就需要将值覆盖。
- 最后判断是否需要进行扩容。

#### get

```java
public V get(Object key) {
    Node<K,V> e;
    return (e = getNode(hash(key), key)) == null ? null : e.value;
}
final Node<K,V> getNode(int hash, Object key) {
    Node<K,V>[] tab; Node<K,V> first, e; int n; K k;
    if ((tab = table) != null && (n = tab.length) > 0 &&
        (first = tab[(n - 1) & hash]) != null) {
        if (first.hash == hash && // always check first node
            ((k = first.key) == key || (key != null && key.equals(k))))
            return first;
        if ((e = first.next) != null) {
            if (first instanceof TreeNode)
                return ((TreeNode<K,V>)first).getTreeNode(hash, key);
            do {
                if (e.hash == hash &&
                    ((k = e.key) == key || (key != null && key.equals(k))))
                    return e;
            } while ((e = e.next) != null);
        }
    }
    return null;
}
```

- 首先将key hash之后取得所定位的桶
- 如果桶为空，则直接返回null
- 否则判断桶的第一个位置（有可能是链表、红黑树）的key是否为查询的key，是就直接返回value
- 如果第一个不匹配，则判断它的下一个是红黑树还是链表
- 红黑树就按照树的查找方式返回值
- 不然就按照链表的方式遍历匹配返回值

**从这两个核心方法（get/put）可以看出 1.8 中对大链表做了优化，修改为红黑树之后查询效率直接提高到了 `O(logn)`。**

## 问题

但是 HashMap 原有的问题也都存在，比如在并发场景下使用时容易出现死循环。

```java
final HashMap<String, String> map = new HashMap<String, String>();
for (int i = 0; i < 1000; i++) {
    new Thread(new Runnable() {
        @Override
        public void run() {
            map.put(UUID.randomUUID().toString(), "");
        }
    }).start();
}
```

- HashMap扩容的时候会调用resize()方法，就是这里的并发操作容易在一个桶上形成环形链表
- 这样当获取一个不存在的key时，计算出的index正好是环形链表的下标就会出现死循环。
- 但是1.7的头插法造成的问题，1.8改变了插入顺序，就解决了这个问题，但是为了内存可见性等安全性，还是需要ConCurrentHashMap

![](https://i.loli.net/2019/05/08/5cd1d2c4ede54.jpg)

## 遍历方式

还有一个值得注意的是 HashMap 的遍历方式，通常有以下几种：

```java
Iterator<Map.Entry<String, Integer>> entryIterator = map.entrySet().iterator();
        while (entryIterator.hasNext()) {
            Map.Entry<String, Integer> next = entryIterator.next();
            System.out.println("key=" + next.getKey() + " value=" + next.getValue());
        }
        
Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()){
            String key = iterator.next();
            System.out.println("key=" + key + " value=" + map.get(key));
        }
```

- 建议使用第一种，同时可以把key value取出。
- 第二种还需要通过key取一次key，效率较低。

## ConCurrentHashMap

### jdk1.7

![](https://i.loli.net/2019/05/08/5cd1d2c5ce95c.jpg)

- Segment数组
- HashEntry组成
- 和HashMap一样，仍然是数组加链表

```java
/**
 * Segment 数组，存放数据时首先需要定位到具体的 Segment 中。
 */
final Segment<K,V>[] segments;
transient Set<K> keySet;
transient Set<Map.Entry<K,V>> entrySet;
```

Segment 是 ConcurrentHashMap 的一个内部类，主要的组成如下：

```java
static final class Segment<K,V> extends ReentrantLock implements Serializable {
       private static final long serialVersionUID = 2249069246763182397L;
       
       // 和 HashMap 中的 HashEntry 作用一样，真正存放数据的桶
       transient volatile HashEntry<K,V>[] table;
       transient int count;
       transient int modCount;
       transient int threshold;
       final float loadFactor;
       
}
```

![](https://i.loli.net/2019/05/08/5cd1d2c635c69.jpg)

- 唯一的区别就是其中的核心数据如 value ，以及链表都是 volatile 修饰的，保证了获取时的可见性。
- ConcurrentHashMap 采用了分段锁技术，其中 Segment 继承于 ReentrantLock。
- 不会像HashTable那样不管是put还是get操作都需要做同步处理，理论上 ConcurrentHashMap 支持 CurrencyLevel (Segment 数组数量)的线程并发。
- 每当一个线程占用锁访问一个 Segment 时，不会影响到其他的 Segment。

#### put

```java
public V put(K key, V value) {
    Segment<K,V> s;
    if (value == null)
        throw new NullPointerException();
    int hash = hash(key);
    int j = (hash >>> segmentShift) & segmentMask;
    if ((s = (Segment<K,V>)UNSAFE.getObject          // nonvolatile; recheck
         (segments, (j << SSHIFT) + SBASE)) == null) //  in ensureSegment
        s = ensureSegment(j);
    return s.put(key, hash, value, false);
}
```

- 通过key定位到Segment，之后在对应的Segment中进行具体的put

```java
final V put(K key, int hash, V value, boolean onlyIfAbsent) {
    HashEntry<K,V> node = tryLock() ? null :
        scanAndLockForPut(key, hash, value);
    V oldValue;
    try {
        HashEntry<K,V>[] tab = table;
        int index = (tab.length - 1) & hash;
        HashEntry<K,V> first = entryAt(tab, index);
        for (HashEntry<K,V> e = first;;) {
            if (e != null) {
                K k;
                if ((k = e.key) == key ||
                    (e.hash == hash && key.equals(k))) {
                    oldValue = e.value;
                    if (!onlyIfAbsent) {
                        e.value = value;
                        ++modCount;
                    }
                    break;
                }
                e = e.next;
            }
            else {
                if (node != null)
                    node.setNext(first);
                else
                    node = new HashEntry<K,V>(hash, key, value, first);
                int c = count + 1;
                if (c > threshold && tab.length < MAXIMUM_CAPACITY)
                    rehash(node);
                else
                    setEntryAt(tab, index, node);
                ++modCount;
                count = c;
                oldValue = null;
                break;
            }
        }
    } finally {
        unlock();
    }
    return oldValue;
}
```

- 虽然HashEntry中的value是用volatile关键字修饰的，但是并不能保证并发的原子性，所以put操作仍然需要加锁处理。
- 首先第一步的时候会尝试获取锁，如果获取失败肯定就是其他线程存在竞争，则利用 `scanAndLockForPut()` 自旋获取锁。

![](https://i.loli.net/2019/05/08/5cd1d2cc3c982.jpg)

- 尝试获取自旋锁
- 如果重试的次数达到了`MAX_SCAN_RETRIES` 则改为阻塞锁获取，保证能获取成功。

![](https://i.loli.net/2019/05/08/5cd1d2cd25c37.jpg)

- 将当前的Segment中的table通过key的hashcode定位到HashEntry
- 遍历该HashEntry，如果不为空则判断传入的key和当前遍历的key是否相等，相等则覆盖旧的value
- 不为空则需要新建一个HashEntry并加入到Segment中，同时会先判断是否需要扩容
- 最后会解除在1中所获取当前Segment的所。

#### get方法

```java
public V get(Object key) {
    Segment<K,V> s; // manually integrate access methods to reduce overhead
    HashEntry<K,V>[] tab;
    int h = hash(key);
    long u = (((h >>> segmentShift) & segmentMask) << SSHIFT) + SBASE;
    if ((s = (Segment<K,V>)UNSAFE.getObjectVolatile(segments, u)) != null &&
        (tab = s.table) != null) {
        for (HashEntry<K,V> e = (HashEntry<K,V>) UNSAFE.getObjectVolatile
                 (tab, ((long)(((tab.length - 1) & h)) << TSHIFT) + TBASE);
             e != null; e = e.next) {
            K k;
            if ((k = e.key) == key || (e.hash == h && key.equals(k)))
                return e.value;
        }
    }
    return null;
}
```

- 只需要将 Key 通过 Hash 之后定位到具体的 Segment ，再通过一次 Hash 定位到具体的元素上。
- 由于 HashEntry 中的 value 属性是用 volatile 关键词修饰的，保证了内存可见性，所以每次获取时都是最新值。
- ConcurrentHashMap 的 get 方法是非常高效的，**因为整个过程都不需要加锁**。

### jdk1.8

**那就是查询遍历链表效率太低。**

![](https://i.loli.net/2019/05/08/5cd1d2ce33795.jpg)

**其中抛弃了原有的 Segment 分段锁，而采用了 `CAS + synchronized` 来保证并发安全性**

![](https://i.loli.net/2019/05/08/5cd1d2ceebe02.jpg)

- 也将 1.7 中存放数据的 HashEntry 改为 Node，但作用都是相同的。
- 其中的 `val next` 都用了 volatile 修饰，保证了可见性。

#### put

![](https://i.loli.net/2019/05/08/5cd1d2cfc3293.jpg)

- 根据key计算出hashcode
- 判断是否需要进行初始化
- f即为当前key定位出的Node，如果为空表示当前位置可以写入数据，利用CAS尝试写入，失败则自旋保证成功。
- 如果当前位置的`hashcode == MOVED == -1`，则需要进行扩容
- 如果都不满足，则利用synchronized锁写入数据
- 如果数量大于`TREEIFY_THRESHOLD` 则要转换为红黑树。

#### get

![](https://i.loli.net/2019/05/08/5cd1d2d22c6cb.jpg)

- 根据计算出来的 hashcode 寻址，如果就在桶上那么直接返回值。
- 如果是红黑树那就按照树的方式获取值。
- 就不满足那就按照链表的方式遍历获取值。

1.8 在 1.7 的数据结构上做了大的改动，采用红黑树之后可以保证查询效率（`O(logn)`），甚至取消了 ReentrantLock 改为了 synchronized，这样可以看出在新版的 JDK 中对 synchronized 优化是很到位的。

## 总结

套路：

- 谈谈你理解的 HashMap，讲讲其中的 get put 过程。
- 1.8 做了什么优化？
- 是线程安全的嘛？
- 不安全会导致哪些问题？
- 如何解决？有没有线程安全的并发容器？
- ConcurrentHashMap 是如何实现的？ 1.7、1.8 实现有何不同？为什么这么做？