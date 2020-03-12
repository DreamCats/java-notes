/**
 * @program JavaBooks
 * @description: 最小栈
 * @author: mf
 * @create: 2020/03/12 14:44
 */

package subject.sq;

import java.util.Stack;

public class T5 {
    private Integer min = Integer.MAX_VALUE;
    private Stack<Integer> stack = new Stack<>();

    public void push(int x) {
        if (x <= min) {
            stack.push(min);
            min = x;
        }
        stack.push(x);
    }

    public void pop() {
        if (stack.pop() == min) {
            min = stack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min;
    }

}
