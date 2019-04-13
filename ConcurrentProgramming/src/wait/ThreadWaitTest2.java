/**
 * @program JavaBooks
 * @description: 线程等待被中断
 * @author: mf
 * @create: 2019/04/13 17:28
 */

package wait;



public class ThreadWaitTest2 {
    static Object obj = new Object();

    public static void main(String[] args) throws InterruptedException {
        // 创建线程
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("---begin---");
                    // 阻塞当前线程
                    synchronized (obj) {
                        obj.wait();
                    }

                    System.out.println("---end---");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        threadA.start();
        Thread.sleep(1000);

        System.out.println("---begin interrupt threadA");
        threadA.interrupt();
        System.out.println("---end interrupt threadA");


    }
}
