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

public class ProdConsumerSynchronized {

    private final LinkedList<String> lists = new LinkedList<>();

    public synchronized void put(String s) {
        while (lists.size() != 0) { // 用while怕有存在虚拟唤醒线程
            // 满了， 不生产了
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        lists.add(s);
        System.out.println(Thread.currentThread().getName() + " " + lists.peekFirst());
        this.notifyAll(); // 这里可是通知所有被挂起的线程，包括其他的生产者线程
    }

    public synchronized void get() {
        while (lists.size() == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " " + lists.removeFirst());
        this.notifyAll(); // 通知所有被wait挂起的线程  用notify可能就死锁了。
    }

    public static void main(String[] args) {
        ProdConsumerSynchronized prodConsumerSynchronized = new ProdConsumerSynchronized();

        // 启动消费者线程
        for (int i = 0; i < 5; i++) {
            new Thread(prodConsumerSynchronized::get, "ConsA" + i).start();
        }

        // 启动生产者线程
        for (int i = 0; i < 5; i++) {
            int tempI = i;
            new Thread(() -> {
                prodConsumerSynchronized.put("" + tempI);
            }, "ProdA" + i).start();
        }








    }
}
