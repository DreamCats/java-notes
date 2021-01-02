## 类

```java
public class Object // 顶层所有父类，不需要实现和继承。。 造娃第一人
```

## 方法

### registerNatives

```java
static {
    registerNatives(); // 打开试试，看名字知道是注册本地方法的
}

// 打开是
private static native void registerNatives(); // 额
```

### getClass()

```java
public final native Class<?> getClass(); // 本地方法，返回runtime的类
```

### hashcode()

```java
public native int hashCode(); // 返回对象的哈希码值。
```

注意：对象的哈希码，意味着，只要对象内存地址不一样，这玩意就不一样

### equals()

```java
public boolean equals(Object obj) {
    // 还是用了==， ==在对象的时候，比较地址值
    return (this == obj);
}
```

### clone()

```java
// 创建并返回此对象的副本。
protected native Object clone() throws CloneNotSupportedException;
```

### toString()

```java
public String toString() {
    // 哈希码的16进制
    return getClass().getName() + "@" + Integer.toHexString(hashCode());
}
```

### notify()

```java
// 本地方法，和wait结合使用，很香
public final native void notify()
```

### notifyAll()

```java
// 很上面一样，不过区别在于，这个能唤醒所有wait的线程
public final native void notifyAll();
```

### wait()

```java
public final native void wait(long timeout) throws InterruptedException;

public final void wait(long timeout, int nanos) throws InterruptedException {
    if (timeout < 0) {
        throw new IllegalArgumentException("timeout value is negative");
    }

    if (nanos < 0 || nanos > 999999) {
        throw new IllegalArgumentException(
            "nanosecond timeout value out of range");
    }

    if (nanos > 0) {
        timeout++;
    }

    wait(timeout);
}

public final void wait() throws InterruptedException {
    wait(0);
}
// 其实还是调用wait(long timeout)

```

### Finalize()

```java
// jvm gc 你们懂得 回收该对象
protected void finalize() throws Throwable { }
```

