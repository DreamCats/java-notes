/**
 * @program JavaBooks
 * @description: MapSafe
 * @author: mf
 * @create: 2020/02/14 17:24
 */

package com.juc.collectiontest;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MapSafe {
    public static void main(String[] args) {
        notSafe();
    }

    public static void notSafe() {
//        Map<String, String> map = new HashMap<>();
//        Map<String, String> map = new ConcurrentHashMap<>();
        Map<String, String> map = Collections.synchronizedMap(new HashMap<>());

        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString());
                System.out.println(map);
            }, "Thread " + i).start();
        }
    }
}
