/**
 * @program JavaBooks
 * @description: 并行计算
 * @author: mf
 * @create: 2020/01/25 12:40
 */

package com.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class ParallerStreamDemo {

    public static void main(String[] args) {
        int max = 1000000;
        List<String> values = new ArrayList<>(max);
        for (int i = 0; i < max; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }

        // Sequential Sort(串行排序)
        long t0 = System.nanoTime();
//        long t0 = System.currentTimeMillis();
        long count  = values.stream().sorted().count();
        System.out.println(count);
        long t1 = System.nanoTime();
//        long t1 = System.currentTimeMillis();
        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
//        long millis = t1 - t0;
        System.out.println(String.format("sequential sort took: %d ms", millis));

        // paraller sorted

        long t2 = System.nanoTime();
        long count1 = values.parallelStream().sorted().count();
        System.out.println(count1);
        long t3 = System.nanoTime();
        long millis1 = TimeUnit.NANOSECONDS.toMillis(t3 - t2);
        System.out.println(String.format("paraller sort took: %d ms", millis1));
    }
}
