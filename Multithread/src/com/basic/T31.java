package com.basic; /**
 * @program JavaBooks
 * @description: 死锁的模拟
 * @author: mf
 * @create: 2020/01/09 13:57
 */


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 用线程池模拟，比较方便一些
 */
public class T31 {

    private static Object res1 = new Object(); // 资源1

    private static Object res2 = new Object(); // 资源2

    public void m1() {
        synchronized (res1) {
            // 占用res1的锁
            System.out.println(Thread.currentThread().getName() + " get res1");
            try {
                Thread.sleep(1000); // 延时一会，等待m2的方法占用res2
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("waiting get res2");
            synchronized (res2) {
                System.out.println(Thread.currentThread().getName() + " get res2");
            }
        }
    }

    public void m2() {
        synchronized (res2) {
            // 占用res2的锁
            System.out.println(Thread.currentThread().getName() + " get res2");
            try {
                Thread.sleep(1000); //
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("waiting get res1");
            synchronized (res1) {
                System.out.println(Thread.currentThread().getName() + " get res1");
            }
        }
    }

    public static void main(String[] args) {
        T31 t31 = new T31();
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(() -> t31.m1());
        service.execute(() -> t31.m2());
    }
}
