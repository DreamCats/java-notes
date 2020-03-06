package books;

/**
 * @program JavaBooks
 * @description: 重建二叉树
 * @author: mf
 * @create: 2019/08/20 14:38
 */

/*
输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。
假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
例如，输入前序遍历序列{1, 2, 4, 7, 3, 5, 6, 8}和
中序遍历序列{4, 7, 2, 1, 5, 3, 8, 6}，则如下二叉树并
输出它的头节点。
                        1
            2                                    3
        4                           5               6
            7                                    8
 */
public class T7 {
    // 递归
    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        TreeNode root = reConstructBinaryTree(pre, 0, pre.length - 1, in, 0, in.length - 1);
        return root;
    }

    //前序遍历{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}
    private TreeNode reConstructBinaryTree(int[] pre, int startPre, int endPre, int[] in, int startIn, int endIn) {

        if (startPre > endPre || startIn > endIn) {
            return null;
        }
        TreeNode root = new TreeNode(pre[startPre]);
        for (int i = startIn; i <= endIn; i++) {
            if (in[i] == pre[startPre]) {
                //注意边界，递归可看成重复的子问题。
                root.left = reConstructBinaryTree(pre, startPre + 1, startPre + i - startIn, in, startIn, i - 1);
                root.right = reConstructBinaryTree(pre, i - startIn + startPre + 1, endPre, in, i + 1, endIn);
            }
        }
        return root;
    }
}




class TestT7 {
    public static void main(String[] args) {
        int[] pre = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] in = {4, 7, 2, 1, 5, 3, 8, 6};
        T7 t7 = new T7();
        TreeNode root = t7.reConstructBinaryTree(pre, in);
        System.out.println(root.val);
    }
}