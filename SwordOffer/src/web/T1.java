package web; /**
 * @program LeetNiu
 * @description: 二维数组中的查找
 * @author: mf
 * @create: 2020/01/06 14:57
 */

/**
 * 在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，
 * 每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，
 * 判断数组中是否含有该整数。
 *
 * 思路：
 * 注意数组值的规律，可适当举个例子
 */
public class T1 {
    public boolean Find(int target, int [][] array) {
        int col = 0;
        for (int i = array.length - 1; i >= 0; i--) {
            while (col < array[0].length && array[i][col] < target) col++;
            if (col == array[0].length) return false;
            if (array[i][col] == target) return true;
        }
        return false;
    }
}
