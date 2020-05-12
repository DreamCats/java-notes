## 京东（成都）一面面经
- [https://www.nowcoder.com/discuss/423874](https://www.nowcoder.com/discuss/423874)

### redis平时的使用（消息队列如何实现的）
> Redis能做消息队列得益于他list对象blpop/brpop接口以及Pub/Sub(发布/订阅)的某些接口。他们都是阻塞版的，所以Redis实现消息队列有两种方式：

- 通过数据结构list来实现
- 通过pub/sub来实现

#### 通过数据结构list来实现
消息接收：
```
redis> brpop tasklist 0
“im task 01”
```
这个例子使用blpop命令会阻塞方式地从tasklist列表中取头一个数据，最后一个参数就是等待超时的时间。如果设置为0则表示无限等待。
消息发送：
```
redis> lpush tasklist ‘im task 01’
redis> lpush tasklist ‘im task 02’
```

优点：
- 能够实现持久化：采用 Master－Slave 数据复制模式。队列操作都是写操作，Master任务繁重，能让Slave分担的持久化工作，就不要Master做。RDB和AOF两种方法都用上，多重保险。
- 支持集群
- 接口使用简单

不足：
- Redis上消息只会被一个消费者消费，不会有多个订阅者消费同一个消息，简单一对一
- 生产者或者消费者崩溃后的处理机制，需要自己实现
- 生产者写入太快，消费者消费太慢，导致Redis的内存问题，处理机制需要自己实现

#### 通过pub/sub来实现
> 订阅，取消订阅和发布实现了发布/订阅消息范式，发布者不是计划发送消息给特定的订阅者。而是发布的消息分到不同的频道，不需要知道什么样的订阅者订阅。订阅者对一个或多个频道感兴趣，只需接收感兴趣的消息，不需要知道什么样的发布者发布的。
  这是一种基于非持久化的消息机制，消息发布者和订阅者必须同时在线，否则一旦消息订阅者由于各种异常情况而被迫断开连接，在其重新连接后，其离线期间的消息是无法被重新通知的（即发即弃）。

优点：
- 一个生产者能够对应多个消费者
- 支持集群
- 接口使用简单

不足：
- 首先这些消息并没有持久化机制，属于即发即弃模式。
- 由于本来就是即发即弃的消息模式，所以Redis也不需要专门制定消息的备份和恢复机制。
- Redis也没有为发布者和订阅者准备保证消息性能的任何方案，例如在大量消息同时到达Redis服务是，如果消息订阅者来不及完成消费，就可能导致消息堆积。而ActiveMQ中有专门针对这种情况的慢消息机制。

### spring boot启动的过程（run方法和bean的生命周期）
#### ConfigurableApplicationContext的run方法
```java
public ConfigurableApplicationContext run(String... args) {
        StopWatch stopWatch = new StopWatch(); // 1. 创建计时器StopWatch
        stopWatch.start();
        ConfigurableApplicationContext context = null;
        Collection<SpringBootExceptionReporter> exceptionReporters = new ArrayList();
        this.configureHeadlessProperty();
        SpringApplicationRunListeners listeners = this.getRunListeners(args); // 2. 获取SpringApplicationRunListeners并启动
        listeners.starting(); // 
 
        Collection exceptionReporters;
        try {
            ApplicationArguments applicationArguments = new DefaultApplicationArguments(args); // 创建ApplicationArguments
            ConfigurableEnvironment environment = this.prepareEnvironment(listeners, applicationArguments); // 创建并初始化ConfigurableEnvironment
            this.configureIgnoreBeanInfo(environment); // 
            Banner printedBanner = this.printBanner(environment); //  打印Banner
            context = this.createApplicationContext(); // 创建ConfigurableApplicationContext
            exceptionReporters = this.getSpringFactoriesInstances(SpringBootExceptionReporter.class, new Class[]{ConfigurableApplicationContext.class}, context);
            this.prepareContext(context, environment, listeners, applicationArguments, printedBanner);// 准备ConfigurableApplicationContext
            this.refreshContext(context); // 刷新ConfigurableApplicationContext，这个refreshContext()加载了bean，还启动了内置web容器，需要细细的去看看
            this.afterRefresh(context, applicationArguments); // 容器刷新后动作，啥都没做
            stopWatch.stop();// 计时器停止计时
            if (this.logStartupInfo) {
                (new StartupInfoLogger(this.mainApplicationClass)).logStarted(this.getApplicationLog(), stopWatch);
            }
 
            listeners.started(context);
            this.callRunners(context, applicationArguments);
        } catch (Throwable var10) {
            this.handleRunFailure(context, var10, exceptionReporters, listeners);
            throw new IllegalStateException(var10);
        }
 
        try {
            listeners.running(context);
            return context;
        } catch (Throwable var9) {
            this.handleRunFailure(context, var9, exceptionReporters, (SpringApplicationRunListeners)null);
            throw new IllegalStateException(var9);
        }
    }
```
- 创建计时器StopWatch
- 获取SpringApplicationRunListeners并启动
- 创建ApplicationArguments
- 创建并初始化ConfigurableEnvironment
- 打印Banner
- 创建ConfigurableApplicationContext
- 准备ConfigurableApplicationContext
- 刷新ConfigurableApplicationContext，**这个refreshContext()加载了bean，还启动了内置web容器，需要细细的去看看**
- 容器刷新后动作，啥都没做
- 计时器停止计时

#### refreshContext()
- **不过这里的refresh()是在`AbstractApplicationContext`抽象类上**
- **其他就不提了，关注点在onrefresh()方法上，但是个空方法，毕竟是抽象类，去找其子类继承的它**
- **debug调试可以找到ServletWebServerApplicationContext**

![](http://media.dreamcat.ink//20200203211202.png)

- `onRefresh()`->`createWebServer()`->`getWebServerFactory()`，此时已经加载了个web容器
- 可以返回刚才的`createWebServer()`，然后看`factory.getWebServer`


```java
public WebServer getWebServer(ServletContextInitializer... initializers) {
        //tomcat这位大哥出现了
		Tomcat tomcat = new Tomcat();
		File baseDir = (this.baseDirectory != null ? this.baseDirectory
				: createTempDir("tomcat"));
		tomcat.setBaseDir(baseDir.getAbsolutePath());
		Connector connector = new Connector(this.protocol);
		tomcat.getService().addConnector(connector);
		customizeConnector(connector);
		tomcat.setConnector(connector);
		tomcat.getHost().setAutoDeploy(false);
		configureEngine(tomcat.getEngine());
		for (Connector additionalConnector : this.additionalTomcatConnectors) {
			tomcat.getService().addConnector(additionalConnector);
		}
		prepareContext(tomcat.getHost(), initializers);
		return getTomcatWebServer(tomcat);
	}
```
- 内置的Tomcat就出现了
- **总结：run() 方法主要调用了spring容器启动方法扫描配置，加载bean到spring容器中；启动的内置Web容器**

#### bean生命周期

![bean周期](http://my-blog-to-use.oss-cn-beijing.aliyuncs.com/18-9-17/5496407.jpg)

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


### sql优化
#### 索引优化
- 通过创建**唯一性索引**，可以保证数据库表中每一行数据的唯一性。
- 可以大大**加快数据的检索速度**，这也是创建索引的最主要的原因。
- 帮助服务器**避免排序和临时表**。
- 将**随机IO变为顺序IO**。
- 可以加速**表和表之间的连接**，特别是在实现数据的参考完整性方面特别有意义。

#### 数据库结构优化
- **范式优化**： 比如消除冗余（节省空间。。）
- **反范式优化**：比如适当加冗余等（减少join）
- **限定数据的范围**： 务必禁止不带任何限制数据范围条件的查询语句。比如：我们当用户在查询订单历史的时候，我们可以控制在一个月的范围内。
- **读/写分离**： 经典的数据库拆分方案，主库负责写，从库负责读；
- **拆分表**：分区将数据在物理上分隔开，不同分区的数据可以制定保存在处于不同磁盘上的数据文件里。这样，当对这个表进行查询时，只需要在表分区中进行扫描，而不必进行全表扫描，明显缩短了查询时间，另外处于不同磁盘的分区也将对这个表的数据传输分散在不同的磁盘I/O，一个精心设置的分区可以将数据传输对磁盘I/O竞争均匀地分散开。对数据量大的时时表可采取此方法。可按月自动建表分区。

### 存储引擎的区别
#### InnoDB
- 是 MySQL 默认的**事务型**存储引擎，只有在需要它不支持的特性时，才考虑使用其它存储引擎。
- 实现了四个标准的隔离级别，默认级别是**可重复读**(REPEATABLE READ)。在可重复读隔离级别下，**通过多版本并发控制(MVCC)+ 间隙锁(Next-Key Locking)防止幻影读。**
- 主索引是**聚簇索引**，在索引中保存了数据，从而避免直接读取磁盘，因此对查询性能有很大的提升。
- 内部做了很多优化，包括从磁盘读取数据时采用的**可预测性读**、能够加快读操作并且自动创建的**自适应哈希索引**、能够加速插入操作的插入缓冲区等。
- 支持真正的**在线热备份**。其它存储引擎不支持在线热备份，要获取一致性视图需要停止对所有表的写入，而在读写混合场景中，停止写入可能也意味着停止读取。

#### MyISAM
- 设计简单，数据以**紧密格式存储**。对于只读数据，或者表比较小、可以容忍修复操作，则依然可以使用它。
- 提供了大量的特性，包括**压缩表**、**空间数据索引**等。
- 不支持事务
- 不支持行级锁
- 可以**手工或者自动执行检查和修复操作**，但是和事务恢复以及崩溃恢复不同，**可能导致一些数据丢失，而且修复操作是非常慢的**。

### HashSet有用过吗
> HashSet中不允许有重复元素，这是因为HashSet是基于HashMap实现的，HashSet中的元素都存放在HashMap的key上面，而value中的值都是统一的一个private static final Object PRESENT = new Object();。
> HashSet跟HashMap一样，都是一个存放链表的数组。

### 谈一下并发跟并行的区别

#### **并发：同一时间段，多个任务都在执行 (单位时间内不一定同时执行)；**
  
#### **并行：单位时间内，多个任务同时执行。**

### 谈一下Java中的多态
> 所谓多态就是指程序中定义的**引用变量所指向的具体类型**和**通过该引用变量发出的方法**调用在编程时**并不确定**，而是在程序**运行期间才确定**，即一个引用变量到底会指向哪个类的实例对象，该引用变量发出的方法调用到底是哪个类中实现的方法，必须在由程序运行期间才能决定。

在Java中有两种形式可以实现多态：继承（多个子类对同一方法的重写）和接口（实现接口并覆盖接口中同一方法）。

### 重写和重载的区别
#### 重载
> 发生在同一个类中，方法名必须相同，参数类型不同、个数不同、顺序不同，方法返回值和访问修饰符可以不同。

#### 重写
> 重写是子类对父类的允许访问的方法的实现过程进行重新编写,发生在子类中，方法名、参数列表必须相同，返回值范围小于等于父类，抛出的异常范围小于等于父类，访问修饰符范围大于等于父类。另外，如果父类方法访问修饰符为 private 则子类就不能重写该方法。**也就是说方法提供的行为改变，而方法的外貌并没有改变。**

### xss
> XSS攻击通常指的是通过利用网页开发时留下的漏洞，通过巧妙的方法注入恶意指令代码到网页，使用户加载并执行攻击者恶意制造的网页程序。 这些恶意网页程序通常是JavaScript，但实际上也可以包括Java、 VBScript、ActiveX、 Flash 或者甚至是普通的HTML。

### token的过程
#### 登录
- 第一次认证：第一次登陆，用户在浏览器输入用户名和密码，提交后到达服务端的登录处理Controller层。
- Controller调用服务，进行用户登陆的用户名和密码的验证，如果认证通过，Controller调用用户信息服务，获取用户信息（包括完整的用户信息以及权限信息）。
- 返回用户登录信息后，Controller从配置文件中获取生成的Token秘钥信息，进行Token的生成。
- 生成Token的过程中可以调用第三方的JWT Lib生成签名后的JWT数据。
- 完成JWT数据后，将其设置到Cookie对象中，并重定向到首页，完成登录过程。

#### 请求认证
> 基于Token的认证机制，在每一次请求时，都会带上完成签名的Token信息，这个Token信息可能在Cookie中，也可能在HTTP的Authorization头中。

- 客户端（浏览器或者APP）通过 GET 或者 POST 请求访问资源（页面或者调用API）。
- 认证服务作为一个中间件儿（Middleware HOOK ），对请求进行拦截，首先在Cookie中查找Token信息，如果没有找到在HTTP的Authorization Head中查找。
- 如果找到Token信息，则根据配置文件中的签名加密秘钥，调用JWT Lib的对Token信息进行解密和解码操作。
- 完成解码并验证签名通过后，对Token中的exp、nbf、aud等信息进行验证。
- 全部通过后，通过获取的用户的角色权限信息，进行对请求资源的权限逻辑判断。
- 如果权限逻辑判断通过，则通过Response对象返回，否则返回 HTTP 401.

[参考](https://www.jianshu.com/p/6c02a490785f)