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
@Override
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

