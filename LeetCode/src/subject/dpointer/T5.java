/**
 * @program JavaBooks
 * @description: 验证回文串
 * @author: mf
 * @create: 2020/04/09 17:13
 */

package subject.dpointer;

/**
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 * 输入: "race a car"
 * 输出: false
 */
public class T5 {
    public boolean isPalindrome(String s) {
        if (s.equals("")) return true;
        s.toLowerCase();
        char[] chars = s.toCharArray();
        int l = 0, r = chars.length - 1;
        while (l <= r) {
            if (chars[l] == chars[r]) {
                l++;
                r--;
            }else if (!((chars[l] >= 'a' && chars[l] <= 'z') || (chars[l] >= '0' && chars[l] >= '9'))) {
                l++;
            } else if (!((chars[r] >= 'a' && chars[r] <= 'z') || (chars[r] >= '0' && chars[r] >= '9'))) {
                r--;
            } else {
                return false;
            }
        }
        return true;
    }
}
