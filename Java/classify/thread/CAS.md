# CAS

面试官：了解CAS嘛？

我：了解，**我们在读Concurrent包下的类的源码时，发现无论是**ReenterLock内部的AQS，还是各种Atomic开头的原子类，内部都应用到了`CAS`，并且在调用getAndAddInt方法中，会有compareAndSwapInt方法。其实都是调用unsafe.compareAndSwap()方法。

## compareAndSwapInt

细说compareAndSwapInt方法，那岂不是要看源码：

```cpp
UNSAFE_ENTRY(jboolean, Unsafe_CompareAndSwapInt(JNIEnv *env, jobject unsafe, jobject obj, jlong offset, jint e, jint x))
  UnsafeWrapper("Unsafe_CompareAndSwapInt");
  oop p = JNIHandles::resolve(obj); // oop 安全点 引用
  jint* addr = (jint *) index_oop_from_field_offset_long(p, offset); // 计算偏移地址
  return (jint)(Atomic::cmpxchg(x, addr, e)) == e; // 原子操作，比较和更新
UNSAFE_END
```

Java中的`compareAndSwapInt（obj, offset, expect, update）`比较清楚，**意思就是如果`obj`内的`value`和`expect`相等，就证明没有其他线程改变过这个变量，那么就更新它为`update`，如果这一步的`CAS`没有成功，那就采用自旋的方式继续进行`CAS`操作**，取出乍一看这也是两个步骤了啊，其实在`JNI`里是借助于一个`CPU`指令完成的。所以还是原子操作。

原子操作：可理解为要么这些行为都执行，不被打扰，要不都不执行。实在不行，就理解为串行操作，

再细说这个cpu指令，那就要看该源码了：

```hpp
inline jint     Atomic::cmpxchg    (jint     exchange_value, volatile jint*     dest, jint     compare_value) {
  int mp = os::is_MP(); // 返回处理器（判断是多核还是单核，单核省略lock前缀）
  __asm__ volatile (LOCK_IF_MP(%4) "cmpxchgl %1,(%3)" // 比较
                    : "=a" (exchange_value)
                    : "r" (exchange_value), "a" (compare_value), "r" (dest), "r" (mp)
                    : "cc", "memory");
  return exchange_value;
}
```



调用了`Atomic::cmpxchg(x, addr, e)`, 其中参数**x是即将更新的值**，参数**e是原内存的值**。代码中能看到cmpxchg有基于各个平台的实现。

## ABA

面试官：知道ABA问题嘛？

我：知道，描述: 第一个线程取到了变量 x 的值 A，然后巴拉巴拉干别的事，总之就是只拿到了变量 x 的值 A。这段时间内第二个线程**也取到了变量 x 的值 A，然后把变量 x 的值改为 B，然后巴拉巴拉干别的事，最后又把变量 x 的值变为 A** （相当于还原了）。在这之后第一个线程终于进行了变量 x 的操作，但是此时**变量 x 的值还是 A**，所以 compareAndSet 操作是成功。

解决方法：**目前在JDK的atomic包里提供了一个类`AtomicStampedReference`来解决ABA问题。**说白了，就是版本号

举一个例子：

```java
public class ABADemo {
    static AtomicInteger atomicInteger = new AtomicInteger(100);
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100, 1);

    public static void main(String[] args) {
        System.out.println("=====ABA的问题产生=====");
        new Thread(() -> {
            atomicInteger.compareAndSet(100, 101);
            atomicInteger.compareAndSet(101, 100);
        }, "t1").start();

        new Thread(() -> {
            // 保证线程1完成一次ABA问题
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println(atomicInteger.compareAndSet(100, 2020) + " " + atomicInteger.get());
        }, "t2").start();
        try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }

        System.out.println("=====解决ABA的问题=====");
        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp(); // 第一次获取版本号
            System.out.println(Thread.currentThread().getName() + " 第1次版本号" + stamp);
            try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }
            atomicStampedReference.compareAndSet(100, 101, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + "\t第2次版本号" + atomicStampedReference.getStamp());
            atomicStampedReference.compareAndSet(101, 100, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + "\t第3次版本号" + atomicStampedReference.getStamp());
        }, "t3").start();

        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t第1次版本号" + stamp);
            try { TimeUnit.SECONDS.sleep(4); } catch (InterruptedException e) { e.printStackTrace(); }
            boolean result = atomicStampedReference.compareAndSet(100, 2020, stamp, stamp + 1);
            System.out.println(Thread.currentThread().getName() + "\t修改是否成功" + result + "\t当前最新实际版本号：" + atomicStampedReference.getStamp());
            System.out.println(Thread.currentThread().getName() + "\t当前最新实际值：" + atomicStampedReference.getReference());
        }, "t4").start();

    }
}
```

## CAS设计思想

面试官：了解CAS的设计思想吗？

我：思想？我理解的是乐观思想，你听我娓娓道来，何为乐观，乐观就是在我角度上认为"哈士奇"不会去偷吃我的菜，那么我就不用同步加个房间锁住门防止"哈士奇"吃。那设计的时候，cpu咋知道"哈士奇"吃过没，那就看这道菜是不是少了点，或者量少了一半等。那么主人回来吃的时候肯定不会开吃了，是吧？所以主人吃就跟CAS的更新值一样，这道菜没被"哈士奇"吃过是CAS的期望值。 那么cpu的这个cmpxchg就是干这个事的。