package books;

/**
 * @program JavaBooks
 * @description: 二维数组中的查找
 * @author: mf
 * @create: 2019/08/17 10:42
 */


/*
在一个二维数组中，每一行都按照从左到右递增的顺序排序
每一列都按照从上到下递增的排序顺序。请完成一个人函数
输入这样的一个二维数组和一个整数，判断数组中是否含有该函数
 */
public class T4 {

    public static void main(String[] args) {
        int[][] arr = {{1, 2, 8, 9},
                       {2, 4, 9, 12},
                       {4, 7, 10, 13},
                       {6, 8, 11, 15}};
        boolean res = find(arr, 7);
        System.out.println(res);
    }

    /**
     * 数组规律特性，边界问题
     * @param arr
     * @param target
     * @return
     */
    public static boolean find(int[][] arr, int target) {
        int row = 0;
        // i = 最后一行，往上扫
        for (int i = arr.length - 1; i >= 0; i--) {
            // 往右扫
            // 只要小于target，row就++，不满足条件则跳到上一行，保持现在的row
            while (row < arr[0].length && arr[i][row] < target) row++;
            if (row == arr[0].length) return false;// row 越界 flase
            if(arr[i][row] == target) return true; // 判断是否相等
        }
        return false;
    }
}
