package books;

/**
 * @program JavaBooks
 * @description: 二进制中1的个数
 * @author: mf
 * @create: 2019/08/26 10:04
 */

/*
请实现一个函数，输入一个整数，输出该整数二进制表示中1的个数。
例如，把9表示成二进制是1001，有2位是1。因此，如果输入9，则该函数
输出2。
 */
public class T15 {
    public static void main(String[] args) {
        System.out.println(NumberOf1(9));
        System.out.println(NumberOf2(9));
        System.out.println(NumberOf3(9));
    }

    // 最高效的方法
    private static int NumberOf3(int n) {
        int count = 0;
        while (n != 0) {
            count++;
            n = (n - 1) & n;
        }
        return count;
    }


    // 出现负数就凉了
    private static int NumberOf1(int num) {
        int count = 0 ;
        while(num != 0) {
            if ((1 & num) == 1) {
                count++;
            }
            num = num >> 1;
        }
        return count;
    }
    // 但是循环32次，慢
    private static int NumberOf2(int num) {
        int count = 0;
        int flag = 1;
        while(flag >= 1) {
            if ((num & flag) >= 1)
                count++;

            flag = flag << 1;
        }
        return count;
    }
}
