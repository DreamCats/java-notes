/**
 * @program JavaBooks
 * @description: 三角形最小路径和
 * @author: mf
 * @create: 2020/04/15 19:04
 */

package subject.dp;

import java.util.List;

/**
 * [
 *      [2],
 *     [3,4],
 *    [6,5,7],
 *   [4,1,8,3]
 * ]
 */
public class T5 {
    public int minimumTotal(List<List<Integer>> triangle){
        if (triangle.size() == 0) return 0;
        int row = triangle.size();
        int[][] dp = new int[row][triangle.get(row - 1).size()];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                dp[i][j] = 0;
            }
        }
        for (int i = 0; i < triangle.get(row - 1).size(); i++) {
            dp[row - 1][i] = triangle.get(row - 1).get(i);
        }
        for (int i = row - 2; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                dp[i][j] = Math.min(dp[i+1][j], dp[i+1][j+1]) + triangle.get(i).get(j);
            }
        }
        return dp[0][0];
    }
}
