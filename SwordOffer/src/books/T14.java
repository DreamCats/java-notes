package books;

/**
 * @program JavaBooks
 * @description: 剪绳子
 * @author: mf
 * @create: 2019/08/25 15:55
 */

/*
给你一根长度为n的绳子，请把绳子剪成m段（m，n都是整数，n>1并且m>1），
每段绳子的长度记为k[0],k[1],...,k[m]。请问k[0]xk[1]x...xk[m]可能
的最大值乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别2、3、3的三段，
此时得到的最大乘积是18
 */
public class T14 {
    public static void main(String[] args) {
        int max = maxProductAfterCutting1(4);
        System.out.println(max);
        int max1 = maxProductAfterCutting2(4);
        System.out.println(max1);
    }

    // 动态规划
    private static int maxProductAfterCutting1(int length) {
        if (length < 2) return 0;
        if (length == 2) return 1;
        if (length == 3) return 2;
        int[] products = new int[length + 1];
        products[0] = 0;
        products[1] = 1; // 长度为2...
        products[2] = 2; // 长度为3...
        products[3] = 3; // 长度为4...

        int max = 0;
        for (int i = 4; i <= length; i++) {
            max = 0;
            for (int j = 1; j <= i / 2; j++) {
                int product = products[j] * products[i - j];
                max = max > product ? max : product;
                products[i] = max;
            }
        }
        max = products[length];

        return max;
    }

    // 贪婪算法
    public static int maxProductAfterCutting2(int length) {
        if (length < 2) return 0;
        if (length == 2) return 1;
        if (length == 3) return 2;
        // 尽可能剪3
        int timesOf3 = length / 3;
        // 如果==1的话， 我就变为最后剩4 ，然后变2x2
        if (length - timesOf3 * 3 == 1) timesOf3 -= 1;
        int timeOfs2 = (length - timesOf3 * 3) / 2;
        return (int)(Math.pow(3, timesOf3)) * (int)(Math.pow(2, timeOfs2));
    }
}
