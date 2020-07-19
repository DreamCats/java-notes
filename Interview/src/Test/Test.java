/**
 * @program JavaBooks
 * @description: 测试
 * @author: mf
 * @create: 2020/05/24 00:04
 */

package Test;

import java.util.Scanner;

public class Test {

    public static void main(String[] args) {

//        Pair
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
        int n = sc.nextInt();
        long[] nums = new long[n];
        for (int i = 0; i < n; i++)
            nums[i] = sc.nextInt();
        long max = 0;
        long b = 0, c = 0;
        for(int i = 1; i < n; i++){
            if (nums[i] > nums[i - 1]){
                max += nums[i] - nums[i - 1];
                if (c == 0){
                    b++;
                }
                c = 1;
            }
            if (nums[i] < nums[i - 1]){
                b += c;
                c = 0;
            }
        }
        b += c;
        System.out.println(max + " " + b);
    }


}
