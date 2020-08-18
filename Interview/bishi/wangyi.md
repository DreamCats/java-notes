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

## 分苹果

[https://www.nowcoder.com/practice/a174820de48147d489f64103af152709?tpId=122&&tqId=33656&rp=1&ru=/ta/exam-wangyi&qru=/ta/exam-wangyi/question-ranking](https://www.nowcoder.com/practice/a174820de48147d489f64103af152709?tpId=122&&tqId=33656&rp=1&ru=/ta/exam-wangyi&qru=/ta/exam-wangyi/question-ranking)

```java
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int sum = 0;
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
            sum += a[i];
        }
        System.out.println(minCount(a, sum));
    }

    public static int minCount(int[] a, int sum){
        if (sum % a.length != 0)
            return -1;
        int avg = sum / a.length;
        int cnt = 0;
        for (int i = 0; i < a.length; i++) {
            int sub = a[i] - avg;
            if (sub > 0){
                if (sub % 2 == 0)
                    cnt += sub / 2;
                else
                    return -1;
            }
        }
        return cnt;
    }
}
```

## 混合颜料

[https://www.nowcoder.com/practice/5b1116081ee549f882970eca84b4785a?tpId=122&&tqId=33660&rp=1&ru=/ta/exam-wangyi&qru=/ta/exam-wangyi/question-ranking](https://www.nowcoder.com/practice/5b1116081ee549f882970eca84b4785a?tpId=122&&tqId=33660&rp=1&ru=/ta/exam-wangyi&qru=/ta/exam-wangyi/question-ranking)

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
        System.out.println(min(a, n));
    }

    public static int min(int[] a, int n){
        for (int i = n - 1; i >= 0; i--) {
            Arrays.sort(a);
            for (int j = i - 1; j >= 0; j--) {
                if ((a[i] ^ a[j]) < a[j])
                    a[j] ^= a[i];
            }
        }
        int cnt = 0;
        for (int i : a) {
            if (i != 0)
                cnt++;
        }
        return cnt;
    }
}
```

## 等差数列

[https://www.nowcoder.com/practice/e11bc3a213d24fc1989b21a7c8b50c3f?tpId=122&&tqId=33681&rp=1&ru=/ta/exam-wangyi&qru=/ta/exam-wangyi/question-ranking](https://www.nowcoder.com/practice/e11bc3a213d24fc1989b21a7c8b50c3f?tpId=122&&tqId=33681&rp=1&ru=/ta/exam-wangyi&qru=/ta/exam-wangyi/question-ranking)

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int sum = 0;
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
            sum += a[i];
            min = Math.min(min, a[i]);
            max = Math.max(max, a[i]);
        }
        int total = (max + min) * n;
        if ((total % 2 == 0) && (sum == total / 2))
            System.out.println("Possible");
        else
            System.out.println("Impossible");
    }
}

```

## 独立的小易

[https://www.nowcoder.com/practice/a99cdf4e2f44499e80749699cc2ec2b9?tpId=122&&tqId=33684&rp=1&ru=/ta/exam-wangyi&qru=/ta/exam-wangyi/question-ranking](https://www.nowcoder.com/practice/a99cdf4e2f44499e80749699cc2ec2b9?tpId=122&&tqId=33684&rp=1&ru=/ta/exam-wangyi&qru=/ta/exam-wangyi/question-ranking)

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int f = sc.nextInt();
        int d = sc.nextInt();
        int p = sc.nextInt();
        if ((d - x * f) < 0) {
            // 说明 钱不够
            System.out.println(d / x);
        } else {
            // 说明钱够
            int sub = d - x * f; // 剩下的钱
            int day = f;
            System.out.println(day + sub / (x + p));
        }
    }
}
```

## 被3整除

[https://www.nowcoder.com/practice/51dcb4eef6004f6f8f44d927463ad5e8?tpId=122&&tqId=33692&rp=1&ru=/ta/exam-wangyi&qru=/ta/exam-wangyi/question-ranking](https://www.nowcoder.com/practice/51dcb4eef6004f6f8f44d927463ad5e8?tpId=122&&tqId=33692&rp=1&ru=/ta/exam-wangyi&qru=/ta/exam-wangyi/question-ranking)

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long l = sc.nextInt();
        long r = sc.nextInt();
        long lSum = 0;
        for (int i = 1; i <= l; i++) {
            lSum += i;
        }
        long rSum = lSum;
        long cnt = rSum % 3 == 0 ? 1 : 0;
        for (long i = l + 1; i <= r ; i++) {
            rSum += i;
            cnt = rSum % 3 == 0 ? cnt + 1 : cnt;
        }
        System.out.println(cnt);
    }
}
```

## 彩色的砖块

