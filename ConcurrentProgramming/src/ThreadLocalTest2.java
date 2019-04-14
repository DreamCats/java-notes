/**
 * @program JavaBooks
 * @description: 本地变量
 * @author: mf
 * @create: 2019/04/14 17:40
 */

public class ThreadLocalTest2 {
    // （1）创建线程变量
    public static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        // (2) 设置线程变量
        threadLocal.set("hello world");
        // (3) 启动子线程
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // (4) 子线程输出线程变量的值
                System.out.println("thread:" + threadLocal.get());
            }
        });
        thread.start();

        // (5) 主线程输出线程变量的值
        System.out.println("main:" + threadLocal.get());

        // 在主线程设置的本地变量的值， 在子线程是获取不到的。
    }
}
