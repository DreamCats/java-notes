/**
 * @program JavaBooks
 * @description: 启动方式：线程继承
 * @author: mf
 * @create: 2019/04/12 16:01
 */

package start;

public class ThreadExtends {
    // 继承Thread类并重写run方法
    public static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("I am a child thread");
        }
    }

    public static void main(String[] args) {
        // 创建线程
        MyThread mt = new MyThread();

        // 启动线程

        mt.start();
    }
}
