

## Springboot自动装配原理

SpringBootApplication的注解
[https://www.jianshu.com/p/943650ab7dfd](https://www.jianshu.com/p/943650ab7dfd)

- @SpringBootConfiguration:允许在上下文中注册额外的bean或导入其他配置类
- @EnableAutoConfiguration:启用 SpringBoot 的自动配置机制
- @ComponentScan: 扫描常用的注解

其中 @EnableAutoConfiguration 是实现自动配置的入口，该注解又通过 @Import 注解导入了AutoConfigurationImportSelector，在该类中加载 META-INF/spring.factories 的配置信息。然后筛选出以 EnableAutoConfiguration 为 key 的数据，加载到 IOC 容器中，实现自动配置功能！


## @Resource和@Autowired区别

### 共同点
两者都可以写在字段和setter方法上。两者如果都写在字段上，那么就不需要再写setter方法。

```java
public class TestServiceImpl {
    // 下面两种@Autowired只要使用一种即可
    @Autowired
    private UserDao userDao; // 用于字段上
    
    @Autowired
    public void setUserDao(UserDao userDao) { // 用于属性的方法上
        this.userDao = userDao;
    }
}
```

### 不同点

1. @Autowired

@Autowired注解是**按照类型**（byType）装配依赖对象，默认情况下它要求依赖对象必须存在，如果允许null值，可以设置它的required属性为false。如果我们想使用**按照名称**（byName）来装配，可以结合**@Qualifier注解**一起使用。(通过类型匹配找到多个candidate,在没有@Qualifier、@Primary注解的情况下，会使用对象名作为最后的fallback匹配)如下：

```java
public class TestServiceImpl {
    @Autowired
    @Qualifier("userDao")
    private UserDao userDao; 
}
```

2. @Resource

@Resource默认按照ByName自动注入。@Resource有两个重要的属性：**name和type**，而Spring将@Resource注解的name属性解析为bean的名字，而type属性则解析为bean的类型。**所以，如果使用name属性，则使用byName的自动注入策略，而使用type属性时则使用byType自动注入策略。如果既不制定name也不制定type属性，这时将通过反射机制使用byName自动注入策略**