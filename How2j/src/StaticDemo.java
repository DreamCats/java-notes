/**
 * @program JavaBooks
 * @description: static用法
 * @author: mf
 * @create: 2019/04/04 16:11
 */

public class StaticDemo {
    // 当一个属性被static修饰的时候，就叫做类属性，又叫做静态属性
    // 当一个属性被声明成类属性，那么所有的对象，都共享一个值
    public String name; //实例属性，对象属性，非静态属性
    protected float hp;
    static String copyright;//类属性,静态属性

    public static void main(String[] args) {
        StaticDemo garen =  new StaticDemo();
        garen.name = "盖伦";
        StaticDemo.copyright = "版权由Riot Games公司所有";

        System.out.println(garen.name);
        System.out.println(garen.copyright);

        StaticDemo teemo =  new StaticDemo();
        teemo.name = "提莫";
        System.out.println(teemo.name);
        System.out.println(teemo.copyright);


        //无需对象，直接通过类调用
        StaticDemo.battleWin();
    }



    // 类方法： 又叫做静态方法
    // 对象方法： 又叫实例方法，非静态方法
    // 访问一个对象方法，必须建立在有一个对象的前提的基础上
    // 访问类方法，不需要对象的存在，直接就访问
    //类方法，静态方法
    //通过类就可以直接调用
    public static void battleWin(){
        System.out.println("battle win");
    }

}
