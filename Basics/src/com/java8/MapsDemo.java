/**
 * @program JavaBooks
 * @description: map
 * @author: mf
 * @create: 2020/01/25 12:48
 */

package com.java8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MapsDemo {
    public static void main(String[] args) {
//        Map<Integer, String> map = new HashMap<>();
//        for (int i = 0; i < 10; i++) {
//            map.put(i, "val" + i);
//        }
//        map.forEach((id, val) -> System.out.println(val));

        Student s1 = new Student(1L, "肖战", 15);
        Student s2 = new Student(2L, "王一博", 15);
        Student s3 = new Student(3L, "杨紫", 17);
        Student s4 = new Student(4L, "李现", 17);
        List<Student> students = new ArrayList<>();
        students.add(s1);
        students.add(s2);
        students.add(s3);
        students.add(s4);

        List<String> names = students.stream().map(s -> "名字:" + s.getName()).collect(Collectors.toList());
        names.forEach(a -> System.out.println(a));
    }
}
