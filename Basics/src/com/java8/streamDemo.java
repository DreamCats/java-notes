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
        List<String> strings = Arrays.asList("ddd3", "bbb", "ccc", "www", "eee", "ddd2");

        System.out.println("过滤：");
        // 过滤
        strings
                .stream()
                .filter((s) -> s.startsWith("d"))
                .forEach(System.out::println);

        System.out.println("排序：");
        // 排序
        strings
                .stream()
                .sorted()
                .filter((s) -> s.startsWith("d"))
                .forEach(System.out::println);

        System.out.println("映射：");
        strings
                .stream()
                .map(String::toUpperCase)
                .sorted()
                .forEach(System.out::println);
    }
}
