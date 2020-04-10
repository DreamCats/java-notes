/**
 * @program JavaBooks
 * @description: 存在重复元素 II
 * @author: mf
 * @create: 2020/04/10 21:54
 */

package subject.hash;

import java.util.HashSet;

/**
 * 输入: nums = [1,2,3,1], k = 3
 * 输出: true
 * 输入: nums = [1,0,1,1], k = 1
 * 输出: true
 * 输入: nums = [1,2,3,1,2,3], k = 2
 * 输出: false
 */
public class T4 {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) return true;
            set.add(nums[i]);
            // 维护这个滑动窗口欧
            if (set.size() > k) {
                set.remove(nums[i - k]);
            }
        }
        return false;
    }
}
