package normal;

/**
 * @program JavaBooks
 * @description: 101.对称二叉树
 * @author: mf
 * @create: 2019/11/08 10:32
 */

/*
题目：https://leetcode-cn.com/problems/symmetric-tree/
类型：二叉树
难度：easy
 */
/*
    1
   / \
  2   2
 / \ / \
3  4 4  3
 */

// 思路：
    // 对称二叉树的前序遍历是一样的
public class IsSymmetric {
    public static void main(String[] args) {
        int[] pre = {1,2,3,4,2,4,3};
        int[] in = {3,2,4,1,4,2,3};
        TreeNode tree = TreeNode.setBinaryTree(pre, in);
        System.out.println(isSymmetric(tree, tree));
    }

    private static boolean isSymmetric(TreeNode tree, TreeNode tree1) {
        if (tree == null && tree1 == null) return true; // 遍历到底了
        if (tree == null || tree1 == null) return false; // 说明二叉树不完整
        if (tree.val != tree1.val) return false; // 说明值不相等，不对称
        // node.left right 前序， node1 right left 对称前序遍历
        return isSymmetric(tree.left, tree1.right) && isSymmetric(tree.right, tree1.left);
    }
}
