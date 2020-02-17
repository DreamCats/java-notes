/**
 * @program JavaBooks
 * @description: DeadLockDemo
 * @author: mf
 * @create: 2020/02/17 19:01
 */

package com.juc.pool;

import java.util.concurrent.TimeUnit;

public class DeadLockDemo {
    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";
        new Thread(new HoldThread(lockA, lockB), "lockAAA").start();
        new Thread(new HoldThread(lockB, lockA), "lockBBB").start();

        // 通过jsp和jstack可以配合查看死锁
    }
}

class HoldThread implements Runnable {

    private String lockA;

    private String lockB;

    public HoldThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName() + "\t自己持有：" + lockA + "\t尝试获得：" + lockB);
            // 为了看的更清楚
            try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }
            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() + "\t自己持有：" + lockB + "\t尝试获得：" + lockA);
            }
        }
    }
}