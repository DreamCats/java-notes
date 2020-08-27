import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 6, 5, 4};
        ListNode node = ListNodeTools.getListNode(nums);
        ListNodeTools.printListNode(node);
        reorderList(node);
        ListNodeTools.printListNode(node);
    }

    public static void reorderList(ListNode head) {
        List<Integer> list = new ArrayList<>();
        ListNode p = head;
        while (p != null) {
            list.add(p.val);
            p = p.next;
        }
        int l = 0, r = list.size() - 1;
        int idx = 0;
        int[] nums = new int[list.size()];
        while (l < r) {
            nums[idx++] = list.get(l++);
            nums[idx++] = list.get(r--);
        }
        p = head;
        idx = 0;
        while (p != null) {
            p.val = nums[idx++];
            p = p.next;
        }
    }
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
