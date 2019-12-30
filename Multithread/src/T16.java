import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program JavaBooks
 * @description: 用lock和condition进行生产者和消费者
 * @author: mf
 * @create: 2019/12/30 23:28
 */

public class T16<T> {

    private final LinkedList<T> lists = new LinkedList<>();

    private final int MAX = 10;

    private int count = 0;

    private Lock lock = new ReentrantLock();

    private Condition producer = lock.newCondition();

    private Condition consumer = lock.newCondition();

    public void put(T t) {
        try {
            lock.lock();
            while (lists.size() == MAX) {
                producer.await(); // 空间满了， 那就能先站着看手机把。。。
            }
            lists.add(t);
            count++;
            consumer.signalAll(); // 把消费者全部叫醒，吃东西
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public T get() {
        T t = null;
        try {
            lock.lock();
            while (lists.size() == 0) {
                consumer.await();
            }
            t = lists.removeFirst();
            count--;
            producer.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return t;
    }

    public static void main(String[] args) {
        T16<String> t16 = new T16<>();

        // new 四个消费者
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                for (int i1 = 0; i1 < 4; i1++) {
                    System.out.println(t16.get());
                }
            }, "消费者：" + i).start();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 来2个生产者
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int i1 = 0; i1 < 10; i1++) {
                    t16.put(Thread.currentThread().getName() + "产品id：" + i1);
                }
            }, "生产者id：" + i).start();
        }
    }
}
