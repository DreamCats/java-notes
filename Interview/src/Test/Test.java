/**
 * @program JavaBooks
 * @description: 测试
 * @author: mf
 * @create: 2020/05/24 00:04
 */

package Test;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        int[] a = new int[]{1,2, 3};

        int[] b = new int[3];

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(1);

        int[] c = list.stream().mapToInt(Integer::valueOf).toArray();
        for (int i : c) {
            System.out.println(i);
        }

    }



}
