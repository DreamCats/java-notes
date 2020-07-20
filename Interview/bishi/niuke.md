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