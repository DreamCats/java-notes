/**
 * @program JavaBooks
 * @description: 变态跳台阶
 * @author: mf
 * @create: 2019/08/23 09:34
 */

/*
一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶，更可以跳上n级台阶，求该青蛙
跳上一个n级的台阶总共有多少跳法
 */
public class T11 {

    public static void main(String[] args) {
        System.out.println(frog(5));
        System.out.println(frog2(5));
    }

    public static int frog(int n) {
        if (n == 1 || n == 2) return n;
        return 2 * frog(n - 1);
    }

    public static int frog2(int n) {
        return 1<<(n - 1);
    }
}
