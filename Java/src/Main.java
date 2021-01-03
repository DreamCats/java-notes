
// n m
// 1 1
// 2 2
// 3 3
// 4 4
// 4 4

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // n
        int m = sc.nextInt(); // m
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int id = sc.nextInt();
            int score = sc.nextInt();
            if (map.containsKey(id)) {
                map.put(id, map.get(id) + score);
            } else {
                map.put(id, score);
            }
        }

        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
        // 按照value排序
        Collections.sort(list, (o1, o2) -> {
            return o2.getValue() - o1.getValue();
        });
        for (int i = 0; i < m; i++) {
            System.out.println(list.get(i).getKey());
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






