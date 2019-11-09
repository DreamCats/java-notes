/**
 * @program JavaBooks
 * @description: 125. 验证回文串
 * @author: mf
 * @create: 2019/11/09 19:27
 */

/*
题目：https://leetcode-cn.com/problems/valid-palindrome/
难度：easy
 */
public class IsPalindromeStr {
    public static void main(String[] args) {
        String s = "A man, a plan, a canal: Panama";
        String s1 = "ab2a";
        System.out.println(isPalindrome(s1));
    }
    public static boolean isPalindrome(String s) {
        if (s.equals("")) return true;
        s = s.toLowerCase();
        char[] sChar = s.toCharArray();
        int l = 0, r = sChar.length - 1;
        while (l <= r) {
            if (sChar[l] == sChar[r]) {
                l++;
                r--;
            } else if (!((sChar[l] >= 'a' && sChar[l] <= 'z') || (sChar[l] >= '0' && sChar[l] <= '9'))) {
                l++;
            } else if (!((sChar[r] >= 'a' && sChar[r] <= 'z') || (sChar[r] >= '0' && sChar[r] <= '9'))) {
                r--;
            } else {
                return false;
            }
        }
        return true;
    }
}
