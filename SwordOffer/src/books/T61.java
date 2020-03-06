package books;

import java.util.Arrays;

/**
 * @program JavaBooks
 * @description: 扑克牌中的顺子
 * @author: mf
 * @create: 2019/10/08 21:53
 */

/*
从扑克牌中随机抽5张牌，判断是不是一个顺子，即这5张牌是不是连续的
2～10为数字本身，A为1，J为11，Q为12，k为13，而大、小王可以
看成任意数字。
 */
public class T61 {
    public static void main(String[] args) {
        int[] arr = {2, 3, 4, 6, 6};
        System.out.println(isContinuous(arr));
    }

    private static boolean isContinuous(int[] arr) {
        int numberOfZero = 0;
        int numOfInterval = 0;
        int length = arr.length;
        if (length == 0) return false;
        Arrays.sort(arr);
        for (int i = 0; i < length - 1; i++) {
            // 大小王
            if (arr[i] == 0) {
                numberOfZero++;
                continue;
            }
            // 对子直接返回
            if (arr[i] == arr[i + 1]) {
                return false;
            }
            numOfInterval += arr[i + 1] - arr[i] - 1;
        }
        if (numberOfZero >= numOfInterval) {
            return true;
        }
        return false;
    }
}
