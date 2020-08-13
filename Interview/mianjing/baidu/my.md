
一面
时间：8.12 12:00 时长：110分钟


1. 自我介绍
2. 项目介绍（20min）
3. 为什么采用微服务架构？
4. 为什么用Dubbo？
5. 为什么用Redis？
6. 谈一谈Redis的持久化
7. Redis与MySQL双写一致性方案
8. 谈一谈zk？
9. zk如何实现分布式锁？
10. zk的leader选举过程
11. zk的zab算法讲一下
12. ArrayList和LinkedList的区别
13. ArrayList的add源码
14. 讲一下生产者消费者模型（讲了三种方案）
15. 生产者为什么用while？
16. 说一下synchronized的修饰范围以及锁的部门？
17. 了解volatile吗？都有哪些特性？分别举例子
18. JMM内存模型
19. JVM运行时区域
20. 堆、方法区和虚拟机栈分别是什么？存了什么？
21. 方法区的版本变化以及内部常量池的变化？
22. 堆为什么要分成新生代和老年代
23. 新生代为什么又分为Eden、s0和s1
24. 标记算法？
25. 可达性分析工作原理？哪些可以作为GC Roots根
26. 虚拟机栈局部变量表存什么？
27. class加载过程讲一下？
28. new对象，发生了什么过程？ 讲一下
29. 写一道SQL题，不记得了，大致还要用到join和分组求和。
30. left join right join inner join的区别
31. TCP三次握手
32. 写一道题：求一个数组的所有递增子序列。
33. 反问

总结：我倾其所有，想睡个午觉。

```java
import java.util.ArrayList;
import java.util.List;

public class Main {

    static List<List<Integer>> sub = new ArrayList<>();
    static List<List<Integer>> ret = new ArrayList<>();

    public static void main(String[] args) {
        int[] nums = {4, 6, 9, 8, 8};
        subsets(nums);
        isInc();
        for (List<Integer> list : ret) {
            System.out.println(list.toString());
        }
    }

    public static void isInc() {
        for (List<Integer> list : sub) {
            for (int i = 1; i < list.size(); i++) {
                if (list.get(i) < list.get(i - 1))
                    break;
                if (i == list.size() - 1)
                    ret.add(new ArrayList<>(list));
            }
        }
    }

    public static List<List<Integer>> subsets(int[] nums) {
//        Arrays.sort(nums);
        for (int i = 2; i <= nums.length; i++) {
            dfs(0, nums, new ArrayList<Integer>(), i, new boolean[nums.length]);
        }
        return sub;
    }

    private static void dfs(int start, int[] nums, ArrayList<Integer> list, int size, boolean[] marked) {
        if (list.size() == size) {
            sub.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            if (i != 0 && nums[i] == nums[i - 1] && !marked[i - 1])
                continue;
            marked[i] = true;
            list.add(nums[i]);
            dfs(i + 1, nums, list, size, marked);
            list.remove(list.size() - 1);
            marked[i] = false;
        }
    }
}
```

```java
import java.util.*;

public class Main {

    static Set<List<Integer>> sub = new LinkedHashSet<>();
    static List<List<Integer>> ret = new ArrayList<>();

    public static void main(String[] args) {
        int[] nums = {4, 6, 9, 8, 8};
        subsets(nums);
        isInc();
        for (List<Integer> list : ret) {
            System.out.println(list.toString());
        }
    }

    public static void isInc() {
        for (List<Integer> list : sub) {
            for (int i = 1; i < list.size(); i++) {
                if (list.get(i) < list.get(i - 1))
                    break;
                if (i == list.size() - 1)
                    ret.add(new ArrayList<>(list));
            }
        }
    }

    public static Set<List<Integer>> subsets(int[] nums) {
        for (int i = 2; i <= nums.length; i++) {
            dfs(0, nums, new ArrayList<Integer>(), i);
        }
        return sub;
    }

    private static void dfs(int start, int[] nums, ArrayList<Integer> list, int size) {
        if (list.size() == size) {
            sub.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            list.add(nums[i]);
            dfs(i + 1, nums, list, size);
            list.remove(list.size() - 1);
        }
    }
}
```