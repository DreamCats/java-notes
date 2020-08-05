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
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        System.out.println(min(a, n));
    }

    public static int min(int[] a, int n){
        for (int i = n - 1; i >= 0; i--) {
            Arrays.sort(a);
            for (int j = i - 1; j >= 0; j--) {
                if ((a[i] ^ a[j]) < a[j])
                    a[j] ^= a[i];
            }
        }
        int cnt = 0;
        for (int i : a) {
            if (i != 0)
                cnt++;
        }
        return cnt;
    }
}
