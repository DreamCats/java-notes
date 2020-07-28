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
        int num = sc.nextInt();
        int x = (int) Math.sqrt(num);
        int l = 0, r = x;
        int sum = 0;
        while (l < r) {
            if (l == 0 && r * r == num){
                sum += 4;
                l++;
                r--;
            }
            else if ((l * l + r * r) == num) {
                sum += 8;
                l++;
                r--;
            }
            else if ((l * l + r * r) < num)
                l++;
            else
                r--;
        }
        if (l == r)
            System.out.println(sum += 4);
        else
            System.out.println(sum);
    }
}
