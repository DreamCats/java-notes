/**
 * @program JavaBooks
 * @description: volatile有序
 * @author: mf
 * @create: 2020/02/13 01:13
 */

package com.juc.volatiletest;

public class VolatileOrder {
    // 单例
    private static VolatileOrder instance = null;
    //private static volatile Singleton instance = null;
    private VolatileOrder() { }

    public static VolatileOrder getInstance() {
        //第一次判断
        if(instance == null) {
            synchronized (VolatileOrder.class) {
                if(instance == null) {
                    //初始化，并非原子操作
                    instance = new VolatileOrder();
                }
            }
        }
        return instance;
    }
}
