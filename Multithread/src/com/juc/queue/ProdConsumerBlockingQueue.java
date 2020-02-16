/**
 * @program JavaBooks
 * @description: ProdConsumerBlockingQueue
 * @author: mf
 * @create: 2020/02/16 14:20
 */

package com.juc.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ProdConsumerBlockingQueue {

    private volatile boolean flag = true;

    private AtomicInteger atomicInteger = new AtomicInteger();

    BlockingQueue<String> blockingQueue = null;

    public ProdConsumerBlockingQueue(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public void myProd() throws Exception {
        String data = null;
        boolean retValue;
        while (flag) {
            data = atomicInteger.incrementAndGet() + "";
            retValue = blockingQueue.offer(data, 2, TimeUnit.SECONDS);
            if (retValue) {
                System.out.println(Thread.currentThread().getName() + " 插入队列" + data + " 成功");
            } else {
                System.out.println(Thread.currentThread().getName() + " 插入队列" + data + " 失败");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName() + " 大老板叫停了，flag=false，生产结束");
    }

    public void myConsumer() throws Exception {
        String result = null;
        while (flag) {
            result = blockingQueue.poll(2, TimeUnit.SECONDS);
            if (null == result || result.equalsIgnoreCase("")) {
                flag = false;
                System.out.println(Thread.currentThread().getName() + " 超过2s没有取到蛋糕，消费退出");
                return;
            }
            System.out.println(Thread.currentThread().getName() + " 消费队列" + result + "成功");
        }
    }

    public void stop() {
        flag = false;
    }

    public static void main(String[] args) {
        ProdConsumerBlockingQueue prodConsumerBlockingQueue = new ProdConsumerBlockingQueue(new ArrayBlockingQueue<>(10));
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " 生产线程启动");
            try {
                prodConsumerBlockingQueue.myProd();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "Prod").start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " 消费线程启动");
            try {
                prodConsumerBlockingQueue.myConsumer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "Consumer").start();

        try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println("5s后main叫停，线程结束");
        prodConsumerBlockingQueue.stop();
    }
}
