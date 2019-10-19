
/**
 * @program JavaBooks
 * @description: 链表
 * @author: mf
 * @create: 2019/10/17 01:03
 */

public class ListNode {
    int val;
    ListNode next;

    public ListNode(int val) {
        this.val = val;
    }

    public static ListNode setListNode(int[] arr) {
        ListNode node = new ListNode(-1);
        ListNode p1 = node;
        for (int num : arr) {
            ListNode temp = new ListNode(num);
            p1.next = temp;
            p1 = p1.next;
        }
        return node.next;
    }

    public static void printNode(ListNode node) {
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }
}
