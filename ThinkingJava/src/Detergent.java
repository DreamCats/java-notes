/**
 * @program JavaBooks
 * @description: 继承语法
 * @author: mf
 * @create: 2019/03/10 10:14
 */

/**
 * Cleanser和detergent都含有main方法，可以为每个类都创建一个main方法
 * 这种在每个类中都设置一个main方法的技术可使每个类的单元测试都变得简便易行。**/

// detergent 继承 cleanser
public class Detergent extends Cleanser{
    // change a method
    public void scrub() {
        append(" Detergent.scrub()");
        super.scrub(); // call base-class version
    }
    // add methods to the interface;
    public void foam() {append(" foam()");}
    // test the new class;
    public static void main(String[] args) {
        Detergent x = new Detergent();
        x.dilute();
        x.apply();
        x.scrub();
        x.foam();
        System.out.println(x);
        System.out.println("Testing base class:");
        Cleanser.main(args);

    }
}

/*Cleanser类*/

class Cleanser {
    private String s = "Cleanser";
    public void append(String a) {s += a;}
    public void dilute() {append(" dilute()");}
    public void apply() {append(" apply()");}
    public void scrub() {append(" scrub()");}
    public String toString() {return s;}
    public static void main(String[] args) {
        Cleanser x = new Cleanser();
        x.dilute();
        x.apply();
        x.scrub();
        System.out.println(x);
    }
}
