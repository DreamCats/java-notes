# Object

```java
public final native Class<?> getClass();
public native int hashCode(); // 返回对象的哈希代码值。
public boolean equals(Object obj) 
protected native Object clone() // 创建并返回此对象的副本。
public String toString() // 返回对象的字符串表示形式。
public final native void notify(); // 唤醒正在该对象的监视器上等待的单个线程。
public final native void notifyAll(); // 唤醒正在该对象的监视器上等待的全部线程。
public final native void wait(); // 使当前线程等待，直到另一个线程调用此对象的方法或方法。
protected void finalize(); // 当垃圾回收确定不再有对对象的引用时，由对象上的垃圾回收器调用。
```

