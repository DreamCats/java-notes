package web; /**
 * @program LeetNiu
 * @description: 把字符串转成整数
 * @author: mf
 * @create: 2020/01/16 13:55
 */

/**
 * 将一个字符串转换成一个整数，要求不能使用字符串转换整数的库函数。
 * 数值为0或者字符串不是一个合法的数值则返回0
 */
public class T49 {
    public int StrToInt(String str) {
        if (str.trim().equals("") || str.length() == 0) {
            return 0;
        }
        if (str.equals("-2147483649") || str.equals("2147483648")) {
            return 0;
        }
        char[] chars = str.toCharArray();
        int sign = 0;
        if (chars[0] == '-') {
            sign = 1;
        }
        int sum = 0;
        for (int i = sign; i < chars.length; i++) {
            if (chars[i] == '+') {
                continue;
            }
            if (chars[i] < 48 || chars[i] > 57) {
                return 0;
            }
            sum = sum * 10 + chars[i] - 48;
        }
        return sign == 0 ? sum : sum * -1;
    }
}
