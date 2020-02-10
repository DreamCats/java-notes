/**
 * @program JavaBooks
 * @description: Subject
 * @author: mf
 * @create: 2020/02/10 21:22
 */

package com.copy;

public class Subject {

    private String name;

    public Subject(String name) {
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
        return "Subject{" +
                "name='" + name + '\'' +
                '}';
    }
}
