package web; /**
 * @program LeetNiu
 * @description: 斐波那契数列
 * @author: mf
 * @create: 2020/01/09 13:28
 */

/**
 *大家都知道斐波那契数列，现在要求输入一个整数n，
 * 请你输出斐波那契数列的第n项（从0开始，第0项为0）。
 * n<=39
 * 思路：
 * 递归，重复项太多，自底向上
 */
public class T7 {
    public int Fibonacci(int n) {
        // 条件
        if (n <= 1) return n;
        int pre2 = 0, pre1 = 1;
        int f = 0;
        for (int i = 2; i <= n; i++) {
            f = pre2 + pre1;
            pre2 = pre1;
            pre1 = f;
        }
        return f;
    }
}
