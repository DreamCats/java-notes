/**
 * @program JavaBooks
 * @description: 牛客
 * @author: mf
 * @create: 2020/07/24 22:24
 */

package Test;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        if (isUpper(s) && secJudge(s) && thirdJudge(s))
            System.out.println("Likes");
        else
            System.out.println("Dislikes");
    }

    public static boolean isUpper(String s){
        return s.matches("[A-Z]+");
    }

    public static boolean secJudge(String s){
        return !s.matches(".*(.)\\1.*");
    }

    public static boolean thirdJudge(String s){
        return !s.matches(".*(.).*(.)\\1.*\\2.*");
    }
}
