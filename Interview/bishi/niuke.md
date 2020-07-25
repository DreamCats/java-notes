
## [回合制游戏](https://www.nowcoder.com/practice/17a083854661490e85e5bb6c4b26e546?tpId=158&&tqId=34025&rp=1&ru=/ta/exam-pdd&qru=/ta/exam-pdd/question-ranking)

你在玩一个回合制角色扮演的游戏。现在你在准备一个策略，以便在最短的回合内击败敌方角色。在战斗开始时，敌人拥有HP格血量。当血量小于等于0时，敌人死去。一个缺乏经验的玩家可能简单地尝试每个回合都攻击。但是你知道辅助技能的重要性。
在你的每个回合开始时你可以选择以下两个动作之一：聚力或者攻击。
  聚力会提高你下个回合攻击的伤害。
  攻击会对敌人造成一定量的伤害。如果你上个回合使用了聚力，那这次攻击会对敌人造成buffedAttack点伤害。否则，会造成normalAttack点伤害。
给出血量HP和不同攻击的伤害，buffedAttack和normalAttack，返回你能杀死敌人的最小回合数。

**输入描述**：

```html
第一行是一个数字HP
第二行是一个数字normalAttack
第三行是一个数字buffedAttack
1 <= HP,buffedAttack,normalAttack <= 10^9
```

输出描述：

```html
输出一个数字表示最小回合数
```

示例1:

输入

```html
13
3
5
```

输出

```html
5
```

```java
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int hp = sc.nextInt();
        int normal = sc.nextInt();
        int buffed = sc.nextInt();
        int res = 0;
        if (buffed <= 2 * normal) {
            // 说明normal攻击，划算
            System.out.println(hp % normal == 0 ? hp / normal : hp / normal + 1);
        } else {
            int mod = hp % buffed;
            if (mod == 0) {
                // 说明为0，直接除就ok
                System.out.println(hp / buffed * 2);
            } else if (mod <= normal) {
                // 说明余量小于normal，最后一次攻击normal，不需要蓄力
                System.out.println(hp / buffed * 2 + 1);
            } else {
                // 还得蓄力才行
                System.out.println(hp / buffed * 2 + 2);
            }
        }
    }
}
```

## [两两配对差值最小](https://www.nowcoder.com/practice/60594521f1db4d75ad78266b0b35cfbb?tpId=158&&tqId=34024&rp=1&ru=/ta/exam-pdd&qru=/ta/exam-pdd/question-ranking)

```
给定一个长度为偶数的数组arr，将该数组中的数字两两配对并求和，在这些和中选出最大和最小值，请问该如何两两配对，才能让最大值和最小值的差值最小？
```

**输入描述**:

```html
一共2行输入。
第一行为一个整数n，2<=n<=10000, 第二行为n个数，组成目标数组，每个数大于等于2，小于等于100。
```

**输出描述**:

```html
输出最小的差值。
```

```html
4
2 6 4 3
```

```html
1
```

```html
6
11 4 3 5 7 1
```

```html
3
```

```java
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.valueOf(sc.nextLine());
        String[] ss = sc.nextLine().split(" ");
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.valueOf(ss[i]);
        }
        // 排序
        Arrays.sort(nums);
        // 双指针
        int p1 = 0, p2 = nums.length - 1;
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        while (p1 < p2) {
            int sum = nums[p1++] + nums[p2--];
            max = Math.max(max, sum);
            min = Math.min(min, sum);
        }
        System.out.println(max - min);
    }
}
```

## [小熊吃糖](https://www.nowcoder.com/practice/dc49df3bbc0146dd92322889d40afcb1?tpId=158&&tqId=34021&rp=1&ru=/ta/exam-pdd&qru=/ta/exam-pdd/question-ranking)

有n只小熊，他们有着各不相同的战斗力。每次他们吃糖时，会按照战斗力来排，战斗力高的小熊拥有优先选择权。前面的小熊吃饱了，后面的小熊才能吃。每只小熊有一个饥饿值，每次进食的时候，小熊们会选择最大的能填饱自己当前饥饿值的那颗糖来吃，可能吃完没饱会重复上述过程，但不会选择吃撑。

现在给出n只小熊的战斗力和饥饿值，并且给出m颗糖能填饱的饥饿值。

求所有小熊进食完之后，每只小熊剩余的饥饿值。

**输入描述**：

