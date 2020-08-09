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