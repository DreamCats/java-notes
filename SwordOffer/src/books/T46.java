package books;

/**
 * @program JavaBooks
 * @description: 把数字翻译成字符串
 * @author: mf
 * @create: 2019/09/29 10:35
 */

/*
给定一个数字，我们按照如下规则把它翻译为字符串：
0翻译成”a”，1翻译成”b”，……，11翻译成”l”，……，25翻译成”z”。
一个数字可能有多个翻译。例如12258有5种不同的翻译，
它们分别是”bccfi”、”bwfi”、”bczi”、”mcfi”和”mzi”。请编程实现一个函数用来计算一个数字有多少种不同的翻译方法。
 */

/*
思路
可以选一个数字或两个连续的数字（10~25）翻译成一个字符。
定义f(i)：从第i位数字开始的不同翻译数目
1）若第i个数字和第i+1个数字拼接成的数字在10~25范围内，则递归式子为：
    f(i)=f(i+1)+f(i+2)
2）否则
    f(i)=f(i+1)
 */
public class T46 {
    public static void main(String[] args) {
        System.out.println(getTranslationCount(12258));
    }

    private static int getTranslationCount(int number) {
        if (number < 10) return 0;
        return translationCount(String.valueOf(number));
    }

    private static int translationCount(String number) {
        int length = number.length();
        int[] countRecords = new int[length];
        // 只有一个数字，则只有一种翻译方式
        countRecords[length - 1] = 1;
        int count;
        for (int i = length - 2; i >=0; i--) {
            count = countRecords[i + 1];
            int digit1 = number.charAt(i) - '0';
            int digit2 = number.charAt(i + 1) - '0';
            int connectNumber = digit1 * 10 + digit2; // 拼接两个数字
            if (connectNumber >= 10 && connectNumber <= 25) {
                if (i < length - 2) {
                    //f(i) = f(i+1) + f(i+2)
                    count += countRecords[i + 2];
                } else if (i == length - 2) {
                    count += 1;
                }
            }
            countRecords[i] = count;
        }
        //
        return countRecords[0];
    }
}

