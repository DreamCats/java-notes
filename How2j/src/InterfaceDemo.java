/**
 * @program JavaBooks
 * @description: 接口
 * @author: mf
 * @create: 2019/04/05 11:28
 */

public class InterfaceDemo {

}

interface AD {
    //物理伤害
    public void physicAttack();
}

interface AP {
    // 魔法伤害
    public void magicAttack();
}



class ADHero extends Hero implements AD {

    @Override
    public void physicAttack() {
        System.out.println("进行物理攻击");
    }
}

class APHero extends Hero implements AP {

    @Override
    public void magicAttack() {
        System.out.println("进行魔法攻击");
    }
}

class APADHero extends Hero implements AD, AP {

    @Override
    public void physicAttack() {
        System.out.println("进行物理攻击");
    }

    @Override
    public void magicAttack() {
        System.out.println("进行魔法攻击");
    }
}



