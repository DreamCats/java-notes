package books;

/**
 * @program JavaBooks
 * @description: 数字序列中某一位的数字
 * @author: mf
 * @create: 2019/09/27 09:44
 */

/*
数字以01234567891011121213141516...的格式序列化到一个字符
序列中。在这个序列中，第5位（从0开始计数）是5，第13位是1，第19位是4，
等等。请写一个函数，求任意第n位对应的数字。
 */
public class T44 {
    public static void main(String[] args) {
        int res = digitAtIndex(1002);
        System.out.println(res);
    }

    private static int digitAtIndex(int index) {
        if (index < 0) return -1;
        int digits = 1;
        while (true) {
            int numbers = countOfInteger(digits);
            if (index < numbers * digits) return digitAtIndex(index, digits);
            index -= digits * numbers;
            digits++;
        }
    }

    // 得到m位的数字总共有多少个
    private static int countOfInteger(int digits) {
        if (digits == 1) {
            return 10;
        }
        int count = (int) Math.pow(10, digits - 1);
        return 9 * count;
    }

    private static int digitAtIndex(int index, int digits) {
        int number = beginNumber(digits) + index / digits;
        int indexFromRight = digits - index % digits;
        for (int i = 1; i < indexFromRight; i++) {
            number /= 10;
        }
        return number % 10;
    }

    private static int beginNumber(int digits) {
        if (digits == 1) {
            return 0;
        }
        return (int) Math.pow(10, digits - 1);
    }
}
