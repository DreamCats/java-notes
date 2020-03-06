package normal; /**
 * @program JavaBooks
 * @description: 26.删除排序数组中的重复项
 * @author: mf
 * @create: 2019/11/03 17:10
 */

/**
 * 题目：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/
 * 难度：easy
 * 类型：数组
 */

import java.util.Arrays;

/**
 * 题目：
 * 给定数组 nums = [1,1,2],
 * 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
 * 你不需要考虑数组中超出新长度后面的元素。
 */
public class RemoveDuplicates {
    public static void main(String[] args) {
        int[] arr = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        System.out.println(removeDuplicates(arr));
        System.out.println(Arrays.toString(arr));
    }

    private static int removeDuplicates(int[] arr) {
        int p = 0; // 符合条件指针
        for (int i = 1; i < arr.length; i++) {
            if (arr[p] != arr[i]) {
                arr[++p] = arr[i];
            }
        }
        return p + 1;
    }
}
