/**
 * @program JavaBooks
 * @description: Student
 * @author: mf
 * @create: 2020/02/07 02:07
 */

package com.java8;

import java.util.HashSet;
import java.util.Objects;

/**
 * HashCode、equals和==
 */
public class Student {

    private Long id;

    private String name;

    private Integer age;

    public Student(Long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        System.out.println("name:" + this.getName() + "-->equals:" +
                (Objects.equals(id, student.id) && Objects.equals(name, student.name)) + " " + student.getName());
        return Objects.equals(id, student.id) &&
                Objects.equals(name, student.name) &&
                Objects.equals(age, student.age);
    }

    @Override
    public int hashCode() {
        System.out.println("name:" + this.getName() + "-->hashcode:" + Objects.hash(id, name));
        return Objects.hash(id, name, age);
    }


    public static void main(String[] args) {
        Student student = new Student(1L, "Mai", 16);
        Student student1 = new Student(1L, "Mai", 16);
//        System.out.println(student == student1);
//        System.out.println(student.equals(student1));
        Student student2 = new Student(2L, "Liu", 18);
        HashSet<Student> set = new HashSet<>();
        set.add(student);
        set.add(student1);
        set.add(student2);
        System.out.println(set.toString());
    }
}
