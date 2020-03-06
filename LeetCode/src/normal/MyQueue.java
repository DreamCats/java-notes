package normal;

import java.util.Stack;

/**
 * @program JavaBooks
 * @description: 232.用栈实现队列
 * @author: mf
 * @create: 2019/11/07 14:55
 */

/*
题目：https://leetcode-cn.com/problems/implement-queue-using-stacks/
类型：栈
难度：easy
 */
/*
MyQueue queue = new MyQueue();

queue.push(1);
queue.push(2);
queue.peek();  // 返回 1
queue.pop();   // 返回 1
queue.empty(); // 返回 false
 */
public class MyQueue {
    private Stack<Integer> in;
    private Stack<Integer> out;
    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue();
        myQueue.push(1);
        myQueue.push(2);
        System.out.println(myQueue.peek());
        System.out.println(myQueue.pop());
        System.out.println(myQueue.empty());
    }

    public MyQueue() {
        in = new Stack<>();
        out = new Stack<>();
    }

    public void push (int x) {
        in.push(x);
    }

    public int pop () {
        if (out.isEmpty()) {
            while (! in.isEmpty()) {
                out.push(in.pop());
            }
        }
        return out.pop();
    }

    public int peek () {
        if (out.isEmpty()) {
            while (! in.empty()) {
                out.push(in.pop());
            }
        }
        return out.peek();
    }

    public boolean empty() {
        return in.isEmpty() && out.isEmpty();
    }
}
