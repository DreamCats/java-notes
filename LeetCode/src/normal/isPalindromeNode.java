package normal;

/**
 * @program JavaBooks
 * @description: 234.回文链表
 * @author: mf
 * @create: 2019/11/05 21:17
 */

/*
题目：https://leetcode-cn.com/problems/palindrome-linked-list/
类型：链表
难度：easy
 */
/*
输入: 1->2
输出: false
输入: 1->2->2->1
输出: true
 */
public class isPalindromeNode {
    public static void main(String[] args) {
        int[] arr = {1,2,2,1};
        ListNode head = ListNode.setListNode(arr);
        System.out.println(isPalindrome(head));
    }

    private static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;
        // 找中点
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast != null) slow = slow.next; // 说明是偶数，让slow指向下一个节点
        // 切成两半
        cut(head, slow);

        //反转比较
        return isEqual(head, reverse(slow));
    }

    private static void cut(ListNode head, ListNode cutNode) {
        ListNode node = head;
        while (node.next != cutNode) {
            node = node.next;
        }
        node.next = null;
    }

    private static ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode nextNode = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nextNode;
        }
        return pre;
    }

    private static boolean isEqual(ListNode l1, ListNode l2) {
        while (l1 != null && l2 != null) {
            if (l1.val != l2.val) return false;
            l1 = l1.next;
            l2 = l2.next;
        }
        return true;
    }
}
