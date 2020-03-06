package web; /**
 * @program LeetNiu
 * @description: 二叉树的下一个结点
 * @author: mf
 * @create: 2020/01/16 14:22
 */

/**
 * 给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。
 * 注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。
 */
public class T57 {
    public TreeLinkNode GetNext(TreeLinkNode pNode) {
        if (null == pNode) {
            return null;
        }
        if (null != pNode.right) {
            pNode = pNode.right;
            while (null != pNode.left) {
                pNode = pNode.left;
            }
            return pNode;
        }
        while (null != pNode.next) {
            if (pNode.next.left == pNode) {
                return pNode.next;
            }
            pNode = pNode.next;
        }
        return null;
    }
}
