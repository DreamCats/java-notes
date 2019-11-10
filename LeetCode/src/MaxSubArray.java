/**
 * @program JavaBooks
 * @description: 53. 最大子序和
 * @author: mf
 * @create: 2019/11/10 10:37
 */

/*
题目：https://leetcode-cn.com/problems/maximum-subarray/
类型：动态规划
难度：easy
 */

/*
输入: [-2,1,-3,4,-1,2,1,-5,4],
输出: 6
解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 */
public class MaxSubArray {
    public static void main(String[] args) {
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArray(nums));
    }

    public static int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int max = nums[0]; // 记录包含arr[i]的连续子数组的和的最大值
        int ans = nums[0]; // 记录当前所有子数组的和的最大值
        for (int i = 1; i < nums.length; i++) {
            max = Math.max(max + nums[i], nums[i]);
            ans = Math.max(max, ans);
        }
        return ans;
    }
}
