/**
 * @program JavaBooks
 * @description: 371. 两整数之和
 * @author: mf
 * @create: 2019/11/09 13:58
 */

/*
题目：https://leetcode-cn.com/problems/sum-of-two-integers/
难度：easy
 */

/*
不使用运算符 + 和 - ​​​​​​​，计算两整数 ​​​​​​​a 、b ​​​​​​​之和。
输入: a = 1, b = 2
输出: 3
 */
public class GetSum {
    public static void main(String[] args) {
        System.out.println(getSum(5,3));
    }

    private static int getSum(int a, int b) {
        return b == 0 ? a : getSum(a ^ b, (a & b) << 1);
    }
}
