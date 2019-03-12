/**
 * @program JavaBooks
 * @description: 成员内部类
 * @author: mf
 * @create: 2019/03/12 12:59
 */

public class Circle {
    double radius = 0;

    public Circle (double radius) {
        this.radius = radius;
    }

    class Draw {  // 内部类
        public void drawShape() {
            System.out.println("drawShape");
        }
    }
}

// 类Draw像是类Circle的一个成员, Circle 是外部类
// 成员内部类可以无条件访问外部类的所有成员属性和成员方法（包括private成员和静态成员）

