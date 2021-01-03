/**
 * @program JavaBooks
 * @description: 带参数的简写
 * @author: mf
 * @create: 2020/08/04 21:14
 */

package lmd;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Test2 {
    public static void main(String[] args) {
        // jdl7
        List<String> list = Arrays.asList("I", "love", "you", "too");
        int[] array = list.stream().mapToInt(Integer::valueOf).toArray();
        // 按长度排序
//        Collections.sort(list, new Comparator<String>() { // 接口 匿名
//            @Override
//            public int compare(String o1, String o2) {
//                if (o1 == null)
//                    return -1;
//                if (o2 == null)
//                    return 1;
//                return o1.length() - o2.length();
//            }
//        });
//        System.out.println(list.toString());

        // java 8
        Collections.sort(list, (o1, o2) -> o1.length() - o2.length());
        System.out.println(list.toString());
    }
}
