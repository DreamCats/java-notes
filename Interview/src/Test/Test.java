package Test;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(2);
        int[] array = list.stream().mapToInt(Integer::valueOf).toArray();

    }
}