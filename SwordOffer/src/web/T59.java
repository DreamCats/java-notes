package web; /**
 * @program LeetNiu
 * @description: 按之字形顺序打印二叉树
 * @author: mf
 * @create: 2020/01/16 15:31
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * 请实现一个函数按照之字形打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右至左的顺序打印，第三行按照从左到右的顺序打印，其他行以此类推。
 */
public class T59 {
    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
        if (null == pRoot) return ret;
        ArrayList<Integer> list = new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addLast(null); // 层分隔符
        queue.addLast(pRoot);
        boolean leftToRight = true;

        while(queue.size() != 1) {
            TreeNode node = queue.removeFirst();
            if (null == node) {
                Iterator<TreeNode> iter = null;
                if (leftToRight) {
                    iter = queue.iterator();
                } else {
                    iter = queue.descendingIterator();
                }
                leftToRight = ! leftToRight;
                while(iter.hasNext()) {
                    TreeNode temp = (TreeNode) iter.next();
                    list.add(temp.val);
                }
                ret.add(new ArrayList<>(list));
                list.clear();
                queue.addLast(null);
                continue;
            }
            if (node.left != null) {
                queue.addLast(node.left);
            }
            if (node.right != null) {
                queue.addLast(node.right);
            }
        }
        return ret;
    }
}
