package books;

import javax.naming.RefAddr;

/**
 * @program JavaBooks
 * @description: 表示数值的字符串
 * @author: mf
 * @create: 2019/09/01 10:16
 */

/*
请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。例如
字符串"+100"、"5e2"、"-123"、"3。1416"及"-1E-16"都表示数值。
但"12e"、"1a3.14"、"1.2.3"、"+-5"及"12e+5.4"都不是
 */

/*
数字的格式可以用A[.[B]][e|EC]表示，其中A和C都是
整数（可以有正负号，也可以没有），而B是一个无符号整数
 */
public class T20 {
    public static void main(String[] args) {
        char[] str = {'1', '2', '3', '.', '4', '5', 'e', '+', '6'};
        boolean res = isNumeric(str);
        System.out.println(res);
    }

    private static boolean isNumeric(char[] str) {
        int index = 0;
        if (str == null || str.length == 0) return false;
        if (str.length == 1 && (str[0] == '+' || str[0] == '-')) return false; // 说明只有一个字符，要不+要不-
        if (str[0] == '+' || str[0] == '-') index++; // 跳过+ 或者-
        index = judgeDigits(str, index); // 跳过整数的数字部分
        if (index == str.length) return true; // 正好满足
        if (str[index] == '.') {
            // 跳过整数， 就是小数点了
            //跳过小数点
            index++;
            if (index == str.length) return false; // 不满足
            index = judgeDigits(str, index); // 跳过小数点后的整数部分
            if (index == str.length) return true; // 正好满足就返回
            if (str[index] == 'e' || str[index] == 'E') {
                index++; // 吧e和E跳过去
                return judgeE(str, index);
            }
            return false;
        } else if(str[index] == 'e' || str[index] == 'E') {
            index++; // 吧e和E跳过去
            return judgeE(str, index);
        }

        return false;
    }

    private static boolean judgeE(char[] str, int index) {
        if (index >= str.length) return false;
        if (str[index] == '+' || str[index] == '-') index++; // 跳过+ 或者-
        if (index >= str.length) return false;//如果刚跳过e就到了字符串末尾 是12e就是不规范的
        index = judgeDigits(str, index); // 跳过数字部分部分
        if (index == str.length) return true;
        return false;
    }

    private static int judgeDigits(char[] str, int index) {
        while (index < str.length) {
            // 判断是不是0-9之间，不是的话就break返回index下标
            int number = str[index] - '0';
            if (number <= 9 && number >= 0) index++;
            else break;
        }
        return index;
    }
}
