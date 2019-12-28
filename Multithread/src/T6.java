import java.util.concurrent.TimeUnit;

/**
 * @program JavaBooks
 * @description: volatile关键字
 * @author: mf
 * @create: 2019/12/28 22:54
 */

public class T6 {

    /*volatile*/ boolean running = true; // 对比一下有无valotile的情况下，整个程序运行结果的区别

    void m() {
        System.out.println("m start");
        while (running) {
            // 啥都没得
//            try {
//                TimeUnit.SECONDS.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
        System.out.println("m end ...");
    }

    public static void main(String[] args) {
        T6 t6 = new T6();

        new Thread(() -> t6.m(), "t1").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t6.running = false;
    }
}
