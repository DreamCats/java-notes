/**
 * @program JavaBooks
 * @description: 删除链表的节点
 * @author: mf
 * @create: 2020/03/08 21:11
 */

package subject.linked;

/**
 * head = [4,5,1,9], node = 5
 * 输出: [4,1,9]
 */
public class T7 {
    /**
     * node的下个节点强行替代他
     * @param node
     */
    public void deleteNode(ListNode node) {
        // node 5 node.next 1
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
