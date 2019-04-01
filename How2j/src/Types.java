/**
 * @program JavaBooks
 * @description: 变量类型
 * @author: mf
 * @create: 2019/04/01 10:21
 */

public class Types {
    public static void main(String[] args) {

        // 整形数据
        byte b = 1;    //8位  -128~127
        short s= 200;   //16位 -32768~32767
        int i = 300;    //32位
        long l = 400;   // 64位


        // if we assgin b to 300;
//        b = 300; // 此时编译器就报错


        // 字符形
        // char 是16位

        char c = '中'; // 只能存放一个字符

        // if we assgin c to 2;
//        c = '中国'; // 报错

        // 浮点类型
        // float 长度32位
        // double 长度64位
        // 默认小树值是double类型的，在数值后面加个f 则是float类型的
//        float f = 54.321; // 报错，默认是double
        float f1 = 54.321f;

        // 布尔形

        // 其长度位1
        boolean b1 = true;
        boolean b2 = false;
        // 虽然布尔形真正存放的是0(flase)1(true)
        // 但是，不能直接使用0 1 进行赋值
//        boolean b3 = 1; // 报错

    }
}
