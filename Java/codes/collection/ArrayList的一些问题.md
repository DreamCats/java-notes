# ArrayList
## 一些特点

- ArrayList实现了List接口，是顺序容器，即元素存放的数据与放进去的顺序相同，允许放入`null`元素，底层通过**数组**实现。
- 每个ArrayList都有一个容量（capacity），表示**底层数组的实际大小**，容器内存储元素的个数不能多于当前容量。
- 当向容器中添加元素时，如果容量不足，**容器会自动增大底层数组的大小**。
- Java泛型只是编译器提供的语法糖，所以这里的数组是一个Object数组，以便能够容纳任何类型的对象。

## 底层数据结构
```java
private static final int DEFAULT_CAPACITY = 10; // 默认容量
transient Object[] elementData; // Object 数组
private int size; // 大小
```

## 构造函数

### 有参
```java
public ArrayList(int initialCapacity) {
    if (initialCapacity > 0) {
        this.elementData = new Object[initialCapacity];
    } else if (initialCapacity == 0) {
        this.elementData = EMPTY_ELEMENTDATA; // 默认10
    } else {
        throw new IllegalArgumentException("Illegal Capacity: "+
                                           initialCapacity);
    }
}
```

### 无参
```java
public ArrayList() {
    this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA; // 默认10
}
```

## 扩容
常问，看一下源码吧

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
```
继续看ensureExplicitCapacity
```java
private void ensureExplicitCapacity(int minCapacity) {
    modCount++;

    // overflow-conscious code
    if (minCapacity - elementData.length > 0)
        grow(minCapacity); // 扩容最关键的方法
}
```
其实可以之和看grow(jdk1.8)

```java
    private void grow(int minCapacity) {
    // overflow-conscious code
    int oldCapacity = elementData.length;
    int newCapacity = oldCapacity + (oldCapacity >> 1); // old + old * 2 就在这...
    if (newCapacity - minCapacity < 0)
        newCapacity = minCapacity;
    if (newCapacity - MAX_ARRAY_SIZE > 0)
        newCapacity = hugeCapacity(minCapacity);
    // minCapacity is usually close to size, so this is a win:
    elementData = Arrays.copyOf(elementData, newCapacity);
}
```

## add
还是看直接看源码方便一些
```java
public boolean add(E e) {
    // 扩容判断
    ensureCapacityInternal(size + 1);  // Increments modCount!! 这个参数，起到并发异常作用。 
    elementData[size++] = e; // 这一步非原子性，并发容易出错，好几种情况。 下次分析
    return true;
}
```

## get
```java
一般获取元素，第一步都要判断索引是否越界
public E get(int index) {
    rangeCheck(index); // 判断给定索引是否越界
    return elementData(index);
}
```

## 聊一下并发容易出现的问题
### 首先是数组越界，举个例子
1. 列表大小为9，即size=9
2. 线程A开始进入add方法，这时它获取到size的值为9，调用ensureCapacityInternal方法进行容量判断。
3. 线程B此时也进入add方法，它和获取的size的值也为9，也开始调用ensureCapacityInternal方法。
4. 线程A发现需求大小为10，而elementData的大小就为10，可以容纳。于是它不再扩容，返回。
5. 线程B也发现需要大小为10，也可以容纳，返回。
6. 好了，**问题来了哈**
7. 线程A开始进行设置值操作，elementData[size++] = e操作。此时size变为10。
8. 线程B也开始进行设置值操作，它尝试设置elementData[10] = e, 而elementData没有进行过扩容，它的下标最大为9，于是此时会报出一个数组越界的异常`ArrayIndexOutOfBoundsException`。

### null值，举个例子
1. 列表大小为10，即size=0
2. 线程A开始添加元素，值为A。此时它执行第一条操作，将A放在了elementData下标为0的位置上。也就是说，线程挂在了`element[0] = e`上。
3. 接着线程B刚好也要开始天极爱一个值为B的元素，且走到了第一条的操作。此时线程B获取的size的值依然为0，于是它将B也放在了elementData下标为0的位置上。
4. **问题来了**，其实上面也是问题，覆盖了。。。
5. 线程A将size的值增加为1
6. 线程B开始将size的值增加为2
7. 当你获取1索引的时候，那不就是null了？

## Fail-Fast机制
ArrayList也采用了快速失败的机制，**通过记录modCount参数来实现**。在面对并发的修改时，迭代器很快就会完全失败，而不是冒着在将来某个不确定时间发生任意不确定行为的风险。