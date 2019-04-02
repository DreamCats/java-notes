import java.util.Scanner;

/**
 * @program JavaBooks
 * @description: Scanner的使用
 * @author: mf
 * @create: 2019/04/02 19:08
 */

public class ScannerDemo {


    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);
//        System.out.println("请输入一个整数：");
//        int a = s.nextInt();
//        System.out.println("第一个整数："+ a);
//        System.out.println("请继续输入一个整数：");
//        int b = s.nextInt();
//        System.out.println("第二个整数："+b);
//
//        System.out.println("请输入一个浮点数：");
//        float c = s.nextFloat();
//        System.out.println("读取的浮点数的值是："+c);
          // 需要注意的是，如果在通过nextInt()读取了整数后，再接着读取字符串，读出来的是回车换行:"\r\n",因为nextInt仅仅读取数字信息，而不会读取回车换行"\r\n".


//        System.out.println("请输入一串字符串：");
//        String d = s.nextLine();
//        System.out.println("读取的字符串是："+d);


        int i = s.nextInt();
        System.out.println("读取的整数是"+ i);
//        String rn = s.nextLine();
        String a = s.nextLine();
        System.out.println("读取的字符串是："+a);

    }


}
