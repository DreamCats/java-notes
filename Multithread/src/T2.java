/**
 * @program JavaBooks
 * @description: 加锁和不加锁输出
 * @author: mf
 * @create: 2019/12/26 21:19
 */

public class T2 implements Runnable{

    private int count = 10;

    @Override
    public /*synchronized*/  void run() {
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

    public static void main(String[] args) {
        T2 t2 = new T2();
        for (int i = 0; i < 5; i++) { // 5个线程共同访问T2这个对象
            new Thread(t2, "thread" + i).start();
        }
    }
}
