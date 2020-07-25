/**
 * @program JavaBooks
 * @description: 牛客
 * @author: mf
 * @create: 2020/07/24 22:24
 */

package Test;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        long[] dp = new long[x + 1];
        dp[1] = 3;
        dp[2] = 9;
        for (int i = 3; i <= x; i++) {
            dp[i] = 2 * dp[i - 1] + dp[i - 2];
        }
        System.out.println(dp[x]);
    }
}
