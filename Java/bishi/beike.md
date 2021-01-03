
第一题，忘了
```java
class Main1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        String s = sc.nextLine();
        int l = 0, r = s.length() - 1;
        int cnt = 0;
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) {
                cnt++;
            }
            l++;
            r--;
        }
        System.out.println(cnt);
    }
}
```

第二题，忘了
```java
class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            System.out.println(solve(n, m));
        }
    }

    public static int solve(int n, int m) {
        if (m == 1)
            return leastDivisor(n);
        if (n == 1)
            return leastDivisor(m);
        return Math.min(leastDivisor(m), leastDivisor(n));
    }

    public static int leastDivisor(int n) {
        if (n % 2 == 0)
            return 2;
        for (int i = 3; i <= (int) Math.floor(n / 2); i++) {
            if (n % i == 0)
                return i;
        }
        return n;
    }
}
```

第三题，忘了
```java
class Main3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        int maxV = 0, minL = n;
        int v = 0, j = 0;
        int[] count = new int[32];
        for (int i = 0; i < n; i++) {
            v |= a[i];
            add(count, a[i]);
            if (v >= maxV) {
                while (j <= i && canDel(count, a[j])) {
                    dec(count, a[j]);
                    j += 1;
                }
                if (v == maxV)
                    minL = Math.min(minL, i - j + 1);
                else
                    minL = i - j + 1;
                maxV = v;
            }
        }
        System.out.println(minL);
    }

    public static void add(int[] count, int x) {
        int i = 0;
        while (x > 0) {
            count[i] += (x & 1);
            i += 1;
            x = x >> 1;
        }
    }

    public static void dec(int[] count, int x) {
        int i = 0;
        while (x > 0) {
            count[i] -= (x & 1);
            i += 1;
            x = x >> 1;
        }
    }

    public static boolean canDel(int[] count, int x) {
        int i = 0;
        while (x > 0) {
            if (count[i] <= (x & 1)) {
                return false;
            }
            i += 1;
            x = x >> 1;
        }
        return true;
    }
}

```

第四题，忘了
```java
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
//        long mod = 1000000007;
        int[][] c = new int[1001][1001];
        for (int i = 1; i < 1001; i++) {
            c[i][0] = 1;
            c[i][i] = 1;
            for (int j = 1; j < i; j++) {
                c[i][j] = ((c[i - 1][j - 1] + c[i - 1][j]) % 1000000007);
            }
        }
        int[][] paths = new int[m][3];
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int a = sc.nextInt();
            int b = sc.nextInt();
            paths[i][0] = u;
            paths[i][1] = v;
            paths[i][2] = c[a][b];
        }
        Arrays.sort(paths, (a, b) -> b[2] - a[2]);
        int ans = -1;
        int i = 0;
        while (i < m) {
            HashMap<Integer, Integer> hset = new HashMap<>();
            int tmp = 0;
            Integer nex = -1;
            for (int j = i; j < m; j++) {
                if (union(hset, paths[j][0], paths[j][1]))
                    tmp += paths[j][2];
                else if (nex == -1)
                    nex = j;
                if (hset.size() == n)
                    break;
            }
            if (hset.size() == n)
                ans = Math.max(ans, tmp);
            else
                break;
            if (nex == -1)
                break;
            else
                i = nex;
        }
        System.out.println(ans);

//        for (int i = 0; i < m; i++) {
//            HashMap<Integer, Integer> hset = new HashMap<>();
//            int tmp = 0;
//            for (int j = i; j < m; j++) {
//                if (union(hset, paths[j][0], paths[j][1]))
//                    tmp += paths[j][2];
//                if (hset.size() == n)
//                    break;
//            }
//            if (hset.size() == n)
//                ans = Math.max(ans, tmp);
//        }
//        System.out.println(ans);
    }

    public static int find (HashMap<Integer, Integer> hset, int x) {
        if (!hset.containsKey(x))
            hset.put(x, x);
        else if(hset.get(x) != x)
            hset.put(x, find(hset, hset.get(x)));
        return hset.get(x);
    }

    public static boolean union(HashMap<Integer,Integer> hset, int x, int y) {
        int fx = find(hset, x);
        int fy = find(hset, y);
        if (fx == fy)
            return false;
        hset.put(fy, hset.get(fx));
        return true;
    }
}
```