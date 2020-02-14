/**
 * @program JavaBooks
 * @description: ContainerNotSafeDemo
 * @author: mf
 * @create: 2020/02/14 11:52
 */

package com.juc.collectiontest;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class ContainerNotSafeDemo {
    public static void main(String[] args) {
//        notSafe();
//        vectorTest();
//        synchronizedTest();
        copyOnWriteTest();
    }

    /**
     * 故障现象
     * java.util.ConcurrentModificationException
     */
    public static void notSafe() {
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, "Thread " + i).start();
        }
    }

    /**
     * Vector
     */
    public static void vectorTest() {
        List<String> list = new Vector<>();
        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, "Thread " + i).start();
        }
    }

    /**
     * Collections.synchronizedList
     */
    public static void synchronizedTest() {
        List<String> list = Collections.synchronizedList(new ArrayList<>());
        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, "Thread " + i).start();
        }
    }

    /**
     * copyOnWriteArrayList
     */
    public static void copyOnWriteTest() {
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, "Thread " + i).start();
        }
    }

}
