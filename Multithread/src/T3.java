/**
 * @program JavaBooks
 * @description: 同步和非同步方法是否可以同时使用
 * @author: mf
 * @create: 2019/12/26 21:30
 */

/**
 * m1方法执行过程当中，能否执行m2
 * 可以执行
 */
public class T3 {

    public synchronized void m1() { // 锁定的对象的同步方法
        System.out.println(Thread.currentThread().getName() + " m1 start ...");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " m1 end ...");
    }

    public void m2() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " m2 ");
    }

    public static void main(String[] args) {
        T3 t3 = new T3();

        new Thread(() -> t3.m1(), "t1").start();
        new Thread(() -> t3.m2(), "t2").start();
    }
}
