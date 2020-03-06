package books;

/**
 * @program JavaBooks
 * @description: 股票的最大利润
 * @author: mf
 * @create: 2019/10/09 16:18
 */

/*
假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票
一次可能获得的最大利润是多少？例如，一只股票在某些时间节点的价格为
{9, 11, 8, 5, 7, 12, 16, 14}。如果我们能在价格为5的时候买入并
在价格为16时卖出，则能收获最大的利润是11。
 */
public class T63 {
    public static void main(String[] args) {
        int[] arr = {9, 11, 8, 5, 7, 12, 16, 14};
        System.out.println(getMaxDiff(arr));
    }

    private static int getMaxDiff(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int min = arr[0];
        int maxDiff = arr[1] - min;
        for (int i = 2; i < arr.length; i++) {
            min = arr[i - 1] < min ? arr[i - 1] : min;
            int currentDiff = arr[i] - min;
            maxDiff = currentDiff > maxDiff ? currentDiff : maxDiff;
        }
        return maxDiff;
    }
}
