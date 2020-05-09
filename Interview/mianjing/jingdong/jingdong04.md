## 京东Java春季实习
- [原文链接](https://www.nowcoder.com/discuss/413662)

## 一面

### hashcode和equals方法
#### ==
> 它的作用是判断两个对象的地址是不是相等。即，判断两个对象是不是同一个对象（基本数据类型==比较的是值，引用数据类型==比较的是内存地址）。

#### equals
> 它的作用也是判断两个对象是否相等。但它一般有两种使用情况:
- 情况1：类没有覆盖equals方法。则通过equals比较该类的两个对象时，等价于通过"=="比较这两个对象。
- 情况2：类覆盖了equals方法。一般，我们都覆盖了equals方法来比较两个对象的内容呢是否相相等；若它们的内容相等，则返回true（即，认为这两个对象相等）。
- String中的equals方法是被重写过的，因为object的equals方法是比较的对象的内存地址，而String的equals方法比较的是对象的值。
- 当创建String类型的对象时，虚拟机会在常量池中查找有没有已经存在的值和要创建的值相同的对象，如果有就把它赋给当前你引用。如果没有就在常量池中重新创建哪一个String对象。

#### hashcode
> 它的作用是获取哈希吗，也称为散列码，它实际上是返回一个int整数。这个哈希吗的作用是确定该对象在哈希表中的索引位置。hashcode定义在jdk的object.java中，这就意味着Java中的任何类都包涵hashcode函数。

#### 为什么要用hashcode
我们先以“HashSet 如何检查重复”为例子来说明为什么要有 hashCode： 
> 当你把对象加入 HashSet 时，HashSet 会先计算对象的 hashcode 值来判断对象加入的位置，同时也会与其他已经加入的对象的 hashcode 值作比较，
> 如果没有相符的hashcode，HashSet会假设对象没有重复出现。
> 但是如果发现有相同 hashcode 值的对象，这时会调用 equals()方法来检查 hashcode 相等的对象是否真的相同。
> 如果两者相同，HashSet 就不会让其加入操作成功。
> 如果不同的话，就会重新散列到其他位置。这样我们就大大减少了 equals 的次数，相应就大大提高了执行速度。

### 接口和抽象类的区别
1. 接口的方法默认是public，所有方法在接口中不能有实现（除了java8），而抽象类可以有非抽象的方法。
2. 接口中除了static、final变量，不能有其他变量，而抽象类中则不一定。
3. 一个类可以实现多个接口，但只能实现一个抽象类。接口自己本身可以通过implement关键字扩展多个接口。
4. 接口方法默认修饰符是public，抽象方法可以有public、protected和default这些修饰符（抽象方法就是为了被重写所以不能使用private关键字修饰）
5. 从设计层面来讲，抽象是对类的抽象，是一种能模板设计，而接口是对行为的抽象， 是一种行为的规范。

### 重写和重载的区别

#### 重载
> 发生在同一个类中，方法名必须相同，参数类型不同、个数不同、顺序不同，方法返回值和访问修饰符可以不同。

#### 重写
> 重写是子类对父类的允许访问的方法的实现过程进行重新编写，发生在子类中，方法名、参数列表必须相同，返回值范围小于等于父类，抛出的异常范围小于等于父类，访问修饰符范围大于等于父类。
> 另外，如果父类方法访问修饰符为private则子类就不能重写该方法。
> 也就是说方法提供的行为改变，而方法的外貌并没有改变。

### 集合类有哪些，有什么区别
> 容器主要包括 Collection 和 Map 两种，Collection 存储着对象的集合，而 Map 存储着键值对（两个对象）的映射表。

#### Set
- TreeSet
> 基于红黑树实现，支持有序性操作，例如根据一个范围查找元素的操作。但是查找效率不如HashSet，HashSet查找的时间复杂为O(1)，TreeSet则为O(logN)。

- HashSet
> 基于哈希表实现，支持快速查找，但不支持有序性操作。并且失去了元素的插入顺序信息，也就是说使用Iterator遍历HashSet得到的结构是不确定的。

- LinkedHashSet
> 具有HashSet的查找效率，且内部使用双向链表维护元素的插入顺序。

#### List
- ArrayList
> 基于动态数组实现，支持随机访问。

- Vector
> 和ArrayList类似，但它是线程安全的。

- LinkedList
> 基于双向链表实现，只能顺序访问，但是可以快速地在链表中间插入和删除元素。不仅如此，LinkedList还可以用作栈、队列和双向队列。

#### Queue
- LinkedList
> 可以用来实现双向队列。

- PriorityQueue
> 基于堆结构实现，可以用它实现优先队列。

#### Map
- TreeMap
> 基于红黑树实现

- HashMap
> 基于哈希表实现

- HashTable
    - 和 HashMap 类似，但它是线程安全的，这意味着同一时刻多个线程可以同时写入 HashTable 并且不会导致数据不一致。
    - 它是遗留类，不应该去使用它。
    - 现在可以使用 ConcurrentHashMap 来支持线程安全，并且 ConcurrentHashMap 的效率会更高，因为 ConcurrentHashMap 引入了分段锁(1.8前)。

- LinkedHashMap
> 使用双向链表来维护元素的顺序，顺序为插入顺序或者最近最少使用（LRU）顺序。

### 如何实现多线程
- 继承Thread类创建线程
```java
public class ThreadTest extends Thread{
  //重写run()方法，run()方法的方法体是线程执行体
  public void run(){
      for(int i=0;i<5;i++){
          //使用线程的getName()方法可以直接获取当前线程的名称
          System.out.println(this.getName() + "   " + i);
      }
  }

  public static void main(String[] args){
      //输出Java程序运行时默认运行的主线程名称
      System.out.println(Thread.currentThread().getName());
      //创建第一个线程并开始执行
      new ThreadTest().start();
      //创建第二个线程并开始执行
      new ThreadTest().start();
  }
}
```
- 实现Runnable接口创建线程
```java
public class ThreadTest implements Runnable{
  private int i;
  //重写run()方法，run()方法的方法体是线程执行体
  public void run(){
      //实现Runnable接口时，只能使用如下方法获取线程名
      System.out.println(Thread.currentThread().getName() + "   " + i);
      i++;
  }

  public static void main(String[] args){
      ThreadTest tt = new ThreadTest();
      //创建第一个线程并开始执行
      //输出   新线程1   0
      new Thread(tt,"新线程1").start();
      //创建第二个线程并开始执行
      //输出   新线程2   1
      new Thread(tt,"新线程2").start();
      //使用Lambda表达式创建Runnable对象
      new Thread(()->{
          System.out.print("hello");
          System.out.println("'world");
      }).start();
  }
}
```
- 使用Callable和Future创建线程
```java
public class ThreadTest {
  public static void main(String[] args){
      //使用FutureTask来包装Callable对象
      //使用Lambda表达式来创建Callable<Integer>对象
      FutureTask<Integer> task = new FutureTask<>(()->{
          System.out.println(Thread.currentThread().getName() + "   " + "开始执行任务！");
          return 0;
      });
      //实质还是以Callable对象来创建并启动线程
      //输出 新线程   开始执行任务！
      new Thread(task,"新线程").start();
      try{
          //获取线程的返回值
          //输出 0
          System.out.print(task.get());
      }catch (Exception ex){
          ex.printStackTrace();
      }

  }
}
```

- 使用线程池例如用Executor框架
```java
public class ThreadTest {
  public static void main(String[] args){
      ExecutorService executorService = Executors.newFixedThreadPool(1);
      executorService.submit(()->{
          System.out.println(Thread.currentThread().getName());
      });
  }
}
```

### JVM内存分布
#### 1.8之前
![JVM内存模型-1.8之前](http://media.dreamcat.ink/uPic/JVM内存模型-1.8之前.png)

#### 1.8及之后
![JVM内存模型-1.8](http://media.dreamcat.ink/uPic/JVM内存模型-1.8.png)

#### 大概总结
线程私有：  
- 程序计数器
- 虚拟机栈
- 本地方法栈

线程共享：
- 堆
- 直接内存

##### 程序计数器
- 字节码解释器通过改变程序计数器来一次读取命令，从而实现代码的流程控制，如：顺序执行、选择、循环、异常处理。
- 在多线程的情况下，程序计数器用于记录当前线程执行的位置，从而当线程切换回来的时候能够知道线程上次运行到哪
> 注意：程序计数器是唯一一个不会出现OutOfMemoryError的内存区域，它的生命周期随着线程的创建而创建，随着线程的结束而死亡。

##### Java虚拟机栈
> 与程序计数器一样，Java虚拟机栈也是线程私有的，它的生命周期和线程相同，描述的Java方法执行的内存模型，每次方法调用的数据都是通过栈传递的。

Java内存可以粗糙的区分为堆内存和栈内存，其中栈就是现在说的虚拟机栈，或者说是虚拟机栈中的局部变量表部分：
- 局部变量表
- 操作数栈：
    - 8大基本类型
    - 对象引用：可能是一个指向对象其实地址的引用指针，也可能是指向一个代表对象的句柄或其他与此对象相关的位置
- 动态链接
- 方法出口

Java虚拟机栈会出现两种异常：`StackOverFlowError`和`OutOfMemoryError`。
- `StackOverFlowError`：若Java虚拟机栈的内存大小不允许动态扩展，那么当线程请求栈的深度超过当前Java虚拟机栈的祖达深度的时候，就抛出`StackOverFlowError`异常。
- `OutOfMemoryError`：若Java虚拟机栈的内存大小允许动态扩展，且线程请求栈时内存用完了，无法再动态扩展了，此时抛出`OutOfMemoryError`异常。

##### 本地方法栈
> 和虚拟机栈所发挥的作用非常相似，区别是： 虚拟机栈为虚拟机执行 Java 方法 （也就是字节码）服务，而本地方法栈则为虚拟机使用到的 Native 方法服务。 其他和Java虚拟机差不多的

##### 堆
> Java 虚拟机所管理的内存中最大的一块，Java 堆是所有线程共享的一块内存区域，在虚拟机启动时创建。此内存区域的唯一目的就是存放对象实例，几乎所有的对象实例以及数组都在这里分配内存。
  Java 堆是垃圾收集器管理的主要区域，因此也被称作GC 堆（Garbage Collected Heap）.从垃圾回收的角度，由于现在收集器基本都采用分代垃圾收集算法，所以 Java 堆还可以细分为：新生代和老年代：再细致一点有：Eden 空间、From Survivor、To Survivor 空间等。进一步划分的目的是更好地回收内存，或者更快地分配内存。

![堆内存分区](http://media.dreamcat.ink/uPic/堆内存分区.png)

- 上图所示的 eden 区、s0("From") 区、s1("To") 区都属于新生代，tentired 区属于老年代。
- 大部分情况，对象都会首先在 Eden 区域分配，在一次新生代垃圾回收后，如果对象还存活，则会进入 s1("To")，并且对象的年龄还会加 1(Eden 区->Survivor 区后对象的初始年龄变为 1)，当它的年龄增加到一定程度（默认为 15 岁），就会被晋升到老年代中。
- 对象晋升到老年代的年龄阈值，可以通过参数 -XX:MaxTenuringThreshold 来设置。经过这次GC后，Eden区和"From"区已经被清空。
- 这个时候，"From"和"To"会交换他们的角色，也就是新的"To"就是上次GC前的“From”，新的"From"就是上次GC前的"To"。
- 不管怎样，都会保证名为To的Survivor区域是空的。Minor GC会一直重复这样的过程，直到“To”区被填满，"To"区被填满之后，会将所有对象移动到年老代中。

### 讲一讲Spring原理
谈Spring原来，不得不谈Ioc了
> IoC(Inverse of Control:控制反转)是一种设计思想，就是将原本在程序中手动创建对象的控制权，交由Spring框架来管理。IoC在其他语言中也有应用，并非Spring持有。
> IoC容器是Spring用来实现IoC的载体，IC容器实际上就是个Map(key，value)，Map中存放的是各种对象。

将对象之间的相互依赖关系交给 IoC 容器来管理，并由 IoC 容器完成对象的注入。
这样可以很大程度上简化应用的开发，把应用从复杂的依赖关系中解放出来。 
IoC 容器就像是一个工厂一样，当我们需要创建一个对象的时候，只需要配置好配置文件/注解即可，完全不用考虑对象是如何被创建出来的。 
在实际项目中一个 Service 类可能有几百甚至上千个类作为它的底层，假如我们需要实例化这个 Service，你可能要每次都要搞清这个 Service 所有底层类的构造函数，这可能会把人逼疯。
如果利用 IoC 的话，你只需要配置好，然后在需要的地方引用就行了，这大大增加了项目的可维护性且降低了开发难度。

#### Spring中的ioc运行机制
```java
public void refresh() throws BeansException, IllegalStateException {
   // 来个锁，不然 refresh() 还没结束，你又来个启动或销毁容器的操作，那不就乱套了嘛
   synchronized (this.startupShutdownMonitor) {

      // 准备工作，记录下容器的启动时间、标记“已启动”状态、处理配置文件中的占位符
      prepareRefresh();

      // 这步比较关键，这步完成后，配置文件就会解析成一个个 Bean 定义，注册到 BeanFactory 中，
      // 当然，这里说的 Bean 还没有初始化，只是配置信息都提取出来了，
      // 注册也只是将这些信息都保存到了注册中心(说到底核心是一个 beanName-> beanDefinition 的 map)
      ConfigurableListableBeanFactory beanFactory = obtainFreshBeanFactory();

      // 设置 BeanFactory 的类加载器，添加几个 BeanPostProcessor，手动注册几个特殊的 bean
      // 这块待会会展开说
      prepareBeanFactory(beanFactory);

      try {
         // 【这里需要知道 BeanFactoryPostProcessor 这个知识点，Bean 如果实现了此接口，
         // 那么在容器初始化以后，Spring 会负责调用里面的 postProcessBeanFactory 方法。】

         // 这里是提供给子类的扩展点，到这里的时候，所有的 Bean 都加载、注册完成了，但是都还没有初始化
         // 具体的子类可以在这步的时候添加一些特殊的 BeanFactoryPostProcessor 的实现类或做点什么事
         postProcessBeanFactory(beanFactory);
         // 调用 BeanFactoryPostProcessor 各个实现类的 postProcessBeanFactory(factory) 方法
         invokeBeanFactoryPostProcessors(beanFactory);

         // 注册 BeanPostProcessor 的实现类，注意看和 BeanFactoryPostProcessor 的区别
         // 此接口两个方法: postProcessBeforeInitialization 和 postProcessAfterInitialization
         // 两个方法分别在 Bean 初始化之前和初始化之后得到执行。注意，到这里 Bean 还没初始化
         registerBeanPostProcessors(beanFactory);

         // 初始化当前 ApplicationContext 的 MessageSource，国际化这里就不展开说了，不然没完没了了
         initMessageSource();

         // 初始化当前 ApplicationContext 的事件广播器，这里也不展开了
         initApplicationEventMulticaster();

         // 从方法名就可以知道，典型的模板方法(钩子方法)，
         // 具体的子类可以在这里初始化一些特殊的 Bean（在初始化 singleton beans 之前）
         onRefresh();

         // 注册事件监听器，监听器需要实现 ApplicationListener 接口。这也不是我们的重点，过
         registerListeners();

         // 重点，重点，重点
         // 初始化所有的 singleton beans
         //（lazy-init 的除外）
         finishBeanFactoryInitialization(beanFactory);

         // 最后，广播事件，ApplicationContext 初始化完成
         finishRefresh();
      }

      catch (BeansException ex) {
         if (logger.isWarnEnabled()) {
            logger.warn("Exception encountered during context initialization - " +
                  "cancelling refresh attempt: " + ex);
         }

         // Destroy already created singletons to avoid dangling resources.
         // 销毁已经初始化的 singleton 的 Beans，以免有些 bean 会一直占用资源
         destroyBeans();

         // Reset 'active' flag.
         cancelRefresh(ex);

         // 把异常往外抛
         throw ex;
      }

      finally {
         // Reset common introspection caches in Spring's core, since we
         // might not ever need metadata for singleton beans anymore...
         resetCommonCaches();
      }
   }
}
```
![spring-ioc](http://media.dreamcat.ink/uPic/spring-ioc.png)

大概总结：
1. Spring容器在启动的时候，先会保存所有注册进来的Bean的定义信息；
    1. xml注册bean
    2. 注解注册bean；@Service、@Component、@Bean，xxx
    3. Spring容器会在合适时机创建这些Bean
        1. 用到这个bean的创建；利用getBean创建bean；创建好以后保存在容器中。
        2. 统一创建剩下所有的bean的时候；finishBeanFactoryInitialization();
    4. 后置处理器；BeanPostProcessor
        1. 每一个bean创建完成，都会使用各种后置处理器进行处理，来增强bena的功能，比如：
            - AutowiredAnnotationBeanPostProcessor：处理自动注入
            - AnnotationAwareAspectJAutoProxyCreator：来做AOP功能；
    5. 事件驱动模型：
        - ApplicationListener：事件监听；
        - ApplicationEventMulticaster：事件派发；


### mysql的引擎，索引结构，索引有哪些类型？
#### 数据库引擎innodb与myisam的区别
InnoDB：
- 是MySQL默认的事务型存储引擎，只有在需要它不支持的特性时，才考虑使用其他存储引擎。
- 实现了四个标准的隔离级别，默认级别是可重复读。在可重复读隔离级别下，通过多版本并发控制(MVCC)+间隙锁(Next-Key Locking)防止幻影读。
- 主索引是聚簇索引，在索引中保存了数据，从而避免直接读取磁盘，因此对查询性能有很大的提升。
- 内部做了很多优化，包括从磁盘读取数据时采用的可预测性读、能够加快读操作并且自动创建的自适应哈希索引、能够加速插入操作的插入缓冲区等。
- 支持真正的在线热备份。其他存储引擎不支持在线热备份，要获取一致性视图需要停止对所有表的写入，而在读写混合场景中，停止写入可能也意味着停止读取。

MyISAM：
- 设计简单，数据以紧密格式存储。对于只读数据，或者表比较小、可以容忍修复操作，则依然可以使用它。
- 提供了大量的特性，包括压缩表、空间数据索引等。
- 不支持事务。
- 不支持行级锁，只能对整张表加锁，读取时会对需要读到的所有表加共享锁，写入时则对表加排它锁。但在表有读取操作的同时，也可以往表中插入新的记录，这被称为并发插入(CONCURRENT INSERT)。

#### 索引类型
- FULLTEXT
  即为全文索引，目前只有MyISAM引擎支持。其可以在CREATE TABLE ，ALTER TABLE ，CREATE INDEX 使用，不过目前只有 CHAR、VARCHAR ，TEXT 列上可以创建全文索引。
- HASH
  由于HASH的唯一（几乎100%的唯一）及类似键值对的形式，很适合作为索引。
  HASH索引可以一次定位，不需要像树形索引那样逐层查找,因此具有极高的效率。但是，这种高效是有条件的，即只在“=”和“in”条件下高效，对于范围查询、排序及组合索引仍然效率不高。
- BTREE
  BTREE索引就是一种将索引值按一定的算法，存入一个树形的数据结构中（二叉树），每次查询都是从树的入口root开始，依次遍历node，获取leaf。这是MySQL里默认和最常用的索引类型。
- RTREE
  RTREE在MySQL很少使用，仅支持geometry数据类型，支持该类型的存储引擎只有MyISAM、BDb、InnoDb、NDb、Archive几种。
  相对于BTREE，RTREE的优势在于范围查找。

#### B+树
B+ 树是一种树数据结构，通常用于关系型数据库（如Mysql）和操作系统的文件系统中。B+ 树的特点是能够保持数据稳定有序，其插入与修改拥有较稳定的对数时间复杂度。B+ 树元素自底向上插入，这与二叉树恰好相反。
在B树基础上，为叶子结点增加链表指针（B树+叶子有序链表），所有关键字都在叶子结点 中出现，非叶子结点作为叶子结点的索引；B+树总是到叶子结点才命中。
b+树的非叶子节点不保存数据，只保存子树的临界值（最大或者最小），所以同样大小的节点，b+树相对于b树能够有更多的分支，使得这棵树更加矮胖，查询时做的IO操作次数也更少。

### mysql 的共享锁和排它锁

#### 共享锁
> 共享锁又称为读锁，简称S锁，顾名思义，共享锁就是多个事务对于同一数据可以共享一把锁，都能访问到数据，但是只能读不能修改。

#### 排它锁
> 排他锁又称为写锁，简称X锁，顾名思义，排他锁就是不能与其他所并存，如一个事务获取了一个数据行的排他锁，其他事务就不能再获取该行的其他锁，包括共享锁和排他锁，但是获取排他锁的事务是可以对数据就行读取和修改。

### mybatis如何防止sql注入
**首先看一下下面两个sql语句的区别：**
```mysql
 <select id="selectByNameAndPassword" parameterType="java.util.Map" resultMap="BaseResultMap"
 select id, username, password, role
 from user
 where username = #{username,jdbcType=VARCHAR}
 and password = #{password,jdbcType=VARCHAR}
 </select
```
```mysql
 <select id="selectByNameAndPassword" parameterType="java.util.Map" resultMap="BaseResultMap"
 select id, username, password, role
 from user
 where username = ${username,jdbcType=VARCHAR}
 and password = ${password,jdbcType=VARCHAR}
 </select
```

**mybatis中的#和$的区别：**
- `#`将传入的数据都当成一个字符串，会对自动传入的数据加一个双引号。


