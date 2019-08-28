import java.util.Arrays;

/**
 * @program JavaBooks
 * @description: 打印从1到最大的n位数
 * @author: mf
 * @create: 2019/08/28 09:51
 */
/*
输入数字n，按顺序打印出从1到最大的n位十进制数。比如输入3
则打印出1、2、3一直到最大的3位数999
 */

/*
面试官没给n的范围， 万一很大呢？ int和long岂不是要溢出？
这种题溢出的题，字符串可以搞定。
 */
public class T18 {
    public static void main(String[] args) {
//        printToMax(2);
        printToMax2(2);
    }
    // 最笨的方法，一定不符合面试官的要求
    public static void printToMax(int n) {
        int number = 1;
        // 先求最大数
        while (n-- > 0) {
            number *= 10;
        }

        while (number-- > 1) {
            System.out.println(number);
        }
    }
    //
    public static void printToMax2(int n) {
        if (n <= 0) return;
        char[] number = new char[n];
        for (int i = 0; i < number.length; i++) {
            number[i] = '0';
        }
        while (!Increment(number)) {
            printNumber(number);
        }
    }



    private static boolean Increment(char[] number) {
        boolean isOverflow = false;
        int nTakeOver = 0;
        int nLength = number.length;
        for (int i = nLength - 1; i >= 0; i--) {
            int nSum = number[i] - '0' + nTakeOver;
            if (i == nLength - 1) nSum++;
            if (nSum >= 10) {
                if (i == 0) isOverflow = true;
                else {
                    nSum -= 10;
                    nTakeOver = 1;
                    number[i] = (char) ('0' + nSum);
                }
            } else {
                number[i] = (char) ('0' + nSum);
                break;
            }

        }
        return isOverflow;
    }
    private static void printNumber(char[] number) {
        boolean isBeginning0 = true;

        int nLength = number.length;
        for (int i = 0; i < nLength; i++) {
            if (isBeginning0 && number[i]!= '0') isBeginning0 = false;
            if(! isBeginning0) System.out.print(number[i]);
        }
        System.out.print('\t');
    }
}
