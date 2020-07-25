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
        if (n % 8 == 0)
            System.out.println(n / 8);
        else {
            int t = n / 8;
            if (t == 0 && n % 6 == 0)
                System.out.println(n / 6);
            else {
                n -= (t - 1) * 8;
                if (n % 6 == 0)
                    System.out.println(t - 1 + (n / 6));
                else
                    System.out.println(-1);
            }
        }
    }
}
