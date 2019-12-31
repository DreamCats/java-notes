/**
 * @program JavaBooks
 * @description: 模拟栈溢出
 * @author: mf
 * @create: 2019/12/31 13:46
 */

/**
 * 模拟栈溢出，虚拟机栈，本地方法栈
 */
public class T1 {

    public void m() {
        System.out.println("stack test overflow...");
        m();
    }

    public static void main(String[] args) {
        new T1().m();
    }
}
