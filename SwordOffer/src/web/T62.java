package web; /**
 * @program LeetNiu
 * @description: 二叉搜索树的第k个结点
 * @author: mf
 * @create: 2020/01/17 23:01
 */

/**
 * 给定一棵二叉搜索树，请找出其中的第k小的结点。例如， （5，3，7，2，4，6，8中，按结点数值大小顺序第三小结点的值为4。
 */
public class T62 {
    int index = 0;
    TreeNode KthNode(TreeNode pRoot, int k) {
        if (null != pRoot) {
            TreeNode node = KthNode(pRoot.left, k);
            if (null != node) {
                return node;
            }
            index++;
            if (index == k) {
                return pRoot;
            }
            node = KthNode(pRoot.right, k);
            if (null != node) {
                return node;
            }
        }
        return null;
    }
}
