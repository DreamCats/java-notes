## 引言

> 使用了SpringBoot框架做项目，难道就不好奇它为什么那么方便吗？简化了很多配置，那么首先就先分析Spring底层的源码和原理


## Spring源码分析

**Spring容器的创建，最重要的就是`refresh()`[创建刷新]，在该方法内部，有着12大步骤流程。**

**Spring版本：5.2.1.RELEASE**

```java
    public void refresh() throws BeansException, IllegalStateException {
        synchronized(this.startupShutdownMonitor) {
            this.prepareRefresh(); // 1.
            ConfigurableListableBeanFactory beanFactory = this.obtainFreshBeanFactory(); // 2.
            this.prepareBeanFactory(beanFactory); // 3.

            try {
                this.postProcessBeanFactory(beanFactory); // 4.
                this.invokeBeanFactoryPostProcessors(beanFactory); // 5.
                this.registerBeanPostProcessors(beanFactory); // 6.
                this.initMessageSource(); // 7.
                this.initApplicationEventMulticaster(); // 8.
                this.onRefresh(); // 9.
                this.registerListeners(); // 10.
                this.finishBeanFactoryInitialization(beanFactory); // 11.
                this.finishRefresh(); // 12.
            } catch (BeansException var9) {
                if (this.logger.isWarnEnabled()) {
                    this.logger.warn("Exception encountered during context initialization - cancelling refresh attempt: " + var9);
                }

                this.destroyBeans();
                this.cancelRefresh(var9);
                throw var9;
            } finally {
                this.resetCommonCaches();
            }
        }
    }
```



### prepareRefresh()

**刷新前的预处理如一些属性设置，环境，容器的一些早起事件等，主要执行的三个方法：**

1. `initPropertySource()`初始化一些属性设置，子类自定义个性化的属性设置方法；
2. `getEnvironment().validateRequiredProperties()`检验属性的合法等
3. `earlyApplicationEvents= new LinkedHashSet<ApplicationEvent>();`保存容器中的一些早起的事件；

### obtainFreshBeanFactory()

**获取BeanFactory，主要也是二个方法：**

1. `refreshBeanFactory();`负责刷新创建BeanFactory
   1. 创建了一个默认的`this.beanFactory = new DefaultListableBeanFactory();`
   2. 设置id；
2. `getBeanFactory();`回刚才GenericApplicationContext创建的BeanFactory对象；

### prepareBeanFactory(beanFactory)

**BeanFactory的预准备工作（BeanFactory进行一些设置）；**

1. 设置BeanFactory的类加载器、支持表达式解析器...
2. 添加部分BeanPostProcessor【ApplicationContextAwareProcessor】
3. 设置忽略的自动装配的接口EnvironmentAware、EmbeddedValueResolverAware、xxx；
4. 注册可以解析的自动装配；我们能直接在任何组件中自动注入：BeanFactory、ResourceLoader、ApplicationEventPublisher、ApplicationContext
5. 添加BeanPostProcessor【ApplicationListenerDetector】
6. 添加编译时的AspectJ；
7. 给BeanFactory中注册一些能用的组件；environment【ConfigurableEnvironment】、systemProperties【Map<String, Object>】、systemEnvironment【Map<String, Object>

### postProcessBeanFactory(beanFactory)

**BeanFactory准备工作完成后进行的后置处理工作；**

1. 子类通过重写这个方法来在BeanFactory创建并预准备完成以后做进一步的设置

### invokeBeanFactoryPostProcessors(beanFactory)

**执行BeanFactoryPostProcessor的方法，BeanFactoryPostProcessor：BeanFactory的后置处理器。在BeanFactory标准初始化之后执行的；**

**分别是两个接口：两个接口：BeanFactoryPostProcessor、BeanDefinitionRegistryPostProcessor**

1. 先执行BeanDefinitionRegistryPostProcessor(注册bean的一堆定义信息)
   1. 获取所有的BeanDefinitionRegistryPostProcessor；
   2. 看先执行实现了PriorityOrdered优先级接口的BeanDefinitionRegistryPostProcessor、postProcessor.postProcessBeanDefinitionRegistry(registry)
   3. 在执行实现了Ordered顺序接口的BeanDefinitionRegistryPostProcessor；postProcessor.postProcessBeanDefinitionRegistry(registry)
   4. 最后执行没有实现任何优先级或者是顺序接口的BeanDefinitionRegistryPostProcessors；postProcessor.postProcessBeanDefinitionRegistry(registry)
2. 再执行BeanFactoryPostProcessor的方法
   1. 获取所有的BeanFactoryPostProcessor
   2. 看先执行实现了PriorityOrdered优先级接口的BeanFactoryPostProcessor、
      postProcessor.postProcessBeanFactory()
   3. 在执行实现了Ordered顺序接口的BeanFactoryPostProcessor；
      postProcessor.postProcessBeanFactory()
   4. 最后执行没有实现任何优先级或者是顺序接口的BeanFactoryPostProcessor；
      postProcessor.postProcessBeanFactory()

