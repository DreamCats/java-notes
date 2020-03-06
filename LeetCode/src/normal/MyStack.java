package normal;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @program JavaBooks
 * @description: 225.用队列实现栈
 * @author: mf
 * @create: 2019/11/07 15:10
 */

/*
题目：https://leetcode-cn.com/problems/implement-stack-using-queues/
类型：队列
难度：easy
 */
public class MyStack {
    public static void main(String[] args) {
        MyStack myStack = new MyStack();
        myStack.push(1);
        myStack.push(2);
        System.out.println(myStack.pop());
    }
    private Queue<Integer> queue;
//    private LinkedList<Integer> queue;
    public MyStack() {
        queue = new LinkedList<>();

    }

    public void push(int x) {
        queue.add(x);
        int cnt = queue.size();
        while (cnt-- > 1) {
            queue.add(queue.poll());
        }
//        queue.addFirst(x);
    }

    public int pop() {
        return queue.remove();
    }

    public int top() {
        return queue.peek();
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}
