/**
 * @program JavaBooks
 * @description: 查看Volatile的字节码
 * @author: mf
 * @create: 2020/02/12 02:58
 */

package com.juc.volatiletest;

public class VolatileClassDemo {
    volatile int n = 0;

    public void add() {
        n++;
    }

    public static void main(String[] args) {

    }
}
