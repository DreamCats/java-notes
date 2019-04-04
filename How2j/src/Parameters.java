/**
 * @program JavaBooks
 * @description: 传入参数
 * @author: mf
 * @create: 2019/04/04 15:45
 */

public class Parameters {
    String name; //姓名

    float hp; //血量

    float armor; //护甲

    int moveSpeed; //移动速度

    public Parameters(){

    }
    public Parameters(String name,float hp){
        this.name = name;
        this.hp = hp;
    }

    //回血方法
    public void huixue(int xp){
        hp = hp + xp;
        //回血完毕后，血瓶=0
        xp=0;
    }

    // 攻击一个英雄，并让他掉damage点血
    public void attack(Parameters hero, int damage) {
        hero.hp = hero.hp - damage;
    }

    public static void main(String[] args) {
        Parameters teemo =  new Parameters("提莫",383);
        //血瓶，其值是100
        int xueping = 100;

        //提莫通过这个血瓶回血

        teemo.huixue(xueping);

        System.out.println(xueping);


        Parameters teemo1 = new Parameters("提莫", 383);
        Parameters garen = new Parameters("盖伦", 616);
        garen.attack(teemo1, 100);
        System.out.println(teemo.hp);

    }

}
