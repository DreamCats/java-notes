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
        int x = sc.nextInt();
        int f = sc.nextInt();
        int d = sc.nextInt();
        int p = sc.nextInt();
        if ((d - x * f) < 0) {
            // 说明 钱不够
            System.out.println(d / x);
        } else {
            // 说明钱够
            int sub = d - x * f; // 剩下的钱
            int day = f;
            System.out.println(day + sub / (x + p));
        }
    }
}