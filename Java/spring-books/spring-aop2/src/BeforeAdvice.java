import java.lang.reflect.Method;

/**
 * @program SpringBooks
 * @description: 前置通知
 * @author: mf
 * @create: 2020/02/04 20:00
 */

public class BeforeAdvice implements Advice {

    private Object bean; // bean对象

    private MethodInvocation methodInvocation; // 方法调用

    public BeforeAdvice(Object bean, MethodInvocation methodInvocation) {
        this.bean = bean;
        this.methodInvocation = methodInvocation;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 在目标方法前调用
        methodInvocation.invoke();
        return method.invoke(bean, args);
    }
}
