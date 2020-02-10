package com.basic;

import java.util.Arrays;

/**
 * @program JavaBooks
 * @description: 单例，内部类方式，不需要加锁
 * @author: mf
 * @create: 2019/12/31 11:17
 */

public class T18 {

    private T18() {
        System.out.println("single");
    }

    private static class Inner {
        private static T18 s = new T18();
    }

    private static T18 getSingle() {
        return Inner.s;
    }

    public static void main(String[] args) {
        Thread[] ths = new Thread[100];
        for (int i = 0; i < ths.length; i++) {
            ths[i] = new Thread(() -> {
                T18.getSingle();
            });
        }
        Arrays.asList(ths).forEach(o -> o.start());
     }
}
