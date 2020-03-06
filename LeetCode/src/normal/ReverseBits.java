package normal;

/**
 * @program JavaBooks
 * @description: 190. 颠倒二进制位
 * @author: mf
 * @create: 2019/11/09 11:08
 */

/*
题目：https://leetcode-cn.com/problems/reverse-bits/
难度:easy
 */
public class ReverseBits {
    public static void main(String[] args) {
        System.out.println(reverseBits(43261596));
    }
    private static int reverseBits(int n) {
        int a = 0;
        for (int i = 0; i < 32; i++) {
            a += ((1 & (n >> i)) << (32 - 1- i));
        }
        return a;
    }
}
