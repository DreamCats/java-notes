/**
 * @program JavaBooks
 * @description: 类型转换
 * @author: mf
 * @create: 2019/04/02 11:04
 */

public class TypeConversion {
    // 精度高的数据类型就像容量大的杯子，可以放更大的数据
    // 精度低的数据类型就像容量小的杯子，只能放更小的数据

    public static void main(String[] args) {
        char c = 'A';
        short s = 80;
        //虽然short和char都是16位的，长度是一样的
        //但是彼此之间，依然需要进行强制转换

        c = (char)s;
//        s  = c;  // 报错



        // 低精度向高精度转换
        long l = 50;
        int i = 50;
        // //int比较小，要放进比较大的long,随便怎么样，都放的进去
        l = i;


        // 高精度向低精度转换
        byte b = 5;
        int i1 = 10;
        int i2 = 300;

        b = (byte) i1;
        //因为i1的值是在byte范围之内，所以即便进行强制转换
        //最后得到的值，也是10
        System.out.println(b);

        //因为i2的值是在byte范围之外，所以就会按照byte的长度进行截取
        //i2的值是300，其对应的二进制数是 100101100
        //按照byte的长度8位进行截取后，其值为 00101100 即44
        b =(byte) i2;
        System.out.println(b);

        //查看一个整数对应的二进制的方法：
        System.out.println(Integer.toBinaryString(i2));
    }

}
