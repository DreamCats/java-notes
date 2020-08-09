
题目忘记了，改天补上。


求最小车辆
```java
class Main2 {
    public static void main(String[] args) {
        int[][] trips = {{2, 1, 4}, {4, 2, 6}, {6, 6, 8}};
        int capacity = 5;
        System.out.println(minCarCount(trips, capacity));
    }

    public static int minCarCount (int[][] trips, int capacity) {
        // write code here
        Arrays.sort(trips, (a, b) -> a[2] - b[2]);
        int n = trips.length;
        int cnt = trips[0][0] <= capacity ? 1 : trips[0][0] / 5 + 1;
        int e = trips[0][2];
        for (int i = 1; i < n; i++) {
            if (trips[i][1] != e) {
                cnt += trips[i][0] <= capacity ? 1 : trips[i][0] / 5 + 1;
            } else {
                cnt += trips[i][0] <= capacity ? 0 : trips[i][0] / 5;
            }
            e = trips[i][2];
        }
        return cnt;
    }
}
```


做任务升级，求最大级别
```java
class Main1 {
    public static void main(String[] args) {

        int x = 2;
        int leval = 1;
//        int[][] tasks = {{0,1}, {1, 2}, {1, 3}};
        int[][] tasks = {{5,15}, {5, 10}, {1, 4}};
        int ret = maxLevel(x, leval, tasks);
        System.out.println(ret);
    }
    public static int maxLevel (int x, int level, int[][] tasks) {
        // write code here
        Arrays.sort(tasks, (a, b) -> a[1] == b[1] ? a[0] - b[0] : b[1] - a[1]);
        int n = tasks.length;
        int nLevel = level;
        boolean flag = false;
        Queue<int[]> queue = new LinkedList<>();
        if (x == 0)
            return level;
        for (int i = 0; i < n; i++) {
            if (nLevel >= tasks[i][0]){
                nLevel += tasks[i][1];
                x--;
                if (x == 0)
                    break;
                int size = queue.size();
                while (size-- > 0){
                    int[] tmp = queue.peek();
                    if (nLevel >= tmp[0]) {
                        nLevel += tmp[1];
                        queue.poll();
                        x--;
                    }
                    if (x == 0){
                        flag = true;
                        break;
                    }
                }
            } else {
                queue.add(tasks[i]);
            }
            if (flag)
                break;
        }
        if (queue.size() == n)
            return level;
        return nLevel;
    }
}
```