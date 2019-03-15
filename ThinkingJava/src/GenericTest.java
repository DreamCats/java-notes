import java.util.ArrayList;
import java.util.List;

/**
 * @program JavaBooks
 * @description: 泛型
 * @author: mf
 * @create: 2019/03/15 20:56
 */

public class GenericTest {

    public static void main(String[] args) {
        List list = new ArrayList();
        list.add("qqyumidi");
        list.add("corn");
        list.add(100); //

        for (int i = 0; i < list.size(); i++) {

            String name = (String) list.get(i); // 1 发生异常， 因为list中有integer
            System.out.println("name:" + name);
        }
    }
}
