## 美团小象二面实习面经
- [原文链接](https://www.nowcoder.com/discuss/411053)
### 最小生成树prim和kruskal算法
> 最小生成树是一副连通加权无向图中一棵权值最小的生成树。

#### Prim算法原理：
>1）以某一个点开始，寻找当前该点可以访问的所有的边；
>2）在已经寻找的边中发现最小边，这个边必须有一个点还没有访问过，将还没有访问的点加入我们的集合，记录添加的边；
>3）寻找当前集合可以访问的所有边，重复2的过程，直到没有新的点可以加入；
>4）此时由所有边构成的树即为最小生成树。

#### Kruskal算法原理：
>现在我们假设一个图有m个节点，n条边。
>首先，我们需要把m个节点看成m个独立的生成树，并且把n条边按照从小到大的数据进行排列。
>在n条边中，我们依次取出其中的每一条边，如果发现边的两个节点分别位于两棵树上，那么把两棵树合并成为一颗树；
>如果树的两个节点位于同一棵树上，那么忽略这条边，继续运行。
>等到所有的边都遍历结束之后，如果所有的生成树可以合并成一条生成树，那么它就是我们需要寻找的最小生成树，反之则没有最小生成树。

#### 总结
总的来说，Prim算法是以点为对象，挑选与点相连的最短边来构成最小生成树。
而Kruskal算法是以边为对象，不断地加入新的不构成环路的最短边来构成最小生成树。

### Hash冲突哪几种解决方式
- 开放定址法（线性探测再散列，二次探测再散列，伪随机探测再散列）
- 再哈希法
- 链地址法(Java hashmap就是这么做的)
- 建立一个公共溢出区
#### 拉链法的优点
- 拉链法处理冲突简单，且无堆积现象，即非同义词决不会发生冲突，因此平均查找长度较短；
- 由于拉链法中各链表上的结点空间是动态申请的，故它更适合于造表前无法确定表长的情况；
- 开放定址法为减少冲突，要求装填因子α较小，故当结点规模较大时会浪费很多空间。而拉链法中可取α≥1，且结点较大时，拉链法中增加的指针域可忽略不计，因此节省空间；
- 在用拉链法构造的散列表中，删除结点的操作易于实现。

#### 拉链法的缺点
指针需要额外的空间，故当结点规模较小时，开放定址法较为节省空间，而若将节省的指针空间用来扩大散列表的规模，可使装填因子变小，这又减少了开放定址法中的冲突，从而提高平均查找速度。

### HashSet怎么实现
> 对于 HashSet 而言，它是基于 HashMap 实现的，底层采用 HashMap 来保存元素，所以如果对 HashMap 比较熟悉了，那么学习 HashSet 也是很轻松的。

对于 HashSet 而言，它是基于 HashMap 实现的，HashSet 底层使用 HashMap 来保存所有元素，因此 HashSet 的实现比较简单，相关 HashSet 的操作，基本上都是直接调用底层 HashMap 的相关方法来完成，我们应该为保存到 HashSet 中的对象覆盖 hashCode() 和 equals()
### MySQL数据库有哪几种索引
### 三次握手和四次挥手
### Linux查看当前文件路径命令
### 找出数组中的两个出现次数是奇数的数字（其他都是偶数）（剑指offer）
```java
public class SingleNumbers {
    public int[] singleNumbers(int[] nums) {
        int xorNumber = nums[0];
        for (int i = 1; i < nums.length; i++) {
            xorNumber ^= nums[i];
        }
        int onePosition = xorNumber & (-xorNumber);
        int ans1 = 0, ans2 = 0;
        for (int i = 0; i < nums.length; i++) {
            if ((nums[i] & onePosition) == onePosition) {
                ans1 ^= nums[i];
            } else {
                ans2 ^= nums[i];
            }
        }
        return new int[] {ans1^0, ans2^0};
    }
}
```
### 无重复最长子串（HashMap）
```java
public class LongestSubstring {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        // abcabc
        for (int i = 0, j = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }
}
```