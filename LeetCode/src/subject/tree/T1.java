/**
 * @program JavaBooks
 * @description: 相同的树
 * @author: mf
 * @create: 2020/03/09 13:14
 */

package subject.tree;

/**
 * 输入:       1         1
 *           / \       / \
 *          2   3     2   3
 *
 *         [1,2,3],   [1,2,3]
 * 输出 true
 */
public class T1 {
    /**
     * 递归判断(实际上前行遍历)
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true; // 递归到最后二者都是null 说明相同
        if (p == null || q == null) return false; // 递归到其中有一方为空，则不相同
        if (p.val != q.val) return false; // 值不相等
        return (p.left == q.left) && (p.right == q.right);
    }
}
