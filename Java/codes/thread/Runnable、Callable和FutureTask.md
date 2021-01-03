# Runnable、Callable和FutureTask
> Runnable自Java 1.0以来一直存在，但Callable仅在Java 1.5中引入,目的就是为了来处理**Runnable不支持的用例**。**Runnable 接口不会返回结果或抛出检查异常，但是Callable 接口可以**。所以，如果任务不需要返回结果或抛出异常推荐使用 Runnable 接口，这样代码看起来会更加简洁。

## Runnable源码
```java
@FunctionalInterface
public interface Runnable {
   /**
    * 被线程执行，没有返回值也无法抛出异常
    */
    public abstract void run();
}
```

## Callable源码
```java
@FunctionalInterface
public interface Callable<V> {
    /**
     * 计算结果，或在无法这样做时抛出异常。
     * @return 计算得出的结果
     * @throws 如果无法计算结果，则抛出异常
     */
    V call() throws Exception;
}
```

## 线程池的submit

![看图说话](https://imgkr.cn-bj.ufileos.com/16a8713e-b2d1-408d-82e2-ac411a51b6e0.png)

## 再瞧Thread


![](https://imgkr.cn-bj.ufileos.com/ff6bdf55-1616-4887-b320-1b21576f8211.png)

有个疑问，没有FutureTask的参数哇？

再看FutureTask：

![](https://imgkr.cn-bj.ufileos.com/6f63d5a6-50a5-4412-b301-1bab8ce91b4d.png)

它的参数有Callable和Runnable。

`public class FutureTask<V> implements RunnableFuture<V>`
源码实现了RunnableFuture，那我们跟进再看：

```java
public interface RunnableFuture<V> extends Runnable, Future<V> {
    /**
     * Sets this Future to the result of its computation
     * unless it has been cancelled.
     */
    void run();
}
```
RunnableFuture继承了Runnable和Future。意味着FutureTask既有Runnable和Future的特性，Future的方法：

![](https://imgkr.cn-bj.ufileos.com/5c856df9-feb9-43c0-9329-97db80239de0.png)

可看出，Future的特性的get方法了。
