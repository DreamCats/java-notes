/**
 * @program JavaBooks
 * @description: ShalldowCopy
 * @author: mf
 * @create: 2020/02/10 21:21
 */

package com.copy;

public class ShallowCopyDemo {
    public static void main(String[] args) throws CloneNotSupportedException {
        // 原始对象
        Student student = new Student(new Subject("code"), "dream");
        System.out.println("原始对象: " + student.getName() + " - " + student.getSubject().getName());

        // 拷贝对象
        Student cloneStu = (Student) student.clone();
        System.out.println("拷贝对象: " + cloneStu.getName() + " - " + cloneStu.getSubject().getName());

        // 原始对象和拷贝对象是否一样：
        System.out.println("原始对象和拷贝对象是否一样: " + (student == cloneStu));

        // 原始对象和拷贝对象的name属性是否一样
        System.out.println("原始对象和拷贝对象的name属性是否一样: " + (student.getName() == cloneStu.getName()));

        // 原始对象和拷贝对象的subj属性是否一样
        System.out.println("原始对象和拷贝对象的subj属性是否一样: " + (student.getSubject() == cloneStu.getSubject()));

        student.setName("cat");
        student.getSubject().setName("eat");
        System.out.println("更新后的原始对象: " + student.getName() + " - " + student.getSubject().getName());
        System.out.println("更新原始对象后的克隆对象: " + cloneStu.getName() + " - " + cloneStu.getSubject().getName());

        // 在这个例子中，让要拷贝的类Student实现了Clonable接口并重写Object类的clone()方法，然后在方法内部调用super.clone()方法。
        // 从输出结果中我们可以看到，对原始对象stud的"name"属性所做的改变并没有影响到拷贝对象clonedStud；
        // 但是对引用对象subj的"name"属性所做的改变影响到了拷贝对象clonedStud。
    }
}
