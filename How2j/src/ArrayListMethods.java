import java.util.ArrayList;

/**
 * @program JavaBooks
 * @description: ArrayList常用方法
 * @author: mf
 * @create: 2019/04/09 15:17
 */

public class ArrayListMethods {
    // add 添加
    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(2);

        // 判断是否存在
        System.out.println(arrayList.contains(1));

        // 获取指定位置的对象
        System.out.println(arrayList.get(0));

        // 获取对象所处的位置
        System.out.println(arrayList.indexOf(1));

        // 删除
        arrayList.remove(0);

        arrayList.add(1);
        // 替换

        arrayList.set(0, 5);
        System.out.println(arrayList.get(0));

        // 获取大小
        System.out.println(arrayList.size());

        // 转换为数组

        Object[] intArray = arrayList.toArray();

        System.out.println("数组：");
        for (Object o : intArray) {
            System.out.println(o);
        }

        // 把另一个容器加进来


    }
}
