package books;

/**
 * @program JavaBooks
 * @description: 1~n整数中1出现的次数
 * @author: mf
 * @create: 2019/09/26 09:48
 */

/*
输入一个整数n，求1～n这n个整数的十进制表示中1出现的
次数。例如，输入12，1～12这些整数中包含1的数字有1、10
、11和12，1一共出现了5次
 */
public class T43 {
    public static void main(String[] args) {
//        int count = numberOfBetweenAndN(12);
        int count = numberOfBetweenAndN2(12);
        System.out.println(count);
    }

    private static int numberOfBetweenAndN(int num) {
        int count = 0;
        for (int i = 0; i <= num; i++) {
            String n = String.valueOf(i);
            for (int j = 0; j < n.length(); j++) {
                if ('1' == n.charAt(j)) {
                    count++;
                }
            }
        }
        return count;
    }

    private static int numberOfBetweenAndN2(int num) {
        int count = 0;
        for (int i = 1; i <= num; i++) {
            int n = i;
            while (n != 0) {
                if (n % 10 == 1) {
                    count++;
                }
                n /=  10;
            }
        }
        return count;
    }
}
