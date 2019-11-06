import java.util.HashMap;

/**
 * @program JavaBooks
 * @description: 387.字符串中的第一个唯一字符
 * @author: mf
 * @create: 2019/11/06 16:59
 */

/*
题目：https://leetcode-cn.com/problems/first-unique-character-in-a-string/
类型：
难度：easy
 */

/*
s = "leetcode"
返回 0.

s = "loveleetcode",
返回 2.
 */
public class FirstUniqChar {
    public static void main(String[] args) {
        String s = "leetcode";
        String s1 = "loveleetcode";
        System.out.println(firstUniqChar(s1));
    }

    private static int firstUniqChar(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (int i = 0; i < s.length(); i++) {
            if (map.get(s.charAt(i)) == 1) {
                return i;
            }
        }
        return -1;
    }
}
