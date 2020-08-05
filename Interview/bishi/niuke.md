
## [回合制游戏](https://www.nowcoder.com/practice/17a083854661490e85e5bb6c4b26e546?tpId=158&&tqId=34025&rp=1&ru=/ta/exam-pdd&qru=/ta/exam-pdd/question-ranking)

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


## 回文数索引
[https://www.nowcoder.com/practice/b6edb5ca15d34b1eb42e4725a3c68eba?tpId=182&&tqId=34896&rp=1&ru=/ta/exam-all&qru=/ta/exam-all/question-ranking](https://www.nowcoder.com/practice/b6edb5ca15d34b1eb42e4725a3c68eba?tpId=182&&tqId=34896&rp=1&ru=/ta/exam-all&qru=/ta/exam-all/question-ranking)



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

## 优雅的点

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int x = (int) Math.sqrt(num);
        int l = 0, r = x;
        int sum = 0;
        while (l < r) {
            if (l == 0 && r * r == num){
                sum += 4;
                l++;
                r--;
            }
            else if ((l * l + r * r) == num) {
                sum += 8;
                l++;
                r--;
            }
            else if ((l * l + r * r) < num)
                l++;
            else
                r--;
        }
        if (l == r && l * l + r * r == num)
            System.out.println(sum += 4);
        else
            System.out.println(sum);
    }
}

```

## 回文序列

[https://www.nowcoder.com/practice/0147cbd790724bc9ae0b779aaf7c5b50?tpId=122&&tqId=33672&rp=1&ru=/ta/exam-wangyi&qru=/ta/exam-wangyi/question-ranking](https://www.nowcoder.com/practice/0147cbd790724bc9ae0b779aaf7c5b50?tpId=122&&tqId=33672&rp=1&ru=/ta/exam-wangyi&qru=/ta/exam-wangyi/question-ranking)

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        int l = 0, r = n - 1;
        int cnt = 0;
        while (l < r){
            if (a[l] == a[r]){
                l++;
                r--;
            } else if (a[l] < a[r]){
                a[l + 1] = a[l] + a[l + 1];
                l++;
                cnt++;
            } else {
                a[r - 1] = a[r] + a[r - 1];
                r--;
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}

```

## 数字游戏

[https://www.nowcoder.com/practice/876e3c5fcfa5469f8376370d5de87c06?tpId=122&&tqId=33669&rp=1&ru=/ta/exam-wangyi&qru=/ta/exam-wangyi/question-ranking](https://www.nowcoder.com/practice/876e3c5fcfa5469f8376370d5de87c06?tpId=122&&tqId=33669&rp=1&ru=/ta/exam-wangyi&qru=/ta/exam-wangyi/question-ranking)

```java
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        Arrays.sort(a);
        int p = 1;
        for (int i = 0; i < n; i++) {
            if (a[i] > p)
                break;
            else
                p += a[i];
        }
        System.out.println(p);
    }
}

```

## Fibonacci数列

[https://www.nowcoder.com/practice/18ecd0ecf5ef4fe9ba3f17f8d00d2d66?tpId=122&&tqId=33668&rp=1&ru=/ta/exam-wangyi&qru=/ta/exam-wangyi/question-ranking](https://www.nowcoder.com/practice/18ecd0ecf5ef4fe9ba3f17f8d00d2d66?tpId=122&&tqId=33668&rp=1&ru=/ta/exam-wangyi&qru=/ta/exam-wangyi/question-ranking)

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if (n <= 2) System.out.println(0);
        else {
            int pre2 = 0, pre1 = 1;
            for (int i = 3; i < n; i++) {
                int sum = pre2 + pre1;
                if (sum == n){
                    System.out.println(0);
                    break;
                }
                else if (pre1 < n && n < sum){
                    System.out.println(Math.min(n - pre1, sum - n));
                    break;
                }
                pre2 = pre1;
                pre1 = sum;
            }
        }
    }
}

