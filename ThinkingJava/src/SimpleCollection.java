import java.util.ArrayList;
import java.util.Collection;

/**
 * @program JavaBooks
 * @description: Collection demo
 * @author: mf
 * @create: 2019/03/13 22:22
 */

public class SimpleCollection {

    public static void main(String[] args) {
        Collection<Integer> c = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            c.add(i);
        }
        for (Integer i : c) {
            System.out.print(i + ", ");
        }
    }
}
