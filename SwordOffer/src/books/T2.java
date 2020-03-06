package books; /**
 * @program JavaBooks
 * @description: 单例模式
 * @author: mf
 * @create: 2019/08/15 15:53
 */

/**
 * 饿汉
 */
public class T2 {
    private static T2 instance = new T2();
    private T2(){}

    public static T2 getInstance() {
        return instance;
    }
}

/**
 * 饿汉变种
 */
class Singleton1 {
    private static Singleton1 instance = null;
    static {
        instance = new Singleton1();
    }
    private Singleton1() {}

    public static Singleton1 getInstance() {
        return instance;
    }
}

/**
 * 懒汉 -- 线程不安全...
 */
class Singleton2 {
    private static Singleton2 instance = null;
    private Singleton2(){}

    public static Singleton2 getInstance() {
        if (instance == null){
            instance = new Singleton2();
        }
        return instance;

    }
}

/**
 * 懒汉 -- 线程安全， 但消耗资源较为严重
 */
class Singleton3 {
    private static Singleton3 instance = null;

    private Singleton3() {
    }

    public static synchronized Singleton3 getInstance() {
        if (instance == null) {
            instance = new Singleton3();
        }
        return instance;
    }
}

/**
 * 线程安全，双重校验
 */
class Singleton4 {
    private static volatile Singleton4 instance = null;

    private Singleton4() {
    }

    public static Singleton4 getInstance() {
        if (instance == null) {
            synchronized (Singleton4.class) {
                if (instance == null) {
                    instance = new Singleton4();
                }
            }
        }
        return instance;
    }
}