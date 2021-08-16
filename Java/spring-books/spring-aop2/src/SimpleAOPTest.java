/**
 * @program SpringBooks
 * @description: SimpleAOPTest
 * @author: mf
 * @create: 2020/02/04 20:06
 */

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
