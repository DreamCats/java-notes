import java.util.*;

/**
 * @program JavaBooks
 * @description: 有效的括号
 * @author: mf
 * @create: 2019/04/11 17:09
 */

public class IsValid {
    public static void main(String[] args) {
        SolutionIsValid s = new SolutionIsValid();
        boolean res = s.isValid("{[]}");
        boolean res1 = s.isValid("()[]{}");
        System.out.println(res);
        System.out.println(res1);
    }
}

class SolutionIsValid {
    public boolean isValid(String s) {
        if (s.length() == 0) return true;
        Stack<Character> stack = new Stack<>();
        Map<Character,Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{') {
                stack.push(s.charAt(i));
            }
            else {
                if (stack.empty()) {
                    return false;
                }
                char top = stack.pop();
                if (map.get(top) != s.charAt(i)) {
                    return false;
                }
            }
        }

        if (!stack.isEmpty()) {
            return false;
        }
        return true;
    }
}