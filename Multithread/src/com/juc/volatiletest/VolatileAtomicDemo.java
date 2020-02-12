/**
 * @program JavaBooks
 * @description: 不能保证原子
 * @author: mf
 * @create: 2020/02/13 01:16
 */

package com.juc.volatiletest;

public class VolatileAtomicDemo {
    public static volatile int count = 0;

    public static void increase() {
        count++;
    }

    public static void main(String[] args) {
        Thread[] threads = new Thread[20];
        for(int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for(int j = 0; j < 1000; j++) {
                    increase();
                }
            });
            threads[i].start();
        }
        //等待所有累加线程结束
        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
        System.out.println(count);
    }
}
