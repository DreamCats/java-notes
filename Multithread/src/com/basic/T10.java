package com.basic;

/**
 * @program JavaBooks
 * @description: 不要以字符串常量作为锁定对象
 * @author: mf
 * @create: 2019/12/29 00:34
 */

public class T10 {

    private String s1 = "hello";

    private String s2 = "hello";

    void m1() {
        synchronized (s1) {

        }
    }

    void m2() {
        synchronized (s2) {

        }
    }

    // m1和m2锁的同一个字符串常量对象。
}
