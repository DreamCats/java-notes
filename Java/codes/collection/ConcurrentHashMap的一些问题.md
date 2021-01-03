# ConcurrentHashMap
> 1.7和1.8的源码还不太一样，也就是说加锁的方式不一样。

## 1.7
> 分段锁

![图示意图](https://i.loli.net/2019/05/08/5cd1d2c5ce95c.jpg)

### Segment的数据结构
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
       transient volatile HashEntry<K,V>[] table; // volatile修饰
       transient int count;
       transient int modCount;
       transient int threshold;
       final float loadFactor;

}
```
- 唯一的区别就是其中的核心数据如 value ，以及链表都是 volatile 修饰的，保证了获取时的可见性。
- ConcurrentHashMap 采用了分段锁技术，其中 Segment 继承于 `ReentrantLock`。
- 不会像HashTable那样不管是put还是get操作都需要做同步处理，理论上 ConcurrentHashMap 支持 CurrencyLevel (Segment 数组数量)的线程并发。
- **每当一个线程占用锁访问一个 Segment 时，不会影响到其他的 Segment。**

### put
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
通过key定位到Segment，之后在对应的Segment中进行具体的put
```java
final V put(K key, int hash, V value, boolean onlyIfAbsent) {
    HashEntry<K,V> node = tryLock() ? null :
        scanAndLockForPut(key, hash, value); // 1. 加锁处理
    V oldValue;
    try {
        HashEntry<K,V>[] tab = table;
        int index = (tab.length - 1) & hash;
        HashEntry<K,V> first = entryAt(tab, index);
        for (HashEntry<K,V> e = first;;) {
            if (e != null) {
                K k;
                if ((k = e.key) == key || // 2. 遍历该HashEntry，如果不为空则判断传入的key和当前遍历的key是否相等，相等则覆盖旧的value
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
            else { // 3. 不为空则需要新建一个HashEntry并加入到Segment中，同时会先判断是否需要扩容
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
        unlock(); // 4. 解锁
    }
    return oldValue;
}
```
- 虽然HashEntry中的value是用volatile关键字修饰的，但是并不能保证并发的原子性，所以put操作仍然需要加锁处理。
- 首先第一步的时候会尝试获取锁，如果获取失败肯定就是其他线程存在竞争，则利用 `scanAndLockForPut()` 自旋获取锁。
  - 尝试获取自旋锁
  - 如果重试的次数达到了`MAX_SCAN_RETRIES` 则改为阻塞锁获取，保证能获取成功。
  
总的来说：
- 将当前的Segment中的table通过key的hashcode定位到HashEntry
- 遍历该HashEntry，如果不为空则判断传入的key和当前遍历的key是否相等，相等则覆盖旧的value
- 不为空则需要新建一个HashEntry并加入到Segment中，同时会先判断是否需要扩容
- 最后会解除在1中所获取当前Segment的锁。

### get
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
- ConcurrentHashMap 的 get 方法是非常高效的，**因为整个过程都不需要加锁。**

## 1.8
> 1.7 查询遍历链表效率太低。其中抛弃了原有的 Segment 分段锁，而采用了 `CAS + synchronized` 来保证并发安全性

### put
```java
    final V putVal(K key, V value, boolean onlyIfAbsent) {
        if (key == null || value == null) throw new NullPointerException();
        int hash = spread(key.hashCode());
        int binCount = 0;
        for (Node<K,V>[] tab = table;;) { // 1. 根据key计算出hashcode
            Node<K,V> f; int n, i, fh;
            if (tab == null || (n = tab.length) == 0) // 2. 判断是否需要进行初始化
                tab = initTable();
            else if ((f = tabAt(tab, i = (n - 1) & hash)) == null) { // 3. f即为当前key定位出的Node，如果为空表示当前位置可以写入数据，利用CAS尝试写入，失败则自旋保证成功。
                if (casTabAt(tab, i, null,
                             new Node<K,V>(hash, key, value, null)))
                    break;                   // no lock when adding to empty bin
            }
            else if ((fh = f.hash) == MOVED) // 4. 如果当前位置的`hashcode == MOVED == -1`，则需要进行扩容
                tab = helpTransfer(tab, f);
            else {
                V oldVal = null;
                synchronized (f) { // 5. 如果都不满足，则利用synchronized锁写入数据
                    if (tabAt(tab, i) == f) {
                        if (fh >= 0) {
                            binCount = 1;
                            for (Node<K,V> e = f;; ++binCount) {
                                K ek;
                                if (e.hash == hash &&
                                    ((ek = e.key) == key ||
                                     (ek != null && key.equals(ek)))) {
                                    oldVal = e.val;
                                    if (!onlyIfAbsent)
                                        e.val = value;
                                    break;
                                }
                                Node<K,V> pred = e;
                                if ((e = e.next) == null) {
                                    pred.next = new Node<K,V>(hash, key,
                                                              value, null);
                                    break;
                                }
                            }
                        }
                        else if (f instanceof TreeBin) {
                            Node<K,V> p;
                            binCount = 2;
                            if ((p = ((TreeBin<K,V>)f).putTreeVal(hash, key,
                                                           value)) != null) {
                                oldVal = p.val;
                                if (!onlyIfAbsent)
                                    p.val = value;
                            }
                        }
                    }
                }
                if (binCount != 0) { 
                    if (binCount >= TREEIFY_THRESHOLD) // 6. 如果数量大于`TREEIFY_THRESHOLD` 则要转换为红黑树。
                        treeifyBin(tab, i);
                    if (oldVal != null)
                        return oldVal;
                    break;
                }
            }
        }
        addCount(1L, binCount);
        return null;
    }

```
- 根据key计算出hashcode
- 判断是否需要进行初始化
- f即为当前key定位出的Node，如果为空表示当前位置可以写入数据，**利用CAS尝试写入，失败则自旋保证成功。**
- 如果当前位置的`hashcode == MOVED == -1`，则需要进行扩容
- 如果都不满足，则利用`synchronized`锁写入数据
- 如果数量大于TREEIFY_THRESHOLD 则要转换为红黑树。

### get
```java
    public V get(Object key) {
        Node<K,V>[] tab; Node<K,V> e, p; int n, eh; K ek;
        int h = spread(key.hashCode());
        if ((tab = table) != null && (n = tab.length) > 0 &&
            (e = tabAt(tab, (n - 1) & h)) != null) {
            if ((eh = e.hash) == h) {
                if ((ek = e.key) == key || (ek != null && key.equals(ek)))
                    return e.val;
            }
            else if (eh < 0)
                return (p = e.find(h, key)) != null ? p.val : null;
            while ((e = e.next) != null) {
                if (e.hash == h &&
                    ((ek = e.key) == key || (ek != null && key.equals(ek))))
                    return e.val;
            }
        }
        return null;
    }
```
- 根据计算出来的 hashcode 寻址，如果就在桶上那么直接返回值。
- 如果是红黑树那就按照树的方式获取值。
- 就不满足那就按照链表的方式遍历获取值。

## 总结
1.8 在 1.7 的数据结构上做了大的改动，采用红黑树之后可以保证查询效率（O(logn)），甚至取消了 ReentrantLock 改为了 synchronized，这样可以看出在新版的 JDK 中对 synchronized 优化是很到位的。