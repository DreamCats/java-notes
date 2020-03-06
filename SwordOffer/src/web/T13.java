package web; /**
 * @program LeetNiu
 * @description: 调整数组顺序使奇数位于偶数前面
 * @author: mf
 * @create: 2020/01/10 14:03
 */

/**
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，
 * 所有的偶数位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。
 */
public class T13 {

    public void reOrderArray(int [] array) {
        // 判断
        if (array == null || array.length == 0) return;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1; j++) {
                if ((array[j] & 0x1) == 0 && (array[j + 1] & 0x1) == 1) {
                    swap(array, j, j + 1);
                }
            }
        }
    }

    /**
     * 数据交换
     * @param arr
     * @param x
     * @param y
     */
    private void  swap(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }
}
