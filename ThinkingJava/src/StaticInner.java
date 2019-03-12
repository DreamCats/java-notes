/**
 * @program JavaBooks
 * @description: 静态内部类
 * @author: mf
 * @create: 2019/03/12 16:03
 */

public class StaticInner {
    public static void main(String[] args) {
        StaticOutter.Inner inner = new StaticOutter.Inner();
    }
}


class StaticOutter {
    public StaticOutter() {

    }

    static class Inner {
        public Inner() {

        }
    }
}
// 静态内部类是不需要依赖于外部类的，这点和类的静态成员属性有点类似
