import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] a = new int[5];
        for (int i = 0; i < 5; i++) {
            a[i] = sc.nextInt();
        }
        int c = 1;
        boolean flag = false;
        while (!flag){
            int cnt = 0;
            for (int i = 0; i < 5; i++) {
                if (c / a[i] != 0 && c % a[i] == 0) {
                    cnt++;
                    if (cnt == 3) {
                        flag = true;
                        break;
                    }
                }
            }
            c++;
        }
        System.out.println(c-1);
    }
}