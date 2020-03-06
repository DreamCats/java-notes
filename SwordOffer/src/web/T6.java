package web; /**
 * @program LeetNiu
 * @description: 旋转数组的最小数组
 * @author: mf
 * @create: 2020/01/09 12:06
 */

/**
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 * 输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。
 * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
 * NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
 */
public class T6 {
    public int minNumberInRotateArray(int [] array) {
        // 判断条件
        if (array.length == 0) return 0;
        if (array.length == 1) return array[0];

        int a = array[0];
        // 根据数组的特征
        for (int i = 1; i < array.length; i++) {
            if (a > array[i]) {
                return array[i];
            } else {
                a = array[i];
            }
        }
        return 0;
    }
}
