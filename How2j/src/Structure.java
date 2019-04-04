/**
 * @program JavaBooks
 * @description: 构造方法
 * @author: mf
 * @create: 2019/04/04 15:13
 */

public class Structure {
    // 通过一个类创建一个对象，这个过程叫做实例化
    // 实例化是通过调用构造方法(又叫做构造器)实现的
    // 方法名和类名一样（包括大小写)
    // 没有返回类型
    // 实例化一个对象的时候，必然调用构造方法



    // 默认不写， 默认提供一个无参的构造器
    // 构造器也可以添加参数，也可以重载
    public Structure() {
        System.out.println("I am Structure");
    }

    public static void main(String[] args) {
        Structure s = new Structure();
        // 引用对象， 内部构造器已经被加载
    }

}
