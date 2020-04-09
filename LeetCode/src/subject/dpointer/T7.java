/**
 * @program JavaBooks
 * @description: 两数之和 II - 输入有序数组
 * @author: mf
 * @create: 2020/04/09 17:37
 */

package subject.dpointer;

/**
 * 输入: numbers = [2, 7, 11, 15], target = 9
 * 输出: [1,2]
 * 解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
 *
 */
public class T7 {
    public int[] twoSum(int[] numbers, int target) {
        // 双指针
        int l = 0, r = numbers.length - 1;
        while (l < r) {
            if (numbers[l] + numbers[r] == target) return new int[] {l+1, r+1};
            else if (numbers[l] + numbers[r] > target) r--;
            else l++;
        }
        return new int[] {0, 0};
    }
}
