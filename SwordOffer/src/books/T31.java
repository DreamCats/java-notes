package books;

import java.util.Stack;

/**
 * @program JavaBooks
 * @description: 栈的压入、弹出序列
 * @author: mf
 * @create: 2019/09/12 10:16
 */

/*
输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈
的弹出顺序。假设压入栈的所有数字均不相等。例如，序列{1， 2， 3，4， 5}
是某栈的压栈序列，序列{4，5，3，2，1}是该压栈序列对应的一个弹出序列，但
{4，3，5，1，2}就不肯能是该压栈序列的弹出序列。
 */

/*
思路：
用一个Pop的指针即可
每次压栈，取出栈顶去和当前pop的值做比较，若相等，pop++
并且stack弹出栈顶
 */
public class T31 {
    public static void main(String[] args) {
        int[] arrPush = {1, 2, 3, 4, 5};
        int[] arrPop = {4, 5, 3, 2, 1};
        int[] arrPop1 = {4, 3, 5, 1, 2};
        boolean res = isPopOrder(arrPush, arrPop1);
        System.out.println(res);
    }

    private static boolean isPopOrder(int[] arrPush, int[] arrPop) {
        if (arrPush == null || arrPop == null) return false;
        int pPop = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arrPush.length; i++) {
            stack.push(arrPush[i]);
            while (!stack.empty() && stack.peek() == arrPop[pPop]) {
                stack.pop();
                pPop++;
            }
        }

        return stack.empty();
    }
}
