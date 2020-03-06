package normal;

/**
 * @program JavaBooks
 * @description: 104. 二叉树的最大深度
 * @author: mf
 * @create: 2019/11/08 15:23
 */

/*
题目：https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/
类型：树
难度：easy
 */
/*
给定二叉树 [3,9,20,null,null,15,7]，

    3
   / \
  9  20
    /  \
   15   7
 */
public class MaxDepth {
    public static void main(String[] args) {

    }

    private static int maxDepth(TreeNode root) {
        return root == null ? 0 : Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}
