/**
 * @program JavaBooks
 * @description: 测试
 * @author: mf
 * @create: 2020/05/24 00:04
 */

package Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Test {

//    int count = 0;

    AtomicInteger count = new AtomicInteger(0);
    void m() {
        for (int i = 0; i < 1000; i++) {
            count.incrementAndGet();
        }
    }

    public static void main(String[] args) {
        Test t1 = new Test();
        for (int i = 0; i < 10; i++) {
            // 开两个线程
            new Thread(t1::m, "Thread" + i).start();
        }
        // 延迟等待一下
        try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println(t1.count);
    }
}
