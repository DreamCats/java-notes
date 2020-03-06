package normal;

/**
 * @program JavaBooks
 * @description: 28. 实现 strStr()
 * @author: mf
 * @create: 2019/11/09 18:15
 */

/*
题目：https://leetcode-cn.com/problems/implement-strstr/
难度：easy
 */

public class StrStr {
    public static void main(String[] args) {
        String haystack = "aaa";
        String needle = "aaaa";
        System.out.println(strStr(haystack, needle));

    }
    public static int strStr(String haystack, String needle) {
        if (needle.equals("")) return 0;
        char[] sChar = haystack.toCharArray();
        char[] nChar = needle.toCharArray();
        int s1 = 0, s2 = 0;
        int e1 = sChar.length - 1, e2 = nChar.length - 1;
        while (s1 <= e1) {
            if (sChar[s1] == nChar[s2]) {
                int k = s1;
                while (k <= e1 && s2 <= e2) {
                    if (sChar[k] == nChar[s2]) {
                        k++;
                        s2++;
                    } else {
                        s2 = 0;
                        break;
                    }
                }
                if (k >= e1 && s2 <= e2) return -1;
                if (s2 > e2) return s1;
            }
            s1++;
        }
        return -1;
    }
}
