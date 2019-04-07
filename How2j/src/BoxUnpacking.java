/**
 * @program JavaBooks
 * @description: 装箱 拆箱
 * @author: mf
 * @create: 2019/04/07 11:22
 */

public class BoxUnpacking {

    // 封装类
    // 所有的基本类型，都有对应的类类型
    // 比如int对应的类是Integer
    // 这种类就叫做封装类
    public static void main(String[] args) {
        int i = 5;

        //把一个基本类型的变量,转换为Integer对象
        Integer it = new Integer(i);
        //把一个Integer对象，转换为一个基本类型的int
        int i2 = it.intValue();



        // Number类
        // Byte,Short,Integer,Long,Float,Double
        //这些类都是抽象类Number的子类
        //Integer是Number的子类，所以打印true
        System.out.println(it instanceof Number);


        // 基本类型转封装类
        //基本类型转换成封装类型
        Integer it1 = new Integer(i);


        // 自动装箱
        // 不需要调用构造方法，通过=符号自动把 基本类型 转换为 类类型 就叫装箱
        //基本类型转换成封装类型
        Integer it2 = new Integer(i);

        //自动转换就叫装箱
        Integer it3 = i;

        // 自动拆箱
        //封装类型转换成基本类型
        int i3 = it.intValue();

        //自动转换就叫拆箱
        int i4 = it;

        // int的最大值，最小值
        //int的最大值
        System.out.println(Integer.MAX_VALUE);
        //int的最小值
        System.out.println(Integer.MIN_VALUE);

    }


}
