package books;

/**
 * @program JavaBooks
 * @description: 合并两个排序的链表
 * @author: mf
 * @create: 2019/09/06 09:19
 */

/*
输入两个递增排序的链表，合并这两个链表并使新链表中的节点
仍然使递增排序的。例如，。。。
1 3 5
2 4 6
1 2 3 4 5 6
 */

/*
思路还是类似于准备两个指针一样，但是这次不同
比较两个值，谁小，谁让出来，
比如， p1 < p2
那么，node = p1
那么node.next 继续和p2比较， 如果还小， 继续上面的过程，所以递归(因为p1 p2已经排好序)
如果p1 >= p2，那就将node = p2， node.next 和p1剩下的比较， 继续上面的过程

 */
public class T25 {
    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        ListNode listNode6 = new ListNode(6);


        listNode1.next = listNode3;
        listNode3.next = listNode5;

        listNode2.next = listNode4;
        listNode4.next = listNode6;

        ListNode node = merge(listNode1, listNode2);
        while (node != null) {
            System.out.println(node.value);
            node = node.next;
        }
    }

    // 递归
    private static ListNode merge(ListNode headNode1, ListNode headNode2) {
        if (headNode1 == null) return headNode2;
        if (headNode2 == null) return headNode1;
        ListNode node = null;
        if (headNode1.value < headNode2.value) {
            node = headNode1;
            node.next = merge(node.next, headNode2);
        } else {
            node = headNode2;
            node.next = merge(node.next, headNode1);
        }
        return node;
    }
}
