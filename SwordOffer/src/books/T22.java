package books;

import java.util.Stack;

/**
 * @program JavaBooks
 * @description: 链表中倒数第K个节点
 * @author: mf
 * @create: 2019/09/03 09:32
 */

/*
输入一个链表，输出该链表中倒数第K个节点。为了符合大多数
人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。
例如，一个链表有6个节点，从头节点开始，它们的值依次是1、2、3
、4、5、6。这个链表的倒数第3个节点是值为4的节点。链表的定义如下：
 */


/*
思路
准备两个指针p1 p2
当p1++ 到k的时候，p2开始++
当当尾节点的时候，p2正好是倒数k个节点  n-k+1
追赶思路。。。
 */
public class T22 {
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

        ListNode kNode = findKthToTail(listNode1, 6);
        System.out.println(kNode.value);
    }

    /**
     * 快慢指针问题
     * @param headListNode
     * @param k
     * @return
     */
    public static ListNode findKthToTail(ListNode headListNode, int k) {
        if (headListNode == null || k == 0 ) return null;
        //
        ListNode pNode = headListNode;
        ListNode kNode = headListNode;
        int p1 = 0;
        while (pNode != null) {
            if (p1 > k - 1) { // 当p1走完k-1步，那么kNode开始走
                kNode = kNode.next;
            }
            pNode = pNode.next;
            p1++;
        }
        if (p1 >= k) {
            return kNode;
        }
        return null;
    }

    /**
     * 栈
     * @param headListNode
     * @param k
     * @return
     */
    public static ListNode findKthToTail2(ListNode headListNode, int k) {
        if(headListNode == null || k <= 0) return null;
        Stack<ListNode> stack = new Stack<>();
        ListNode root = headListNode;
        while(root != null) {
            stack.push(root);
            root = root.next;
        }
        int temp = 0;
        while(!stack.isEmpty()) {
            ListNode listNode = stack.pop();
            temp++;
            if (temp == k) {
                return listNode;
            }
        }
        return null;
    }
}
