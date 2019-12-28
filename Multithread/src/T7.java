import java.util.ArrayList;
import java.util.List;

/**
 * @program JavaBooks
 * @description: volatile并不能保证的多线程的一致性
 * @author: mf
 * @create: 2019/12/28 23:46
 */

public class T7 {



    /*volatile*/ int count = 0;

    synchronized void m() {
        for (int i = 0; i < 1000; i++) {
            count++;
        }
    }

    public static void main(String[] args) {
        T7 t7 = new T7();

        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(t7::m, "thread-" + i));
        }

        threads.forEach((o) -> o.start());

        threads.forEach((o) -> {
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(t7.count);
    }
}
