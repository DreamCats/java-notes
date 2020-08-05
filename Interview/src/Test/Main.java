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
        int sum = 0;
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
            sum += a[i];
        }
        System.out.println(minCount(a, sum));
    }

    public static int minCount(int[] a, int sum){
        if (sum % a.length != 0)
            return -1;
        int avg = sum / a.length;
        int cnt = 0;
        for (int i = 0; i < a.length; i++) {
            int sub = a[i] - avg;
            if (sub > 0){
                if (sub % 2 == 0)
                    cnt += sub / 2;
                else
                    return -1;
            }

        }
        return cnt;
    }
}
