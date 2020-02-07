/**
 * @program JavaBooks
 * @description: Student
 * @author: mf
 * @create: 2020/02/07 22:01
 */

package com.transfer;

public class Student {

    private String name;

    public Student(String name) {
        this.name = name;
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
                "name='" + name + '\'' +
                '}';
    }
}
