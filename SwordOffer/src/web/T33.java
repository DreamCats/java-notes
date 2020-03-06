package web; /**
 * @program LeetNiu
 * @description: 丑数
 * @author: mf
 * @create: 2020/01/14 14:03
 */


/**
 * 把只包含质因子2、3和5的数称作丑数（Ugly Number）。例如6、8都是丑数，但14不是，因为它包含质因子7。
 * 习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。
 */
public class T33 {
    public int GetUglyNumber_Solution(int index) {
        if (index <= 0) return 0;
        int[] ans = new int[index];
        int count = 0;
        int i2 = 0, i3 = 0, i5 = 0;
        ans[0] = 1;
        int temp = 0;
        while (count < index - 1) {
            temp = min(ans[i2] * 2, min(ans[i3] * 3, ans[i5] * 5));
            if (temp == ans[i2] * 2) i2++;
            if (temp == ans[i3] * 3) i3++;
            if (temp == ans[i5] * 5) i5++;
            ans[++count] = temp;
        }
        return ans[index - 1];
    }

    /**
     * 求最小值
     * @param a
     * @param b
     * @return
     */
    private int min(int a, int b) {
        return (a > b) ? b : a;
    }
}
