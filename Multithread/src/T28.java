import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @program JavaBooks
 * @description: 第五种，WorkStealingPool
 * @author: mf
 * @create: 2020/01/01 15:05
 */


public class T28 {
    public static void main(String[] args) throws IOException {
        // 默认核数
        ExecutorService service = Executors.newWorkStealingPool();

        service.execute(new R(1000));
        service.execute(new R(2000));
        service.execute(new R(2000));
        service.execute(new R(2000));
        service.execute(new R(2000));

        // 由于产生的是精灵线程(守护线程、后台线程)，主线程如果不阻塞的话，看不到输出
        System.in.read();

        service.shutdown();
    }

    private static class R implements Runnable {

        int time;

        public R(int time) {
            this.time = time;
        }

        @Override
        public void run() {
            try {
                TimeUnit.MILLISECONDS.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        }
    }
}
