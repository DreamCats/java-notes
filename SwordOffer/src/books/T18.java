package books;

/**
 * @program JavaBooks
 * @description: 删除链表的节点 & 删除链表中重复的节点
 * @author: mf
 * @create: 2019/08/29 14:49
 */

/*
在O(1)时间内删除链表节点
给定单向链表的头指针和一个节点指针，定义一个函数在O(1)时间内删除该节点
链表节点与函数的定义如下
 */

public class T18 {
    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);

        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        System.out.println("source ListNode...");
        printListNode(listNode1);
        deleteNode(listNode1, listNode3);
        System.out.println("deleted ListNode...");
        printListNode(listNode1);

//        ListNode listNode1 = new ListNode(1);
//        ListNode listNode2 = new ListNode(2);
//        ListNode listNode3 = new ListNode(2);
//        ListNode listNode4 = new ListNode(3);
//
//        listNode1.next = listNode2;
//        listNode2.next = listNode3;
//        listNode3.next = listNode4;
//        System.out.println("source");
//        printListNode(listNode1);
//        deleteDuplication(listNode1);
//        System.out.println("deleted");
//        printListNode(listNode1);
    }

    private static void deleteNode(ListNode headListNode, ListNode pListNode) {
        // 要删除的节点不是尾节点
        if (pListNode.next != null) {
            ListNode node = pListNode.next;
            pListNode.value = node.value;
            pListNode.next = node.next;
        } else if (headListNode == pListNode) { // 只有一个头节点
            pListNode = null;
            headListNode = null;
        } else { // 链表中有多个节点，删除的是尾节点
            // 只能遍历了
            ListNode node = headListNode;
            while (node.next != pListNode) {
                node = node.next;
            }
            node.next = null;
            pListNode = null;
        }
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
