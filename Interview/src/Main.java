import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int len = 1;
        int res = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) != s.charAt(i - 1)) {
                len++;
                res = Math.max(len, res);
            } else {
                len = 1;
            }
        }
        System.out.println(res);
    }
}
