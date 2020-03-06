package books;

/**
 * @program JavaBooks
 * @description: 青蛙跳台阶问题 & 斐波那契数列
 * @author: mf
 * @create: 2019/08/22 15:25
 */

/*
求斐波那契数列的第n项
写一个函数，输入n，求斐波那契数列的第n项，
斐波那契数列的定义如下：
        0             n=0
f(n)=   1             n=1
        f(n-1)+f(n-2) n>1
 */




/*
一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶
求该青蛙跳上一个n级的台阶总共有多少种跳法。
 */
public class T10 {
    public static void main(String[] args) {
        int res = frog(5);
        System.out.println(res);
        int res1 = frog2(5);
        System.out.println(res1);

        System.out.println(fibonacci2(10));
    }


    /*
    青蛙
     */
    // 递归
    public static int frog(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 2;
        return  frog(n - 1) + frog(n - 2);
    }

    // 从下往上for循环
    public static int frog2(int n) {
        int[] res = {0 , 1, 2};
        if (n < 3) return res[n];

        int one = 1;
        int two = 2;
        int sum = 0;
        for (int i = 3; i <= n; i++) {
            sum = one + two;
            one = two;
            two = sum;
        }
        return sum;
    }

    /*
    变态青蛙
     */
    // 递归 2*(n - 1) 次方
    public static int btFrog(int n) {
        if (n == 1 || n == 2) return n;
        return 2 * frog(n - 1);
    }
    //  2*(n - 1) 次方
    public static int btFrog2(int n) {
        return 1<<(n - 1);
    }

    /*
    斐波那契数列
     */

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
