/**
 * @program JavaBooks
 * @description: 两个链表的第一个公共节点
 * @author: mf
 * @create: 2020/03/08 20:41
 */

package subject.linked;

import java.util.HashSet;

/**
 *
 */
public class T6 {
    /**
     * 最容易想的是哈希
     * headA走完一圈
     * 开始走headB，判断哪个节点和A相等，即可
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        HashSet<ListNode> set = new HashSet<>();
        while (headA != null) {
            set.add(headA);
            headA = headA.next;
        }
        while (headB != null) {
            if (set.contains(headB)) {
                return headB;
            }
            headB = headB.next;
        }
        return null;
    }

    /**
     * 双指针
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        // 一男一女
        ListNode node1 = headA;
        ListNode node2 = headB;
        // 我走你，你走我，直到相爱
        while (node1 != node2) {
            node1 = node1 == null ? headB : node1.next;
            node2 = node2 == null ? headA : node2.next;
        }
        return node1;
    }
}
