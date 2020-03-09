/**
 * @program JavaBooks
 * @description: 将有序数组转成二叉搜索树
 * @author: mf
 * @create: 2020/03/09 17:06
 */

package subject.tree;

public class T4 {
    /**
     * 中序逆遍历
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return nums == null ? null : buildTreee(nums, 0, nums.length - 1);
    }

    public TreeNode buildTreee(int[] nums, int l, int r) {
        if (l > r) return null;
        int m = l + (r - l) / 2;
        // left -> root -> right
        TreeNode root = new TreeNode(nums[m]); // new一个root
        root.left = buildTreee(nums, l, m - 1);
        root.right = buildTreee(nums, m + 1, r);
        return root;
    }
}
