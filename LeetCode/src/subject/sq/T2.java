/**
 * @program JavaBooks
 * @description: 用一个队列实现栈
 * @author: mf
 * @create: 2020/03/11 12:24
 */

package subject.sq;

import java.util.LinkedList;
import java.util.Queue;

public class T2 {
    private Queue<Integer> queue;

    public T2() {
        queue = new LinkedList<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        queue.add(x);
        // 加下来的操作是将头部的数据重新加入到队列后边
        // 比如， 1，2 经过以下操作：2，1
        // 比如，2，1，3 经过以下操作：3，2，1
        // 实际上，就是进来一个元素，就把他放到队头
        int cnt = queue.size();
        while(cnt > 1) {
            cnt--;
            queue.add(queue.poll());
        }
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return queue.remove();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue.isEmpty();
    }
    /** Get the top element. */
    public int top() {
        return queue.peek();
    }

    public static void main(String[] args) {
        T2 t2 = new T2();
        t2.push(1);
        t2.push(2);
        t2.push(3);
        System.out.println(t2.pop());
    }
}
