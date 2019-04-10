import java.util.ArrayList;
import java.util.Iterator;

/**
 * @program JavaBooks
 * @description: 迭代器
 * @author: mf
 * @create: 2019/04/10 09:51
 */

public class IteratorDemo {


    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);


        // 使用迭代器遍历
        System.out.println("--------使用while的iterator-------");
        Iterator<Integer> iterator = arrayList.iterator();
        //从最开始的位置判断"下一个"位置是否有数据
        //如果有就通过next取出来，并且把指针向下移动
        //直到"下一个"位置没有数据
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        // for
        System.out.println("--------使用for的iterator-------");
        for (Iterator<Integer> iterator1 = arrayList.iterator(); iterator1.hasNext();) {
            System.out.println(iterator1.next());
        }

        Iterator<Integer> iterator2 = arrayList.iterator();
        System.out.println("test");
        for (;iterator2.hasNext();) {
            System.out.println(iterator2.next());
        }

        System.out.println("增强for");
        // 增强for
        for (Integer integer : arrayList) {
            System.out.println(integer);
        }
    }
}
