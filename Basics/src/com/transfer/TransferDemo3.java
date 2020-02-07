/**
 * @program JavaBooks
 * @description: TransferDemo3
 * @author: mf
 * @create: 2020/02/07 22:05
 */

package com.transfer;

public class TransferDemo3 {
    public static void main(String[] args) {
        // 有些程序员（甚至本书的作者）认为 Java 程序设计语言对对象采用的是引用调用，实际上，这种理解是不对的。
        Student s1 = new Student("Mai");
        Student s2 = new Student("Feng");
        swap2(s1, s2);
        System.out.println("s1:" + s1.getName());
        System.out.println("s2:" + s2.getName());
        // 方法并没有改变存储在变量 s1 和 s2 中的对象引用。
        // swap 方法的参数 x 和 y 被初始化为两个对象引用的拷贝，这个方法交换的是这两个拷贝
    }

    private static void swap2(Student x, Student y) {
        Student temp = x;
        x = y;
        y = temp;
        System.out.println("x:" + x.getName());
        System.out.println("y:" + y.getName());
    }
}
