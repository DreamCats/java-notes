/**
 * @program JavaBooks
 * @description: 二叉树的深度搜索遍历
 * @author: mf
 * @create: 2019/10/20 02:17
 */

/**
 * 其实就是二叉树的后序遍历
 */
public class DFSDemo {
    public void DFS(TreeNode node) {
        if (node == null) return;
        if (node.left != null) DFS(node.left);
        if (node.right != null) DFS(node.right);
        System.out.println(node.val);
    }
}
