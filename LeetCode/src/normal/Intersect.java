package normal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @program JavaBooks
 * @description: 350.两个数组的交集2
 * @author: mf
 * @create: 2019/11/06 16:14
 */

/*
题目：https://leetcode-cn.com/problems/intersection-of-two-arrays-ii/
类型：哈希和集合
难度：easy
 */
public class Intersect {
    public static void main(String[] args) {
        int[] nums1 = {1,2,2,1};
        int[] nums2 = {2,2};
        int[] ans = intersect2(nums1, nums2);
        System.out.println(Arrays.toString(ans));
    }

    /**
     * 集合
     * @param nums1
     * @param nums2
     * @return
     */
    private static int[] intersect(int[] nums1, int[] nums2) {
        ArrayList<Integer> list1 = new ArrayList<>();
        for (int num : nums1) {
            list1.add(num);
        }
        ArrayList<Integer> list2 = new ArrayList<>();
        for (int num : nums2) {
            if (list1.contains(num)) {
                list2.add(num);
                list1.remove(num);
            }
        }
        int[] ans = new int[list2.size()];
        for (int i = 0; i < list2.size(); i++) {
            ans[i] = list2.get(i);
        }
        return ans;
    }

    /**
     * 哈希
     * @param nums1
     * @param nums2
     * @return
     */
    private static int[] intersect2(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums1) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        ArrayList<Integer> list = new ArrayList<>();
        for (int num : nums2) {
            Integer count = map.get(num);
            if (count != null && count != 0) {
                list.add(num);
                map.put(num, --count);
            }
        }
        int[] ans = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }
}
