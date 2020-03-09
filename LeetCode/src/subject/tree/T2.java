/**
 * @program JavaBooks
 * @description: 对称二叉树
 * @author: mf
 * @create: 2020/03/09 13:28
 */

package subject.tree;

/**
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 */
public class T2 {
    /**
     * 还是前序递归
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        return isSym(root, root);
    }

    public boolean isSym(TreeNode root, TreeNode root1) {
        if (root == null && root1 == null) return true;
        if (root == null || root1 == null) return false;
        if (root.val != root1.val) return  false;
        return isSym(root.left, root1. right) && isSym(root.right, root1.left);
    }
}
