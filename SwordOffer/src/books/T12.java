package books;

/**
 * @program JavaBooks
 * @description: 矩阵中的路径
 * @author: mf
 * @create: 2019/08/24 15:02
 */

/*
请设计一个函数，用来判断在一个矩阵中是否存在一条包含某
字符串所有字符的路径。路径可以从矩阵中的任意一格开始，每一步
可以在矩阵中向左、右、上、下移动一格。如果一条路径经过了矩阵
的某一格，那么该路径不能再次进入该格子。例如，在下面的3x4的矩阵中包含一条
字符串"afce"的路径（路径中的字母用下画线标出。）但矩阵中不包含字符串"abfb"的
路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次
进入这个格子
 */
public class T12 {
    public static void main(String[] args) {
        char[][] arr = {
                {'a', 'b', 't', 'g'},
                {'c', 'f', 'c', 's'},
                {'j', 'd', 'e', 'h'}};
        char[] s = {'b', 'f', 'c', 'e', '\0'};
        System.out.println(hasPath(arr, s));

    }

    public static boolean hasPath(char[][] arr, char[] s) {
        if (arr == null || arr.length < 1 || arr[0].length < 1) return false;
        int rowNum = arr.length;
        int colNum = arr[0].length;
        int pathNum = 0;
        boolean[][] visited = new boolean[rowNum][colNum];
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                if (hasPathCore(arr, rowNum, colNum, i, j, s, pathNum, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean hasPathCore(char[][] arr, int rowNum, int colNum, int i, int j, char[] s, int pathNum, boolean[][] visited) {
        if (s[pathNum] == '\0') {
            return true;
        }

        boolean hasPath = false;
        if (i >= 0 && i < rowNum && j >= 0 && j < colNum && arr[i][j] == s[pathNum] && !visited[i][j]){
            pathNum++;
            visited[i][j] = true;
            hasPath = hasPathCore(arr, rowNum, colNum, i, j -1, s, pathNum, visited)
                    ||hasPathCore(arr, rowNum, colNum, i - 1, j, s, pathNum, visited)
                    ||hasPathCore(arr, rowNum, colNum, i, j + 1, s, pathNum, visited)
                    ||hasPathCore(arr, rowNum, colNum, i + 1, j, s, pathNum, visited);
            if (!hasPath){
                pathNum--;
                visited[i][j] = false;
            }
        }
        return hasPath;
    }

}
