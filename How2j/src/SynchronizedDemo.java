import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program JavaBooks
 * @description: synchronized关键字
 * @author: mf
 * @create: 2019/04/11 16:04
 */

public class SynchronizedDemo {
    public static String now(){
        return new SimpleDateFormat("HH:mm:ss").format(new Date());
    }

    public static void main(String[] args) {
        final Object someObject = new Object();
        Thread t1 = new Thread(){
            public void run(){
                try {
                    System.out.println( now()+" t1 线程已经运行");
                    System.out.println( now()+this.getName()+ " 试图占有对象：someObject");
                    synchronized (someObject) {

                        System.out.println( now()+this.getName()+ " 占有对象：someObject");
                        Thread.sleep(5000);
                        System.out.println( now()+this.getName()+ " 释放对象：someObject");
                    }
                    System.out.println(now()+" t1 线程结束");
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        };
        t1.setName(" t1");
        t1.start();
        Thread t2 = new Thread(){
            public void run(){
                try {
                    System.out.println( now()+" t2 线程已经运行");
                    System.out.println( now()+this.getName()+ " 试图占有对象：someObject");
                    synchronized (someObject) {
                        System.out.println( now()+this.getName()+ " 占有对象：someObject");
                        Thread.sleep(5000);
                        System.out.println( now()+this.getName()+ " 释放对象：someObject");
                    }
                    System.out.println(now()+" t2 线程结束");
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        };
        t2.setName(" t2");
        t2.start();
    }
}
