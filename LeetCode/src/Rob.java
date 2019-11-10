import com.sun.org.apache.bcel.internal.generic.RET;

/**
 * @program JavaBooks
 * @description: 198. 打家劫舍
 * @author: mf
 * @create: 2019/11/10 13:27
 */

/*
题目：https://leetcode-cn.com/problems/house-robber/
类型：动态规划
难度：easy
 */

/*
输入: [1,2,3,1]
输出: 4
解释: 偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
     偷窃到的最高金额 = 1 + 3 = 4 。
 */
public class Rob {
    public static void main(String[] args) {
        int[] nums = {1,2,3,1};
        System.out.println(rob(nums));
    }
    public static int rob(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        int pre3 = 0, pre2 = 0, pre1 = 0;
        for (int i = 0; i < nums.length; i++) {
            int cur = Math.max(pre2, pre3) + nums[i];
            pre3 = pre2;
            pre2 = pre1;
            pre1 = cur;
        }
        return Math.max(pre1, pre2);
    }
}
