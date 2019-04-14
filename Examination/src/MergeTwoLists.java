/**
 * @program JavaBooks
 * @description: 合并两个有序链表
 * @author: mf
 * @create: 2019/04/14 20:59
 */

public class MergeTwoLists {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);
        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);
        SolutionMergeTwoLists smt = new SolutionMergeTwoLists();
        ListNode res = smt.solutionMergeTwoLists(l1, l2);


        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }



    }
}



class ListNode {
    int val;
    ListNode next;

    public ListNode(int i) {
        val = i;
    }
}

class SolutionMergeTwoLists {
    public ListNode solutionMergeTwoLists (ListNode l1, ListNode l2){
        ListNode listNode = new ListNode(0);
        ListNode temp = listNode;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                temp.next = l1;
                temp = temp.next;
                l1 = l1.next;
            } else {
                temp.next = l2;
                temp = temp.next;
                l2 = l2.next;
            }
        }
        if(l1 == null) temp.next = l2;
        else if(l2 == null) temp.next = l1;

        return listNode.next;
    }
}