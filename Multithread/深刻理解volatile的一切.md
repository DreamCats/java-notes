## 引言

> 不得不说，如果谈到volatile只会它的作用：可见性，可序性和不能保证原子性，就太Low了些，因此还得熟悉其中的奥妙才行呀...

<!-- more -->

## 可见性

**volatile的可见性是指当一个变量被volatile修饰后，这个变量就对所有线程均可见。白话点就是说当一个线程修改了一个volatile修饰的变量后，其他线程可以立刻得知这个变量的修改，拿到最这个变量最新的值。**

```java
public class VolatileVisibleDemo {

//    private  boolean isReady = true;
    private volatile boolean isReady = true;

    void m() {
        System.out.println(Thread.currentThread().getName() + " m start...");
        while (isReady) {
        }
        System.out.println(Thread.currentThread().getName() + " m end...");
    }

    public static void main(String[] args) {
        VolatileVisibleDemo demo = new VolatileVisibleDemo();
        new Thread(() -> demo.m(), "t1").start();
        try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
        demo.isReady = false; // 刚才一秒过后开始执行
    }
}
```

**分析：一开始isReady为true，m方法中的while会一直循环，而主线程开启开线程之后会延迟1s将isReady赋值为false，若不加volatile修饰，则程序一直在运行，若加了volatile修饰，则程序最后会输出t1 m end...**

## 有序性

**有序性是指程序代码的执行是按照代码的实现顺序来按序执行的；volatile的有序性特性则是指禁止JVM指令重排优化。**

```java
public class Singleton {
    private static Singleton instance = null;
    //private static volatile Singleton instance = null;
    private Singleton() { }

    public static Singleton getInstance() {
        //第一次判断
        if(instance == null) {
            synchronized (Singleton.class) {
                if(instance == null) {
                    //初始化，并非原子操作
                    instance = new Singleton(); 
                }
            }
        }
        return instance;
    }
}
```

**上面的代码是一个很常见的单例模式实现方式，但是上述代码在多线程环境下是有问题的。为什么呢，问题出在instance对象的初始化上，因为`instance = new Singleton();`这个初始化操作并不是原子的，在JVM上会对应下面的几条指令：**

```java
memory =allocate();    //1. 分配对象的内存空间 
ctorInstance(memory);  //2. 初始化对象 
instance =memory;     //3. 设置instance指向刚分配的内存地址
```

**上面三个指令中，步骤2依赖步骤1，但是步骤3不依赖步骤2，所以JVM可能针对他们进行指令重拍序优化，重排后的指令如下：**

```java
memory =allocate();    //1. 分配对象的内存空间 
instance =memory;     //3. 设置instance指向刚分配的内存地址
ctorInstance(memory);  //2. 初始化对象 
```

**这样优化之后，内存的初始化被放到了instance分配内存地址的后面，这样的话当线程1执行步骤3这段赋值指令后，刚好有另外一个线程2进入getInstance方法判断instance不为null，这个时候线程2拿到的instance对应的内存其实还未初始化，这个时候拿去使用就会导致出错。**

**所以我们在用这种方式实现单例模式时，会使用volatile关键字修饰instance变量，这是因为volatile关键字除了可以保证变量可见性之外，还具有防止指令重排序的作用。当用volatile修饰instance之后，JVM执行时就不会对上面提到的初始化指令进行重排序优化，这样也就不会出现多线程安全问题了。**

## 不能保证原子性

**volatile关键字能保证变量的可见性和代码的有序性，但是不能保证变量的原子性，下面我再举一个volatile与原子性的例子：**

```java
public class VolatileAtomicDemo {
    public static volatile int count = 0;

    public static void increase() {
        count++;
    }

    public static void main(String[] args) {
        Thread[] threads = new Thread[20];
        for(int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for(int j = 0; j < 1000; j++) {
                    increase();
                }
            });
            threads[i].start();
        }
        //等待所有累加线程结束
        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
        System.out.println(count);
    }
}
```

