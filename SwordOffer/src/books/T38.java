package books;

/**
 * @program JavaBooks
 * @description: 字符串的排列
 * @author: mf
 * @create: 2019/09/19 09:51
 */

/*
输入一个字符串，打印出该字符串中字符的所有排列。
例如，输入字符串abc，则打印出由字符a、b、c所能
排列出来的所有字符串abc、acb、bca、cab和cba
 */
/*
思路：
每次分为两个部分
第一部分固定第一个字符，后面的交换
第二部分继续可以分为两个部分，
第一个字符固定，后面交换
依次类堆
递归
 */
public class T38 {
    public static void main(String[] args) {
        pemutation("abc");
    }

    private static void pemutation(String s) {
        if (s == null) return;
        char[] chars = s.toCharArray();
        pemutation(chars, 0);
    }

    private static void pemutation(char[] chars, int begin) {
        if (chars.length == 0 || begin < 0 || begin > chars.length - 1) return;
        if (begin == chars.length - 1) {
            String s = new String(chars);
            System.out.print(s + '\t');
        } else {
            for (int i = begin; i < chars.length; i++) {
                swap(chars, begin, i); // 交换
                pemutation(chars, begin + 1);
                swap(chars, begin, i); // 交换回去
            }
        }
    }

    private static void swap(char[] chars, int begin, int i) {
        char temp = chars[begin];
        chars[begin] = chars[i];
        chars[i] = temp;
    }
}
