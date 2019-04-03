/**
 * @program JavaBooks
 * @description: 冒泡排序
 * @author: mf
 * @create: 2019/04/03 13:43
 */

public class BubbleOrder {
    // 思路
    // 第一步：从第一位开始，把相邻两位进行比较
    // 如果发现前面的比后面的大，就把大的数据交换在后面，循环比较完毕后，最后一位就是最大的
    // 第二步： 再来一次，只不过不用比较最后一位
    public static void main(String[] args) {
        int a [] = new int[]{18,62,68,82,65,9};

        System.out.println("排序前");
        for (int i : a) {
            System.out.print(i + " ");
        }
        System.out.println(" ");

        for (int j = 0; j < a.length; j++) {
            for (int i = 0; i < a.length - 1 - j; i++) {
                int temp = a[i];
                if (a[i] > a[i + 1]) {
                    a[i] = a[i + 1];
                    a[i + 1] = temp;
                }
            }
        }
        System.out.println("排序后");
        for (int i : a) {
            System.out.print(i + " ");
        }
    }
}
