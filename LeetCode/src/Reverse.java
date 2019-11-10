/**
 * @program JavaBooks
 * @description: 7. 整数反转
 * @author: mf
 * @create: 2019/11/10 14:49
 */

/*
题目：https://leetcode-cn.com/problems/reverse-integer/
难度：easy
 */
/*
输入: -123
输出: -321
 */
public class Reverse {
    public static void main(String[] args) {
        System.out.println(reverse(-123));
    }
    public static int reverse(int x) {
        long ans = 0;
        while (x != 0) {
            ans = ans * 10 + x % 10;
            x /= 10;
        }
        if (ans > Integer.MAX_VALUE || ans < Integer.MIN_VALUE) {
            return 0;
        }
        return (int) ans;
    }
}
