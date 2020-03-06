package web; /**
 * @program LeetNiu
 * @description: 从尾到头打印链表
 * @author: mf
 * @create: 2020/01/08 16:16
 */

import java.util.ArrayList;

/**
 * 输入一个链表，按链表从尾到头的顺序返回一个ArrayList。
 * 思路：
 *
 */
public class T3 {
    // 创建list
    ArrayList<Integer> list = new ArrayList<>();
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        // 判断头节点是否为空
        if (listNode != null) {
            // 递归
            this.printListFromTailToHead(listNode.next);
            list.add(listNode.val);
        }
        return list;
    }
}
