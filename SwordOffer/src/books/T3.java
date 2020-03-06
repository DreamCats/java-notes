package books;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @program JavaBooks
 * @description: 数组中重复的数字
 * @author: mf
 * @create: 2019/08/16 19:22
 */


/*
在一个长度为n的数组中里的所有数字都在0～n-1的范围中。数组中某些数字是重复的，
但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复
的数字。例如，如果输入长度为7的数组{2, 3, 1, 0, 2, 5, 3},那么对应的输出
重复的数字2或者3
 */
public class T3 {
    public static void main(String[] args) {
        int[] arr = {2, 3, 1, 0, 2, 5, 3};

        // 第一种 可修改数组
        ArrayList<Integer> helpList = duplication(arr);
        System.out.println(helpList);

        int[] arr1 = {2, 3, 1, 0, 2, 5, 3};
        // 第二种，哈希表 -- 不可修改数组
        ArrayList<Integer> helpList1 = duplication2(arr1);
        System.out.println(helpList1);

        // 刚才的第一种，可以添加辅助数组来解决不可修改数组的方案
        // 或者用二分查找...以时间换空间...

    }

    /**
     * 可修改数组
     * @param arr
     * @return
     */
    public static ArrayList<Integer> duplication(int[] arr) {
        ArrayList<Integer> helpList = new ArrayList<>();
        // 提高鲁棒性
        for (int i : arr) {
            if (i < 0 || i > arr.length - 1) {
                System.out.println("arr is not true...");
                break;
            }
        }
        // 核心步骤
        for (int i = 0; i < arr.length; i++) {
            while (arr[i] != i) {
                if (arr[i] == arr[arr[i]]) {
                    helpList.add(arr[i]);
                    arr[i] = i;
                    break;
                }
                swap(arr, arr[i], arr[arr[i]]);
            }
        }
        return  helpList;

    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 哈希
     * @param arr
     * @return
     */
    public static ArrayList<Integer> duplication2(int[] arr) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        ArrayList<Integer> helpList = new ArrayList<>();
        for (int i : arr) {
            if (hashMap.containsKey(i)) {
                helpList.add(i);
            }
            hashMap.put(i,i);
        }
        return helpList;
    }

}
