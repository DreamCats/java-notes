/**
 * @program JavaBooks
 * @description: 牛客
 * @author: mf
 * @create: 2020/07/24 22:24
 */

package Test;


import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        System.out.println(minSkip(n, m));
    }

    public static int minSkip(int n, int m) {
        if (n == m)
            return 0;
        Map<Integer, Integer> map = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();
        map.put(n, 0);
        queue.add(n);
        while (!queue.isEmpty()) {
            int num = queue.poll();
            if (num == m)
                return map.get(num);
            if (num > m)
                continue;
            HashSet<Integer> set = new HashSet<>();
            yueShu(num, set);
            for (Integer item : set) {
                if (!map.containsKey(num + item)) {
                    map.put(num + item, map.get(num) + 1);
                    queue.add(num + item);
                }
            }
        }
        return -1;
    }

    private static void yueShu(int num, HashSet<Integer> set) {
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0){
                set.add(i);
                set.add(num / i);
            }
        }
    }
}

