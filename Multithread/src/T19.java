/**
 * @program JavaBooks
 * @description: 售票程序
 * @author: mf
 * @create: 2019/12/31 18:36
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

/**
 * 有N张火车票，每张票都有一个编号
 * 同时有10个窗口对外售票
 *
 */
public class T19 {

    private static List<String> tickets = new ArrayList<>();

    static {
        for (int i = 0; i < 1000; i++) {
            tickets.add("票编号：" + i);
        }
    }

    public static void main(String[] args) {
        // 10个窗口售票
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (tickets.size() > 0) {
                    // size和remove不是原子性的
                    System.out.println("销售了--" + tickets.remove(0));
                }
            }).start();
        }
    }
}

/**
 * 用vector安全容器
 */
class T19_1 {

    private static Vector<String> lists = new Vector<>();

    static {
        for (int i = 0; i < 1000; i++) {
            lists.add("编号：" + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (lists.size() > 0) {
                    // 加点延迟
                    try {
                        TimeUnit.MILLISECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // 但是size和remove之间不是原子性的
                    System.out.println("销售了--" + lists.remove(0));
                }
            }).start();
        }
    }
}
