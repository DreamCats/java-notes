import java.util.concurrent.*;

/**
 * @program JavaBooks
 * @description: CyclicBarrier的使用示例
 * @author: mf
 * @create: 2020/01/09 17:17
 */

public class T34 {

    // 请求的数量
    private static final int threadCount = 30;

    // 需要同步的线程数量
    private static final CyclicBarrier cyclicBarrier = new CyclicBarrier(5);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(10);

        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            Thread.sleep(1000);
            service.execute(() -> {
                test(threadNum);
            });
        }
        service.shutdown();
    }

    private static void test(int threadNum) {
        System.out.println("threadNum: " + threadNum + " is ready");
        try {
            cyclicBarrier.await(60, TimeUnit.SECONDS); // 等待60s，保证子线程完全执行结束
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        System.out.println("threadNum: " + threadNum + " finish...");
    }
}
