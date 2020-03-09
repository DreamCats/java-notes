/**
 * @program JavaBooks
 * @description: 平衡二叉树
 * @author: mf
 * @create: 2020/03/09 17:14
 */

package subject.tree;

/**
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * true
 */
public class T5 {
    /**
     * 自顶向下递归
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        return Math.abs(maxDepth(root.left) - maxDepth(root.right)) <= 1
                && isBalanced(root.left) && isBalanced(root.right);
    }

    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}
