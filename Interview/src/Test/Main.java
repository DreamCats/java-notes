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
        int a = 0, b = 0, c = 0, d = 0;
        int[] nums = new int[4];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = sc.nextInt();
        }
        a = (nums[0] + nums[2]) / 2;
        b = (nums[1] + nums[3]) / 2;
        c = nums[3] - b;
        if (b - c != nums[1])
            System.out.println("No");
        else if (a - b != nums[0])
            System.out.println("No");
        else if (a + b != nums[2])
            System.out.println("No");
        else
            System.out.println(a + " " + b + " " + c);
    }
}
