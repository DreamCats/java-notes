import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @program JavaBooks
 * @description: parallel的小例子
 * @author: mf
 * @create: 2020/01/01 16:04
 */

public class T30 {
    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < 10000; i++) nums.add(100000 + r.nextInt(100000));

        long start = System.currentTimeMillis();
        nums.forEach(v -> isPrime(v));
        long end = System.currentTimeMillis();
        System.out.println(end - start);

        // 使用parallel stream api
        start = System.currentTimeMillis();
        nums.parallelStream().forEach(T30::isPrime);
        end = System.currentTimeMillis();
        System.out.println(end - start);
    }


    private static boolean isPrime(int num) {
        for (int i = 2; i < num / 2; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}