```

## 小易喜欢的单词

[https://www.nowcoder.com/practice/ca7b8af83e2f4ec1af2f23d6733223b5?tpId=122&&tqId=33667&rp=1&ru=/ta/exam-wangyi&qru=/ta/exam-wangyi/question-ranking](https://www.nowcoder.com/practice/ca7b8af83e2f4ec1af2f23d6733223b5?tpId=122&&tqId=33667&rp=1&ru=/ta/exam-wangyi&qru=/ta/exam-wangyi/question-ranking)

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        if (isUpper(s) && secJudge(s) && thirdJudge(s))
            System.out.println("Likes");
        else
            System.out.println("Dislikes");
    }

    public static boolean isUpper(String s){
        return s.matches("[A-Z]+");
    }

    public static boolean secJudge(String s){
        return !s.matches(".*(.)\\1.*");
    }

    public static boolean thirdJudge(String s){
        return !s.matches(".*(.).*(.)\\1.*\\2.*");
    }
}

// \1的意思是，和第一个()里的内容相同，注意转义字符的处理并且必须和()配套使用。
// \2的用法同理。

// .:匹配除换行符以外的任意字符
// *:重复零次或更多次
// +:重复一次或更多次
```

## 完全背包

```html
100
5
77 92
22 22
29 36
50 46
99 90

输出
114
```


```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 完全背包
        Scanner sc = new Scanner(System.in);
        int W = sc.nextInt();
        int n = sc.nextInt();
        int[] ps = new int[n];
        int[] vs = new int[n];
        for (int i = 0; i < n; i++) {
            ps[i] = sc.nextInt();
            vs[i] = sc.nextInt();
        }

        int[] dp = new int[W + 1];
        for (int i = 1; i <= n; i++) {
            int p = ps[i - 1], v = vs[ i - 1];
            for (int j = p; j <= W; j++) {
                dp[j] = Math.max(dp[j], dp[j - p] + v);
            }
        }
        System.out.println(dp[W]);
    }
}

```

## 找亲戚

有重复的数字，如{1,1,2} 全排列， 符合亲7数{112, 112}两个

```java
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<String> ret = new ArrayList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s  = sc.nextLine();
        String ss = convertNumber(s);

        char[] chars = ss.toCharArray();
        dfs(chars, new boolean[chars.length], new StringBuilder());
        int cnt = 0;
        for (String s1 : ret) {
            int num = Integer.parseInt(s1);
            if (num % 7 == 0)
                cnt++;
        }
        System.out.println(cnt);
    }

    public static void dfs(char[] chars, boolean[] marked, StringBuilder s){
        if (s.length() == chars.length){
            ret.add(s.toString());
            return;
        }
        for (int i = 0; i < marked.length; i++) {
            if (marked[i])
                continue;

            marked[i] = true;
            s.append(chars[i]);
            dfs(chars, marked, s);
            marked[i] = false;
            s.deleteCharAt(s.length() - 1);
        }
    }

    public static String convertNumber(String s){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9')
                sb.append(s.charAt(i));
        }
        return sb.toString();
    }
}
```

## 两种排序方法

