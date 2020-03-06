package web; /**
 * @program LeetNiu
 * @description: 求1+2+3+...+n
 * @author: mf
 * @create: 2020/01/15 13:42
 */

/**
 * 求1+2+3+...+n，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
 */
public class T47 {
    public int Sum_Solution(int n) {
        int res = n;
        boolean t = ((res != 0) && ((res += Sum_Solution(n - 1)) != 0));
        return res;
    }
}
