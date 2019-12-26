/**
 * @program JavaBooks
 * @description: synchroniezd关键字
 * @author: mf
 * @create: 2019/12/26 21:09
 */

public class T2 {

    private int count = 10;

    public void m() {
        synchronized (this) { // 任何线程要执行下面的代码，必须先拿到this的锁
            count--;
            System.out.println(Thread.currentThread().getName() + " count = " + count);
        }
    }

    public synchronized void m1() { // 等效synchroniezd(this)
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }
}
