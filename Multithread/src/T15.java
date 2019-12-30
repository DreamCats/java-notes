import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * @program JavaBooks
 * @description: 写一个固定容量同步器，拥有put和get方法和getCount方法
 * @author: mf
 * @create: 2019/12/30 22:28
 */

/*
能够支持2个生产者线程以及10个消费者线程的阻塞调用
 */
public class T15<T> {

    private final LinkedList<T> lists = new LinkedList<>();

    private final int MAX = 10; // 最多10个元素

    private int count = 0;


    public synchronized void put(T t) {
        while (lists.size() == MAX) { // 想想为什么用while而不是用if
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        lists.add(t);
        count++;
        this.notifyAll(); // 通知所有被wait挂起的线程
    }

    public synchronized T get() {
        T t = null;
        while (lists.size() == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        t = lists.removeFirst();
        count--;
        this.notifyAll(); // 通知所有被wait挂起的线程
        return t;
    }

    public static void main(String[] args) {
        T15<String> t15 = new T15<>();
        // 启动消费者线程
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10; j++) System.out.println("消费者：" + t15.get());
            }, "t15_" + i).start();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 启动生产者线程
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int i1 = 0; i1 < 15; i1++) {
                    t15.put("生产者ID：" + Thread.currentThread().getName() + " 编号：" + i1);
                }
            }, "p" + i).start();
        }
    }
}
