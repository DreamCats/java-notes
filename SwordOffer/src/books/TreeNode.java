package books;

/**
 * @program JavaBooks
 * @description: 二叉树
 * @author: mf
 * @create: 2019/09/06 10:05
 */

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }

    // 给定一个前序和中序数组，生成一颗二叉树  // 根据T7笔试题
    public static TreeNode setBinaryTree(int[] pre, int[] in) {
        TreeNode root = reConstructBinaryTree(pre, 0, pre.length - 1, in, 0, in.length - 1);
        return root;
    }

    private static TreeNode reConstructBinaryTree(int[] pre, int startPre, int endPre, int[] in, int startIn, int endIn) {

        if (startPre > endPre || startIn > endIn) {
            return null;
        }
        TreeNode root = new TreeNode(pre[startPre]);
        for (int i = startIn; i <= endIn; i++) {
            if (in[i] == pre[startPre]) {
                root.left = reConstructBinaryTree(pre, startPre + 1, startPre + i - startIn, in, startIn, i - 1);
                root.right = reConstructBinaryTree(pre, i - startIn + startPre + 1, endPre, in, i + 1, endIn);
            }
        }
        return root;
    }

    // 递归打印前序
    public static void preOrderRe(TreeNode root) {
        System.out.println(root.val);
        TreeNode leftNode = root.left;
        if (leftNode != null) {
            preOrderRe(leftNode);
        }

        TreeNode rightNode = root.right;
        if (rightNode != null) {
            preOrderRe(rightNode);
        }
    }
    // 递归打印中序
    public static void midOrderRe(TreeNode node) {
        if (node == null) {
            return;
        } else {
            midOrderRe(node.left);
            System.out.println(node.val);
            midOrderRe(node.right);
        }
    }

    // 递归打印后序
    public static void postOrderRe(TreeNode node) {
        if (node == null) {
            return;
        } else {
            postOrderRe(node.left);
            postOrderRe(node.right);
            System.out.println(node.val);
        }
    }

}
