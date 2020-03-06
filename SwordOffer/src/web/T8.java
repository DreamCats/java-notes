package web; /**
 * @program LeetNiu
 * @description: 跳台阶
 * @author: mf
 * @create: 2020/01/09 13:32
 */

/**
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。
 * 求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
 * 思路：
 * 递归，自底向上
 */
public class T8 {
    public int JumpFloor(int target) {
        // 条件
        if (target <= 2) return target;
        int pre2 = 1, pre1 = 2;
        int sum = 0;
        for (int i = 3; i <= target; i++) {
            sum = pre2 + pre1;
            pre2 = pre1;
            pre1 = sum;
        }
        return sum;
    }
}
