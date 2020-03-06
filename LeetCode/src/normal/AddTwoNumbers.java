package normal; /**
 * @program JavaBooks
 * @description: 两数相加
 * @author: mf
 * @create: 2019/10/17 00:53
 */

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。
 * 其中，它们各自的位数是按照 逆序 的方式存储的，
 * 并且它们的每个节点只能存储 一位 数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 */

/**
 * 思路
 * 搞一个进位标志即可--carried
 * 挺简单的，注意边界
 */
/// 时间复杂度: O(n)
/// 空间复杂度: O(n)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class AddTwoNumbers {
    public static void main(String[] args) {
        int[] arr = {2, 4, 3};
        int[] arr1 = {5, 6, 4};
        ListNode l1 = ListNode.setListNode(arr);
        ListNode l2 = ListNode.setListNode(arr1);
        ListNode l3 = addTwoNumbers(l1, l2);
        ListNode.printNode(l3);
    }

    private static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) return null;
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode p1 = l1;
        ListNode p2 = l2;
        ListNode l3 = new ListNode(-1);
        ListNode p3 = l3;
        int carried = 0;
        while (p1 != null || p2 != null) {
            // 两个链表长度不一定相等
            int a = p1 != null ? p1.val : 0;
            int b = p2 != null ? p2.val : 0;
            p3.next = new ListNode((a + b + carried) % 10);
            carried = (a + b + carried) / 10;
            p3 = p3.next;
            // 两个链表长度不一定相等
            p1 = p1 != null ? p1.next : null;
            p2 = p2 != null ? p2.next : null;
        }
        // 当两个末位正好进位，并且next都为空
        p3.next = carried != 0 ? new ListNode(1) : null;
        return l3.next;
    }
}