[https://www.nowcoder.com/practice/839f681bf36c486fbcc5fcb977ffe432?tpId=122&&tqId=33666&rp=1&ru=/ta/exam-wangyi&qru=/ta/exam-wangyi/question-ranking](https://www.nowcoder.com/practice/839f681bf36c486fbcc5fcb977ffe432?tpId=122&&tqId=33666&rp=1&ru=/ta/exam-wangyi&qru=/ta/exam-wangyi/question-ranking)

```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        boolean lenFlag = true;
        List<String> v = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            v.add(sc.nextLine());
        }
        List<String> p = new ArrayList<>(v);
        Collections.sort(p);
        boolean dictFlag = p.equals(v);
        for (int i = 1; i < n; i++) {
            if (v.get(i).length() < v.get(i - 1).length()){
                lenFlag = false;
                break;
            }
        }

        if (dictFlag && lenFlag)
            System.out.println("both");
        else if (lenFlag)
            System.out.println("lengths");
        else if (dictFlag)
            System.out.println("lexicographically");
        else
            System.out.println("none");
    }
}

```

## 统计回文

[https://www.nowcoder.com/practice/9d1559511b3849deaa71b576fa7009dc?tpId=122&&tqId=33664&rp=1&ru=/ta/exam-wangyi&qru=/ta/exam-wangyi/question-ranking](https://www.nowcoder.com/practice/9d1559511b3849deaa71b576fa7009dc?tpId=122&&tqId=33664&rp=1&ru=/ta/exam-wangyi&qru=/ta/exam-wangyi/question-ranking)

```java
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String a = sc.nextLine();
        String b = sc.nextLine();
        int cnt = 0;
        for (int i = 0; i < a.length(); i++) {
            String s = a.substring(0, i + 1) + b + a.substring(i + 1);
            if (isSym(s))
                cnt++;
        }
        if (isSym(b+a))
            cnt++;
        System.out.println(cnt);
    }

    public static boolean isSym(String s){
        int l = 0, r = s.length() - 1;
        while (l < r){
            if (s.charAt(l) != s.charAt(r)){
                return false;
            }
            l++;
            r--;
        }
        return true;
    }
}

```

## 解救小易

[https://www.nowcoder.com/practice/cd763d8541fc4243b8d3b967bb6d6b6a?tpId=122&&tqId=33663&rp=1&ru=/ta/exam-wangyi&qru=/ta/exam-wangyi/question-ranking](https://www.nowcoder.com/practice/cd763d8541fc4243b8d3b967bb6d6b6a?tpId=122&&tqId=33663&rp=1&ru=/ta/exam-wangyi&qru=/ta/exam-wangyi/question-ranking)

```java
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] nums = new int[n][2];
        for (int i = 0; i < n; i++) {
            nums[i][0] = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {
            nums[i][1] = sc.nextInt();
        }
        Arrays.sort(nums, (a, b) -> (a[0] + a[1]) - (b[0] + b[1]));
        System.out.println((nums[0][0] - 1) + nums[0][1] - 1);
    }
}

```

## 不要二

[https://www.nowcoder.com/practice/1183548cd48446b38da501e58d5944eb?tpId=122&&tqId=33662&rp=1&ru=/ta/exam-wangyi&qru=/ta/exam-wangyi/question-ranking](https://www.nowcoder.com/practice/1183548cd48446b38da501e58d5944eb?tpId=122&&tqId=33662&rp=1&ru=/ta/exam-wangyi&qru=/ta/exam-wangyi/question-ranking)

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int w = sc.nextInt();
        int h = sc.nextInt();
        int[][] a = new int[w][h];
        int cnt = 0;
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                if (a[i][j] == 0){
                    cnt++;
                    if ((i + 2) < w)
                        a[i + 2][j] = -1;
                    if ((j + 2) < h)
                        a[i][j + 2] = -1;
                }
            }
        }
        System.out.println(cnt);
    }
}
```

## 大整数相乘

[https://www.nowcoder.com/practice/0f0badf5f2204a6bb968b0955a82779e?tpId=158&&tqId=34014&rp=1&ru=/ta/exam-pdd&qru=/ta/exam-pdd/question-ranking](https://www.nowcoder.com/practice/0f0badf5f2204a6bb968b0955a82779e?tpId=158&&tqId=34014&rp=1&ru=/ta/exam-pdd&qru=/ta/exam-pdd/question-ranking)

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] ss = sc.nextLine().split(" ");
        String s1 = ss[0];
        String s2 = ss[1];
        int len = s2.length() - 1;
        String pre = "0";
        for (int i = len; i >= 0; i--) {
            String s = calc1(s1, s2.charAt(i), len - i);
            String curS  = calc2(s, pre);
            pre = curS;
        }
        System.out.println(pre);
    }

    public static String calc1(String s1, char c, int idx){
        StringBuilder sb = new StringBuilder();
        int carry = 0, i = s1.length() - 1;
        while (idx-- > 0)
            sb.append('0');
        while (carry != 0 || i >= 0){
            int x = i < 0 ? 0 : s1.charAt(i--) - '0';
            int y = c - '0';
            sb.append(((x * y + carry) % 10));
            carry = (x * y + carry) / 10;
        }
        return sb.reverse().toString();
    }

    public static String calc2(String s1, String s2) {
        StringBuilder sb = new StringBuilder();
        int carry = 0, i = s1.length() - 1, j = s2.length() - 1;
        while (carry != 0 || i >= 0 || j >= 0){
            int x = i < 0 ? 0 : s1.charAt(i--) - '0';
            int y = j < 0 ? 0 : s2.charAt(j--) - '0';
            sb.append(((x + y + carry) % 10));
            carry = (x + y + carry) / 10;
        }
        return sb.reverse().toString();
    }
}

```

