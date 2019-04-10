import java.util.HashSet;
import java.util.Iterator;

/**
 * @program JavaBooks
 * @description: HashSet
 * @author: mf
 * @create: 2019/04/10 12:59
 */

public class HashSetDemo {
    // Set中的元素，不能重复
    // 没有顺序
    public static void main(String[] args) {

        HashSet<String> names = new HashSet<String>();

        names.add("gareen");

        System.out.println(names);

        //第二次插入同样的数据，是插不进去的，容器中只会保留一个
        names.add("gareen");
        System.out.println(names);

        names.add("1");
        names.add("2");
        names.add("3");
        System.out.println(names);


        // 遍历
        Iterator<String> it = names.iterator();
//        while (it.hasNext()) {
//            System.out.println(it.next());
//        }

//        for (;it.hasNext();) {
//            System.out.println(it.next());
//        }

        for (String name : names) {
            System.out.println(name);
        }
    }
}
