/**
 * @program JavaBooks
 * @description: 零钱兑换
 * @author: mf
 * @create: 2020/04/15 17:35
 */

package subject.dp;

/**
 * 输入: coins = [1, 2, 5], amount = 11
 * 输出: 3
 * 解释: 11 = 5 + 5 + 1
 * 输入: coins = [2], amount = 3
 * 输出: -1
 */
public class T4 {
    public int coinChange(int[] coins, int amount) {
        // 初始化bp
        int[] dp = new int[amount + 1];
        for (int i = 0; i < amount; i++) {
            dp[i] = -1;
        }
        dp[0] = 0; // 金额0的最优解
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (i - coins[j] >= 0 && dp[i - coins[j]] != -1) {
                    if (dp[i] == -1 || dp[i] > dp[i - coins[j]] + 1) {
                        dp[i] = dp[i - coins[j]] + 1;
                    }
                }
            }
        }
        return dp[amount];
    }
}
