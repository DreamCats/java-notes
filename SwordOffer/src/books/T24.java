package books;

/**
 * @program JavaBooks
 * @description: 反转链表
 * @author: mf
 * @create: 2019/09/05 09:55
 */

/*
定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表
头节点。
 */

/*
思路：
设定三个指针，pre p next， 交换即可，但交换之前检查next是否为空，以防锻炼
 */
public class T24 {
    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);

        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;

        ListNode headNode = reverseListNode(listNode1);
        System.out.println(headNode.value);
    }

    /**
     * 循环
     * @param headNode
     * @return
     */
    private static ListNode reverseListNode(ListNode headNode) {
        if (headNode == null) return  null;
        ListNode pre = null; // 当前节点的前一个节点
        ListNode cur = headNode;  // 当前节点
        while (cur != null) {
            ListNode next = cur.next; // 存一下当前节点的下一个节点
            cur.next = pre; // 将当前节点的下一个节点直接指向当前节点的pre
            pre = cur; // 前一个节点指向当前节点
            cur = next; // 当前节点指向下一个节点
        }
        return pre;
    }

    /**
     * 尾递归
     * @param headNode
     * @return
     */
    private static ListNode reverseListNode2(ListNode headNode) {
        if (headNode == null) return null;
        return reverse(null, headNode);
    }

    private static ListNode reverse(ListNode pre, ListNode cur) {
        if (cur == null) return pre;
        ListNode next = cur.next;
        cur.next = pre;
        return reverse(cur, next);
    }
}
