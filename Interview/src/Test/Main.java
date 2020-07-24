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
        int k = sc.nextInt();
        int[] nums1 = new int[n];
        int[] nums2 = new int[n];
        int[] sumInterest = new int[n];
        for (int i = 0; i < n; i++) {
            nums1[i] = sc.nextInt();
        }
        int sum0 = 0;
        int sum1 = 0;
        for (int i = 0; i < n; i++) {
            nums2[i] = sc.nextInt();
            if (nums2[i] == 1)
                sum0 += nums1[i];
            else
                sum1 += nums1[i];
            sumInterest[i] = sum1;
        }
        int cur = 0;
        int max = 0;
        for (int i = 0; i < n; i++) {
            if (nums2[i] == 0){
                if (i + k - 1 <= n - 1) {
                    cur = sumInterest[i + k - 1] - (i - 1 > 0 ? sumInterest[i - 1] : 0);
                } else {
                    cur = sumInterest[n - 1] - (i - 1 > 0 ? sumInterest[i - 1] : 0);
                }
            }
            max = Math.max(cur, max);
        }
        System.out.println(max + sum0);
    }
}
