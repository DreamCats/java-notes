## 类

```java
// 什么也没有，就有个泛型
public class ThreadLocal<T>
```

但是Entry

```java
// 
static class Entry extends WeakReference<ThreadLocal<?>> {
    /** The value associated with this ThreadLocal. */
    Object value;

    Entry(ThreadLocal<?> k, Object v) {
        super(k); // key super了， 弱引用
        value = v;
    }
}
```



## 变量

```java
// hashcode 是指定一个固定值，开始自增
private static AtomicInteger nextHashCode =
        new AtomicInteger();
// 连续生成的哈希码之间的区别——将隐式顺序线程本地id转换为接近最优扩散的倍增哈希值，用于两级幂的表。	
private static final int HASH_INCREMENT = 0x61c88647;
```

## 方法

### get()

```java
// 依托Thread
public T get() {
    // 获取线程
    Thread t = Thread.currentThread();
    // 得到定制的map
    ThreadLocalMap map = getMap(t);
    if (map != null) {
        ThreadLocalMap.Entry e = map.getEntry(this);
        if (e != null) {
            @SuppressWarnings("unchecked")
            T result = (T)e.value;
            return result;
        }
    }
    return setInitialValue();
}
```

### set()

```java
// 依然是map
public void set(T value) {
    // 获取线程
    Thread t = Thread.currentThread();
    // 得到map
    ThreadLocalMap map = getMap(t);
    if (map != null)
        // set
        map.set(this, value);
    else
        // 没有就创建
        createMap(t, value);
}
```

