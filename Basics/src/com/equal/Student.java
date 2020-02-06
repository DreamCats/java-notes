/**
 * @program JavaBooks
 * @description: Student
 * @author: mf
 * @create: 2020/02/07 02:07
 */

package com.equal;

import java.util.HashSet;
import java.util.Objects;

/**
 * HashCode、equals和==
 */
public class Student {

    private Long id;

    private String name;

    public Student(Long id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
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
                Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        System.out.println("name:" + this.getName() + "-->hashcode:" + Objects.hash(id, name));
        return Objects.hash(id, name);
    }


    public static void main(String[] args) {
        Student student = new Student(1L, "Mai");
        Student student1 = new Student(1L, "Mai");
//        System.out.println(student == student1);
//        System.out.println(student.equals(student1));
        Student student2 = new Student(2L, "Liu");
        HashSet<Student> set = new HashSet<>();
        set.add(student);
        set.add(student1);
        set.add(student2);
        System.out.println(set.toString());
    }
}
