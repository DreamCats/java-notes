import java.util.Queue;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @program JavaBooks
 * @description: 利用容器LinkedBlockingQueue生产者消费者
 * @author: mf
 * @create: 2019/12/31 22:26
 */

public class T21 {

//    private static Queue<String> strs = new ConcurrentLinkedDeque<>();

//    private static BlockingQueue<String> strs = new ArrayBlockingQueue<>(10); // 有界队列

//    private static LinkedTransferQueue<String> strs = new LinkedTransferQueue<>(); // 更高的高并发，先找消费者

    private static BlockingQueue<String> strs = new LinkedBlockingDeque<>(); // 无界队列

    private static Random r  = new Random();

    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    strs.put("a" + i);
                    TimeUnit.MILLISECONDS.sleep(r.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "p1").start();

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                for (;;) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " take -" + strs.take());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "c" + i).start();
        }
    }
}
