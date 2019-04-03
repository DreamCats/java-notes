import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @program JavaBooks
 * @description: 两个数求和
 * @author: mf
 * @create: 2019/04/03 17:02
 */

public class TwoSum {
    public static void main(String[] args) {
        int[] nums = {2,7,11,15};
        int target = 26;
        Solution s = new Solution();
//        s.twoSum(nums, target);
        int[] res = s.twoSum(nums, target);
        System.out.println(Arrays.toString(res));
    }
}

class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
//                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        System.out.println(map.containsKey(30));
//        throw new IllegalArgumentException("No two sum solution");
        return new int[] {1,2};
    }
}
