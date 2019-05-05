import java.util.ArrayList;

/**
 * @program JavaBooks
 * @description: 实现StrStr方法
 * @author: mf
 * @create: 2019/04/27 13:11
 */

public class StrStr {
    public static void main(String[] args) {
        String haystack = "";
        String needle = "";
        SolutionStrStr solutionStrStr = new SolutionStrStr();
        int res = solutionStrStr.strStr(haystack, needle);
        System.out.println(res);


    }
}

class SolutionStrStr {
    public int strStr(String haystack, String needle) {

        if (haystack.length() == needle.length()) {
            if (haystack.equals(needle)) {
                return 0;
            } else {
                return -1;
            }
        }

        for (int i = 0; i < haystack.length(); i++) {
            int k = i;
            int j = 0;
            while (j < needle.length() && k < haystack.length() && haystack.charAt(k) == needle.charAt(j)) {
                j += 1;
                k += 1;
                if (j == needle.length()) {
                    return i;
                }
            }

        }
        if (needle.isEmpty()) {
            return 0;
        } else {
            return -1;
        }

    }
}