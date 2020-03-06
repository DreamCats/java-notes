package normal;

/**
 * @program JavaBooks
 * @description: 206.反转链表
 * @author: mf
 * @create: 2019/11/05 19:36
 */

/*
题目：https://leetcode-cn.com/problems/reverse-linked-list/comments/
类型：链表
难度：easy
 */

public class ReverseList {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5};
        ListNode head = ListNode.setListNode(arr);
        ListNode l = reverseList2(head);
        ListNode.printNode(l);
    }

    /**
     * 递归优点绕，还是用循环好理解一些
     * @param head
     * @return
     */
    private static ListNode reverseList(ListNode head) {
        ListNode pre = null; // 当前节点的前一个节点
        ListNode cur = head; // 当前节点
        while (cur != null) {
            ListNode nextTemp = cur.next; // 先保存当前节点的下一个节点
            cur.next = pre; // 将当前节点的下个节点指向前一个节点pre
            pre = cur; // 将pre往后移动指向当前节点
            cur = nextTemp; // 将当前指点指针往后移动next
        }
        return pre;
    }

    /**
     * 尾递归
     * @param head
     * @return
     */
    private static ListNode reverseList2(ListNode head) {
        return reverse(null, head);
    }
    private static ListNode reverse(ListNode pre, ListNode cur) {
        if (cur == null) return pre;
        ListNode next = cur.next; // 保存当前节点的下个节点的指针
        cur.next = pre;
        return reverse(cur, next);
    }
}
