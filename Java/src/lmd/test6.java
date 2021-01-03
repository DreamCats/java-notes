/**
 * @program JavaBooks
 * @description: map
 * @author: mf
 * @create: 2020/08/04 22:11
 */

package lmd;

import java.util.HashMap;
import java.util.Map;

public class test6 {
    public static void main(String[] args) {
        // 假设有一个数字到对应英文单词的Map，请输出Map中的所有映射关系．
        // java7
        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
        map.forEach((k, v) -> System.out.println(k + "=" + v));
    }
}
