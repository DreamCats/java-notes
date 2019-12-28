import java.util.concurrent.TimeUnit;

/**
 * @program JavaBooks
 * @description: 锁对象变了，锁就被释放了
 * @author: mf
 * @create: 2019/12/29 00:28
 */

public class T9 {

    Object o = new Object();

    void m() {
        synchronized (o) {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()); // 打印自己的名字
            }
        }
    }

    public static void main(String[] args) {
        T9 t9 = new T9();

        new Thread(t9::m, "t9").start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t9.o = new Object(); // 注释掉这句话的话， t9二代是不会启动的，因为还在上锁呢。

        new Thread(t9::m, "t9二代").start();
    }
}
