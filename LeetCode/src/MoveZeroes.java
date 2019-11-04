/**
 * @program JavaBooks
 * @description: 283.移动零
 * @author: mf
 * @create: 2019/11/04 18:29
 */

import java.util.Arrays;

/**
 * 题目：https://leetcode-cn.com/problems/move-zeroes/
 * 难度：easy
 * 类型：数组
 */

/*
输入: [0,1,0,3,12]
输出: [1,3,12,0,0]
 */
public class MoveZeroes {
    public static void main(String[] args) {
        int[] nums = {0,1,0,3,12};
        moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }

    private static void moveZeroes(int[] nums) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[index++] = nums[i];
            }
        }
        for (int i = index; i < nums.length; i++) {
            nums[i] = 0;
        }
    }
}
