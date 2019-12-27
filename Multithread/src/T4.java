import java.util.concurrent.TimeUnit;

/**
 * @program JavaBooks
 * @description: 一个同步方法可以调用另外一个同步方法，synchronized是可重入锁 子类同步也可以调用父类的同步方法
 * @author: mf
 * @create: 2019/12/26 22:00
 */

public class T4 {

    synchronized void m1() {
        System.out.println("m1 start");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        m2();
    }

    synchronized void m2() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m2");
    }
}
