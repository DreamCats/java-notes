package books;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @program JavaBooks
 * @description: 把数组排成最小的数
 * @author: mf
 * @create: 2019/09/28 10:18
 */

/*
输入一个正整数数组，把数组里所有数字拼接起来排成一个数，
打印能拼接出的所有数字中最小的一个。例如输入数组{3,32,321}，则
打这3个数字能排成的最小数字321323
 */
public class T45 {
    public static void main(String[] args) {
        int[] arr = {3, 32, 321};
        String res = printMinNumber(arr);
        System.out.println(res);
    }

    private static String printMinNumber(int[] arr) {
        if (arr == null || arr.length == 0) return "";
        int len = arr.length;
        String[] str = new String[len];
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < len; i++) {
            str[i] = String.valueOf(arr[i]);
        }
        Arrays.sort(str, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String c1 = o1 + o2;
                String c2 = o2 + o1;
                return c1.compareTo(c2);
            }
        });
        for (int i = 0; i < len; i++) {
            stringBuffer.append(str[i]);
        }
        return stringBuffer.toString();
    }
}
