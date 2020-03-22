/**
 * @program JavaBooks
 * @description: 实现strStr()
 * @author: mf
 * @create: 2020/03/22 14:56
 */

package subject.dpointer;

public class T3 {
    public int strStr(String haystack, String needle) {
        int l = haystack.length(), n = needle.length();
        for (int i = 0; i < l - n + 1; i++) {
            if (haystack.substring(i, i + n).equals(needle)) {
                return i;
            }
        }
        return -1;
    }
}
