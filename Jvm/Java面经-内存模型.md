## 引言

**JVM - 内存模型**

<!-- more -->

![](https://www.pdai.tech/_images/pics/c9ad2bf4-5580-4018-bce4-1b9a71804d9c.png)

## 程序计数器

记录正在执行的虚拟机字节码指令的地址（如果正在执行的是本地方法则为空）。

## Java虚拟机栈

每个 Java 方法在执行的同时会创建一个栈帧用于存储局部变量表、操作数栈、常量池引用等信息，从调用直至执行完成的过程，就对应着一个栈帧在 Java 虚拟机栈中入栈和出栈的过程。

![](https://www.pdai.tech/_images/pics/926c7438-c5e1-4b94-840a-dcb24ff1dafe.png)

可以通过 -Xss 这个虚拟机参数来指定每个线程的 Java 虚拟机栈内存大小：java -Xss512M HackTheJava

该区域可能抛出以下异常：

- 当线程请求的栈深度超过最大值，会抛出 StackOverflowError 异常；
- 栈进行动态扩展时如果无法申请到足够内存，会抛出 OutOfMemoryError 异常。

## 本地方法栈

本地方法一般是用其它语言（C、C++ 或汇编语言等）编写的，并且被编译为基于本机硬件和操作系统的程序，对待这些方法需要特别处理。

本地方法栈与 Java 虚拟机栈类似，它们之间的区别只不过是本地方法栈为本地方法服务。

## 堆

所有对象都在这里分配内存，是垃圾收集的主要区域（"GC 堆"）。

现代的垃圾收集器基本都是采用分代收集算法，针对不同类型的对象采取不同的垃圾回收算法，可以将堆分成两块：

- 新生代（Young Generation）
- 老年代（Old Generation）

新生代可以继续划分成以下三个空间：

- Eden（伊甸园）
- From Survivor（幸存者）
- To Survivor

堆不需要连续内存，并且可以动态增加其内存，增加失败会抛出 OutOfMemoryError 异常。

可以通过 -Xms 和 -Xmx 两个虚拟机参数来指定一个程序的堆内存大小，第一个参数设置初始值，第二个参数设置最大值。java -Xms1M -Xmx2M HackTheJava

## 方法区

用于存放已被加载的类信息、常量、静态变量、即时编译器编译后的代码等数据。

和堆一样不需要连续的内存，并且可以动态扩展，动态扩展失败一样会抛出 OutOfMemoryError 异常。

对这块区域进行垃圾回收的主要目标是对常量池的回收和对类的卸载，但是一般比较难实现。

JDK 1.7 之前，HotSpot 虚拟机把它当成永久代来进行垃圾回收。但是从 JDK 1.7 开始，已经把原本放在永久代的字符串常量池移到 Native Method 中。

## 运行时常量池

运行时常量池是方法区的一部分。

Class 文件中的常量池（编译器生成的各种字面量和符号引用）会在类加载后被放入这个区域。

除了在编译期生成的常量，还允许动态生成，例如 String 类的 intern()。

## 直接内存

在 JDK 1.4 中新加入了 NIO 类，它可以使用 Native 函数库直接分配堆外内存，然后通过一个存储在 Java 堆里的 DirectByteBuffer 对象作为这块内存的引用进行操作。

这样能在一些场景中显著提高性能，因为避免了在 Java 堆和 Native 堆中来回复制数据。

## 请你谈谈对OOM的认识

- `java.lang.StackOverflowError`:栈空间溢出 ，递归调用卡死
- `java.lang.OutOfMemoryError:Java heap space`:堆内存溢出 ， 对象过大
- `java.lang.OutOfMemoryError:GC overhead limit exceeded`:GC回收时间过长
- `java.lang.OutOfMemoryError:Direct buffer memory`执行内存挂了，比如：NIO
- `java.lang.OutOfMemoryError:unable to create new native thread`
  - 应用创建了太多线程，一个应用进程创建了多个线程，超过系统承载极限
  - 你的服务器并不允许你的应用程序创建这么多线程，linux系统默认允许单个进程可以创建的线程数是1024，超过这个数量，就会报错
  - 解决办法：降低应用程序创建线程的数量，分析应用给是否针对需要这么多线程，如果不是，减到最低修改linux服务器配置

- `java.lang.OutOfMemoryError:Metaspace`:元空间主要存放了虚拟机加载的类的信息、常量池、静态变量、即时编译后的代码