/**
 * @program JavaBooks
 * @description: 只出现一次的数字
 * @author: mf
 * @create: 2020/04/11 18:05
 */

package subject.bit;

/**
 * 输入: [2,2,1]
 * 输出: 1
 * 输入: [4,1,2,1,2]
 * 输出: 4
 */
public class T2 {
    public int singleNumber(int[] nums) {
        if (nums.length == 1) return nums[0];
        int ans = nums[0];
        for (int i = 1; i < nums.length; i++) {
            ans ^= nums[i];
        }
        return ans;
    }
}
