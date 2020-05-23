/**
 * @program JavaBooks
 * @description: 测试
 * @author: mf
 * @create: 2020/05/24 00:04
 */

package Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test{
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(1);
        threadPool.submit(() -> {
            System.out.println("dream");
        });
        threadPool.shutdown();
    }
}
