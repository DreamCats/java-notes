/**
 * @program JavaBooks
 * @description: map
 * @author: mf
 * @create: 2020/01/25 12:48
 */

package com.java8;

import java.util.HashMap;
import java.util.Map;

public class MapsDemo {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            map.put(i, "val" + i);
        }
        map.forEach((id, val) -> System.out.println(val));
    }
}
