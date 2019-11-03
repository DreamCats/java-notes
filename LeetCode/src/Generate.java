/**
 * @program JavaBooks
 * @description: 118.杨辉三角
 * @author: mf
 * @create: 2019/11/03 16:38
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 难度：easy
 * 类别：数组
 * 链接：https://leetcode-cn.com/problems/pascals-triangle/
 * 思路：分析规律
 * */

/**
 * 题目描述
 * 输入：5
 * 输出：
 * [
 *      [1],
 *     [1,1],
 *    [1,2,1],
 *   [1,3,3,1],
 *  [1,4,6,4,1]
 * ]
 */

public class Generate {
    public static void main(String[] args) {
        List<List<Integer>> res = generate(5);
        for (List<Integer> re : res) {
            System.out.println(re.toString());
        }
    }

    private static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> curRow = new ArrayList<>();
            for (int j = 0; j <= i ; j++) {
                // 第一个元素和最后一个元素
                if (j == 0 || j == i){
                    curRow.add(1);
                    continue;
                }
                if (i == 0 || i == 1) {
                    // 前两行没得中间元素
                    continue;
                }
                List<Integer> preRow = ans.get(i - 1);
                // 处理中间元素
                int value = preRow.get(j - 1) + preRow.get(j);
                curRow.add(value);
            }
            ans.add(curRow);
        }
        return ans;
    }
}
