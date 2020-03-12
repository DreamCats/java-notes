/**
 * @program JavaBooks
 * @description: 最小栈（两个栈实现）
 * @author: mf
 * @create: 2020/03/12 11:53
 */

package subject.sq;

import java.util.Stack;

public class T4 {
    private Stack<Integer> dataStack = new Stack<>();
    private Stack<Integer> minStack = new Stack<>();
    private Integer min = Integer.MAX_VALUE;

    public void push(int x) {
        dataStack.push(x);
        min = Math.min(x, min);
        minStack.push(min);
    }

    public void pop() {
        dataStack.pop();
        minStack.pop();
        min = minStack.isEmpty() ? Integer.MAX_VALUE : minStack.peek();
    }

    public int top() {
        return dataStack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }

}
