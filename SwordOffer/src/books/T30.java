package books;

import java.util.Stack;

/**
 * @program JavaBooks
 * @description: 包含min函数的栈
 * @author: mf
 * @create: 2019/09/11 10:12
 */

/*
定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素
的min函数。在干栈中，调用min、push及pop的时间复杂度都是o（1）
 */

/*
思路
一般来个辅助栈
好多题， 都需要辅助空间的
辅助栈的话，就很简单了。思路也很明了了。
 */
public class T30 {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        Stack<Integer> helpStack = new Stack<>();

        dataPush(stack, helpStack, 3);
        dataPush(stack, helpStack, 2);
        dataPush(stack, helpStack, 1);
        dataPush(stack, helpStack, 5);
        dataPush(stack, helpStack, 0);
//        dataPop(stack, helpStack);
        Integer value = dataMin(stack, helpStack);
        System.out.println(value);
    }

    private static Integer dataMin(Stack<Integer> stack, Stack<Integer> helpStack) {
        if (stack.isEmpty() || helpStack.isEmpty()) return null;
        return helpStack.peek();
    }

    private static void dataPop(Stack<Integer> stack, Stack<Integer> helpStack) {
        if (stack.empty() || helpStack.empty()) return;
        stack.pop();
        helpStack.pop();
    }

    private static void dataPush(Stack<Integer> stack, Stack<Integer> helpStack, int value) {
        stack.push(value);
        if (helpStack.empty() || value < helpStack.peek()) {
            helpStack.push(value);
        } else {
            helpStack.push(helpStack.peek());
        }
    }
}
