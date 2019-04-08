/**
 * @program JavaBooks
 * @description: 判断回文数
 * @author: mf
 * @create: 2019/04/08 10:46
 */

public class Palindrome {
    public static void main(String[] args) {
        SolutionPalindrome s = new SolutionPalindrome();
        boolean res = s.isPalindrome(-121);
        System.out.println(res);
    }
}


class SolutionPalindrome {
    public boolean isPalindrome(int x) {
        boolean res = true;
        long a = 0;
        int b = x;
        while (x != 0) {
            int temp = x % 10;
            x /= 10;
            a = a * 10 + temp;
        }
        if (b < 0) a = -a;
        if (a != b) res = false;
        return res;
    }
}