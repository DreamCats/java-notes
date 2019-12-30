import java.util.concurrent.TimeUnit;

/**
 * @program JavaBooks
 * @description: ThreadLocal
 * @author: mf
 * @create: 2019/12/30 23:55
 */

public class T17 {

    private static ThreadLocal<person> tl = new ThreadLocal<>();

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(tl.get()); // 无法得到线程二的new的person
        }).start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tl.set(new person());
        }).start();
    }
}


class person {
    String name = "zhangsan";
}
