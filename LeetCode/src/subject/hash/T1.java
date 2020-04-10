/**
 * @program JavaBooks
 * @description: 两数之和
 * @author: mf
 * @create: 2020/04/10 20:11
 */

package subject.hash;

import java.util.HashMap;

/**
 * 给定 nums = [2, 7, 11, 15], target = 9
 *
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 */
public class T1 {
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length == 0) return null;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int error = target - nums[i];
            if (map.containsKey(error)) {
                return new int[] {map.get(error), i};
            } else {
                map.put(nums[i], i);
            }
        }
        return null;
    }
}
