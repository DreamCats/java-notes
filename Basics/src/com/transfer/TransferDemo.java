/**
 * @program JavaBooks
 * @description: TransferDemo
 * @author: mf
 * @create: 2020/02/07 21:22
 */

package com.transfer;

/**
 * Java 程序设计语言总是采用按值调用。
 * 也就是说，方法得到的是所有参数值的一个拷贝，也就是说，方法不能修改传递给它的任何参数变量的内容。
 */
public class TransferDemo {
    public static void main(String[] args) {
        int num1 = 10;
        int num2 = 20;

        swap(num1, num2);
        System.out.println("num1 = " + num1);
        System.out.println("num2 = " + num2);
        // 通过上面例子，我们已经知道了一个方法不能修改一个基本数据类型的参数，而对象引用作为参数就不一样，请看


    }

    private static void swap(int a, int b) {
        int temp = a;
        a = b;
        b = temp;
        System.out.println("a = " + a);
        System.out.println("b = " + b);
    }
}
