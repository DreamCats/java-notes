
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program JavaBooks
 * @description: CountDownLatch例子
 * @author: mf
 * @create: 2020/01/09 16:52
 */

public class T33 {

    // 请求的数量
    private static final int threadCount= 30;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(10);
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            service.execute(() -> {
                try {
                    Thread.sleep(500);
                    System.out.println("threadNum: " + threadNum);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        service.shutdown();
        System.out.println("finish...");
    }
}
