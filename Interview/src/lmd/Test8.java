/**
 * @program JavaBooks
 * @description: reduce
 * @author: mf
 * @create: 2020/08/05 00:22
 */

package lmd;

import java.util.stream.Stream;

public class Test8 {
    public static void main(String[] args) {
        Stream<String> stream = Stream.of("I", "love", "you", "too");
//        Optional<String> s = stream.reduce((s1, s2) -> s1.length() >= s2.length() ? s1 : s2);
//        System.out.println(s.get());
        Integer reduce = stream.reduce(0, (sum, s) -> sum + s.length(), (a, b) -> a + b);
        System.out.println(reduce);
    }
}
