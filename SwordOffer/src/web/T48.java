package web; /**
 * @program LeetNiu
 * @description: 不用加减乘除做加法
 * @author: mf
 * @create: 2020/01/15 13:47
 */

/**
 *写一个函数，求两个整数之和，要求在函数体内不得使用+、-、*、/四则运算符号。
 */
public class T48 {
    public int Add(int num1,int num2) {
        while (num2 != 0) {
            int temp = num1 ^ num2;
            num2 = (num1 & num2) << 1;
            num1 = temp;
        }
        return num1;
    }
}
