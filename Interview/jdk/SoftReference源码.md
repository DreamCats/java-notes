## 类

```java
// 继承了Reference
public class SoftReference<T> extends Reference<T>
```

## 参数

```java
// 时间戳时钟，由垃圾收集器更新
static private long clock;
// 通过每次调用get方法更新时间戳。当选择要清除的软引用时，VM可以使用这个字段，但这不是必需的。
private long timestamp;
```

## 方法

### get()

```java
// 返回该引用对象的引用。
// 如果该引用对象已经被程序或垃圾收集器清除，则该方法返回<code>null</code>。
public T get() {
    T o = super.get();
    if (o != null && this.timestamp != clock)
        this.timestamp = clock;
    return o;
}
```

