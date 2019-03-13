import java.util.ArrayList;

/**
 * @program JavaBooks
 * @description: ArrayList 泛型
 * @author: mf
 * @create: 2019/03/13 22:02
 */

public class ApplesAndOranges {
    public static void main(String[] args){
        ArrayList<Apple> apples = new ArrayList<Apple>();
        for (int i = 0; i < 3 ; i++) {
            apples.add(new Apple());
        }
        System.out.println("fori");
        for (int i = 0; i < apples.size(); i++) {
            System.out.println(apples.get(i).id());
        }
        System.out.println("-------");
        for (Apple c : apples) {
            System.out.println(c.id());
        }
    }
}

