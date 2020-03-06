package web; /**
 * @program LeetNiu
 * @description: 二进制1的个数
 * @author: mf
 * @create: 2020/01/09 13:41
 */

/**
 * 输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
 */
public class T11 {
    public int NumberOf1(int n) {
        int count = 0;
        while ( n!= 0) {
            count++;
            n = (n - 1) & n;
        }
        return count;
    }
}