**上面这段代码创建了20个线程，每个线程对变量count进行1000次自增操作，如果这段代码并发正常的话，结果应该是20000，但实际运行过程中经常会出现小于20000的结果，因为count++这个自增操作不是原子操作。**[可参照这张图](https://www.processon.com/diagraming/5e130e50e4b0c090e0b7c183)

## 内存屏障

**Java的Volatile的特征是任何读都能读到最新值，本质上是JVM通过内存屏障来实现的；为了实现volatile内存语义，JMM会分别限制重排序类型。下面是JMM针对编译器制定的volatile重排序规则表：**

| 是否能重排序 | 第二个操作 |            |            |
| :----------: | :--------: | :--------: | :--------: |
|  第一个操作  | 普通读/写  | volatile读 | volatile写 |
|  普通读/写   |            |            |     no     |
|  volatile读  |     no     |     no     |     no     |
|  volatile写  |            |     no     |     no     |

**从上表我们可以看出：**

- 当第二个操作是volatile写时，不管第一个操作是什么，都不能重排序。这个规则确保volatile写之前的操作不会被编译器重排序到volatile写之后。
- 当第一个操作是volatile读时，不管第二个操作是什么，都不能重排序。这个规则确保volatile读之后的操作不会被编译器重排序到volatile读之前。
- 当第一个操作是volatile写，第二个操作是volatile读时，不能重排序。

**为了实现volatile的内存语义，编译器在生成字节码时，会在指令序列中插入内存屏障来禁止特定类型的处理器重排序。对于编译器来说，发现一个最优布置来最小化插入屏障的总数几乎不可能，为此，JMM采取保守策略。下面是基于保守策略的JMM内存屏障插入策略：**

- 在每个volatile写操作的前面插入一个StoreStore屏障。
- 在每个volatile写操作的后面插入一个StoreLoad屏障。
- 在每个volatile读操作的后面插入一个LoadLoad屏障。
- 在每个volatile读操作的后面插入一个LoadStore屏障。

**volatile写插入内存指令图：**

![volatile](https://segmentfault.com/img/bV7PaN?w=485&h=313)

**上图中的StoreStore屏障可以保证在volatile写之前，其前面的所有普通写操作已经对任意处理器可见了。这是因为StoreStore屏障将保障上面所有的普通写在volatile写之前刷新到主内存。**

**这里比较有意思的是volatile写后面的StoreLoad屏障。这个屏障的作用是避免volatile写与后面可能有的volatile读/写操作重排序。因为编译器常常无法准确判断在一个volatile写的后面，是否需要插入一个StoreLoad屏障（比如，一个volatile写之后方法立即return）。为了保证能正确实现volatile的内存语义，JMM在这里采取了保守策略：在每个volatile写的后面或在每个volatile读的前面插入一个StoreLoad屏障。从整体执行效率的角度考虑，JMM选择了在每个volatile写的后面插入一个StoreLoad屏障。因为volatile写-读内存语义的常见使用模式是：一个写线程写volatile变量，多个读线程读同一个volatile变量。当读线程的数量大大超过写线程时，选择在volatile写之后插入StoreLoad屏障将带来可观的执行效率的提升。从这里我们可以看到JMM在实现上的一个特点：首先确保正确性，然后再去追求执行效率。**

**volatile读插入内存指令图：**
![volatile读](https://segmentfault.com/img/bV7Pbg?w=500&h=306)

**上图中的LoadLoad屏障用来禁止处理器把上面的volatile读与下面的普通读重排序。LoadStore屏障用来禁止处理器把上面的volatile读与下面的普通写重排序。**

**上述volatile写和volatile读的内存屏障插入策略非常保守。在实际执行时，只要不改变volatile写-读的内存语义，编译器可以根据具体情况省略不必要的屏障。下面我们通过具体的示例代码来说明：**

```java
class VolatileBarrierExample {
    int a;
    volatile int v1 = 1;
    volatile int v2 = 2;

    void readAndWrite() {
        int i = v1;           //第一个volatile读
        int j = v2;           // 第二个volatile读
        a = i + j;            //普通写
        v1 = i + 1;          // 第一个volatile写
        v2 = j * 2;          //第二个 volatile写
    }
}
```

**针对readAndWrite()方法，编译器在生成字节码时可以做如下的优化：**

![readAndWrite](https://segmentfault.com/img/bV7Pbl?w=491&h=465)

**注意，最后的StoreLoad屏障不能省略。因为第二个volatile写之后，方法立即return。此时编译器可能无法准确断定后面是否会有volatile读或写，为了安全起见，编译器常常会在这里插入一个StoreLoad屏障。**

### volatile汇编：

```java
0x000000011214bb49: mov    %rdi,%rax
  0x000000011214bb4c: dec    %eax
  0x000000011214bb4e: mov    %eax,0x10(%rsi)
  0x000000011214bb51: lock addl $0x0,(%rsp)     ;*putfield v1
                                                ; - com.earnfish.VolatileBarrierExample::readAndWrite@21 (line 35)

  0x000000011214bb56: imul   %edi,%ebx
  0x000000011214bb59: mov    %ebx,0x14(%rsi)
  0x000000011214bb5c: lock addl $0x0,(%rsp)     ;*putfield v2
                                                ; - com.earnfish.VolatileBarrierExample::readAndWrite@28 (line 36)
```

**对应的Java：**

```java
v1 = i - 1;          // 第一个volatile写
v2 = j * i;          // 第二个volatile写
```

**可见其本质是通过一个lock指令来实现的。那么lock是什么意思呢？**

**它的作用是使得本CPU的Cache写入了内存，该写入动作也会引起别的CPU invalidate其Cache。所以通过这样一个空操作，可让前面volatile变量的修改对其他CPU立即可见。**

- 锁住内存
- 任何读必须在写完成之后再执行
- 使其它线程这个值的栈缓存失效

[参考volatile内存屏障](https://segmentfault.com/a/1190000014315651?utm_source=tag-newest)