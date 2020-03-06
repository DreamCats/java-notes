package books;

/**
 * @program JavaBooks
 * @description: 序列化二叉树
 * @author: mf
 * @create: 2019/09/18 10:02
 */

/*
请实现两个函数，分别用来序列化和反序列化二叉树。
 */
public class T37 {
    public static void main(String[] args) {
        int[] pre = {1, 2, 4, 3, 5, 6};
        int[] in = {4, 2, 1, 5, 3, 6};
        TreeNode root = TreeNode.setBinaryTree(pre, in);

        serialize(root);
    }


    private static void serialize(TreeNode root) {
        if (root == null) {
            System.out.print("$,");
            return;
        }
        System.out.print(root.val + ",");
        serialize(root.left);
        serialize(root.right);

    }
}
