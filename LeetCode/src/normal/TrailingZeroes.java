package normal;

/**
 * @program JavaBooks
 * @description: 172. 阶乘后的零
 * @author: mf
 * @create: 2019/11/10 20:20
 */

/*
题目：https://leetcode-cn.com/problems/factorial-trailing-zeroes/comments/
难度：easy
 */



public class TrailingZeroes {
    public static void main(String[] args) {
        System.out.println(trailingZeroes(10));
    }

    public static int trailingZeroes(int n) {
        int count = 0;
        while (n >= 5) {
            count += n / 5;
            n /= 5;
        }
        return count;
    }
}
