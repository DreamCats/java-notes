import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(10, 20);
        System.out.println(map.get(new Integer(10)));
        System.out.println(map.get(new Long(10)));
        System.out.println(new Long(10).hashCode() == new Integer(10).hashCode());

    }
}