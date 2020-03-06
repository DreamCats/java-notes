package web; /**
 * @program LeetNiu
 * @description: 数组中只出现一次的数字
 * @author: mf
 * @create: 2020/01/15 10:27
 */

import java.util.HashMap;
import java.util.Map;

/**
 * 一个整型数组里除了两个数字之外，其他的数字都出现了两次。请写程序找出这两个只出现一次的数字。
 */
public class T40 {
    public void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
        if (array == null || array.length <= 1) {
            num1[0] = num2[0] = 0;
            return;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            if (map.containsKey(array[i])) {
                map.put(array[i], 2);
            } else {
                map.put(array[i], 1);
            }
        }
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                sb.append(entry.getKey());
                sb.append(",");
            }
        }
        String[] strings = sb.toString().split(",");
        num1[0] = Integer.valueOf(strings[0]);
        num2[0] = Integer.valueOf(strings[1]);
    }
}
