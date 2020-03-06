package web; /**
 * @program LeetNiu
 * @description: 树的子结构
 * @author: mf
 * @create: 2020/01/10 16:07
 */

/**
 * 输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
 */
public class T17 {

    public boolean HasSubtree(TreeNode root1,TreeNode root2) {
        // 判断
        if (root1 == null || root2 == null) return false;
        boolean result = false;
        if (root1.val == root2.val) {
            result = goOnFind(root1, root2);
        }
        if (!result) {
            result = HasSubtree(root1.left, root2);
        }
        if (!result) {
            result = HasSubtree(root1.right, root2);
        }
        return result;
    }

    /**
     * 递归找
     * @param root1
     * @param root2
     * @return
     */
    private boolean goOnFind(TreeNode root1, TreeNode root2) {
        if (root2 == null) return true;
        if (root1 == null) return false;
        if (root1.val != root2.val) return false;
        return goOnFind(root1.left, root2.left) && goOnFind(root1.right, root2.right);
    }
}
