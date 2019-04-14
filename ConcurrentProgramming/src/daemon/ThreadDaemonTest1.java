/**
 * @program JavaBooks
 * @description: 守护进程1
 * @author: mf
 * @create: 2019/04/14 17:06
 */

package daemon;

public class ThreadDaemonTest1 {
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (;;){}
            }
        });

        // 设置为守护线程
//        thread.setDaemon(true);
        // 启动子线程
        thread.start();

        System.out.println("main thread is over");

        // jps查看

        // 说明当父线程结束之后， 子线程还是继续存在的。

    }
}
