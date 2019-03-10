/**
 * @program JavaBooks
 * @description: 组合语法， 该文件演示java的复用类中的组合语法的使用
 * @author: mf
 * @create: 2019/03/10 11:04
 */

public class SprinkSystem {
    private String value1, value2, value3, value4;
    private WaterSource waterSource = new WaterSource();
    private int i;
    private float f;
    public String toString() {
        return
                "value1 = " + value1 + " " +
                        "value2 = " + value2 + " " +
                        "value3 = " + value3 + " " +
                        "value4 = " + value4 + "\n" +
                        "i = " + i + " " + "f = " + f + " " +
                        "source = " + waterSource;
    }
    // 入口main方法
    public static void main(String[] args) {
//        SprinkSystem sprinkers = new SprinkSystem();
//        System.out.println(sprinkers);
        StringDemo stringDemo = new StringDemo();
        System.out.println(stringDemo);
    }

}

/*
 * 定义一个水资源的类
 * */
class WaterSource {
    private String s;
    WaterSource() {
        System.out.println("WaterSource()");
        s = "Constructed";
    }
    // WaterSource toString
    public String toString() { return s;}
}


/*
 * 若该类重写了toString的方法，当被调用的时候，会默认启用toString方法*/
class StringDemo {
    private String s = "Mai feng";
    public String toString() {return s;}
}
