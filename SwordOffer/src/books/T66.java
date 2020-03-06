package books; /**
 * @program JavaBooks
 * @description: 构建乘积数组
 * @author: mf
 * @create: 2019/10/09 20:09
 */

import java.util.Arrays;

/**
 * 给定一个数组A[0,1,...,n-1],
 * 请构建一个数组B[0,1,...,n-1],
 * 其中B中的元素B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。
 * 不能使用除法。
 */
public class T66 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        int[] res = multiply(arr);
        System.out.println(Arrays.toString(res));
    }

    private static int[] multiply(int[] arr) {
        int length = arr.length;
        int[] temp = new int[length];
        if (length != 0) {
            temp[0] = 1;
            // 计算下三角
            for (int i = 1; i < length; i++) {
                temp[i] = temp[i - 1] * arr[i - 1];
            }
            int t = 1;
            // 计算上三角
            for (int i = length - 2; i >= 0; i--) {
                t *= arr[i + 1];
                temp[i] *= t;
            }
        }
        return temp;
    }
}
