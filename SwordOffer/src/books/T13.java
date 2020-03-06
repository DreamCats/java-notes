package books;

/**
 * @program JavaBooks
 * @description: 机器人的运动范围
 * @author: mf
 * @create: 2019/08/25 09:45
 */

/*
地上有一个m行n列的方格。一个机器人从坐标(0,0)的格子开始移动
它每次可以向左、右、上、下移动一格，但不能进入行坐标和列坐标
的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格(35, 37), 因为
3+5+3+7=18。但它不能进入方格(35, 38)，因为3+5+3+8=19。请问？
该机器人能够到达多少个格子。
 */
public class T13 {
    public static void main(String[] args) {

        int count = movingCount(18, 40, 40);
        System.out.println(count);
    }

    public static int movingCount(int threshold, int rows, int cols) {
        if (threshold < 0 || rows < 1 || cols < 0) return 0;
        boolean[][] visited = new boolean[rows][cols];
        int count = movingCountCore(threshold, rows, cols, 0, 0, visited);
        return count;

    }

    public static int movingCountCore(int threshold, int rows, int cols, int i, int j, boolean[][] visited) {
        int count = 0;
        if (check(threshold, rows, cols, i, j, visited)) {
            visited[i][j] = true;
            // 核心之二
            count = 1 + movingCountCore(threshold, rows, cols, i, j - 1, visited)
                    + movingCountCore(threshold, rows, cols, i, j + 1, visited)
                    + movingCountCore(threshold, rows, cols, i - 1, j, visited)
                    + movingCountCore(threshold, rows, cols, i + 1, j, visited);
        }
        return count;
    }

    public static boolean check(int threshold, int rows, int cols, int i, int j, boolean[][] visited) {
        // 核心之一
        if (i >=0 && j >= 0 && i < rows && j < cols
                && getDigiSum(i) + getDigiSum(j) <= threshold
                && !visited[i][j]) return true;
        return false;
    }


    // 常用方法，求一个数的总和
    public static int getDigiSum(int i) {
        int sum = 0;
        while (i > 0 ) {
            sum += i % 10;
            i /= 10;
        }

        return sum;
    }
}
