> 这个先留着，这个内容有点多，待我这周结束之后来写。

面试官：AQS是什么？

我：AbstractQueuedSynchronizer抽象同步队列。说白了，就是个FIFO双向队列。其内部通过节点head和tail记录对首和队尾元素。

**state**：在AQS中维持了一个单一的状态信息state，可以通过**getState**、**setState**、**compareAndSetState**函数修改其值。

- **ReentrantLock**：state可以用来表示当前线程获取锁的可重入次数。

- **ReentrantReadWriteLock**：state的高16位表示读状态，也就是获取该读锁的次数，低16位表示获取到写锁的线程的可重入次数。
- **semaphore**：state用来表示当前可用信号的个数。
- **CountDownlatch**：state用来表示计数器当前的值。

对于AQS来讲，线程同步的关键是对状态值**state**进行操作。

在独占方式下获取和释放资源使用的方法：

```java
void acquire(int arg);
void acquireInterruptibly(int arg);
boolean release(int arg);
```

在共享方式获取和释放资源使用的方法：

```java
void acquireShared(int arg);
void acquireSharedInterruptibly(int arg);
boolean releaseShared(int arg);
```

使用独占方式获取的资源是与**具体线程绑定**的，就是说如果一个线程获取到了资源，就会标记是这个线程获取到了，其他线程再尝试操作state获取资源时会**发现当前该资源不是自己持有的**，就会在**获取失败后被阻塞**。（比如ReentrantLock）

对应的共享方式的资源与具体线程是不相关的，当多个线程去请求资源时通过CAS方式竞争获取资源，当一个线程获取到了资源后，另外一个线程再次去获取时如果当前资源还能满足它的资源，则当前线程只需要使用CAS方式进行获取即可。（比如semaphore）

看一下**acquire**方法：

```java
// tryAcquire 具体的子类去实现，并维护state的状态
public final void acquire(int arg) {
    if (!tryAcquire(arg) &&
        acquireQueued(addWaiter(Node.EXCLUSIVE), arg)) // 如果失败标记状态，入队
        selfInterrupt();
}
```

看一下**release**方法：

```java
// tryRelease 具体的子类是实现，并设置state的状态
public final boolean release(int arg) {
    if (tryRelease(arg)) {
        Node h = head;
        if (h != null && h.waitStatus != 0)
            unparkSuccessor(h); // 调用unpark唤醒队列的线程，并调用tryAcquire尝试，看是否需要，如果不需要，继续挂起
        return true;
    }
    return false;
}
```

**acquireShared和releaseShared**和上面的方法的思想差不多


