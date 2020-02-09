/**
 * @program JavaBooks
 * @description: MethodDemo
 * @author: mf
 * @create: 2020/02/09 23:45
 */

package com.reflect;

import java.lang.reflect.Method;

public class MethodDemo {
    public static void main(String[] args) throws Exception {
        // 1. 获取对象
        Class clazz = Class.forName("com.reflect.Student");
        // 2. 获取所有公有方法
        System.out.println("***************获取所有的”公有“方法*******************");
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            System.out.println(method);
        }
        System.out.println("***************获取所有的方法，包括私有的*******************");
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println(declaredMethod);
        }
        System.out.println("***************获取公有的show1()方法*******************");
        Method m = clazz.getMethod("show1", String.class);
        System.out.println(m);
        // 实例化对象
        Object o = clazz.getConstructor().newInstance();
        m.invoke(o, "买");

        System.out.println("***************获取私有的show4()方法******************");
        m = clazz.getDeclaredMethod("show4", int.class);
        System.out.println(m);
        m.setAccessible(true); // 暴力解除 私有
        Object result = m.invoke(o, 20);//需要两个参数，一个是要调用的对象（获取有反射），一个是实参
        System.out.println("返回值：" + result);
    }
}
