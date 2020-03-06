package books;

/**
 * @program JavaBooks
 * @description: 树的子结构
 * @author: mf
 * @create: 2019/09/06 10:07
 */

/*
输入两颗二叉树A和B，判断B是不是A的子结构。
 */

/*
思路
两个递归， 第一个大递归的功能是遍历A的node和B树root是否相等，终止条件就是是否为空，如果都不相等，最后肯定是false
当相等的时候，那第二个递归的功能就是同时遍历A树和B树的对应的左右子节点是否相等，终止条件就是
 */
public class T26 {
    public static void main(String[] args) {
        int[] preA = {8, 8, 9, 2, 4, 7, 7};
        int[] inA = {9, 8, 4, 2, 7, 8, 7};

        int[] preB = {8, 9 , 2};
        int[] inB = {9, 8, 2};

        // 根据笔试题T7， 前序列和中序，重建二叉树
        TreeNode nodeA = TreeNode.setBinaryTree(preA, inA);
        TreeNode nodeB = TreeNode.setBinaryTree(preB, inB);

//        System.out.println("前序A：");
//        TreeNode.preOrderRe(nodeA);
//        System.out.println("前序B：");
//        TreeNode.preOrderRe(nodeB);

        boolean isSubTree = hasSubTree(nodeA, nodeB);
        System.out.println(isSubTree);
    }

    private static boolean hasSubTree(TreeNode nodeA, TreeNode nodeB) {

        boolean result = false;
        // 注意条件
        if (nodeA != null && nodeB != null) {
            if (nodeA.val == nodeB.val) {
                // root's val equal  , so 开始遍历子节点
                result = goOnFind(nodeA, nodeB);
            }
            // 递归找左
            if (!result) {
                result = hasSubTree(nodeA.left, nodeB);
            }
            // 递归找右
            if (!result) {
                result = hasSubTree(nodeA.right, nodeB);
            }
        }

        return result;
    }

    private static boolean goOnFind(TreeNode nodeA, TreeNode nodeB) {
        if (nodeB == null) return true; // 说明b提前遍历完成
        if (nodeA == null) return false; // 说明B还有节点，A没有子节点等
        if (nodeA.val != nodeB.val) return false; // 说明值不相等
        // 递归子节点
        return goOnFind(nodeA.left, nodeB.left) && goOnFind(nodeA.right, nodeB.right);
    }
}