```html
第一行两个正整数n和m，分别表示小熊数量和糖的数量。（n <= 10, m <= 100）
第二行m个正整数，每个表示着颗糖能填充的饥饿值。
接下来的n行，每行2个正整数，分别代表每只小熊的战斗力和当前饥饿值。
题目中所有输入的数值小于等于100。
```

**输出描述**：

```
输出n行，每行一个整数，代表每只小熊剩余的饥饿值。
```

```html
2 5
5 6 10 20 30
4 34
3 35
```

```html
4
0
```

```java
import java.util.*;
public class Main {
    public static void main(String[] args) {
        //内部类
        class Bear {
            int power;
            int hunger;
            Bear(int p, int h) {
                power = p;
                hunger = h;
            }
        }
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        LinkedList<Integer> sugers = new LinkedList<>();
        for (int i = 0; i < m; i++) sugers.add(sc.nextInt());
        // 排序
        sugers.sort((o1, o2) -> (o2 - o1));
        LinkedList<Bear> bears = new LinkedList<>();
        for (int i = 0; i < n; i++) bears.add(new Bear(sc.nextInt(), sc.nextInt()));
        // 备份
        LinkedList<Bear> bearsBackup = new LinkedList<>();
        bearsBackup.addAll(bears);
        // 排序
        bears.sort((o1, o2) -> (o2.power - o1.power));
        for (Bear bear : bears) {
            Iterator<Integer> it = sugers.iterator();
            while (it.hasNext()) {
                int t = it.next();
                if (bear.hunger >= t) {
                    bear.hunger -= t;
                    it.remove();
                }
            }
        }
        for (Bear bear : bearsBackup) {
            System.out.println(bear.hunger);
        }
    }
}
```

## [列表补全](https://www.nowcoder.com/practice/8d677d9c6af643f28c2153ee4e435ea4?tpId=158&&tqId=34017&rp=1&ru=/ta/exam-pdd&qru=/ta/exam-pdd/question-ranking)

在商城的某个位置有一个商品列表，该列表是由L1、L2两个子列表拼接而成。当用户浏览并翻页时，需要从列表L1、L2中获取商品进行展示。展示规则如下：

1. 用户可以进行多次翻页，用offset表示用户在之前页面已经浏览的商品数量，比如offset为4，表示用户已经看了4个商品

2. n表示当前页面需要展示的商品数量

3. 展示商品时首先使用列表L1，如果列表L1长度不够，再从列表L2中选取商品

4. 从列表L2中补全商品时，也可能存在数量不足的情况

请根据上述规则，计算列表L1和L2中哪些商品在当前页面被展示了

**输入描述**：

```html
每个测试输入包含1个测试用例，包含四个整数，分别表示偏移量offset、元素数量n，列表L1的长度l1，列表L2的长度l2。
```

**输出描述**：

```html
在一行内输出四个整数分别表示L1和L2的区间start1，end1，start2，end2，每个数字之间有一个空格。
注意，区间段使用半开半闭区间表示，即包含起点，不包含终点。如果某个列表的区间为空，使用[0, 0)表示，如果某个列表被跳过，使用[len, len)表示，len表示列表的长度。
```

**输入**：

```html
2 4 4 4
1 2 4 4
4 1 3 3
```

**输出**：

```html
2 4 0 2
1 3 0 0
3 3 1 2
```

```java
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()) {
            int offset = sc.nextInt();
            int n = sc.nextInt();
            int l1 = sc.nextInt();
            int l2 = sc.nextInt();
            int start1 = 0, end1 = l1, start2 = 0, end2 = l2;
            if (offset >= l1 + l2) {
                start1 = l1;
                end1 = l1;
                start2 = l2;
                end2 = l2;
            } else {
                if (offset >= l1) {
                    start1 = l1;
                    end1 = l1;
                    start2 = offset - l1;
                    if (offset + n > l1 + l2) {
                        end2 = l2;
                    } else {
                        end2 = offset + n - l1;
                    }
                } else {
                    start1 = offset;
                    if (offset + n  > l1) {
                        end1 = l1;
                        start2 = 0;
                        end2 = offset + n - l1;
                    } else {
                        end1 = offset + n;
                        start2 = 0;
                        end2 = 0;
                    }
                }
            }
            System.out.println(start1 + " " + end1 + " " + start2 + " " + end2);
        }
    }
}
```

