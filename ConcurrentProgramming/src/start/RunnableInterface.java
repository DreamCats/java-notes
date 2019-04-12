/**
 * @program JavaBooks
 * @description: 启动方式：实现Runnable的run方法
 * @author: mf
 * @create: 2019/04/12 16:07
 */

package start;

public class RunnableInterface {

    public static class RunnableTask implements Runnable {

        @Override
        public void run() {
            System.out.println("I am a child thread");
        }
    }

    public static void main(String[] args) {
        RunnableTask rt = new RunnableTask();
        new Thread(rt).start();
        new Thread(rt).start();
    }
}
