/**
 * @program JavaBooks
 * @description: 线程方法
 * @author: mf
 * @create: 2019/04/11 13:25
 */

public class Threadmethods {
    // 线程方法
    // 1。 当前线程暂停
    // Thread.sleep(1000); 表示当前线程暂停1000毫秒 ，其他线程不受影响
    // Thread.sleep(1000); 会抛出InterruptedException 中断异常，因为当前线程sleep的时候，有可能被停止，这时就会抛出 InterruptedException


    // 2。 加入到当前线程中
    // 所有进程，至少会有一个线程即主线程，即main方法开始执行，就会有一个看不见的主线程存在。
//    Thread t1= new Thread(){
//        public void run(){
//            while(!teemo.isDead()){
//                gareen.attackHero(teemo);
//            }
//        }
//    };
//
//        t1.start();
//
//    //代码执行到这里，一直是main线程在运行
//        try {
//        //t1线程加入到main线程中来，只有t1线程运行结束，才会继续往下走
//        t1.join();
//    } catch (InterruptedException e) {
//        // TODO Auto-generated catch block
//        e.printStackTrace();
//    }
//
//    Thread t2= new Thread(){
//        public void run(){
//            while(!leesin.isDead()){
//                bh.attackHero(leesin);
//            }
//        }
//    };
//    //会观察到盖伦把提莫杀掉后，才运行t2线程
//        t2.start();


    // 3。 当线程处于竞争关系的时候，优先级高的线程会有更大的几率获得CPU资源
//        t1.setPriority(Thread.MAX_PRIORITY);
//        t2.setPriority(Thread.MIN_PRIORITY);
//        t1.start();
//        t2.start();


    //4。 临时暂停，使得t1可以占用CPU资源
//        Thread.yield();


    // 5。守护线程
    // 守护线程的概念是： 当一个进程里，所有的线程都是守护线程的时候，结束当前进程。
    // 守护线程通常会被用来做日志，性能统计等工作。
}
