/**
 * @program JavaBooks
 * @description: 爬楼梯
 * @author: mf
 * @create: 2020/04/12 21:14
 */

package subject.dp;

/**
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 */
public class T1 {
    public int climbStairs(int n) {
        if (n <= 2) return n;
        int pre2 = 1, pre1 = 2;
        for (int i = 3; i <= n; i++) {
            int cur = pre2 + pre1;
            pre2 = pre1;
            pre1 = cur;
        }
        return pre1;
    }
}
