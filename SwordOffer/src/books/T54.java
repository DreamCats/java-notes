package books;

import java.util.ArrayList;

/**
 * @program JavaBooks
 * @description: 二叉搜索树的第K大节点
 * @author: mf
 * @create: 2019/10/06 10:14
 */

/*
如书T54
 */
public class T54 {
    public static void main(String[] args) {
        int[] pre = {5,3,2,4,7,6,8};
        int[] in = {2,3,4,5,6,7,8};
        TreeNode pNode = TreeNode.setBinaryTree(pre, in);
        TreeNode kNode = kthNode(pNode, 3);
        System.out.println(kNode.val);
    }

    private static TreeNode kthNode(TreeNode pNode, int k) {
        ArrayList<TreeNode> arr = new ArrayList<>();
        if (pNode == null || k == 0) {
            return null;
        }
        arr = inOrder(arr, pNode);
        TreeNode tr = pNode;
        if (k <= arr.size()) {
            tr = arr.get(k - 1);
        }

        return tr;
    }

    private static ArrayList<TreeNode> inOrder(ArrayList<TreeNode> arr, TreeNode root) {

        if(root ==null){
            return null;
        }
        if(root.left != null){
            inOrder(arr, root.left);
        }
        arr.add(root);
        if(root.right != null){
            inOrder(arr, root.right);
        }
        return arr;
    }
}
