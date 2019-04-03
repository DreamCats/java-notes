/**
 * @program JavaBooks
 * @description: 选择法排序
 * @author: mf
 * @create: 2019/04/03 11:18
 */

public class SelectOrder {

    // 思路
    // 把第一位和其他所有的进行比较，只要比第一位小的，就换到第一个位置来
    // 比较完后，第一位就是最小的
    // 然后再从第二位和剩余的其他所有进行比较，只要比第二位小，就换到第二个位置来
    // 比较完后，第二位就是第二小的

    public static void main(String[] args) {

        int a [] = new int[]{18,62,68,82,65,9};
        int temp = 0;
        System.out.println(a.length);
        System.out.println("排序前");
        for (int i : a) {
            System.out.print(i + ",");
        }
        System.out.println(" ");
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[i] > a[j]) {
                    temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }

            }
        }
        System.out.println("排序后");
        for (int i : a) {
            System.out.print(i + ",");
        }
    }


}