## [种树](https://www.nowcoder.com/practice/52f25c8a8d414f8f8fe46d4e62ef732c?tpId=158&&tqId=34023&rp=1&ru=/ta/exam-pdd&qru=/ta/exam-pdd/question-ranking)

```
小多想在美化一下自己的庄园。他的庄园毗邻一条小河，他希望在河边种一排树，共 M 棵。小多采购了 N 个品种的树，每个品种的数量是 Ai (树的总数量恰好为 M)。但是他希望任意两棵相邻的树不是同一品种的。小多请你帮忙设计一种满足要求的种树方案。
```

**输入描述**：

```
第一行包含一个正整数 N，表示树的品种数量。
第二行包含 N 个正整数，第 i (1 <= i <= N) 个数表示第 i 个品种的树的数量。
数据范围：
1 <= N <= 1000
1 <= M <= 2000
```

**输出描述**：

```
输出一行，包含 M 个正整数，分别表示第 i 棵树的品种编号 (品种编号从1到 N)。若存在多种可行方案，则输出字典序最小的方案。若不存在满足条件的方案，则输出"-"。
```

```html
3
4 2 1
```

```html
1 2 1 2 1 3 1
```

```java
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        int total = 0;
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
            total += nums[i];
        }
        int[] curr = new int[total];
        if (!dfs(curr, 0, nums, -1)) {
            System.out.println("-");
        }
    }
    
    private static boolean dfs(int[] curr, int index, int[] nums, int last) {
        int rest = curr.length - index;
        if (rest == 0) {
            for (int i : curr) System.out.print(i + 1 + " ");
            return true;
        }
        for (int tree : nums) {
            if (tree > (rest + 1) / 2) return false;
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0 && i != last) {
                int[] temp = new int[nums.length];
                System.arraycopy(nums, 0, temp, 0, nums.length);
                temp[i]--;
                int[] currTemp = new int[curr.length];
                System.arraycopy(curr, 0, currTemp, 0, curr.length);
                currTemp[index] = i;
                if (dfs(currTemp, index + 1, temp, i)) return true;
            }
        }
        return false;
    }
}
```




## Shopee的办公室（二）
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

## 建物流中转站
Shopee物流会有很多个中转站。在选址的过程中，会选择离用户最近的地方建一个物流中转站。

假设给你一个二维平面网格，每个格子是房子则为1，或者是空地则为0。找到一个空地修建一个物流中转站，使得这个物流中转站到所有的房子的距离之和最小。 能修建，则返回最小的距离和。如果无法修建，则返回 -1。


若范围限制在100*100以内的网格，如何计算出最小的距离和？

当平面网格非常大的情况下，如何避免不必要的计算？

```html
4
0 1 1 0
1 1 0 1
0 0 1 0
0 0 0 0
```

```html
8

能修建，则返回最小的距离和。如果无法修建，则返回 -1。
```

```html
4
1 1 1 1
1 1 1 1
1 1 1 1
1 1 1 1
```

```html
-1
```

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


