package normal;

/**
 * @program JavaBooks
 * @description: 326. 3的幂
 * @author: mf
 * @create: 2019/11/10 21:42
 */

/*
题目：https://leetcode-cn.com/problems/power-of-three/
难度：easy
 */
/*
输入: 27
输出: true
输入: 0
输出: false
 */
public class IsPowerOfThree {
    public static void main(String[] args) {
        System.out.println(isPowerOfThree(45));
    }

    public static boolean isPowerOfThree(int n) {
        while (n > 1) {
            if (n % 3 != 0) {
                return false;
            }
            n /= 3;
        }
        return true;
    }
}
