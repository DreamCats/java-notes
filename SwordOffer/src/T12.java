/**
 * @program JavaBooks
 * @description: 旋转数组的最小数字
 * @author: mf
 * @create: 2019/08/23 10:18
 */

/*
把一个数组最开始的若干个元素搬到数组的末尾，我们称之
为数组的旋转。输入一个递增排序的数组是的一个旋转，输出旋转
数组的最小元素。例如{3, 4, 5, 1, 2}为{1, 2, 3, 4, 5}的
一个旋转，该数组的最小值为1。
 */
public class T12 {
    public static void main(String[] args) {
        int[] arr = {3, 4, 5, 1, 2};
        System.out.println(arr[findMin(arr)]);
    }

    // 别用从头到尾遍历的...那个对这道题没有意义
    // 无重复的
    public static int findMin(int[] arr) {
        int p1 = 0;
        int p2 = arr.length - 1;
        int mid = p1;

        while (arr[p1] >= arr[p2]) {
            if (p2 - p1 == 1) {
                mid = p2;
                break;
            }
            mid = p1 + ((p2 - p1) >> 1);
            // 提高代码健壮性，若是出现arr[p1] arr[p2] mid三者相等
            if (arr[p1] == arr[p2] && arr[p1] == mid) {
                return ergodic(arr, p1, p2);
            }
            if (arr[p1] < arr[mid]) {
                p1 = mid;
            } else {
                p2 = mid;
            }

        }
        return mid;
    }

    public static int ergodic(int[] arr, int p1, int p2) {
        int res = 0;
        for (int i = p1; i <= p2; i++) {
            if (res > arr[i]) {
                res = arr[i];
            }
        }
        return res;
    }
}
