import java.util.HashSet;

/**
 * @program JavaBooks
 * @description: 202.快乐数
 * @author: mf
 * @create: 2019/11/06 10:15
 */


public class IsHappy {
    public static void main(String[] args) {
        System.out.println(isHappy(19));
        System.out.println(isHappy2(19));
    }

    /**
     * 递归，但有4的话，就一直循环，所以是4就false
     * @param n
     * @return
     */
    private static boolean isHappy(int n) {
        if (n == 1) return true;
        if (n != 4) {
            int sum = 0, k = n;
            while (k > 0) {
                sum += (k % 10) * (k % 10);
                k /= 10;
            }
            return isHappy(sum);
        }
        return false;
    }

    private static boolean isHappy2(int n) {
        if (n == 1) return true;
        HashSet<Integer> set = new HashSet<>();
        while (2 > 1) {
            int sum = 0;
            while (n > 0) {
                sum += (n % 10) * (n % 10);
                n /= 10;
            }
            if (sum == 1) return true;
            if (!set.add(sum)) return false;
            n = sum;
        }
    }
}
