package web; /**
 * @program LeetNiu
 * @description: 数组中出现次数超过一半的数字
 * @author: mf
 * @create: 2020/01/14 00:19
 */

import java.util.HashMap;
import java.util.Map;

/**
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 * 例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。
 * 由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0。
 */
public class T28 {
    public int MoreThanHalfNum_Solution(int [] array) {
        // 哈希
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            if (map.containsKey(array[i])) {
                map.put(array[i], map.get(array[i]) + 1);
            } else {
                map.put(array[i], 1);
            }
        }
        int length = array.length >> 1;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > length) {
                return entry.getKey();
            }
        }
        return 0;
    }
}
