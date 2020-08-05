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
        long h = sc.nextInt();
        for (int i = 1; i <= h; i++) {
            if (i * (i + 1) > h){
                System.out.println(i - 1);
                break;
            }
        }
    }
}





// 以下暂时保留， 是pdd的笔试题
// pdd的面试喜欢问笔试的思路

class Main10 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] a = new int[6][6];
        for (int i = 0; i < 6; i++) {
            String s = sc.nextLine();
            for (int j = 0; j < s.length(); j++) {
                char c  = s.charAt(j);
                if (c == '#')
                    a[i][j] = 0;
                else
                    a[i][j] = 1;
            }
        }
        System.out.println(Arrays.toString(a[0]));
    }
}


class Main4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        int i = 0, back = 0;
        if (k == 0)
            System.out.println("paradox");
        else {
            while (k != 0 && i < n){
                k -= a[i];
                if (k == 0){
                    System.out.println("paradox");
                    break;
                }
                if (k < 0){
                    k = -k;   // 我试试
                    back++;
                }
                i++;
            }
            if (k != 0)
                System.out.println(k + " " + back);
        }
    }

}

class Main3 {

    public static void main(String[] args) {
        // ok
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int T = sc.nextInt();
        int ans = Integer.MAX_VALUE - 1;
        int[][] lun = new int[N][2];
        for (int i = 0; i < N; i++) {
            lun[i][0] = sc.nextInt();
            lun[i][1] = sc.nextInt();
            if (lun[i][1] >= T)
                ans = Math.min(lun[i][0], ans);
        }
        int[][] din = new int[M][2];
        for (int i = 0; i < M; i++) {
            din[i][0] = sc.nextInt();
            din[i][1] = sc.nextInt();
            if (din[i][1] >= T)
                ans = Math.min(din[i][0], ans);
        }

        Arrays.sort(din, (a, b) -> a[0]- b[0]);
        if (T == 0)
            System.out.println(0);
        else {
            for (int i = 0; i < M; i++) {
                int hot = bisect(din, T - lun[i][1]);
                ans = Math.min(ans, hot + lun[i][0]);
            }
            if (ans > 0 && ans < Integer.MAX_VALUE)
                System.out.println(ans);
            else
                System.out.println(-1);
        }
    }

    public static int bisect(int[][] a, int t){
        int lo = 0, hi = a.length;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (a[mid][1] < t)
                lo = mid + 1;
            else
                hi = mid;
        }
        if (lo < a.length)
            return a[lo][0];
        else
            return Integer.MAX_VALUE;
    }
}


//import java.util.Scanner;

class Main1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int T = sc.nextInt();
        int[][] lun = new int[N][2];
        for (int i = 0; i < N; i++) {
            lun[i][0] = sc.nextInt();
            lun[i][1] = sc.nextInt();
        }
        int[][] din = new int[M][2];
        for (int i = 0; i < M; i++) {
            din[i][0] = sc.nextInt();
            din[i][1] = sc.nextInt();
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (lun[i][1] >= T)
                    ans = Math.min(ans, lun[i][0]);
                if (din[j][1] >= T)
                    ans = Math.min(ans, din[j][0]);
                if (lun[i][1] + din[j][1] >= T)
                    ans = Math.min(ans, lun[i][0] + din[j][0]);
            }
        }
        if (T == 0)
            System.out.println(0);
        else if (ans < Integer.MAX_VALUE)
            System.out.println(ans);
        else
            System.out.println(-1);
    }
}


class Main2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        int sum = 0;
        int j = 0;
        for (int i = 0; i < n; i++) {
            sum += a[i];
            if (sum == k){
                System.out.println("paradox");
                break;
            } else if (sum > k){
                j = i;
                break;
            }
        }
        if (k == 0){
            System.out.println("paradox");
        } else if (sum < k)
            System.out.println(k - sum + " " + "0");
        else if (sum > k && j != 0) {
            System.out.println(k - (sum - a[j]) + " " + (n - j));
        }
    }

}
