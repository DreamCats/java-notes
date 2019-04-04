/**
 * @program JavaBooks
 * @description: 重载
 * @author: mf
 * @create: 2019/04/04 15:05
 */

public class OverloadDemo extends Hero{
    // 方法的重载指的是方法名一样，但是参数类型不一样
    // 继承Hero.java 这给类

    public void attack() {
        System.out.println(name + " 进行了一次攻击 ，但是不确定打中谁了");
    }

    public void attack(Hero h1) {
        System.out.println(name + "对" + h1.name + "进行了一次攻击 ");
    }

    public void attack(Hero h1, Hero h2) {
        System.out.println(name + "同时对" + h1.name + "和" + h2.name + "进行了攻击 ");
    }

    // 可变数量的参数
    public void attack(Hero... heros) {
        for (int i = 0; i < heros.length; i++) {
            System.out.println(name + " 攻击了 " + heros[i].name);

        }


    public static void main(String[] args) {
        OverloadDemo bh = new OverloadDemo();
        bh.name = "赏金猎人";

        Hero h1 = new Hero();
        h1.name = "盖伦";
        Hero h2 = new Hero();
        h2.name = "提莫";

        bh.attack(h1);
        bh.attack(h1, h2);
    }
}
