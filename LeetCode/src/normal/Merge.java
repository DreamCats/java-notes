package normal; /**
 * @program JavaBooks
 * @description: 88、合并两个有序数组
 * @author: mf
 * @create: 2019/11/04 09:46
 */

/**
 * 题目：https://leetcode-cn.com/problems/merge-sorted-array/
 * 难度:easy
 */

import java.util.Arrays;

/**
 * 输入:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 *
 * 输出: [1,2,2,3,5,6]
 *
 */
public class Merge {
    public static void main(String[] args) {
//        int[] nums1 = {1,2,3,0,0,0};
//        int[] nums2 = {2,5,6};
        int[] nums1 = {2,0};
        int[] nums2 = {1};
        merge(nums1, 1, nums2, 1);
        System.out.println(Arrays.toString(nums1));
    }
    // 可以双指针
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        // 两种情况
        // 第一种，nums1全是0，也就是m==0
        if (m == 0) {
            for (int i = 0; i < n; i++) {
                nums1[i] = nums2[i];
            }
            return;
        }
        // 第二种，m不是0的情况
        int p = nums1.length - 1;
        int a1 = m - 1;
        for (int i = n - 1; i >= 0; i--) {
            while (a1 >= 0 && nums1[a1] > nums2[i]) {
                nums1[p--] = nums1[a1--];
            }
            nums1[p--] = nums2[i];
        }
    }
}
