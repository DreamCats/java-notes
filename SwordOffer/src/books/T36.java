package books;

/**
 * @program JavaBooks
 * @description: 二叉搜索树与双向链表
 * @author: mf
 * @create: 2019/09/17 09:54
 */

/*
输入一颗二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。
要求不能创建任何新的结点，只能调整树中结点指针的指向。
 */
public class T36  {
    public static void main(String[] args) {
        int[] pre = {10, 6, 4, 8, 14, 12, 16};
        int[] in = {4, 6, 8, 10, 12, 14, 16};
        TreeNode treeNode = TreeNode.setBinaryTree(pre, in);
        TreeNode listNode = Convert(treeNode);
    }

    private static TreeNode Convert(TreeNode treeNode) {
        if (treeNode == null) return null;
        if (treeNode.left == null && treeNode.right == null) return treeNode;
        // left
        TreeNode left = Convert(treeNode.left);
        TreeNode p = left;
        while (p != null && p.right != null) {
            p = p.right;
        }
        if (left != null) {
            p.right = treeNode;
            treeNode.left = p;
        }
        // right
        TreeNode right = Convert(treeNode.right);
        if (right != null) {
            treeNode.right = right;
            right.left = treeNode;
        }
        return left != null ? left : treeNode;
    }
}
