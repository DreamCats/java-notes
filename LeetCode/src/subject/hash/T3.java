/**
 * @program JavaBooks
 * @description: 存在重复元素
 * @author: mf
 * @create: 2020/04/10 21:25
 */

package subject.hash;

import java.util.HashSet;

/**
 * 输入: [1,2,3,1]
 * 输出: true
 * 输入: [1,2,3,4]
 * 输出: false
 * 输入: [1,1,1,3,3,4,3,2,4,2]
 * 输出: true
 */
public class T3 {
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (!set.add(num)) {
                return false;
            }
        }
        return true;
    }
}
