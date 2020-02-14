---
title: 为什么说ArrayList是线程不安全的?
author: DreamCat
id: 1
date: 2020-02-14 16:41:53
tags: Java
categories: Java
---
## 引言

> 面试时相信面试官首先就会问到关于它的知识。一个经常被问到的问题就是：ArrayList是否是线程安全的？那么它为什么是线程不安全的呢？它线程不安全的具体体现又是怎样的呢？我们从源码的角度来看下。

<!-- more -->

## 源码分析

**首先看看该类的属性字段**：

```java
 		/**
     * 列表元素集合数组
     * 如果新建ArrayList对象时没有指定大小，那么会将EMPTY_ELEMENTDATA赋值给elementData，
     * 并在第一次添加元素时，将列表容量设置为DEFAULT_CAPACITY 
     */
    transient Object[] elementData; 

    /**
     * 列表大小，elementData中存储的元素个数
     */
    private int size;
```

**ArrayList的实现主要就是用了一个Object的数组，用来保存所有的元素，以及一个size变量用来保存当前数组中已经添加了多少元素。**

**再次看add方法的源码**

```java
public boolean add(E e) {

    /**
     * 添加一个元素时，做了如下两步操作
     * 1.判断列表的capacity容量是否足够，是否需要扩容
     * 2.真正将元素放在列表的元素数组里面
     */
    ensureCapacityInternal(size + 1);  // Increments modCount!!
    elementData[size++] = e;
    return true;
}
```

**由此看到add元素时，实际做了两个大的步骤：**

1. 判断elementData数组容量是否满足需求
2. 在elementData对应位置上设置值

## 数组越界

1. 列表大小为9，即size=9
2. 线程A开始进入add方法，这时它获取到size的值为9，调用ensureCapacityInternal方法进行容量判断。
3. 线程B此时也进入add方法，它获取到size的值也为9，也开始调用ensureCapacityInternal方法。
4. 线程A发现需求大小为10，而elementData的大小就为10，可以容纳。于是它不再扩容，返回。
5. 线程B也发现需求大小为10，也可以容纳，返回。
6. 线程A开始进行设置值操作， elementData[size++] = e 操作。此时size变为10。
7. 线程B也开始进行设置值操作，它尝试设置elementData[10] = e，而elementData没有进行过扩容，它的下标最大为9。于是此时会报出一个数组越界的异常ArrayIndexOutOfBoundsException.

## null值的情况

**elementData[size++] = e不是一个原子操作**：

- elementData[size] = e;
- size = size + 1;

**逻辑：**

1. 列表大小为0，即size=0
2. 线程A开始添加一个元素，值为A。此时它执行第一条操作，将A放在了elementData下标为0的位置上。
3. 接着线程B刚好也要开始添加一个值为B的元素，且走到了第一步操作。此时线程B获取到size的值依然为0，于是它将B也放在了elementData下标为0的位置上。
4. 线程A开始将size的值增加为1
5. 线程B开始将size的值增加为2

**这样线程AB执行完毕后，理想中情况为size为2，elementData下标0的位置为A，下标1的位置为B。而实际情况变成了size为2，elementData下标为0的位置变成了B，下标1的位置上什么都没有。并且后续除非使用set方法修改此位置的值，否则将一直为null，因为size为2，添加元素时会从下标为2的位置上开始。**

## 案例

```java
    /**
     * 故障现象
     * java.util.ConcurrentModificationException
     */
    public static void notSafe() {
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, "Thread " + i).start();
        }
    }
```




