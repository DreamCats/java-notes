import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program JavaBooks
 * @description: 第三种，single
 * @author: mf
 * @create: 2020/01/01 14:56
 */


/**
 * 尽管是线程池中的单个线程，但是比那种每次创建个线程，系统开销大的稍微好点。。。
 */
public class T26 {
    public static void main(String[] args) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            final  int j = i;
            service.execute(() -> {
                System.out.println(j + " " + Thread.currentThread().getName());
            });
        }
        service.shutdown();
    }
}
