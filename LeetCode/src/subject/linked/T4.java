/**
 * @program JavaBooks
 * @description: 合并两个有序链表
 * @author: mf
 * @create: 2020/03/07 15:13
 */

package subject.linked;

/**
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 */
public class T4 {
    /**
     * 递归
     * 比较大小，谁小，谁的下一个节点指向递归的结果
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }
}
