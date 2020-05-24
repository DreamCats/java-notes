/**
 * @program JavaBooks
 * @description: 测试
 * @author: mf
 * @create: 2020/05/24 00:04
 */

package Test;

import java.util.concurrent.CountDownLatch;

public class Test {
    private CountDownLatch latch;

    public Test(CountDownLatch latch) {
        this.latch = latch;
    }

    void m() {
        System.out.println(Thread.currentThread().getName());
        latch.countDown();
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        Test t1 = new Test(countDownLatch);
        for (int i = 0; i < 5; i++) {
            new Thread(t1::m, "Thread " + i).start();
        }
        countDownLatch.await();
        System.out.println("main thread");
    }
}
