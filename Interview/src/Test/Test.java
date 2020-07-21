/**
 * @program JavaBooks
 * @description: 测试
 * @author: mf
 * @create: 2020/05/24 00:04
 */

package Test;

import java.util.Scanner;

public class Test {

    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        long start = 1;
        long end = n;
        long temp = 0;
        // 二分法
        while (start < end){
            long mid = start + (end - start) / 2;
            if (minNum(mid, n)){
                temp = mid;
                end = mid;
            } else{
                start = mid + 1;
            }
        }
        System.out.println(temp);
    }

    public static boolean minNum(long m, long n){
        long nums1 = 0;
        long temp = n;
        long mid = 0;
        while (temp >= 0){
            if (temp < m){
                nums1 += temp;
                break;
            }
            nums1 += m;
            temp -= m;
            temp -= temp / 10;
        }
        mid = n % 2 == 0 ? n / 2 : (n + 1) / 2;
        return nums1 >= mid ? true : false;
    }

}