### registerBeanPostProcessors(beanFactory)

**注册BeanPostProcessor（Bean的后置处理器）【 intercept bean creation】**

**不同接口类型的BeanPostProcessor；在Bean创建前后的执行时机是不一样的：**

- BeanPostProcessor
- DestructionAwareBeanPostProcessor
- InstantiationAwareBeanPostProcessor
- SmartInstantiationAwareBeanPostProcessor
- MergedBeanDefinitionPostProcessor【internalPostProcessors】

1. 获取所有的 BeanPostProcessor;后置处理器都默认可以通过PriorityOrdered、Ordered接口来执行优先级
2. 先注册PriorityOrdered优先级接口的BeanPostProcessor；把每一个BeanPostProcessor；添加到BeanFactory中
3. 再注册Ordered接口的
4. 最后注册没有实现任何优先级接口的
5. 最终注册MergedBeanDefinitionPostProcessor；
6. 注册一个ApplicationListenerDetector；来在Bean创建完成后检查是否是ApplicationListener，如果是applicationContext.addApplicationListener((ApplicationListener<?>) bean);

### initMessageSource()

**初始化MessageSource组件（做国际化功能；消息绑定，消息解析）；**

1. 获取BeanFactory
2. 看容器中是否有id为messageSource的，类型是MessageSource的组件;MessageSource：取出国际化配置文件中的某个key的值；能按照区域信息获取；
   1. 如果有赋值给messageSource
   2. 如果没有自己创建一个DelegatingMessageSource；

3. 把创建好的MessageSource注册在容器中，以后获取国际化配置文件的值的时候，可以自动注入MessageSource；

   ```java
   beanFactory.registerSingleton(MESSAGE_SOURCE_BEAN_NAME, this.messageSource);	
   MessageSource.getMessage(String code, Object[] args, String defaultMessage, Locale locale);
   ```

### initApplicationEventMulticaster()

**初始化事件派发器；**

1. 获取BeanFactory
2. 从BeanFactory中获取applicationEventMulticaster的ApplicationEventMulticaster；
3. 如果上一步没有配置；创建一个SimpleApplicationEventMulticaster
4. 将创建的ApplicationEventMulticaster添加到BeanFactory中，以后其他组件直接自动注入

### onRefresh()

**留给子容器（子类）**

1. 子类重写这个方法，在容器刷新的时候可以自定义逻辑；

### registerListeners()

**给容器中将所有项目里面的ApplicationListener注册进来；**

1. 从容器中拿到所有的ApplicationListener
2. 将每个监听器添加到事件派发器中；`getApplicationEventMulticaster().addApplicationListenerBean(listenerBeanName);`
3. 派发之前步骤产生的事件；

### finishBeanFactoryInitialization(beanFactory)

**初始化所有剩下的单实例bean；**

1. 获取容器中的所有Bean，依次进行初始化和创建对象
2. 获取Bean的定义信息；RootBeanDefinition
3. 判断Bean是非抽象的，是单实例的，是非懒加载；
   1. 判断是否是FactoryBean；是否是实现FactoryBean接口的Bean；
   2. 不是工厂Bean。利用getBean(beanName);创建对象
      1. `getBean(beanName)；`-->` ioc.getBean();`
      2. `doGetBean(name, null, null, false);`
      3. 先获取缓存中保存的单实例Bean。如果能获取到说明这个Bean之前被创建过（所有创建过的单实例Bean都会被缓存起来）`private final Map<String, Object> singletonObjects = new ConcurrentHashMap<String, Object>(256);`
      4. 缓存中获取不到，开始Bean的创建对象流程；
      5. 标记当前bean已经被创建
      6. 获取Bean的定义信息；
      7. 【获取当前Bean依赖的其他Bean;如果有按照getBean()把依赖的Bean先创建出来；】
      8. 启动单实例Bean的创建流程；
         1. `createBean(beanName, mbd, args);`
         2. `Object bean = resolveBeforeInstantiation(beanName, mbdToUse);`让BeanPostProcessor先拦截返回代理对象；【InstantiationAwareBeanPostProcessor】：提前执行；先触发：postProcessBeforeInstantiation()；如果有返回值：触发postProcessAfterInitialization()；
         3. 如果前面的InstantiationAwareBeanPostProcessor没有返回代理对象；调用4）
         4. `Object beanInstance = doCreateBean(beanName, mbdToUse, args);`创建Bean
            1. 【创建Bean实例】；`createBeanInstance(beanName, mbd, args);`利用工厂方法或者对象的构造器创建出Bean实例；
            2. `applyMergedBeanDefinitionPostProcessors(mbd, beanType, beanName);`调用`MergedBeanDefinitionPostProcessor的postProcessMergedBeanDefinition(mbd, beanType, beanName);`
            3. 【Bean属性赋值】`populateBean(beanName, mbd, instanceWrapper);`赋值之前：
               1. 拿到InstantiationAwareBeanPostProcessor后置处理器；postProcessAfterInstantiation();
               2. 拿到InstantiationAwareBeanPostProcessor后置处理器；postProcessPropertyValues();
               3. 应用Bean属性的值；为属性利用setter方法等进行赋值；`applyPropertyValues(beanName, mbd, bw, pvs);`
            4. 【Bean初始化】`initializeBean(beanName, exposedObject, mbd);`
               1. 【执行Aware接口方法】`invokeAwareMethods(beanName, bean);`执行xxxAware接口的方法		 		BeanNameAware\BeanClassLoaderAware\BeanFactoryAware
               2. 【执行后置处理器初始化之前】`applyBeanPostProcessorsBeforeInitialization(wrappedBean, beanName);`
               3. 【执行初始化方法】`invokeInitMethods(beanName, wrappedBean, mbd);`
                  1. 是否是InitializingBean接口的实现；执行接口规定的初始化；
                  2. 是否自定义初始化方法；
               4. 【执行后置处理器初始化之后】applyBeanPostProcessorsAfterInitialization-->BeanPostProcessor.postProcessAfterInitialization()；
            5. 注册Bean的销毁方法；
         5. 将创建的Bean添加到缓存中singletonObjects；ioc容器就是这些Map；很多的Map里面保存了单实例Bean，环境信息。。。。；
