/**
 * @program JavaBooks
 * @description: 反转字符串
 * @author: mf
 * @create: 2020/04/09 17:45
 */

package subject.dpointer;

/**
 * 输入：["h","e","l","l","o"]
 * 输出：["o","l","l","e","h"]
 */
public class T9 {
    public void reverseString(char[] s) {
        int l = 0, r = s.length - 1;
        while (l < r) {
            swap(s, l++, r--);
        }
    }

    public void swap(char[] s, int i, int j) {
        char temp = s[i];
        s[i] = s[j];
        s[j] = temp;
    }
}
