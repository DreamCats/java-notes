package normal;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @program JavaBooks
 * @description: 204.计算质数
 * @author: mf
 * @create: 2019/11/06 10:44
 */

/*
题目：https://leetcode-cn.com/problems/count-primes/
类型：筛选
难度：easy
 */
/*
输入: 10
输出: 4
解释: 小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。s
 */
public class CountPrimes {
    public static void main(String[] args) {
        System.out.println(countPrimes(10));
    }

    private static int countPrimes(int n) {
        if (n == 0 || n == 1) return 0;
        boolean[] num = new boolean[n + 1];
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (num[i] == false) {
                count++;
                for (int j = 2 * i; j < n + 1; j += i) {
                    num[j] = true;
                }
            }
        }
        return count;
    }
}
