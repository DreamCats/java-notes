## 美团一面
### 自我介绍 + 项目简单介绍
略

### 集合有哪些
- Set
    - TreeSet
    - HashSet
    - LinkedHashSet
- List
    - ArrayList
    - Vector
    - LinkedList
- Map
    - TreeMap
    - HashMap
    - HashTable

### ArrayList和LinkedList区别
- ArrayList实现了List接口，是顺序容器，即元素存放的数据与放进去的顺序相同，允许放入null元素，底层通过数组实现。
- LinkedList同时实现了List接口和Deque接口，也就是说它既可以看作一个顺序容器，又可以看作一个队列（Queue），同时又可以看作一个栈（Stack）。LinkedList底层通过双向链表实现。

### hashmap怎么扩容（多线程扩容为什么会死循环），put过程
#### 扩容
> resize()

1. 创建一个新的Entry空数组，长度是原来的2倍。
2. 遍历原Entry数组，把所有的Entry重新Hash到新数组里。为什么要重新Hash呢？因为长度扩大以后，Hash的规则也随之改变了。

**多线程问题**：
> 多个线程并发进行时，因为一个线程先期完成了扩容，将原Map的链表重新散列到自己的表中，并且链表变成了倒序，后一个线程再扩容时，又进行自己的散列，再次将倒序链表变为正序链表。于是形成了一个环形链表，当get表中不存在的元素时，造成死循环。

#### put
1. 1.7
- 判断当前数组是否需要初始化
- 如果key为空，则put一个空值进去
- 根据key计算hashcode
- 根据计算的hashcode定位index的桶
- 如果桶是一个链表，则需要遍历判断里面的hashcode、key是否和传入的key相等，如果相等则进行覆盖，并返回原来的值
- 如果桶是空的，说明当前位置没有数据存入，此时新增一个Entry对象写入当前位置。

2. 1.8
- 判断当前桶是否为空，空的就需要初始化（resize中会判断是否进行初始化）。
- 根据当前key的hashcode定位到具体的桶中并判断是否为空，为空则表明没有Hash冲突，就直接在当前位置创建一个新桶。
- 如果当前桶有值（Hash冲突），那么就要比较当前桶中的key、key的hashcode与写入的key是否相等，相等就赋值给e，在第8步的时候会统一进行赋值及返回。
- 如果当前桶为「红黑树」，那就要按照红黑树的方式写入数据。
- 如果是个链表，就需要将当前的key、value封装称一个新节点写入到当前桶的后面形成链表。
- 接着判断当前链表的大小是否「大于预设的阈值」，大于就要转换成为「红黑树」
- 如果在遍历过程中找到key相同时直接退出遍历。
- 如果e != null就相当于存在相同的key，那就需要将值覆盖。
- 最后判断是否需要进行扩容。

### concurrentHashMap 1.7和1.8
1. 1.7
- 唯一的区别就是其中的核心数据如 value ，以及链表都是 volatile 修饰的，保证了获取时的可见性。
- ConcurrentHashMap 采用了「分段锁」技术，其中 Segment 继承于 ReentrantLock。
- 不会像HashTable那样不管是put还是get操作都需要做同步处理，理论上 ConcurrentHashMap 支持 CurrencyLevel (Segment 数组数量)的线程并发。
- 每当一个线程占用锁访问一个 Segment 时，不会影响到其他的 Segment。

put:
- 将当前的Segment中的table通过key的hashcode定位到HashEntry
- 遍历该HashEntry，如果不为空则判断传入的key和当前遍历的key是否相等，相等则覆盖旧的value
- 不为空则需要新建一个HashEntry并加入到Segment中，同时会先判断是否需要扩容
- 最后会解除在1中所获取当前Segment的锁。

2. 1.8
- 其中抛弃了原有的 Segment 分段锁，而采用了 CAS + synchronized 来保证并发安全性

put:
- 根据key计算出hashcode
- 判断是否需要进行初始化
- f即为当前key定位出的Node，如果为空表示当前位置可以写入数据，利用CAS尝试写入，失败则自旋保证成功。
- 如果当前位置的hashcode == MOVED == -1，则需要进行扩容。
- 如果都不满足，则利用synchronized锁写入数据
- 如果数量大于TREEIFY_THRESHOLD 则要转换为红黑树。

