import java.util.ArrayList;

/**
 * @program JavaBooks
 * @description: 和为s的数字
 * @author: mf
 * @create: 2019/10/08 15:21
 */

/*
输入一个递增排序的数组和一个数字s，在数组中查找两个树，使得
它们的和正好是s。如果有多对数字的和等于s，则输入任意一对即可。
 */
public class T57 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 4, 7, 11, 15};
        ArrayList<Integer> lists = sumToS(arr, 15);
        System.out.println(lists.toString());
    }

    private static ArrayList<Integer> sumToS(int[] arr, int target) {
        if (arr == null || arr.length < 2) return null;
        ArrayList<Integer> list = new ArrayList<>();
        int p1 = 0;
        int p2 = arr.length - 1;
        while (p1 != p2) {
            if (arr[p1] + arr[p2] < target) {
                p1++;
            } else if(arr[p1] + arr[p2] > target) {
                p2--;
            } else {
                list.add(arr[p1]);
                list.add(arr[p2]);
                return list;
            }
        }
        return null;
    }
}
