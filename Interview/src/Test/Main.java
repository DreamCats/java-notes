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
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        int l = 0, r = n - 1;
        int cnt = 0;
        while (l < r){
            if (a[l] == a[r]){
                l++;
                r--;
            } else if (a[l] < a[r]){
                a[l + 1] = a[l] + a[l + 1];
                l++;
                cnt++;
            } else {
                a[r - 1] = a[r] + a[r - 1];
                r--;
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}
