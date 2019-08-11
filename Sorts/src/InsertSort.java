import java.util.Arrays;

/**
 * @program JavaBooks
 * @description: 插入排序
 * @author: mf
 * @create: 2019/08/11 12:17
 */

/*
- 将第一待排序序列第一个元素看作一个有序序列，把第二个元素到最后一个元素当成是末排序序列。
- 从头到尾依次扫描末排序序列，将扫描到的每个元素插入有序序列的适当位置。
- 如果待插入的元素与有序序列中的某个元素相等，则将待插入元素插入到相等元素的后面）
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {5, 2, 3, 1, 4};
        System.out.println("Select sort ...");
        System.out.println(Arrays.toString(arr));
        int[] resArr = insertSort(arr);
        System.out.println(Arrays.toString(resArr));
    }

    public static int[] insertSort(int[] sourceArray) {
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
        }
        return arr;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
