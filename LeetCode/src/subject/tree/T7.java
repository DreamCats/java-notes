/**
 * @program JavaBooks
 * @description: 路径总和
 * @author: mf
 * @create: 2020/03/10 14:14
 */

package subject.tree;

/**
 *              5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \      \
 *         7    2      1
 *  sum = 22
 */
public class T7 {
    /**
     * 递归
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        // 总和减去路径上节点的值
        sum -= root.val;
        // 如果节的左右节点都为空，说明那个路径上走完了，可以开始比较sum是否为0
        if (root.left == null && root.right == null) return sum == 0;
        return hasPathSum(root.left, sum) || hasPathSum(root.right, sum);
    }
}
