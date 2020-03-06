package web; /**
 * @program LeetNiu
 * @description: 和为S的连续正数序列
 * @author: mf
 * @create: 2020/01/15 10:37
 */

import java.util.ArrayList;

/**
 * 小明很喜欢数学,有一天他在做数学作业时,要求计算出9~16的和,他马上就写出了正确答案是100。
 * 但是他并不满足于此,他在想究竟有多少种连续的正数序列的和为100(至少包括两个数)。
 * 没多久,他就得到另一组连续正数和为100的序列:18,19,20,21,22。
 * 现在把问题交给你,你能不能也很快的找出所有和为S的连续正数序列? Good Luck!
 */
public class T41 {
    public ArrayList<ArrayList<Integer> > FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<>();
        int phigh = 2;
        int plow = 1;
        while(phigh > plow) {
            int cur = (phigh + plow) * (phigh - plow + 1) / 2;
            if (cur < sum) {
                phigh++;
            }
            if (cur == sum) {
                ArrayList<Integer> arrayList = new ArrayList<>();
                for (int i = plow; i <= phigh; i++) {
                    arrayList.add(i);
                }
                arrayLists.add(arrayList);
                plow++;
            }
            if (cur > sum) {
                plow++;
            }
        }
        return arrayLists;
    }
}
