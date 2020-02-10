package com.basic; /**
 * @program JavaBooks
 * @description: synchronized关键字
 * @author: mf
 * @create: 2019/12/26 21:00
 */

/**
 * 对某个对象加锁
 */
public class T1 {

    private int count = 10;

    private static int count1 = 10;

    private Object o = new Object();

    public void m() {
        synchronized (o) { // 给o对象加锁， o是监视器
            count--;
            System.out.println(Thread.currentThread().getName() + " count = " + count);
        }
    }

    public void m1() {
        synchronized (this) { // 任何线程要执行下面的代码，必须先拿到this的锁
            count--;
            System.out.println(Thread.currentThread().getName() + " count = " + count);
        }
    }

    public synchronized void m2() { // 等效synchroniezd(this)
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

    public static synchronized void m3() { // class对象
        count1--;
        System.out.println(Thread.currentThread().getName() + " count = " + count1);
    }

    public static void m4() {
        synchronized (T1.class) {
            count1--;
        }
    }
}
