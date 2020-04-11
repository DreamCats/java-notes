/**
 * @program JavaBooks
 * @description: 最大子序和
 * @author: mf
 * @create: 2020/04/11 19:26
 */

package subject.dp;

/**
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 */
public class T0 {
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int max = nums[0];
        int ans = nums[0];
        for (int i = 1; i < nums.length; i++) {
            max = Math.max(max + nums[i], nums[i]);
            ans = Math.max(max, ans);
        }
        return ans;
    }
}
