/**
 * @program JavaBooks
 * @description: 反转链表
 * @author: mf
 * @create: 2020/03/07 12:04
 */

package subject.linked;

/**
 * 1->2->3->4->5->NULL
 * NULL<-1<-2<-3<-4<-5
 */
public class T3 {
    /**
     * 迭代
     * 流程：
     *  核心还是双指针...
     *  pre和cur一直移动
     *  接着相互指向
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode pre = null; // 当前节点之前的节点 null
        ListNode cur = head;
        while (cur != null) {
            ListNode nextTemp = cur.next; // 获取当前节点的下一个节点
            cur.next = pre; // 当前节点的下个节点指向前一个节点
            // 尾递归其实省了下面这两步
            pre = cur; // 将前一个节点指针移动到当前指针
            cur = nextTemp; // 当当前节点移动到下一个节点
        }
        return pre;
    }

    /**
     * 递归：尾递归
     * @param head
     * @return
     */
    public ListNode reverseList2(ListNode head) {
        return reverse(null, head);
    }

    public ListNode reverse(ListNode pre, ListNode cur) {
        if (cur == null) return pre; // 如果当前节点为null，直接返回
        ListNode next = cur.next; // next节点指向当前节点的下一个节点
        cur.next = pre; // 将当前节点指向 当前节点的前一个节点
        return reverse(cur, next);
    }
}
