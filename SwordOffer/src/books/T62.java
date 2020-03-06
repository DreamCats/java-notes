package books;

import java.util.ArrayList;

/**
 * @program JavaBooks
 * @description: 圆圈中最后剩下的数字
 * @author: mf
 * @create: 2019/10/09 16:05
 */

/*
0，1，...，n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里
删除第m个数字。求出这个圆圈里剩下的最后一个数字。
 */
public class T62 {
    public static void main(String[] args) {
        System.out.println(lastRemaining(5, 3));
    }

    private static int lastRemaining(int n, int m) {
        if (n == 0 || m == 0) return -1;
        ArrayList<Integer> data = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            data.add(i);
        }
        int index = -1;
        while (data.size() > 1) {
            index = (index + m) % data.size();
            data.remove(index);
            index--;
        }
        return data.get(0);
    }
}
