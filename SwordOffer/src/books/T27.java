package books;

/**
 * @program JavaBooks
 * @description: 二叉树的镜像
 * @author: mf
 * @create: 2019/09/08 10:20
 */

/*
请完成一个函数，输入一颗二叉树，该函数输出它的镜像

 */

/*
root的子节点左右互选，发现有规律，那么依次类堆
 */
public class T27 {
    public static void main(String[] args) {
        int[] pre = {8, 6, 5, 7, 10, 9, 11};
        int[] in = {5, 6, 7, 8, 9, 10, 11};
        TreeNode node = TreeNode.setBinaryTree(pre, in);

        System.out.println("镜像前：");
        TreeNode.preOrderRe(node);
        MirrorRec(node);
        System.out.println("镜像后：");
        TreeNode.preOrderRe(node);
    }

    private static void MirrorRec(TreeNode node) {
        if (node == null) return;
        if (node.left == null && node.right == null) return;
        swap(node, node.left, node.right);

        // 递归
        if (node.left != null) MirrorRec(node.left);
        if (node.right != null) MirrorRec(node.right);
    }

    private static void swap(TreeNode node, TreeNode left, TreeNode right) {
        TreeNode temp = left;
        node.left = right;
        node.right = temp;
    }
}
