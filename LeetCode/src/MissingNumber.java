/**
 * @program JavaBooks
 * @description: 268.缺失数字
 * @author: mf
 * @create: 2019/11/04 17:59
 */

/**
 * 题目：https://leetcode-cn.com/problems/missing-number/
 * 难度：easy
 * 类型：数组
 */

/*
输入: [3,0,1]
输出: 2

输入: [9,6,4,2,3,5,7,0,1]
输出: 8
 */
public class MissingNumber {
    public static void main(String[] args) {
        int[] nums = {3, 0 , 1};
        int[] nums1 = {9,6,4,2,3,5,7,0,1};
        System.out.println(missingNumber(nums1));
    }

    private static int missingNumber(int[] nums) {
        int ans = nums.length;
        for (int i = 0; i < nums.length; i++) {
            ans ^= nums[i];
            ans ^= i;
        }
        return ans;
    }
}
