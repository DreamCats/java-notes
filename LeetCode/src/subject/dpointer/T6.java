/**
 * @program JavaBooks
 * @description: 环形链表
 * @author: mf
 * @create: 2020/04/09 17:24
 */

package subject.dpointer;

public class T6 {
    public boolean hasCycle(ListNode head) {
        if (head != null && head.next != null) {
            // 快慢指针
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
