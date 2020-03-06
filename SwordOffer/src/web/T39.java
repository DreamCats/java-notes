package web; /**
 * @program LeetNiu
 * @description: 平衡二叉树
 * @author: mf
 * @create: 2020/01/15 10:20
 */


/**
 * 输入一棵二叉树，判断该二叉树是否是平衡二叉树。
 */
public class T39 {
    public boolean IsBalanced_Solution(TreeNode root) {
        if (root == null) {
            return true;
        }
        return Math.abs(maxDepth(root.left) - maxDepth(root.right)) <= 1 && IsBalanced_Solution(root.left) && IsBalanced_Solution(root.right);
    }

    private int maxDepth(TreeNode root) {
        if(root == null) return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}
