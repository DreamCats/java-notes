/**
 * @program JavaBooks
 * @description: 牛客
 * @author: mf
 * @create: 2020/07/24 22:24
 */

package Test;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
        String s = "   hello world!  ";
        String[] ss = s.trim().split("\\s+");
        System.out.println(Arrays.toString(ss));
        StringBuffer sb = new StringBuffer();
        for (int i = ss.length - 1; i > 0; i--) {
            sb.append(ss[i]).append(" ");
        }
        sb.append(ss[0]);
//        return sb.toString();
    }
}
