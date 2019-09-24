import java.util.LinkedList;

/**
 * @program JavaBooks
 * @description: 数据流中的中位数
 * @author: mf
 * @create: 2019/09/24 08:59
 */

/*
如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值
那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中
读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
 */

/*
思路：
先用链表
 */
public class T43 {
    public static void main(String[] args) {
        int[] arr = {1, 3, 2, 4, 5};
        LinkedList<Integer> list = new LinkedList<>();
        for (int num : arr) {
            insert(list, num);
        }
        Double median = getMedian(list);
        System.out.println(median);
    }

    private static void insert(LinkedList<Integer> list, Integer num) {
        if (list.size() == 0 || num < list.getFirst()) {
            list.add(num);
        } else {
            boolean insertFlag = false;
            for (Integer e : list) {
                if (num < e) {
                    int index = list.indexOf(e);
                    list.add(index, num);
                    insertFlag = true;
                    break;
                }
            }
            if (!insertFlag) {
                list.addLast(num);
            }
        }
    }

    private static Double getMedian(LinkedList<Integer> list) {
        if (list.size() == 0) return null;
        if ((list.size() & 0x1) == 0) {
            int i = list.size() >> 1;
            Double a = Double.valueOf(list.get(i - 1) + list.get(i));
            return a / 2;
        }
        return Double.valueOf(list.get(list.size() / 2));
    }
}
