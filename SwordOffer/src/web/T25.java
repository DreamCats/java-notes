package web; /**
 * @program LeetNiu
 * @description: 复杂链表的复制
 * @author: mf
 * @create: 2020/01/13 13:09
 */


/**
 * 输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，另一个特殊指针指向任意一个节点），返回结果为复制后复杂链表的head。
 * （注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）
 */
public class T25 {
    public RandomListNode Clone(RandomListNode pHead) {
        if (pHead == null) return null;
        RandomListNode node = pHead;
        while (node != null) {
            RandomListNode copyNode = new RandomListNode(node.label);
            copyNode.next = node.next;
            node.next = copyNode;
            node = copyNode.next;
        }
        node = pHead;
        while (node != null) {
            node.next.random = node.random == null ? null : node.random.next;
            node = node.next.next;
        }
        node = pHead;
        RandomListNode pCloneHead = pHead.next;
        while (node != null) {
            RandomListNode copyNode = node.next;
            node.next = copyNode.next;
            copyNode.next = copyNode.next == null ? null : copyNode.next.next;
            node = node.next;
        }
        return pCloneHead;
    }
}
