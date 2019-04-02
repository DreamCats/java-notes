/**
 * @program JavaBooks
 * @description: 作用域
 * @author: mf
 * @create: 2019/04/02 11:15
 */

public class Scope {
    int i = 1;
    int j = i;  //其他的属性可以访问i
    public static void main(String[] args) {

    new Scope().method4(5);

    }
    public void method1() {
        System.out.println(i);
    }

    public void method2() {
        System.out.println(i);
    }

    // 如果一个变量，是声明在一个方法上的，就叫做参数
    // 其他方法不能访问该参数
    // 类里面也不能访问该参数
    public void method1(int i){ //参数i的作用域即方法method1
        System.out.println(i);
    }

    // 声明在方法内的变量，叫做局部变量
    // 其作用域在声明开始的位置，到其所处于的块结束位置
    public void method3() {
        int i  = 5;  //其作用范围是从声明的第4行，到其所处于的块结束12行位置
        System.out.println(i);
        {            //子块
            System.out.println(i); //可以访问i
            int j = 6;
            System.out.println(j); //可以访问j
        }
        System.out.println(j); //不能访问j,因为其作用域到第10行就结束了
    }

    public void method4(int i){ //参数也是i
        System.out.println(i);
    }
}
