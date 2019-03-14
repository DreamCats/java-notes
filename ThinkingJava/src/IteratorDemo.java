import java.util.ArrayList;
import java.util.List;

/**
 * @program JavaBooks
 * @description: 迭代器
 * @author: mf
 * @create: 2019/03/14 15:30
 */

public class IteratorDemo {

    public static void main(String[] args){
        // 对于数组

//        int array[] = new int[3];
//        for (int i = 0; i < array.length; i++) {
//            System.out.println(array[i]);
//        }

        // 对于ArrayList
//        List<String> list = new ArrayList<String>();
//        for (int i = 0; i < list.size(); i++) {
////            System.out.println(list.get(i));
//            String string = list.get(i);
//        }

        // 对于它们俩，访问代码和集合本身是紧密耦合的，无法将访问逻辑从集合类和客户端代码中分离出来
        // 不同的集合会对应不同的遍历方法，客户端代码无法复用。在实际应用中如何将上面两个集合整合是相当麻烦的
        // 所以才有Iterator，它总是用同一种逻辑来遍历集合。
        // 使得客户端自身不需要来维护集合的内部结构，所有的内部状态都由Iterator来维护。


        // 使用迭代器遍历集合
        List<String> list = new ArrayList<String>();
        list.add("张三1");
        list.add("张三2");
        list.add("张三3");
        list.add("张三4");



    }
}
