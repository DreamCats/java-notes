package books;

/**
 * @program JavaBooks
 * @description: 复杂链表的复制
 * @author: mf
 * @create: 2019/09/16 12:06
 */

/*
见书
 */

/*
思路：
1. 复制每个节点，如复制节点A得到A1，将节点A1插到节点A后面
2. 重新遍历链表，复制老结点的随机指针给新节点，如 A1.random = A.random.next;
3. 拆分链表，将链表拆分为原链表和复制后的链表
 */
public class T35 {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        node1.random = node3;
        node2.random = node5;
        node4.random = node2;

        ListNode resNode = Clone(node1);
    }

    private static ListNode Clone(ListNode pHead) {
        if (pHead == null) return null;
        ListNode node = pHead;
        // 1. 复制每个节点，如复制节点A得到A1，将节点A1插到节点A后面
        while (node != null) {
            ListNode copyNode = new ListNode(node.value);
            copyNode.next = node.next;
            node.next = copyNode;
            node = copyNode.next;
        }
        node = pHead;
        // 2. 重新遍历链表，复制老结点的随机指针给新节点，如 A1.random = A.random.next;
        while (node != null) {
            node.next.random = node.random == null ? null : node.random.next;
            node = node.next.next;
        }
        // 3. 拆分链表，将链表拆分为原链表和复制后的链表
        node = pHead;
        ListNode pCloneHead = pHead.next;
        while (node != null) {
            ListNode copyNode = node.next;
            node.next = copyNode.next;
            copyNode.next = copyNode.next == null ? null : copyNode.next.next;
            node = node.next;
        }
        return pCloneHead;
    }
}
