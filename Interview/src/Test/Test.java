/**
 * @program JavaBooks
 * @description: 测试
 * @author: mf
 * @create: 2020/05/24 00:04
 */

package Test;

import java.util.Arrays;

public class Test {

    public static void main(String[] args) {
        String s = ", , , , a, eaefa";
        String[] s1 = s.split(" ");
        if (s1.length == 1 && s1[0] == "") System.out.println("hhh");
        System.out.println(s1.length);
        System.out.println(Arrays.toString(s1));
    }
}
