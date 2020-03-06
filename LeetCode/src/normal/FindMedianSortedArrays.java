package normal; /**
 * @program JavaBooks
 * @description: 两个有序数组的中位数
 * @author: mf
 * @create: 2019/10/19 22:08
 */

/**
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * 你可以假设 nums1 和 nums2 不会同时为空。
 * nums1 = [1, 3]
 * nums2 = [2]
 * 则中位数是 2.0
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * 则中位数是 (2 + 3)/2 = 2.5
 */

/**
 * 具体思路如下，将问题 转化为在两个数组中找第 K 个小的数 。
 * A[k/2] = B[k/2],那么第 k 大的数就是 A[k/2]
 * A[k/2] > B[k/2],那么第 k 大的数肯定在 A[0:k/2+1] 和 B[k/2:] 中，这样就将原来的所有数的总和减少到一半了，再在这个范围里面找第 k/2 大的数即可，这样也达到了二分查找的区别了。
 * A[k/2] < B[k/2]，那么第 k 大的数肯定在 B[0:k/2+1]和 A[k/2:] 中，同理在这个范围找第 k/2 大的数就可以了。
 */

public class FindMedianSortedArrays {
    public static void main(String[] args) {
        int[] A = {1,3,4,7};
        int[] B = {1,2,3,4,5,6,7,8,9,10};
        double res = findMedianSortedArrays(A, B);
        System.out.println(res);

    }

    private static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int left = (n + m + 1) / 2;
        int right = (n + m + 2) / 2;
        //一个小技巧：将偶数和奇数的情况合并，如果是奇数，会求两次同样的 k 。
        return (getKth(nums1, 0, n - 1, nums2, 0, m - 1, left) + getKth(nums1, 0, n - 1, nums2, 0, m - 1, right)) * 0.5;
    }
    private static int getKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
        int len1 = end1 - start1 + 1;
        int len2 = end2 - start2 + 1;
        //让 len1 的长度小于 len2，这样就能保证如果有数组空了，一定是 len1
        if (len1 > len2) return getKth(nums2, start2, end2, nums1, start1, end1, k);
        if (len1 == 0) return nums2[start2 + k - 1];

        if (k == 1) return Math.min(nums1[start1], nums2[start2]);

        int i = start1 + Math.min(len1, k / 2) - 1;
        int j = start2 + Math.min(len2, k / 2) - 1;

        if (nums1[i] > nums2[j]) {
            return getKth(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1));
        }
        else {
            return getKth(nums1, i + 1, end1, nums2, start2, end2, k - (i - start1 + 1));
        }
    }
}
