package books;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @program JavaBooks
 * @description: 滑动窗口的最大值
 * @author: mf
 * @create: 2019/10/08 16:02
 */

/*
给定一个数组和滑动窗口的大小，请找出所有滑动窗口里的最大值。
例如，如果输入数组{2,3,4,2,6,2,5,1}及滑动窗口的大小为3，
那么一共存在6个滑动窗口，它们的最大值分别为{4,4,6,6,6,5}
 */
public class T59 {
    public static void main(String[] args) {
        int[] arr = {2, 3, 4, 2, 6, 2, 5, 1};
        System.out.println(maxInWindow(arr, 3));
    }

    private static ArrayList<Integer> maxInWindow(int[] arr, int size) {
        if (arr == null || arr.length == 0 || size > arr.length || size == 0) return null;
        ArrayList<Integer> res = new ArrayList<>();
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < arr.length; i++) {
            if (! queue.isEmpty()) {
                if (i >= queue.peek() + size) {
                    queue.pop();
                }
                while (!queue.isEmpty() && arr[i] > arr[queue.getLast()]) {
                    queue.removeLast();
                }
            }
            queue.add(i);
            if (i + 1 >= size) {
                res.add(arr[queue.peek()]); //
            }
        }
        return res;
    }
}
