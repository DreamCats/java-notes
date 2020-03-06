package web; /**
 * @program LeetNiu
 * @description: 和为S的两个数字
 * @author: mf
 * @create: 2020/01/15 10:48
 */

import java.util.ArrayList;

/**
 * 输入一个递增排序的数组和一个数字S，在数组中查找两个数，使得他们的和正好是S，如果有多对数字的和等于S，输出两个数的乘积最小的。
 */
public class T42 {

    public ArrayList<Integer> FindNumbersWithSum(int [] array, int sum) {
        int start = 0, end = array.length - 1;
        ArrayList<Integer> list = new ArrayList<>();
        while (start < end) {
            int count = array[start] + array[end];
            if (count < sum) {
                start++;
            }
            if (count == sum) {
                list.add(array[start]);
                list.add(array[end]);
                return list;
            }
            if (count > sum) {
                end--;
            }
        }
        return list;
    }
}
