package web; /**
 * @program LeetNiu
 * @description: 二叉树的镜像
 * @author: mf
 * @create: 2020/01/10 16:18
 */

/**
 * 操作给定的二叉树，将其变换为源二叉树的镜像。
 */
public class T18 {
    public void Mirror(TreeNode root) {
        // 判断
        if (root == null) return;
        if (root.left == null && root.right == null) return;
        // 交换
        swap(root, root.left, root.right);
        // 递归
        if (root.left != null) Mirror(root.left);
        if (root.right != null) Mirror(root.right);

    }

    private void swap(TreeNode root, TreeNode left, TreeNode right) {
        TreeNode temp = left;
        root.left = right;
        root.right = temp;
    }
}
