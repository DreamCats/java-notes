/**
 * @program JavaBooks
 * @description: ProdConsumerReentrantLock
 * @author: mf
 * @create: 2020/02/16 13:43
 */

package com.juc.queue;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProdConsumerReentrantLock {

    private LinkedList<String> lists = new LinkedList<>();

    private Lock lock = new ReentrantLock();

    private Condition prod = lock.newCondition();

    private Condition cons = lock.newCondition();

    public void put(String s) {
        lock.lock();
        try {
            // 1. 判断
            while (lists.size() != 0) {
                // 等待不能生产
                prod.await();
            }
            // 2.干活
            lists.add(s);
            System.out.println(Thread.currentThread().getName() + " " + lists.peekFirst());
            // 3. 通知
            cons.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void get() {
        lock.lock();
        try {
            // 1. 判断
            while (lists.size() == 0) {
                // 等待不能消费
                cons.await();
            }
            // 2.干活
            System.out.println(Thread.currentThread().getName() + " " + lists.removeFirst());
            // 3. 通知
            prod.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ProdConsumerReentrantLock prodConsumerReentrantLock = new ProdConsumerReentrantLock();
        for (int i = 0; i < 5; i++) {
            int tempI = i;
            new Thread(() -> {
                prodConsumerReentrantLock.put(tempI + "");
            }, "ProdA" + i).start();
        }
        for (int i = 0; i < 5; i++) {
            new Thread(prodConsumerReentrantLock::get, "ConsA" + i).start();
        }
    }
}
