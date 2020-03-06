package books;

/**
 * @program JavaBooks
 * @description: 数组中只出现一次的两个数字
 * @author: mf
 * @create: 2019/10/08 10:10
 */
/*
一个整型数组里除两个数字之外，其他数字都出现了两次。
请写程序找出这两个只出现一次的数字。要求时间复杂度是o(n)，
空间复杂度是o(1)
 */
public class T56 {
    public static void main(String[] args) {
        int[] arr = {2, 4, 3, 6, 3, 2, 5, 5,};
        int[] num1 = new int[1];
        int[] num2 = new int[1];
        findNumsAppearOnce(arr, num1, num2);
        System.out.println(num1[0] + "..." + num2[0]);
    }

    private static void findNumsAppearOnce(int[] arr, int[] num1, int[] num2) {
        if (arr == null || arr.length < 2) return;
        int exorRes = 0;
        for (int num : arr) {
            exorRes ^= num;
        }
            System.out.println(exorRes);
        //从右向左找到第一个为1的位的位置
        int firstOneIndex = findFirstOneBit(exorRes);
        for (int num : arr) {
            // 分成两个子数组
            if (isOneBitAt(firstOneIndex, num)) {
                num1[0] ^= num;
            } else {
                num2[0] ^= num;
            }
        }
    }

    /**
     * bitIndex处的位数是否为1
     * @param bitIndex
     * @param num
     * @return
     */
    private static boolean isOneBitAt(int bitIndex, int num) {
        num = num >> bitIndex;
        return (num & 1) == 1;
    }

    /**
     * 从右向左找到第一个为1的位的位置
     * @param exorRes
     * @return
     */
    private static int findFirstOneBit(int exorRes) {
        int oneBitIndex = 0;
        while ((exorRes & 1) == 0 && oneBitIndex < 32) {
            exorRes = exorRes >> 1;
            oneBitIndex++;
        }
        return oneBitIndex;
    }
}
