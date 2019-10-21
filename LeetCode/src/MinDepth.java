/**
 * @program JavaBooks
 * @description: 二叉树的最小深度
 * @author: mf
 * @create: 2019/10/18 23:04
 */

/**
 * 给定一个二叉树，找出其最小深度。
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 */
public class MinDepth {
    public static void main(String[] args) {
        int[] pre = {3, 9, 20, 15, 7};
        int[] in = {9, 3, 15, 20, 7};
        TreeNode node = TreeNode.setBinaryTree(pre, in);
        int number = minDepth(node);
        System.out.println(number);
    }

    private static int minDepth(TreeNode node) {
        if (node == null) return 0;
        if (node.left == null && node.right == null) return 1;
        int left, right;
        if (node.left != null) left = minDepth(node.left);
        else left = Integer.MAX_VALUE;
        if (node.right != null) right = minDepth(node.right);
        else right = Integer.MAX_VALUE;
        return Math.min(left, right) + 1;
    }
}
