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
        int y = sc.nextInt();

        System.out.println(reverse(reverse(x) + reverse(y)));
    }

    public static int reverse(int x) {
        int res = 0;
        while (x != 0){
            res = res * 10 + x % 10;
            x /= 10;
        }
        return res;
    }
}
