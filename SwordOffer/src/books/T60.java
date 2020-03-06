package books;

/**
 * @program JavaBooks
 * @description: n个骰子的点数
 * @author: mf
 * @create: 2019/10/08 20:30
 */

/*
把n个骰子仍在地上，所有骰子朝上一面的点数之和为s。
输入n，打印出s的所有可能的值出现的概率。
 */
public class T60 {
    public static void main(String[] args) {
        // 基于循环
        PrintProbability(2);
    }

    private static void PrintProbability(int number) {
        if (number < 1) return;
        int[][] pProbabilities = new int[2][6 * number + 1];
        // 初始化数组
        for (int i = 0; i < 6; i++) {
            pProbabilities[0][i] = 0;
            pProbabilities[1][i] = 0;
        }
        int flag = 0;
        for (int i = 1; i <= 6; i++) {
            pProbabilities[flag][i] = 1; //当第一次抛掷骰子时，有6种可能，每种可能出现一次
        }
        //从第二次开始掷骰子，假设第一个数组中的第n个数字表示骰子和为n出现的次数，
        //在下一循环中，我们加上一个新骰子，此时和为n的骰子出现次数应该等于上一次循环中骰子点数和为n-1,n-2,n-3,n-4,n-5，
        //n-6的次数总和，所以我们把另一个数组的第n个数字设为前一个数组对应的n-1,n-2,n-3,n-4,n-5，n-6之和
        for (int k = 2; k <= number; k++) {
            for (int i = 0; i < k; i++) {
                //第k次掷骰子，和最小为k，小于k的情况是不可能发生的！所以另不可能发生的次数设置为0！
                pProbabilities[1-flag][i] = 0;
            }
            for(int i = k;i <= 6 * k; i++) {
                pProbabilities[1-flag][i] = 0;//初始化，因为这个数组要重复使用，上一次的值要清0
                for(int j = 1;j <= i && j <= 6; j++) {
                    pProbabilities[1-flag][i] += pProbabilities[flag][i-j];
                }
            }
            flag = 1-flag;
        }
        double total = Math.pow(6, number);
        for(int i = number; i <= 6 * number; i++) {
            double ratio = pProbabilities[flag][i]/total;
            System.out.println("sum: "+i+" ratio: "+ratio);
        }
    }
}