## 最大乘积

[https://www.nowcoder.com/practice/5f29c72b1ae14d92b9c3fa03a037ac5f?tpId=158&&tqId=34013&rp=1&ru=/ta/exam-pdd&qru=/ta/exam-pdd/question-ranking](https://www.nowcoder.com/practice/5f29c72b1ae14d92b9c3fa03a037ac5f?tpId=158&&tqId=34013&rp=1&ru=/ta/exam-pdd&qru=/ta/exam-pdd/question-ranking)

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;
        long min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int x = sc.nextInt();
            if (x > max1){
                max3 = max2;
                max2 = max1;
                max1 = x;
            } else if (x > max2){
                max3 = max2;
                max2 = x;
            } else if (x > max3)
                max3 = x;
            if (x < min1){
                min2 = min1;
                min1 = x;
            } else if (x < min2)
                min2 = x;
        }
        System.out.println(Math.max(max1 * max2 * max3, max1 * min1 * min2));
    }
}

```

## 六一儿童节

[https://www.nowcoder.com/practice/d2dfc62bf1ba42679a0e358c57da9828?tpId=158&&tqId=34015&rp=1&ru=/ta/exam-pdd&qru=/ta/exam-pdd/question-ranking](https://www.nowcoder.com/practice/d2dfc62bf1ba42679a0e358c57da9828?tpId=158&&tqId=34015&rp=1&ru=/ta/exam-pdd&qru=/ta/exam-pdd/question-ranking)

```java
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int h = sc.nextInt();
        int[] hs = new int[h];
        for (int i = 0; i < h; i++) {
            hs[i] = sc.nextInt();
        }
        int w = sc.nextInt();
        int[] ws = new int[w];
        for (int i = 0; i < w; i++) {
            ws[i] = sc.nextInt();
        }
        Arrays.sort(hs);
        Arrays.sort(ws);
        int i = 0, j = 0;
        while (i < h && j < w){
            if (hs[i] <= ws[j])
                i++;
            j++;
        }
        System.out.println(i);
    }
}

```

## 藏宝图

[nowcoder.com/practice/74475ee28edb497c8aa4f8c370f08c30?tpId=122&&tqId=33658&rp=1&ru=/ta/exam-wangyi&qru=/ta/exam-wangyi/question-ranking](nowcoder.com/practice/74475ee28edb497c8aa4f8c370f08c30?tpId=122&&tqId=33658&rp=1&ru=/ta/exam-wangyi&qru=/ta/exam-wangyi/question-ranking)

```java
import java.util.Arrays;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String t = sc.nextLine();
        if (isSeq(s, t))
            System.out.println("Yes");
        else
            System.out.println("No");
    }

    public static boolean isSeq(String s, String t){
        if (t.equals(" "))
            return true;
        int inx = -1;
        for (char c : t.toCharArray()) {
            inx = s.indexOf(c, inx + 1);
            if (inx == -1) return false;
        }
        return true;
    }

}
```