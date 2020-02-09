/**
 * @program JavaBooks
 * @description: FieldDemo
 * @author: mf
 * @create: 2020/02/09 20:08
 */

package com.reflect;

import java.lang.reflect.Field;

public class FieldDemo {
    public static void main(String[] args) throws Exception {
        // 1. 获取class对象
        Class clazz = Class.forName("com.reflect.Student");

        // 2. 获取所有字段
        System.out.println("************获取所有公有的字段********************");
        Field[] fields = clazz.getFields();
        for (Field field : fields) {
            System.out.println(field);
        }
        //
        System.out.println("************获取所有的字段(包括私有、受保护、默认的)********************");
        Field[] fields1 = clazz.getDeclaredFields();
        for (Field field : fields1) {
            System.out.println(field);
        }
        //
        System.out.println("*************获取公有字段**并调用***********************************");
        Field gender = clazz.getField("gender");
        System.out.println(gender);
        // 获取一个对象
        Object o = clazz.getConstructor().newInstance();
        gender.set(o, "男");
        Student stu = (Student) o;
        System.out.println("验证性别：" + stu.getGender());
        //
        System.out.println("*************获取公有字段**并调用***********************************");
        Field name = clazz.getDeclaredField("name");
        System.out.println(name);
        name.setAccessible(true); //暴力反射，解除私有限定
        name.set(o, "买");
        System.out.println("验证姓名：" + stu);


    }
}
