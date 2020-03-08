/**
 * @program JavaBooks
 * @description: 复杂链表的复制
 * @author: mf
 * @create: 2020/03/08 02:19
 */

package subject.linked;

/**
 * 输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * 输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
 */
public class T5 {
    /**
     * 迭代三次
     * @param head
     * @return
     */
    public Node copyRandomList(Node head) {
        if (head == null) return null;
        Node node = head; // 一般这样的操作其实就是好比是指针了，链表定义个指针， 走你
        // 第一次迭代的目的是复制next
        while (node != null) {
            // 接下来的三步操作 复制节点
            Node temp = new Node(node.val); // new一个和node值相同的当前节点 temp 比如1`
            temp.next = node.next; // temp 的下个节点指向 node的下个节点， 比如1`>2
            node.next = temp; // node 的下个节点 指向temp 比如 1 > 1` > 2
            // 一般迭代， 都会有这一步操作， 移动指针
            node = temp.next; // 将node 指针 指向 temp的下个节点， 比如2
        }
        // 这次的目的让复制的节点的random 和 原先的random各个指向的一致
        // 将指针移动首部
        node = head;
        while (node != null) {
            //  2   2`
            // ^_^ ^_^
            // 1 > 1` > 2 > 2` > 3 > 3`
            node.next.random = node.random == null ? null : node.random.next;
            // 迭代，移动指针
            node = node.next.next;
        }
        // 第三次目的是切断 返回复制的链表
        // 双指针， 重新指向
        node = head;
        Node pCloneHead = head.next;
        while (node != null) {
            Node temp = node.next; // 其实就是当前的复制节点
            node.next = temp.next; // 其实就是 1 > 2
            temp.next = temp.next == null ? null : temp.next.next; // 其实就是 1` > 2`
            // 迭代， 移动指针
            node = node.next;
        }
        return pCloneHead;
    }
}
