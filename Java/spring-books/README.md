# Spring书籍、源码分析与学习
## 引言
> 个人Spring学习之旅，📝笔记。

## 参考笔记
- [b站视频入口](https://www.bilibili.com/video/av32102436?p=1)
- [代码资料](https://gitee.com/adanzzz/spring_source_parsing_data)
## 思维导图
- [Spring注解驱动开发](https://www.processon.com/view/link/5e30213ae4b096de64c8e9bf)


## 目录说明
### 组件注册
- [xml注册bean方式](docs/1.xml注解bean方式.md)
- [xml注册包扫描方式](docs/2.xml注册包扫描方式.md)
- [注解注册bean方式](docs/3.注解注册bean方式.md)
- [注解注册包扫描方式](docs/4.注解注册包扫描方式.md)
- [@Filter的使用](docs/5.@Filter的使用.md)
- [@Scope的使用](docs/6.@Scope的使用.md)
- [@Lazy的使用](docs/7.@Lazy的使用.md)
- [@Conditional的使用](docs/8.@Conditional的使用.md)
- [@Import的使用](docs/9.@Import的使用.md)
### 生命周期
- [bean的生命周期](docs/10.bean的生命周期.md)
### 属性赋值
- [@Value的使用](docs/11.@Value的使用.md)
- [@PropertySource的使用](docs/12.@PropertySource的使用.md)
### 自动装配
- [@Autowired的使用](docs/13.@Autowired的使用.md)
- [@Qualifier的使用](docs/14.@Qualifier的使用.md)
- [@Profile的使用](docs/15.@Profile的使用.md)
### AOP
- [AOP小例子](docs/16.AOP小例子.md)
### 事务
- [Spring事务](docs/17.Spring事务.md)
- [Spring事务源码](docs/17.Spring事务.md)
### 扩展原理
- [BeanFactoryPostProcessor](docs/18.BeanFactoryPostProcessor.md)
- [BeanDefinitionRegistryPostProcessor](docs/19.BeanDefinitionRegistryPostProcessor.md)
- [ApplicationListener](docs/20.ApplicationListener.md)
## 源码总结
- [Spring和SpringAOP](Spring和SpringAOP源码总结.md)
- [参考这位大佬的MVC原理](https://snailclimb.gitee.io/javaguide/#/docs/system-design/framework/spring/SpringMVC-Principle) **这位大佬总结的不错，可参考**
- [SpringMVC开发文档](https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html) **这里就不贴视频中的SpringMVC工程**
## 手写简单的IOC和AOP
- [手写简单的IOC](spring-ioc) 非常简单，每行都有注释
- [手写简单的AOP](spring-aop2) 非常简单，每行都有注释

## SpringBoot
**[参考这位大佬](https://snailclimb.gitee.io/springboot-guide/#/)**
**项目结构过于具体简单的文件解释就不说了，主要是看细节和原理**
- [SpringBoot启动流程分析](SpringBoot启动流程分析.md)


## Spring常见面试问题
- [参考这位大佬总结的，挺好的](https://snailclimb.gitee.io/javaguide/#/docs/system-design/framework/spring/SpringInterviewQuestions) 其实专注一个参考资料，认真备面就完全ok
- [什么是Spring框架](https://snailclimb.gitee.io/javaguide/#/docs/system-design/framework/spring/SpringInterviewQuestions?id=_1-%e4%bb%80%e4%b9%88%e6%98%af-spring-%e6%a1%86%e6%9e%b6)
- [列举一些重要的Spring模块](https://snailclimb.gitee.io/javaguide/#/docs/system-design/framework/spring/SpringInterviewQuestions?id=_2-%e5%88%97%e4%b8%be%e4%b8%80%e4%ba%9b%e9%87%8d%e8%a6%81%e7%9a%84spring%e6%a8%a1%e5%9d%97%ef%bc%9f)
- [@RestController vs @Controller](https://snailclimb.gitee.io/javaguide/#/docs/system-design/framework/spring/SpringInterviewQuestions?id=_3-restcontroller-vs-controller)
- **@ResponseBody的作用**：**注解的作用是将 Controller 的方法返回的对象通过适当的转换器转换为指定的格式之后，写入到HTTP 响应(Response)对象的 body 中，通常用来返回 JSON 或者 XML 数据，返回 JSON 数据的情况比较多。**
- [谈谈自己对于Spring IoC和AOP的理解](https://snailclimb.gitee.io/javaguide/#/docs/system-design/framework/spring/SpringInterviewQuestions?id=_41-%e8%b0%88%e8%b0%88%e8%87%aa%e5%b7%b1%e5%af%b9%e4%ba%8e-spring-ioc-%e5%92%8c-aop-%e7%9a%84%e7%90%86%e8%a7%a3)
- [SpringAOP和AspectAOP有什么区别](https://snailclimb.gitee.io/javaguide/#/docs/system-design/framework/spring/SpringInterviewQuestions?id=_41-%e8%b0%88%e8%b0%88%e8%87%aa%e5%b7%b1%e5%af%b9%e4%ba%8e-spring-ioc-%e5%92%8c-aop-%e7%9a%84%e7%90%86%e8%a7%a3)
- [Spring中的bean的作用域有哪些](https://snailclimb.gitee.io/javaguide/#/docs/system-design/framework/spring/SpringInterviewQuestions?id=_51-spring-%e4%b8%ad%e7%9a%84-bean-%e7%9a%84%e4%bd%9c%e7%94%a8%e5%9f%9f%e6%9c%89%e5%93%aa%e4%ba%9b)
- [Spring中的单例bean的线程安全问题了解吗](https://snailclimb.gitee.io/javaguide/#/docs/system-design/framework/spring/SpringInterviewQuestions?id=_52-spring-%e4%b8%ad%e7%9a%84%e5%8d%95%e4%be%8b-bean-%e7%9a%84%e7%ba%bf%e7%a8%8b%e5%ae%89%e5%85%a8%e9%97%ae%e9%a2%98%e4%ba%86%e8%a7%a3%e5%90%97%ef%bc%9f)
- [@Component和@bean的区别是什么](https://snailclimb.gitee.io/javaguide/#/docs/system-design/framework/spring/SpringInterviewQuestions?id=_53-component-%e5%92%8c-bean-%e7%9a%84%e5%8c%ba%e5%88%ab%e6%98%af%e4%bb%80%e4%b9%88%ef%bc%9f)
- [将一个类声明为Spring的bean的注解有哪些](https://snailclimb.gitee.io/javaguide/#/docs/system-design/framework/spring/SpringInterviewQuestions?id=_54-%e5%b0%86%e4%b8%80%e4%b8%aa%e7%b1%bb%e5%a3%b0%e6%98%8e%e4%b8%baspring%e7%9a%84-bean-%e7%9a%84%e6%b3%a8%e8%a7%a3%e6%9c%89%e5%93%aa%e4%ba%9b)
- [Spring中的bean生命周期-一](https://snailclimb.gitee.io/javaguide/#/docs/system-design/framework/spring/SpringInterviewQuestions?id=_55-spring-%e4%b8%ad%e7%9a%84-bean-%e7%94%9f%e5%91%bd%e5%91%a8%e6%9c%9f)
- [Spring中的bean生命周期-二](https://snailclimb.gitee.io/javaguide/#/docs/system-design/framework/spring/SpringBean)
- [说说自己对于SpringMVC了解](https://snailclimb.gitee.io/javaguide/#/docs/system-design/framework/spring/SpringInterviewQuestions?id=_61-%e8%af%b4%e8%af%b4%e8%87%aa%e5%b7%b1%e5%af%b9%e4%ba%8e-spring-mvc-%e4%ba%86%e8%a7%a3)
- [SpringMVC工作原理了解吗](https://snailclimb.gitee.io/javaguide/#/docs/system-design/framework/spring/SpringInterviewQuestions?id=_62-springmvc-%e5%b7%a5%e4%bd%9c%e5%8e%9f%e7%90%86%e4%ba%86%e8%a7%a3%e5%90%97)
- [Spring框架中用到了哪些设计模型-一](https://snailclimb.gitee.io/javaguide/#/docs/system-design/framework/spring/SpringInterviewQuestions?id=_7-spring-%e6%a1%86%e6%9e%b6%e4%b8%ad%e7%94%a8%e5%88%b0%e4%ba%86%e5%93%aa%e4%ba%9b%e8%ae%be%e8%ae%a1%e6%a8%a1%e5%bc%8f%ef%bc%9f)
- [Spring框架中用到了哪些设计模型-二](https://snailclimb.gitee.io/javaguide/#/docs/system-design/framework/spring/Spring-Design-Patterns)
                   
