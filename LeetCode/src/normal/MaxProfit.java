package normal; /**
 * @program JavaBooks
 * @description: 121.买卖股票的最佳时机
 * @author: mf
 * @create: 2019/11/04 14:22
 */

/**
 * 题目：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/
 * 难度:easy
 * 类型:数组
 */

/*
输入: [7,1,5,3,6,4]
输出: 5
解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
输入: [7,6,4,3,1]
输出: 0
解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 */

public class MaxProfit {
    public static void main(String[] args) {
        int[] arr = {7,1,5,3,6,4};
        int[] arr1 = {7,6,4,3,1};
        System.out.println(maxProfit(arr));
    }
    // 动态规划
    public static int maxProfit(int[] prices) {
        if (prices.length <= 1) return 0;
        // 1. 记录之前买入的最小值
        int min = prices[0], max = 0;
        for (int i = 1; i < prices.length; i++) {
            max = Math.max(max, prices[i] - min);
            min = Math.min(min, prices[i]);
        }
        return max;

    }
    // 两次循环
//    public static int maxProfit(int[] prices) {
//        int maxPro = 0;
//        for (int i = 0; i < prices.length; i++) {
//            for (int j = prices.length - 1; j > i; j--) {
//                if (prices[i] < prices[j]) {
//                    maxPro = Math.max(maxPro, prices[j] - prices[i]);
//                }
//            }
//        }
//        return maxPro;
}
