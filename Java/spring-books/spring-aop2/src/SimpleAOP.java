import java.lang.reflect.Proxy;

/**
 * @program SpringBooks
 * @description: AOP的简单实现
 * @author: mf
 * @create: 2020/02/04 20:02
 */

public class SimpleAOP {

    public static Object getProxy(Object bean, Advice advice) {
        return Proxy.newProxyInstance(SimpleAOP.class.getClassLoader()
        , bean.getClass().getInterfaces(), advice);
    }
}
