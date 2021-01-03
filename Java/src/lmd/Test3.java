/**
 * @program JavaBooks
 * @description: foreach
 * @author: mf
 * @create: 2020/08/04 21:24
 */

package lmd;

import java.util.Arrays;
import java.util.List;

public class Test3 {
    public static void main(String[] args) {
        // java 7 增强for
        List<String> list = Arrays.asList("I", "love", "you", "too");
        for (String s : list) {
            System.out.println(s);
        }

        // java 8
        list.forEach(System.out::println);

        // java 7
        for (String s : list) {
            if (s.length() > 3)
                System.out.println(s);
        }

        // java 8
        list.forEach(s -> {
            if (s.length() > 3)
                System.out.println(s);
        });
    }
}
