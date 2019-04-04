/**
 * @program JavaBooks
 * @description: 面向对象
 * @author: mf
 * @create: 2019/04/04 14:38
 */

public class ObjectDemo {

    // 引用的概念，如果一个变量的类型是 类类型，而非基本类型，那么该变量又叫做引用。


    // new Hero(); 代表创建了一个Hero对象 但是也仅仅是创建了一个对象，没有办法访问它
    // 为了访问这个对象，会使用引用来代表这个对象
    // Hero h = new Hero();
    // h这个变量是Hero类型，又叫做引用
    // =的意思指的h这个引用代表右侧创建的对象
    // “代表” 在面向对象里，又叫做“指向”

    String name; //姓名

    float hp; //血量

    float armor; //护甲

    int moveSpeed; //移动速度

    public static void main(String[] args) {
        //创建一个对象
        new Hero();

        //使用一个引用来指向这个对象
        Hero h = new Hero();

        // 对象就像 "房产"， 引用就像"房产证"
        // 房产证的复印件可以有多张，但是真正的"房产" 只有这么一处

        //使用一个引用来指向这个对象
        Hero h1 = new Hero();
        Hero h2 = h1;  //h2指向h1所指向的对象
        Hero h3 = h1;
        Hero h4 = h1;
        Hero h5 = h4;

        //h1,h2,h3,h4,h5 五个引用，都指向同一个对象


    }






}
