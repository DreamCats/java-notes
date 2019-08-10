import java.util.Arrays;

/**
 * @program JavaBooks
 * @description: 选择排序
 * @author: mf
 * @create: 2019/08/10 20:30
 */

/*
- 首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置
- 再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。
- 重复第二步，直到所有元素均排序完毕。
 */

public class SelectSort {
    public static void main(String[] args) {
        int[] arr = {5, 2, 3, 1, 4};
        System.out.println("Select sort ...");
        System.out.println(Arrays.toString(arr));
        int[] resArr = selectSort(arr);
        System.out.println(Arrays.toString(resArr));

    }

    public static int[] selectSort(int[] sourceArray) {
        int [] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        // N-1轮
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            // 每轮需要N-i次比较
            for (int j = i + 1; j < arr.length; j++) {
                min = arr[j] < arr[min] ? j : min; // 保存最小值坐标
            }
            // 将找到最小值的坐标与i交换
            swap(arr, i, min);
        }
        return arr;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
