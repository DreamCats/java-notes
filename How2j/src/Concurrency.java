/**
 * @program JavaBooks
 * @description: 线程同步问题
 * @author: mf
 * @create: 2019/04/11 15:44
 */

public class Concurrency {
    // 多线程的同步问题指的是多个线程同时修改一个数据的时候，可能导致的问题
    public static void main(String[] args) {
        final Object someObject = new Object();
        final HeroConcurrency gareen = new HeroConcurrency();
        gareen.name = "盖伦";
        gareen.hp = 10000;
        System.out.printf("盖伦的初始血量是 %.0f%n", gareen.hp);

        //多线程同步问题指的是多个线程同时修改一个数据的时候，导致的问题

        //假设盖伦有10000滴血，并且在基地里，同时又被对方多个英雄攻击

        //用JAVA代码来表示，就是有多个线程在减少盖伦的hp
        //同时又有多个线程在恢复盖伦的hp

        //n个线程增加盖伦的hp

        int n = 10000;
        Thread[] addThreads = new Thread[n];
        Thread[] reduceThreads = new Thread[n];
        for (int i = 0; i < n; i++) {
            Thread t = new Thread(){
                public void run(){
                    // 解决方案
                    synchronized (someObject) {
                        gareen.recover();
                    }

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            };
            t.start();
            addThreads[i] = t;

        }

        //n个线程减少盖伦的hp
        for (int i = 0; i < n; i++) {
            Thread t = new Thread(){
                public void run(){
                    synchronized (someObject) {
                        gareen.hurt();
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            };
            t.start();
            reduceThreads[i] = t;
        }
        //等待所有增加线程结束
        for (Thread t : addThreads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        //等待所有减少线程结束
        for (Thread t : reduceThreads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        //代码执行到这里，所有增加和减少线程都结束了

        //增加和减少线程的数量是一样的，每次都增加，减少1.
        //那么所有线程都结束后，盖伦的hp应该还是初始值

        //但是事实上观察到的是：
        System.out.printf("%d个增加线程和%d个减少线程结束后%n盖伦的血量变成了 %.0f%n", n,n,gareen.hp);


        //总体解决思路是： 在增加线程访问hp期间，其他线程不可以访问hp
        // 1. 增加线程获取到hp的值，并进行运算
        // 2. 在运算期间，减少线程试图来获取hp的值，但是不被允许
        // 3. 增加线程运算结束，并成功修改hp的值为10001
        // 4. 减少线程，在增加线程做完后，才能访问hp的值，即10001
        // 5. 减少线程运算，并得到新的值10000

        // synchronized 同步对象概念
        // synchronized表示当前线程，独占 对象 someObject


    }
}


class HeroConcurrency {
    public String name;
    public float hp;
    public int damage;
    //回血
    public void recover(){
        hp=hp+1;
    }
    //掉血
    public void hurt(){
        hp=hp-1;
    }
    public void attackHero(HeroConcurrency h) {
        h.hp-=damage;

        System.out.format("%s 正在攻击 %s, %s的血变成了 %.0f%n",name,h.name,h.name,h.hp);

        if(h.isDead())
            System.out.println(h.name +"死了！");
    }
    public boolean isDead() {
        return 0>=hp?true:false;
    }
}
