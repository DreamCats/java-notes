面试官：了解volatile嘛？有啥子特性

我：了解，两个特性：内存可见性、禁止重排序

禁止重排序：不管是编译器还是JVM还是CPU，都会对一些指令进行重排序，目的是为了提高程序运行的速度，提高程序的性能，毫无疑问，在单线程下没毛病，多线程就似乎生病了。

给你稍微举举例子：禁止重排->单例模式

```java
// 来一波双重校验
public class Test {
    private volatile static Test instance = null;
    private Test(){}

    private static Test getInstance() {
        if (instance == null) {
            synchronized (Test.class) {
                if (instance == null) {
                    instance = new Test();
                }
            }
        }
        return instance;
    }
}
// instance类变量前面修饰的volatile？是吧？
```

问题在于：上面的代码是一个很常见的单例模式实现方式，但是上述代码在多线程环境下是有问题的。为什么呢，问题出在instance对象的初始化上，因为`instance = new Singleton();`这个初始化操作并不是原子的，在JVM上会对应下面的几条指令：

```c
memory =allocate();    //1. 分配对象的内存空间 
ctorInstance(memory);  //2. 初始化对象 
instance = memory;     //3. 设置instance指向刚分配的内存地址
```

上面三个指令中，步骤2依赖步骤1，但是步骤3不依赖步骤2，所以JVM可能针对他们进行指令重拍序优化，重排后的指令如下：

```c
memory =allocate();    //1. 分配对象的内存空间 
instance = memory;     //3. 设置instance指向刚分配的内存地址
ctorInstance(memory);  //2. 初始化对象 
```

这样优化之后，内存的初始化被放到了instance分配内存地址的后面，这样的话当线程1执行步骤3这段赋值指令后，刚好有另外一个线程2进入getInstance方法判断instance不为null，这个时候线程2拿到的instance对应的内存其实还未初始化，这个时候拿去使用就会导致出错。

这里多说多说一点：为什么在synchronized上面多加了一次判断

还不是因为synchronized比较笨重，锁了代码块嘛，多线程不能每次都要进来块中，岂不是都要发生阻塞等这class的锁呀，直接给他上面判断一下不为空就直接跳出去了。提高了性能哇。

其实这里也能体现出volatile的内存可见性，让其他线程对这个实例可见。

我们继续说volatile的内存可见性

扯一波JMM内存模型

![volatile保证内存可见性和避免重排](http://media.dreamcat.ink/uPic/volatile保证内存可见性和避免重排.png)

根据这个图如何回答总结JMM内存模型，看各位的造化了，理解讲出来即可。结合例子讲也可以

1. 先说结构
2. 再说为什么是这样的结构，原因是什么？
3. 然后扯流程
4. 撒花结束

面试官：知道底层结构嘛？

我：禁止重排是利用内存屏障来解决的，其实最根本的还是cpu的一个**lock**指令：**它的作用是使得本CPU的Cache写入了内存，该写入动作也会引起别的CPU invalidate其Cache。所以通过这样一个空操作，可让前面volatile变量的修改对其他CPU立即可见。**

- 锁住内存
- 任何读必须在写完成之后再执行
- 使其它线程这个值的栈缓存失效