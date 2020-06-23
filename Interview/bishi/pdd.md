## PDD笔试题

### 1、[回合制游戏](https://www.nowcoder.com/practice/17a083854661490e85e5bb6c4b26e546?tpId=158&&tqId=34025&rp=1&ru=/ta/exam-pdd&qru=/ta/exam-pdd/question-ranking)

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

### 2、[两两配对差值最小](https://www.nowcoder.com/practice/60594521f1db4d75ad78266b0b35cfbb?tpId=158&&tqId=34024&rp=1&ru=/ta/exam-pdd&qru=/ta/exam-pdd/question-ranking)

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

### 3、[小熊吃糖](https://www.nowcoder.com/practice/dc49df3bbc0146dd92322889d40afcb1?tpId=158&&tqId=34021&rp=1&ru=/ta/exam-pdd&qru=/ta/exam-pdd/question-ranking)

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

### 4、[列表补全](https://www.nowcoder.com/practice/8d677d9c6af643f28c2153ee4e435ea4?tpId=158&&tqId=34017&rp=1&ru=/ta/exam-pdd&qru=/ta/exam-pdd/question-ranking)

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

