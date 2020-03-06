package normal;

/**
 * @program JavaBooks
 * @description: 171. Excel表列序号
 * @author: mf
 * @create: 2019/11/10 19:27
 */

/*
题目：https://leetcode-cn.com/problems/excel-sheet-column-number/
难度：easy
 */

/*
    A -> 1
    B -> 2
    C -> 3
    ...
    Z -> 26
    AA -> 27
    AB -> 28
    ...
 */
//        for (int i = 0; i < n; i++) {
//            int value = s.charAt(i) - 'A' + 1;
//            value = (int) (Math.pow(26, n - i - 1)) * value;
//            ans += value;
//        }
public class TitleToNumber {
    public static void main(String[] args) {
        String s = "AAB";
        System.out.println(titleToNumber(s));
    }

    public static int titleToNumber(String s) {
        int ans = 0;
        int n = s.length();
        int m = 26;
        for (int i = 0; i < n; i++) {
            int value = s.charAt(i) - 'A' + 1;
            int k = n - i - 1;
            int temp = 1;
            while (k > 0) {
                temp = temp * m;
                k--;
            }
            ans = ans + temp * value;
        }
        return ans;
    }
}
