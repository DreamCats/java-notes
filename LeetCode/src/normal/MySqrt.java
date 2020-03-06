package normal;

/**
 * @program JavaBooks
 * @description: 69. x 的平方根
 * @author: mf
 * @create: 2019/11/09 09:38
 */

/*
题目：https://leetcode-cn.com/problems/sqrtx/
难度：easy
 */
/*
输入: 4
输出: 2
输入: 8
输出: 2
说明: 8 的平方根是 2.82842...,
     由于返回类型是整数，小数部分将被舍去。
 */
public class MySqrt {
    public static void main(String[] args) {
        System.out.println(mySqrt(8));
    }
    public static int mySqrt(int x) {
        if (x <= 1) return x;
        int l = 1, h = x;
        while (l <= h) {
            int mid = l + (h - l) / 2;
            int sqrt = x / mid;
            if (sqrt == mid ) return sqrt;
            else if (mid > sqrt) h = mid - 1;
            else l = mid + 1;
        }
        return h;
    }
}
