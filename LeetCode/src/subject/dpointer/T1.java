/**
 * @program JavaBooks
 * @description: 删除排序数组中的重复项
 * @author: mf
 * @create: 2020/03/13 12:05
 */

package subject.dpointer;

/**
 * 给定数组 nums = [1,1,2],
 * 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
 */
public class T1 {
    public int removeDuplicates(int[] nums) {
        int p = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[p] != nums[i]) {
                nums[++p] = nums[i];
            }
        }
        return p + 1;
    }
}
