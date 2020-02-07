/**
 * @program JavaBooks
 * @description: TransferDemo2
 * @author: mf
 * @create: 2020/02/07 22:04
 */

package com.transfer;

public class TransferDemo2 {

    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5 };
        System.out.println(arr[0]);
        change(arr);
        System.out.println(arr[0]);
        // 法得到的是对象引用的拷贝，对象引用及其他的拷贝同时引用同一个对象。
    }

    private static void change(int[] array) {
        // 修改数组中的一个元素
        array[0] = 0;
    }
}
