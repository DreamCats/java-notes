/**
 * @program JavaBooks
 * @description: 组合语法， 实例初始化
 * @author: mf
 * @create: 2019/03/09 13:48
 */

public class Bath {
    private String // 初始化
            s1 = "Happy", // 直接赋值
            s2 = "Sad",
            s3, s4;
    private Soap castille;
    private int i;
    private float toy;
    public Bath() {
        System.out.println("Inside Bath()...");
        s3 = "Joy"; // 构造器中赋值
        toy = 3.14f;
        castille = new Soap();
    }
    // 实例初始化
    {i = 47;}
    public String toString() {
        if (s4 == null) s4 = "Joy";
        return
                "s1 = " + s1 + "\n" +
                        "s2 = " + s2 + "\n" +
                        "s3 = " + s3 + "\n" +
                        "s4 = " + s4 + "\n" +
                        "i = " + i + "\n" +
                        "toy = " + toy + "\n" +
                        "castille = " + castille;
    }
    public static void main(String[] args) {
        Bath b = new Bath();
        System.out.println(b);
    }
}

/*Soap类
 * 构造器*/
class Soap {
    private String s;
    Soap() {
        System.out.println("该方法soap()已执行...");
        s = "constructed";
    }
    public String toString() {return s;}
}
