/**
 * @program JavaBooks
 * @description: 牛客
 * @author: mf
 * @create: 2020/07/24 22:24
 */

package Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        ArrayList<Integer> towers = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            towers.add(sc.nextInt());
        }
        int cnt = 0;
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        int max = Collections.max(towers);
        int min = Collections.min(towers);
        while (max - min > 1 && cnt < k){
            max = Collections.max(towers); min = Collections.min(towers);
            list1.add(towers.indexOf(max) + 1); list2.add(towers.indexOf(min) + 1);
            towers.set(towers.indexOf(min), min + 1);
            towers.set(towers.indexOf(max), max - 1);
            cnt++;
        }
        System.out.println(Collections.max(towers) - Collections.min(towers) + " " + cnt);
        for (int i = 0; i < list1.size(); i++) {
            System.out.println(list1.get(i) + " " + list2.get(i));
        }
    }
}
