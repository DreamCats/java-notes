/**
 * @program JavaBooks
 * @description: 多数元素
 * @author: mf
 * @create: 2020/04/11 17:49
 */

package subject.bit;

import java.util.HashMap;
import java.util.Map;

/**
 * 输入: [3,2,3]
 * 输出: 3
 * 输入: [2,2,1,1,1,2,2]
 * 输出: 2
 */
public class T0 {
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>((nums.length + 1) / 2);
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
            if (nums.length / 2 < map.get(num)) {
                return num;
            }
        }
        return 0;
    }
}
