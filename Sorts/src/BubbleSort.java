import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @program JavaBooks
 * @description: 冒泡排序
 * @author: mf
 * @create: 2019/08/10 19:14
 */

/*
- 比较相邻的两个元素，如果第一个比第二个大，就交换他们两个。
- 对每一对相邻的元素作同样的工作，从开始第一到结尾的最后一对。这步结束后，最后的元素会是最大的数
 */

public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {5, 2, 3, 1, 4};
        System.out.println("Bubble sort ...");
        System.out.println(Arrays.toString(arr));
        int[] resArr = bubbleSort(arr);
        System.out.println(Arrays.toString(resArr));

    }


    public static int[] bubbleSort(int[] sourceArray) {
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);
        for (int i = 1; i < arr.length; i++) {
            // 设定一个标记，若为true，则表示此次循环没有进行交换，也就是待排序列已经有序，排序已经完成。
            for (int j = 0; j < arr.length - i; j++) {
                // 如果前面的数比后面的数大，则交换
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
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
