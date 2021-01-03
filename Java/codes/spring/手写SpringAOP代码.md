# 手写SpringAOP

## 定义advice接口

```java
public interface Advice extends InvocationHandler {
}
```

## 定义方法回调接口
```java
public interface MethodInvocation {
    void invoke();
}
```

## 定义前置通知
```java
public class BeforeAdvice implements Advice {

    private Object bean; // bean对象

    private MethodInvocation methodInvocation; // 方法调用

    public BeforeAdvice(Object bean, MethodInvocation methodInvocation) {
        this.bean = bean;
        this.methodInvocation = methodInvocation;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 在目标方法前调用,前置
        methodInvocation.invoke();
        return method.invoke(bean, args);
    }
}
```

## 定义后置通知
```java
public class AfterAdvice implements Advice {

    private Object bean;

    private MethodInvocation methodInvocation;

    public AfterAdvice(Object bean, MethodInvocation methodInvocation) {
        this.bean = bean;
        this.methodInvocation = methodInvocation;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 业务逻辑调用
        Object invoke = method.invoke(bean, args);
        // 后置通知调用
        methodInvocation.invoke();
        return invoke;
    }
}
```

## 定义目标方法接口
```java
public interface HelloService {
    void sayHelloWorld();
}
```

## 实现目标方法
```java
public class HelloServiceImpl implements HelloService {

    @Override
    public void sayHelloWorld() {
        System.out.println("hello world...");
    }
}
```

## SpringAOP的实现
```java
public class SimpleAOP {
    // 反射代理 
    public static Object getProxy(Object bean, Advice advice) {
        return Proxy.newProxyInstance(SimpleAOP.class.getClassLoader()
        , bean.getClass().getInterfaces(), advice);
    }
}
```

## 简单测试
```java
public class SimpleAOPTest {
    public static void main(String[] args) {
        // 1. 创建一个 MethodInvocation 实现类 切面逻辑类
        MethodInvocation logTask = () -> System.out.println("log task start");
        MethodInvocation logTaskEnd = () -> System.out.println("log task end");

        // 业务逻辑类
        HelloServiceImpl helloService = new HelloServiceImpl();

        // 2. 创建一个Advice 切入点
        BeforeAdvice beforeAdvice = new BeforeAdvice(helloService, logTask);
        AfterAdvice afterAdvice = new AfterAdvice(helloService, logTaskEnd);

        // 3. 为目标对象生成代理
        HelloService helloServiceImplProxy = (HelloService) SimpleAOP.getProxy(helloService, beforeAdvice);
        HelloService helloServiceImplProxyAfter = (HelloService) SimpleAOP.getProxy(helloService, afterAdvice);

        helloServiceImplProxy.sayHelloWorld();
        helloServiceImplProxyAfter.sayHelloWorld();

    }
}
```
