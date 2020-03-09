/**
 * @program JavaBooks
 * @description: 二叉树的最大深度
 * @author: mf
 * @create: 2020/03/09 13:36
 */

package subject.tree;

/**
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回结果是3
 */
public class T3 {
    /**
     * 递归
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        return root == null ? 0 : Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}
