/**
 * @program JavaBooks
 * @description: 单例模式
 * @author: mf
 * @create: 2019/04/04 16:27
 */

public class SingletonMode {

    // 单例模式又叫做 Singleton模式，指的是一个类，在一个JVM里，只有一个实例存在。
    // 饿汉式单例模式
    // GiantDragon 应该只有一只，通过私有化其构造方法，使得外部无法通过new 得到新的实例。
    // GiantDragon 提供了一个public static的getInstance方法，外部调用者通过该方法获取12行定义的对象，而且每一次都是获取同一个对象。 从而达到单例的目的。
    // 这种单例模式又叫做饿汉式单例模式，无论如何都会创建一个实例
    //私有化构造方法使得该类无法在外部通过new 进行实例化
    private SingletonMode(){

    }

    //准备一个类属性，指向一个实例化对象。 因为是类属性，所以只有一个

    private static SingletonMode instance = new SingletonMode();

    //public static 方法，提供给调用者获取12行定义的对象
    public static SingletonMode getInstance(){
        return instance;
    }



}


// 饿汉式单例模式 测试
class TestDemo {
    public static void main(String[] args) {
        // 报错
//        SingletonMode g = new SingletonMode();
        SingletonMode g1 = SingletonMode.getInstance();
        SingletonMode g2 = SingletonMode.getInstance();
        SingletonMode g3 = SingletonMode.getInstance();
        //都是同一个对象
        System.out.println(g1==g2);
        System.out.println(g1==g3);
    }
}

// 懒汉式单例模式

// 懒汉式单例模式与饿汉式单例模式不同，只有在调用getInstance的时候，才会创建实例
class SingletonMode1 {

    // 私有化  使得该类无法初始化
    private SingletonMode1(){

    }
    // 准备一个类属性
    private static SingletonMode1 instance;

    // 返回实例对象
    public static SingletonMode1 getInstance() {
        if (null == instance) {
            instance = new SingletonMode1();
        }

        // 返回 instance对象
        return instance;
    }
}

// 测试懒汉式

class TestDemo1 {
    public static void main(String[] args) {
        // new 报错

//        SingletonMode1 g = new SingletonMode1();

        SingletonMode1 g1 = SingletonMode1.getInstance();
        SingletonMode1 g2 = SingletonMode1.getInstance();
        SingletonMode1 g3 = SingletonMode1.getInstance();
        //都是同一个对象
        System.out.println(g1==g2);
        System.out.println(g1==g3);
    }
}
// 饿汉式是立即加载的方式，无论是否会用到这个对象，都会加载。
// 如果在构造方法里写了性能消耗较大，占时较久的代码，比如建立与数据库的连接，那么就会在启动的时候感觉稍微有些卡顿。

// 懒汉式，是延迟加载的方式，只有使用的时候才会加载。
// 并且有线程安全的考量(鉴于同学们学习的进度，暂时不对线程的章节做展开)。

// 使用懒汉式，在启动的时候，会感觉到比饿汉式略快，因为并没有做对象的实例化。


// 1. 构造方法私有化
// 2. 静态属性指向实例
// 3. public static的 getInstance方法，返回第二步的静态属性


