/**
 * @program JavaBooks
 * @description: ReadWriteLockDemo
 *              多个线程同时读一个资源类没有任何问题，所以为了满足并发量，读取共享资源应该可以同时进行。
 *              如果有一个线程象取写共享资源来，就不应该自由其他线程可以对资源进行读或写
 *              因此：
 *                  读读能共存
 *                  读写不能共存
 *                  写写不能共存
 * @author: mf
 * @create: 2020/02/15 14:31
 */

package com.juc.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {
    public static void main(String[] args) {
        Mycache mycache = new Mycache();
        for (int i = 1; i <= 5; i++) {
            final int tempInt = i;
            new Thread(() -> {
                mycache.put(tempInt + "", tempInt + "");
            }, "Thread " + i).start();
        }

        for (int i = 1; i <= 5; i++) {
            final int tempInt = i;
            new Thread(() -> {
                mycache.get(tempInt + "");
            }, "Thread " + i).start();
        }

        for (int i = 1; i <= 5; i++) {
            final int tempInt = i;
            new Thread(() -> {
                mycache.put(tempInt + "", tempInt * 2);
            }, "Thread====" + i).start();
        }
    }
}

class Mycache {
    private volatile Map<String, Object> map = new HashMap<>();
    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    /**
     * 写操作：原子+独占
     * @param key
     * @param value
     */
    public void put(String key, Object value) {
        rwLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " 正在写入：" + key);
            TimeUnit.MILLISECONDS.sleep(300);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + " 写入完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    public void get(String key) {
        rwLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " 正在读取：" + key);
            TimeUnit.SECONDS.sleep(3);
            Object result = map.get(key);
            System.out.println(Thread.currentThread().getName() + " 读取完成：" + result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rwLock.readLock().unlock();
        }
    }
}