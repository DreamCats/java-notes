package web; /**
 * @program LeetNiu
 * @description: 把二叉树打印多行
 * @author: mf
 * @create: 2020/01/17 21:11
 */

import java.util.ArrayList;

/**
 * 从上到下按层打印二叉树，同一层结点从左至右输出。每一层输出一行。
 */
public class T60 {

    ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        depth(pRoot, 1, list);
        return list;
    }

    private void depth(TreeNode root, int depth, ArrayList<ArrayList<Integer>> list) {
        if (null == root) {
            return;
        }
        if (depth > list.size()) {
            list.add(new ArrayList<>());
        }

        list.get(depth - 1).add(root.val);
        depth(root.left, depth + 1, list);
        depth(root.right, depth + 1, list);
    }
}
