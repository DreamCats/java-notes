/**
 * @program JavaBooks
 * @description: HashSetTest
 * @author: mf
 * @create: 2020/02/14 17:21
 */

package com.juc.collectiontest;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

public class HashSetTest {
    public static void main(String[] args) {
//        notSafe();
//        safe1();
        safe2();
    }
    /**
     * 故障现象
     * java.util.ConcurrentModificationException
     */
    public static void notSafe() {
        Set<String> list = new HashSet<>();
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, "Thread " + i).start();
        }
    }
    public static void safe1() {
        Set<String> list = Collections.synchronizedSet(new HashSet<>());
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, "Thread " + i).start();
        }
    }

    public static void safe2() {
        Set<String> list = new CopyOnWriteArraySet<>();
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, "Thread " + i).start();
        }
    }
}
