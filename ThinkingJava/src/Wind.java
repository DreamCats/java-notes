/**
 * @program JavaBooks
 * @description: 向上转型
 * @author: mf
 * @create: 2019/03/10 11:06
 */

// Wind objects are instruments
// because they have the same interface

public class Wind extends Instrument{
    public static void main(String[] args) {
        Wind flute = new Wind();
        Instrument.tune(flute);
    }
}



class Instrument {
    public void play() {}
    static void tune(Instrument i) {
        i.play();
    }
}

// 导出类是基类的超集。 它可能比基类含有更多的方法，但它必须至少具备基类中所含有的方法。
