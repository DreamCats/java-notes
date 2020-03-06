package books;

import java.util.Arrays;

/**
 * @program JavaBooks
 * @description: 调整数组顺序使奇数位于偶数前面
 * @author: mf
 * @create: 2019/09/02 09:47
 */
/*
输入一个整数数组，实现一个函数来调整该数组中数字的顺序，
使得所有奇数位于数组的前半部分
所有偶数位于数组的后半部分
 */
public class T21 {
    public static void main(String[] args) {
        int[] arr = {2, 3, 6, 4, 7, 5};
        reorderOddEven(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void reorderOddEven(int[] arr) {
        if (arr == null || arr.length == 0) return;
        int p1 = 0;
        int p2 = arr.length - 1;
        while (p1 < p2) {
            // 向后移动p1, 直到指向偶数  根据题目的话，这里可扩展
            while (p1 < p2 && (arr[p1] & 0x1) != 0) {
                p1++;
            }
            // 向前移动p2，直到指向奇数  同理
            while (p1 < p2 && (arr[p2] & 0x1) != 1) {
                p2--;
            }

            if(p1 < p2) {
                swap(arr, p1, p2);
            }
        }
    }

    private static void swap(int[] arr, int p1, int p2) {
        int temp = arr[p1];
        arr[p1] = arr[p2];
        arr[p2] = temp;
    }
}
