package web; /**
 * @program LeetNiu
 * @description: 链表中倒数第k个结点
 * @author: mf
 * @create: 2020/01/10 15:11
 */

import java.util.Stack;

/**
 * 输入一个链表，输出该链表中倒数第k个结点。
 */
public class T14 {
    /**
     * 双指针
     * @param head
     * @param k
     * @return
     */
    public ListNode FindKthToTail(ListNode head,int k) {
        // 判断
        if (head == null || k <= 0) return null;
        ListNode pNode = head;
        ListNode kNode = head;
        int p1 = 0;
        while (pNode != null) {
            if (p1 > k - 1){
                kNode = kNode.next;
            }
            pNode = pNode.next;
            p1++;
        }
        if (p1 >= k) {
            return kNode;
        }
        return null;
    }

    /**
     * 栈
     * @param head
     * @param k
     * @return
     */
    public ListNode FindKthToTail2(ListNode head,int k) {
        // 判断
        if (head == null || k <= 0) return null;
        Stack<ListNode> stack = new Stack<>();
        while (head != null) {
            stack.push(head);
            head = head.next;
        }
        int temp = 0;
        while (!stack.empty()) {
            ListNode listNode = stack.pop();
            temp++;
            if (temp == k) {
                return listNode;
            }
        }
        return null;
    }
}
