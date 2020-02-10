package com.basic;

import java.util.concurrent.*;

/**
 * @program JavaBooks
 * @description: future
 * @author: mf
 * @create: 2020/01/01 14:18
 */

public class T23 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> task = new FutureTask<>(() -> {
            TimeUnit.MILLISECONDS.sleep(500);
            return 1000;
        });

        new Thread(task).start();

        System.out.println(task.get()); // 阻塞

        ExecutorService service = Executors.newFixedThreadPool(5);

        Future<Integer> f = service.submit(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        });
//        System.out.println(f.get());
        System.out.println(f.isDone());
        System.out.println(f.get());
        System.out.println(f.isDone());
        service.shutdown();
    }
}
