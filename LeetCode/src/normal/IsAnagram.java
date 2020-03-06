package normal;

import java.util.HashMap;

/**
 * @program JavaBooks
 * @description: 242.有效的字母异位词
 * @author: mf
 * @create: 2019/11/06 15:19
 */

/*
题目：https://leetcode-cn.com/problems/valid-anagram/
类型：哈希等
难度：easy
 */

/*
输入: s = "anagram", t = "nagaram"
输出: true
输入: s = "rat", t = "car"
输出: false
 */
public class IsAnagram {
    public static void main(String[] args) {
        String s = "anagram";
        String t = "nagaram";
        System.out.println(isAnagram(s, t));
        System.out.println(isAnagram2(s, t));
    }

    /**
     * 普通字符串方法
     * @param s
     * @param t
     * @return
     */
    private static boolean isAnagram(String s, String t) {
        int[] sCount = new int[26];
        int[] tCount = new int[26];
        for (char ch : s.toCharArray()) {
            sCount[ch - 'a']++;
        }
        for (char c : t.toCharArray()) {
            tCount[c - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (sCount[i] != tCount[i]) {
                return  false;
            }
        }
        return true;
    }

    /**
     * 哈希
     * @param s
     * @param t
     * @return
     */
    private static boolean isAnagram2(String s, String t) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (char c : t.toCharArray()) {
            Integer count = map.get(c);
            if (count == null){
                return false;
            } else if (count > 1) {
                map.put(c, count - 1);
            } else {
                map.remove(c);
            }
        }
        return map.isEmpty();
    }
}
