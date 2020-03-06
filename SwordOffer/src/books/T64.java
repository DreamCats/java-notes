package books;

/**
 * @program JavaBooks
 * @description: 求1+2+...+n
 * @author: mf
 * @create: 2019/10/09 16:29
 */

/*
求1+2+3+...+n，要求不能使用乘除法、
for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
 */
public class T64 {
    public static void main(String[] args) {
        System.out.println(sum(10));
    }

    private static int sum(int n) {
        int res = n;
        boolean t = ((res != 0) && ((res += sum( n - 1)) != 0));
        return res;
    }

}
