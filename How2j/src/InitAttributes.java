/**
 * @program JavaBooks
 * @description: 初始化类属性
 * @author: mf
 * @create: 2019/04/04 16:24
 */

public class InitAttributes {
    public String name = "some hero"; //声明该属性的时候初始化
    protected float hp;
    float maxHP;

    {
        maxHP = 200; //初始化块
    }

    public InitAttributes(){
        hp = 100; //构造方法中初始化

    }


}
