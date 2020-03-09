/**
 * @program JavaBooks
 * @description: 二叉树最小深度
 * @author: mf
 * @create: 2020/03/09 21:34
 */

package subject.tree;

/**
 *    3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *    2
 */
public class T6 {
    /**
     * 注意仔细读题，一开始没想到根左和根右任意为空的情况
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        if(root == null) return 0;
        int m1 = minDepth(root.left);
        int m2 = minDepth(root.right);
        return root.left == null || root.right == null ? m1 + m2 + 1 : Math.min(m1, m2) + 1;
    }
}
