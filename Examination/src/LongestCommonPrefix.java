/**
 * @program JavaBooks
 * @description: 最长公共前缀
 * @author: mf
 * @create: 2019/04/10 23:00
 */

public class LongestCommonPrefix {
    public static void main(String[] args) {
        SolutionPrefix s = new SolutionPrefix();
        String[] strs = {"flower", "flow", "flight"};
        String res = s.longestCommonPrefix(strs);
        System.out.println(res);
    }
}



class SolutionPrefix {
    public String longestCommonPrefix(String[] strs) {
        String res = "";
        boolean d = false;
        if (strs.length == 0) return res;
        if (strs.length == 1) return  strs[0];
        for (int i = 0; i < strs[0].length(); i++) {
            for (int j = 1; j < strs.length; j++) {
                if (strs[j].length() == 0) return "";
                if (i == strs[j].length()) {
                    d = true;
                    break;
                }
                if (strs[0].charAt(i) == strs[j].charAt(i)) {
                    if (j == strs.length - 1) res += strs[0].charAt(i);
                }
                else {
                    d = true;
                    break;
                }
            }
            if (d) {
                break;
            }
        }
        return res;
    }
}