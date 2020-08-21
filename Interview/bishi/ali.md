
这给我难的...

智力题

```java
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            int n = sc.nextInt();
            int[] a = new int[n];
            for (int j = 0; j < n; j++) {
                a[j] = sc.nextInt();
            }
            Arrays.sort(a);
            System.out.println(min(a, n - 1));
        }
    }

    public static long min(int[] a, int end) {
        if (end + 1 == 1)
            return 0;
        if (end + 1 <= 2)
            return a[1];
        if (end + 1 == 3)
            return a[2] + a[1] + a[0];
        if (a[0] + a[end - 1] > 2 * a[1])
            return a[1] * 2 + a[0] + a[end] + min(a, end - 2);
        else
            return a[end] + a[0] + min(a, end - 2);
    }
}

```