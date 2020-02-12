/**
 * @program JavaBooks
 * @description: volatile的特性：可见性
 * @author: mf
 * @create: 2020/02/13 00:49
 */

package com.juc.volatiletest;

import java.util.concurrent.TimeUnit;

public class VolatileVisibleDemo {

//    private  boolean isReady = true;
    private volatile boolean isReady = true;

    void m() {
        System.out.println(Thread.currentThread().getName() + " m start...");
        while (isReady) {
        }
        System.out.println(Thread.currentThread().getName() + " m end...");
    }

    public static void main(String[] args) {
        VolatileVisibleDemo demo = new VolatileVisibleDemo();
        new Thread(() -> demo.m(), "t1").start();
        try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
        demo.isReady = false; // 刚才一秒过后开始执行
    }
}
