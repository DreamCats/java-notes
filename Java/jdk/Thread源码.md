## 类

```java
// 实现了Runnable
public class Thread implements Runnable
```

## 变量

```java
// 默认不是守护线程
private boolean     daemon = false;
// run的时候要用到
private Runnable target;
// 线程组
private ThreadGroup group;

// 这里很重要，TL的变量在Thread里面自定义的一种Map
ThreadLocal.ThreadLocalMap threadLocals = null;
```

## 方法

### start()

```java
public synchronized void start() {
    /**
         * This method is not invoked for the main method thread or "system"
         * group threads created/set up by the VM. Any new functionality added
         * to this method in the future may have to also be added to the VM.
         *
         * A zero status value corresponds to state "NEW".
         */
    if (threadStatus != 0) // 不为0，就抛出异常了
        throw new IllegalThreadStateException();

    /* Notify the group that this thread is about to be started
         * so that it can be added to the group's list of threads
         * and the group's unstarted count can be decremented. */
    group.add(this); // 添加到线程组

    boolean started = false;
    try {
        start0(); // 本地方法
        started = true;
    } finally {
        try {
            if (!started) {
                group.threadStartFailed(this);
            }
        } catch (Throwable ignore) {
            /* do nothing. If start0 threw a Throwable then
                  it will be passed up the call stack */
        }
    }
}

private native void start0();	
```

### run()

```java
// 实际上走的Runnable接口的run
@Override
public void run() {
    if (target != null) {
        target.run();
    }
}
```

### join(long millis)

```java
public final synchronized void join(long millis)
    throws InterruptedException {
    long base = System.currentTimeMillis();
    long now = 0;

    if (millis < 0) {
        throw new IllegalArgumentException("timeout value is negative");
    }

    if (millis == 0) { // 调用join，没有传参数，默认是0，那么会来到这里
        while (isAlive()) { // 当run方法走完，才能为false
            wait(0);
        }
    } else {
        while (isAlive()) {
            long delay = millis - now;
            if (delay <= 0) {
                break;
            }
            wait(delay);
            now = System.currentTimeMillis() - base;
        }
    }
}

// 测试此线程是否处于活动状态。如果一个线程已经启动并且还没有死，那么它就是活的。
public final native boolean isAlive();
```

