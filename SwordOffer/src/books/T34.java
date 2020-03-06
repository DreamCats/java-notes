package books;

import java.util.ArrayList;

/**
 * @program JavaBooks
 * @description: 二叉树中和为某一值的路径
 * @author: mf
 * @create: 2019/09/15 13:56
 */

/*
输入一颗二叉树和一个整数，打印出二叉树中节点值的和
为输入整数的所有路径。从树的根节点开始往下一直到叶节点
所经过的节点形成一条路径。
 */

/*
思路：
准备两个容器
一个存放走过来的路径
一个存放和为target的路径
递归的过程，前序遍历
用第一个容器去记录节点值，
每当路径走完计算和是否为target， 不管等不等，都需要减掉最后节点
 */
public class T34 {
    private static ArrayList<ArrayList<Integer>> listall = new ArrayList<>();
    private static ArrayList<Integer> lists = new ArrayList<>();

    public static void main(String[] args) {
        int[] pre = {10, 5, 4, 7, 12};
        int[] in = {4, 5, 7, 10, 12};
        TreeNode node = TreeNode.setBinaryTree(pre, in);
        ArrayList<ArrayList<Integer>> resList = findPath(node, 22);
        System.out.println(resList);
    }

    private static ArrayList<ArrayList<Integer>> findPath(TreeNode node, int target) {
        if (node == null) return listall;
        lists.add(node.val);
        target -= node.val;
        if (target == 0 && node.left == null && node.right == null) {
            listall.add(new ArrayList<>(lists));
        }
        findPath(node.left, target);
        findPath(node.right, target);
        lists.remove(lists.size() - 1);
        return listall;
    }
}
