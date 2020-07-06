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
        int[] a = new int[] {1,2,3};
        int b = Arrays.stream(a).max().orElse(0);
        System.out.println(b);
    }

}
