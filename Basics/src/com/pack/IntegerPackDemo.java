/**
 * @program JavaBooks
 * @description: IntegerPackDemo
 * @author: mf
 * @create: 2020/02/12 00:08
 */

package com.pack;

/**
 * 所有整型包装类对象值的比较必须使用equals方法。
 */
public class IntegerPackDemo {
    public static void main(String[] args) {
        Integer x = 3; // 装箱
        int z = x; // 拆箱
        Integer y = 3;
        System.out.println(x == y); // true
        // 当使用自动装箱方式创建一个Integer对象时，当数值在-128 ~127时，
        // 会将创建的 Integer 对象缓存起来，当下次再出现该数值时，直接从缓存中取出对应的Integer对象。
        // 所以上述代码中，x和y引用的是相同的Integer对象。
        Integer a = new Integer(3);
        Integer b = new Integer(3);
        System.out.println(a == b); // false
        System.out.println(a.equals(b)); // true

        // 可以看一下整形包装类的equals的源码
        /**
         * public boolean equals(Object obj) {
         *         if (obj instanceof Integer) {
         *         // 返回参数中的Integer的value，所以最后的比的是内容内容
         *             return value == ((Integer)obj).intValue();
         *         }
         *         return false;
         *     }
         */

        // 缓存池
        // new Integer(123) 与 Integer.valueOf(123)
        // new Integer(123) 每次都会新建一个对象
        // Integer.valueOf(123) 会使用缓存池中的对象，多次调用会取得同一个对象的引用。前提注意范围
        Integer ab = new Integer(123);
        Integer ba = new Integer(123);
        System.out.println(ab == ba); // false
        // 从缓存中拿123
        Integer aa = Integer.valueOf(123);
        Integer bb = Integer.valueOf(123);
        System.out.println(aa == bb); // true
        /**
         *
         * public static Integer valueOf(int i) {
         *      // 判断是否在Integer的范围内
         *     if (i >= IntegerCache.low && i <= IntegerCache.high)
         *         return IntegerCache.cache[i + (-IntegerCache.low)];
         *     return new Integer(i);
         * }
         */
    }
}
