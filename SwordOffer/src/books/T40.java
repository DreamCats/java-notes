package books;

import java.util.Arrays;

/**
 * @program JavaBooks
 * @description: 最小的k个数
 * @author: mf
 * @create: 2019/09/23 14:22
 */

/*
输入n个整数，找出其中最小的k个数。例如，输入
4、5、1、6、2、7、3、8这个8个数字，则最小的
4个数字数1、2、3、4
 */

/*
思路：
partition操作
或者
最大堆
或者
红黑树
 */
public class T40 {
    public static void main(String[] args) {
        // partition
        int[] arr = {4, 5, 1, 6, 2, 7, 3, 8};
        int[] resArr = getLeastNumbers(arr, 4);
        System.out.println(Arrays.toString(resArr));

    }

    // partition 可修改数组
    private static int[] getLeastNumbers(int[] arr, int k) {
        if (arr == null || k < 0) return null;
        int index = partition(arr, 0, arr.length - 1);
        while (index != k - 1) {
            if (index > k -1) {
                index = partition(arr, 0, index - 1);
            } else {
                index = partition(arr, index + 1, arr.length - 1);
            }
        }
        int[] resArr = new int[k];
        for (int i = 0; i < k; i++) {
            resArr[i] = arr[i];
        }
        return resArr;
    }

    private static int partition(int[] arr, int left, int right) {
        int pivot = left;
        int index = pivot + 1;
        for (int i = index; i <= right; i++) {
            if (arr[i] < arr[pivot]) {
                swap(arr, i, index++);
            }
        }
        swap(arr, pivot, index - 1);
        return index - 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
