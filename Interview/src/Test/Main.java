/**
 * @program JavaBooks
 * @description: 牛客
 * @author: mf
 * @create: 2020/07/24 22:24
 */

package Test;


import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        HashSet<Character> set = new HashSet<Character>();
        for (int i = 0; i < s.length(); i++) {
            set.add(s.charAt(i));
        }
        if (set.size() == 2)
            System.out.println(2);
        else if (set.size() == 1)
            System.out.println(1);
        else
            System.out.println(0);
    }
}