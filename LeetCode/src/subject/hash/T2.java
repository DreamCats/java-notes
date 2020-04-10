/**
 * @program JavaBooks
 * @description: 只出现一次的数字
 * @author: mf
 * @create: 2020/04/10 20:34
 */

package subject.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * 输入: [2,2,1]
 * 输出: 1
 * 输入: [4,1,2,1,2]
 * 输出: 4
 */
public class T2 {
    /**
     * 亦或
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        if (nums.length == 1) return nums[0];
        int ans = nums[0];
        for (int i = 1; i < ans; i++) {
            ans ^= nums[i];
        }
        return ans;
    }

    /**
     * 哈希
     * @param nums
     * @return
     */
    public int singleNumber2(int[] nums) {
        if (nums.length == 1) return nums[0];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getKey() == 1) {
                return entry.getKey();
            }
        }
        return -1;
    }
}
