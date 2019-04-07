/**
 * @program JavaBooks
 * @description: 数学方法
 * @author: mf
 * @create: 2019/04/07 13:39
 */

public class MathDemo {
    // 四舍五入, 随机数，开方，次方，π，自然常数
    public static void main(String[] args) {
        float f1 = 5.4f;
        float f2 = 5.5f;
        //5.4四舍五入即5
        System.out.println(Math.round(f1));
        //5.5四舍五入即6
        System.out.println(Math.round(f2));

        //得到一个0-1之间的随机浮点数（取不到1）
        System.out.println(Math.random());

        //得到一个0-10之间的随机整数 （取不到10）
        System.out.println((int)( Math.random()*10));
        //开方
        System.out.println(Math.sqrt(9));
        //次方（2的4次方）
        System.out.println(Math.pow(2,4));

        //π
        System.out.println(Math.PI);

        //自然常数
        System.out.println(Math.E);
    }
}
