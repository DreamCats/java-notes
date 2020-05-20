# HashMap
> 谈到这个，真的是面试高频呀，处处问，关键HashMap在jdk1.7和1.8的源码是我不一样的我这边就只放1.8的源码。

## 底层结构

> HashMap的底层结构是是**数组+链表**。关于为什么是链表，那是因为哈希冲突，采取链表一种方式。

## 常见参数
```java
static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16 初始容量
static final float DEFAULT_LOAD_FACTOR = 0.75f; // 默认负载因子0.75
// 给定的默认容量为16，负载因子为0.75.
// Map在使用过程中不断的往里面存放数据，当数量达到了16 * 0.75 = 12就需要将当前16的容量进行扩容，
// 而扩容这个过程涉及到rehash（重新哈希）、复制数据等操作，所有非常消耗性能。
static final int TREEIFY_THRESHOLD = 8;// 成为红黑树的阈值，为什么是8？
static final int UNTREEIFY_THRESHOLD = 6; // 红黑树转链表阈值6
static final int MIN_TREEIFY_CAPACITY = 64;
// 个人感觉，8是经验值，或者链表为8的时候，性能开始下降，大家知道链表查找慢，当元素过多的时候，非常影响性能的。
// 因此采取为8的时候转成红黑树。元素过少，其实没必要直接红黑树，元素过少，链表查找的时间也不是很能影响性能。
// 在 hash 函数设计合理的情况下，发生 hash 碰撞 8 次的几率为百万分之 6，概率说话。
// 6是因为如果 hash 碰撞次数在 8 附近徘徊，会一直发生链表和红黑树的转化，为了预防这种情况的发生。
```

## hash
```java
static final int hash(Object key) {
    int h;
    return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
}
```
hash 函数是先拿到通过 key 的 hashcode，是 32 位的 int 值，然后让 hashcode 的高 16 位和低 16 位进行异或操作。这个也叫扰动函数，这么设计有二点原因：
- 一定要尽可能降低 hash 碰撞，越分散越好；
- 算法一定要尽可能高效，因为这是高频操作, 因此采用位运算；

## put
> 这里介绍1.8的，1.7的其实很简单，被1.8包括了
```java
final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
               boolean evict) {
    Node<K,V>[] tab; Node<K,V> p; int n, i;
    if ((tab = table) == null || (n = tab.length) == 0) // 1. 判断当前桶是否为空，空的就需要初始化（resize中会判断是否进行初始化）
        n = (tab = resize()).length;
    if ((p = tab[i = (n - 1) & hash]) == null) // 2. 根据当前key的hashcode定位到具体的桶中并判断是否为空，为空则表明没有Hash冲突，就直接在当前位置创建一个新桶
        tab[i] = newNode(hash, key, value, null);
    else {
        Node<K,V> e; K k;
        if (p.hash == hash && // 3. 如果当前桶有值（Hash冲突），那么就要比较当前桶中的key、key的hashcode与写入的key是否相等，相等就赋值给e，在第8步的时候会统一进行赋值及返回
            ((k = p.key) == key || (key != null && key.equals(k))))
            e = p;
        else if (p instanceof TreeNode) // 4. 如果当前桶为红黑树，那就要按照红黑树的方式写入数据
            e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
        else { // 5. 如果是个链表，就需要将当前的key、value封装称一个新节点写入到当前桶的后面形成链表。
            for (int binCount = 0; ; ++binCount) {
                if ((e = p.next) == null) {
                    p.next = newNode(hash, key, value, null);
                    if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st // 6. 接着判断当前链表的大小是否大于预设的阈值，大于就要转换成为红黑树
- 
                        treeifyBin(tab, hash);
                    break;
                }
                if (e.hash == hash && // 7. 如果在遍历过程中找到key相同时直接退出遍历。
                    ((k = e.key) == key || (key != null && key.equals(k))))
                    break;
                p = e;
            }
        }
        if (e != null) { // existing mapping for key 8. 如果`e != null`就相当于存在相同的key，那就需要将值覆盖。
            V oldValue = e.value;
            if (!onlyIfAbsent || oldValue == null)
                e.value = value;
            afterNodeAccess(e);
            return oldValue;
        }
    }
    ++modCount;
    if (++size > threshold) // 9. 最后判断是否需要进行扩容。
        resize();
    afterNodeInsertion(evict);
    return null;
}
```
看代码有点晕乎！

![put过程](https://imgkr.cn-bj.ufileos.com/b2206341-08ce-4e67-87bd-3a467ccac31e.png)

1. 判断数组是否为空，为空进行初始化;
2. 不为空，计算 k 的 hash 值，通过(n - 1) & hash计算应当存放在数组中的下标 index;
3. 查看 table[index] 是否存在数据，没有数据就构造一个 Node 节点存放在 table[index] 中；

4. 存在数据，说明发生了 hash 冲突(存在二个节点 key 的 hash 值一样), 继续判断 key 是否相等，相等，用新的 value 替换原数据(onlyIfAbsent 为 false)；

5. 如果不相等，判断当前节点类型是不是树型节点，如果是树型节点，创造树型节点插入红黑树中；
6. 如果不是树型节点，创建普通 Node 加入链表中；判断链表长度是否大于 8， 大于的话链表转换为红黑树；

7. 插入完成之后判断当前节点数是否大于阈值，如果大于开始扩容为原数组的二倍。

## get
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

## 1.8的优化
1. 数组+链表改成了数组+链表或红黑树；
2. 链表的插入方式从头插法改成了尾插法，简单说就是插入时，如果数组位置上已经有元素，1.7 将新元素放到数组中，原始节点作为新节点的后继节点，1.8 遍历链表，将元素放置到链表的最后；
3. 扩容的时候 1.7 需要对原数组中的元素进行重新 hash 定位在新数组的位置，1.8 采用更简单的判断逻辑，位置不变或索引+旧容量大小；
4. 在插入时，1.7 先判断是否需要扩容，再插入，1.8 先进行插入，插入完成再判断是否需要扩容；

## 并发问题
- HashMap扩容的时候会调用resize()方法，就是这里的并发操作容易在一个桶上形成环形链表
- 这样当获取一个不存在的key时，计算出的index正好是环形链表的下标就会出现死循环。
- **但是1.7的头插法造成的问题，1.8改变了插入顺序，就解决了这个问题，但是为了内存可见性等安全性，还是需要ConCurrentHashMap**

[死循环分析](https://zhuanlan.zhihu.com/p/67915754)

## 哈希遍历

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
