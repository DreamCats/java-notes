package books;

import java.security.PrivateKey;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

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
public class T41 {
    private static int count = 0;
    private static PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    private static PriorityQueue<Integer> maxHeap = new PriorityQueue<>(15, new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    });
    public static void main(String[] args) {
        int[] arr = {1, 3, 2, 4, 5};
        LinkedList<Integer> list = new LinkedList<>();
        for (int num : arr) {
            insert(list, num);
        }
        Double median = getMedian(list);
        System.out.println(median);



        // 最大堆最小堆
        for (int num : arr) {
            insert(num);
        }
        Double median2 = getMedian();
        System.out.println(median2);
    }

    // 最大堆和最小堆
    private static void insert(Integer num) {
        if ((count & 0x1) == 0) {
            maxHeap.offer(num);
            int filterMaxNum = maxHeap.poll();
            minHeap.offer(filterMaxNum);
        } else {
            minHeap.offer(num);
            int filteredMinNum = minHeap.poll();
            maxHeap.offer(filteredMinNum);
        }
        count++;
    }

    private static Double getMedian() {
        if ((count & 0x1) == 0) {
            return new Double((minHeap.peek() + maxHeap.peek())) / 2;
        } else {
            return new Double(minHeap.peek());
        }
    }

    // 链表
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

    // 链表
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
