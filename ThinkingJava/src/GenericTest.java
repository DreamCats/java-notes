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
// 定义了一个List类型的集合，先向其中加入了两个字符串类型的值，随后加入一个Integer类型的值。这是完全允许的，因为此时list默认的类型为Object类型。
// 由于忘记了之前在list中也加入了Integer类型的值或其他编码原因，很容易出现类似于//1中的错误。

// 1。 当我们将一个对象放入集合中，集合不会记住此对象的类型，当再次从集合中取出此对象时，改对象的编译类型变成了Object类型，但其运行时类型任然为其本身类型。
// 2。 因此，//1处取出集合元素时需要人为的强制类型转化到具体的目标类型，且很容易出