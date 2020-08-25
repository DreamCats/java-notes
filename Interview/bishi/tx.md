```java
class Main1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        for (int i = 0; i < n; i++) {
            int a = sc.nextInt();
            if (i != k - 1) {
                System.out.print(a);
            }
            if (i != n - 1)
                System.out.print(" ");

        }
    }
}
```

```java
class Main2 {
    static TreeSet<String> ret = new TreeSet<>();
    static int k = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        char[] cs = s.toCharArray();
        Arrays.sort(cs);
        k = sc.nextInt();
        boolean[] marked = new boolean[s.length()];
        dfs(0, new StringBuilder(), cs, marked);
        for (String s1 : ret) {
            System.out.println(s1);
            return;
        }
    }

    public static void dfs(int start, StringBuilder sb, char[] cs, boolean[] marked) {
        if (sb.length() == k){
            ret.add(sb.toString());
            return;
        }
        for (int i = start; i < cs.length; i++) {
            if (i != 0 && cs[i] == cs[i - 1] && !marked[i - 1])
                continue;
            marked[i] = true;
            sb.append(cs[i]);
            dfs(start+1, sb, cs, marked);
            sb.deleteCharAt(sb.length() - 1);
            marked[i] = false;
        }
    }


}
```

```java
class Main3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            long n = sc.nextLong();
            if (n == 1)
                System.out.println(1);
            else
                System.out.println(maxVal(n));
        }
    }

    public static long maxVal(long n) {
        long l = 0, r = n;
        long max = 0;
        while (l < r) {
            max = Math.max(max, (sum(l) + sum(r)));
            l++;
            r--;
        }
        return max;
    }

    public static long sum(long n) {
        long sum = 0;
        while (n != 0) {
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }
}
```

```java
class Main4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        System.out.println(minCount(a));
    }
    public static int minCount(int[] a)  {
        if (a.length == 1)
            return a[0];
        if (a.length == 2)
            return Math.max(a[0], a[1]);
        boolean[] marked = new boolean[a.length];
        int stopCnt = 0;
        int cnt = 1;
        while (stopCnt < marked.length - 1) {
            for (int i = 0; i < a.length; i++) {
                if (marked[i])
                    continue;
                if (cnt == 1 && a[i] == cnt) {
                    marked[i] = true;
                    stopCnt++;
                }else if (i < a.length - 1 && a[i] == cnt && a[i + 1] == cnt) {
                    int j = i;
                    while (j < a.length - 1 && a[j] == a[j + 1] && a[j] == cnt) {
                        marked[i] = true;
                        marked[i + 1] = true;
                        stopCnt += 2;
                        j += 2;
                    }

                }else if (a[i] == cnt) {
                    marked[i] = true;
                    stopCnt++;
                }
                if (stopCnt > a.length - 1)
                    return cnt;
            }
            cnt++;
        }
        return cnt;
    }
}
```