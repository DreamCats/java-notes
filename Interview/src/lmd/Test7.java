/**
 * @program JavaBooks
 * @description: steam
 * @author: mf
 * @create: 2020/08/04 22:15
 */

package lmd;

import java.util.stream.Stream;

public class Test7 {
    public static void main(String[] args) {
        // forEach
        Stream<String> stream = Stream.of("I", "love", "you", "too");
//        stream.forEach(s -> System.out.println(s));
//        stream.filter(s -> s.length() == 3).forEach(s -> System.out.println(s));
        Stream<String> stream1 = Stream.of("I", "love", "you", "too", "too");

        stream1.distinct().forEach(s -> System.out.println(s));

        stream.map(s -> s.toUpperCase()).forEach(s -> System.out.println(s));
    }

}
