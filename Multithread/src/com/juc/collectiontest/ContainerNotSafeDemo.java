/**
 * @program JavaBooks
 * @description: ContainerNotSafeDemo
 * @author: mf
 * @create: 2020/02/14 11:52
 */

package com.juc.collectiontest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ContainerNotSafeDemo {
    public static void main(String[] args) {
        notSafe();
    }

    /**
     * 故障现象
     * java.util.ConcurrentModificationException
     */
    public static void notSafe() {
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, "Thread " + i).start();
        }
    }

}
