package books;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @program JavaBooks
 * @description: 从上到下打印二叉树
 * @author: mf
 * @create: 2019/09/13 12:38
 */

/*
不分行从下到上打印二叉树
从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印
例如，见书
 */


/*
思路
这个题需要辅助队列容器
也就是说， 从上往下打印某个节点时，该节点的两个子节点不为空，就加进容器中，
直到打印到容器为空为止。
 */
public class T32 {
    public static void main(String[] args) {
        int[] pre = {8, 6, 5, 7, 10, 9, 11};
        int[] in = {5, 6, 7, 8, 9, 10, 11};
        TreeNode node = TreeNode.setBinaryTree(pre, in);
//        printNode(node);
        printNode2(node);
    }

    private static void printNode(TreeNode node) {
        if (node == null) return;
        // new一个队列
        LinkedBlockingQueue<TreeNode> queue = new LinkedBlockingQueue<>();

        queue.offer(node);
        while (!queue.isEmpty()) {
            TreeNode tempNode = queue.poll();
            System.out.print(tempNode.val + "\t");
            if (tempNode.left != null) {
                queue.offer(tempNode.left);
            }
            if (tempNode.right != null) {
                queue.offer(tempNode.right);
            }
        }
    }
    // 分行打印
    private static void printNode2(TreeNode node) {
        if (node == null) return;
        LinkedBlockingQueue<TreeNode> queue = new LinkedBlockingQueue<>();
        queue.offer(node);
        int cLevel = 1;
        int nextLevel = 0;
        while (!queue.isEmpty()) {
            TreeNode tempNode = queue.poll();
            System.out.print(tempNode.val + "\t");
            cLevel--;
            if (tempNode.left != null) {
                queue.offer(tempNode.left);
                nextLevel++;
            }
            if (tempNode.right != null) {
                queue.offer(tempNode.right);
                nextLevel++;
            }
            if (cLevel == 0) {
                System.out.print("\n");
                cLevel = nextLevel;
                nextLevel = 0;
            }

        }
    }
}
