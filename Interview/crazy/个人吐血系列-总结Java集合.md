> 个人感觉掌握常用的集合类，看其中的源码即可，有很多其实都差不多的，把个别不同的源码多看看，其实就是增删查
>
> 比如，常见的ArrayList、LinkedList、HashMap和ConcurrentHashMap经常被问到的多准备准备。
>
> 这一块就是看源码分析，没别的

### ArrayList

#### 概述

- *ArrayList*实现了*List*接口，是顺序容器，即元素存放的数据与放进去的顺序相同，允许放入`null`元素，底层通过**数组实现**。
- 除该类未实现同步外，其余跟*Vector*大致相同。
- 每个*ArrayList*都有一个容量（capacity），表示底层数组的实际大小，容器内存储元素的个数不能多于当前容量。
- 当向容器中添加元素时，如果容量不足，**容器会自动增大底层数组的大小**。
- 前面已经提过，Java泛型只是编译器提供的语法糖，所以这里的数组是一个Object数组，以便能够容纳任何类型的对象。

![](https://www.pdai.tech/_images/collection/ArrayList_base.png)

- size(), isEmpty(), get(), set()方法均能在**常数时间**内完成，add()方法的时间开销跟插入位置有关，addAll()方法的时间开销跟添加元素的个数成正比。其余方法大都是线性时间。
- 为追求效率，ArrayList没有实现同步（**synchronized**），如果需要多个线程并发访问，用户可以手动同步，也可使用Vector替代。

#### 实现

##### 底层数据结构

```java
transient Object[] elementData; // Object 数组
private int size; // 大小
```

##### 构造函数

```java
		// 参数为容量的构造参数
		public ArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENTDATA; // 默认
        } else {
            throw new IllegalArgumentException("Illegal Capacity: "+
                                               initialCapacity);
        }
    }
		// 无参的构造参数
    public ArrayList() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA; // 默认容量
    }

    public ArrayList(Collection<? extends E> c) {
        elementData = c.toArray();
        if ((size = elementData.length) != 0) {
            // c.toArray might (incorrectly) not return Object[] (see 6260652)
            if (elementData.getClass() != Object[].class)
                elementData = Arrays.copyOf(elementData, size, Object[].class);
        } else {
            // replace with empty array.
            this.elementData = EMPTY_ELEMENTDATA;
        }
    }
```

##### 自动扩容

```java
    public void ensureCapacity(int minCapacity) {
        int minExpand = (elementData != DEFAULTCAPACITY_EMPTY_ELEMENTDATA)
            // any size if not default element table
            ? 0
            // larger than default for default empty table. It's already
            // supposed to be at default size.
            : DEFAULT_CAPACITY;

        if (minCapacity > minExpand) {
            ensureExplicitCapacity(minCapacity);
        }
    }

    private void ensureCapacityInternal(int minCapacity) {
        if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
        }

        ensureExplicitCapacity(minCapacity);
    }

    private void ensureExplicitCapacity(int minCapacity) {
        modCount++;

        // overflow-conscious code
        if (minCapacity - elementData.length > 0)
            grow(minCapacity);
    }

		private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    private void grow(int minCapacity) {
        // overflow-conscious code
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;
        if (newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = hugeCapacity(minCapacity);
        // minCapacity is usually close to size, so this is a win:
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) // overflow
            throw new OutOfMemoryError();
        return (minCapacity > MAX_ARRAY_SIZE) ?
            Integer.MAX_VALUE :
            MAX_ARRAY_SIZE;
    }
```



- 每当向数组中添加元素时，都要去检查添加后**元素的个数是否会超出当前数组的长度**，如果超出，**数组将会进行扩容**，以满足添加数据的需求。
- 数组扩容通过一个公开的方法`ensureCapacity(int minCapacity)`来实现。在实际添加大量元素前，我也可以使用ensureCapacity来手动增加ArrayList实例的容量，以减少递增式再分配的数量。
- 数组进行扩容时，会将老数组中的元素重新**拷贝**一份到新的数组中，每次数组容量的增长大约是其原容量的**1.5倍**。
- 这种操作的代价是很高的，因此在实际使用时，我们应该**尽量避免数组容量的扩张**。
- 当我们可预知要保存的元素的多少时，要在构造ArrayList实例时，就**指定其容量**，以避免数组扩容的发生。
- 或者根据实际需求，通过调用ensureCapacity方法来手动增加ArrayList实例的容量。

![扩容](https://www.pdai.tech/_images/collection/ArrayList_grow.png)

##### add()

是向容器中添加新元素，这可能会导致*capacity*不足，因此在添加元素之前，都需要进行剩余空间检查，如果需要则自动扩容。扩容操作最终是通过`grow()`方法完成的。

```java
    public boolean add(E e) {
        ensureCapacityInternal(size + 1);  // Increments modCount!! // 多线程容易出问题
        elementData[size++] = e; // 这里也是
        return true;
    }

    public void add(int index, E element) {
        rangeCheckForAdd(index);

        ensureCapacityInternal(size + 1);  // Increments modCount!!
        System.arraycopy(elementData, index, elementData, index + 1,
                         size - index);
        elementData[index] = element;
        size++;
    }
```

##### get()

`get()`方法同样很简单，唯一要注意的是由于底层数组是Object[]，得到元素后需要进行类型转换。

```java
public E get(int index) {
    rangeCheck(index);
    return (E) elementData[index];//注意类型转换
}
```

##### remove()

`remove()`方法也有两个版本，一个是`remove(int index)`删除指定位置的元素，另一个是`remove(Object o)`删除第一个满足`o.equals(elementData[index])`的元素。删除操作是`add()`操作的逆过程，需要将删除点之后的元素向前移动一个位置。需要注意的是为了让**GC**起作用，必须显式的为最后一个位置赋`null`值。

```java
public E remove(int index) {
    rangeCheck(index);
    modCount++;
    E oldValue = elementData(index);
    int numMoved = size - index - 1;
    if (numMoved > 0)
        System.arraycopy(elementData, index+1, elementData, index, numMoved);
    elementData[--size] = null; //清除该位置的引用，让GC起作用
    return oldValue;
}
```

##### indexOf()

**循环遍历用equals**

```java
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++)
                if (elementData[i]==null)
                    return i;
        } else {
            for (int i = 0; i < size; i++)
                if (o.equals(elementData[i]))
                    return i;
        }
        return -1;
    }
```



##### Fail-Fast机制

ArrayList也采用了**快速失败的机制**，**通过记录modCount参数来实现**。在面对并发的修改时，迭代器很快就会完全失败，而不是冒着在将来某个不确定时间发生任意不确定行为的风险。

##### 多线程问题

[请参考->个人吐血系列-总结java多线程](http://dreamcat.ink/2020/03/25/ge-ren-tu-xie-xi-lie-zong-jie-java-duo-xian-cheng/#toc-heading-14)

### LinkedList

#### 概述

**LinkedList**同时实现了**list**接口和**Deque**接口，也就是说它既可以看作一个**顺序容器**，又可以看作**一个队列（Queue）**，同时又可以看作**一个栈（Stack）**。这样看来，LinkedList简直就是个全能冠军。当你需要使用栈或者队列时，可以考虑使用LinkedList，一方面是因为Java官方已经声明不建议使用Stack类，更遗憾的是，Java里根本没有一个叫做Queue的类（它是个接口名字）。**关于栈或队列，现在的首选是ArrayDeque，它有着比LinkedList（当作栈或队列使用时）有着更好的性能**。

![LinkedList](https://www.pdai.tech/_images/collection/LinkedList_base.png)

LinkedList的实现方式决定了所有跟**下标相关的操作都是线性时间**，而在**首段或者末尾删除元素只需要常数时间**。为追求效率LinkedList没有实现同步（synchronized），如果需要多个线程并发访问，可以先采用`Collections.synchronizedList()`方法对其进行包装。

#### 实现

##### 底层数据接口

```java
    transient int size = 0;
    transient Node<E> first; // 经常用到
    transient Node<E> last; // 也经常用到
		// Node是私有的内部类
    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
```

LinkedList底层**通过双向链表实现**，本节将着重讲解插入和删除元素时双向链表的维护过程，也即是之间解跟List接口相关的函数。双向链表的每个节点用内部类Node表示。LinkedList通过`first`和`last`引用分别指向链表的第一个和最后一个元素。注意这里没有所谓的哑元，当链表为空的时候`first`和`last`都指向`null`。

##### 构造函数

```java
    public LinkedList() {
    }

    public LinkedList(Collection<? extends E> c) {
        this();
        addAll(c);
    }
```

##### getFirst()，getLast()

**本身在数据结构中，维护了first和last的变量，因此其实挺简单的**。

```java
    public E getFirst() {
        final Node<E> f = first; // 获取第一个元素
        if (f == null)
            throw new NoSuchElementException();
        return f.item;
    }

    public E getLast() {
        final Node<E> l = last; // 获取最后一个元素
        if (l == null)
            throw new NoSuchElementException();
        return l.item;
    }
```

##### removeFirst()，removeLast()，remove(e)，remove(index)

![remove](https://www.pdai.tech/_images/collection/LinkedList_remove.png)

**删除元素** - 指的是删除第一次出现的这个元素, 如果没有这个元素，则返回false；判读的依据是`equals`方法， 如果equals，则直接unlink这个node；由于LinkedList可存放null元素，故也可以删除第一次出现null的元素；

```java
    public boolean remove(Object o) {
        if (o == null) {
            for (Node<E> x = first; x != null; x = x.next) {
                if (x.item == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Node<E> x = first; x != null; x = x.next) {
                if (o.equals(x.item)) { // 循环遍历 用equals判断
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }
        E unlink(Node<E> x) {
        // assert x != null;
        final E element = x.item; // 当前元素
        final Node<E> next = x.next; // 指向下一个节点
        final Node<E> prev = x.prev; // 上一个节点

        if (prev == null) {// 第一个元素，如果该节点的上节点为空，那么就把该节点的下个节点放在第一个位置
            first = next;
        } else {
            prev.next = next; // 不为空，则把上个节点指向该节点的下个节点
            x.prev = null;
        }

        if (next == null) {// 最后一个元素
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        x.item = null; // GC
        size--;
        modCount++;
        return element;
    }
```

`remove(int index)`使用的是下标计数， 只需要判断该index是否有元素即可，如果有则直接unlink这个node。

```java
    public E remove(int index) {
        checkElementIndex(index);
        return unlink(node(index));
    }
```

`removeFirst()`其实挺简单的

```java
    public E removeFirst() {
        final Node<E> f = first; // 拿到firs直接unlink
        if (f == null)
            throw new NoSuchElementException();
        return unlinkFirst(f);
    }

    private E unlinkFirst(Node<E> f) {
        // assert f == first && f != null;
        final E element = f.item; // first e
        final Node<E> next = f.next; // first 没有 pre ， 只有next
        f.item = null;
        f.next = null; // help GC
        first = next; // 让first指向next
        if (next == null) // 如果next为空，则当前元素已经是最后一个元素了，那么last自然为空
            last = null;
        else
            next.prev = null; // 如果不为空，next的上个节点指向为空
        size--;
        modCount++;
        return element;
    }
```

`removLast()`其实挺简单的，和上面差不多

```java
    public E removeLast() {
        final Node<E> l = last;
        if (l == null)
            throw new NoSuchElementException();
        return unlinkLast(l);
    }

    private E unlinkLast(Node<E> l) {
        // assert l == last && l != null;
        final E element = l.item;
        final Node<E> prev = l.prev;
        l.item = null;
        l.prev = null; // help GC
        last = prev;
        if (prev == null)
            first = null;
        else
            prev.next = null;
        size--;
        modCount++;
        return element;
    }
```

##### add()

```java
    public boolean add(E e) {
        linkLast(e); // 在链表末尾插入元素，所以常数时间
        return true;
    }

    void linkLast(E e) { // 其实就是最后面修改引用
        final Node<E> l = last; 
        final Node<E> newNode = new Node<>(l, e, null);
        last = newNode;
        if (l == null)
            first = newNode;
        else
            l.next = newNode;
        size++;
        modCount++;
    }
```

`add(int index, E element)`, 当index==size时，等同于add(E e); 如果不是，则分两步：1.先根据index找到要插入的位置,即node(index)方法；2.修改引用，完成插入操作，其实想就是遍历插入。

##### indexOf()

循环遍历equals，找到对应的下标

```java
    public int indexOf(Object o) {
        int index = 0; // 维护index
        if (o == null) {
            for (Node<E> x = first; x != null; x = x.next) {
                if (x.item == null)
                    return index;
                index++;
            }
        } else {
            for (Node<E> x = first; x != null; x = x.next) {
                if (o.equals(x.item)) // 用equals
                    return index;
                index++;
            }
        }
        return -1;
    }
```

### HashMap(面试常问)

众所周知，HashMap的底层结构是**数组和链表**组成的，不过在jdk1.7和jdk1.8中具体实现略有不同。

![底层结构](https://i.loli.net/2019/05/08/5cd1d2be77958.jpg)

#### 1.7的实现

##### 成员变量

这里，就不贴1.7版本的源码了，因此贴图。

![](https://i.loli.net/2019/05/08/5cd1d2bfd6aba.jpg)

介绍成员变量：

1. **初始化桶大小**，因为底层是数组，所以这是数组默认的大小。
2. **桶最大值**。
3. 默认的**负载因子**（0.75）
4. table真正存放数据的数组。
5. map存放数量的大小
6. 桶大小，可在构造函数时显式指定。
7. 负载因子，可在构造函数时显式指定。

##### 负载因子

```java
public HashMap() {
    this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR); // 桶和负载因子
}
public HashMap(int initialCapacity, float loadFactor) {
    if (initialCapacity < 0)
        throw new IllegalArgumentException("Illegal initial capacity: " +
                                           initialCapacity);
    if (initialCapacity > MAXIMUM_CAPACITY)
        initialCapacity = MAXIMUM_CAPACITY; // 只能获取默认的最大值
    if (loadFactor <= 0 || Float.isNaN(loadFactor))
        throw new IllegalArgumentException("Illegal load factor: " +
                                           loadFactor);
    this.loadFactor = loadFactor;
    threshold = initialCapacity;
    in
```

- **给定的默认容量为16，负载因子为0.75**.
- Map在使用过程中不断的往里面存放数据，当数量达到了`16 * 0.75 = 12`就需要将当前16的容量进行扩容，而扩容这个过程涉及到`rehash`（重新哈希）、复制数据等操作，所有非常消耗性能。
- 因此通常建议能提前预估HashMap的大小最好，尽量的减少扩容带来的额外性能损耗。
- 关于这部分后期专门出一篇文章进行讲解。

##### Entry

![Entry](https://i.loli.net/2019/05/08/5cd1d2c08e693.jpg)

Entry是**Hashmap中的一个内部类**，从他的成员变量很容易看出：

- key就是写入时的键
- value自然就是值
- 开始的时候就提到HashMap是由数组和链表组成，所以这个next就是用于实现链表结构
- hash存放的是当前key的hashcode

##### put(重点来了)

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
        resize(2 * table.length); // 两倍扩容 重新哈希
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
- 如果需要就进行**两倍扩充**，并将当前的key重新hash并定位。
- 而在createEntry中会将当前位置的桶传入到新建的桶中，如果当前桶有值就会在位置形成链表。

##### get

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
    for (Entry<K,V> e = table[indexFor(hash, table.length)]; //循环遍历equals key拿值
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

#### 1.8的实现

不知道 1.7 的实现大家看出需要优化的点没有？

其实一个很明显的地方就是链表

**当 Hash 冲突严重时，在桶上形成的链表会变的越来越长，这样在查询时的效率就会越来越低；时间复杂度为 `O(N)`。**

![](https://i.loli.net/2019/05/08/5cd1d2c1c1cd7.jpg)

##### 成员变量

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

##### put

```java
    /**
     * Implements Map.put and related methods
     *
     * @param hash hash for key
     * @param key the key
     * @param value the value to put
     * @param onlyIfAbsent if true, don't change existing value
     * @param evict if false, the table is in creation mode.
     * @return previous value, or null if none
     */
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

- 判断当前桶是否为空，空的就需要初始化（resize中会判断是否进行初始化）
- 根据当前key的hashcode定位到具体的桶中并判断是否为空，为空则表明没有Hash冲突，就直接在当前位置创建一个新桶
- 如果当前桶有值（Hash冲突），那么就要比较当前桶中的key、key的hashcode与写入的key是否相等，相等就赋值给e，在第8步的时候会统一进行赋值及返回
- 如果当前桶为**红黑树**，那就要按照红黑树的方式写入数据
- 如果是个链表，就需要将当前的key、value封装称一个新节点写入到当前桶的后面形成链表。
- 接着判断当前链表的大小是否**大于预设的阈值**，大于就要转换成为**红黑树**
- 如果在遍历过程中找到key相同时直接退出遍历。
- 如果`e != null`就相当于存在相同的key，那就需要将值覆盖。
- 最后判断是否需要进行扩容。

##### get

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

#### 问题

但是 HashMap 原有的问题也都存在，比如在并发场景下使用时容易出现**死循环**。

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
- **但是1.7的头插法造成的问题，1.8改变了插入顺序，就解决了这个问题，但是为了内存可见性等安全性，还是需要ConCurrentHashMap**

> 参考：hashMap死循环分析
>
> [hashMap死循环分析](https://zhuanlan.zhihu.com/p/67915754)

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

- **建议使用第一种，同时可以把key value取出**。
- 第二种还需要通过key取一次key，效率较低。

### ConcurrentHashMap

#### 1.7

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

- 唯一的区别就是其中的核心数据如 value ，以及链表都是 `volatile` 修饰的，保证了获取时的可见性。
- ConcurrentHashMap 采用了**分段锁**技术，其中 Segment 继承于 `ReentrantLock`。
- 不会像HashTable那样不管是put还是get操作都需要做同步处理，理论上 ConcurrentHashMap 支持 CurrencyLevel (Segment 数组数量)的线程并发。
- **每当一个线程占用锁访问一个 Segment 时，不会影响到其他的 Segment**。

##### put

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

- **虽然HashEntry中的value是用volatile关键字修饰的，但是并不能保证并发的原子性，所以put操作仍然需要加锁处理**。

- **首先第一步的时候会尝试获取锁，如果获取失败肯定就是其他线程存在竞争，则利用 `scanAndLockForPut()` 自旋获取锁**。

  1. 尝试获取自旋锁

  2. 如果重试的次数达到了`MAX_SCAN_RETRIES` 则改为**阻塞锁获取**，保证能获取成功。

总的来说：

- 将当前的Segment中的table通过key的hashcode定位到HashEntry
- 遍历该HashEntry，如果不为空则判断传入的key和当前遍历的key是否相等，相等则覆盖旧的value
- 不为空则需要新建一个HashEntry并加入到Segment中，同时会先判断是否需要扩容
- 最后会解除在1中所获取当前Segment的锁。

##### get

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

#### 1.8

**那就是查询遍历链表效率太低。**

![](https://i.loli.net/2019/05/08/5cd1d2ce33795.jpg)

**其中抛弃了原有的 Segment 分段锁，而采用了 `CAS + synchronized` 来保证并发安全性**

##### put

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
- f即为当前key定位出的Node，如果为空表示当前位置可以写入数据，**利用CAS尝试写入，失败则自旋保证成功**。
- 如果当前位置的`hashcode == MOVED == -1`，则需要进行扩容
- **如果都不满足，则利用synchronized锁写入数据**
- 如果数量大于`TREEIFY_THRESHOLD` 则要转换为**红黑树**。

##### get

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

1.8 在 1.7 的数据结构上做了大的改动，采用红黑树之后可以保证查询效率（`O(logn)`），甚至取消了 ReentrantLock 改为了 synchronized，这样可以看出在新版的 JDK 中对 synchronized 优化是很到位的。

### 总结

套路：

- 谈谈你理解的 HashMap，讲讲其中的 get put 过程。
- 1.8 做了什么优化？
- 是线程安全的嘛？
- 不安全会导致哪些问题？
- 如何解决？有没有线程安全的并发容器？
- ConcurrentHashMap 是如何实现的？ 1.7、1.8 实现有何不同？为什么这么做？

> 创作不易哇，觉得有帮助的话，给个小小的star呗。github地址😁😁😁