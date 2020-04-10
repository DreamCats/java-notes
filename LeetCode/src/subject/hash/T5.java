/**
 * @program JavaBooks
 * @description: 单词规律
 * @author: mf
 * @create: 2020/04/10 22:12
 */

package subject.hash;

import java.util.HashMap;

/**
 * 输入: pattern = "abba", str = "dog cat cat dog"
 * 输出: true
 * 输入:pattern = "abba", str = "dog cat cat fish"
 * 输出: false
 * 输入: pattern = "aaaa", str = "dog cat cat dog"
 * 输出: false
 * 输入: pattern = "abba", str = "dog dog dog dog"
 * 输出: false
 */
public class T5 {
    public boolean wordPattern(String pattern, String str) {
        String[] array1 = str.split(" ");
        if (array1.length != pattern.length()) return false;
        String[] array2 = pattern.split("");
        return wordPatternHelper(array1, array2) && wordPatternHelper(array2, array1);
    }

    private boolean wordPatternHelper(String[] array1, String[] array2) {
        HashMap<String, String> map = new HashMap<>();
        for (int i = 0; i < array1.length; i++) {
            String key = array1[i];
            if (map.containsKey(key)) {
                if (!map.get(key).equals(array1[i])) {
                    return false;
                }
            } else {
                map.put(key, array2[i]);
            }
        }
        return true;
    }
}
