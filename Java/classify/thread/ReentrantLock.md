# ReentrantLock

> 经常拿synchronized和ReentrantLock做比较，那就来看看这个锁的一些重点吧

面试官：了解ReentrantLock嘛？

## 原理

我：当然，它实现了Lock接口，同时调用内部类sync继承的AQS，先说一下**state**：它代表获取该锁的**可重入次数**，在默认下，state的值为0表示**当前锁没有被任何线程持有**。当一个线程第一次获取该锁时会尝试使用CAS设置state的值为1，并且记录该锁的持有者为当前线程。若该线程第二次获取该锁后，状态值被设置2。


## 方法

我们来看一下lock方法：

```java
public void lock() {
    sync.lock(); // 委托给sync了
}
```

而且它具有非公平锁还是公平锁的特性。比如，我们可以看构造方法

```java
// 这不，由fair来决定是公平的还是非公平的
public ReentrantLock(boolean fair) {
    sync = fair ? new FairSync() : new NonfairSync();
}
```

- 非公平锁：

```java
final void lock() {
    if (compareAndSetState(0, 1)) // cas设置state的状态，0->1
        setExclusiveOwnerThread(Thread.currentThread()); // 设置独占
    else
        acquire(1); // 否则尝试，如果还是当前线程，就state累加，若不是，则挂起
}
// 看看 acquire
public final void acquire(int arg) {
    if (!tryAcquire(arg) &&
        acquireQueued(addWaiter(Node.EXCLUSIVE), arg))
        selfInterrupt();
}
// 看看重写的tryAcquire
protected final boolean tryAcquire(int acquires) {
    return nonfairTryAcquire(acquires);
}
// 重点来了
final boolean nonfairTryAcquire(int acquires) {
    final Thread current = Thread.currentThread();
    int c = getState();
    if (c == 0) { // 0->acquires
        if (compareAndSetState(0, acquires)) {
            setExclusiveOwnerThread(current);
            return true;
        }
    }
    else if (current == getExclusiveOwnerThread()) { // 否则判断该资源是否被该线程持有
        int nextc = c + acquires; // 持有，则+acquires
        if (nextc < 0) // overflow
            throw new Error("Maximum lock count exceeded");
        setState(nextc);
        return true;
    }
    return false;
}
```

- 公平锁和上面的差不多，就多了一个这样的判断：

```java
if (c == 0) {
    if (!hasQueuedPredecessors() && // 队列中是否轮到该线程了
        compareAndSetState(0, acquires)) {
        setExclusiveOwnerThread(current);
        return true;
    }
}
```

我们来看一下unlock()方法：

```java
public void unlock() {
    sync.release(1);
}
// release看看
public final boolean release(int arg) {
    if (tryRelease(arg)) { // 依然调
        Node h = head;
        if (h != null && h.waitStatus != 0)
            unparkSuccessor(h);
        return true;
    }
    return false;
}
// 看看sync重写的
protected final boolean tryRelease(int releases) {
    int c = getState() - releases;// 并不会设置为0，而是减releases
    if (Thread.currentThread() != getExclusiveOwnerThread())
        throw new IllegalMonitorStateException();
    boolean free = false;
    if (c == 0) { // 如果状态为0了，则free为true
        free = true;
        setExclusiveOwnerThread(null); // 并且将持有该资源的线程设置为null
    }
    setState(c); // cas操作
    return free; 
}
```

