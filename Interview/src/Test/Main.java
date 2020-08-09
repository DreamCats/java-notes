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
        System.out.println(maxSub(a));
    }

    public static int maxSub(int[] a) {
        Arrays.sort(a);
        int n = a.length;
        int[] b = new int[n];
        int l = 0, r = n - 1;
        int m = n / 2;
        int p = m - 1;
        while (l <= r || m < n || p > 0) {
            if (m < n)
                b[m++] = (m - 1) % 2 == 0 ? a[r--] : a[l++];
            if (p >= 0)
                b[p--] = (p + 1) % 2 == 0 ? a[r--] : a[l++];
        }
        int max = 0;
        for (int i = 1; i < n; i++) {
            max += Math.abs(b[i] - b[i - 1]);
        }
        return max;
    }
}

