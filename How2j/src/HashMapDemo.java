import java.util.HashMap;

/**
 * @program JavaBooks
 * @description: HashMap
 * @author: mf
 * @create: 2019/04/10 12:55
 */

public class HashMapDemo {
    public static void main(String[] args) {
        HashMap<String,String> dictionary = new HashMap<>();
        dictionary.put("adc", "物理英雄");
        dictionary.put("apc", "魔法英雄");
        dictionary.put("t", "坦克");
        dictionary.put("t", "Mai");
        System.out.println(dictionary.get("t"));
    }
}
