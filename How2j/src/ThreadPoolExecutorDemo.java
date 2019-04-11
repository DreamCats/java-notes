import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @program JavaBooks
 * @description: java自带的线程池
 * @author: mf
 * @create: 2019/04/11 20:44
 */

public class ThreadPoolExecutorDemo {
    public static void main(String[] args) throws InterruptedException {

        ThreadPoolExecutor threadPool= new ThreadPoolExecutor(10, 15, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());

        threadPool.execute(new Runnable(){

            @Override
            public void run() {
                // TODO Auto-generated method stub
                System.out.println("任务1");
            }

        });

    }
}
