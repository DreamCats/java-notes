import java.util.*;


class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] a = new int[n][3];
        int len = 0;
        for (int i = 0; i < n; i++) {
            a[i][0] = sc.nextInt() - 1;
            a[i][1] = sc.nextInt() - 1;
            len = Math.max(len, a[i][1]);
            a[i][2] = sc.nextInt();
        }
        Arrays.sort(a, (x,y) -> x[1] - y[1]);
        int[] dp = new int[len + 1];
        for (int i = 0; i < n; i++) {
            int l = a[i][0];
            int r = a[i][1];
            int w = a[i][2];
            int[] tmp = Arrays.copyOfRange(dp, 0, l + 1);
            int max = Arrays.stream(tmp).max().orElse(0);
            if (l > 0)
                dp[r] = Math.max(dp[r], max + w);
            else
                dp[r] = Math.max(dp[r], w);
        }
        System.out.println(Arrays.stream(dp).max().orElse(0));
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] a = new int[n];
        int[][] b = new int[m][2];
//        int[] b = new int[m];
//        int[] c = new int[m];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        for (int i = 0; i < m; i++) {
            b[i][0] = sc.nextInt();
            b[i][1] = sc.nextInt();
//            b[i] = sc.nextInt();
//            c[i] = sc.nextInt();
//            list.add(b[i]);
        }
        Arrays.sort(a);
        Arrays.sort(b, (x,y) -> x[1] == y[1] ? x[0] - y[0] : y[1] - x[1]);
        boolean[] marked = new boolean[n];
        int ans = 0;
        for (int i = 0; i < m; i++) {
            int idx = bis(a, b[i][0]);
            if (idx < a.length ) {
               while (idx < a.length && marked[idx]) {
                   idx += 1;
               }
               if (idx < a.length) {
                   marked[idx] = true;
                   ans += b[i][1];
               }
            }
        }
        System.out.println(ans);
    }

    public static int bis(int[] a, int t) {
        int l = 0, r = a.length;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (a[m] < t) {
                l = m + 1;
            } else {
                r = m;
            }
        }
        return l;
    }


}