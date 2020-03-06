package books;

/**
 * @program JavaBooks
 * @description: 连续子数组的最大和
 * @author: mf
 * @create: 2019/09/25 09:33
 */

/*
输入一个整型数组，数组里有正数也有负数。数组中的
一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
要求时间复杂度为o（n）。
 */
public class T42 {
    public static void main(String[] args) {
        int[] arr = {1, -2, 3, 10, -4, 7, 2, -5};
        int max = findGreatestSumOfArr(arr);
        int max2 = findGreatestSumOfarr2(arr);
        System.out.println(max);
        System.out.println(max2);
    }
    // 分析数组规律
    private static int findGreatestSumOfArr(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int nCurNum = 0;
        int greatestSum = 0;
        for (int i = 0; i < arr.length; i++) {
            if (nCurNum <= 0) {
                nCurNum = arr[i];
            } else {
                nCurNum += arr[i];
            }
            if (nCurNum > greatestSum) {
                greatestSum = nCurNum;
            }
        }
        return greatestSum;
    }

    // 动态规划 感觉和上面的方法异曲同工罢了。。
    private static int findGreatestSumOfarr2(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int res = arr[0]; // 记录当前所有子数组的和的最大值
        int max = arr[0]; // 记录包含arr[i]的连续子数组的和的最大值
        for (int i = 1; i < arr.length; i++) {
            max = Math.max(max + arr[i], arr[i]);
            res = Math.max(max, res); // 更新最大值 max其实就是nCurNum
        }
        return res;
    }
}