4. 所有Bean都利用getBean创建完成以后；检查所有的Bean是否是SmartInitializingSingleton接口的；如果是；就执行afterSingletonsInstantiated()；

### finishRefresh()

**完成BeanFactory的初始化创建工作；IOC容器就创建完成；**

1. initLifecycleProcessor();初始化和生命周期有关的后置处理器；LifecycleProcessor
   1. 默认从容器中找是否有lifecycleProcessor的组件【LifecycleProcessor】；如果没有`new DefaultLifecycleProcessor();`加入到容器；
   2. 写一个LifecycleProcessor的实现类，可以在BeanFactory-->
      void onRefresh();
      void onClose();	
2. getLifecycleProcessor().onRefresh();拿到前面定义的生命周期处理器（BeanFactory）；回调onRefresh()；
3. `publishEvent(new ContextRefreshedEvent(this));`发布容器刷新完成事件；
4. `liveBeansView.registerApplicationContext(this);`

### Spring源码总结

1. Spring容器在启动的时候，先会保存所有注册进来的Bean的定义信息；
    1. xml注册bean；<bean>
    2. 注解注册Bean；@Service、@Component、@Bean、xxx
 2. Spring容器会合适的时机创建这些Bean
     1. 用到这个bean的时候；利用getBean创建bean；创建好以后保存在容器中；
     2. 统一创建剩下所有的bean的时候；finishBeanFactoryInitialization()；
 3. 后置处理器；BeanPostProcessor
     1. 每一个bean创建完成，都会使用各种后置处理器进行处理；来增强bean的功能；比如
         1. AutowiredAnnotationBeanPostProcessor:处理自动注入
         2. AnnotationAwareAspectJAutoProxyCreator:来做AOP功能；
         3. xxx
 4. 事件驱动模型；
     1. ApplicationListener；事件监听；
     2. ApplicationEventMulticaster；事件派发：

## SpringAOP源码分析

**实际上，Spring容器过程当中，如果开启了AOP功能，那么会创建一个后置器[AnnotationAwareAspectJAutoProxyCreator]，看到UML图就明白了它的特点了。**

![AnnotationAwareAspectJAutoProxyCreator](http://media.dreamcat.ink//20200201150935.png)

**AOP具体流程，就不赘述了，毕竟其中有很多Spring容器创建的很多步骤，直接看总结即可包括了整个流程了**

### 总结

1. @EnableAspectJAutoProxy 开启AOP功能
2. @EnableAspectJAutoProxy 会给容器中注册一个组件 AnnotationAwareAspectJAutoProxyCreator
3. AnnotationAwareAspectJAutoProxyCreator是一个后置处理器；
4. 容器的创建流程：
   1. registerBeanPostProcessors（）注册后置处理器；创建AnnotationAwareAspectJAutoProxyCreator对象（**Spring源码**）
   2. finishBeanFactoryInitialization（）初始化剩下的单实例bean（**Spring源码**）
      1. 创建业务逻辑组件和切面组件
      2. AnnotationAwareAspectJAutoProxyCreator拦截组件的创建过程
      3. 组件创建完之后，判断组件是否需要增强；是->切面的通知方法，包装成增强器（Advisor）;给业务逻辑组件创建一个代理对象（cglib）；
5. 执行目标方法：
   1. 代理对象执行目标方法
   2. CglibAopProxy.intercept()；
      1. 得到目标方法的拦截器链（增强器包装成拦截器MethodInterceptor）
      2. 利用拦截器的链式机制，依次进入每一个拦截器进行执行；
      3. 效果：
         1. 正常执行：前置通知-》目标方法-》后置通知-》返回通知
         2. 出现异常：前置通知-》目标方法-》后置通知-》异常通知