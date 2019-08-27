/**
 * @program JavaBooks
 * @description: 数值的整数次方
 * @author: mf
 * @create: 2019/08/27 09:49
 */

/*

 */
public class T17 {
    public static void main(String[] args) {
        double value = doublePow(5.0, -2);
        System.out.println(value);
    }
    // 0的0次方没有意义， 所以有个条件限制
    private static double doublePow(double number, int exp) {
        double result = number;
        if (number == 0.0 && exp < 0) return 0.0;
        boolean expSign = true;
        if (exp < 0) {
            expSign = false;
            exp = - exp;
        }
        for (int i = 1; i < exp; i++) {
            result *= number;
        }
        if (!expSign) {
            result = 1 / result;
        }
        return result;
    }
}
