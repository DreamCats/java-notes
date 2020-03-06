package books;

/**
 * @program JavaBooks
 * @description: 数组中的逆序对
 * @author: mf
 * @create: 2019/10/04 09:42
 */

/*
在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
输入一个数组，求出这个数组中的逆序对的总数。例如，在数组{7,5,6,4}中，一共存在
5个逆序对，分别是是(7,6)、(7,5)、(7，4)和(5,4)。
 */
public class T51 {
    private static int count = 0;
    public static void main(String[] args) {
        // 其实就是归并排序
        int[] arr = {7, 5, 6, 4};
        mergeSort(arr);
        System.out.println(count);
    }

    private static void mergeSort(int[] arr) {
        if (arr == null || arr.length == 0) return;
        mergeSort(arr, 0, arr.length - 1);
    }

    private static void mergeSort(int[] arr, int left, int right) {
        if (left == right) return;
        int mid = (left + right) >> 1;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int[] help = new int[right - left + 1];
        int i = 0;
        int p1 = left;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= right) {
            if (arr[p1] > arr[p2]) {
                help[i++] = arr[p2++];
                // arr[p1] > arr[p2]
                // 那么arr[p1]后面的元素都比arr[p2]大
                // 因为有序
                count += mid - p1 + 1;
            } else {
                help[i++] = arr[p1++];
            }
        }
        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }
        while (p2 <= right) {
            help[i++] = arr[p2++];
        }
        for (int j = 0; j < help.length; j++) {
            arr[left + j] = help[j];
        }
    }
}
