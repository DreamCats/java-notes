/**
 * @program JavaBooks
 * @description: Lambda表达式
 * @author: mf
 * @create: 2020/01/23 16:20
 */

package com.stream;

import java.util.*;

public class LambdaDemo {
    public static void main(String[] args) {
        // 老版本的排序
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
        System.out.println(names);
//        Collections.sort(names, new Comparator<String>() {
//            @Override
//            public int compare(String a, String b) {
//                return b.compareTo(a);
//            }
//        });

        // 新版本 lambda表达式：

//        Collections.sort(names, (String a, String b) -> {
//            return b.compareTo(a);
//        });
//        Collections.sort(names, (String a, String b) -> b.compareTo(a));
        names.sort((String a, String b) -> b.compareTo(a));
        System.out.println(names);
    }
}
