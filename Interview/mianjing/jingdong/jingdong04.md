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
如：`where username=#{username}`，如果传入的值是111,那么解析成sql时的值为`where username="111"`, 如果传入的值是id，则解析成的sql为`where username="id"`.　
- `$`将传入的数据直接显示生成在sql中。如：`where username=${username}`，如果传入的值是111,那么解析成sql时的值为`where username=111`；如果传入的值是：`drop table user;`，则解析成的sql为：`select id, username, password, role from user where username=;drop table user`;
- `#`方式能够很大程度防止sql注入，`$`方式无法防止Sql注入。
- `$`方式一般用于传入数据库对象，例如传入表名.
- 一般能用`#`的就别用`$`，若不得不使用`“${xxx}”`这样的参数，要手工地做好过滤工作，来防止sql注入攻击。
- 在MyBatis中，`“${xxx}”`这样格式的参数会直接参与SQL编译，从而不能避免注入攻击。但涉及到动态表名和列名时，只能使用`“${xxx}”`这样的参数格式。所以，这样的参数需要我们在代码中手工进行处理来防止注入。

**sql注入**：
>  **SQL注入**，大家都不陌生，是一种常见的攻击方式。**攻击者**在界面的表单信息或URL上输入一些奇怪的SQL片段（例如“or ‘1’=’1’”这样的语句），有可能入侵**参数检验不足**的应用程序。所以，在我们的应用中需要做一些工作，来防备这样的攻击方式。在一些安全性要求很高的应用中（比如银行软件），经常使用将**SQL语句**全部替换为**存储过程**这样的方式，来防止SQL注入。这当然是**一种很安全的方式**，但我们平时开发中，可能不需要这种死板的方式。

**mybatis是如何做到防止sql注入的**
 MyBatis框架作为一款半自动化的持久层框架，其SQL语句都要我们自己手动编写，这个时候当然需要防止SQL注入。其实，MyBatis的SQL是一个具有“**输入+输出**”的功能，类似于函数的结构，参考上面的两个例子。其中，parameterType表示了输入的参数类型，resultType表示了输出的参数类型。回应上文，如果我们想防止SQL注入，理所当然地要在输入参数上下功夫。上面代码中使用#的即输入参数在SQL中拼接的部分，传入参数后，打印出执行的SQL语句，会看到SQL是这样的：
```sql
select id, username, password, role from user where username=? and password=?
```
不管输入什么参数，打印出的SQL都是这样的。这是因为MyBatis启用了预编译功能，在SQL执行前，会先将上面的SQL发送给数据库进行编译；执行时，直接使用编译好的SQL，替换占位符“?”就可以了。因为SQL注入只能对编译过程起作用，所以这样的方式就很好地避免了SQL注入的问题。
 [底层实现原理]MyBatis是如何做到SQL预编译的呢？其实在框架底层，是JDBC中的PreparedStatement类在起作用，PreparedStatement是我们很熟悉的Statement的子类，它的对象包含了编译好的SQL语句。这种“准备好”的方式不仅能提高安全性，而且在多次执行同一个SQL时，能够提高效率。原因是SQL已编译好，再次执行时无需再编译。
 ```java
 //安全的，预编译了的
 Connection conn = getConn();//获得连接
 String sql = "select id, username, password, role from user where id=?"; //执行sql前会预编译号该条语句
 PreparedStatement pstmt = conn.prepareStatement(sql); 
 pstmt.setString(1, id); 
 ResultSet rs=pstmt.executeUpdate(); 
 ......
```
```java
 //不安全的，没进行预编译
 private String getNameByUserId(String userId) {
     Connection conn = getConn();//获得连接
     String sql = "select id,username,password,role from user where id=" + id;
     //当id参数为"3;drop table user;"时，执行的sql语句如下:
     //select id,username,password,role from user where id=3; drop table user;  
     PreparedStatement pstmt =  conn.prepareStatement(sql);
     ResultSet rs=pstmt.executeUpdate();
     ......
 }
```
**结论**：
- **\#{}**：相当于JDBC中的PreparedStatement
- **${}**：是输出变量的值
简单说，#{}是经过预编译的，是安全的；${}是未经过预编译的，仅仅是取变量的值，是非安全的，存在SQL注入。

