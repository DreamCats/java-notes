package books;

/**
 * @program JavaBooks
 * @description: 不用加减乘除做加法
 * @author: mf
 * @create: 2019/10/09 19:36
 */

/*
写一个函数，求两个整数之和，要求在函数体内不得使用"+"，"-"，
"x"，"+"四则运算符号。
 */
public class T65 {
    public static void main(String[] args) {
        System.out.println(add(2, 3));
    }

    private static int add(int num1, int num2) {
        while (num2 != 0) {
            int temp = num1 ^ num2;
            num2 = (num1 & num2) << 1;
            num1 = temp;
        }
        return num1;
    }
}
