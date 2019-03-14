import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @program JavaBooks
 * @description: Map
 * @author: mf
 * @create: 2019/03/14 16:04
 */

public class MapDemo {
    public static void main(String[] args){
        // 初始化
        Map<String, String> map = new HashMap<String, String>();


        // 插入元素
        map.put("key1", "value1");

        // 获取元素
        map.get("key1");

        // 移除元素
        map.remove("key1");

        // 清空
        map.clear();

        // 遍历
        map.put("key1", "value1");
        map.put("key2", "value2");

        // 使用keySet遍历
        System.out.println("使用keySet遍历");
        for (String s : map.keySet()) {
            System.out.println(s + ":" + map.get(s));
        }

        // 使用entrySet遍历
        System.out.println("使用entrySet遍历");
        for (Map.Entry<String, String> stringStringEntry : map.entrySet()) {
            System.out.println(stringStringEntry.getKey() + ":" + stringStringEntry.getValue());
        }


        // 使用迭代器遍历
        System.out.println("使用迭代器遍历---keySet");
        Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            System.out.println(key + ":" + map.get(key));
        }

        // 使用迭代器遍历
        System.out.println("使用迭代器遍历---entrySet");

        Iterator<Map.Entry<String, String>> iterator1 = map.entrySet().iterator();

        while (iterator1.hasNext()) {
            Map.Entry<String, String> entry = iterator1.next();
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }

        // 增强for循环使用方便，但性能较差，不适合处理超大量级的数据。
        // 迭代器的遍历速度要比增强for循环快很多，是增强for循环的2倍左右。
        // 使用entrySet遍历的速度要比keySet快很多，是keySet的1.5倍左右。
    }
}
