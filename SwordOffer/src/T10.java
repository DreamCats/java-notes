/**
 * @program JavaBooks
 * @description: 青蛙跳台阶问题
 * @author: mf
 * @create: 2019/08/22 15:25
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
    }
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
}
