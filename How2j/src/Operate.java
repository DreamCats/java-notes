/**
 * @program JavaBooks
 * @description: 操作符
 * @author: mf
 * @create: 2019/04/02 11:24
 */

public class Operate {
    // + - * / %
    // ++ --
    public static void main(String[] args) {
//        int i = 10;
//        int j = 5;
//        int a = i+j;
//        int b = i - j;
//        int c = i*j;
//        int d = i /j;



        // 如果有任何运算单元的长度超过int,那么运算结果就按照最长的长度计算
//        int a = 5;
//        long b = 6;
//        int c = (int) (a+b); //a+b的运算结果是long型，所以要进行强制转换
//        long d = a+b;



        // 任意运算单元的长度小于int
//        byte a = 1;
//        byte b= 2;
//        byte c = (byte) (a+b); //虽然a b都是byte类型，但是运算结果是int类型，需要进行强制转换
//        int d = a+b;


        // %取模

//        int i = 5;
//        int j = 2;
//        System.out.println(i%j); //输出为1

        // ++

//        int i = 5;
//        i++;
//        System.out.println(i);//输出为6



        // 自增 自减操作符置前以及置后的区别

        // i++; 先取值，再运算
        // ++i; 先运算，再取值

//        int i = 5;
//
//        System.out.println(i++); //输出5
//        System.out.println(i);   //输出6
//
//        int j = 5;
//        System.out.println(++j); //输出6
//        System.out.println(j);   //输出6


        // > 大于
        //>= 大于或等于
        //< 小于
        //<= 小于或等于
        //== 是否相等
        //!= 是否不等

        //

//        int a = 5;
//        int b = 6;
//        int c = 5;
//
//        System.out.println(a>b);  //返回 false
//        System.out.println(a>=c);  //返回 true
//
//        System.out.println(a==b); //返回false
//        System.out.println(a!=b);//返回true



        // 长路与 和 短路与

        // 两边的运算单元都是布尔值

        // 长路与 两侧，都会被运算
        //短路与 只要第一个是false，第二个就不进行运算了

        //长路与  无论第一个表达式的值是true或者false,第二个的值，都会被运算
//        int i = 2;
//        System.out.println( i== 1 & i++ ==2  ); //无论如何i++都会被执行，所以i的值变成了3
//        System.out.println(i);
//
//        //短路与 只要第一个表达式的值是false的，第二个表达式的值，就不需要进行运算了
//        int j = 2;
//        System.out.println( j== 1 && j++ ==2  );  //因为j==1返回false,所以右边的j++就没有执行了，所以j的值，还是2
//        System.out.println(j);

        // 长路或 和 短路或
        // 两边的运算单元都是布尔值
        // 长路或 两侧都会被运算
        //短路或 只要第一个是true的，第二个就不进行运算了

        //长路或  无论第一个表达式的值是true或者false,第二个的值，都会被运算
//        int i = 2;
//        System.out.println( i== 1 | i++ ==2  ); //无论如何i++都会被执行，所以i的值变成了3
//        System.out.println(i);
//
//        //短路或 只要第一个表达式的值是true的，第二个表达式的值，就不需要进行运算了
//        int j = 2;
//        System.out.println( j== 2 || j++ ==2  );  //因为j==2返回true,所以右边的j++就没有执行了，所以j的值，还是2
//        System.out.println(j);

        // 异或^
        // 不同，返回真
        // 相同，返回假
//        boolean a = true;
////        boolean b = false;
////
////        System.out.println(a^b); //不同返回真
////        System.out.println(a^!b); //相同返回假


//        int i = 5;
//        String b = (Integer.toBinaryString(i)); // 5的二进制的表达101
//        System.out.println(i+" 的二进制表达是: "+b);


        // 位或
//        int i  =5;
//        int j = 6;
//
//        System.out.println(Integer.toBinaryString(i)); //5的二进制是101
//
//        System.out.println(Integer.toBinaryString(j)); //6的二进制是110
//
//        System.out.println(i|j); //所以 5|6 对每一位进行或运算，得到 111->7
//
//        System.out.println(i&j); //所以 5&6 对每一位进行与运算，得到 100->4
//
//
//        System.out.println(i^j); //所以 5^6 对每一位进行或运算，得到 011->3
//
//        System.out.println(i^0);
//        System.out.println(i^i);
//
//        System.out.println(Integer.toBinaryString(i)); //5的二进制是00000101,所以取非即为11111010,即为-6
//
//        System.out.println(~i);

        byte m  =6;

        //6的二进制是110
        System.out.println(Integer.toBinaryString(m));
        //6向左移1位后，变成1100，对应的10进制是12
        System.out.println(m<<1);
        //6向右移1位后，变成11，对应的10进制是3
        System.out.println(m>>1);

    }


}
