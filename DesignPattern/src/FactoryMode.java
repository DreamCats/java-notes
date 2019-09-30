/**
 * @program JavaBooks
 * @description: 工厂模式
 * @author: mf
 * @create: 2019/09/30 09:59
 */

/*
简单工厂模式
工厂模版模式
抽象工厂模式

说白了， 就是为解耦。。。
 */


// 举个例子
abstract class INoodles {
    /**
     * 描述每种面条长什么样的...
     */
    public abstract void desc();
}

// 先来一份兰州拉面（具体产品类）
class LzNoodles extends INoodles {

    @Override
    public void desc() {
        System.out.println("兰州拉面,成都的好贵 家里的才5-6块钱一碗");
    }
}

// 程序员加班也的来一份泡面哈 （具体产品类）
class PaoNoodles extends INoodles {

    @Override
    public void desc() {
        System.out.println("泡面可还行...");
    }
}

// 家乡的杂酱面，那才叫好吃撒...
class ZaNoodles extends INoodles {

    @Override
    public void desc() {
        System.out.println("杂酱面，嗯？ 真香...");
    }
}

// 开个面馆吧... 做生意
class SimpleNoodlesFactory {
    public static final int TYPE_LZ = 1; // 兰州拉面
    public static final int TYPE_PAO = 2; // 泡面撒
    public static final int TYPE_ZA = 3; // 杂酱面
    // 提供静态方法
    public static INoodles createNoodles(int type) {
        switch (type) {
            case TYPE_LZ:return new LzNoodles();
            case TYPE_PAO:return new PaoNoodles();
            case TYPE_ZA:return new ZaNoodles();
            default:return new ZaNoodles();
        }
    }
}

public class FactoryMode {
    public static void main(String[] args) {
        INoodles noodles = SimpleNoodlesFactory.createNoodles(SimpleNoodlesFactory.TYPE_ZA);
        noodles.desc();
    }
}
