package web; /**
 * @program LeetNiu
 * @description: 链表中环的入口结点
 * @author: mf
 * @create: 2020/01/16 14:13
 */

import java.util.HashMap;

/**
 * 给一个链表，若其中包含环，请找出该链表的环的入口结点，否则，输出null。
 */
public class T55 {
    public ListNode EntryNodeOfLoop(ListNode pHead) {
        if (null == pHead) {
            return null;
        }
        HashMap<ListNode, Integer> map = new HashMap<>();
        map.put(pHead, 1);
        while (null != pHead.next) {
            if (map.containsKey(pHead.next)) {
                return pHead.next;
            }
            map.put(pHead.next, 1);
            pHead = pHead.next;
        }
        return null;
    }
}
