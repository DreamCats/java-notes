/**
 * @program JavaBooks
 * @description: 牛客
 * @author: mf
 * @create: 2020/07/24 22:24
 */

package Test;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<String> ret = new ArrayList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s  = sc.nextLine();
        String ss = convertNumber(s);

        char[] chars = ss.toCharArray();
        dfs(chars, new boolean[chars.length], new StringBuilder());
        int cnt = 0;
        for (String s1 : ret) {
            int num = Integer.parseInt(s1);
            if (num % 7 == 0)
                cnt++;
        }
        System.out.println(cnt);
    }

    public static void dfs(char[] chars, boolean[] marked, StringBuilder s){
        if (s.length() == chars.length){
            ret.add(s.toString());
            return;
        }
        for (int i = 0; i < marked.length; i++) {
            if (marked[i])
                continue;

            marked[i] = true;
            s.append(chars[i]);
            dfs(chars, marked, s);
            marked[i] = false;
            s.deleteCharAt(s.length() - 1);
        }
    }

    public static String convertNumber(String s){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9')
                sb.append(s.charAt(i));
        }
        return sb.toString();
    }
}
