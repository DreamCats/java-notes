package com.basic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @program JavaBooks
 * @description: Semaphore例子
 * @author: mf
 * @create: 2020/01/09 15:35
 */

public class T32 {

    // 请求的数量
    private static final int threadCount = 20;

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(10);

        // 一次只能允许执行的线程数量
        final Semaphore semaphore = new Semaphore(5);

        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            service.execute(() -> {
                try {
                    semaphore.acquire(); // 获取一个许可，所以可运行线程数量为5/1=5
                    Thread.sleep(1000);
                    System.out.println("threadNum: " + threadNum);
                    Thread.sleep(1000);
                    semaphore.release(); // 释放一个许可
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        service.shutdown();
        System.out.println("finish...");
    }
}
