/**
 * @program JavaBooks
 * @description: 测试
 * @author: mf
 * @create: 2020/05/24 00:04
 */

package Test;

import java.util.Arrays;

public class Test {

    public static void main(String[] args) {
        int[] arr = {5,3,8,10,2,1,9};
        int[] res = quickSort(arr);
        System.out.println(Arrays.toString(res));
    }
    public static int[] quickSort(int[] arr) {
        return quickSort(arr, 0, arr.length - 1);
    }

    public static int[] quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int partitionIndex = partition(arr, left, right);
            // 左半部分递归
            quickSort(arr, left, partitionIndex - 1);
            // 右半部分递归
            quickSort(arr, partitionIndex + 1, right);
        }
        return arr;
    }

    public static int partition(int[] arr, int left, int right) {
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

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
