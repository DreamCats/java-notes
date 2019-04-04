

/**
 * @program JavaBooks
 * @description: 整数反转
 * @author: mf
 * @create: 2019/04/04 21:10
 */

public class IntegerInversion {

    public static void main(String[] args) {
        Solution s = new Solution();
        int res = s.reverse(1534236469);

    }

}


class Solution {
    public int reverse(int x) {
        long res = 0;
        while (x != 0) {
            int temp = x % 10;
            x = x / 10;
            res = res * 10 + temp;
        }
        if (res > Integer.MAX_VALUE || res < Integer.MIN_VALUE) {
            return 0;
        }
        return (int)res;
    }
}

