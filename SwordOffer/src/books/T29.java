package books;

/**
 * @program JavaBooks
 * @description: 顺时针打印矩阵
 * @author: mf
 * @create: 2019/09/10 10:31
 */

/*
输入一个矩阵，按照从外向里以顺时针依次打印出每一个数字。
例如
 1 2 3 4
 5 6 7 8
 9 10 11 12
 13 14 15 16
 */

/*
思路：
考虑圈的截止条件，从左上作为圈的开始和结束
然后按圈打印，注意按圈打印的四个边界条件。

 */
public class T29 {
    public static void main(String[] args) {
        int[][] arr = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        printMatrixClock(arr, arr.length, arr[0].length);
    }

    private static void printMatrixClock(int[][] arr, int rows, int cols) {
        if (arr == null || rows <= 0 || cols <= 0) return;
        int start = 0;
        while (rows > start * 2 && cols > start * 2) {// 判断打圈的条件
            printMatrixCircle(arr, rows, cols, start); // 打印圈
            start++;
        }
    }

    private static void printMatrixCircle(int[][] arr, int rows, int cols, int start) {
        int endX = cols - 1 - start;
        int endY = rows - 1 - start;
        // 从左到右打印一行
        for (int i = start; i <= endX; i++) {
            System.out.print(arr[start][i]);
            System.out.print('\t');
        }
        // 从上到下打印一列
        if (start < endY) {
            for (int i = start + 1; i <= endY; i++) {
                System.out.print(arr[i][endX]);
                System.out.print('\t');
            }
        }
        // 从右往左打印一行
        if (start < endX && start < endY) {
            for (int i = endX - 1; i >= start; i--) {
                System.out.print(arr[endY][i]);
                System.out.print('\t');
            }
        }

        // 从下往上打印一列
        if (start < endX && start < endY) {
            for (int i = endY - 1; i >= start + 1; i--) {
                System.out.print(arr[i][start]);
                System.out.print('\t');
            }
        }
    }
}
