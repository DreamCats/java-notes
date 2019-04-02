/**
 * @program JavaBooks
 * @description: Final
 * @author: mf
 * @create: 2019/04/02 11:21
 */

public class FinalDemo {
    // 准确的描述是 当一个变量被final修饰的时候，该变量只有一次赋值的机会
    public void method1() {
        final int i = 5;

//        i = 10; //i在第4行已经被赋值过了，所以这里会出现编译错误

    }


    // 如果在声明的时候未赋值，那么可以在后面代码进行唯一的一次赋值

    public void method2() {
        final int i;

        i = 10; //i在第4行，只是被声明，但是没有被赋值，所以在这里可以进行第一次赋值

//        i = 11; //i在第6行已经被赋值过了，所以这里会出现编译错误

    }

    // final 除了修饰变量，还可以修饰类，修饰方法

    // 如果final修饰的是参数
    public void method3(final int j) {
//        j = 5; //这个能否执行？
    }
}
