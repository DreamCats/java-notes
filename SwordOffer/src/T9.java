/**
 * @program JavaBooks
 * @description: 斐波那契数列
 * @author: mf
 * @create: 2019/08/22 14:22
 */

/*
求斐波那契数列的第n项
写一个函数，输入n，求斐波那契数列的第n项，
斐波那契数列的定义如下：
        0             n=0
f(n)=   1             n=1
        f(n-1)+f(n-2) n>1
 */
public class T9 {
    public static void main(String[] args) {
        int res = fibonacci(10);
        System.out.println(res);
        int res1 = fibonacci2(10);
        System.out.println(res1);
    }
    // 递归 缺点太慢
    public static int fibonacci(int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    // 从下向上循环
    public static int fibonacci2(int n) {
        int[] result = {0, 1};
        if (n < 2) return result[n];

        int fiOne = 0;
        int fiTwo = 1;
        int fiRes = 0;
        for (int i = 2; i <= n; i++) {
            fiRes = fiOne + fiTwo;
            fiOne = fiTwo;
            fiTwo = fiRes;
        }
        return fiRes;
    }
}
