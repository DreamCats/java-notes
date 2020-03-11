/**
 * @program JavaBooks
 * @description: 用两个栈实现队列
 * @author: mf
 * @create: 2020/03/11 12:12
 */

package subject.sq;

import java.util.Stack;

public class T1 {
    private Stack<Integer> in;
    private Stack<Integer> out;
    /** Initialize your data structure here. */
    public T1() {
        in = new Stack<>();
        out = new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        in.push(x); // 输入栈，不断的push元素，没啥特殊的
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        // 弹出栈在弹出前，先判断栈是否有元素，如果为空就从in栈中导入到out栈
        if(out.isEmpty()) {
            while(! in.isEmpty()) {
                out.push(in.pop());
            }
        }
        return out.pop();
    }

    /** Get the front element. */
    public int peek() {
        if(out.isEmpty()) {
            while(! in.isEmpty()) {
                out.push(in.pop());
            }
        }
        return out.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return in.isEmpty() && out.isEmpty();
    }
}
