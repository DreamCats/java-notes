package normal;

import java.util.HashSet;

/**
 * @program JavaBooks
 * @description: 141.环形链表
 * @author: mf
 * @create: 2019/11/05 10:19
 */

/*
题目：
难度：easy
类型：链表
 */
public class HasCycle {
    public static void main(String[] args) {

    }

    /**
     * 哈希
     * @param head
     * @return
     */
    public static boolean hasCycle(ListNode head) {
        HashSet<ListNode> set = new HashSet<>();
        while (head != null) {
            if (set.contains(head)) {
                return true;
            } else {
                set.add(head);
            }
            head = head.next;
        }
        return false;
    }

    /**
     * 快慢指针
     * @param head
     * @return
     */
    public static boolean hasCycle2(ListNode head) {
        if (head != null && head.next != null) {
            ListNode quick = head;
            ListNode slow = head;
            while (2 > 1) {
                quick = quick.next;
                if (quick == null) return false;
                quick = quick.next;
                if (quick == null) return false;
                slow = slow.next;
                if (slow == quick) return true;
            }
        } else {
            return false;
        }
    }
}
