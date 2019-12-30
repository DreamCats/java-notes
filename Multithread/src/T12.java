import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program JavaBooks
 * @description: ReentrantLock
 * @author: mf
 * @create: 2019/12/30 19:51
 */



public class T12 {

    Lock lock = new ReentrantLock();

    void m1() {
        try {
            lock.lock(); // 加锁
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void m2() {
        lock.lock();
        System.out.println("m2...");
    }

    public static void main(String[] args) {
        T12 t12 = new T12();
        new Thread(t12::m1, "t1").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(t12::m2, "t2").start();
    }
}
