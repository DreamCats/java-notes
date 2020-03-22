/**
 * @program JavaBooks
 * @description: 移除元素
 * @author: mf
 * @create: 2020/03/22 14:40
 */

package subject.dpointer;

/**
 * 给定 nums = [3,2,2,3], val = 3,
 * 函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。
 */
public class T2 {
    public int removeElement(int[] nums, int val) {
        int p = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[p++] = nums[i];
            }
        }
        return p;
    }
}
