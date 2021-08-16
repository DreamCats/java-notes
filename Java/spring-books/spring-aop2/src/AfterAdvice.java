import java.lang.reflect.Method;

/**
 * @program SpringBooks
 * @description: 后置通知
 * @author: mf
 * @create: 2020/02/04 20:23
 */

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
