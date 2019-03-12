/**
 * @program JavaBooks
 * @description: 局部内部类
 * @author: mf
 * @create: 2019/03/12 15:57
 */


//局部内部类是定义在一个方法或者一个作用域里面的类，它和成员内部类的区别在于局部内部类的访问仅限于方法内或者该作用域内。
public class People {
    public People (){}
}


class Man {
    public Man() {}


    public People getWomen() {
        class Women extends People { // 局部内部类
            int age = 0;

        }
        return new Women();
    }
}
// 局部内部类就像是方法里面的一个局部变量一样，是不能有public、protected、private以及static修饰符的。