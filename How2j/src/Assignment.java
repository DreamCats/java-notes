/**
 * @program JavaBooks
 * @description: 赋值操作
 * @author: mf
 * @create: 2019/04/02 19:04
 */

public class Assignment {
    public static void main(String[] args) {
//        int i = 5+5;
//        System.out.println(i);


//        int i =3;
//        i+=2;
//        System.out.println(i);
//
//        int j=3;
//        j=j+2;
//        System.out.println(j);



        // 三元操作符

        // 表达式?值1:值2
        // 如果表达式为真 返回值1
        //如果表达式为假 返回值2
        int i = 5;
        int j = 6;

        int k = i < j ? 99 : 88;

        // 相当于
        if (i < j) {
            k = 99;
        } else {
            k = 88;
        }

        System.out.println(k);

    }
}
