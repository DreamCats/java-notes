/**
 * @program JavaBooks
 * @description: 避免死锁1
 * @author: mf
 * @create: 2019/04/14 17:01
 */

package deadlock;

public class DeadLockTest2 {
    // 创建资源
    private static Object resourceA = new Object();
    private static Object resourceB = new Object();

    public static void main(String[] args) {
        // 创建线程A
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceA) {
                    System.out.println(Thread.currentThread() + "get ResourceA");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread() + "waiting get sourceB");
                    synchronized (resourceB) {
                        System.out.println(Thread.currentThread() + "get esourceB");
                    }
                }
            }
        });

        // 创建线程B
        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceA) {
                    System.out.println(Thread.currentThread() + "get ResourceB");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread() + "waiting get sourceA");
                    synchronized (resourceB) {
                        System.out.println(Thread.currentThread() + "get esourceA");
                    }
                }
            }
        });

        threadA.start();
        threadB.start();
    }
}
