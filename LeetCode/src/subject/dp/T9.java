/**
 * @program JavaBooks
 * @description: 完全平方数
 * @author: mf
 * @create: 2020/04/18 13:38
 */

package subject.dp;

import java.util.Arrays;

public class T9 {
    public int numSquares(int n) {
        int[] dp = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        int maxSquareIndex = (int) (Math.sqrt(n) + 1);
        int[] squareNum = new int[maxSquareIndex];
        for (int i = 1; i < maxSquareIndex; i++) {
            squareNum[i] = i * i;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j < maxSquareIndex; j++) {
                if (i < squareNum[j]) break;
                dp[i] = Math.min(dp[i], dp[i-squareNum[j]] + 1);
            }
        }
        return dp[n];
    }
}
