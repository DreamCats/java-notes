package books;

/**
 * @program JavaBooks
 * @description: 链表中环的入口节点
 * @author: mf
 * @create: 2019/09/04 14:28
 */

/*

 */
public class T23 {
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
        listNode5.next = listNode3;
        ListNode enterNode = findEnterNode(listNode1);
        System.out.println(enterNode.value);
    }

    private static ListNode findEnterNode(ListNode headNode) {
        if (headNode == null) return null;
        ListNode meetingNode = findMeetingNode(headNode);
        if (meetingNode == null) return null; // 无环

        // 找环中有几个节点
        ListNode tempNode = meetingNode.next;
        int ringNum = 1;
        while (tempNode != meetingNode) {
            tempNode = tempNode.next;
            ringNum++;
        }

        // 设定两个指针，比如p1 p2
        // 一开始都在头节点，当p1 跑到ringNum的时候，p2开始移动
        // 当p1 == p2 的时候， 就是环入口节点
        ListNode enterNode = headNode;
        headNode = headNode.next;
        int p1 = 1;
        while (enterNode != headNode) {
            headNode = headNode.next;
            p1++;
            if (p1 > ringNum) {
                enterNode = enterNode.next;
            }
        }
        return enterNode;
    }

    private static ListNode findMeetingNode(ListNode headNode) {
        ListNode slowNode = headNode.next;
        if (slowNode == null) return null;
        ListNode fastNode = slowNode.next;
        while (fastNode != null && slowNode != null) {
            if (fastNode == slowNode) return fastNode;
            slowNode = slowNode.next;
            fastNode = fastNode.next;
            if (fastNode != null) fastNode = fastNode.next; // 提高代码的鲁棒性而已
//            fastNode = fastNode.next;
        }
        return null;
    }


}
