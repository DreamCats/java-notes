


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