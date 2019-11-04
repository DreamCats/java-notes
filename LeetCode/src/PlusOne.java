/**
 * @program JavaBooks
 * @description: 加一
 * @author: mf
 * @create: 2019/11/03 17:41
 */

import java.util.Arrays;

/**
 * 题目：https://leetcode-cn.com/problems/plus-one/
 * 难度:easy
 * 类型：数组
 */
public class PlusOne {
    public static void main(String[] args) {
        int[] arr = {4,3,2,1};
        int[] res = plusOne(arr);
        System.out.println(Arrays.toString(res));
    }

    private static int[] plusOne(int[] digits) {
        int length = digits.length;
        int[] res = new int[length + 1];
        int carry = 1; // 进位
        for (int i = length - 1; i >= 0 ; i--) {
            int sums = digits[i] + carry;
            if (sums >= 10) {
                carry = 1;
                res[i] = sums % 10;
            } else {
                carry = 0;
                res[i] = sums;
            }
        }
        if (carry == 1) {
            res[0] = 1;
            return res;
        }
        return Arrays.copyOfRange(res,0,length);
    }
}
