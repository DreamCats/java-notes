package web; /**
 * @program LeetNiu
 * @description: 数值的整数次方
 * @author: mf
 * @create: 2020/01/10 09:47
 */

/**
 * 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
 *
 * 保证base和exponent不同时为0
 */
public class T12 {
    public double Power(double base, int exponent) {
        // 还是先判断特殊情况
        if (exponent == 0) return 1;
        double ans = 1;
        boolean flag = false; // 判断倒数
        if (exponent < 0) {
            flag = true;
            exponent = -exponent;
        }
        for (int i = 1; i <= exponent; i++) {
            ans *= base;
        }
        if (flag) {
            ans = 1 / ans;
        }
        return ans;
    }
}
