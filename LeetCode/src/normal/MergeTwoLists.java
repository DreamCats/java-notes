package normal;

/**
 * @program JavaBooks
 * @description: 合并两个有序链表
 * @author: mf
 * @create: 2019/11/05 09:55
 */

/*
题目：https://leetcode-cn.com/problems/merge-two-sorted-lists/
难度：easy
类型：链表
*/

/*
输入：1->2->4, 1->3->4
输出：1->1->2->3->4->4
 */

// 一般链表都是递归
public class MergeTwoLists {
    public static void main(String[] args) {
        int[] arr1 =  {1 ,2 ,4};
        int[] arr2 =  {1 ,3 ,4};
        ListNode l1 = ListNode.setListNode(arr1);
        ListNode l2 = ListNode.setListNode(arr2);
        ListNode l3 = mergeTwoLists(l1, l2);
        ListNode.printNode(l3);
    }

    private static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }
}
