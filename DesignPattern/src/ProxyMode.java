import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @program JavaBooks
 * @description: 代理模式
 * @author: mf
 * @create: 2019/10/02 10:04
 */


/*
所谓代理模式是指客户端并不直接调用实际的对象，而是通过调用代理，来间接的调用实际的对象。
一般是因为客户端不想直接访问实际的对象，或者访问实际的对象存在困难，因此通过一个代理对象来完成间接的访问。
 */

/*
静态代理
 */

// 主题
interface Subject {
    void visit();
}

// 实现subject的两个类
class RealSubject implements Subject {

    private String name = "feng zi";
    @Override
    public void visit() {
        System.out.println(name);
    }
}

class ProxySubject implements Subject {

    private Subject subject;

    public ProxySubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public void visit() {
        subject.visit();
    }
}

/*
代理类接受一个Subject接口的对象，任何实现该接口的对象，都可以通过代理类进行代理，增加了通用性。
每一个代理类都必须实现一遍委托类（也就是realsubject）的接口，如果接口增加方法，则代理类也必须跟着修改。
代理类每一个接口对象对应一个委托对象，如果委托对象非常多，则静态代理类就非常臃肿，难以胜任。
 */

/*
动态代理
动态代理是实现方式，是通过反射来实现的，借助Java自带的java.lang.reflect.Proxy,通过固定的规则生成。
 */
class DynamicProxy implements InvocationHandler {

    private Object object;

    public DynamicProxy(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = method.invoke(object, args);
        return result;
    }
}

public class ProxyMode {
    public static void main(String[] args) {
        // 静态代理
        ProxySubject proxySubject = new ProxySubject(new RealSubject());
        proxySubject.visit();

        // 动态代理
        RealSubject realSubject = new RealSubject();
        DynamicProxy dynamicProxy = new DynamicProxy(realSubject);
        ClassLoader classLoader = realSubject.getClass().getClassLoader();
        Subject subject = (Subject) Proxy.newProxyInstance(classLoader, new Class[]{Subject.class}, dynamicProxy);
        subject.visit();
    }
}
