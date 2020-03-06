package normal; /**
 * @program JavaBooks
 * @description: 两数之和
 * @author: mf
 * @create: 2019/10/16 23:47
 */

import java.util.Arrays;
import java.util.HashMap;

/**
 * 给定一个整数数组 nums 和一个目标值 target，
 * 请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * 给定 nums = [2, 7, 11, 15],
 * target = 9
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 */

/**
 * 方法
 *  1。 暴力法
 *  2。 两遍哈希
 *  3。 一遍哈希。
 */

// 时间复杂度：O(n)
// 空间复杂度：O(n)
public class TwoSum {
    public static void main(String[] args) {
        int[] arr = {2, 7, 11, 15};
        int target = 9;
        int[] res = twoSum(arr, target);
        System.out.println(Arrays.toString(res));
    }

    // 一遍哈希
    private static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int error = target - nums[i];
            if (map.containsKey(error)) {
                return new int[] {map.get(error), i};
            } else {
                map.put(nums[i], i);
            }
        }
        return new int[0];
    }
}
