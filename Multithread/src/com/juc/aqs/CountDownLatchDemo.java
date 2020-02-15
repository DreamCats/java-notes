/**
 * @program JavaBooks
 * @description: CountDownLatchDemo
 * @author: mf
 * @create: 2020/02/15 17:09
 */

package com.juc.aqs;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        countDownLatchTest();
//        general();
    }

    public static void general() {
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " 上完自习，离开教师");
            }, "Thread --> " + i).start();
        }
        while (Thread.activeCount() > 2) {
            try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println(Thread.currentThread().getName() + " ====班长最后走人");
        }
    }

    public static void countDownLatchTest() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " 上完自习，离开教师");
                countDownLatch.countDown();
            }, "Thread --> " + i).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + " ====班长最后走人");
    }
}



