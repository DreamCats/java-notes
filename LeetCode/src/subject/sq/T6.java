/**
 * @program JavaBooks
 * @description: 有效的括号
 * @author: mf
 * @create: 2020/03/12 15:21
 */

package subject.sq;

import java.util.Stack;

public class T6 {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (stack.size() == 0) {
                stack.push(c);
            } else if (isSym(stack.peek(), c)) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        return stack.size() == 0;
    }

    public boolean isSym(char c1, char c2) {
        return (c1 == '(' && c2 == ')')
                || (c1 == '[' && c2 == ']')
                || (c1 == '{' && c2 == '}');
    }
}
