## Shopee的办公室（二）

```java
import java.util.*;
public class Main {
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int y = sc.nextInt();
 
        long[][] map = new long[x + 1][y + 1];
        for (int i = 0; i < map.length; i++)
            map[i][0] = 1;
        Arrays.fill(map[0], 1);
 
        int boss_num = sc.nextInt();
        for (int i = 0; i < boss_num; i++) {
            map[sc.nextInt()][sc.nextInt()] = -1;
        }
 
        Main.path(map);
        System.out.println(map[x][y]);
    }
 
    public static void path(long[][] map) {
        for (int i = 1; i < map.length; i++) {
            for (int j = 1; j < map[0].length; j++) {
                if (map[i][j] == -1)  map[i][j] = 0;
                else map[i][j] = map[i - 1][j] + map[i][j - 1];
            }
        }
    }
}
```

## 建物流中转站


```java
import java.util.*;
public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] nums = new int[n][n];
        ArrayList<int[][]> list = new ArrayList<>();
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                nums[i][j] = sc.nextInt();
                if (nums[i][j] == 1){
                    int[][] xy = new int[1][2];
                    xy[0][0] = i;
                    xy[0][1] = j;
                    list.add(xy);
                }
            }
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                int t = 0;
                if (nums[i][j] == 0){
                    for (int k = 0; k < list.size(); k++){
                        t += Math.abs(list.get(k)[0][0] - i) + Math.abs(list.get(k)[0][1] - j);
                    }
                    res = res > t ? t : res;
                }
            }
        }
        System.out.println(res == Integer.MAX_VALUE ? -1 : res);
    }
}
```