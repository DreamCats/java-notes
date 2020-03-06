package web; /**
 * @program LeetNiu
 * @description: 反转链表
 * @author: mf
 * @create: 2020/01/10 15:21
 */

/**
 * 输入一个链表，反转链表后，输出新链表的表头。
 */
public class T15 {

    public ListNode ReverseList(ListNode head) {
        // 判断
        if (head == null) return null;
        return reverse(null, head);
    }

    /**
     * 递归
     * @param pre
     * @param cur
     * @return
     */
    private ListNode reverse(ListNode pre, ListNode cur) {
        if (cur == null) return pre;
        ListNode next = cur.next;
        cur.next = pre;
        return reverse(cur, next);
    }
}