## 回文数索引
[https://www.nowcoder.com/practice/b6edb5ca15d34b1eb42e4725a3c68eba?tpId=182&&tqId=34896&rp=1&ru=/ta/exam-all&qru=/ta/exam-all/question-ranking](https://www.nowcoder.com/practice/b6edb5ca15d34b1eb42e4725a3c68eba?tpId=182&&tqId=34896&rp=1&ru=/ta/exam-all&qru=/ta/exam-all/question-ranking)

给定一个仅由小写字母组成的字符串。现在请找出一个位置，删掉那个字母之后，字符串变成回文。请放心总会有一个合法的解。如果给定的字符串已经是一个回文串，那么输出-1。

输入：
```html
第一行包含T，测试数据的组数。后面跟有T行，每行包含一个字符串。
```

输出：
```html
如果可以删去一个字母使它变成回文串，则输出任意一个满足条件的删去字母的位置（下标从0开始）。例如：

bcc

我们可以删掉位置0的b字符。
```

```html
3
aaab
baa
aaa
```
```html
3
0
-1
```

[https://leetcode-cn.com/problems/valid-palindrome-ii/](https://leetcode-cn.com/problems/valid-palindrome-ii/)
```java
import java.util.*;

public class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        for (int k = 0; k < n; k++) {
            String s = sc.nextLine();
            // 双指针
            boolean flag = true;
            int i = 0, j = s.length() - 1;
            while(i < j){
                if (s.charAt(i) != s.charAt(j)){
                    if(isVaild(s, i, j - 1)){
                        System.out.println(j);
                        flag = false;
                        break;
                    } else if(isVaild(s, i + 1, j)){
                        System.out.println(i);
                        flag = false;
                        break;
                    }
                }
                i++;
                j--;
            }
            if (flag)
                System.out.println(-1);
        }
    }
    
    public static boolean isVaild(String s, int i, int j){
        while (i < j){
            if (s.charAt(i) != s.charAt(j))
                return false;
            i++;
            j--;
        }
        return true;
    }
}
```

## 字符串替换
[https://www.nowcoder.com/practice/f409e49e3f3e4b68819ffceb50df7df5?tpId=182&&tqId=34891&rp=1&ru=/ta/exam-all&qru=/ta/exam-all/question-ranking](https://www.nowcoder.com/practice/f409e49e3f3e4b68819ffceb50df7df5?tpId=182&&tqId=34891&rp=1&ru=/ta/exam-all&qru=/ta/exam-all/question-ranking)

```java
import java.util.*;
public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int cnt = 0; // y cnt
        int res = 0; // res
        for (int i = s.length() - 1; i >= 0; i--){
            if (s.charAt(i) == 'y')
                cnt++;
            if (s.charAt(i) == 'x'){
                res = (res + cnt) % 1000000007;
                cnt = (cnt * 2) % 1000000007;
            }
        }
        System.out.println(res % 1000000007);
        
    }
}
```

## 商品交易
[https://www.nowcoder.com/practice/ce9d7cdac6e34e42919e787a8baf8a2b?tpId=182&&tqId=34889&rp=1&ru=/ta/exam-all&qru=/ta/exam-all/question-ranking](https://www.nowcoder.com/practice/ce9d7cdac6e34e42919e787a8baf8a2b?tpId=182&&tqId=34889&rp=1&ru=/ta/exam-all&qru=/ta/exam-all/question-ranking)

```java
import java.util.*;
public class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[] nums = new long[n];
        for (int i = 0; i < n; i++)
            nums[i] = sc.nextInt();
        long max = 0;
        long cnt = 0, deal = 0;
        for(int i = 1; i < n; i++){
            if (nums[i] > nums[i - 1]){
                max += nums[i] - nums[i - 1];
                if (deal == 0)
                    cnt++;
                deal = 1;
            }
            if (nums[i] < nums[i - 1]){
                cnt += deal;
                deal = 0;
            }
        }
        System.out.println(max + " " + cnt + deal);
    }
}
```

## 火车站台

[https://www.nowcoder.com/practice/bade66d32ad8479fbcecc002ea983ff0?tpId=182&&tqId=34887&rp=1&ru=/ta/exam-all&qru=/ta/exam-all/question-ranking](https://www.nowcoder.com/practice/bade66d32ad8479fbcecc002ea983ff0?tpId=182&&tqId=34887&rp=1&ru=/ta/exam-all&qru=/ta/exam-all/question-ranking)

```java
import java.util.*;
public class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[100001];
        for (int i = 0; i < n; i++){
            int x = sc.nextInt();
            int y = sc.nextInt();
            nums[x]++;
            nums[y]--;
        }
        int cnt = 0;
        int res = 0;
        for (int i = 1; i < nums.length; i++){
            cnt += nums[i];
            res = Math.max(res, cnt);
        }
        System.out.println(res);
    }
}
```

## 非整数集合

[https://www.nowcoder.com/practice/361ff5dd893c4e11856735e52007fca7?tpId=182&&tqId=34894&rp=1&ru=/ta/exam-all&qru=/ta/exam-all/question-ranking](https://www.nowcoder.com/practice/361ff5dd893c4e11856735e52007fca7?tpId=182&&tqId=34894&rp=1&ru=/ta/exam-all&qru=/ta/exam-all/question-ranking)


链接：https://www.nowcoder.com/questionTerminal/361ff5dd893c4e11856735e52007fca7?f=discussion
来源：牛客网

先说一下例子里面的「19，22，25」是能被4整除的，应该是题目出错了！
思路很简单，直接求余数，假如按例子「10，10，12，19，22，24，25」看求4的余数可以知道：[2,2,0,3,2,0,1]，统计起来就是2个0，1个1，3个2，1个3。
0这里，最多只能有一个，比如两个被4整除的数肯定加起来还能被4整除，接下来就是1和3了，这两个哪个多就选哪个，因为1和3任选一个加起来肯定就能被4整除，所以就选最多的那个，另一个不选。接下来就是最中间那个，这里就是2了，它也最多只能选1个，因为两个求余为2的加起来也能被4整除，所以这里特殊的就是0和中间那个，存在就加1，其他的是选两个里面最大的那个

```java
import java.util.*; 
public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] nums = new int[k];
        for (int i = 0; i < n; i++)
            nums[(sc.nextInt()) % k]++;
        
        // 
        int sum = nums[0] > 0 ? 1 : 0;
        for (int i = 1, j = k - 1; i <= j; i++, j--){
            sum += (i == j)
                ? (nums[i] >= 1 ? 1 : 0) // 中间这个数，取一个，取多加起来会被整除的
                : Math.max(nums[i], nums[j]);
        }
        System.out.println(sum);
    }
}
```

## 0/1背包

[https://www.nowcoder.com/questionTerminal/7e157ce9a8c249daa3ddafad322dbf1e?answerType=1&f=discussion](https://www.nowcoder.com/questionTerminal/7e157ce9a8c249daa3ddafad322dbf1e?answerType=1&f=discussion)

```java
import java.util.*;
public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int W = sc.nextInt();
        int[] ws = new int[n];
        int[] vals = new int[n];
        for (int i = 0; i < n; i++){
            ws[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++){
            vals[i] = sc.nextInt();
        }
        int[] dp = new int[W + 1];
        for (int i = 1; i <= n; i++){
            int w = ws[i - 1], v = vals[i - 1];
            for (int j = W; j >=0; j--){
                if (j >= w){
                    dp[j] = Math.max(dp[j], dp[j - w] + v);
                }
            }
        }
        System.out.println(dp[W]);
    }
}
```

## 模数求和
[https://www.nowcoder.com/practice/34dc8aef8295470ea536f1c9255fef7e?tpId=122&&tqId=33727&rp=1&ru=/ta/exam-wangyi&qru=/ta/exam-wangyi/question-ranking](https://www.nowcoder.com/practice/34dc8aef8295470ea536f1c9255fef7e?tpId=122&&tqId=33727&rp=1&ru=/ta/exam-wangyi&qru=/ta/exam-wangyi/question-ranking)
```java
import java.util.*;
public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += sc.nextInt() - 1;
        }
        System.out.println(sum);
    }
}
```

## 美妙的约会

[https://www.nowcoder.com/practice/cc3eef5aed91489f9b706f4196e0d5c6?tpId=122&&tqId=33726&rp=1&ru=/ta/exam-wangyi&qru=/ta/exam-wangyi/question-ranking](https://www.nowcoder.com/practice/cc3eef5aed91489f9b706f4196e0d5c6?tpId=122&&tqId=33726&rp=1&ru=/ta/exam-wangyi&qru=/ta/exam-wangyi/question-ranking)

```java
import java.util.*;
public class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = n * 2;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < m; i++)
            list.add(sc.nextInt());
        int idx = 0;
        int sum = 0;
        while (idx < list.size()){
            int lastIdx = list.lastIndexOf(list.get(idx));
            sum += (lastIdx - idx - 1);
            list.remove(lastIdx);
            idx++;
        }
        System.out.println(sum);
    }
}
```

## 字母卡片

[https://www.nowcoder.com/practice/9369f06924fa44a4ba2c462504c53297?tpId=122&&tqId=33722&rp=1&ru=/ta/exam-wangyi&qru=/ta/exam-wangyi/question-ranking](https://www.nowcoder.com/practice/9369f06924fa44a4ba2c462504c53297?tpId=122&&tqId=33722&rp=1&ru=/ta/exam-wangyi&qru=/ta/exam-wangyi/question-ranking)

```java
import java.util.*;
public class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            int n = sc.nextInt();
            int m = sc.nextInt();
            sc.nextLine();
            int[] a = new int[26];
            String s = sc.nextLine();
            for(int i = 0; i < n; i++){
                a[s.charAt(i) - 'A']++;
            }
            Arrays.sort(a);
            long sum = 0L;
            int cnt = 0;
            for (int i = a.length - 1; i >=0; i--){
                if (cnt + a[i] > m)
                    continue;
                cnt += a[i];
                sum += (long)a[i] * (long)a[i];
            }
            sum = cnt == m ? sum : sum + (m - cnt) * (m - cnt);
            System.out.println(sum);
        }
    }
}
```

## 分贝壳

[https://www.nowcoder.com/practice/9b59014cc1544aeeb4082f5f37ecfaea?tpId=122&&tqId=33725&rp=1&ru=/ta/exam-wangyi&qru=/ta/exam-wangyi/question-ranking](https://www.nowcoder.com/practice/9b59014cc1544aeeb4082f5f37ecfaea?tpId=122&&tqId=33725&rp=1&ru=/ta/exam-wangyi&qru=/ta/exam-wangyi/question-ranking)

```java
import java.util.*;
public class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        long start = 1;
        long end = n;
        long temp = 0;
        // 二分法
        while (start < end){
            long mid = start + (end - start) / 2;
            if (minNum(mid, n)){
                temp = mid;
                end = mid;
            } else{
                start = mid + 1;
            }
        }
        System.out.println(temp);
    }
    public static boolean minNum(long m, long n){
        long nums1 = 0;
        long temp = n;
        long mid = 0;
        while (temp >= 0){
            if (temp < m){
                nums1 += temp;
                break;
            }
            nums1 += m;
            temp -= m;
            temp -= temp / 10;
        }
        mid = n % 2 == 0 ? n / 2 : (n + 1) / 2;
        return nums1 >= mid ? true : false;
    }
}
```

## 橡皮泥斑马

[https://www.nowcoder.com/practice/0277b16d84ae42888b0c80fe4e316968?tpId=122&&tqId=33720&rp=1&ru=/ta/exam-wangyi&qru=/ta/exam-wangyi/question-ranking](https://www.nowcoder.com/practice/0277b16d84ae42888b0c80fe4e316968?tpId=122&&tqId=33720&rp=1&ru=/ta/exam-wangyi&qru=/ta/exam-wangyi/question-ranking)

```java
import java.util.*;
public class Main{
    public static void main(String[] args){
        // 任意位置翻转，形成环
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        s += s;
        int cur = 1, max = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) != s.charAt(i + 1)){
                cur++;
            } else{
                max = Math.max(max, cur);
                cur = 1;
            }
        }
        if (cur == s.length())
            System.out.println(max / 2);
        else
            System.out.println(max);
    }
}
```

## 买房

[https://www.nowcoder.com/practice/edf9346066f047a9833b3284798d6c29?tpId=122&&tqId=33717&rp=1&ru=/ta/exam-wangyi&qru=/ta/exam-wangyi/question-ranking](https://www.nowcoder.com/practice/edf9346066f047a9833b3284798d6c29?tpId=122&&tqId=33717&rp=1&ru=/ta/exam-wangyi&qru=/ta/exam-wangyi/question-ranking)

```java
import java.util.*;
public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        sc.nextInt();
        while (sc.hasNext()){
            int n = sc.nextInt();
            int k = sc.nextInt();
            int ans = 0;
            // 模拟
            if (n < 3 || k == n || k < 1)
                ans = 0;
            else {
                ans = Math.min(n - k, k - 1);
            }
            System.out.println(0 + " " + ans);
        }
    } 
}
```

## 访友

[https://www.nowcoder.com/practice/b8e21f5816874425836b7d32011f46b0?tpId=122&&tqId=33715&rp=1&ru=/ta/exam-wangyi&qru=/ta/exam-wangyi/question-ranking](https://www.nowcoder.com/practice/b8e21f5816874425836b7d32011f46b0?tpId=122&&tqId=33715&rp=1&ru=/ta/exam-wangyi&qru=/ta/exam-wangyi/question-ranking)

```java
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if (n <= 5)
            System.out.println(1);
        else {
            int cnt = 0;
            int t = 5;
            while(n != 0 && t > 0){
                int tmp = n / t;
                n = n - tmp * t;
                t--;
                cnt += tmp;
            }
            System.out.println(cnt);
        }

    }
}
```

## 塔

[https://www.nowcoder.com/practice/54868056c5664586b121d9098d008719?tpId=122&&tqId=33712&rp=1&ru=/ta/exam-wangyi&qru=/ta/exam-wangyi/question-ranking](https://www.nowcoder.com/practice/54868056c5664586b121d9098d008719?tpId=122&&tqId=33712&rp=1&ru=/ta/exam-wangyi&qru=/ta/exam-wangyi/question-ranking)

```java
import java.util.*;
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

```

## 瞌睡

[https://www.nowcoder.com/practice/93f2c11daeaf45959bb47e7894047085?tpId=122&&tqId=33708&rp=1&ru=/ta/exam-wangyi&qru=/ta/exam-wangyi/question-ranking](https://www.nowcoder.com/practice/93f2c11daeaf45959bb47e7894047085?tpId=122&&tqId=33708&rp=1&ru=/ta/exam-wangyi&qru=/ta/exam-wangyi/question-ranking)

```java
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] nums1 = new int[n];
        int[] nums2 = new int[n];
        int[] sumInterest = new int[n];
        for (int i = 0; i < n; i++) {
            nums1[i] = sc.nextInt();
        }
        int sum0 = 0;
        int sum1 = 0;
        for (int i = 0; i < n; i++) {
            nums2[i] = sc.nextInt();
            if (nums2[i] == 1)
                sum0 += nums1[i];
            else
                sum1 += nums1[i];
            sumInterest[i] = sum1;
        }
        int cur = 0;
        int max = 0;
        for (int i = 0; i < n; i++) {
            if (nums2[i] == 0){
                if (i + k - 1 <= n - 1) {
                    cur = sumInterest[i + k - 1] - (i - 1 > 0 ? sumInterest[i - 1] : 0);
                } else {
                    cur = sumInterest[n - 1] - (i - 1 > 0 ? sumInterest[i - 1] : 0);
                }
            }
            max = Math.max(cur, max);
        }
        System.out.println(max + sum0);
    }
}

```

## 相等序列

[https://www.nowcoder.com/practice/7492dceb022a4bbebb990695c107823e?tpId=122&&tqId=33723&rp=1&ru=/ta/exam-wangyi&qru=/ta/exam-wangyi/question-ranking](https://www.nowcoder.com/practice/7492dceb022a4bbebb990695c107823e?tpId=122&&tqId=33723&rp=1&ru=/ta/exam-wangyi&qru=/ta/exam-wangyi/question-ranking)

```java
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        while (k-- > 0) {
            int n = sc.nextInt();
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = sc.nextInt();
            }
            System.out.println(eql(nums));
        }
    }

    public static String eql(int[] arr){
        Arrays.sort(arr);
        int a = arr[0];
        int b = arr[arr.length-1];
        if(arr.length == 2){
            return "YES";
        }
        if((a+b) % 2 != 0){
            return "NO";
        }
        int mid = (a+b)/2;
        int offset = b - mid;
        for(int i = 0;i < arr.length;i++){
            if(!(arr[i] + offset == mid || arr[i] - offset == mid || arr[i] == mid)){
                return "NO";
            }
        }
        return "YES";
    }
}

```

## 代价

[https://www.nowcoder.com/practice/b7985769dc434d85a16717908669bcab?tpId=122&&tqId=33714&rp=1&ru=/ta/exam-wangyi&qru=/ta/exam-wangyi/question-ranking](https://www.nowcoder.com/practice/b7985769dc434d85a16717908669bcab?tpId=122&&tqId=33714&rp=1&ru=/ta/exam-wangyi&qru=/ta/exam-wangyi/question-ranking)

```java
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = 3;
        int i = 0;
        Integer[] nums = new Integer[n];
        while (n-- > 0){
            nums[i++] = sc.nextInt();
        }
        Arrays.sort(nums, (o1, o2) -> o2 - o1);
        System.out.println((nums[0] - nums[1]) + (nums[1] - nums[2]));
    }
}

```

## 俄罗斯方块

[https://www.nowcoder.com/practice/9407e24a70b04fedba4ab3bd3ae29704?tpId=122&&tqId=33707&rp=1&ru=/ta/exam-wangyi&qru=/ta/exam-wangyi/question-ranking](https://www.nowcoder.com/practice/9407e24a70b04fedba4ab3bd3ae29704?tpId=122&&tqId=33707&rp=1&ru=/ta/exam-wangyi&qru=/ta/exam-wangyi/question-ranking)

```java
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        int n = sc.nextInt();
        int[] a = new int[k + 1];
        for (int i = 0; i < n; i++) {
            a[sc.nextInt()]++;
        }
        Arrays.sort(a);
        if (a[1] == 0)
            System.out.println(0);
        else
            System.out.println(a[1]);
    }
}
```

## 一封奇怪的信

[https://www.nowcoder.com/practice/d7764905e41a413c98900e22a9cc4ec3?tpId=122&&tqId=33699&rp=1&ru=/ta/exam-wangyi&qru=/ta/exam-wangyi/question-ranking](https://www.nowcoder.com/practice/d7764905e41a413c98900e22a9cc4ec3?tpId=122&&tqId=33699&rp=1&ru=/ta/exam-wangyi&qru=/ta/exam-wangyi/question-ranking)

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] a = new int[26];
        for (int i = 0; i < 26; i++) {
            a[i] = sc.nextInt();
        }
        sc.nextLine();
        String s = sc.nextLine();
        int cnt = 1;
        int tmp = 100;
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i) - 'a';
            int w = a[c];
            sum += w;
            if (sum >= tmp){
                sum = sum == tmp ? 0 : w;
                cnt++;
            }
        }
        System.out.println(cnt + " " + sum);
    }
}

```

## 计算糖果

[https://www.nowcoder.com/practice/02d8d42b197646a5bbd0a98785bb3a34?tpId=122&&tqId=33679&rp=1&ru=/ta/exam-wangyi&qru=/ta/exam-wangyi/question-ranking](https://www.nowcoder.com/practice/02d8d42b197646a5bbd0a98785bb3a34?tpId=122&&tqId=33679&rp=1&ru=/ta/exam-wangyi&qru=/ta/exam-wangyi/question-ranking)

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = 0, b = 0, c = 0, d = 0;
        int[] nums = new int[4];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = sc.nextInt();
        }
        a = (nums[0] + nums[2]) / 2;
        b = (nums[1] + nums[3]) / 2;
        c = nums[3] - b;
        if (b - c != nums[1])
            System.out.println("No");
        else if (a - b != nums[0])
            System.out.println("No");
        else if (a + b != nums[2])
            System.out.println("No");
        else
            System.out.println(a + " " + b + " " + c);
    }
}

```

## 买苹果

[https://www.nowcoder.com/practice/61cfbb2e62104bc8aa3da5d44d38a6ef?tpId=122&&tqId=33678&rp=1&ru=/ta/exam-wangyi&qru=/ta/exam-wangyi/question-ranking](https://www.nowcoder.com/practice/61cfbb2e62104bc8aa3da5d44d38a6ef?tpId=122&&tqId=33678&rp=1&ru=/ta/exam-wangyi&qru=/ta/exam-wangyi/question-ranking)

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if (n % 8 == 0)
            System.out.println(n / 8);
        else {
            int t = n / 8;
            if (t == 0 && n % 6 == 0)
                System.out.println(n / 6);
            else {
                n -= (t - 1) * 8;
                if (n % 6 == 0)
                    System.out.println(t - 1 + (n / 6));
                else
                    System.out.println(-1);
            }
        }
    }
}

```

## 数字翻转

[https://www.nowcoder.com/practice/bc62febdd1034a73a62224affe8bddf2?tpId=122&&tqId=33676&rp=1&ru=/ta/exam-wangyi&qru=/ta/exam-wangyi/question-ranking](https://www.nowcoder.com/practice/bc62febdd1034a73a62224affe8bddf2?tpId=122&&tqId=33676&rp=1&ru=/ta/exam-wangyi&qru=/ta/exam-wangyi/question-ranking)

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int y = sc.nextInt();

        System.out.println(reverse(reverse(x) + reverse(y)));
    }

    public static int reverse(int x) {
        int res = 0;
        while (x != 0){
            res = res * 10 + x % 10;
            x /= 10;
        }
        return res;
    }
}
```

## 暗黑的字符串

[https://www.nowcoder.com/practice/7e7ccd30004347e89490fefeb2190ad2?tpId=122&&tqId=33675&rp=1&ru=/ta/exam-wangyi&qru=/ta/exam-wangyi/question-ranking](https://www.nowcoder.com/practice/7e7ccd30004347e89490fefeb2190ad2?tpId=122&&tqId=33675&rp=1&ru=/ta/exam-wangyi&qru=/ta/exam-wangyi/question-ranking)

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        long[] dp = new long[x + 1];
        dp[1] = 3;
        dp[2] = 9;
        for (int i = 3; i <= x; i++) {
            dp[i] = 2 * dp[i - 1] + dp[i - 2]; // 把状态方程给忘了...可惜了
        }
        System.out.println(dp[x]);
    }
}
```