import java.util.Stack;

/**
 * @program JavaBooks
 * @description: 20.有效的括号
 * @author: mf
 * @create: 2019/11/07 10:20
 */

/*
题目：https://leetcode-cn.com/problems/valid-parentheses/
类型：栈
难度：easy
 */
/*
输入: "()"
输出: true
输入: "()[]{}"
输出: true
输入: "(]"
输出: false
 */
public class IsValid {
    public static void main(String[] args) {
        String s = "()";
        System.out.println(isValid(s));
    }

    private static boolean isValid(String s) {
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
    private static boolean isSym(char c1, char c2) {
        return (c1 == '(' && c2 == ')') || (c1 == '[' && c2 == ']') || (c1 == '{' && c2 == '}');
    }
}
