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
        if (n <= 2) System.out.println(0);
        else {
            int pre2 = 0, pre1 = 1;
            for (int i = 3; i < n; i++) {
                int sum = pre2 + pre1;
                if (sum == n){
                    System.out.println(0);
                    break;
                }
                else if (pre1 < n && n < sum){
                    System.out.println(Math.min(n - pre1, sum - n));
                    break;
                }
                pre2 = pre1;
                pre1 = sum;
            }
        }
    }
}
