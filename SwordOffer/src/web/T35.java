package web; /**
 * @program LeetNiu
 * @description: 数组中的逆序对
 * @author: mf
 * @create: 2020/01/15 09:45
 */

/**
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
 * 输入一个数组,求出这个数组中的逆序对的总数P。并将P对1000000007取模的结果输出。 即输出P%1000000007
 */
public class T35 {

    private Integer count = 0;

    public int InversePairs(int [] array) {
        if (array == null || array.length == 0) return 0;
        mergeSort(array, 0, array.length - 1);
        return (count % 1000000007);
    }

    private void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            int mid = (left + right) >> 1;
            mergeSort(array, left ,mid);
            mergeSort(array, mid + 1, right);
            merge(array, left, mid, right);
        }

    }

    private void merge(int[] array, int left, int mid, int right) {
        int[] help = new int[right - left + 1];
        int i = 0;
        int p1 = left;
        int p2 = mid + 1;
        while(p1 <= mid && p2 <= right) {
            if(array[p1] > array[p2]) {
                help[i++] = array[p2++];
                count += mid - p1 + 1;
            } else {
                help[i++] = array[p1++];
            }
        }

        while(p1 <= mid) {
            help[i++] = array[p1++];
        }

        while(p2 <= right) {
            help[i++] = array[p2++];
        }

        for(int j = 0; j < help.length; j++) {
            array[left + j] = help[j];
        }
    }
}
