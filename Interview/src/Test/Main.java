/**
 * @program JavaBooks
 * @description: 牛客
 * @author: mf
 * @create: 2020/07/24 22:24
 */

package Test;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = 3;
        int i = 0;
        Integer[] nums = new Integer[n];
        while (n-- > 0){
            nums[i++] = sc.nextInt();
        }
        Arrays.sort(nums, (o1, o2) -> o2 - o1);
        System.out.println((nums[0] - nums[1]) + (nums[1] - nums[2]));
    }
}
