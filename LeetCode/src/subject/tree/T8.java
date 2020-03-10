/**
 * @program JavaBooks
 * @description: 翻转二叉树
 * @author: mf
 * @create: 2020/03/10 14:30
 */

package subject.tree;

public class T8 {
    /**
     * 还是递归
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }
}
