package web; /**
 * @program LeetNiu
 * @description: 滑动窗口的最大值
 * @author: mf
 * @create: 2020/01/17 23:07
 */

import java.util.ArrayList;
import java.util.Collections;

/**
 *给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值
 * 。例如，如果输入数组{2,3,4,2,6,2,5,1}及滑动窗口的大小3，那么一共存在6个滑动窗口，
 * 他们的最大值分别为{4,4,6,6,6,5}； 针对数组{2,3,4,2,6,2,5,1}的滑动窗口有以下6个：
 * {[2,3,4],2,6,2,5,1}， {2,[3,4,2],6,2,5,1}， {2,3,[4,2,6],2,5,1}， {2,3,4,[2,6,2],5,1}， {2,3,4,2,[6,2,5],1}， {2,3,4,2,6,[2,5,1]}。
 */
public class T64 {
    public ArrayList<Integer> maxInWindows(int [] num, int size) {
        if (null == num || size < 0) {
            return null;
        }
        ArrayList<Integer> list = new ArrayList<>();
        if(size == 0) {
            return list;
        }
        int length = num.length;
        ArrayList<Integer> temp = null;
        if (length < size) {
            return list;
        } else {
            for (int i = 0; i < length - size + 1; i++) {
                temp = new ArrayList<>();
                for (int j = i; j < size + i; j++) {
                    temp.add(num[j]);
                }
                Collections.sort(temp);
                list.add(temp.get(temp.size() - 1));
            }
        }
        return list;
    }
}
