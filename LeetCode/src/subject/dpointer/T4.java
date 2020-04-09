/**
 * @program JavaBooks
 * @description: 合并两个有序数组
 * @author: mf
 * @create: 2020/03/22 15:13
 */

package subject.dpointer;

/**
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 * 输出: [1,2,2,3,5,6]
 */
public class T4 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (m == 0) {
            for (int i = 0; i < n; i++) {
                nums1[i] = nums2[i];
            }
        }
        // 双指针
        int p = nums1.length - 1;
        int a1 = m - 1;
        for (int i = n - 1; i >= 0; i--) {
            while (a1 > 0 && nums1[a1] > nums2[i]) {
                nums1[p--] = nums1[a1--];
            }
            nums1[p--] = nums2[i];
        }
    }
}
