/**
 * @program JavaBooks
 * @description: 160.相交链表
 * @author: mf
 * @create: 2019/11/05 10:36
 */
/*
题目：https://leetcode-cn.com/problems/intersection-of-two-linked-lists/
难度：easy
类型：链表
 */

/*
A:          a1 → a2
                    ↘
                      c1 → c2 → c3
                    ↗
B:    b1 → b2 → b3
 */
public class GetIntersectionNode {
    public static void main(String[] args) {

    }

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode l1 = headA, l2 = headB;
        while (l1 != l2) {
            l1 = (l1 == null) ? headB : l1.next;
            l2 = (l2 == null) ? headA : l2.next;
        }
        return l1;
    }

}
