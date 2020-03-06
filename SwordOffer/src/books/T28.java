package books;

/**
 * @program JavaBooks
 * @description: 对称的二叉树
 * @author: mf
 * @create: 2019/09/09 10:11
 */

/*
请实现一个函数，用来判断一颗二叉树是不是对称的。如果一颗二叉树
和它的镜像一样，那么它是对称的。具体举例请看书
 */
/*
思路
前序遍历和对称的前序遍历是一样的，这就是规律。
但注意的是考虑空值，以防二叉树不完整
 */
public class T28 {
    public static void main(String[] args) {
        int[] pre = {8, 6, 5, 7, 6, 7, 5};
        int[] in = {5, 6, 7, 8, 7, 6, 5};
        TreeNode node = TreeNode.setBinaryTree(pre, in);
        boolean res = isSymmetrical(node, node);
        System.out.println(res);
    }

    private static boolean isSymmetrical(TreeNode node, TreeNode node1) {
        if (node == null && node1 == null) return true; // 遍历到底了
        if (node == null || node1 == null) return false; // 以防二叉树不完整
        if (node.val != node1.val) return false; // 互为对称的值是相等的。
        // node.left right 前序， node1 right left 对称前序遍历
        return isSymmetrical(node.left, node1.right) && isSymmetrical(node.right, node1.left);
    }
}
