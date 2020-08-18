import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        Arrays.sort(a);
        System.out.println(dfs(a, 0, 0, 1));
    }

    public static int dfs(int[] a, int start, long sum, long multi) {
        int cnt = 0;
        for (int i = start; i < a.length; i++) {
            sum += a[i];
            multi *= a[i];
            if (sum > multi)
                cnt += 1 + dfs(a, i + 1, sum, multi);
            else if (a[i] == 1)
                cnt += dfs(a, i + 1, sum, multi);
            else
                break;
            sum -= a[i];
            multi /= a[i];
            while (i < a.length - 1 && a[i] == a[i + 1]) i++;
        }
        return cnt;
    }
}