package books;

/**
 * @program JavaBooks
 * @description: 在排序数组中查找数字
 * @author: mf
 * @create: 2019/10/06 10:01
 */

/*
统计一个数字在排序数组中出现的次数。例如，输入排序数组{1,2,3,3,3,3,4,5}
和数字3，由于3在这个数组中出现了4次，因此输出4。
 */
public class T53 {
    public static void main(String[] args) {
        int[] arr = {1,2,3,3,3,3,4,5};
        System.out.println(getArrayOfK(arr, 3));
    }

    private static int getArrayOfK(int[] arr, int k) {
        if (arr == null || arr.length == 0) return 0;
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == k) count++;
        }
        return count;
    }
}
