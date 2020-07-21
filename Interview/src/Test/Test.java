/**
 * @program JavaBooks
 * @description: 测试
 * @author: mf
 * @create: 2020/05/24 00:04
 */

package Test;

import java.util.*;

public class Test {

    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = n * 2;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < m; i++)
            list.add(sc.nextInt());
        int idx = 0;
        int sum = 0;
        while (idx < list.size()){
            int lastIdx = list.lastIndexOf(list.get(i));
            sum += (lastIdx - idx - 1);
            list.remove(lastIdx);
            idx++;
        }
        System.out.println(sum);
    }

}
