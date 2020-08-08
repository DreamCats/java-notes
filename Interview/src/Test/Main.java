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
        int n = sc.nextInt();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int sum = 0;
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
            sum += a[i];
            min = Math.min(min, a[i]);
            max = Math.max(max, a[i]);
        }
        if (min == max || sum == (max + min) * n / 2)
            System.out.println("Possible");
        else
            System.out.println("Impossible");
    }
}
