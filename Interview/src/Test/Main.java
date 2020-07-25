/**
 * @program JavaBooks
 * @description: 牛客
 * @author: mf
 * @create: 2020/07/24 22:24
 */

package Test;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        while (k-- > 0) {
            int n = sc.nextInt();
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = sc.nextInt();
            }
            System.out.println(eql(nums));
        }
    }

    public static String eql(int[] arr){
        Arrays.sort(arr);
        int a = arr[0];
        int b = arr[arr.length-1];
        if(arr.length == 2){
            return "YES";
        }
        if((a+b) % 2 != 0){
            return "NO";
        }
        int mid = (a+b)/2;
        int offset = b - mid;
        for(int i = 0;i < arr.length;i++){
            if(!(arr[i] + offset == mid || arr[i] - offset == mid || arr[i] == mid)){
                return "NO";
            }
        }
        return "YES";
    }
}
