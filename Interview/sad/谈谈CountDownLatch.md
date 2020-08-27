> 我们经常在主线程中开启多个线程去并行任务，并且主线程需要等待所有子线程执行完毕后再进行汇总的场景。知道你们要说join，但是join不够灵活。

面试官：讲讲CountDownLatch原理

我：我试试

首先状态变量state：**state用来表示计数器当前的值**，当线程调用CountDownLatch对象的await方法后， 当前线程会被阻塞，直到下面的情况之一发生才返回：当所有线程都调用了CountDownLatch对象的countDown方法后，也就是**计数器的值为0**时：其他线程调用了**当前线程的interrupt()方法中断了当前线程**，当前线程就会抛出InterruptedException异常。

所以，我们看一下await方法：

```java
public final void acquireSharedInterruptibly(int arg)
    throws InterruptedException {
    if (Thread.interrupted()) // 线程可中断
        throw new InterruptedException();
    if (tryAcquireShared(arg) < 0) // 如果等于-1， 说明还在挂起
        doAcquireSharedInterruptibly(arg);
}
protected int tryAcquireShared(int acquires) { 
    return (getState() == 0) ? 1 : -1; // 如果为0，则返回1，不为0，则返回-1
}
```

看一下countDown方法：

```java
public final boolean releaseShared(int arg) {
    if (tryReleaseShared(arg)) {
        doReleaseShared(); // 激活被阻塞的线程
        return true;
    }
    return false;
}
protected boolean tryReleaseShared(int releases) { // 尝试释放锁
    // Decrement count; signal when transition to zero
    for (;;) {
        int c = getState();
        if (c == 0)
            return false; // 如果等于0， 返回false，不用释放
        int nextc = c-1;
        if (compareAndSetState(c, nextc)) // 更新state
            return nextc == 0; // nextc如果等于0了，说明资源释放成功，但是不管成功与否，都会退出循环
        // 并且去激活被await阻塞的线程
    }
}
```

