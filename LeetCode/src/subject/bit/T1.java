/**
 * @program JavaBooks
 * @description: 汉明距离
 * @author: mf
 * @create: 2020/04/11 18:02
 */

package subject.bit;

public class T1 {
    public int hammingDistance(int x, int y) {
        int xor = x ^ y;
        int distance = 0;
        while (xor != 0) {
            if (xor % 2 == 1) {
                distance += 1;
            }
            xor = xor >> 1;
        }
        return distance;
    }
}
