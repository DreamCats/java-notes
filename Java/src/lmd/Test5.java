/**
 * @program JavaBooks
 * @description: 对每个元素执行operator指定的操作，并用操作结果来替换原来的元素
 * @author: mf
 * @create: 2020/08/04 22:08
 */

package lmd;

import java.util.ArrayList;
import java.util.Arrays;

public class Test5 {
    public static void main(String[] args) {
        // java 7
        // 假设有一个字符串列表，将其中所有长度大于3的元素转换成大写，其余元素不变。
        ArrayList<String> list = new ArrayList<>(Arrays.asList("I", "love", "you", "too"));
//        for (int i = 0; i < list.size(); i++) {
//            String str = list.get(i);
//            if (str.length() > 3)
//                list.set(i, str.toUpperCase());
//        }
//        System.out.println(list.toString());

        // java 8
        list.replaceAll(s -> {
            if (s.length() > 3)
                return s.toUpperCase();
            return s;
        });
        System.out.println(list.toString());
    }
}
