
这给我难的...

智力题

```java
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            int n = sc.nextInt();
            int[] a = new int[n];
            for (int j = 0; j < n; j++) {
                a[j] = sc.nextInt();
            }
            Arrays.sort(a);
            System.out.println(min(a, n - 1));
        }
    }

    public static long min(int[] a, int end) {
        if (end + 1 == 1)
            return 0;
        if (end + 1 <= 2)
            return a[1];
        if (end + 1 == 3)
            return a[2] + a[1] + a[0];
        if (a[0] + a[end - 1] > 2 * a[1])
            return a[1] * 2 + a[0] + a[end] + min(a, end - 2);
        else
            return a[end] + a[0] + min(a, end - 2);
    }
}

```


```python

# 容易想到的是过河方案就是最轻的人作为摆渡人，一趟一趟运
# 还有一种是，最轻的人把第二轻的送到对岸，自己回来，再让最重的两个人过去，第二轻的再把船划回来
# 一直比较这两种方案，直到人数小于3
def first():
    T = int(input())
    for _ in range(T):
        n = int(input())
        weights = list(map(int, input().split()))
        sorted(weights)
        res = 0
        while n > 3:
            res += min(weights[1] * 2 + weights[0] + weights[-1], weights[0] * 2 + weights[-1] + weights[-2])
            weights.pop()
            weights.pop()
            n -=2
        if n == 1 or n == 3:
            res += sum(weights)
        elif n == 2:
            res += max(weights)
        print(res)

first()

```