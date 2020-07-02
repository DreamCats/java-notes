/**
 * @program JavaBooks
 * @description: 测试
 * @author: mf
 * @create: 2020/05/24 00:04
 */

package Test;

import java.util.ArrayList;

public class Test {

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();

        list.add(1);
        list.add(2);
        list.add(2);
        list.add(3);
        list.add(4);

        //
//        for (int i = 0; i < list.size(); i++) {
//            if (list.get(i) == 2) list.remove(list.get(i));
//        }
        for (Integer integer : list) {
            if (integer == 2) list.remove(integer);
        }
        System.out.println(list);
    }


}
