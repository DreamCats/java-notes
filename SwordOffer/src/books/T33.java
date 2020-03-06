package books;

/**
 * @program JavaBooks
 * @description: 二叉搜索树的后序遍历序列
 * @author: mf
 * @create: 2019/09/14 21:15
 */

/*
输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果
如果是则返回true，否则返回false。假设输入的数组的任意两个数字
都互不相同。例如，输入数组{5, 7, 6, 9, 11, 10, 8}, 则返回true
因为这个整数序列是书上图4.9二叉搜索树的后序遍历结果。如果输入的数组是
{7，4，6，5}
 */
public class T33 {
    public static void main(String[] args) {
        int[] sequence = {5, 7, 6, 9, 11, 10, 8};
        boolean res = VerifySquenceOfBST(sequence);
        System.out.println(res);
    }

    private static boolean VerifySquenceOfBST(int[] sequence) {
        if (sequence == null || sequence.length == 0) return false;
        return isBST(sequence, 0, sequence.length - 1);
    }

    private static boolean isBST(int[] sequence, int start, int end) {
        if (start >= end) {
            return true;
        }
        int inx = sequence[end];
        int m = start;
        // 找到分界点
        for (int i = end - 1; i >= start; i--) {
            if (sequence[i] < inx) {
                m = i;
                break;
            }
            if (i == start) {
                m = -1;
            }
        }
        // 分界点前的数据都小于根节点
        for (int i = start; i <= m; i++) {
            if (sequence[i] > inx) {
                return false;
            }
        }
        // 递归判断根节点的左右子树
        return isBST(sequence, start, m) && isBST(sequence, m + 1, end - 1);

    }
}
