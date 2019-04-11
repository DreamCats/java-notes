/**
 * @program JavaBooks
 * @description: 线程
 * @author: mf
 * @create: 2019/04/11 12:56
 */

public class ThreadDemo {
    // 进程：启动一个LOL.exe就叫一个进程。 接着又启动一个DOTA.exe，这叫两个进程。
    // 线程：线程是在进程内部同时做的事情，比如在LOL里，有很多事情要同时做，比如"盖伦” 击杀“提莫”，同时“赏金猎人”又在击杀“盲僧”，这就是由多线程来实现的。
    public static void main(String[] args) {

        HeroThread gareen = new HeroThread();
        gareen.name = "盖伦";
        gareen.hp = 616;
        gareen.damage = 50;

        HeroThread teemo = new HeroThread();
        teemo.name = "提莫";
        teemo.hp = 300;
        teemo.damage = 30;

        HeroThread bh = new HeroThread();
        bh.name = "赏金猎人";
        bh.hp = 500;
        bh.damage = 65;

        HeroThread leesin = new HeroThread();
        leesin.name = "盲僧";
        leesin.hp = 455;
        leesin.damage = 80;
//
//        //盖伦攻击提莫
//        while(!teemo.isDead()){
//            gareen.attackHero(teemo);
//        }
//
//        //赏金猎人攻击盲僧
//        while(!leesin.isDead()){
//            bh.attackHero(leesin);
//        }


        // 利用线程
         // 继承 thread
//        KillThread killThread1 = new KillThread(gareen,teemo);
//        killThread1.start();
//        KillThread killThread2 = new KillThread(bh,leesin);
//        killThread2.start();
            // 实现runnable接口
//        Battle battle1 = new Battle(gareen,teemo);
//        new Thread(battle1).start();
//        Battle battle2 = new Battle(bh,leesin);
//        new Thread(battle2).start();

        // 匿名类
        //匿名类
        Thread t1= new Thread(){
            public void run(){
                //匿名类中用到外部的局部变量teemo，必须把teemo声明为final
                //但是在JDK7以后，就不是必须加final的了
                while(!teemo.isDead()){
                    gareen.attackHero(teemo);
                }
            }
        };

        t1.start();

        Thread t2= new Thread(){
            public void run(){
                while(!leesin.isDead()){
                    bh.attackHero(leesin);
                }
            }
        };
        t2.start();

    }
}


class HeroThread {
    public String name;
    public float hp;

    public int damage;
    public void attackHero(HeroThread h) {
        try {
            //为了表示攻击需要时间，每次攻击暂停1000毫秒
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        h.hp-=damage;
        System.out.format("%s 正在攻击 %s, %s的血变成了 %.0f%n",name,h.name,h.name,h.hp);

        if(h.isDead())
            System.out.println(h.name +"死了！");
    }
    public boolean isDead() {
        return 0 >= hp? true : false;
    }
}


// 创建多线程-继承线程类

class KillThread extends Thread {
    private HeroThread h1;
    private HeroThread h2;

    public KillThread(HeroThread h1, HeroThread h2){
        this.h1 = h1;
        this.h2 = h2;
    }

    @Override
    public void run() {
        while (! h2.isDead()) {
            h1.attackHero(h2);
        }
    }
}

// 创建多线程-实现Runnable接口

class Battle implements Runnable {

    private HeroThread h1;
    private HeroThread h2;
    public Battle(HeroThread h1, HeroThread h2){
        this.h1 = h1;
        this.h2 = h2;
    }

    @Override
    public void run() {
        while (! h2.isDead()) h1.attackHero(h2);
    }
}

// 1. 继承Thread类
// 2. 实现Runnable接口
// 3. 匿名类的方式