[https://www.nowcoder.com/practice/8c29f4d1bea84d6ba2847e079b7420f7?tpId=122&&tqId=33680&rp=1&ru=/ta/exam-wangyi&qru=/ta/exam-wangyi/question-ranking](https://www.nowcoder.com/practice/8c29f4d1bea84d6ba2847e079b7420f7?tpId=122&&tqId=33680&rp=1&ru=/ta/exam-wangyi&qru=/ta/exam-wangyi/question-ranking)

```java
import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        HashSet<Character> set = new HashSet<Character>();
        for (int i = 0; i < s.length(); i++) {
            set.add(s.charAt(i));
        }
        if (set.size() == 2)
            System.out.println(2);
        else if (set.size() == 1)
            System.out.println(1);
        else
            System.out.println(0);
    }
}
```

---

网易8.8笔试(题目改天整理)

```java
class Main1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        int ans = 0;
        for (int item : a) {
            ans += item / 2;
        }
        System.out.println(ans);
    }
}
```

```java
class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] a = new int[m];
        for (int i = 0; i < m; i++) {
            a[i] = sc.nextInt();
        }
        List<Integer> list = solution(a, n);
        for (int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1)
                System.out.print(list.get(i));
            else
                System.out.print(list.get(i) + " ");
        }
    }

    public static List<Integer> solution(int[] nums, int m){
        int[] a = new int[m + 1];
        for (int i = 1; i <= m; i++) {
            a[i - 1] = i;
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > m)
                continue;
            a[nums[i] - 1] = 0;
        }
        int idx = 0;
        List<Integer> list = new ArrayList<>(20);
        for (int i = 0; i < nums.length; i++) {
            list.add(nums[i]);
        }
        for (int i = 0; i < a.length; i++) {
            if (a[i] == 0)
                continue;
            int target = a[i];
            while (idx < list.size() && list.get(idx) < target) idx++;
            list.add(idx, target);
        }
        return list;
    }
}
```


```java
class Main3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int j = 0; j < t; j++) {
            int n = sc.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = sc.nextInt();
            }
            Arrays.sort(a);
            int sub = 0;
            for (int i = 0; i < n; i++) {
                int[] copy = Arrays.copyOfRange(a, i, n);
                if (!canPartition(copy)){
                    sub += a[i];
                } else
                    break;
            }
            System.out.println(sub);
        }
    }

    public static boolean canPartition(int[] nums) {
        int sum = computeSum(nums);
        if (sum % 2 != 0)
            return false;
        int W = sum / 2;
        boolean[] dp = new boolean[W + 1];
        dp[0] = true;
        for (int num : nums) {
            for (int i = W; i >= num; i--) {
                dp[i] = dp[i] || dp[i - num];
            }
        }
        return dp[W];
    }

    public static int computeSum(int[] nums){
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        return sum;
    }
}
```

```java
import java.util.*;

public class Main3 {
    static Set<List<Integer>> marked = new HashSet<>();
    static List<Integer> listSum = new ArrayList<>();
    static int sum = 0;
    public static void main(String[] args) {
        int[] nums = {5, 15, 20, 40, 60, 80, 100, 5, 5, 5, 5, 5, 5, 10, 10, 25};
        sum = Arrays.stream(nums).sum();
        Arrays.sort(nums);
        for (int i = 2; i <= nums.length; i++) {
            dfs(0, nums, new ArrayList<Integer>(), i);
        }
        System.out.println(listSum.stream().min(Comparator.comparingInt(Integer::intValue)).get());
    }

    private static void dfs(int start, int[] nums, ArrayList<Integer>list, int size) {
        if (list.size() == size) {
            if (!marked.contains(list))
                marked.add(list);
                if (canPartition(list.stream().mapToInt(Integer::valueOf).toArray())) {
                    listSum.add(sum - list.stream().mapToInt(Integer::valueOf).sum());
                    return;
                }
        }
        for (int i = start; i < nums.length; i++) {
            list.add(nums[i]);
            dfs(i + 1, nums, list, size);
            list.remove(list.size() - 1);
        }
    }

    public static boolean canPartition(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        if (sum % 2 != 0) return false;
        int w = sum / 2;
        boolean[] dp = new boolean[w + 1];
        dp[0] = true;
        for (int num : nums) {
            for (int i = w; i >= num; i--) {
                dp[i] = dp[i] || dp[i - num];
            }
        }
        return dp[w];
    }

}
```

---


## 疯狂的队列

[https://www.nowcoder.com/practice/d996665fbd5e41f89c8d280f84968ee1?tpId=122&&tqId=33686&rp=1&ru=/ta/exam-wangyi&qru=/ta/exam-wangyi/question-ranking](https://www.nowcoder.com/practice/d996665fbd5e41f89c8d280f84968ee1?tpId=122&&tqId=33686&rp=1&ru=/ta/exam-wangyi&qru=/ta/exam-wangyi/question-ranking)

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
        System.out.println(maxSub(a));
    }

    public static int maxSub(int[] a) {
        Arrays.sort(a);
        int n = a.length;
        int[] b = new int[n];
        int l = 0, r = n - 1;
        int m = n / 2;
        int p = m - 1;
        while (l <= r) {
            if (m < n)
                b[m++] = (m - 1) % 2 == 0 ? a[r--] : a[l++];
            if (p >= 0)
                b[p--] = (p + 1) % 2 == 0 ? a[r--] : a[l++];
        }
        int max = 0;
        for (int i = 1; i < n; i++) {
            max += Math.abs(b[i] - b[i - 1]);
        }
        return max;
    }
}

