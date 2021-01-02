> 代理经常用到，回调方法，很多框架也的到。
>
> 不过这仅仅是动态代理

## 类

```java
// 接口
public interface InvocationHandler
```

## invoke

```java
// proxy：调用方法的代理实例
// method：代理实例的接口方法
// args：参数
public Object invoke(Object proxy, Method method, Object[] args)
    throws Throwable;
```

