## 引言

> SpringBoot大大简化了Spring项目的配置，虽然很舒服，但是为了面试等，还是要知道里面的细节和原理呀，再此举个HelloWorld的例子


## 声明

- 使用Idea分析，并使用Idea创建SpringBoot的工程。
- 项目结构就不一一详细说了，大家肯定都知道每个文件是干什么的。
- 这里主要是负责细看其中的原理和细节。
- **版本：2.2.4.RELEASE**

## SpringApplication实例

**先看源码：**

```java
  public SpringApplication(ResourceLoader resourceLoader, Class... primarySources) {
        this.sources = new LinkedHashSet(); // 1. 
        this.bannerMode = Mode.CONSOLE;
        this.logStartupInfo = true;
        this.addCommandLineProperties = true;
        this.headless = true;
        this.registerShutdownHook = true;
        this.additionalProfiles = new HashSet();
        this.resourceLoader = resourceLoader;
        Assert.notNull(primarySources, "PrimarySources must not be null");
        this.primarySources = new LinkedHashSet(Arrays.asList(primarySources));
        this.webApplicationType = this.deduceWebApplicationType();
        this.setInitializers(this.getSpringFactoriesInstances(ApplicationContextInitializer.class)); // 3. 
        this.setListeners(this.getSpringFactoriesInstances(ApplicationListener.class)); // 4.
        this.mainApplicationClass = this.deduceMainApplicationClass(); // 5.
    }
```

- `com.example.helloworld.HelloworldApplication`放入到Set的集合中
- 判断是否为Web环境：存在（javax.servlet.Servlet && org.springframework.web.context.ConfigurableWebApplicationContext ）类
- 创建并初始化ApplicationInitializer列表 （spring.factories）
- 创建并初始化ApplicationListener列表 （spring.factories）
- 初始化主类mainApplicatioClass  (DemoApplication)
- **总结：上面就是SpringApplication初始化的代码，new SpringApplication()没做啥事情 ，主要加载了META-INF/spring.factories 下面定义的事件监听器接口实现类**

## ConfigurableApplicationContext的run方法

**看源码，都可以找的到**

```java
public ConfigurableApplicationContext run(String... args) {
        StopWatch stopWatch = new StopWatch(); // 1.
        stopWatch.start();
        ConfigurableApplicationContext context = null;
        Collection<SpringBootExceptionReporter> exceptionReporters = new ArrayList();
        this.configureHeadlessProperty();
        SpringApplicationRunListeners listeners = this.getRunListeners(args); // 2.
        listeners.starting(); // 
 
        Collection exceptionReporters;
        try {
            ApplicationArguments applicationArguments = new DefaultApplicationArguments(args); // 
            ConfigurableEnvironment environment = this.prepareEnvironment(listeners, applicationArguments); // 
            this.configureIgnoreBeanInfo(environment); // 
            Banner printedBanner = this.printBanner(environment); // 
            context = this.createApplicationContext(); // 
            exceptionReporters = this.getSpringFactoriesInstances(SpringBootExceptionReporter.class, new Class[]{ConfigurableApplicationContext.class}, context);
            this.prepareContext(context, environment, listeners, applicationArguments, printedBanner);// 
            this.refreshContext(context); // 
            this.afterRefresh(context, applicationArguments); //
            stopWatch.stop();// 
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

## refreshContext()

**该源码中其实就是Spring源码的refresh()的源码**

- **不过这里的refresh()是在`AbstractApplicationContext`抽象类上**

- **其他就不提了，关注点在onrefresh()方法上，但是个空方法，毕竟是抽象类，去找其子类继承的它**
- **debug调试可以找到ServletWebServerApplicationContext**

## ServletWebServerApplicationContext

**先看个类图吧，很吊**

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

## @SpringBootApplication

**主要是三个注解**

- @SpringBootConfiguration:允许在上下文中注册额外的bean或导入其他配置类。
-  @EnableAutoConfiguration:启用 SpringBoot 的自动配置机制
- @ComponentScan: 扫描常用的注解