```

## 跳石板

[https://www.nowcoder.com/practice/4284c8f466814870bae7799a07d49ec8?tpId=122&&tqId=33674&rp=1&ru=/ta/exam-wangyi&qru=/ta/exam-wangyi/question-ranking](https://www.nowcoder.com/practice/4284c8f466814870bae7799a07d49ec8?tpId=122&&tqId=33674&rp=1&ru=/ta/exam-wangyi&qru=/ta/exam-wangyi/question-ranking)

```java
// bfs
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
```

## 牛牛的背包问题

[https://www.nowcoder.com/practice/bf877f837467488692be703735db84e6?tpId=122&&tqId=33698&rp=1&ru=/ta/exam-wangyi&qru=/ta/exam-wangyi/question-ranking](https://www.nowcoder.com/practice/bf877f837467488692be703735db84e6?tpId=122&&tqId=33698&rp=1&ru=/ta/exam-wangyi&qru=/ta/exam-wangyi/question-ranking)

```java
import java.util.Scanner;

public class Main {
    static int res = 1;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long w = sc.nextInt();
        long[] v = new long[n];
        long sum = 0;
        for (int i = 0; i < n; i++) {
            v[i] = sc.nextInt();
            sum += v[i];
        }
        if (sum <= w) {
            System.out.println((int) Math.pow(2, n));
        } else {
            dfs(v, 0, w, 0);
            System.out.println(res);
        }
    }

    public static void dfs(long[] v, int idx, long w, long cur) {
        if (idx == v.length)
            return;
        if (v[idx] + cur <= w) {
            res++;
            dfs(v, idx + 1, w, cur + v[idx]);
        }
        dfs(v, idx + 1, w, cur);
    }
}
```

## 交错01串

[https://www.nowcoder.com/practice/3fbd8fe929ea4eb3a254c0ed34ac993a?tpId=122&&tqId=33682&rp=1&ru=/ta/exam-wangyi&qru=/ta/exam-wangyi/question-ranking](https://www.nowcoder.com/practice/3fbd8fe929ea4eb3a254c0ed34ac993a?tpId=122&&tqId=33682&rp=1&ru=/ta/exam-wangyi&qru=/ta/exam-wangyi/question-ranking)

```java
import java.util.Scanner;

public class Main {
    static int res = 1;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        if (s.length() == 0 || s.length() == 1) {
            System.out.println(0);
            return;
        }
        int len = 1;
        int res = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) != s.charAt(i - 1)) {
                len++;
                res = Math.max(len, res);
            } else {
                len = 1;
            }
        }
        System.out.println(res);
    }
}
```

## 操作序列

[https://www.nowcoder.com/practice/b53bda356a494154b6411d80380295f5?tpId=122&&tqId=33683&rp=1&ru=/ta/exam-wangyi&qru=/ta/exam-wangyi/question-ranking](https://www.nowcoder.com/practice/b53bda356a494154b6411d80380295f5?tpId=122&&tqId=33683&rp=1&ru=/ta/exam-wangyi&qru=/ta/exam-wangyi/question-ranking)

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
        if (n == 1)
            System.out.println(a[0]);
        if (n % 2 == 0) {
            for (int i = n - 1; i >= 0; i -=2)
                System.out.print(a[i] + " ");
            for (int i = 0; i < n - 2; i += 2)
                System.out.print(a[i] + " ");
            System.out.print(a[n - 2]);
        }
        else {
            for (int i = n - 1; i >= 0; i -= 2) {
                System.out.print(a[i] + " ");
            }
            for (int i = 1; i < n - 2; i += 2) 
                System.out.print(a[i] + " ");
            System.out.print(a[n-2]);
        }
    }
}

```

## 幸运的袋子

[https://www.nowcoder.com/practice/a5190a7c3ec045ce9273beebdfe029ee?tpId=122&&tqId=33661&rp=1&ru=/ta/exam-wangyi&qru=/ta/exam-wangyi/question-ranking](https://www.nowcoder.com/practice/a5190a7c3ec045ce9273beebdfe029ee?tpId=122&&tqId=33661&rp=1&ru=/ta/exam-wangyi&qru=/ta/exam-wangyi/question-ranking)

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
        System.out.println(dfs(a, 0, 0, 1));
    }

    public static int dfs(int[] a, int start, long sum, long multi) {
        int cnt = 0;
        for (int i = start; i < a.length; i++) {
            sum += a[i];
            multi *= a[i];
            if (sum > multi)
                cnt += 1 + dfs(a, i + 1, sum, multi);
            else if (a[i] == 1)
                cnt += dfs(a, i + 1, sum, multi);
            else
                break;
            sum -= a[i];
            multi /= a[i];
            while (i < a.length - 1 && a[i] == a[i + 1]) i++;
        }
        return cnt;
    }
}
```