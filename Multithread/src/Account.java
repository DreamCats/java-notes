import java.util.concurrent.TimeUnit;

/**
 * @program JavaBooks
 * @description: 对业务写方法加锁，对业务读方法不加锁，容易产生脏读问题
 * @author: mf
 * @create: 2019/12/26 21:41
 */

public class Account {

    String name;

    double balance;

    public synchronized void set(String name, double balance) {
        this.name = name;
        /**
         * 故意设置2秒，让name和balance之间，受到其他事务的影响，产生脏读
         */
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.balance = balance;
    }

    public /*synchronized*/ double getBalance(String name) {
        return this.balance;
    }

    public static void main(String[] args) {
        Account a = new Account();
        new Thread(() -> a.set("zhangsan", 100.0)).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(a.getBalance("zhangsan"));

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(a.getBalance("shangsan"));
    }
}
