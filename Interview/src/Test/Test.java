/**
 * @program JavaBooks
 * @description: 测试
 * @author: mf
 * @create: 2020/05/24 00:04
 */

package Test;

import java.util.concurrent.CountDownLatch;

public class Test {
    volatile int count = 0;
    private CountDownLatch latch;

    public Test(CountDownLatch latch) {
        this.latch = latch;
    }

    void m() {
        for (int i = 0; i < 10000; i++) {
            count++;
        }
        latch.countDown();
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(20);
        Test t1 = new Test(latch);
        for (int i = 0; i < 20; i++) {
            new Thread(t1::m, "Thread " + i).start();
        }
        latch.await();
        System.out.println(t1.count);
    }
}
