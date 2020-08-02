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
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int x = sc.nextInt();
            if (x > max1){
                max3 = max2;
                max2 = max1;
                max1 = x;
            } else if (x > max2){
                max3 = max2;
                max2 = x;
            } else if (x > max3)
                max3 = x;
            if (x < min1){
                min2 = min1;
                min1 = x;
            } else if (x < min2)
                min2 = x;
        }
        System.out.println(Math.max(max1 * max2 * max3, max1 * min1 * min2));
    }
}
