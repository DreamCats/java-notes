package normal;

/**
 * @program JavaBooks
 * @description: 191. 位1的个数
 * @author: mf
 * @create: 2019/11/09 12:35
 */

/*
题目：https://leetcode-cn.com/problems/number-of-1-bits/
难度：easy
 */

public class HammingWeight {
    public static void main(String[] args) {
        System.out.println(hammingWeight(15));
    }
    public static int hammingWeight(int n) {
        int ans = 0;
        while (n != 0) {
            n &= n - 1;
            ans++;
        }
        return ans;
    }
}
