/**
 * @program JavaBooks
 * @description: 之前的写法
 * @author: mf
 * @create: 2020/08/04 21:11
 */

package lmd;

public class Test1 {
    public static void main(String[] args) {
        // jdk7 以前的写法
        // 匿名类
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World");
            }
        }).start();

        // jdk8 lambda
        new Thread(() -> System.out.println("Hello World...")).start();

        new Thread(() -> {
            System.out.println("Hello");
            System.out.println("World");
        }).start();
    }
}
