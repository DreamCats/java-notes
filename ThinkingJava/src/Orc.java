/**
 * @program JavaBooks
 * @description: protected关键字
 * @author: mf
 * @create: 2019/03/10 10:19
 */

public class Orc extends Villain{
    private int ocrNumber;
    public Orc(String name, int orcNumber) {
        super(name);
        this.ocrNumber = orcNumber;
    }
    public void change(String name, int orcNumber) {
        set(name);
        this.ocrNumber = orcNumber;
    }
    public String toString() {
        return "Orc " + ocrNumber + ": " + super.toString();
    }
    public static void main(String[] args) {
        Orc orc = new Orc("Limburger", 12);
        System.out.println(orc);
        orc.change("Bob", 19);
        System.out.println(orc);
    }
}

class Villain {
    private String name;
    protected void set(String nm) {name = nm;}
    public Villain(String name) { this.name = name;}
    public String toString() {
        return "i am Villain and my name is " + name;
    }
}
