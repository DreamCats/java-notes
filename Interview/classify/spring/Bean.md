
## bean的作用域
- singleton 作用域：表示在 Spring 容器中只有一个 Bean 实例，以单例的形式存在，是默认的 Bean 作用域。
- prototype 作用域：原型作用域，每次调用 Bean 时都会创建一个新实例，也就是说每次调用 getBean() 方法时，相当于执行了 new Bean()。
- request 作用域：每次 Http 请求时都会创建一个新的 Bean，该作用域仅适应于 WebApplicationContext 环境。
- session 作用域：同一个 Http Session 共享一个 Bean 对象，不同的 Session 拥有不同的 Bean 对象，仅适用于 WebApplicationContext 环境。
- application 作用域：全局的 Web 作用域，类似于 Servlet 中的 Application。

## Spring的单例有线程安全问题吗
大部分时候我们并没有在系统中使用多线程，所以很少有人会关注这个问题。单例 bean 存在线程问题，主要是因为当多个线程操作同一个对象的时候，对这个对象的非静态成员变量的写操作会存在线程安全问题。

常见的有两种解决办法：

- 在Bean对象中尽量避免定义可变的成员变量（不太现实）。
- 在类中定义一个ThreadLocal成员变量，将需要的可变成员变量保存在 ThreadLocal 中（推荐的一种方式）。

## 什么是三级缓存
1. 第一级缓存：单例缓存池singletonObjects。
2. 第二级缓存：早期提前暴露的对象缓存earlySingletonObjects。（属性还没有值对象也没有被初始化）
3. 第三级缓存：singletonFactories单例对象工厂缓存。


## 创建Bean的整个过程
1. 首先finishBeanFactoryInitialization->preInstantiateSingletons->getBean->doGetBean;
2. 在doGetBean中，transformedBeanName:主要负责判断一下有木有别名；getSingleton：从一级缓存singletonObjects拿bean，在getSingleton方法中，有一个判断条件就是isSingletonCurrentlyInCreation，判断为false，因为他是第一次进来，并且还没有正在创建该bean；dependsOn：依赖，暂时先不说他。
3. 再来一次getSingleton：再一次的从singketonObjects缓存拿，依然没有的。接着有个重点beforeSingletonCreation：它把bean添加到临时的singletonsCurrentlyInCreation，这就意味着，下次再碰见它，那可就是true了。接着singletonFactory.getObject()，这里getObject调用的是传递的接口createBean方法。
4. 在createBean方法中：有个doCreateBean->createBeanInstance方法：它就是直接实例化，实际上构造器有反应了（区分JVM创建对象和Spring创建对象），但是没有赋值（初始化）；earlySingletonExposure：提前暴漏该bean。但要知道三个变量，为什么他是true：isSingleton()，是否单例，那肯定是哦；（这里解释了这里是单例才能提前曝漏，意味着才能存三级缓存）allowCircularReferences，默认变量为true，写死了；isSingletonCurrentlyInCreation，这里可就为true了，因为步骤3，已经将它设置为true了。那么会进来这个方法：addSingletonFactory
5. addSingletonFactory在这个方法中：将该bean放入到三级缓存singletonFactories中。（解决循环依赖）
6. 接下来，就是它了，populateBean：实际上就是属性赋值。（如果这里要有A依赖B，又发现三级缓存中没有B，那么它就会再次执行一次（递归开始）getBean->doGetBean->createBeanInstance(把B给实例化一下)，同样的道理，这里会将B也会放入三级缓存中，B开始populateBean，那么它发现B依赖A，此时三级缓存中有A(精髓，牛逼)，然后把A放到二级缓存中，同时从三级缓存中移除，接着得到A之后直接赋值，最后完成了初始化，然后来到addSingleton，将B仍到了一级缓存，同时将B从三级缓存仍出去）返回B，递归结束，得到B之后将B的赋值给A了。
7. 最后将二级缓存的A删除，仍到一级缓存中。




## bean的生命周期
![bean的生命周期-GbJvfY](https://gitee.com/dreamcater/blog-img/raw/master/uPic/bean的生命周期-GbJvfY.png)

- Bean 容器找到配置文件中 Spring Bean 的定义。
- Bean 容器利用 Java Reflection API 创建一个Bean的实例。
- 如果涉及到一些属性值 利用 `set()`方法设置一些属性值。
- 如果 Bean 实现了 `BeanNameAware` 接口，调用 `setBeanName()`方法，传入Bean的名字。
- 如果 Bean 实现了 `BeanClassLoaderAware` 接口，调用 `setBeanClassLoader()`方法，传入 `ClassLoader`对象的实例。
- 与上面的类似，如果实现了其他 `*.Aware`接口，就调用相应的方法。
- 如果有和加载这个 Bean 的 Spring 容器相关的 `BeanPostProcessor` 对象，执行`postProcessBeforeInitialization()` 方法
- 如果Bean实现了`InitializingBean`接口，执行`afterPropertiesSet()`方法。
- 如果 Bean 在配置文件中的定义包含 init-method 属性，执行指定的方法。
- 如果有和加载这个 Bean的 Spring 容器相关的 `BeanPostProcessor` 对象，执行`postProcessAfterInitialization()` 方法
- 当要销毁 Bean 的时候，如果 Bean 实现了 `DisposableBean` 接口，执行 `destroy()` 方法。
- 当要销毁 Bean 的时候，如果 Bean 在配置文件中的定义包含 destroy-method 属性，执行指定的方法。