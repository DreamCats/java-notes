/**
 * @program JavaBooks
 * @description: 14. 最长公共前缀
 * @author: mf
 * @create: 2019/11/10 16:09
 */

public class LongestCommonPrefix {
    public static void main(String[] args) {
        String[] strs = {"flower","flow","flight"};
        System.out.println(longestCommonPrefix(strs));
    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        String str = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while(strs[i].indexOf(str) != 0) {
                str = str.substring(0, str.length() - 1);
            }
        }
        return str;
    }

}
