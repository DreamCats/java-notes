package web; /**
 * @program LeetNiu
 * @description: 二叉搜索树的后序遍历序列
 * @author: mf
 * @create: 2020/01/13 10:31
 */

/**
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。如果是则输出Yes,否则输出No。
 * 假设输入的数组的任意两个数字都互不相同。
 */
public class T23 {
    public boolean VerifySquenceOfBST(int [] sequence)  {
        if (sequence == null || sequence.length == 0) return false;
        return isBST(sequence, 0, sequence.length - 1);
    }

    private boolean isBST(int[] seq, int start, int end) {
        if (start >= end) {
            return true;
        }
        int inx = seq[end];
        int m = start;
        // 找到分界点
        for (int i = end - 1; i >= start; i--) {
            if (seq[i] < inx) {
                m = i;
                break;
            }
            if (i == start) {
                m = -1;
            }
        }
        // 分界点前的数据都小于根节点
        for (int i = start; i <= m; i++) {
            if (seq[i] > inx) return false;
        }

        // 递归
        return isBST(seq, start, m) && isBST(seq, m + 1, end - 1);
    }
}
