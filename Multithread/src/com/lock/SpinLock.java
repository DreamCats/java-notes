/**
 * @program JavaBooks
 * @description: 手写自旋锁
 *              通过CAS操作完成自旋锁，A线程先进来调用mylock方法自己持有锁5秒钟，B随后进来发现当前有线程持有锁，不是null，
 *              所以只能通过自旋等待，知道A释放锁后B随后抢到
 * @author: mf
 * @create: 2020/02/15 12:20
 */

package com.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class SpinLock {

    // 原子引用线程
    AtomicReference<Thread> atomicReference =  new AtomicReference<>();

    public void mylock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + " como in...");
        while (!atomicReference.compareAndSet(null, thread)) {
//            System.out.println("不爽，重新获取一次值瞧瞧...");
        }
    }

    public void  myUnlock() {
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
        System.out.println(Thread.currentThread().getName() + " invoke myUnLock...");
    }

    public static void main(String[] args) {
        SpinLock spinLock = new SpinLock();
        new Thread(() -> {
            spinLock.mylock();
            try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
            spinLock.myUnlock();
        }, "t1").start();
        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }

        new Thread(() -> {
            spinLock.mylock();
            spinLock.myUnlock();
        }, "t2").start();
    }

}
