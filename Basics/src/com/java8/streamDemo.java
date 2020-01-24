/**
 * @program JavaBooks
 * @description: 流的一些例子
 * @author: mf
 * @create: 2020/01/23 17:39
 */

package com.java8;

import java.util.Arrays;
import java.util.List;

public class streamDemo {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("ddd", "bbb", "ccc", "www", "eee", "ddd2");

        // 过滤
        strings
                .stream()
                .filter((s) -> s.startsWith("d"))
                .forEach(System.out::println);
    }
}
