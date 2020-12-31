
## IoC
IoC（Inverse of Control:控制反转）是一种**设计思想**，就是 **将原本在程序中手动创建对象的控制权，交由Spring框架来管理。IoC 容器是 Spring 用来实现 IoC 的载体， IoC 容器实际上就是个Map（key，value）,Map 中存放的是各种对象。**

将对象之间的相互依赖关系交给 IoC 容器来管理，并由 IoC 容器完成对象的注入。这样可以很大程度上简化应用的开发，把应用从复杂的依赖关系中解放出来。 **IoC 容器就像是一个工厂一样，当我们需要创建一个对象的时候，只需要配置好配置文件/注解即可，完全不用考虑对象是如何被创建出来的。** 

### 初始化流程

- Resource资源定位：这个Resouce指的是BeanDefinition的资源定位。这个过程就是容器找数据的过程，就像水桶装水需要先找到水一样。
- BeanDefinition的载入和解析：这个载入过程是把用户定义好的Bean表示成Ioc容器内部的数据结构，而这个容器内部的数据结构就是BeanDefition。
- BeanDefinition注册
- prepareRefresh()：预备一下， 标记启动时间，上下文环境，我要的材料（beanDefinition）准备好了嘛？
- obtainFreshBeanFactory()：
    - 如果已经有了BeanFactory就销毁它里面的单例Bean并关闭这个BeanFactory。
    - 创建一个新的BeanFactory。
    - 对这个BeanFactory进行定制（customize),如allowBeanDefinitionOverriding等参数
    - 转载BeanDefinitions(读取配置文件，将xml转换成对应得BeanDefinition)
    - 检查是否同时启动了两个BeanFactory。
- prepareBeanFactory(beanFactory)：设置beanFactory的类加载器，材料（BeanDefinition）解析器等
- postProcessBeanFactory(beanFactory)：
    - 设置beanFactory的后置处理器
    - 具体的子类可以在这步的时候添加一些特殊的 BeanFactoryPostProcessor 的实现类或做点什么事
- invokeBeanFactoryPostProcessors(beanFactory)：
    - 调用beanFactory的后置处理器（BeanDefinitionRegisterPostProcessor和BeanFactoryPostProcessor）
    - 调用 BeanFactoryPostProcessor 各个实现类的 postProcessBeanFactory(factory) 方法
- registerBeanPostProcessors(beanFactory)：
    - 注册 BeanPostProcessor 的实现类（bean的后置处理器）
    - 此接口两个方法: postProcessBeforeInitialization 和 postProcessAfterInitialization 两个方法分别在 Bean 初始化之前和初始化之后得到执行。注意，到这里 Bean 还没初始化
- initMessageSource()：对上下文中的消息源进行初始化
- initApplicationEventMulticaster()：初始化上下文的事件广播器
- onRefresh()：- 模版方法，具体的子类可以在这里初始化一些特殊的 Bean（在初始化 singleton beans 之前）
- registerListeners()：注册事件监听器
- finishBeanFactoryInitialization(beanFactory)：初始化所有的 singleton beans
- finishRefresh()：最后，广播事件，ApplicationContext 初始化完成

## AOP

AOP(Aspect-Oriented Programming:面向切面编程)能够将那些与业务无关，**却为业务模块所共同调用的逻辑或责任（例如事务处理、日志管理、权限控制等）封装起来**，便于**减少系统的重复代码**，**降低模块间的耦合度**，并**有利于未来的可拓展性和可维护性**。

- **Spring AOP就是基于动态代理的**
- 如果要代理的对象，实现了某个接口，那么Spring AOP会使用**JDK Proxy**，
- 而对于没有实现接口的对象，这时候Spring AOP会使用 **Cglib** 生成一个被代理对象的子类来作为代理。


![](https://user-gold-cdn.xitu.io/2018/9/14/165d631e56799a5c?imageView2/0/w/1280/h/960/format/webp/ignore-error/1)

### 初始化流程
registerAspectJAnnotationAutoProxyCreatorIfNecessary

- 第一句，注册一个AnnotationAwareAspectJAutoProxyCreator（称它为自动代理器），这个Creator是AOP的操作核心，也是扫描Bean，代理Bean的操作所在。 
- 第二句，解析配置元素，决定代理的模式。其中有JDK动态代理，还有CGLIB代理，这部分后续会再细讲。
- 第三句，作为系统组件，把Creator这个Bean，放到Spring容器中。让Spring实例化，启动这个Creator。

总结：
- Spring加载自动代理器AnnotationAwareAspectJAutoProxyCreator，当作一个系统组件。
- 当一个bean加载到Spring中时，会触发自动代理器中的bean后置处理，然后会先扫描bean中所有的Advisor
- 然后用这些Adviosr和其他参数构建ProxyFactory
- ProxyFactory会根据配置和目标对象的类型寻找代理的方式（JDK动态代理或CGLIG代理）
- 然后代理出来的对象放回context中，完成Spring AOP代理
