/**
 * @program JavaBooks
 * @description: 流的一些例子
 * @author: mf
 * @create: 2020/01/23 17:39
 */

package com.java8;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class streamDemo {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("ddd3", "bbb", "ccc", "www", "eee", "ddd2", "bbb");

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

        // match
        System.out.println("匹配：");
        boolean anyStartsWithA =
                strings
                .stream()
                .anyMatch((s) -> s.startsWith("d"));
        System.out.println(anyStartsWithA);
        boolean allStartsWithA =
                strings
                .stream()
                .allMatch((s) -> s.startsWith("d"));
        System.out.println(allStartsWithA);
        boolean noneStartsWithA =
                strings
                .stream()
                .noneMatch((s) -> s.startsWith("a"));
        System.out.println(noneStartsWithA);

        System.out.println("计数：");
        long count =
                strings
                .stream()
                .filter((s) -> s.startsWith("d"))
                .count();
        System.out.println(count);

        //规约
        System.out.println("规约");
        Optional<String> reduce = strings
                .stream()
                .sorted()
                .reduce((s1, s2) -> s1 + "#" + s2);
        reduce.ifPresent(System.out::println);

        // 去重
        System.out.println("去重");
        strings.stream().distinct().forEach(s -> System.out.println(s));

        // 字符串拼接
        String reduce1 = Stream.of("A", "B", "C").reduce("", String::concat);
        System.out.println(reduce1);
        // 求最小值
        Double reduce2 = Stream.of(-1.5, 1.0, -3.0).reduce(Double.MAX_VALUE, Double::min);
        System.out.println(reduce2);
        // 求和
        Integer reduce3 = Stream.of(1, 2, 3, 4).reduce(0, Integer::sum);
        System.out.println(reduce3);
        // 求和
        Optional<Integer> reduce4 = Stream.of(1, 2, 3, 4).reduce(Integer::sum);
        System.out.println(reduce4.get());


    }
}
