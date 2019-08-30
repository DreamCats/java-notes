/**
 * @program JavaBooks
 * @description: 删除链表中重复的节点
 * @author: mf
 * @create: 2019/08/30 09:48
 */

/*
在一个排序的链表中，如何删除重复的节点？
 */


public class T20 {
    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(2);
        ListNode listNode4 = new ListNode(3);

        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        System.out.println("source");
        printListNode(listNode1);
        deleteDuplication(listNode1);
        System.out.println("deleted");
        printListNode(listNode1);
    }

    private static void deleteDuplication(ListNode headListNode) {
        if (headListNode == null) return;
        ListNode preNode = null;
        ListNode pNode = headListNode;
        while (pNode != null) {
            ListNode pNext = pNode.next;
            boolean needDelete = false;
            if (pNext != null && pNext.value == pNode.value) needDelete = true;
            if (!needDelete) {
                preNode = pNode;
                pNode = pNext;
            } else {
                int value = pNode.value;
                ListNode pDelNode = pNode;
                while (pDelNode != null && pDelNode.value == value) {
                    pNext = pDelNode.next;
                    pDelNode = pNext;
                }
                if (preNode == null) headListNode = pNext;
                else pNode.next = pNext;
//                else preNode.next = pNext;
//                pNode = pNext;
            }
        }
    }
    public static void printListNode(ListNode headListnode) {
        while (headListnode != null) {
            System.out.println(headListnode.value);
            headListnode = headListnode.next;
        }
    }
}
