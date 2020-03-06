package books;

import java.util.HashMap;
import java.util.Map;

/**
 * @program JavaBooks
 * @description: 第一个只出现一次的字符
 * @author: mf
 * @create: 2019/10/03 09:39
 */

/*
在字符串中找出第一个只出现一次的字符。如输入"abaccdeff"，则输出b
 */
public class T50 {
    public static void main(String[] args) {
        Integer first = FirstNotRepeatingChar("abaccdeff");
        System.out.println(first);
    }
    // 哈希表
    private static int FirstNotRepeatingChar(String str) {
        if (str == null || str.length() == 0) return -1;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            if (map.containsKey(str.charAt(i))) {
                map.put(str.charAt(i), map.get(str.charAt(i)) + 1);
            } else {
                map.put(str.charAt(i), 1);
            }
        }
        for (int i = 0; i < str.length(); i++) {
            if (map.get(str.charAt(i)) == 1) {
                return i;
            }
        }
        return -1;
    }
}
