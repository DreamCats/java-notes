import java.util.Arrays;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/**
 * @program JavaBooks
 * @description: ConcurrentMap
 * @author: mf
 * @create: 2019/12/31 19:59
 */

public class T20 {

    public static void main(String[] args) {
//        Map<String, String> map = new Hashtable<>();
        Map<String, String> map = new ConcurrentHashMap<>();
        Random r = new Random();
        Thread[] ths = new Thread[100]; // 100个线程
        CountDownLatch latch = new CountDownLatch(ths.length);
        long start = System.currentTimeMillis();
        for (int i = 0; i < ths.length; i++) {
            ths[i] = new Thread(() -> {
                for (int i1 = 0; i1 < 10000; i1++) {
                    map.put("a" + r.nextInt(100000), "a" + r.nextInt(100000));
                }
                latch.countDown();
            });
        }
        Arrays.asList(ths).forEach(t -> t.start());
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println(end -start);
    }
}
