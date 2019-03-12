/**
 * @program JavaBooks
 * @description: 成员内部类
 * @author: mf
 * @create: 2019/03/12 12:59
 */

/*public class Circle {
    private double radius = 0;
    public static int count = 0;

    public Circle (double radius) {
        this.radius = radius;
    }

    class Draw {  // 内部类
        public void drawShape() {
            System.out.println(radius); // 外部类的private成员
            System.out.println(count); // 外部类的静态成员
        }
    }
}*/

// 类Draw像是类Circle的一个成员, Circle 是外部类
// 成员内部类可以无条件访问外部类的所有成员属性和成员方法（包括private成员和静态成员）

// 注意：
// 当成员内部类拥有和外部类同名的成员变量或者方法时，会发生隐藏现象
// 即默认情况下访问的是成员内部类的成员
// 如果要访问外部类的同名成员，需要以下面的形式进行访问：
// 外部类.this.成员变量
// 外部类.this.成员方法

// 在外部类中如果要访问成员内部类的成员，必须先创建一个成员内部类的对象，再通过指向这个对象的引用来访问：


public class Circle {
    private double radius = 0;

    public Circle(double radius) {
        this.radius = radius;
        getDrawInstance().drawSahpe(); // 必须先创建成员内部的对象，才能访问。
    }

    private Draw getDrawInstance() {
        return new Draw();
    }

    class Draw {     //内部类
        public void drawSahpe() {
            System.out.println(radius);  //外部类的private成员
        }
    }
}

// 成员内部类是依附外部类而存在的，也就是说，如果要创建成员内部类的对象，前提是必须存在一个外部类的对象。创建成员内部类对象的一般方式如下：