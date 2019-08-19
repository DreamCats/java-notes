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
        ListNote listNote1 = new ListNote(1);
        ListNote listNote2 = new ListNote(2);
        ListNote listNote3 = new ListNote(3);

        listNote1.next = listNote2;
        listNote2.next = listNote3;

        // 栈方法
        ArrayList<Integer> list = printListFromTailToHead1(listNote1);
        list.forEach(System.out::println);

        // 递归方法

    }


    // 栈方法
    public static ArrayList<Integer> printListFromTailToHead1(ListNote listNote) {
        Stack<Integer> stack = new Stack<>();
        // 压栈
        while (listNote != null) {
            stack.push(listNote.val);
            listNote = listNote.next;
        }

        // 弹栈
        ArrayList<Integer> arrayList = new ArrayList<>();
        while (!stack.isEmpty()) {
            arrayList.add(stack.pop());
        }
        return arrayList;
    }

    // 递归方法
    public  ArrayList<Integer> printListFromTailToHead2(ListNote listNote) {
        if (listNote != null) {
            this.printListFromTailToHead2(listNote.next);
            arrayList.add(listNote.val);
        }
        return arrayList;
    }


}


class ListNote {
    int val;
    ListNote next = null;

    public ListNote(int val) {
        this.val = val;
    }
}

class TestMethod2 {
    public static void main(String[] args) {
        ListNote listNote1 = new ListNote(1);
        ListNote listNote2 = new ListNote(2);
        ListNote listNote3 = new ListNote(3);

        listNote1.next = listNote2;
        listNote2.next = listNote3;
        T6 t6 = new T6();
        ArrayList<Integer> list = t6.printListFromTailToHead2(listNote1);
        list.forEach(System.out::println);
    }
}