### 接口和抽象类区别
- 接口的方法默认是 public，所有方法在接口中不能有实现(Java 8 开始接口方法可以有默认实现），而抽象类可以有非抽象的方法。
- 接口中除了static、final变量，不能有其他变量，而抽象类中则不一定。
- 一个类可以实现多个接口，但只能实现一个抽象类。接口自己本身可以通过implement关键字扩展多个接口。
- 接口方法默认修饰符是public，抽象方法可以有public、protected和default这些修饰符（抽象方法就是为了被重写所以不能使用private关键字修饰！）。
- 从设计层面来说，抽象是对类的抽象，是一种模板设计，而接口是对行为的抽象，是一种行为的规范。

### JVM内存分区
两张图：
![](https://user-gold-cdn.xitu.io/2020/3/31/17130c2c0a57c0f7?imageView2/0/w/1280/h/960/format/webp/ignore-error/1)
![](https://user-gold-cdn.xitu.io/2020/3/31/17130c2c0bed60c9?imageView2/0/w/1280/h/960/format/webp/ignore-error/1)

### 新生代：eden，survivor_from, survivor_to
大部分情况，对象都会首先在 Eden 区域分配，在一次新生代垃圾回收后，如果对象还存活，则会进入 s1("To")，并且对象的年龄还会加 1(Eden 区->Survivor 区后对象的初始年龄变为 1)，当它的年龄增加到一定程度（默认为 15 岁），就会被晋升到老年代中。
对象晋升到老年代的年龄阈值，可以通过参数 -XX:MaxTenuringThreshold 来设置。经过这次GC后，Eden区和"From"区已经被清空。这个时候，"From"和"To"会交换他们的角色，也就是新的"To"就是上次GC前的“From”，新的"From"就是上次GC前的"To"。
不管怎样，都会保证名为To的Survivor区域是空的。Minor GC会一直重复这样的过程，直到“To”区被填满，"To"区被填满之后，会将所有对象移动到年老代中。

### 垃圾回收算法
- 标记-清除
该算法分为“标记”和“清除”阶段：首先标记出所有需要回收的对象，在标记完成后统一回收所有被标记的对象。它是最基础的收集算法，后续的算法都是对其不足进行改进得到。这种垃圾收集算法会带来两个明显的问题：
    - 效率问题
    - 空间问题（标记清除后会产生大量不连续的碎片）
- 标记-整理
根据老年代的特点提出的一种标记算法，标记过程仍然与“标记-清除”算法一样，但后续步骤不是直接对可回收对象回收，而是让所有存活的对象向一端移动，然后直接清理掉端边界以外的内存。
- 复制
为了解决效率问题，“复制”收集算法出现了。它可以将内存分为大小相同的两块，每次使用其中的一块。当这一块的内存使用完后，就将还存活的对象复制到另一块去，然后再把使用的空间一次清理掉。这样就使每次的内存回收都是对内存区间的一半进行回收。
- 分代
比如在新生代中，每次收集都会有大量对象死去，所以可以选择复制算法，只需要付出少量对象的复制成本就可以完成每次垃圾收集。而老年代的对象存活几率是比较高的，而且没有额外的空间对它进行分配担保，所以我们必须选择“标记-清除”或“标记-整理”算法进行垃圾收集。

### PretenureSizeThreshold，maxTenuringThreshold
默认15

### JVM调优
![](https://juejin.im/post/5e8344486fb9a03c786ef885#heading-61)

### 如何判断对象是否应该被回收 
（引用计数法，可达性分析）

### root根包括哪些
- 虚拟机栈（栈帧中的局部变量区，也叫局部变量表）中应用的对象。
- 方法区中的类静态属性引用的对象
- 方法区中常量引用的对象
- 本地方法栈中JNI（native方法）引用的对象

### CMS回收过程，优缺点
- 初始标记：暂停所有的其他线程，并记录下直接与 root 相连的对象，速度很快 ；
- 并发标记：同时开启 GC 和用户线程，用一个闭包结构去记录可达对象。但在这个阶段结束，这个闭包结构并不能保证包含当前所有的可达对象。因为用户线程可能会不断的更新引用域，所以 GC 线程无法保证可达性分析的实时性。所以这个算法里会跟踪记录这些发生引用更新的地方。
- 重新标记：重新标记阶段就是为了修正并发标记期间因为用户程序继续运行而导致标记产生变动的那一部分对象的标记记录，这个阶段的停顿时间一般会比初始标记阶段的时间稍长，远远比并发标记阶段时间短
- 并发清除：开启用户线程，同时 GC 线程开始对为标记的区域做清扫。

#### 优点
- 并发收集
- 低停顿

#### 缺点
- 对CPU敏感
- 无法处理浮动垃圾
- 使用的标记-清除算法会带来大量的空间碎片

### G1回收过程
- 初始标记
- 并发标记
- 最终标记
- 筛选标记

### 类加载过程（加载，验证，准备，解析，初始化）
- 加载
- 验证
- 准备
- 解析
- 初始化

### 双亲委派优点
双亲委派模型保证了Java程序的稳定运行，可以避免类的重复加载（JVM 区分不同类的方式不仅仅根据类名，相同的类文件被不同的类加载器加载产生的是两个不同的类），也保证了 Java 的核心 API 不被篡改。

### 七层模型
- 物理层：底层数据传输，如网线；网卡标准。
- 数据链路层：定义数据的基本格式，如何传输，如何标识；如网卡MAC地址。
- 网络层：定义IP编址，定义路由功能；如不同设备的数据转发。
- 传输层：端到端传输数据的基本功能；如 TCP、UDP。
- 会话层：控制应用程序之间会话能力；如不同软件数据分发给不同软件。
- 标识层：数据格式标识，基本压缩加密功能。
- 应用层：各种应用软件，包括 Web 应用。

### 四次分手
- 客户端-发送一个 FIN，用来关闭客户端到服务器的数据传送
- 服务器-收到这个 FIN，它发回一 个 ACK，确认序号为收到的序号加1 。和 SYN 一样，一个 FIN 将占用一个序号
- 服务器-关闭与客户端的连接，发送一个FIN给客户端
- 客户端-发回 ACK 报文确认，并将确认序号设置为收到序号加1

> 任何一方都可以在数据传送结束后发出连接释放的通知，待对方确认后进入半关闭状态。当另一方也没有数据再发送的时候，则发出连接释放通知，对方确认后就完全关闭了TCP连接。举个例子：A 和 B 打电话，通话即将结束后，A 说“我没啥要说的了”，B回答“我知道了”，但是 B 可能还会有要说的话，A 不能要求 B 跟着自己的节奏结束通话，于是 B 可能又巴拉巴拉说了一通，最后 B 说“我说完了”，A 回答“知道了”，这样通话才算结束。

### 为什么TCP能保证不丢失
- 滑动窗口
- 拥塞控制

### HTTP和HTTPS的区别
- HTTP协议是超文本传输协议的缩写，英文是Hyper Text Transfer Protocol。它是从WEB服务器传输超文本标记语言(HTML)到本地浏览器的传送协议。
- HTTP是一个基于TCP/IP通信协议来传递数据的协议，传输的数据类型为HTML 文件,、图片文件, 查询结果等。
- HTTPS 并不是新协议，而是让 HTTP 先和 SSL（Secure Sockets Layer）通信，再由 SSL 和 TCP 通信，也就是说 HTTPS 使用了隧道进行通信。通过使用 SSL，HTTPS 具有了加密（防窃听）、认证（防伪装）和完整性保护（防篡改）。

### GET和POST区别
- GET使用URL或Cookie传参，而POST将数据放在BODY中
- GET方式提交的数据有长度限制，则POST的数据则可以非常大
- POST比GET安全，因为数据在地址栏上不可见，没毛病
- 本质区别：GET请求是幂等性的，POST请求不是。

> 这里的幂等性：幂等性是指一次和多次请求某一个资源应该具有同样的副作用。简单来说意味着对同一URL的多个请求应该返回同样的结果。

### 索引数据结构
- hash
- b+

### 为什么用B+树而不用hash和B-Tree
- 利用Hash需要把数据全部加载到内存中，如果数据量大，是一件很消耗内存的事，而采用B+树，是基于按照节点分段加载，由此减少内存消耗。
- 和业务场景有段，对于唯一查找（查找一个值），Hash确实更快，但数据库中经常查询多条数据，这时候由于B+数据的有序性，与叶子节点又有链表相连，他的查询效率会比Hash快的多。
- b+树的非叶子节点不保存数据，只保存子树的临界值（最大或者最小），所以同样大小的节点，b+树相对于b树能够有更多的分支，使得这棵树更加矮胖，查询时做的IO操作次数也更少。

### InooDB和MyISAM的区别（事务，聚集索引，锁的粒度等）
- 事务: InnoDB 是事务型的，可以使用 Commit 和 Rollback 语句。
- 并发: MyISAM 只支持表级锁，而 InnoDB 还支持行级锁。
- 外键: InnoDB 支持外键。
- 备份: InnoDB 支持在线热备份。
- 崩溃恢复: MyISAM 崩溃后发生损坏的概率比 InnoDB 高很多，而且恢复的速度也更慢。
- 其它特性: MyISAM 支持压缩表和空间数据索引。

### 最左匹配是什么意思，联合索引建立索引过程
> MySQL中的索引可以以一定顺序引用多个列，这种索引叫做联合索引一般的，一个联合索引是一个有序元组<a1, a2, …, an>，其中各个元素均为数据表的一列。另外，单列索引可以看成联合索引元素数为1的特例。

索引匹配的最左原则具体是说，假如索引列分别为A，B，C，顺序也是A，B，C：
```
- 那么查询的时候，如果查询【A】【A，B】 【A，B，C】，那么可以通过索引查询
- 如果查询的时候，采用【A，C】，那么C这个虽然是索引，但是由于中间缺失了B，因此C这个索引是用不到的，只能用到A索引
- 如果查询的时候，采用【B】 【B，C】 【C】，由于没有用到第一列索引，不是最左前缀，那么后面的索引也是用不到了
- 如果查询的时候，采用范围查询，并且是最左前缀，也就是第一列索引，那么可以用到索引，但是范围后面的列无法用到索引
```


### 独占锁，共享锁，乐观锁讲一下
- 独享锁：是指该锁一次只能被一个线程所持有。
- 共享锁：是该锁可被多个线程所持有。
- 乐观锁则认为对于同一个数据的并发操作，是不会发生修改的。在更新数据的时候，会采用尝试更新，不断重新的方式更新数据。乐观的认为，不加锁的并发操作时没有事情的。常常采用的是CAS算法，典型的例子就是原子类，通过CAS自旋实现原子类操作的更新。

### NIO是什么？
NIO是一种同步非阻塞的I/O模型，在Java 1.4 中引入了NIO框架，对应 java.nio 包，提供了 Channel , Selector，Buffer等抽象。
NIO中的N可以理解为Non-blocking，不单纯是New。它支持面向缓冲的，基于通道的I/O操作方法。 
NIO提供了与传统BIO模型中的 Socket 和 ServerSocket 相对应的 SocketChannel 和 ServerSocketChannel 两种不同的套接字通道实现,两种通道都支持阻塞和非阻塞两种模式。
阻塞模式使用就像传统中的支持一样，比较简单，但是性能和可靠性都不好；非阻塞模式正好与之相反。对于低负载、低并发的应用程序，可以使用同步阻塞I/O来提升开发速率和更好的维护性；
对于高负载、高并发的（网络）应用，应使用 NIO 的非阻塞模式来开发

### Redis线程模型？多路复用讲一下，为什么redis很快
> redis 内部使用文件事件处理器 file event handler，这个文件事件处理器是「单线程」的，所以 redis 才叫做单线程的模型。它采用 IO 多路复用机制同时监听多个 socket，根据 socket 上的事件来选择对应的事件处理器进行处理。

文件事件处理器的结构包含 4 个部分：
- 多个 socket
- IO多路复用程序
- 文件事件分派器
- 事件处理器（连接应答处理器、命令请求处理器、命令回复处理器）

### 线程和进程概念
#### 进程
进程是程序的一次执行过程，是系统运行程序的基本单位，因此进程是动态的。系统运行一个程序即是一个进程从创建，运行到消亡的过程。

#### 线程
- 线程是一个比进程更小的执行单位
- 一个进程在其执行的过程中可以产生多个线程
- 与进程不同的是同类的多个线程共享进程的堆和方法区资源，但每个线程有自己的程序计数器、虚拟机栈和本地方法栈，所以系统在产生一个线程，或是在各个线程之间作切换工作时，负担要比进程小得多，也正因为如此，线程也被称为轻量级进程

### 虚拟内存讲一下（分页）
分页存储管理：
- 将用户程序的地址空间分成若干个固定大小区域，称为页，同时将内存空间分成对应大小物理块。系统中维护一个页表，通过页与物理块的对应，完成逻辑地址到物理地址（实际内存的地址，比如内存条）的映射。
- 当进程访问某个逻辑地址中数据，分页系统地址变换机构，用页号检索页表，如果页号大于或等于页表长度，则产生地址越界中断。否则将页表初始地址与页号和页表项长度乘积相加得到页表物理号的地址，获取到物理块号。再将物理块得到的地址与页内地址组合得到物理地址。
- 如果选择的页面太小，虽然可以提高内存利用率，但是每个进程使用过多页面，导致页表过长。降低页面换入换出效率。


### synchronized和Lock的区别
- 两者都是可重入锁:两者都是可重入锁。“可重入锁”概念是：自己可以再次获取自己的内部锁。比如一个线程获得了某个对象的锁，此时这个对象锁还没有释放，当其再次想要获取这个对象的锁的时候还是可以获取的，如果不可锁重入的话，就会造成死锁。同一个线程每次获取锁，锁的计数器都自增1，所以要等到锁的计数器下降为0时才能释放锁。
- Synchronized 依赖于 JVM 而 ReenTrantLock 依赖于 API:synchronized 是依赖于 JVM 实现的，前面我们也讲到了 虚拟机团队在 JDK1.6 为 synchronized 关键字进行了很多优化，但是这些优化都是在虚拟机层面实现的，并没有直接暴露给我们。ReenTrantLock 是 JDK 层面实现的（也就是 API 层面，需要 lock() 和 unlock 方法配合 try/finally 语句块来完成），所以我们可以通过查看它的源代码，来看它是如何实现的。
- ReenTrantLock 比 Synchronized 增加了一些高级功能
    - 等待可中断：过lock.lockInterruptibly()来实现这个机制。也就是说正在等待的线程可以选择放弃等待，改为处理其他事情。
    - 可实现公平锁
    - 可实现选择性通知（锁可以绑定多个条件）：线程对象可以注册在指定的Condition中，从而可以有选择性的进行线程通知，在调度线程上更加灵活。 在使用notify/notifyAll()方法进行通知时，被通知的线程是由 JVM 选择的，用ReentrantLock类结合Condition实例可以实现“选择性通知”
    - 性能已不是选择标准：在jdk1.6之前synchronized 关键字吞吐量随线程数的增加，下降得非常严重。1.6之后，synchronized 和 ReenTrantLock 的性能基本是持平了。
    
### volatile的作用
- 可序性（禁止重排）
- 可见性
- 不能保证原子性


### 长度为n的数组乱序存放着0至n-1. 现在只能进行0与其他数的交换，完成以下函数
```java
public class Solution {
    /**
     * 交换数组里n和0的位置
     * 
     * @param array
     *            数组
     * @param len
     *            数组长度
     * @param n
     *            和0交换的数
     */
    // 不要修改以下函数内容
    public void swapWithZero(int[] array, int len, int n) {
        Main.SwapWithZero(array, len, n);
    }
    // 不要修改以上函数内容


    /**
     * 通过调用swapWithZero方法来排
     * 
     * @param array
     *            存储有[0,n)的数组
     * @param len
     *            数组长度
     */
    public void sort(int[] array, int len) {
        // 完成这个函数
        for (int i = len - 1; i >=0; i--) {
            if (array[i] == i) continue;
            swapWithZero(array, len, array[i]);
            swapWithZero(array, len, i);
        }
    }
}

// 0,1,3,2
// 2,1,3,0
// 2,1,0,3
// 0,1,2,3
```

## 美团二面
### synchronized底层实现
- synchronized 同步语句块的情况
> synchronized 同步语句块的实现使用的是 monitorenter 和 monitorexit 指令，其中 monitorenter 指令指向同步代码块的开始位置，monitorexit 指令则指明同步代码块的结束位置。
> 当执行 monitorenter 指令时，线程试图获取锁也就是获取 monitor(monitor对象存在于每个Java对象的对象头中，synchronized 锁便是通过这种方式获取锁的，也是为什么Java中任意对象可以作为锁的原因) 的持有权.当计数器为0则可以成功获取，获取后将锁计数器设为1也就是加1。
> 相应的在执行 monitorexit 指令后，将锁计数器设为0，表明锁被释放。如果获取对象锁失败，那当前线程就要阻塞等待，直到锁被另外一个线程释放为止。

- synchronized 修饰方法的的情况
> synchronized 修饰的方法并没有 monitorenter 指令和 monitorexit 指令，取得代之的确实是 ACC_SYNCHRONIZED 标识，该标识指明了该方法是一个同步方法，JVM 通过该 ACC_SYNCHRONIZED 访问标志来辨别一个方法是否声明为同步方法，从而执行相应的同步调用。

#### 1.6之后的优化
> JDK1.6对锁的实现引入了大量的优化，如自旋锁、适应性自旋锁、锁消除、锁粗化、偏向锁、轻量级锁等技术来减少锁操作的开销。
  锁主要存在四中状态，依次是：「无锁状态、偏向锁状态、轻量级锁状态、重量级锁状态」，他们会随着竞争的激烈而逐渐升级。注意锁可以升级不可降级，这种策略是为了提高获得锁和释放锁的效率。

1. 偏向锁
- 引入偏向锁的目的和引入轻量级锁的目的很像，他们都是为了没有多线程竞争的前提下，减少传统的重量级锁使用操作系统互斥量产生的性能消耗。但是不同是：轻量级锁在无竞争的情况下使用 CAS 操作去代替使用互斥量。而偏向锁在无竞争的情况下会把整个同步都消除掉。
- 偏向锁的“偏”就是偏心的偏，它的意思是会偏向于第一个获得它的线程，如果在接下来的执行中，该锁没有被其他线程获取，那么持有偏向锁的线程就不需要进行同步！
- 但是对于锁竞争比较激烈的场合，偏向锁就失效了，因为这样场合极有可能每次申请锁的线程都是不相同的，因此这种场合下不应该使用偏向锁，否则会得不偿失，需要注意的是，偏向锁失败后，并不会立即膨胀为重量级锁，而是先升级为轻量级锁。
**升级过程**：
- 访问Mark Word中偏向锁的标识是否设置成1，锁标识位是否为01，确认偏向状态
- 如果为可偏向状态，则判断当前线程ID是否为偏向线程
- 如果偏向线程未当前线程，则通过cas操作竞争锁，如果竞争成功则操作Mark Word中线程ID设置为当前线程ID
- 如果cas偏向锁获取失败，则挂起当前偏向锁线程，偏向锁升级为轻量级锁。

2. 轻量级锁
- 轻量级锁不是为了代替重量级锁，它的本意是在没有多线程竞争的前提下，减少传统的重量级锁使用操作系统互斥量产生的性能消耗，因为使用轻量级锁时，不需要申请互斥量。另外，轻量级锁的加锁和解锁都用到了CAS操作。
- 如果没有竞争，轻量级锁使用 CAS 操作避免了使用互斥操作的开销。但如果存在锁竞争，除了互斥量开销外，还会额外发生CAS操作，因此在有锁竞争的情况下，轻量级锁比传统的重量级锁更慢！如果锁竞争激烈，那么轻量级将很快膨胀为重量级锁！
**升级过程**：
- 线程由偏向锁升级为轻量级锁时，会先把锁的对象头MarkWord复制一份到线程的栈帧中，建立一个名为锁记录空间（Lock Record），用于存储当前Mark Word的拷贝。
- 虚拟机使用cas操作尝试将对象的Mark Word指向Lock Record的指针，并将Lock record里的owner指针指对象的Mark Word。
- 如果cas操作成功，则该线程拥有了对象的轻量级锁。第二个线程cas自旋锁等待锁线程释放锁。
- 如果多个线程竞争锁，轻量级锁要膨胀为重量级锁，Mark Word中存储的就是指向重量级锁（互斥量）的指针。其他等待线程进入阻塞状态。

### AQS底层实现（非公平锁，公平锁）
> ReentrantLock的公平锁和非公平锁 学习AQS的时候，了解到AQS依赖于内部的FIFO同步队列来完成同步状态的管理，当前线程获取同步状态失败时，同步器会将当前线程以及等待状态等信息构造成一个Node对象并将其加入到同步队列，同时会阻塞当前线程，当同步状态释放时，会把首节点中的线程唤醒，使其再次尝试获取同步状态。

- ReentrantLock 默认采用非公平锁，除非在构造方法中传入参数 true 。
```java
//默认
public ReentrantLock() {
  sync = new NonfairSync();
}
//传入true or false
public ReentrantLock(boolean fair) {
  sync = fair ? new FairSync() : new NonfairSync();
}

```
#### 公平锁lock方法
```java
static final class FairSync extends Sync {
  final void lock() {
      acquire(1);
  }
  // AbstractQueuedSynchronizer.acquire(int arg)
  public final void acquire(int arg) {
      if (!tryAcquire(arg) &&
          acquireQueued(addWaiter(Node.EXCLUSIVE), arg))
          selfInterrupt();
  }
  protected final boolean tryAcquire(int acquires) {
      final Thread current = Thread.currentThread();
      int c = getState();
      if (c == 0) {
          // 1. 和非公平锁相比，这里多了一个判断：是否有线程在等待
          if (!hasQueuedPredecessors() &&
              compareAndSetState(0, acquires)) {
              setExclusiveOwnerThread(current);
              return true;
          }
      }
      else if (current == getExclusiveOwnerThread()) {
          int nextc = c + acquires;
          if (nextc < 0)
              throw new Error("Maximum lock count exceeded");
          setState(nextc);
          return true;
      }
      return false;
  }
}

```
> 我们可以看到，在注释1的位置，有个!hasQueuedPredecessors()条件，意思是说当前同步队列没有前驱节点（也就是没有线程在等待）时才会去compareAndSetState(0, acquires)使用CAS修改同步状态变量。所以就实现了公平锁，根据线程发出请求的顺序获取锁。

#### 非公平锁的lock方法
```java
static final class NonfairSync extends Sync {
    final void lock() {
        // 2. 和公平锁相比，这里会直接先进行一次CAS，成功就返回了
        if (compareAndSetState(0, 1))
            setExclusiveOwnerThread(Thread.currentThread());
        else
            acquire(1);
    }
    // AbstractQueuedSynchronizer.acquire(int arg)
    public final void acquire(int arg) {
        if (!tryAcquire(arg) &&
            acquireQueued(addWaiter(Node.EXCLUSIVE), arg))
            selfInterrupt();
    }
    protected final boolean tryAcquire(int acquires) {
        return nonfairTryAcquire(acquires);
    }
}
/**
 * Performs non-fair tryLock.  tryAcquire is implemented in
 * subclasses, but both need nonfair try for trylock method.
 */
final boolean nonfairTryAcquire(int acquires) {
    final Thread current = Thread.currentThread();
    int c = getState();
    if (c == 0) {
        //3.这里也是直接CAS，没有判断前面是否还有节点。
        if (compareAndSetState(0, acquires)) {
            setExclusiveOwnerThread(current);
            return true;
        }
    }
    else if (current == getExclusiveOwnerThread()) {
        int nextc = c + acquires;
        if (nextc < 0) // overflow
            throw new Error("Maximum lock count exceeded");
        setState(nextc);
        return true;
    }
    return false;
}

```
> 非公平锁的实现在刚进入lock方法时会直接使用一次CAS去尝试获取锁，不成功才会到acquire方法中，如注释2。而在nonfairTryAcquire方法中并没有判断是否有前驱节点在等待，直接CAS尝试获取锁，如注释3。由此实现了非公平锁。

#### 总结
> 非公平锁和公平锁的两处不同： 1. 非公平锁在调用 lock 后，首先就会调用 CAS 进行一次抢锁，如果这个时候恰巧锁没有被占用，那么直接就获取到锁返回了。


### Spring Ioc，AOP介绍
#### Ioc
- IoC（Inverse of Control:控制反转）是一种设计思想，就是将原本在程序中手动创建对象的控制权，交由Spring框架来管理。 IoC 在其他语言中也有应用，并非 Spring 特有。 IoC 容器是 Spring 用来实现 IoC 的载体，IoC 容器实际上就是个Map（key，value）,Map中存放的是各种对象。
- 将对象之间的相互依赖关系交给 IoC 容器来管理，并由 IoC 容器完成对象的注入。这样可以很大程度上简化应用的开发，把应用从复杂的依赖关系中解放出来。 
- IoC 容器就像是一个工厂一样，当我们需要创建一个对象的时候，只需要配置好配置文件/注解即可，完全不用考虑对象是如何被创建出来的。
推荐阅读：[https://www.zhihu.com/question/23277575/answer/169698662](https://www.zhihu.com/question/23277575/answer/169698662)

#### AOP
- AOP(Aspect-Oriented Programming:面向切面编程)能够将那些与业务无关，却为业务模块所共同调用的逻辑或责任（例如事务处理、日志管理、权限控制等）封装起来，便于减少系统的重复代码，降低模块间的耦合度，并有利于未来的可拓展性和可维护性。 
- Spring AOP就是基于动态代理的，如果要代理的对象，实现了某个接口，那么Spring AOP会使用JDK Proxy，去创建代理对象，而对于没有实现接口的对象，就无法使用 JDK Proxy 去进行代理了，这时候Spring AOP会使用Cglib ，这时候Spring AOP会使用 Cglib 生成一个被代理对象的子类来作为代理。 

### Spring用到了什么设计模式
- 工厂设计模式 : Spring使用工厂模式通过 BeanFactory、ApplicationContext 创建 bean 对象。
- 代理设计模式 : Spring AOP 功能的实现。
- 单例设计模式 : Spring 中的 Bean 默认都是单例的。
- 模板方法模式 : Spring 中 jdbcTemplate、hibernateTemplate 等以 Template 结尾的对数据库操作的类，它们就使用到了模板模式。
- 适配器模式 :Spring AOP 的增强或通知(Advice)使用到了适配器模式、spring MVC 中也是用到了适配器模式适配Controller。
- 等等......

### 单例为什么加锁，volatile什么作用
- 如果单例不加锁，在高并发情况下可能出现多个实例的情况
- volatile的有序性特性则是指禁止JVM指令重排优化。因为实例初始化的时候，并非原子性，如
```
memory =allocate();    //1. 分配对象的内存空间 
ctorInstance(memory);  //2. 初始化对象 
instance =memory;     //3. 设置instance指向刚分配的内存地址
```
上面三个指令中，步骤2依赖步骤1，但是步骤3不依赖步骤2，所以JVM可能针对他们进行指令重拍序优化，重排后的指令如下：
```
memory =allocate();    //1. 分配对象的内存空间 
instance =memory;     //3. 设置instance指向刚分配的内存地址
ctorInstance(memory);  //2. 初始化对象 
```
> 这样优化之后，内存的初始化被放到了instance分配内存地址的后面，这样的话当线程1执行步骤3这段赋值指令后，刚好有另外一个线程2进入getInstance方法判断instance不为null，这个时候线程2拿到的instance对应的内存其实还未初始化，这个时候拿去使用就会导致出错。

**所以我们在用这种方式实现单例模式时，会使用volatile关键字修饰instance变量，这是因为volatile关键字除了可以保证变量可见性之外，还具有防止指令重排序的作用。当用volatile修饰instance之后，JVM执行时就不会对上面提到的初始化指令进行重排序优化，这样也就不会出现多线程安全问题了**。

### hashmap什么时候用到了红黑树
当 HashMap 中有大量的元素都存放到同一个桶中时，这个桶下有一条长长的链表，这个时候 HashMap 就相当于一个单链表，假如单链表有 n 个元素，遍历的时间复杂度就是 O(n)，完全失去了它的优势。
针对这种情况，JDK 1.8 中引入了 红黑树（查找时间复杂度为 O(logn)）来优化这个问题。
- [参考](https://blog.csdn.net/wushiwude/article/details/75331926)

### 介绍红黑树特点（不是严格意义平衡，插入删除最多两次自旋，时间复杂度O(logn)等），为什么不用AVL树
- 每个结点要么是红的要么是黑的。（红或黑）
- 根结点是黑的。 （根黑）
- 每个叶结点（叶结点即指树尾端NIL指针或NULL结点）都是黑的。 （叶黑）
- 如果一个结点是红的，那么它的两个儿子都是黑的。 （红子黑）
- 对于任意结点而言，其到叶结点树尾端NIL指针的每条路径都包含相同数目的黑结点。（路径下黑相同）
为什么不用AVL：
- AVL树更加严格平衡,因此可以提供更快的查找效果。因此,对于查找密集型任务,使用AVL树。
- 对于插入密集型任务,请使用红黑树。'
- AVL树在每个节点存储平衡因子。这需要。但是,如果我们知道将插入树中的键总是大于零,我们可以使用键的符号位来存储红黑树的颜色信息。因此,在这种情况下,红黑树占用,因此与AVL树相比更有效和有用。
- 通常, AVL树的旋转比红黑树的旋转更难实现和调试。
- 因此，红黑树更加通用，它们在添加、删除和查找方面表现相对较好，但AVL树的查找速度更快，代价是添加/删除速度较慢。

## 美团三面
### 介绍了两个项目
略
### 看过阿里电商的项目结构吗？（没有，随便说了说我的项目怎么做的）
略
### 怎么解决超卖（答：redis + mysql乐观锁）

### 职业规划 + 想成为tech lead应该应该具备什么条件
略