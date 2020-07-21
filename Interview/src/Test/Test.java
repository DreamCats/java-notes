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

    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        sc.nextLine();
        int[] a = new int[26];
        String s = sc.nextLine();
        for(int i = 0; i < n; i++){
            a[s.charAt(i) - 'A']++;
        }
        Arrays.sort(a);
        System.out.println(Arrays.toString(a));
        int sum = 0;
        for (int i = a.length - 1; i >= 0; i--){
            if (m >= a[i]){
                sum += a[i] * a[i];
                m -= a[i];
            } else {
                sum += m * m;
                m -= m;
            }
        }

        System.out.println(sum);
    }

}
