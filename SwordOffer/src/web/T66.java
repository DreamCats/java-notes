package web;

/**
 * @program LeetNiu
 * @description: 机器人的运动范围
 * @author: mf
 * @create: 2020/01/17 23:46
 */

public class T66 {
    public int movingCount(int threshold, int rows, int cols) {
        boolean[][] visited = new boolean[rows][cols];
        return countingStep(threshold,rows,cols,0,0,visited);
    }

    public int countingStep(int limit, int rows, int cols, int r, int c, boolean[][] visited) {
        if (r < 0 || r >= rows || c < 0 || c >= cols || visited[r][c] || bitSum(r) + bitSum(c) > limit) {
            return 0;
        }
        visited[r][c] = true;
        return countingStep(limit,rows,cols,r - 1,c,visited) + countingStep(limit,rows,cols,r,c - 1,visited)+countingStep(limit,rows,cols,r+1,c,visited)+countingStep(limit,rows,cols,r,c+1,visited) + 1;
    }

    public int bitSum(int t) {
        int count = 0;
        while (t != 0) {
            count += t % 10;
            t /= 10;
        }
        return count;
    }
}
