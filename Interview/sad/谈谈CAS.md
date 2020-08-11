面试官：了解CAS嘛？

我：了解，**我们在读Concurrent包下的类的源码时，发现无论是**ReenterLock内部的AQS，还是各种Atomic开头的原子类，内部都应用到了`CAS`，并且在调用getAndAddInt方法中，会有compareAndSwapInt方法。其实都是调用unsafe.compareAndSwap()方法。

细说compareAndSwapInt方法

`compareAndSwapInt（obj, offset, expect, update）`比较清楚，**意思就是如果`obj`内的`value`和`expect`相等，就证明没有其他线程改变过这个变量，那么就更新它为`update`，如果这一步的`CAS`没有成功，那就采用自旋的方式继续进行`CAS`操作**，取出乍一看这也是两个步骤了啊，其实在`JNI`里是借助于一个`CPU`指令完成的。所以还是原子操作。

原子操作：可理解为要么这些行为都执行，不被打扰，要不都不执行。实在不行，就理解为串行操作，

再细说这个cpu指令：

调用了`Atomic::cmpxchg(x, addr, e)`, 其中参数**x是即将更新的值**，参数**e是原内存的值**。代码中能看到cmpxchg有基于各个平台的实现。

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

