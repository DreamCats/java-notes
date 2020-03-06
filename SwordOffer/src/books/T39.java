package books;

import java.util.HashMap;
import java.util.Map;

/**
 * @program JavaBooks
 * @description: 数组中出现次数超过一半的数字
 * @author: mf
 * @create: 2019/09/20 10:01
 */

/*
数组中有一个数字出现的次数超过数组长度的一半
请找出这个数字。例如，输入一个长度为9的数组
{1,2,3,2,2,2,5,4,2},由于2在数组中出现了5
次，超过数组长度的一半，因此输出2
 */
public class T39 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 2, 2, 2, 5, 4, 2};
//        int res = moreThanHalfNum(arr);
//        int res = moreThanHalfNum2(arr);
        int res = moreThanHalfNum3(arr);
        System.out.println(res);
    }

    // 哈希
    private static int moreThanHalfNum3(int[] arr) {
        HashMap<Integer, Integer> maps = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (maps.containsKey(arr[i])) {
                maps.put(arr[i], maps.get(arr[i]) + 1);
            } else {
                maps.put(arr[i], 1);
            }
        }
        int length = arr.length >> 1;
//        for (Map.Entry<Integer, Integer> entry : maps.entrySet()) {
//            if (entry.getValue() > length) {
//                return entry.getKey();
//            }
//        }
        for (Integer key : maps.keySet()) {
            if (maps.get(key) > length) {
                return key;
            }
        }
        return 0;
    }

    // 根据数组特性，相等++， 否则--， 毕竟最后剩最多次数的那个数
    private static int moreThanHalfNum2(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int res = arr[0];
        int times = 1;
        for (int i = 1; i < arr.length; i++) {
            if (times == 0) {
                res = arr[i];
                times = 1;
            } else if (arr[i] == res) {
                times++;
            } else {
                times--;
            }
        }
        if (!checkMoreThanHalf(arr, res)) return 0;

        return res;
    }

    // 采用快排的partition操作
    //
    private static int moreThanHalfNum(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int middle = (arr.length - 1) >> 1;
        int partitionIndex = partition(arr, 0, arr.length - 1);
        while (partitionIndex != middle) {
            if (partitionIndex > middle) {
                partitionIndex = partition(arr, 0, partitionIndex - 1);
            } else {
                partitionIndex = partition(arr, partitionIndex + 1, arr.length - 1);
            }
        }
        int res = arr[middle];
        if (!checkMoreThanHalf(arr, res)) res = 0;
        return res;
    }

    private static boolean checkMoreThanHalf(int[] arr, int res) {
        int times = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == res) times++;
        }
        boolean isMoreThanHalf = true;
        if (times * 2 <= arr.length) isMoreThanHalf = false;
        return isMoreThanHalf;
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
