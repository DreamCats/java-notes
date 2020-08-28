public class Main {

}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x){val = x;}
}

class ListNode {
    int val;
    ListNode next;

    public ListNode(int val) {
        this.val = val;
    }
}


class ListNodeTools {
    public static ListNode getListNode(int[] nums) {
        ListNode dummy = new ListNode(-1);
        ListNode pre = dummy;
        for (int num : nums) {
            pre.next = new ListNode(num);
            pre = pre.next;
        }
        return dummy.next;
    }

    public static void printListNode(ListNode node) {
        String s = "";
        ListNode p = node;
        while (p != null) {
            s += p.val + "->";
            p = p.next;
        }
        System.out.println(s+"null");
    }
}
