package web; /**
 * @program LeetNiu
 * @description: 顺时针打印矩阵
 * @author: mf
 * @create: 2020/01/10 16:22
 */

import java.util.ArrayList;

/**
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，
 * 例如，如果输入如下4 X 4矩阵： 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
 * 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
 */
public class T19 {
    public ArrayList<Integer> printMatrix(int [][] matrix) {
        ArrayList<Integer> list = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return list;
        int start = 0;
        int rows = matrix.length;
        int cols = matrix[0].length;
        while (rows > start * 2 && cols > start * 2) {
            print(matrix, rows, cols, start, list);
            start++;
        }
        return list;
    }

    /**
     * 打印圈
     * @param arr
     * @param rows
     * @param cols
     * @param start
     * @param list
     */
    private void print(int[][] arr, int rows, int cols, int start, ArrayList<Integer> list) {
        int endX = cols - 1 - start;
        int endY = rows - 1 - start;
        // 从左到右打印一行
        for(int i = start; i <= endX; i++) {
            list.add(arr[start][i]);
        }
        // 从上到下打印一列
        if(start < endY) {
            for(int i = start + 1; i <= endY; i++){
                list.add(arr[i][endX]);
            }
        }

        // 从右往左打印哟行
        if(start < endX && start < endY) {
            for(int i = endX - 1; i >= start; i--) {
                list.add(arr[endY][i]);
            }
        }

        // 从下往上打印一列
        if(start < endX && start < endY) {
            for(int i = endY - 1; i >= start + 1; i--) {
                list.add(arr[i][start]);
            }
        }
    }
}
