package normal;

import java.util.Arrays;

/**
 * @program JavaBooks
 * @description: 344. 反转字符串
 * @author: mf
 * @create: 2019/11/10 09:59
 */

/*
题目：https://leetcode-cn.com/problems/reverse-string/
类型：双指针
难度：easy
 */
public class ReverseString {
    public static void main(String[] args) {
        char[] s = {'h','e','l','l','o'};
        System.out.println(Arrays.toString(s));
        reverseString(s);
        System.out.println(Arrays.toString(s));
    }

    private static void reverseString(char[] s) {
        int p1 = 0, p2 = s.length - 1;
        while (p1 < p2) {
            swap(s, p1++, p2--);
        }
    }

    private static void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
