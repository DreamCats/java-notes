import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(a[i]);
            Collections.reverse(list);
        }
        StringBuilder sb = new StringBuilder();
        list.forEach(o -> sb.append(o + " "));
        System.out.println(sb.substring(0, sb.length() - 1));

    }
}
