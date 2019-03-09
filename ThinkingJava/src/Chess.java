/**
 * @program JavaBooks
 * @description: 带参数的构造器
 * @author: mf
 * @create: 2019/03/09 13:54
 */

public class Chess extends BoardGame{
    Chess() {
        super(11);
        System.out.println("Chess constructor");
    }
    public static void main(String[] args) {
        Chess x = new Chess();
    }
}


class Game {
    Game(int i) {
        System.out.println("Game constructor");
    }
}

class BoardGame extends Game {
    BoardGame(int i) {
        super(i);
        System.out.println("BoardGame constructor");
    }
}