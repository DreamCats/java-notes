/**
 * @program JavaBooks
 * @description: TypeConvert
 * @author: mf
 * @create: 2020/02/12 01:46
 */

package com.type;

/**
 * 隐士转换
 */
public class TypeConvert {
    public static void main(String[] args) {
        // 字面量属于 double 类型
        // 不能直接将 1.1 直接赋值给 float 变量，因为这是向下转型
        // Java 不能隐式执行向下转型，因为这会使得精度降低。
        // float f = 1.1;
        float f = 1.1f;

        // 因为字面量 1 是 int 类型，它比 short 类型精度要高，因此不能隐式地将 int 类型下转型为 short 类型。
        short s1 = 1;
         // s1 = s1 + 1;

        // 但是使用 += 运算符可以执行隐式类型转换。
        s1 += 1;
        // 上面的语句相当于将 s1 + 1 的计算结果进行了向下转型:
        s1 = (short) (s1 + 1);

    }
}
