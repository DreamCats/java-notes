package web; /**
 * @program LeetNiu
 * @description: 剪绳子
 * @author: mf
 * @create: 2020/01/17 23:47
 */

/**
 * 给你一根长度为n的绳子，请把绳子剪成整数长的m段（m、n都是整数，n>1并且m>1），每段绳子的长度记为k[0],k[1],...,k[m]。
 * 请问k[0]xk[1]x...xk[m]可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 */
public class T67 {
    // 动态规划
    public int cutRope(int target) {
        if (target < 2) return 0;
        if (target == 2) return 1;
        if (target == 3) return 2;
        int[] products = new int[target + 1];
        products[0] = 0;
        products[1] = 1; // 长度为2...
        products[2] = 2; // 长度为3...
        products[3] = 3; // 长度为4...
        int max = 0;
        for (int i = 4; i <= target; i++) {
            max = 0;
            for (int j = 1; j <= i / 2; j++) {
                int product = products[j] * products[i - j];
                max = max > product ? max : product;
                products[i] = max;
            }
        }
        max = products[target];
        return max;
    }
}
