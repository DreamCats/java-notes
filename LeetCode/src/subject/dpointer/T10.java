/**
 * @program JavaBooks
 * @description: 两个数组的交集 II
 * @author: mf
 * @create: 2020/04/09 17:55
 */

package subject.dpointer;

import java.util.ArrayList;

/**
 * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出: [2,2]
 * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出: [4,9]
 */
public class T10 {
    // 这道题跟双指针没啥关系
    public int[] intersect(int[] nums1, int[] nums2) {
        ArrayList<Integer> list1 = new ArrayList<>();
        for (int i : nums1) {
            list1.add(i);
        }
        ArrayList<Integer> list2 = new ArrayList<>();
        for (int i : nums2) {
            if (list1.contains(i)) {
                list2.add(i);
                list1.remove(i);
            }
        }
        int[] ans = new int[list2.size()];
        for (int i = 0; i < list2.size(); i++) {
            ans[i] = list2.get(i);
        }
        return ans;
    }
}
