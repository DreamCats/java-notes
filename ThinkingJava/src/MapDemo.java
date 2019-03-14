import java.util.HashMap;
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

    }
}
