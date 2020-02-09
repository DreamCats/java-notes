/**
 * @program JavaBooks
 * @description: Student
 * @author: mf
 * @create: 2020/02/09 16:55
 */

package com.reflect;

public class Student {

    private String name;

    private Integer age;

    public String gender;

    // 无参数的构造方法
    public Student() {
        System.out.println("调用了公有、无参构造方法执行了。。。");
    }

    // 默认的构造方法
    public Student(String str) {
        System.out.println("(默认)的构造方法 s = " + str);
    }

    // 有一个参数的构造方法
    public Student(char name) {
        System.out.println("姓名：" + name);
    }

    // 有多个参数的构造方法
    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    //受保护的构造方法
    protected Student(boolean n){
        System.out.println("受保护的构造方法 n = " + n);
    }

    //私有构造方法
    private Student(int age){
        System.out.println("私有的构造方法   年龄："+ age);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    //**************成员方法***************//
    public void show1(String s){
        System.out.println("调用了：公有的，String参数的show1(): s = " + s);
    }
    protected void show2(){
        System.out.println("调用了：受保护的，无参的show2()");
    }
    void show3(){
        System.out.println("调用了：默认的，无参的show3()");
    }
    private String show4(int age){
        System.out.println("调用了，私有的，并且有返回值的，int参数的show4(): age = " + age);
        return "abcd";
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }
}
