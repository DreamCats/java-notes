package web; /**
 * @program LeetNiu
 * @description: 最小的k个数
 * @author: mf
 * @create: 2020/01/14 00:22
 */

import java.util.ArrayList;

/**
 * 输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4,。
 */
public class T29 {
    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> integers = new ArrayList<>();
        if (k > input.length){
            return integers;
        }
        // 先排序。。。
        for (int i = 1; i < input.length; i++) {
            for (int j = 0; j < input.length - i; j++) {
                if (input[j] > input[j + 1]) {
                    swap(input, j, j + 1);
                }
            }
        }
        //
        for (int i = 0; i < k; i++) {
            integers.add(input[i]);
        }
        return integers;
    }

    /**
     * 交换
     * @param arr
     * @param i
     * @param j
     */
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
