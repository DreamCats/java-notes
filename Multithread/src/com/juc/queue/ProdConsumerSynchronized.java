/**
 * @program JavaBooks
 * @description: ProdConsumerSynchronized
 *                  synchronized版本的生产者和消费者，比较繁琐
 *                  能够支持2个生产者线程以及10个消费者线程的阻塞调用
 * @author: mf
 * @create: 2020/02/16 13:01
 */

package com.juc.queue;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class ProdConsumerSynchronized {

    private final LinkedList<String> lists = new LinkedList<>();

    private final int MAX = 10; // 最多10个元素

    private int count = 0;

    public synchronized void put(String s) {
        while (lists.size() == 1) { // 用while怕有存在虚拟唤醒线程
            // 满了， 不生产了
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        lists.add(s); // 毕竟所有线程抢这一把锁
        count++;
        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
        this.notifyAll(); // 这里可是通知所有被挂起的线程，包括其他的生产者线程
    }

    public synchronized String get() {
        String s = null;
        while (lists.size() == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        s = lists.removeFirst();
        count--;
        this.notifyAll(); // 通知所有被wait挂起的线程  用notify可能就死锁了。
        return s;
    }

    public static void main(String[] args) {
        ProdConsumerSynchronized prodConsumerSynchronized = new ProdConsumerSynchronized();
        // 启动消费者线程
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 15; j++) {
                    System.out.println(Thread.currentThread().getName() + prodConsumerSynchronized.get());
                }
            }, "消费者线程ID：" + i).start();
        }

        try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }
        // 启动生产者线程
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 15; j++) {
                    prodConsumerSynchronized.put(Thread.currentThread().getName() + " 编号：" + j);
                }
            }, "生产者线程ID：" + i).start();
        }
    }
}
