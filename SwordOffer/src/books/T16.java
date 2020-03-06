package books;

/**
 * @program JavaBooks
 * @description: 数值的整数次方
 * @author: mf
 * @create: 2019/08/27 09:49
 */

public class T16 {
    public static void main(String[] args) {
        double value = doublePow(2.0, 8);
        System.out.println(value);
    }
    // 0的0次方没有意义， 所以有个条件限制
    private static double doublePow(double number, int exp) {
        double result = 1.0;
        if (number == 0.0 && exp < 0) return 0.0;
        boolean expSign = true;
        if (exp < 0) {
            expSign = false;
            exp = - exp;
        }
//        result = powerUnsignExp(number, exp);
        result = powerUnsignExp2(number, exp);
        if (!expSign) {
            result = 1 / result;
        }
        return result;
    }
    // 效率较低
    private static double powerUnsignExp(double number, int exp) {
        double result = 1.0;
        for (int i = 1; i <= exp; i++) {
            result *= number;
        }
        return result;
    }
    // 高效率 递归
    private static double powerUnsignExp2(double number, int exp) {
        if (exp == 0) return 1;
        if (exp == 1) return number; // 不管是奇数还是偶数，都会将exp递归到1 都会到这里返回
        // 讲究细节
        double result = powerUnsignExp2(number, exp >> 1);
        result *= result;
        // 讲究细节 奇数
        if ((exp & 0x1) == 1) result *= number;

        return result;

    }

}
