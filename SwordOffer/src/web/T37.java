package web; /**
 * @program LeetNiu
 * @description: 数字在排序数组中出现的次数
 * @author: mf
 * @create: 2020/01/15 09:57
 */

/**
 * 统计一个数字在排序数组中出现的次数。
 */
public class T37 {
    public int GetNumberOfK(int [] array , int k) {
        int count = 0;
        for (int i : array) {
            if (i == k) count++;
        }
        return count;
    }
}
