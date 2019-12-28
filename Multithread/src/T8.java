import java.util.concurrent.TimeUnit;

/**
 * @program JavaBooks
 * @description: 锁粒度
 * @author: mf
 * @create: 2019/12/29 00:23
 */

public class T8 {

    private int count = 0;

    synchronized void m1() {
        // 锁粒度较粗，锁的东西较多
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        count++;

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void m2() {
        // 锁粒度较细， 锁的东西较少， 运行时间比上面快
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (this) {
            count++; //
        }
    }
}
