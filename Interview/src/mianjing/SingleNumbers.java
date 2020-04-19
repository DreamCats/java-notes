/**
 * @program JavaBooks
 * @description: I. 数组中数字出现的次数
 * @author: mf
 * @create: 2020/04/19 15:41
 */

package mianjing;

/**
 * 输入：nums = [4,1,4,6]
 * 输出：[1,6] 或 [6,1]
 *
 * 输入：nums = [1,2,10,4,1,4,3,3]
 * 输出：[2,10] 或 [10,2]
 */
public class SingleNumbers {
    public int[] singleNumbers(int[] nums) {
        int xorNumber = nums[0];
        for (int i = 1; i < nums.length; i++) {
            xorNumber ^= nums[i];
        }
        int onePosition = xorNumber & (-xorNumber);
        int ans1 = 0, ans2 = 0;
        for (int i = 0; i < nums.length; i++) {
            if ((nums[i] & onePosition) == onePosition) {
                ans1 ^= nums[i];
            } else {
                ans2 ^= nums[i];
            }
        }
        return new int[] {ans1^0, ans2^0};
    }
}