### 设计模式会吗，单例设计模式？
```java
public class Singleton {
    private static volatile Singleton instance = null;

    private Singleton() { } // 私有

    public static Singleton getInstance() { // 双重校验
        //第一次判断
        if(instance == null) {
            synchronized (Singleton.class) { // 加锁
                if(instance == null) {
                    //初始化，并非原子操作
                    instance = new Singleton(); // 这一行代码展开其实分三步走
                }
            }
        }
        return instance;
    }
}
```
![Singleton](http://media.dreamcat.ink/uPic/Singleton.png)

### 分布式设计原理CAP
CAP原则又称CAP定理，指的是在一个分布式系统中：
- Consistency（一致性）：每次读操作都能保证返回的是最新数据；
- Availability（可用性）：任何一个没有发生故障的节点，会在合理的时间内返回一个正常的结果；
> 备注：在集群中一部分节点故障后，集群整体是否还能响应客户端的读写请求。（对数据更新具备高可用性），换句话就是说，任何时候，任何应用程序都可以读写数据
- Partition tolerance（分区容错性）：以当节点间出现网络分区，照样可以提供服务。

### jdk1.7和jdk1.8的变化
- Java 8允许我们给接口添加一个非抽象的方法实现，只需要使用 default关键字即可，这个特征又叫做扩展方法。
- Lambda 表达式
- 方法与构造函数引用
- 函数式接口
- Annotation 注解（多重注解）
- 新的日期时间 API
- 在Java 8中，Base64编码成为了Java类库的标准。Base64类同时还提供了对URL、MIME友好的编码器与解码器。
- Stream的使用
- Java 8引入Optional类来防止空指针异常
- 支持对数组进行并行处理，主要是parallelSort()方法
- 对HashMap、CurrentHashMap做了升级

### IO和NIO的区别
IO：
- 按照流的流向分，可以分为输入流和输出流；
- 按照操作单元划分，可以划分为字节流和字符流；
- 按照流的角色划分为节点流和处理流。

NIO：
> NIO是一种同步非阻塞的I/O模型，在Java 1.4 中引入了NIO框架，对应 java.nio 包，提供了 Channel , Selector，Buffer等抽象。
> NIO中的N可以理解为Non-blocking，不单纯是New。
> 它支持面向缓冲的，基于通道的I/O操作方法。 NIO提供了与传统BIO模型中的 Socket 和 ServerSocket 相对应的 SocketChannel 和 ServerSocketChannel 两种不同的套接字通道实现,两种通道都支持阻塞和非阻塞两种模式。
> 阻塞模式使用就像传统中的支持一样，比较简单，但是性能和可靠性都不好；非阻塞模式正好与之相反。
> 对于低负载、低并发的应用程序，可以使用同步阻塞I/O来提升开发速率和更好的维护性；
> 对于高负载、高并发的（网络）应用，应使用 NIO 的非阻塞模式来开发

## 二面
### 如何创建线程池
如上

### 线程池的参数
```java
public ThreadPoolExecutor(int corePoolSize,//线程池的核心线程数量
                          int maximumPoolSize,//线程池的最大线程数
                          long keepAliveTime,//当线程数大于核心线程数时，多余的空闲线程存活的最长时间
                          TimeUnit unit,//时间单位
                          BlockingQueue<Runnable> workQueue,//任务队列，用来储存等待执行任务的队列
                          ThreadFactory threadFactory,//线程工厂，用来创建线程，一般默认即可
                          RejectedExecutionHandler handler//拒绝策略，当提交的任务过多而不能及时处理时，我们可以定制策略来处理任务
                           )
}
```
![线程池参数](http://media.dreamcat.ink/uPic/线程池参数.png)
ThreadPoolExecutor 3 个最重要的参数：
- `corePoolSize`:核心线程数定义了最小可以同时运行的线程数量。
- `maximumPoolSize`:当队列中存放的任务达到队列容量的时候，当前可以同时运行的线程数量变为最大线程数。
- `workQueue`:当新任务来的时候会先判断当前运行的线程数量是否达到了核心线程数，如果达到的话，信任就会被从存放到队列中中。

ThreadPoolExecutor 其他常见参数：
- `keepAliveTime`:当线程池中的线程数量大于`corePoolSize`的时候，如果这时没有新的任务提交，核心线程外的线程不会立即销毁，而是会等待，直到等待的时间超过了`keepAliveTime`才会被回收销毁
- `unit`:`keepAliveTime`参数的时间单位
- `threadFactory`:executor创建新线程的时候会用到。
- `handle`:饱和策略。关于饱和策略下面单独介绍

如果当前同时运行的线程数量达到最大线程数量并且队列也已经被放满了任时，`ThreadPoolTaskExecutor`定义一些策略:
- `ThreadPoolExecutor.AbortPolicy`：抛出`RejectedExecutionException`来拒绝新任务的处理。
- `ThreadPoolExecutor.CallerRunsPolicy`：调用执行自己的线程运行任务。您不会任务请求。但是这种策略会降低对于新任务提交速度，影响程序的整体性能。另外，这个策略喜欢增加队列容量。如果您的应用程序可以承受此延迟并且你不能任务丢弃任何一个任务请求的话，你可以选择这个策略。
- `ThreadPoolExecutor.DiscardPolicy`： 不处理新任务，直接丢弃掉。
- `ThreadPoolExecutor.DiscardOldestPolicy`： 此策略将丢弃最早的未处理的任务请求。

### java有哪些锁？
#### 公平锁/非公平锁
**公平锁指多个线程按照申请锁的顺序来获取锁。非公平锁指多个线程获取锁的顺序并不是按照申请锁的顺序，有可能后申请的线程比先申请的线程优先获取锁。有可能，会造成优先级反转或者饥饿现象（很长时间都没获取到锁-非洲人...），ReentrantLock，了解一下。**

#### 可重入锁
**可重入锁又名递归锁，是指在同一个线程在外层方法获取锁的时候，在进入内层方法会自动获取锁，典型的synchronized，了解一下**
```java
synchronized void setA() throws Exception {
  	Thread.sleep(1000);
  	setB(); // 因为获取了setA()的锁，此时调用setB()将会自动获取setB()的锁，如果不自动获取的话方法B将不会执行
}
synchronized void setB() throws Exception {
  	Thread.sleep(1000);
}
```

#### 独享锁/共享锁
- 独享锁：是指该锁一次只能被一个线程所持有。
- 共享锁：是该锁可被多个线程所持有。

#### 互斥锁/读写锁
**上面讲的独享锁/共享锁就是一种广义的说法，互斥锁/读写锁就是其具体的实现**

#### 乐观锁/悲观锁
1. **乐观锁与悲观锁不是指具体的什么类型的锁，而是指看待兵法同步的角度。**
2. **悲观锁认为对于同一个人数据的并发操作，一定是会发生修改的，哪怕没有修改，也会认为修改。因此对于同一个数据的并发操作，悲观锁采取加锁的形式。悲观的认为，不加锁的并发操作一定会出现问题。**
3. **乐观锁则认为对于同一个数据的并发操作，是不会发生修改的。在更新数据的时候，会采用尝试更新，不断重新的方式更新数据。乐观的认为，不加锁的并发操作时没有事情的。**
4. **悲观锁适合写操作非常多的场景，乐观锁适合读操作非常多的场景，不加锁带来大量的性能提升。**
5. **悲观锁在Java中的使用，就是利用各种锁。乐观锁在Java中的使用，是无锁编程，常常采用的是CAS算法，典型的例子就是原子类，通过CAS自旋实现原子类操作的更新。重量级锁是悲观锁的一种，自旋锁、轻量级锁与偏向锁属于乐观锁**

#### 分段锁
1. **分段锁其实是一种锁的设计，并不是具体的一种锁，对于ConcurrentHashMap而言，其并发的实现就是通过分段锁的形式来哦实现高效的并发操作。**
2. **以ConcurrentHashMap来说一下分段锁的含义以及设计思想，ConcurrentHashMap中的分段锁称为Segment，它即类似于HashMap（JDK7与JDK8中HashMap的实现）的结构，即内部拥有一个Entry数组，数组中的每个元素又是一个链表；同时又是ReentrantLock（Segment继承了ReentrantLock）**
3. **当需要put元素的时候，并不是对整个hashmap进行加锁，而是先通过hashcode来知道他要放在那一个分段中，然后对这个分段进行加锁，所以当多线程put的时候，只要不是放在一个分段中，就实现了真正的并行的插入。但是，在统计size的时候，可就是获取hashmap全局信息的时候，就需要获取所有的分段锁才能统计。**
4. **分段锁的设计目的是细化锁的粒度，当操作不需要更新整个数组的时候，就仅仅针对数组中的一项进行加锁操作。**

#### 偏向锁/轻量级锁/重量级锁
1. **这三种锁是锁的状态，并且是针对Synchronized。在Java5通过引入锁升级的机制来实现高效Synchronized。这三种锁的状态是通过对象监视器在对象头中的字段来表明的。偏向锁是指一段同步代码一直被一个线程所访问，那么该线程会自动获取锁。降低获取锁的代价。**
2. **偏向锁的适用场景：始终只有一个线程在执行代码块，在它没有执行完释放锁之前，没有其它线程去执行同步快，在锁无竞争的情况下使用，一旦有了竞争就升级为轻量级锁，升级为轻量级锁的时候需要撤销偏向锁，撤销偏向锁的时候会导致stop the word操作；在有锁竞争时，偏向锁会多做很多额外操作，尤其是撤销偏向锁的时候会导致进入安全点，安全点会导致stw，导致性能下降，这种情况下应当禁用。**
3. **轻量级锁是指当锁是偏向锁的时候，被另一个线程锁访问，偏向锁就会升级为轻量级锁，其他线程会通过自选的形式尝试获取锁，不会阻塞，提高性能。**
4. **重量级锁是指当锁为轻量级锁的时候，另一个线程虽然是自旋，但自旋不会一直持续下去，当自旋一定次数的时候，还没有获取到锁，就会进入阻塞，该锁膨胀为重量级锁。重量级锁会让其他申请的线程进入阻塞，性能降低。**

#### 自旋锁
1. **在Java中，自旋锁是指尝试获取锁的线程不会立即阻塞，而是采用循环的方式去尝试获取锁，这样的好处是减少线程上下文切换的消耗，缺点是循环会消耗CPU。**
2. **自旋锁原理非常简单，如果持有锁的线程能在很短时间内释放锁资源，那么那些等待竞争锁的线程就不需要做内核态和用户态之间的切换进入阻塞挂起状态，它们只需要等一等（自旋），等持有锁的线程释放锁后即可立即获取锁，这样就避免用户线程和内核的切换的消耗。**
3. **自旋锁尽可能的减少线程的阻塞，适用于锁的竞争不激烈，且占用锁时间非常短的代码来说性能能大幅度的提升，因为自旋的消耗会小于线程阻塞挂起再唤醒的操作的消耗。**
4. **但是如果锁的竞争激烈，或者持有锁的线程需要长时间占用锁执行同步块，这时候就不适用使用自旋锁了，因为自旋锁在获取锁前一直都是占用cpu做无用功，同时有大量线程在竞争一个锁，会导致获取锁的时间很长，线程自旋的消耗大于线程阻塞挂起操作的消耗，其它需要cpu的线程又不能获取到cpu，造成cpu的浪费。**

#### 总结
Java锁机制可归为Sychornized锁和Lock锁两类。Synchronized是基于JVM来保证数据同步的，而Lock则是硬件层面，依赖特殊的CPU指令来实现数据同步的。
- Synchronized是一个非公平、悲观、独享、互斥、可重入的重量级锁。
- ReentrantLock是一个默认非公平但可实现公平的、悲观、独享、互斥、可重入、重量级锁。
- ReentrantReadWriteLock是一个默认非公平但可实现公平的、悲观、写独享、读共享、读写、可重入、重量级锁。

### CAS的有哪些实现？
> **我们在读Concurrent包下的类的源码时，发现无论是**ReenterLock内部的AQS，还是各种Atomic开头的原子类，内部都应用到了`CAS`

#### 涉及一下底层
```java
public class Test {

    public AtomicInteger i;

    public void add() {
        i.getAndIncrement();
    }
}
```

**我们来看`getAndIncrement`的内部**：
```java
public final int getAndIncrement() {
    return unsafe.getAndAddInt(this, valueOffset, 1);
}
```

**再深入到`getAndAddInt`()**:

```java
public final int getAndAddInt(Object var1, long var2, int var4) {
    int var5;
    do {
        var5 = this.getIntVolatile(var1, var2);
    } while(!this.compareAndSwapInt(var1, var2, var5, var5 + var4));

    return var5;
}
```
**现在重点来了，`compareAndSwapInt（var1, var2, var5, var5 + var4）`其实换成`compareAndSwapInt（obj, offset, expect, update）`比较清楚，意思就是如果`obj`内的`value`和`expect`相等，就证明没有其他线程改变过这个变量，那么就更新它为`update`，如果这一步的`CAS`没有成功，那就采用自旋的方式继续进行`CAS`操作，取出乍一看这也是两个步骤了啊，其实在`JNI`里是借助于一个`CPU`指令完成的。所以还是原子操作。**

#### CAS底层
```
UNSAFE_ENTRY(jboolean, Unsafe_CompareAndSwapInt(JNIEnv *env, jobject unsafe, jobject obj, jlong offset, jint e, jint x))
  UnsafeWrapper("Unsafe_CompareAndSwapInt");
  oop p = JNIHandles::resolve(obj);
  jint* addr = (jint *) index_oop_from_field_offset_long(p, offset);
  return (jint)(Atomic::cmpxchg(x, addr, e)) == e;
UNSAFE_END
```
**p是取出的对象，addr是p中offset处的地址，最后调用了`Atomic::cmpxchg(x, addr, e)`, 其中参数x是即将更新的值，参数e是原内存的值。代码中能看到cmpxchg有基于各个平台的实现。**

#### ABA问题
描述: 第一个线程取到了变量 x 的值 A，然后巴拉巴拉干别的事，总之就是只拿到了变量 x 的值 A。这段时间内第二个线程也取到了变量 x 的值 A，然后把变量 x 的值改为 B，然后巴拉巴拉干别的事，最后又把变量 x 的值变为 A （相当于还原了）。在这之后第一个线程终于进行了变量 x 的操作，但是此时变量 x 的值还是 A，所以 compareAndSet 操作是成功。

**目前在JDK的atomic包里提供了一个类`AtomicStampedReference`来解决ABA问题。**

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
### 分布式CAP了解吗
一面有

### JVM内存分配
一面有

### full GC的原因？
1. System.gc()方法的调用
2. 老年代不足
3. 永久代不足
4. concurrent mode failure
> concurrent mode failure是在执行CMS GC的过程中同时有对象要放入老年代，而此时老年代空间不足造成的（有时候“空间不足”是CMS GC时当前的浮动垃圾过多导致暂时性的空间不足触发Full GC）。
5. promotion failed
> minor gc时年轻代的存活区空间不足而晋升老年代，老年代又空间不足而触发full gc。

6. 统计得到的Minor GC晋升到旧生代的平均大小大于老年代的剩余空间

### 堆内存的容量？
[https://zhuanlan.zhihu.com/p/66209174](https://zhuanlan.zhihu.com/p/66209174)

### 访问一个网址的过程？DNS解析过程
访问一个网址的过程：
- 根据域名，进行DNS域名解析；
- 拿到解析的IP地址，建立TCP连接；
- 向IP地址，发送HTTP请求；
- 服务器处理请求；
- 返回响应结果；
- 关闭TCP连接；
- 浏览器解析HTML；
- 浏览器布局渲染；

DNS解析过程：
![DNS解析过程](http://media.dreamcat.ink/uPic/DNS解析过程.png)
- 请求一旦发起，若是chrome浏览器，先在浏览器找之前**有没有缓存过的域名所对应的ip地址**，有的话，直接跳过dns解析了，若是没有，就会**找硬盘的hosts文件**，看看有没有，有的话，直接找到hosts文件里面的ip
- 如果本地的hosts文件没有能的到对应的ip地址，浏览器会发出一个**dns请求到本地dns服务器**，**本地dns服务器一般都是你的网络接入服务器商提供**，比如中国电信，中国移动等。
- 查询你输入的网址的DNS请求到达本地DNS服务器之后，**本地DNS服务器会首先查询它的缓存记录**，如果缓存中有此条记录，就可以直接返回结果，此过程是**递归的方式进行查询**。如果没有，本地DNS服务器还要向**DNS根服务器**进行查询。
- 本地DNS服务器继续向域服务器发出请求，在这个例子中，请求的对象是.com域服务器。.com域服务器收到请求之后，也不会直接返回域名和IP地址的对应关系，而是告诉本地DNS服务器，你的域名的解析服务器的地址。
- 最后，本地DNS服务器向**域名的解析服务器**发出请求，这时就能收到一个域名和IP地址对应关系，本地DNS服务器不仅要把IP地址返回给用户电脑，还要把这个对应关系保存在缓存中，以备下次别的用户查询时，可以直接返回结果，加快网络访问。
