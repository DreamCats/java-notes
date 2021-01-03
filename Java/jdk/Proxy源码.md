> 动态代理经常用到该类的一个方法

## newProxyInstance

```java
1. ClassLoader:类加载器
2. interfaces：被代理类所实现的接口
3. InvocationHandler：Invoker回调接口
@CallerSensitive
public static Object newProxyInstance(ClassLoader loader,
                                      Class<?>[] interfaces,
                                      InvocationHandler h)
    throws IllegalArgumentException
{	
    // 回调不能是空
    Objects.requireNonNull(h);
	// 拷贝
    final Class<?>[] intfs = interfaces.clone();
    final SecurityManager sm = System.getSecurityManager();
    if (sm != null) {
        // 检查loader有木有问题吧
        checkProxyAccess(Reflection.getCallerClass(), loader, intfs);
    }

    /*
     * Look up or generate the designated proxy class.
     */
    // 一看0，就知道JNT，注释上写的是为该接口生成代理类
    Class<?> cl = getProxyClass0(loader, intfs);

    /*
     * Invoke its constructor with the designated invocation handler.
     */
    // 用指定的调用处理程序调用它的构造函数。
    try {
        if (sm != null) {
            checkNewProxyPermission(Reflection.getCallerClass(), cl);
        }
		// 获取代理类的构造器
        final Constructor<?> cons = cl.getConstructor(constructorParams);
        // 拿到参数的invoker
        final InvocationHandler ih = h;
        // 如果不是public， 直接暴力
        if (!Modifier.isPublic(cl.getModifiers())) {
            AccessController.doPrivileged(new PrivilegedAction<Void>() {
                // 暴力解除
                public Void run() {
                    cons.setAccessible(true);
                    return null;
                }
            });
        }
        // 直接根据代理的构造器通过反射new一个实例，接着调用回调invoker
        // 当用户通过代理类调用方法的时候，实际上会调用invorker，也就是参数的h
        return cons.newInstance(new Object[]{h});
    } catch (IllegalAccessException|InstantiationException e) {
        throw new InternalError(e.toString(), e);
    } catch (InvocationTargetException e) {
        Throwable t = e.getCause();
        if (t instanceof RuntimeException) {
            throw (RuntimeException) t;
        } else {
            throw new InternalError(t.toString(), t);
        }
    } catch (NoSuchMethodException e) {
        throw new InternalError(e.toString(), e);
    }
}
```

