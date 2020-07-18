## Shopee笔试题

### 1. Shopee的办公室（二）
shopee的办公室非常大，小虾同学的位置坐落在右上角，而大门却在左下角，可以把所有位置抽象为一个网格（门口的坐标为0，0），小虾同学很聪明，每次只向上，或者向右走，因为这样最容易接近目的地，但是小虾同学不想让自己的boss们看到自己经常在他们面前出没，或者迟到被发现。他决定研究一下如果他不通过boss们的位置，他可以有多少种走法？

```html
第一行 x,y,n (0<x<=30, 0<y<=30, 0<=n<= 20) 表示x,y小虾的座位坐标,n 表示boss的数量（ n <= 20）

接下来有n行, 表示boss们的坐标(0<xi<= x, 0<yi<=y，不会和小虾位置重合)

x1, y1

x2, y2

……

xn, yn
```
```html
输出小虾有多少种走法
```

```html
3 3 2
1 1
2 2
```

```html
4
```

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