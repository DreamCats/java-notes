/**
 * @program JavaBooks
 * @description: 217.存在重复元素
 * @author: mf
 * @create: 2019/11/04 16:24
 */

import java.util.HashMap;

/**
 * 题目：https://leetcode-cn.com/problems/contains-duplicate/
 * 难度：easy
 * 类型：数组
 */
/*
输入: [1,2,3,1]
输出: true

输入: [1,2,3,4]
输出: false
 */
public class ContainsDuplicate {
    public static void main(String[] args) {
        int[] nums = {1,2,3,1};
        int[] nums1 = {1,2,3,4};
        int[] nums2 = {0,4,5,0,3,6};
        int[] nums3 = {2,14,18,22,22};
        System.out.println(containsDuplicate(nums1));
    }
    public static boolean containsDuplicate(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                return true;
            } else {
                map.put(num, 1);
            }
        }
        return false;
    }
}
