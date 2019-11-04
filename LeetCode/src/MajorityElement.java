/**
 * @program JavaBooks
 * @description: 169.求众数
 * @author: mf
 * @create: 2019/11/04 15:25
 */

/**
 * 题目：https://leetcode-cn.com/problems/majority-element/
 * 难度：easy
 */

/*
输入: [3,2,3]
输出: 3

输入: [2,2,1,1,1,2,2]
输出: 2
 */
public class MajorityElement {
    public static void main(String[] args) {
        int[] arr = {3,2,3};
        int[] arr1 = {2,2,1,1,1,2,2};
        System.out.println(majorityElement(arr1));
    }

    private static int majorityElement(int[] nums) {
        int count = 1;
        int ans = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == ans) {
                count++;
            } else {
                count--;
                if (count == 0) {
                    ans = nums[i + 1];
                }
            }
        }
        return ans;
    }
}
