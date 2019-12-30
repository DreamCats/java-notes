/**
 * @program JavaBooks
 * @description: 通信
 * @author: mf
 * @create: 2019/12/30 15:54
 */

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 实现一个容器，提供两个方法add size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5个时，线程2给出提示并结束
 */

public class T11 {

//    List lists = new ArrayList();

    // 解决方法1， 添加volatile，使t2能够得到通知
    volatile List lists = new ArrayList();

    public void add(Object o) {
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }

    public static void main(String[] args) {
        T11 t11 = new T11();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                t11.add(new Object());
                System.out.println("add " + i);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1").start();


        new Thread(() -> {
            while (true) {
                if (t11.size() == 5) {
                    break;
                }
            }
            System.out.println("t2结束");
        }, "t2").start();
    }
}


/**
 * 使用wait和notify优化
 */
class T11_1 {

    volatile List lists = new ArrayList();

    public void add(Object o) {
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }

    public static void main(String[] args) {
        T11_1 t11_1 = new T11_1();

        final Object lock
    }
}
