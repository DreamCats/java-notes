/**
 * @program JavaBooks
 * @description: ConstructorsDemo
 * @author: mf
 * @create: 2020/02/09 17:28
 */

package com.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 通过Class对象可以获取某个类中的：构造方法、成员变量、成员方法；并访问成员；
 *
 */
public class ConstructorsDemo {

    public static void main(String[] args) throws Exception {
        // 1. 加载Class对象
        Class clazz = Class.forName("com.reflect.Student");

        // 2. 获取所有公有构造方法
        System.out.println("**********************所有公有构造方法*********************************");
        Constructor[] constructors = clazz.getConstructors();
        for (Constructor constructor : constructors) {
            System.out.println(constructor);
        }

        // 3.
        System.out.println("************所有的构造方法(包括：私有、受保护、默认、公有)***************");
        Constructor[] declaredConstructors = clazz.getDeclaredConstructors();
        for (Constructor declaredConstructor : declaredConstructors) {
            System.out.println(declaredConstructor);
        }

        // 4.
        System.out.println("*****************获取公有、无参的构造方法*******************************");
        Constructor constructor = clazz.getConstructor();
        System.out.println(constructor);

        // 调用构造方法
        Object object = constructor.newInstance();
        System.out.println(object);

        //
        System.out.println("******************获取私有构造方法，并调用*******************************");
        Constructor constructor1 = clazz.getDeclaredConstructor(char.class);
        System.out.println(constructor1);
        // 调用构造方法
        constructor1.setAccessible(true); // 暴力访问
        Object object2 = constructor1.newInstance('买');
        System.out.println(object2);
    }
}
