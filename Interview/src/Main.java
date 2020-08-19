import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x1 = sc.nextInt();
        int y1 = sc.nextInt();
        int x2 = sc.nextInt();
        int y2 = sc.nextInt();
        int sub = (x2 * 60 + y2) - (x1 * 60 + y1);
        System.out.print(sub / 60 + " " + sub % 60);
    }
}