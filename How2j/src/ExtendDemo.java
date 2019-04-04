/**
 * @program JavaBooks
 * @description: 继承
 * @author: mf
 * @create: 2019/04/04 14:53
 */

public class ExtendDemo extends ItemDemo{
    int damage; //攻击力
    public static void main(String[] args) {
        ExtendDemo weapon = new ExtendDemo();
        weapon.damage = 650; //damage属性在类Weapon中新设计的
        weapon.name = "无尽之刃"; // //name属性，是从Item中继承来的，就不需要重复设计了
        weapon.price = 3600;

    }
}

class ItemDemo {
    String name;
    int price;
}

class Weapon{
    String name;
    int price;
    int damage; //攻击力

}

