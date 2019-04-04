/**
 * @program JavaBooks
 * @description: 枚举
 * @author: mf
 * @create: 2019/04/04 16:47
 */

public class Enumerate {
    // 枚举enum是一种特殊的类(还是类)，使用枚举可以很方便的定义常量

    //使用枚举，就能把范围死死的

    public static void main(String[] args) {
        Season season = Season.AUTUMN;
        switch (season) {
            case SPRING:
                System.out.println("春天");
                break;
            case SUMMER:
                System.out.println("夏天");
                break;
            case AUTUMN:
                System.out.println("秋天");
                break;
            case WINTER:
                System.out.println("冬天");
                break;
        }


        // 遍历枚举
        for (Season s : Season.values()) {
            System.out.println(s);
        }
    }
}


enum Season {
    SPRING,SUMMER,AUTUMN,WINTER
}