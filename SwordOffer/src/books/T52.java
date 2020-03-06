package books;

import java.util.HashMap;

/**
 * @program JavaBooks
 * @description: 两个链表的第一个公共节点
 * @author: mf
 * @create: 2019/10/05 13:11
 */

/*
输入两个链表，找出它们的第一个公共节点。
 */
public class T52 {
    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        ListNode listNode1 = new ListNode(2);

        ListNode listNode2 = new ListNode(3);
        ListNode listNode3 = new ListNode(4);
        ListNode listNode4 = new ListNode(5);

        listNode.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;

        listNode1.next = listNode3;

        ListNode commonNode = findFirstCommonNode(listNode, listNode1);
        System.out.println(commonNode.value);
    }

    // 哈希
    private static ListNode findFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        if (pHead1 == null || pHead2 == null) return null;
        ListNode cur1 = pHead1;
        ListNode cur2 = pHead2;
        HashMap<ListNode, Integer> map = new HashMap<>();
        while (cur1 != null) {
            map.put(cur1, 1);
            cur1 = cur1.next;
        }
        while (cur2 != null) {
            if (map.containsKey(cur2)) {
                return cur2;
            }
            cur2 = cur2.next;
        }
        return null;
    }


}
