/**
 * @program JavaBooks
 * @description: String StringBuffer StringBuilder
 * @author: mf
 * @create: 2020/02/07 19:13
 */

package com.strings;

public class SbDemo {
    public static void main(String[] args) {
        // String
        String str = "hello";
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            str += i; // 创建多少个对象，，
        }
        System.out.println("String: " + (System.currentTimeMillis() - start));

        // StringBuffer
        StringBuffer sb = new StringBuffer("hello");
        long start1 = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            sb.append(i);
        }
        System.out.println("StringBuffer: " + (System.currentTimeMillis() - start1));


        // StringBuilder
        StringBuilder stringBuilder = new StringBuilder("hello");
        long start2 = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            stringBuilder.append(i);
        }
        System.out.println("StringBuilder: " + (System.currentTimeMillis() - start2));
    }
}
