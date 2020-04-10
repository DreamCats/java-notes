/**
 * @program JavaBooks
 * @description: 同构字符串
 * @author: mf
 * @create: 2020/04/10 19:59
 */

package subject.hash;

import java.util.HashMap;

/**
 * 输入: s = "egg", t = "add"
 * 输出: true
 * 输入: s = "foo", t = "bar"
 * 输出: false
 * 输入: s = "paper", t = "title"
 * 输出: true
 */
public class T0 {
    public boolean isIsomorphic(String s, String t) {
        return isomerphic(s, t) && isomerphic(t, s);
    }

    private boolean isomerphic(String s, String t) {
        int n = s.length();
        HashMap<Character, Character> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            Character c1 = s.charAt(i);
            Character c2 = t.charAt(i);
            if (map.containsKey(c1)) {
                if (map.get(c1) != c2) {
                    return false;
                }
            } else {
                map.put(c1, c2);
            }
        }
        return true;
    }
}
