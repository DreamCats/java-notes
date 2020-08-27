> 聊一聊读写锁

面试官：了解AQS的读写锁嘛，知道其原理嘛？

我：刚好了解，我们知道，在一些读多写少的场景中，若是用ReentrantLock效率显然不高，于是ReentrantReadWriteLock问世。

老规矩：

```java
// 维护了readlock和writelock
private final ReentrantReadWriteLock.ReadLock readerLock;
/** Inner class providing writelock */
private final ReentrantReadWriteLock.WriteLock writerLock;
/** Performs all synchronization mechanics */
final Sync sync; // 同样的是sync 继承aqs

// 可惜的是state依然是一个， 但是不慌
// 高16位表示读状态，低16位表示获取到写锁的线程的可重入锁
static final int SHARED_SHIFT   = 16;
// 共享锁的读锁的状态单位值65536
static final int SHARED_UNIT    = (1 << SHARED_SHIFT);
// 共享锁线程最大个数65536
static final int MAX_COUNT      = (1 << SHARED_SHIFT) - 1;
// 排它锁写锁掩码，二进制 15个1
static final int EXCLUSIVE_MASK = (1 << SHARED_SHIFT) - 1;
// 返回读锁线程数
static int sharedCount(int c)    { return c >>> SHARED_SHIFT; }
// 返回写锁可重入个数
static int exclusiveCount(int c) { return c & EXCLUSIVE_MASK; }
```

直奔主题：写锁的获取与释放

**lock：**

```java
// lock
public void lock() {
    sync.acquire(1);
}
// acquire
public final void acquire(int arg) {
    if (!tryAcquire(arg) && // 老规矩了
        acquireQueued(addWaiter(Node.EXCLUSIVE), arg))
        selfInterrupt();
}
// tryAcquire
protected final boolean tryAcquire(int acquires) {
    Thread current = Thread.currentThread();
    int c = getState();
    int w = exclusiveCount(c); // 得到低16位的值
    // 如果c！=0， 说明资源已经被其他读锁或者写锁的线程所获取
    if (c != 0) {
        // w==0 代表低16位位0，那么高16位不为0，那么获取了读锁
        if (w == 0 || current != getExclusiveOwnerThread())
            return false;
        // 到这一步，已经是写锁了，那么判断可重入次数
        if (w + exclusiveCount(acquires) > MAX_COUNT)
            throw new Error("Maximum lock count exceeded");
        // Reentrant acquire
        // 更新state
        setState(c + acquires);
        return true;
    }
    // 是否第一个写锁获取线程
    if (writerShouldBlock() ||
        !compareAndSetState(c, c + acquires))
        return false;
    setExclusiveOwnerThread(current);
    return true;
}
```

**unlock：**

```java
// unlock
public void unlock() {
    sync.release(1);
}
// 不提了
public final boolean release(int arg) {
    if (tryRelease(arg)) {
        Node h = head;
        if (h != null && h.waitStatus != 0)
            unparkSuccessor(h);
        return true;
    }
    return false;
}
// 重点
protected final boolean tryRelease(int releases) {
    if (!isHeldExclusively())
        throw new IllegalMonitorStateException();
    int nextc = getState() - releases; // 依然是减 
    boolean free = exclusiveCount(nextc) == 0; // 判断是否为0
    if (free) // 移除该写锁持有的线程
        setExclusiveOwnerThread(null);
    setState(nextc); // 更新
    return free;
}
```

读锁的获取和释放：

**lock：**

```java
protected final int tryAcquireShared(int unused) {
    Thread current = Thread.currentThread();
    int c = getState();
    // 判断是否被写锁占用
    if (exclusiveCount(c) != 0 &&
        getExclusiveOwnerThread() != current)
        return -1;
    // 获取读锁的数量
    int r = sharedCount(c);
    // 尝试获取锁，多个读线程只有一个会成功，不成功的进入fullTryAcqureShared进行重试
    if (!readerShouldBlock() &&
        r < MAX_COUNT &&
        compareAndSetState(c, c + SHARED_UNIT)) {
        // 第一个线程获取读锁
        if (r == 0) {
            firstReader = current;
            firstReaderHoldCount = 1;
        // 如果当前线程是第一个获取读锁的线程
        } else if (firstReader == current) {
            firstReaderHoldCount++;
        } else {
            // 记录最后一个获取读锁的线程的线程或记录其他线程读锁的可重入数
            HoldCounter rh = cachedHoldCounter;
            if (rh == null || rh.tid != getThreadId(current))
                cachedHoldCounter = rh = readHolds.get();
            else if (rh.count == 0)
                readHolds.set(rh);
            rh.count++;
        }
        return 1;
    }
    // 自旋获取
    return fullTryAcquireShared(current);
}
```

**unlock:**

```java
public final boolean releaseShared(int arg) {
    if (tryReleaseShared(arg)) {
        doReleaseShared(); // 释放所有
        return true;
    }
    return false;
}
protected final boolean tryReleaseShared(int unused) {
    Thread current = Thread.currentThread();
    if (firstReader == current) {
        // assert firstReaderHoldCount > 0;
        if (firstReaderHoldCount == 1)
            firstReader = null;
        else
            firstReaderHoldCount--;
    } else {
        HoldCounter rh = cachedHoldCounter;
        if (rh == null || rh.tid != getThreadId(current))
            rh = readHolds.get();
        int count = rh.count;
        if (count <= 1) {
            readHolds.remove();
            if (count <= 0)
                throw unmatchedUnlockException();
        }
        --rh.count;
    }
    // 循环直到自己的读计数-1.CAS更新成功
    for (;;) {
        int c = getState();
        int nextc = c - SHARED_UNIT;
        if (compareAndSetState(c, nextc))
            return nextc == 0;
    }
}
```

