/**
 * @program JavaBooks
 * @description: 移动零
 * @author: mf
 * @create: 2020/04/09 17:40
 */

package subject.dpointer;

/**
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 */
public class T8 {
    public void moveZeroes(int[] nums) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) nums[index++] = nums[i];
        }
        for (int i = index; i < nums.length; i++) {
            nums[i] = 0;
        }
    }
}
