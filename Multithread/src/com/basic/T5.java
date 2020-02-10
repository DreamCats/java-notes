package com.basic;

import java.util.concurrent.TimeUnit;

/**
 * @program JavaBooks
 * @description: 异常释放锁
 * @author: mf
 * @create: 2019/12/27 23:38
 */

public class T5 {

    private int count = 0;

    public synchronized void m()  {
        System.out.println(Thread.currentThread().getName() + " start... ");
        while (true) {
            count++;
            System.out.println(Thread.currentThread().getName() + " count = " + count);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (count == 5) {
                int i = 1 / 0; // 此处抛出异常，锁将被释放，要想不被释放，可以在这里进行catch，然后让循环继续
            }
        }
    }

    public static void main(String[] args) {
        T5 t5 = new T5();

        new Thread(() -> t5.m(), "t1").start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> t5.m(), "t2").start();
    }
}
