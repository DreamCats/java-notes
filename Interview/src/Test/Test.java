/**
 * @program JavaBooks
 * @description: 测试
 * @author: mf
 * @create: 2020/05/24 00:04
 */

package Test;

import java.util.Scanner;

public class Test {

    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        s += s;
        int cur = 1, max = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) != s.charAt(i + 1)){
                cur++;
            } else{
                max = Math.max(max, cur);
                cur = 1;
            }
        }
        if (cur == s.length())
            System.out.println(max / 2);
        else
            System.out.println(max);
    }



}
