package normal;

import java.util.HashMap;

/**
 * @program JavaBooks
 * @description: 136.只出现一次的数字
 * @author: mf
 * @create: 2019/11/06 09:49
 */

/*
题目：https://leetcode-cn.com/problems/single-number/
类型：哈希
难度：easy
 */
/*
输入: [2,2,1]
输出: 1

输入: [4,1,2,1,2]
输出: 4
 */

/*
对于这道题，也可以异或
 */
public class SingleNumber {
    public static void main(String[] args) {
        int[] arr = {2,2,1};
//        System.out.println(singleNumber(arr));
        System.out.println(singleNumber2(arr));
    }

    /**
     * 哈希
     * @param nums
     * @return
     */
    private static int singleNumber(int[] nums) {
        if (nums.length == 1) return nums[0];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        for (Integer key : map.keySet()) {
            if (map.get(key) == 1) {
                return key;
            }
        }
        return 0;
    }

    /**
     * 异或
     * @param nums
     * @return
     */
    private static int singleNumber2(int[] nums) {
        if (nums.length == 1) return nums[0];
        int ans = nums[0];
        for (int i = 1; i < nums.length; i++) {
            ans ^= nums[i];
        }
        return ans;
    }
}
