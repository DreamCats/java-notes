import java.util.LinkedList;
import java.util.Queue;

/**
 * @program JavaBooks
 * @description: LinkedList
 * @author: mf
 * @create: 2019/04/10 10:06
 */

public class LinkedListDemo {
    // 序列分先进先出FIFO,先进后出FILO
    // FIFO在Java中又叫Queue 队列
    // FILO在Java中又叫Stack 栈
    // 与ArrayList一样，LinkedList也实现了List接口，诸如add,remove,contains等等方法。
    // 除了实现了List接口外，LinkedList还实现了双向链表结构Deque
    // 什么是链表结构: 与数组结构相比较，数组结构，就好像是电影院，每个位置都有标示，每个位置之间的间隔都是一样的。
    // 而链表就相当于佛珠，每个珠子，只连接前一个和后一个，不用关心除此之外的其他佛珠在哪里。
    public static void main(String[] args) {
        //LinkedList是一个双向链表结构的list
        LinkedList<String> ll = new LinkedList<>();

        //所以可以很方便的在头部和尾部插入数据
        //在最后插入新的英雄
        ll.addLast("a");
        ll.addLast("b");
        ll.addLast("c");
        ll.addLast("d");
        System.out.println(ll);

        //在最前面插入新的英雄
        ll.addFirst("f");
        System.out.println(ll);

        //查看最前面的英雄
        System.out.println(ll.getFirst());
        //查看最后面的英雄
        System.out.println(ll.getLast());

        //查看不会导致英雄被删除
        System.out.println(ll);
        //取出最前面的英雄
        System.out.println(ll.removeFirst());

        //取出最后面的英雄
        System.out.println(ll.removeLast());

        //取出会导致英雄被删除
        System.out.println(ll);


        // LinkedList 除了实现了List和Deque外，还实现了Queue接口(队列)。
        // Queue是先进先出队列 FIFO，常用方法：
        // offer 在最后添加元素
        // poll 取出第一个元素
        // peek 查看第一个元素

        Queue<String> q = new LinkedList<>();

        // 初始化队列 在最后添加元素
        System.out.println("初始化队列：\t");
        q.offer("1");
        q.offer("2");
        q.offer("3");

        System.out.println(q);
        System.out.print("把第一个元素取poll()出来:\t");
        //取出第一个Hero，FIFO 先进先出
        String s1  = q.poll();
        System.out.println(s1);

        System.out.print("取出第一个元素之后的队列:\t");
        System.out.println(q);

        // 把第一个拿出来看看， 但不取出来
        s1 = q.peek();
        System.out.print("查看peek()第一个元素:\t");
        System.out.println(s1);
        System.out.print("查看并不会导致第一个元素被取出来:\t");
        System.out.println(q);

    }
}
