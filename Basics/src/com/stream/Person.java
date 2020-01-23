/**
 * @program JavaBooks
 * @description: 方法和函数式引用
 * @author: mf
 * @create: 2020/01/23 16:36
 */

package com.stream;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class Person {

    String name;

    Integer age;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }


    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public static void main(String[] args) {
        // 当只有1个参数时
        Supplier<Person> supplier = Person::new; // 构造函数引用指向默认的Apple()构造函数
        Person person = supplier.get(); // new 一个对象
        System.out.println(person);

        Supplier<Person> supplier1 = () -> new Person(); // 和上面等价
        Person person1 = supplier.get();
        System.out.println(person1);

        // 当只有一个参数时
        Function<String, Person> function = Person::new;
        Person person2 = function.apply("mai");
        System.out.println(person2);

        // 当有两个参数时
        BiFunction<String, Integer, Person> function1 = Person::new;
        Person person3 = function1.apply("feng", 25);
        System.out.println(person3);
    }
}
