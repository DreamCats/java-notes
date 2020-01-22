/**
 * @program JavaBooks
 * @description: 回文数
 * @author: mf
 * @create: 2019/10/20 17:33
 */

/**
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * 输入: 121
 * 输出: true
 * 输入: -121
 * 输出: false
 * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * 输入: 10
 * 输出: false
 * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
 */

/**
 * 通过取整和取余操作获取整数中对应的数字进行比较。
 * 举个例子：1221 这个数字。
 * 通过计算 1221 / 1000， 得首位1
 * 通过计算 1221 % 10， 可得末位 1
 * 进行比较
 * 再将 22 取出来继续比较
 */
public class IsPalindrome {
    public static void main(String[] args) {
        boolean res = isPalindrome(1221);
        System.out.println(res);
    }

    private static boolean isPalindrome(int x) {
        // 边界判断 为负数直接false
        if (x < 0) return false;
        int div = 1;
        while (x / div >= 10) div *= 10;
        while (x > 0) {
            int left = x / div;
            int right = x % 10;
            if (left != right) return false;
            x = (x % div) / 10;
            div /= 100;
        }
        return true;
    }
}
