import com.sun.tools.javac.comp.Lower;

/**
 * @program JavaBooks
 * @description: 二分查找
 * @author: mf
 * @create: 2019/10/02 16:45
 */

/*
二分查找
 */

/*
思想：在有序列表中，取中间记录作为比较对象，若给定值与中间记录的关键词相等，则查找成功；
若给定值小于中间记录的关键词，则在中间记录的左半区继续查找；
若给定值大于中间的关键字，则在中间记录的右半区继续查找。
不断重复上述锅中，直到查找成功。
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {0, 1, 16, 24, 35, 47, 59, 62};
        System.out.println(binarySearch(arr, 59));
    }
    private static int binarySearch(int[] arr, int target) {
        if (arr == null || arr.length == 0) return 0;
        int left = 0;
        int right = arr.length;
        int mid;
        while (left < right) {
            mid = (left + right) >> 1;
            if (target < arr[mid]) right = mid - 1;
            else if (target > arr[mid]) left = mid + 1;
            else return mid;
        }
        return 0;
    }
}
