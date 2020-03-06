package web; /**
 * @program LeetNiu
 * @description: 矩形覆盖
 * @author: mf
 * @create: 2020/01/09 13:36
 */

/**
 * 我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
 */
public class T10 {
    public int RectCover(int target) {
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
