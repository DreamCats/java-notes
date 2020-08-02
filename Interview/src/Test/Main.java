/**
 * @program JavaBooks
 * @description: 牛客
 * @author: mf
 * @create: 2020/07/24 22:24
 */

package Test;


import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int h = sc.nextInt();
        int[] hs = new int[h];
        for (int i = 0; i < h; i++) {
            hs[i] = sc.nextInt();
        }
        int w = sc.nextInt();
        int[] ws = new int[w];
        for (int i = 0; i < w; i++) {
            ws[i] = sc.nextInt();
        }
        Arrays.sort(hs);
        Arrays.sort(ws);
        int i = 0, j = 0;
        while (i < h && j < w){
            if (hs[i] <= ws[j])
                i++;
            j++;
        }
        System.out.println(i);
    }
}
