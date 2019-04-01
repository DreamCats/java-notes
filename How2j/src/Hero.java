/**
 * @program JavaBooks
 * @description: 创建英雄类
 * @author: mf
 * @create: 2019/04/01 09:44
 */

public class Hero {
    String name; // 名字
    float hp; // 血量
    float armor; // 护甲
    int moveSpeed; // 移动速度

    public static void main(String[] args) {
        Hero garen = new Hero();
        garen.name = "盖伦";
        garen.hp = 616.28f;
        garen.armor = 27.536f;
        garen.moveSpeed = 35;

        Hero teemo = new Hero();
        teemo.name = "提莫";
        teemo.hp = 383f;
        teemo.armor = 14f;
        teemo.moveSpeed = 330;

    }
}

