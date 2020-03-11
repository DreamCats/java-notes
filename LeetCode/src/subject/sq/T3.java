/**
 * @program JavaBooks
 * @description: 两个队列实现栈
 * @author: mf
 * @create: 2020/03/11 13:07
 */

package subject.sq;

import java.util.LinkedList;
import java.util.Queue;

public class T3 {
    private Queue<Integer> q1 = new LinkedList<>();
    private Queue<Integer> q2 = new LinkedList<>();
    private int top;

    public void push(int x) {
        // 添加顺序1, 2
        q2.add(x); // 2
        top = x;
        while (!q1.isEmpty()) {
            q2.add(q1.remove()); //2, 1
        }
        Queue<Integer> temp = q1; // q1 null
        q1 = q2;
        q2 = temp;
    }

    public int pop() {
        int res = top;
        q1.remove();
        if (!q1.isEmpty()) {
            top = q1.peek();
        }
        return  res;
    }
}
