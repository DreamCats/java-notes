/**
 * @program JavaBooks
 * @description: 启动方式：FutureTask
 * @author: mf
 * @create: 2019/04/12 16:23
 */

package start;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskDemo {

    // 创建任务类
    public static class CallerTask implements Callable<String> {

        @Override
        public String call() throws Exception {
            return "Hello";
        }
    }

    public static void main(String[] args) throws InterruptedException{
        // 创建异步任务
        FutureTask<String> futureTask = new FutureTask<>(new CallerTask());
        // 启动线程
        new Thread(futureTask).start();
        try {
            // 等待任务执行完毕，并返回结果
            String result = futureTask.get();
            System.out.println(result);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
