/**
 * @program JavaBooks
 * @description: compare and swap
 * @author: mf
 * @create: 2020/02/13 14:48
 */

package com.juc.cas;

import java.util.concurrent.atomic.AtomicInteger;

public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        System.out.println(atomicInteger.compareAndSet(5, 2020) +
                " current value: " + atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5, 1024) +
                " current value: " + atomicInteger.get());
    }
}
