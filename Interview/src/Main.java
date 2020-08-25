import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> a = new ArrayList<>();
        List<String> b = new ArrayList<>();
        System.out.println(a.getClass() == b.getClass());
    }
}