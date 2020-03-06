package books;

/**
 * @program JavaBooks
 * @description: 二叉树的深度
 * @author: mf
 * @create: 2019/10/08 09:38
 */

/*
输入一颗二叉树的根节点，求该树的深度。从根节点到叶节点
依次经过的节点（含根、叶节点）形成树的一条路径，最长路径
的长度为树的深度。
 */

public class T55 {
    public static void main(String[] args) {
        int[] pre = {1, 2, 4, 5, 7, 3, 6};
        int[] in = {4, 2, 7, 5, 1, 3, 6};
        TreeNode pNode = TreeNode.setBinaryTree(pre, in);
        System.out.println(treeDepth(pNode));
    }

    // 递归
    private static int treeDepth(TreeNode pNode) {
        if (pNode == null)
            return 0;
        int pLeft = treeDepth(pNode.left);
        int pRight = treeDepth(pNode.right);
        return pLeft > pRight ? (pLeft + 1) : (pRight + 1);
    }
}
