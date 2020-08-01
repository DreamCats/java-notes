/**
 * @program JavaBooks
 * @description: 牛客
 * @author: mf
 * @create: 2020/07/24 22:24
 */

package Test;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int w = sc.nextInt();
        int h = sc.nextInt();
        int[][] a = new int[w][h];
        int cnt = 0;
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                if (a[i][j] == 0){
                    cnt++;
                    if ((i + 2) < w)
                        a[i + 2][j] = -1;
                    if ((j + 2) < h)
                        a[i][j + 2] = -1;
                }
            }
        }
        System.out.println(cnt);
    }
}
