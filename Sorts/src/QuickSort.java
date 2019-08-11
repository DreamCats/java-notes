import java.util.Arrays;

/**
 * @program JavaBooks
 * @description: 快速排序
 * @author: mf
 * @create: 2019/08/11 21:55
 */


/*
- 从数列中挑出一个元素，称为"基准"；
- 重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以放到任一边）。
  在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区操作；

- 递归地把小于基准值元素的子数列和大于基准值元素的子数列排序；

 */

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {9, 10, 8, 3, 6};
        System.out.println(Arrays.toString(arr));
        int[] resArr = quickSort(arr);
        System.out.println(Arrays.toString(resArr));

    }


    public static int[] quickSort(int[] sourceArr) {
        int[] arr = Arrays.copyOf(sourceArr, sourceArr.length);
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
