/**
 * @program JavaBooks
 * @description: 108. 将有序数组转换为二叉搜索树
 * @author: mf
 * @create: 2019/11/08 15:34
 */

/*
题目：
 */
public class SortedArrayToBST {
    public static void main(String[] args) {

    }

    /**
     * [Java] 二叉树中序遍历的逆过程哦
     * @param nums
     * @return
     */
    private static TreeNode sortedArrayToBST(int[] nums) {
        // 左右等分建立左右子树，中间节点作为子树根节点，递归该过程
        return nums == null ? null : buildTree(nums, 0, nums.length - 1);
    }
    private static TreeNode buildTree(int[] nums, int l, int r) {
        if (l > r) {
            return null;
        }
        int m = l + (r - l) / 2;
        TreeNode root = new TreeNode(nums[m]);
        root.left = buildTree(nums, l, m - 1);
        root.right = buildTree(nums, m + 1, r);
        return root;
    }
}
