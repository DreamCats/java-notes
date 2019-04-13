/**
 * @program JavaBooks
 * @description: 线程线程结束的join方法
 * @author: mf
 * @create: 2019/04/13 19:40
 */

package join;

public class ThreadJoinTest1 {
    public static void main(String[] args) throws InterruptedException {
        Thread threadOne = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("child threadOne over!");
            }

        });

        Thread threadTwo = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("child threadTwo over!");
            }
        });

        // 启动自线程
        threadOne.start();
        threadTwo.start();
        System.out.println("wait all child thread over!");

        // 等待子线程执行完毕，返回
        threadOne.join();
        threadTwo.join();

        System.out.println("all child thread over!");
    }
}
