## 京东java实习面经
- [原文链接](https://www.nowcoder.com/discuss/419373)

## 一面
### redis为什么可以这么快，持久化机制
Redis性能如此高的原因，我总结了如下几点：
- 纯内存操作
- 单线程
- 高效的数据结构
- 合理的数据编码
- 其他方面的优化

在 Redis 中，常用的 5 种数据结构和应用场景如下：
- **String**：缓存、计数器、分布式锁等。
- **List**：链表、队列、微博关注人时间轴列表等。
- **Hash**：用户信息、Hash 表等。
- **Set**：去重、赞、踩、共同好友等。
- **Zset**：访问量排行榜、点击量排行榜等。

[https://zhuanlan.zhihu.com/p/57089960](https://zhuanlan.zhihu.com/p/57089960)

### redis持久化机制
Redis支持持久化，而且支持两种不同的持久化操作
- **快照**（snapshotting，RDB）
- **只追加文件**（append-only ﬁle,AOF）

#### RDB
> Redis可以通过创建快照来获得存储在内存里面的数据在某个时间点上的副本。Redis创建快照之后，可以对快照进行 备份，可以将快照复制到其他服务器从而创建具有相同数据的服务器副本（Redis主从结构，主要用来提高Redis性 能），还可以将快照留在原地以便重启服务器的时候使用。

#### AOF
> 与快照持久化相比，AOF持久化的实时性更好，因此已成为主流的持久化方案。
```
appendfsync always #每次有数据修改发生时都会写入AOF文件,这样会严重降低Redis的速度
appendfsync everysec  #每秒钟同步一次，显示地将多个写命令同步到硬盘
appendfsync no  #让操作系统决定何时进行同步
```

#### Redis4.0
> Redis 4.0 开始支持 RDB 和 AOF 的混合持久化

### spring中的ioc运行机制
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

#### 大概总结
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
            - xxx
    5. 事件驱动模型：
        - ApplicationListener：事件监听；
        - ApplicationEventMulticaster：事件派发； 

- [http://dreamcat.ink/2020/01/31/spring-springaop-yuan-ma-fen-xi/](http://dreamcat.ink/2020/01/31/spring-springaop-yuan-ma-fen-xi/)
- [https://javadoop.com/post/spring-ioc](https://javadoop.com/post/spring-ioc)

### spring中aop的原理
> AOP(Aspect-Oriented Programming:面向切面编程)能够将那些与业务无关，却为业务模块所共同调用的逻辑或责任（例如事务处理、日志管理、权限控制等）封装起来，便于减少系统的重复代码，降低模块间的耦合度，并有利于未来的可拓展性和可维护性。 
> Spring AOP就是基于动态代理的

大致流程：
1. @EnableAspectJAutoProxy 开启AOP功能
2. @EnableAspectJAutoProxy 会给容器中注册一个组件 AnnotationAwareAspectJAutoProxyCreator
3. AnnotationAwareAspectJAutoProxyCreator是一个后置处理器  
4. 容器的创建流程：
    1. registerBeanPostProcessors()注册后置处理器，创建AnnotationAwareAspectJAutoProxyCreator对象
    2. finishBeanFactoryInitialization()初始化剩下的单实例bean
        1. 创建业务逻辑组件和切面组件
        2. AnnotationAwareAspectJAutoProxyCreator拦截组件的创建过程
        3. 组件创建完之后，判断组件是否需要增强，如果是：切面的通知方法，包装成增强器(advisor)，给业务逻辑组件创建一个代理对象(cglib)
5. 执行目标方法：
    1. 代理对象执行目标方法
    2. CglibAopProxy.intercept()
        1. 得到目标方法的拦截器链（增强器包装成拦截器MethodInterceptor）
        2. 利用拦截器的链式机制，依次进入每一个拦截器进行执行
        3. 效果：
            - 正常执行：前置通知->目标方法->后置通知->返回通知
            - 出现异常：前置通知->目标方法->后置通知->异常通知

### spring中的单例模式和设计模式中的单例模式有什么区别
- 单例模式是指在一个JVM进程中仅有一个实例，而Spring单例是指一个Spring Bean容器(ApplicationContext)中仅有一个实例。
- 意味着：
    - 在一个JVM进程中（理论上，一个运行的JAVA程序就必定有自己一个独立的JVM）仅有一个实例，于是无论在程序中的何处获取实例，始终都返回同一个对象。
    - 与此相比，Spring的单例Bean是与其容器（ApplicationContext）密切相关的，所以在一个JVM进程中，如果有多个Spring容器，即使是单例bean，也一定会创建多个实例。
    
## 二面

### redis为什么这么快
略

### 介绍redis运行机制
> redis 内部使用文件事件处理器 file event handler，这个文件事件处理器是单线程的，所以 redis 才叫做单线程的模型。它采用 IO 多路复用机制同时监听多个 socket，根据 socket 上的事件来选择对应的事件处理器进行处理。

文件事件处理器的结构包含 4 个部分：
- 多个 socket
- IO多路复用程序
- 文件事件分派器
- 事件处理器（连接应答处理器、命令请求处理器、命令回复处理器）

多个socket可能会并发产生不同的操作，每个操作对应不同的文件事件，但是IO多路复用程序会监听多个socket，会将socket产生的事件放入队列中排队，事件分派器每次从队列中取出一个事件，把该事件交给对应的事件处理器进行处理。

### hashmap多线程下安全问题，如何解决
比如死循环分析：
- [https://zhuanlan.zhihu.com/p/67915754](https://zhuanlan.zhihu.com/p/67915754)

### wait（）和sleep（）区别
- 两者最主要的区别在于：**sleep方法没有释放锁，而wait方法释放了锁**。
- 两者都可以暂停线程的执行。
- wait通常被用于线程间交互/通信，sleep通常被用于暂停执行。
- wait方法被调用后，线程不会自动苏醒，需要别的线程调用同一个对象上的notify或者notifyAll方法。sleep方法执行完成后，线程会自动苏醒。或者可以使用wait(long timeout)超时后线程会自动苏醒。

### synchronized和volatile区别
synchronized的修饰范围：
- 修饰一个代码块
- 修饰一个方法
- 修饰一个类
- 修饰一个静态的方法

> 个人的理解是：因为同步关键字Synchronized不能修饰变量（不能直接使用synchronized声明一个变量），不能使变量得到共享，故引入了轻量级的Volatie

volatile:
- volatile可以修饰变量，共享变量。
- 保障了共享变量对所有线程的可见性。即可保证在线程A将其修改时，线程B可以立刻得到。
- 禁止指令重排序

### 线程池的参数定义，大小
先放一波变量定义
```java
    /**
     * 用给定的初始参数创建一个新的ThreadPoolExecutor。
     */
    public ThreadPoolExecutor(int corePoolSize,//线程池的核心线程数量
                              int maximumPoolSize,//线程池的最大线程数
                              long keepAliveTime,//当线程数大于核心线程数时，多余的空闲线程存活的最长时间
                              TimeUnit unit,//时间单位
                              BlockingQueue<Runnable> workQueue,//任务队列，用来储存等待执行任务的队列
                              ThreadFactory threadFactory,//线程工厂，用来创建线程，一般默认即可
                              RejectedExecutionHandler handler//拒绝策略，当提交的任务过多而不能及时处理时，我们可以定制策略来处理任务
                               ) {
        if (corePoolSize < 0 ||
            maximumPoolSize <= 0 ||
            maximumPoolSize < corePoolSize ||
            keepAliveTime < 0)
            throw new IllegalArgumentException();
        if (workQueue == null || threadFactory == null || handler == null)
            throw new NullPointerException();
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.workQueue = workQueue;
        this.keepAliveTime = unit.toNanos(keepAliveTime);
        this.threadFactory = threadFactory;
        this.handler = handler;
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

大小：
> 如果我们设置的线程池数量太小的话，如果同一时间有大量任务/请求需要处理，可能会导致大量的请求/任务在任务队列中排队等待执行，甚至会出现任务队列满了之后任务/请求无法处理的情况，或者大量任务堆积在任务队列导致 OOM。这样很明显是有问题的！ CPU 根本没有得到充分利用。
> 但是，如果我们设置线程数量太大，大量线程可能会同时在争取 CPU 资源，这样会导致大量的上下文切换，从而增加线程的执行时间，影响了整体执行效率。

- **CPU 密集型任务(N+1)**： 这种任务消耗的主要是 CPU 资源，可以将线程数设置为 N（CPU 核心数）+1，比 CPU 核心数多出来的一个线程是为了防止线程偶发的缺页中断，或者其它原因导致的任务暂停而带来的影响。一旦任务暂停，CPU 就会处于空闲状态，而在这种情况下多出来的一个线程就可以充分利用 CPU 的空闲时间。
- **I/O 密集型任务(2N)**： 这种任务应用起来，系统会用大部分的时间来处理 I/O 交互，而线程在处理 I/O 的时间段内不会占用 CPU 来处理，这时就可以将 CPU 交出给其它线程使用。因此在 I/O 密集型任务的应用中，我们可以多配置一些线程，具体的计算方法是 2N。

### java为何设计成单继承
可以举个例子：
> 在这里有个A类，我们又编写了两个类B类和C类，并且B类和C类分别继承了A类，并且对A类的同一个方法进行了覆盖。
> 如果此时我们再次编写了一个D类，并且D类以多继承的方式同时集成了B类和C类，那么D类也会继承B类和C类从A类中重载的方法，那么问题来了，D类也开始犯迷糊了，我到底应该哪个继承哪个类中的方法呢，
> 因为类是结构性的，这样就会造成结构上的混乱。这就是多继承的菱形继承问题。

可以实现对个接口：
> Java接口是行为性的，也就是说它只是定义某个行为的名称，而具体的行为的实现是集成接口的类实现的，
> 因此就算两个接口中定义了两个名称完全相同的方法，当某个类去集成这两个接口时，
> 类中也只会有一个相应的方法，这个方法的具体实现是这个类来进行编写的，所以并不会出现结构混乱的情况。

### 继承和组合有啥区别
继承：
> 继承是Is a 的关系，比如说Student继承Person,则说明Student is a Person。继承的优点是子类可以重写父类的方法来方便地实现对父类的扩展。

- 父类的内部细节对子类是可见的。
- 子类从父类继承的方法在编译时就确定下来了，所以无法在运行期间改变从父类继承的方法的行为。
- 子类与父类是一种高耦合，违背了面向对象思想。
- 继承关系最大的弱点是打破了封装，子类能够访问父类的实现细节，子类与父类之间紧密耦合，子类缺乏独立性，从而影响了子类的可维护性。
- 不支持动态继承。在运行时，子类无法选择不同的父类。

组合：
- 不破坏封装，整体类与局部类之间松耦合，彼此相对独立。
- 具有较好的可扩展性。
- 支持动态组合。在运行时，整体对象可以选择不同类型的局部对象。

组合是has a的关系, 继承是is a的关系
> 引用一句老话应该更能分清继承和组合的区别：组合可以被说成“我请了个老头在我家里干活” ，继承则是“我父亲在家里帮我干活"。

总结：
- 除非考虑使用多态，否则优先使用组合。
- 要实现类似”多重继承“的设计的时候，使用组合。
- 要考虑多态又要考虑实现“多重继承”的时候，使用组合+接口。


