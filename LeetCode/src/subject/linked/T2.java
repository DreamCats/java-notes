/**
 * @program JavaBooks
 * @description: 链表中倒数第k个节点
 * @author: mf
 * @create: 2020/03/06 20:55
 */

package subject.linked;

import java.util.Stack;

/**
 * 1->2->3->4->5     k = 2
 * 返回：
 * 4->5
 */
public class T2 {
    /**
     * 栈，比较容易想得的到
     * @param head
     * @param k
     * @return
     */
    public ListNode getKthFromEnd(ListNode head, int k) {
        Stack<ListNode> stack = new Stack<>();
        while (head != null) {
            stack.push(head);
            head = head.next;
        }
        ListNode listNode= new ListNode(0);
        for (int i = 0; i < k; i++) {
            listNode = stack.pop();
        }
        return listNode;
    }

    /**
     * 双指针
     * @param head
     * @param k
     * @return
     */
    public ListNode getKthFromEnd2(ListNode head, int k) {
        ListNode pNode = head;
        ListNode kNode = head;
        int p1 = 0;
        while (pNode != null) {
            if (p1 >= k) {
                kNode = kNode.next;
            }
            pNode = pNode.next;
            p1++;
        }
        return kNode;
    }
}
