/**
 * @program JavaBooks
 * @description: 假设有一个字符串列表，需要删除其中所有长度大于3的字符串。
 * @author: mf
 * @create: 2020/08/04 21:28
 */

package lmd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Test4 {
    public static void main(String[] args) {
        // 如果需要在迭代过程冲对容器进行删除操作必须使用迭代器
        ArrayList<String> list = new ArrayList<>(Arrays.asList("I", "love", "you", "too"));
        Iterator<String> it = list.iterator();
        while (it.hasNext()){
            if (it.next().length() > 3)
                it.remove();
        }

        // java8
        list.removeIf(s -> s.length() > 3);
        System.out.println(list.toString());
    }
}
