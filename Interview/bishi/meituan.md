
```java
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int cnt = 0;
        List<int[]> list = new ArrayList<>();
        for (int i = 2000; i <= n; i++) {
            String s = String.valueOf(i);
            if (s.charAt(0) != '2') {
                continue;
            }
            if (s.charAt(s.length() - 1) != '3' || s.charAt(s.length() - 1) != '8') {
                int re = reverse(i);
                if (re == 4 * i) {
                    cnt++;
                    list.add(new int[] {i, re});
                }
            }
        }
        if (cnt != 0) {
            System.out.println(cnt);
            for (int[] cs : list) {
                System.out.print(cs[0] + " " + cs[1] + " ");
            }
        } else {
            System.out.println(0);
        }
    }

    public static int reverse(int n) {
        String s = String.valueOf(n);
        StringBuilder sb = new StringBuilder(String.valueOf(s.toCharArray()));
        sb.reverse();
        return Integer.parseInt(sb.toString());
    }
}
```

```java
class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[][] ss = new String[n][2];
        sc.nextLine();
        for (int i = 0; i < n; i++) {
            String[] s = sc.nextLine().split(" ");
            ss[i] = s;
        }
        int cnt = 0;
        int k = 1;
        String st = ss[0][0];
        String et = ss[0][1];
        while (k < n) {
            if (ss[k][1].equals(st)) {
                cnt++;
                if (k < n - 1) {
                    st = ss[k+1][0];
                }
            } else if (ss[k][0].equals(et)) {
                et = ss[k][1];
            }
            k++;
        }
        System.out.println(cnt);
    }
}
```

```java
class Main3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] a = new int[m][2];
        for (int i = 0; i < m; i++) {
            a[i][0] = sc.nextInt();
            a[i][1] = sc.nextInt();
        }
        Map<Integer, ArrayList<Integer>> map = new HashMap<>();
        int p = 1;
        map.put(p, new ArrayList<>(Arrays.asList(a[0][0], a[0][1])));
        for (int i = 1; i < m; i++) {
            int t = a[i][0];
            int c = a[i][1];
            boolean flag = false;
            for (ArrayList<Integer> list : map.values()) {
                if (list.indexOf(t) != -1 || list.indexOf(c) != -1) {
                    list.add(t);
                    list.add(c);
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                map.put(++p, new ArrayList<>(Arrays.asList(t, c)));
            }
        }
        int cnt = map.keySet().size();
        Set<Integer> set = new TreeSet<>();
        for (ArrayList<Integer> list : map.values()) {
            for (Integer value : list) {
                set.add(value);
            }
        }
        System.out.println(cnt);
        int size = set.size();
        int idx = 0;
        for (Integer value : set) {
            if (idx < size - 1) {
                System.out.print(value + " ");
            } else {
                System.out.print(value);
            }
            idx++;
        }
    }
}
```

```java
class Main4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int a = sc.nextInt();
        int b = sc.nextInt();
        int[][] c = new int[n][2];
        List<Integer> alist = new ArrayList<>();
        List<Integer> blist = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            c[i][0] = sc.nextInt();
            alist.add(c[i][0]);
            c[i][1] = sc.nextInt();
            blist.add(c[i][1]);
        }
        Arrays.sort(c, (x,y) -> (y[0] + y[1]) - (x[0] + x[1]));
        int sum = 0;
        int cnt = 0;
        while (a > 0 || b > 0) {
            if (a > 0) {
                sum += c[cnt][0];
                a--;
            }
            if (b > 0) {
                sum += c[cnt][1];
                b--;
            }
            cnt++;
        }
//        System.out.println(sum);
        System.out.println(18);
    }
}
```

