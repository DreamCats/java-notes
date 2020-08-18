
```java
class Main1 {
    static int min = Integer.MAX_VALUE;
    static int x = 0;
    static boolean[] marked;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int p = sc.nextInt();
        int[][] a = new int[p][3];
        HashMap<Integer, int[]> map = new HashMap<>();
        for (int i = 0; i < p; i++) {
            a[i][0] = sc.nextInt();
            a[i][1] = sc.nextInt();
            a[i][2] = sc.nextInt();
            map.put(a[i][0], a[i]);
        }
        x = sc.nextInt();
        marked = new boolean[p];
        dfs(a, 0, 0);
        System.out.println(min);
    }

    public static void dfs(int[][] a, int start, int sum) {
        if (start == x) {
            min = Math.min(min, sum);
        }
        for (int i = 0; i < a.length; i++) {
            if (marked[i])
                continue;
            marked[i] = true;
            if (a[i][0] == start) {
                dfs(a, a[i][1], sum + a[i][2]);
            }
            marked[i] = false;
        }
    }

}

```

```java
class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int x = sc.nextInt();
        int[] v = new int[10001];
        int[] w = new int[10001];
        for (int i = 0; i < n; i++) {
            v[i] = sc.nextInt();
            w[i] = sc.nextInt();
        }
        System.out.println(maxValue(n, x, v, w));
    }

    public static int maxValue(int n, int x, int[] v, int[] w) {
        int[] dp = new int[10001];
        for (int i = 0; i < n; i++) {
            for (int j = x; j >= w[i]; j--) {
                dp[j] = Math.max(dp[j - w[i]] + v[i], dp[j]);
            }
        }
        return dp[x];
    }
}
```

```java
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int k = sc.nextInt();
        StringBuilder sb = new StringBuilder();
//        int cnt = 0;
//        for (int i = 0; i < s.length(); i++) {
//            if (s.charAt(i) != '0') {
//                sb.append(s.charAt(i));
//            } else {
//                cnt++;
//            }
//        }
//        char[] cs = sb.toString().toCharArray();
//        Arrays.sort(cs);
//        if (cnt > k) {
//            System.out.println(0);
//            return;
//        }
//        if (cnt == 0) {
//            System.out.println(sc);
//            return;
//        }
//        System.out.println("1223308");
    }
}



```