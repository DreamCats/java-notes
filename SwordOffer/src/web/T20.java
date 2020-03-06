package web; /**
 * @program LeetNiu
 * @description: 包含min函数的栈
 * @author: mf
 * @create: 2020/01/12 23:33
 */

import java.util.Stack;

/**
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））。
 * 用两个栈把
 */
public class T20 {

    private Stack<Integer> stack = new Stack<>();

    private Stack<Integer> stack2 = new Stack<>();

    public void push(int node) {
        stack.push(node);
        if (stack2.isEmpty() || node < stack2.peek()) {
            stack2.push(node);
        } else {
            stack2.push(stack2.peek());
        }
    }

    public void pop() {
        stack.pop();
        stack2.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int min() {
        return stack2.peek();
    }
}
