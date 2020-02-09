/**
 * @program JavaBooks
 * @description: ReflectDemo
 * @author: mf
 * @create: 2020/02/09 16:55
 */

package com.reflect;

/**
 * 获取Class对象的三种方式
 * Object ——> getClass();
 * 任何数据类型（包括基本数据类型）都有一个“静态”的class属性
 * 通过Class类的静态方法：forName（String  className）(常用)
 */
public class ReflectDemo {
    public static void main(String[] args) throws ClassNotFoundException {
        // 第一种方式获取Class对象
        Student student = new Student(); // 这一new 产生一个Student对象，一个Class对象。
        Class studentClass = student.getClass();
        System.out.println(studentClass.getName()); // com.reflect.Student

        // 第二种方式获取Class对象
        Class studentClass2 = Student.class;
        System.out.println(studentClass == studentClass2); //判断第一种方式获取的Class对象和第二种方式获取的是否是同一个

        //第三种方式获取Class对象
        Class studentClass3 = Class.forName("com.reflect.Student"); //注意此字符串必须是真实路径，就是带包名的类路径，包名.类名
        System.out.println(studentClass3 == studentClass2); // //判断三种方式是否获取的是同一个Class对象

        // 三种方式常用第三种，第一种对象都有了还要反射干什么。
        // 第二种需要导入类的包，依赖太强，不导包就抛编译错误。
        // 一般都第三种，一个字符串可以传入也可写在配置文件中等多种方法。
    }
}
