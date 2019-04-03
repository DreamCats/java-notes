/**
 * @program JavaBooks
 * @description: 控制流程
 * @author: mf
 * @create: 2019/04/03 10:05
 */

public class ControlProcess {

   public static void main() {
       // if
       boolean b = true;
       if (b) {
           System.out.println("yes");
       }


       // if 一个坑
       // if后面有一个分号; 而分号也是一个完整的表达式
       // 如果b为true，会执行这个分号，然后打印yes
       // 如果b为false，不会执行这个分号，然后打印yes
       // 这样，看上去无论如何都会打印yes
       if (b);
       System.out.println("yes1");


       // if else

       if (b)
           System.out.println("yes");
       else
           System.out.println("no");


       // else if

       //如果只使用 if,会执行4次判断
       int i = 2;
       if (i==1)
           System.out.println(1);
       if (i==2)
           System.out.println(2);
       if (i==3)
           System.out.println(3);
       if (i==4)
           System.out.println(4);

       //如果使用else if, 一旦在18行，判断成立， 20行和22行的判断就不会执行了，节约了运算资源
       if (i==1)
           System.out.println(1);
       else if (i==2)
           System.out.println(2);
       else if (i==3)
           System.out.println(3);
       else if (i==4)
           System.out.println(4);


        //  练习 switch

       //如果使用if else
       int day = 5;
       if (day==1)
           System.out.println("星期一");

       else if (day==2)
           System.out.println("星期二");
       else if (day==3)
           System.out.println("星期三");
       else if (day==4)
           System.out.println("星期四");
       else if (day==5)
           System.out.println("星期五");
       else if (day==6)
           System.out.println("星期六");
       else if (day==7)
           System.out.println("星期天");
       else
           System.out.println("这个是什么鬼？");

       //如果使用switch
       switch(day){
           case 1:
               System.out.println("星期一");
               break;
           case 2:
               System.out.println("星期二");
               break;
           case 3:
               System.out.println("星期三");
               break;
           case 4:
               System.out.println("星期四");
               break;
           case 5:
               System.out.println("星期五");
               break;
           case 6:
               System.out.println("星期六");
               break;
           case 7:
               System.out.println("星期天");
               break;
           default:
               System.out.println("这个是什么鬼？");
       }




       // dowhile   条件为true时 重复执行，至少会执行一次
       // while 条件为true时 重复执行
       //打印0到4
       int k = 0;
       while(k<5){
           System.out.println(k);
           k++;
       }

       int j = 0;
       do{
           System.out.println(j);
           j++;
       } while(j<5);


       // for
       //使用for打印0到4
       for (int m = 0; m < 5; m++) {
           System.out.println("for  循环输出的"+m);
       }


       // continue
       //打印单数
       for (int n = 0; n < 10; n++) {
           if(0==n%2)
               continue; //如果是双数，后面的代码不执行，直接进行下一次循环

           System.out.println(n);
       }

       // break
       //打印单数
       for (int p = 0; p < 10; p++) {
           if(0==p%2)
               break; //如果是双数，直接结束循环

           System.out.println(p);
       }
   }

}
