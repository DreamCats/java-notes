import java.util.concurrent.locks.ReentrantLock;

/**
 * @program JavaBooks
 * @description: ReentrantLock的公平锁
 * @author: mf
 * @create: 2019/12/30 20:42
 */

public class T14 {

    private static ReentrantLock lock = new ReentrantLock(true); // true为公平锁

    public void m() {
        for (int i = 0; i < 1000; i++) {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "获得锁");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        T14 t14 = new T14();


        new Thread(t14::m, "t1").start();

        new Thread(t14::m, "t2").start();
    }
}
