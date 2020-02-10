/**
 * @program JavaBooks
 * @description: Student
 * @author: mf
 * @create: 2020/02/10 21:22
 */

package com.copy;

public class Student implements Cloneable{

    // 对象的引用
    private Subject subject;

    private String name;

    public Student(Subject s, String name) {
        this.subject = s;
        this.name = name;
    }

    public Subject getSubject() {
        return subject;
    }

    public String getName() {
        return name;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "subject=" + subject +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        // 浅拷贝
//        return super.clone();

        // 深拷贝
        Student student = new Student(new Subject(subject.getName()), name);
        return student;
    }
}
