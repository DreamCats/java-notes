/**
 * @program JavaBooks
 * @description: 继承语法-初始化基类
 * @author: mf
 * @create: 2019/03/09 13:51
 */

public class Cartoon extends Drawing{
    public Cartoon() {
        System.out.println("Cartoon constructor");
    }
    public static void main(String[] args) {
        Cartoon x = new Cartoon();
    }
}

// art 类
class Art {
    Art() {
        System.out.println("Art constructor");
    }
}

// drawing 类
class Drawing extends Art {
    Drawing() {
        System.out.println("Drawing constructor");
    }
}

