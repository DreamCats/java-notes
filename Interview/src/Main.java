import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    private static int values = 0;
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Condition conA = lock.newCondition();
        Condition conB = lock.newCondition();
        new Thread(() -> {
            try {
                lock.lock();
                while (values <= 10) {
                    System.out.println(Thread.currentThread().getName() + " " + values++);
                    conB.signal();
                    conA.await();
                }
                conB.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "偶数").start();
        new Thread(() -> {
            try {
                lock.lock();
                while (values <= 10) {
                    System.out.println(Thread.currentThread().getName() + " " + values++);
                    conA.signal();
                    conB.await();
                }
                conA.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "奇数").start();
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x){val = x;}
}

class ListNode {
    int val;
    ListNode next;

    public ListNode(int val) {
        this.val = val;
    }
}


class ListNodeTools {
    public static ListNode getListNode(int[] nums) {
        ListNode dummy = new ListNode(-1);
        ListNode pre = dummy;
        for (int num : nums) {
            pre.next = new ListNode(num);
            pre = pre.next;
        }
        return dummy.next;
    }

    public static void printListNode(ListNode node) {
        String s = "";
        ListNode p = node;
        while (p != null) {
            s += p.val + "->";
            p = p.next;
        }
        System.out.println(s+"null");
    }
}
