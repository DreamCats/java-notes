/**
 * @program JavaBooks
 * @description: 引用计数的循环引用问题
 * @author: mf
 * @create: 2020/01/19 12:53
 */

public class T2 {
    public static void main(String[] args) {
        Object object1 = new Object();
        Object object2 = new Object();
        object1 = object2; // obj1 引用obj2
        object2 = object1; // obj2 引用obj1  循环引用
        object1 = null;
        object2 = null;
    }
}
