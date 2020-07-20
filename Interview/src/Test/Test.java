/**
 * @program JavaBooks
 * @description: 测试
 * @author: mf
 * @create: 2020/05/24 00:04
 */

package Test;

import java.util.Scanner;

public class Test {

    public static void main(String[] args) throws InterruptedException {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[100001];
        for (int i = 0; i < n; i++){
            int x = sc.nextInt();
            int y = sc.nextInt();
            nums[x]++;
            nums[y]--;
        }
        int cnt = 0;
        int res = 0;
        for (int i = 1; i < nums.length; i++){
            cnt += nums[i];
            res = Math.max(res, cnt);
        }
        System.out.println(res);
    }


}
