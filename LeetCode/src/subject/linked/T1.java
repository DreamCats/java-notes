/**
 * @program JavaBooks
 * @description: 从尾到头打印链表
 * @author: mf
 * @create: 2020/03/06 18:40
 */

package subject.linked;

import java.util.ArrayList;
import java.util.Stack;

/**
 * 原先：
 * 1->2->3->4
 * 之后：
 * 1<-2<-3<-4
 */
public class T1 {
    ArrayList<Integer> list = new ArrayList();
    /**
     * 递归
     * @param listNode
     * @return
     */
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        if (listNode != null) {
            this.printListFromTailToHead(listNode.next);
            list.add(listNode.val);
        }
        return list;
    }

    /**
     * 栈试试
     * @param listNode
     * @return
     */
    public ArrayList<Integer> printListFromTailToHead2(ListNode listNode) {
        Stack<Integer> stack = new Stack<>();
        while (listNode != null) {
            stack.push(listNode.val);
            listNode = listNode.next;
        }
        while (!stack.empty()) {
            list.add(stack.pop());
        }
        return list;
    }
}
