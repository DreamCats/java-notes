/**
 * @program JavaBooks
 * @description: String.Intern
 * @author: mf
 * @create: 2020/02/12 01:35
 */

package com.strings;

/**
 * 使用 String.intern() 可以保证相同内容的字符串变量引用同一的内存对象。
 */
public class SIntern {
    public static void main(String[] args) {
        // 采用new String创建对象
        String s1 = new String("aaa");
        String s2 = new String("aaa");
        System.out.println(s1 == s2); // false

        // 将s1的对象通过intern放到String Pool中
        String s3 = s1.intern();
        System.out.println(s1.intern() == s3); // true
        System.out.println(s1.intern() == s2.intern()); // true
        System.out.println(s3 == "aaa"); // true


        // 如果是采用 "bbb" 这种使用双引号的形式创建字符串实例，会自动地将新建的对象放入 String Pool 中。
        String s4 = "bbb";
        String s5 = "bbb";
        System.out.println(s4 == s5); // true
    }
}
