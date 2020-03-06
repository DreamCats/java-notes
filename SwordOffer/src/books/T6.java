package books;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @program JavaBooks
 * @description: 从尾到头打印链表
 * @author: mf
 * @create: 2019/08/19 13:19
 */

/*
输入一个链表的头节点，从尾到头反过来打印出每个节点的值。
链表定义如ListNote
 */

public class T6 {
    ArrayList<Integer> arrayList = new ArrayList<>();
    public static void main(String[] args) {

        // 递归方法中的arraylist
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);

        listNode1.next = listNode2;
        listNode2.next = listNode3;

        // 栈方法
        ArrayList<Integer> list = printListFromTailToHead1(listNode1);
        list.forEach(System.out::println);

        // 递归方法

    }

    /**
     * 栈方法
     * @param listNote
     * @return
     */
    public static ArrayList<Integer> printListFromTailToHead1(ListNode listNote) {
        Stack<Integer> stack = new Stack<>();
        // 压栈
        while (listNote != null) {
            stack.push(listNote.value);
            listNote = listNote.next;
        }

        // 弹栈
        ArrayList<Integer> arrayList = new ArrayList<>();
        while (!stack.isEmpty()) {
            arrayList.add(stack.pop());
        }
        return arrayList;
    }

    /**
     * 递归方法
     * @param listNote
     * @return
     */
    public  ArrayList<Integer> printListFromTailToHead2(ListNode listNote) {
        if (listNote != null) {
            this.printListFromTailToHead2(listNote.next); // 层层递归到最后链表最后一位，从最后一位add
            arrayList.add(listNote.value);
        }
        return arrayList;
    }


}




class TestMethod2 {
    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);

        listNode1.next = listNode2;
        listNode2.next = listNode3;
        T6 t6 = new T6();
        ArrayList<Integer> list = t6.printListFromTailToHead2(listNode1);
        list.forEach(System.out::println);
    }
}