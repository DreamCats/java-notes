/**
 * @program JavaBooks
 * @description: 测试
 * @author: mf
 * @create: 2020/05/24 00:04
 */

package Test;

import java.util.Arrays;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) {
//        int[] a = new int[]{1,2, 3};

//        int[] b = new int[3];

//        List<Integer> list = new ArrayList<>();
//        list.add(1);
//        list.add(1);

//        int[] c = list.stream().mapToInt(Integer::valueOf).toArray();
//        for (int i : c) {
//            System.out.println(i);
//        }

//        int[] c = a;
//        c[0] = 3;
//        System.out.println(Arrays.toString(a));
//        System.out.println(Arrays.toString(c));

        Scanner sc = new Scanner(System.in);
        String[] ll = sc.nextLine().split(" ");
        int m = Integer.parseInt(ll[0]);
        int n = Integer.parseInt(ll[1]);
        int len = Integer.parseInt(ll[2]);
        long[][] nums = new long[n + 1][m + 1];
        for (int i = 0; i < nums.length; i++) {
            nums[i][0] = 1;
        }
        Arrays.fill(nums[0], 1);
        for (int i = 0; i < len; i++) {
            String[] ss = sc.nextLine().split(" ");
            int y = Integer.parseInt(ss[0]);
            int x = Integer.parseInt(ss[1]);
            nums[y][x] = -1;
        }
        differ(nums);
        System.out.println(nums[m][n]);
        System.out.println("结束了...");

    }

    public static void differ(long[][] nums) {
        for (int i = 1; i < nums.length; i++) {
            for (int j = 1; j < nums[0].length; j++) {
                if (nums[i][j] == -1)
                    nums[i][j] = 0;
                else
                    nums[i][j] = nums[i - 1][j] + nums[i][j - 1];
            }
        }
    }

}
