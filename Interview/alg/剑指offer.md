## 3、[数组中重复的数字](https://www.nowcoder.com/practice/623a5ac0ea5b4e5f95552655361ae0a8?tpId=13&tqId=11203&tPage=3&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

### 题目描述

在一个长度为 n 的数组里的所有数字都在 0 到 n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字是重复的，也不知道每个数字重复几次。请找出数组中任意一个重复的数字。

```html
Input:
{2, 3, 1, 0, 2, 5}

Output:
2
```

### 解题思路

要求时间复杂度 O(N)，空间复杂度 O(1)。因此不能使用排序的方法，也不能使用额外的标记数组。

对于这种数组元素在 [0, n-1] 范围内的问题，可以将值为 i 的元素调整到第 i 个位置上进行求解。本题要求找出重复的数字，因此在调整过程中，如果第 i 位置上已经有一个值为 i 的元素，就可以知道 i 值重复。

以 (2, 3, 1, 0, 2, 5) 为例，遍历到位置 4 时，该位置上的数为 2，但是第 2 个位置上已经有一个 2 的值了，因此可以知道 2 重复：

![](https://cs-notes-1256109796.cos.ap-guangzhou.myqcloud.com/643b6f18-f933-4ac5-aa7a-e304dbd7fe49.gif)

```java
public boolean duplicate(int[] nums, int length, int[] duplication) {
    if (nums == null || length <= 0)
        return false;
    for (int i = 0; i < length; i++) {
        while (nums[i] != i) { // 该value和索引不相等，则i,nums[i]的索引的value互相交换
            if (nums[i] == nums[nums[i]]) { // 如果value和索引相等了，那么重复了
                duplication[0] = nums[i];
                return true;
            }
            swap(nums, i, nums[i]);
        }
    }
    return false;
}

private void swap(int[] nums, int i, int j) {
    int t = nums[i];
    nums[i] = nums[j];
    nums[j] = t;
}

```



```java
public class T50 {
    public boolean duplicate(int numbers[],int length,int [] duplication) {
        // 边界
        if (numbers == null || numbers.length == 0) {
            return false;
        }
        // 排序
        Arrays.sort(numbers);
        int flag = 0;
        for (int i = 0; i < length - 1; i++) {
            if (numbers[i] == numbers[i + 1]) {
                duplication[0] = numbers[i];
                flag = 1;
                break;
            }
        }
        return flag == 1 ? true : false;
    }
}
```

## 4、[二维数组中的查找](https://www.nowcoder.com/practice/abc3fe2ce8e146608e868a70efebf62e?tpId=13&tqId=11154&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)

### 题目描述

给定一个二维数组，其每一行从左到右递增排序，从上到下也是递增排序。给定一个数，判断这个数是否在该二维数组中。

```html
Consider the following matrix:
[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]

Given target = 5, return true.
Given target = 20, return false.

```

### 解题思路

要求时间复杂度 O(M + N)，空间复杂度 O(1)。其中 M 为行数，N 为 列数。

该二维数组中的一个数，小于它的数一定在其左边，大于它的数一定在其下边。因此，从右上角开始查找，就可以根据 target 和当前元素的大小关系来缩小查找区间，当前元素的查找区间为左下角的所有元素。

![](https://cs-notes-1256109796.cos.ap-guangzhou.myqcloud.com/35a8c711-0dc0-4613-95f3-be96c6c6e104.gif)

```java
public boolean Find(int target, int[][] matrix) {
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
        return false;
    int rows = matrix.length, cols = matrix[0].length;
    int r = 0, c = cols - 1; // 从右上角开始
    while (r <= rows - 1 && c >= 0) {
        if (target == matrix[r][c]) // 如果相等，直接返回
            return true;
        else if (target > matrix[r][c]) // 如果小于target， r++；
            r++;
        else
            c--; // 如果大于，c--；
    }
    return false;
}

```



```java
public class T1 {
    public boolean Find(int target, int [][] array) {
        // 研究数组的特性，比如：
      	// 3 4 5
      	// 4 8 6
      	// 5 9 10
        int col = 0;
        for (int i = array.length - 1; i >= 0; i--) {
          	// 最后一行开始，按列遍历和target比较：
          	// 如果<target:col++
            // 否则跳出while
            while (col < array[0].length && array[i][col] < target) col++;
            // 如果col等于数组列数，说明其实已经越界了，那么说明没有与target相等的数
            if (col == array[0].length) return false;
            // 如果当前元素和target相等，则返回true
            if (array[i][col] == target) return true;
        }
        return false;
    }
}
```

## 5、[替换空格](https://www.nowcoder.com/practice/4060ac7e3e404ad1a894ef3e17650423?tpId=13&tqId=11155&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)

### 题目描述

将一个字符串中的空格替换成 "%20"。

```html
Input:
"A B"

Output:
"A%20B"
```

### 解题思路

① 在字符串尾部填充任意字符，使得字符串的长度等于替换之后的长度。因为一个空格要替换成三个字符（%20），所以当遍历到一个空格时，需要在尾部填充两个任意字符。

② 令 P1 指向字符串原来的末尾位置，P2 指向字符串现在的末尾位置。P1 和 P2 从后向前遍历，当 P1 遍历到一个空格时，就需要令 P2 指向的位置依次填充 02%（注意是逆序的），否则就填充上 P1 指向字符的值。从后向前遍是为了在改变 P2 所指向的内容时，不会影响到 P1 遍历原来字符串的内容。

③ 当 P2 遇到 P1 时（P2 <= P1），或者遍历结束（P1 < 0），退出。

![](https://cs-notes-1256109796.cos.ap-guangzhou.myqcloud.com/f7c1fea2-c1e7-4d31-94b5-0d9df85e093c.gif)

```java
public String replaceSpace(StringBuffer str) {
    int P1 = str.length() - 1;
    for (int i = 0; i <= P1; i++) // 检测空格
        if (str.charAt(i) == ' ') // 在空格后面多加两个空格
            str.append("  ");

    int P2 = str.length() - 1;
    while (P1 >= 0 && P2 > P1) {
        char c = str.charAt(P1--);
        if (c == ' ') {
            str.setCharAt(P2--, '0'); // 倒着替换
            str.setCharAt(P2--, '2');
            str.setCharAt(P2--, '%');
        } else {
            str.setCharAt(P2--, c);
        }
    }
    return str.toString();
}
```



```java
public class T2 {
    public String replaceSpace(StringBuffer str) {
        // 检测空格数目
        int spaceNum = 0;
        // 第一遍循环，检测空格的数目
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') spaceNum++;
        }
        // 创建新数组 ，也可以StringBuilder
        char[] ans = new char[str.length() + 2 * spaceNum];
        int p1 = ans.length - 1;
        // 倒着遍历，一个一个添加
        for (int i = str.length() - 1; i >= 0; i++) {
            if (str.charAt(i) == ' ') {
                ans[p1--] = '0';
                ans[p1--] = '2';
                ans[p1--] = '%';
            } else {
                ans[p1--] = str.charAt(i);
            }
        }
        return new String(ans);
    }
}
```

## 6、[从尾到头打印链表](https://www.nowcoder.com/practice/d0267f7f55b3412ba93bd35cfa8e8035?tpId=13&tqId=11156&tPage=1&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

### 题目描述

从尾到头反过来打印出每个结点的值。

![](https://cs-notes-1256109796.cos.ap-guangzhou.myqcloud.com/f5792051-d9b2-4ca4-a234-a4a2de3d5a57.png)

### 解题思路

要逆序打印链表 1->2->3（3,2,1)，可以先逆序打印链表 2->3(3,2)，最后再打印第一个节点 1。而链表 2->3 可以看成一个新的链表，要逆序打印该链表可以继续使用求解函数，也就是在求解函数中调用自己，这就是递归函数。

```java
public class T3 {
    // 创建list
    ArrayList<Integer> list = new ArrayList<>();
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        // 判断头节点是否为空
        if (listNode != null) {
            // 递归打印
            this.printListFromTailToHead(listNode.next);
            list.add(listNode.val);
        }
        return list;
    }
}
```

## 7、[重建二叉树](https://www.nowcoder.com/practice/8a19cbe657394eeaac2f6ea9b0f6fcf6?tpId=13&tqId=11157&tPage=1&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

### 题目描述

根据二叉树的前序遍历和中序遍历的结果，重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。

![](https://cs-notes-1256109796.cos.ap-guangzhou.myqcloud.com/image-20191102210342488.png)

### 解题思路

前序遍历的第一个值为根节点的值，使用这个值将中序遍历结果分成两部分，左部分为树的左子树中序遍历结果，右部分为树的右子树中序遍历的结果。然后分别对左右子树递归地求解。

![](https://cs-notes-1256109796.cos.ap-guangzhou.myqcloud.com/60c4a44c-7829-4242-b3a1-26c3b513aaf0.gif)



```java
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        if (n == 0)
            return null;
        int rootVal = preorder[0], rootIndex = 0;
        for (int i = 0; i < n; i++) {
            if (inorder[i] == rootVal) {
                rootIndex = i;
                break;
            }
        }
        TreeNode root = new TreeNode(rootVal);
        root.left = buildTree(Arrays.copyOfRange(preorder, 1, 1 + rootIndex), Arrays.copyOfRange(inorder, 0, rootIndex));
        root.right = buildTree(Arrays.copyOfRange(preorder, 1 + rootIndex, n), Arrays.copyOfRange(inorder, rootIndex + 1, n));

        return root;
    }
}
```

## 8、[二叉树的下一个结点](https://www.nowcoder.com/practice/9023a0c988684a53960365b889ceaf5e?tpId=13&tqId=11210&tPage=3&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

### 题目描述

给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回 。注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。

```java
public class TreeLinkNode {

    int val;
    TreeLinkNode left = null;
    TreeLinkNode right = null;
    TreeLinkNode next = null; // 指向父结点的指针

    TreeLinkNode(int val) {
        this.val = val;
    }
}
```

### 解题思路

我们先来回顾一下中序遍历的过程：先遍历树的左子树，再遍历根节点，最后再遍历右子树。所以最左节点是中序遍历的第一个节点。

```java
void traverse(TreeNode root) {
    if (root == null) return;
    traverse(root.left);
    visit(root);
    traverse(root.right);
}
```

![](https://cs-notes-1256109796.cos.ap-guangzhou.myqcloud.com/ad5cc8fc-d59b-45ce-8899-63a18320d97e.gif)

① 如果一个节点的右子树不为空，那么该节点的下一个节点是右子树的最左节点；

![](https://cs-notes-1256109796.cos.ap-guangzhou.myqcloud.com/7008dc2b-6f13-4174-a516-28b2d75b0152.gif)

② 否则，向上找第一个左链接指向的树包含该节点的祖先节点。

![](https://cs-notes-1256109796.cos.ap-guangzhou.myqcloud.com/094e3ac8-e080-4e94-9f0a-64c25abc695e.gif)



```java
public class T57 {
    public TreeLinkNode GetNext(TreeLinkNode pNode) {
        if (null == pNode) {
            return null;
        }
        if (null != pNode.right) {
            TreeLinkNode node = pNode.right;
            while (null != node.left) {
                node = node.left;
            }
            return node;
        }
        while (null != pNode.next) {
            TreeLinkNode parent = pNode.next;
            if (parent.left == pNode) {
                return parent;
            }
            pNode = pNode.next;
        }
        return null;
    }
}
```


## 9、[用两个栈实现一个队列](https://www.nowcoder.com/practice/54275ddae22f475981afa2244dd448c6?tpId=13&tqId=11158&tPage=1&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

### 题目描述

用两个栈来实现一个队列，完成队列的 Push 和 Pop 操作。

### 解题思路

in 栈用来处理入栈（push）操作，out 栈用来处理出栈（pop）操作。一个元素进入 in 栈之后，出栈的顺序被反转。当元素要出栈时，需要先进入 out 栈，此时元素出栈顺序再一次被反转，因此出栈顺序就和最开始入栈顺序是相同的，先进入的元素先退出，这就是队列的顺序。

![](https://cs-notes-1256109796.cos.ap-guangzhou.myqcloud.com/3ea280b5-be7d-471b-ac76-ff020384357c.gif)

```java
public class T5 {
    // 举个例子：
    // 1，2，3，4，5依次push
    // stack1：5，4，3，2，1 栈顶是5
    // stack2：1，2，3，4，5 栈顶是1 
    // 这样就是队列的先进先出了
    Stack<Integer> in = new Stack<>();

    Stack<Integer> out = new Stack<>();

    public void push (int node) {
      	// 添加value
        in.push(node);
    }
		
    public int pop() {
      	// 判断stack2是否为空
        if (out.isEmpty()) {
          	// 如果为空
            while (!in.isEmpty()) {
              	// 并且stack1不为空，然后将栈1所有的元素重新弹出去添加到栈2
              	// 这样的话，用栈2弹，就是FIFO的队列了
                out.push(stack1.pop());
            }
        }
        return out.pop();
    }
}
```

## 10.1、[斐波那契数列](https://www.nowcoder.com/practice/c6c7742f5ba7442aada113136ddea0c3?tpId=13&tqId=11160&tPage=1&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

### 题目描述

求斐波那契数列的第 n 项，n <= 39。

![](https://cs-notes-1256109796.cos.ap-guangzhou.myqcloud.com/45be9587-6069-4ab7-b9ac-840db1a53744.jpg)

### 解题思路

如果使用递归求解，会重复计算一些子问题。例如，计算 f(4) 需要计算 f(3) 和 f(2)，计算 f(3) 需要计算 f(2) 和 f(1)，可以看到 f(2) 被重复计算了。

![](https://cs-notes-1256109796.cos.ap-guangzhou.myqcloud.com/c13e2a3d-b01c-4a08-a69b-db2c4e821e09.png)

递归是将一个问题划分成多个子问题求解，动态规划也是如此，但是动态规划会把子问题的解缓存起来，从而避免重复求解子问题。

```java
public int Fibonacci(int n) {
    if (n <= 1)
        return n;
    int[] dp = new int[n + 1];
    dp[1] = 1;
    for (int i = 2; i <= n; i++)
        dp[i] = dp[i - 1] + dp[i - 2];
    return dp[n];
}

```

考虑到第 i 项只与第 i-1 和第 i-2 项有关，因此只需要存储前两项的值就能求解第 i 项，从而将空间复杂度由 O(N) 降低为 O(1)

```java
public class T7 {
    public int Fibonacci(int n) {
        // 条件
        if (n <= 1) return n;
       	// 可以用自底向上的方法
        int pre2 = 0, pre1 = 1;
        int f = 0;
        for (int i = 2; i <= n; i++) {
            f = pre2 + pre1; // 如果动态规划，这个就是dp的公式
            pre2 = pre1;
            pre1 = f;
        }
        return f;
    }
}
```
## 10.2、[跳台阶](https://www.nowcoder.com/practice/8c82a5b80378478f9484d87d1c5f12a4?tpId=13&tqId=11161&tPage=1&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

### 题目描述

一只青蛙一次可以跳上 1 级台阶，也可以跳上 2 级。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。

### 解题思路

![](https://cs-notes-1256109796.cos.ap-guangzhou.myqcloud.com/508c6e52-9f93-44ed-b6b9-e69050e14807.jpg)

```java
public class T8 {
    public int JumpFloor(int target) {
        // 条件
        if (target <= 2) return target;
      	// 自底向上的方法
        int pre2 = 1, pre1 = 2;
        int sum = 0;
        for (int i = 3; i <= target; i++) {
            sum = pre2 + pre1; // 一样的道理， 和上面那道题的初始值不一样
            pre2 = pre1;
            pre1 = sum;
        }
        return sum;
    }
}
```

## 10.3、[矩形覆盖](https://www.nowcoder.com/practice/72a5a919508a4251859fb2cfb987a0e6?tpId=13&tqId=11163&tPage=1&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

### 题目描述

我们可以用 2*1 的小矩形横着或者竖着去覆盖更大的矩形。请问用 n 个 2*1 的小矩形无重叠地覆盖一个 2*n 的大矩形，总共有多少种方法？

![](https://cs-notes-1256109796.cos.ap-guangzhou.myqcloud.com/b903fda8-07d0-46a7-91a7-e803892895cf.gif)

### 解题思路

![](https://cs-notes-1256109796.cos.ap-guangzhou.myqcloud.com/508c6e52-9f93-44ed-b6b9-e69050e14807.jpg)



```java
public class T10 {
    public int RectCover(int target) {
        // 条件
        if (target <= 2) return target;
      	// 自底向上
        int pre2 = 1, pre1 = 2;
        int sum = 0;
        for (int i = 3; i <= target; i++) {
            sum = pre2 + pre1; // 同理呀
            pre2 = pre1;
            pre1 = sum;
        }
        return sum;
    }
}
```


## 10.4、[变态跳台阶](https://www.nowcoder.com/practice/22243d016f6b47f2a6928b4313c85387?tpId=13&tqId=11162&tPage=1&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

### 题目描述

一只青蛙一次可以跳上 1 级台阶，也可以跳上 2 级... 它也可以跳上 n 级。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。

![](https://cs-notes-1256109796.cos.ap-guangzhou.myqcloud.com/cd411a94-3786-4c94-9e08-f28320e010d5.png)

### 解题思路

```java
public int JumpFloorII(int target) {
    int[] dp = new int[target];
    Arrays.fill(dp, 1);
    for (int i = 1; i < target; i++)
        for (int j = 0; j < i; j++)
            dp[i] += dp[j];
    return dp[target - 1];
}
```



```java
public class T9 {
    public int JumpFloorII(int target) {
      	// 公式 2的target-1次方
        return 1 << (target - 1);
    }
}
```


## 11、[旋转数组的最小数字](https://www.nowcoder.com/practice/9f3231a991af4f55b95579b44b7a01ba?tpId=13&tqId=11159&tPage=1&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

### 题目描述

把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。

![](https://cs-notes-1256109796.cos.ap-guangzhou.myqcloud.com/0038204c-4b8a-42a5-921d-080f6674f989.png)

```java
public int minNumberInRotateArray(int[] nums) {
    if (nums.length == 0)
        return 0;
    int l = 0, h = nums.length - 1;
    while (l < h) {
        int m = l + (h - l) / 2;
        if (nums[m] <= nums[h])
            h = m;
        else
            l = m + 1;
    }
    return nums[l];
}
```



```java
public class T6 {
    // 这道题也可以倒着遍历
    public int minNumberInRotateArray(int [] array) {
        // 判断条件
        if (array.length == 0) return 0;
        if (array.length == 1) return array[0];

        int a = array[0];
        // 根据数组的特征，一开始递增，突然变小，于是，那个突然变小的那个元素就是最小数字
        for (int i = 1; i < array.length; i++) {
            if (a > array[i]) {
                // array[i] < a,则代表最小
                return array[i];
            } else { // 否则 a
                a = array[i];
            }
        }
        return 0;
    }
}
```

## 12、[矩阵中的路径](https://www.nowcoder.com/practice/c61c6999eecb4b8f88a98f66b273a3cc?tpId=13&tqId=11218&tPage=4&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

### 题目描述

判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一个格子开始，每一步可以在矩阵中向上下左右移动一个格子。如果一条路径经过了矩阵中的某一个格子，则该路径不能再进入该格子。

例如下面的矩阵包含了一条 bfce 路径。

![](https://cs-notes-1256109796.cos.ap-guangzhou.myqcloud.com/1db1c7ea-0443-478b-8df9-7e33b1336cc4.png)

### 解题思路

使用回溯法（backtracking）进行求解，它是一种暴力搜索方法，通过搜索所有可能的结果来求解问题。回溯法在一次搜索结束时需要进行回溯（回退），将这一次搜索过程中设置的状态进行清除，从而开始一次新的搜索过程。例如下图示例中，从 f 开始，下一步有 4 种搜索可能，如果先搜索 b，需要将 b 标记为已经使用，防止重复使用。在这一次搜索结束之后，需要将 b 的已经使用状态清除，并搜索 c。

```java
private final static int[][] next = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
private int rows;
private int cols;

public boolean hasPath(char[][] matrix, int rows, int cols, char[] str) {
    if (rows == 0 || cols == 0) return false;
    this.rows = rows;
    this.cols = cols;
    boolean[][] marked = new boolean[rows][cols];
    for (int i = 0; i < rows; i++)
        for (int j = 0; j < cols; j++)
            if (backtracking(matrix, str, marked, 0, i, j))
                return true;

    return false;
}

private boolean backtracking(char[][] matrix, char[] str,
                             boolean[][] marked, int pathLen, int r, int c) {

    if (pathLen == str.length) return true;
    if (r < 0 || r >= rows || c < 0 || c >= cols
            || matrix[r][c] != str[pathLen] || marked[r][c]) {

        return false;
    }
    marked[r][c] = true;
    for (int[] n : next)
        if (backtracking(matrix, str, marked, pathLen + 1, r + n[0], c + n[1]))
            return true;
    marked[r][c] = false;
    return false;
}

```

```java
public class T65 {
    public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        if (null == matrix || matrix.length == 0 || null == str || str.length == 0 || matrix.length != rows * cols || rows <= 0 || cols <= 0){
            return false;
        }
        boolean[] visited = new boolean[rows * cols];
        int[] pathLength = {0};
        for (int i = 0; i <= rows - 1; i++){
            for (int j = 0; j <= cols - 1; j++){
                if (hasPathCore(matrix, rows, cols, str, i, j, visited, pathLength)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean hasPathCore(char[] matrix, int rows, int cols, char[] str,int row,int col, boolean[] visited,int[] pathLength) {
        boolean flag = false;

        if (row >= 0 && row < rows && col >= 0 && col < cols && !visited[row * cols + col] && matrix[row * cols + col] == str[pathLength[0]]) {
            pathLength[0]++;
            visited[row * cols + col] = true;
            if (pathLength[0] == str.length) {
                return true;
            }
            flag = hasPathCore(matrix, rows, cols, str, row, col + 1, visited, pathLength) || hasPathCore(matrix, rows, cols, str, row + 1, col, visited, pathLength) || hasPathCore(matrix, rows, cols, str, row, col - 1, visited, pathLength) || hasPathCore(matrix, rows, cols, str, row - 1, col, visited, pathLength);
            if (!flag) {
                pathLength[0]--;
                visited[row * cols + col] = false;
            }
        }
        return flag;
    }
}
```

## 13、[机器人的运动范围](https://www.nowcoder.com/practice/6e5207314b5241fb83f2329e89fdecc8?tpId=13&tqId=11219&tPage=4&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

### 题目描述

地上有一个 m 行和 n 列的方格。一个机器人从坐标 (0, 0) 的格子开始移动，每一次只能向左右上下四个方向移动一格，但是不能进入行坐标和列坐标的数位之和大于 k 的格子。

例如，当 k 为 18 时，机器人能够进入方格 (35,37)，因为 3+5+3+7=18。但是，它不能进入方格 (35,38)，因为 3+5+3+8=19。请问该机器人能够达到多少个格子？

### 解题思路

使用深度优先搜索（Depth First Search，DFS）方法进行求解。回溯是深度优先搜索的一种特例，它在一次搜索过程中需要设置一些本次搜索过程的局部状态，并在本次搜索结束之后清除状态。而普通的深度优先搜索并不需要使用这些局部状态，虽然还是有可能设置一些全局状态。

```java
public class T66 {
    public int movingCount(int threshold, int rows, int cols) {
        boolean[][] visited = new boolean[rows][cols]; // 设置状态
        return countingStep(threshold,rows,cols,0,0,visited);
    }

    public int countingStep(int limit, int rows, int cols, int r, int c, boolean[][] visited) {
        if (r < 0 || r >= rows || 
            c < 0 || c >= cols || 
            visited[r][c] || 
            bitSum(r) + bitSum(c) > limit) return 0;
        visited[r][c] = true;
        return countingStep(limit,rows,cols,r - 1,c,visited) + countingStep(limit,rows,cols,r,c - 1,visited) + countingStep(limit,rows,cols,r+1,c,visited) + countingStep(limit,rows,cols,r,c+1,visited) + 1;
    }

    public int bitSum(int t) {
        int count = 0;
        while (t != 0) {
            count += t % 10;
            t /= 10;
        }
        return count;
    }
}

```

## 14、[剪绳子](https://www.nowcoder.com/practice/57d85990ba5b440ab888fc72b0751bf8?tpId=13&tqId=33257&tPage=4&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

### 题目描述

把一根绳子剪成多段，并且使得每段的长度乘积最大。

```html
n = 2
return 1 (2 = 1 + 1)

n = 10
return 36 (10 = 3 + 3 + 4)

```

### 解题思路

```java
// 动态规划
public int integerBreak(int n) {
    int[] dp = new int[n + 1];
    dp[1] = 1;
    for (int i = 2; i <= n; i++)
        for (int j = 1; j < i; j++)
            dp[i] = Math.max(dp[i], Math.max(j * (i - j), dp[j] * (i - j)));
    return dp[n];
}

```

```java
public class T67 {
    // 动态规划
    public int cutRope(int target) {
        if (target < 2) return 0;
        if (target == 2) return 1;
        if (target == 3) return 2;
        int[] products = new int[target + 1];
        products[0] = 0;
        products[1] = 1; // 长度为2...
        products[2] = 2; // 长度为3...
        products[3] = 3; // 长度为4...
        int max = 0;
        for (int i = 4; i <= target; i++) {
            max = 0;
            for (int j = 1; j <= i / 2; j++) {
                int product = products[j] * products[i - j];
                max = max > product ? max : product;
                products[i] = max;
            }
        }
        max = products[target];
        return max;
    }
}
```


## 15、[二进制1的个数](https://www.nowcoder.com/practice/8ee967e43c2c4ec193b040ea7fbb10b8?tpId=13&tqId=11164&tPage=1&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

### 题目描述

输入一个整数，输出该数二进制表示中 1 的个数。

### 解题思路

(n-1) & n ：该位运算去除 n 的位级表示中最低的那一位。

```java
public class T11 {
    public int NumberOf1(int n) {
        int count = 0;
        while ( n!= 0) {
            count++;
          	// (n-1) & n 注意这个。。 
            n = (n - 1) & n;
        }
        return count;
    }
}
```

## 16、[数值的整数次方](https://www.nowcoder.com/practice/1a834e5e3e1a4b7ba251417554e07c00?tpId=13&tqId=11165&tPage=1&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

### 题目描述

给定一个 double 类型的浮点数 base 和 int 类型的整数 exponent，求 base 的 exponent 次方。

### 解题思路

![](https://cs-notes-1256109796.cos.ap-guangzhou.myqcloud.com/48b1d459-8832-4e92-938a-728aae730739.jpg)

```java
public double Power(double base, int exponent) {
    if (exponent == 0)
        return 1;
    if (exponent == 1)
        return base;
    boolean isNegative = false;
    if (exponent < 0) {
        exponent = -exponent;
        isNegative = true;
    }
    // 递归走起，速度快
    double pow = Power(base * base, exponent / 2);
    if (exponent % 2 != 0)
        pow = pow * base;
    return isNegative ? 1 / pow : pow;
}

```

```java
public class T12 {
    public double Power(double base, int exponent) {
        // 还是先判断特殊情况，是0？还是>0，还是<0?
        if (exponent == 0) return 1;
        double ans = 1;
        boolean flag = false; // 判断倒数
        // 如果小于0，取绝对值
        if (exponent < 0) {
            flag = true;
            exponent = -exponent;
        }
        for (int i = 1; i <= exponent; i++) {
            ans *= base;
        }
        // 如果小于0，不仅取绝对值，还要最终求倒数
        if (flag) {
            ans = 1 / ans;
        }
        return ans;
    }
}
```


## 18、[删除链表中重复的结点](https://www.nowcoder.com/practice/fc533c45b73a41b0b44ccba763f866ef?tpId=13&tqId=11209&tPage=3&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

### 题目描述

![](https://cs-notes-1256109796.cos.ap-guangzhou.myqcloud.com/17e301df-52e8-4886-b593-841a16d13e44.png)

```java
public ListNode deleteDuplication(ListNode pHead) {
    if (pHead == null || pHead.next == null)
        return pHead;
    ListNode next = pHead.next;
    if (pHead.val == next.val) {
        while (next != null && pHead.val == next.val)
            next = next.next;
        return deleteDuplication(next);
    } else {
        pHead.next = deleteDuplication(pHead.next);
        return pHead;
    }
}

```

```java
public class T56 {
    public ListNode deleteDuplication(ListNode pHead) {
        // 只有0个或1个节点，则返回。
        if (null == pHead || pHead.next == null) {
            return pHead;
        }
        // 当前节点是重复节点
        if (pHead.val == pHead.next.val) {
            ListNode pNode = pHead.next;
            while (pNode != null && pHead.val == pNode.val) {
                pNode = pNode.next; // 是不是一直重复， so while
            }
            return deleteDuplication(pNode); // 递归继续
        } else {
            // 当前节点不是重复节点
            // 保留当前节点，从下一个节点开始递归
            pHead.next = deleteDuplication(pHead.next);
            return pHead;
        }
    }
}
```

## 19、[正则表达式匹配](https://www.nowcoder.com/practice/45327ae22b7b413ea21df13ee7d6429c?tpId=13&tqId=11205&tPage=3&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

### 题目描述

请实现一个函数用来匹配包括 '.' 和 '*' 的正则表达式。模式中的字符 '.' 表示任意一个字符，而 '*' 表示它前面的字符可以出现任意次（包含 0 次）。

在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串 "aaa" 与模式 "a.a" 和 "ab*ac*a" 匹配，但是与 "aa.a" 和 "ab*a" 均不匹配。

```java
public class T52 {
    public boolean match(char[] str, char[] pattern) {
        if (str == null || pattern == null) {
            return false;
        }
        int strIndex = 0;
        int patternIndex = 0;
        return matchCore(str, strIndex, pattern, patternIndex);
    }

    public boolean matchCore(char[] str, int strIndex, char[] pattern, int patternIndex) {
        // 有效性校验：str到尾， pattern到尾，匹配成功
        if (strIndex == str.length && patternIndex == pattern.length) {
            return true;
        }
        // pattern 先到尾，匹配失败
        if (strIndex != str.length && patternIndex == pattern.length) {
            return false;
        }
        // 模式第二个是*，且字符串第一个根模式第一个匹配，分三种匹配模式；
        // 如果不匹配，模式后移两
        if (patternIndex + 1 < pattern.length && pattern[patternIndex + 1] == '*') {
            if ((strIndex != str.length && pattern[patternIndex] == str[strIndex]) || (pattern[patternIndex] == '.' && strIndex != str.length)) {
                return matchCore(str, strIndex, pattern, patternIndex + 2) || matchCore(str, strIndex + 1, pattern, patternIndex + 2) || matchCore(str, strIndex + 1, pattern, patternIndex);
            } else {
                return matchCore(str, strIndex, pattern, patternIndex + 2);
            }
        }
        // 模式第二个不是*，且字符串第一个根模式第一个匹配，则都后移一位，否则直接返回false
        if ((strIndex != str.length && pattern[patternIndex] == str[strIndex])|| (pattern[patternIndex] == '.' && strIndex != str.length)) {
            return matchCore(str, strIndex + 1, pattern, patternIndex + 1);
        }
        return false;
    }
}

```

## 20、[表示数值的字符串](https://www.nowcoder.com/practice/6f8c901d091949a5837e24bb82a731f2?tpId=13&tqId=11206&tPage=3&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

### 题目描述

```html
true

"+100"
"5e2"
"-123"
"3.1416"
"-1E-16"
```

```html
false

"12e"
"1a3.14"
"1.2.3"
"+-5"
"12e+4.3"
```

### 解题思路

```html
[]  ： 字符集合
()  ： 分组
?   ： 重复 0 ~ 1 次
+   ： 重复 1 ~ n 次
*   ： 重复 0 ~ n 次
.   ： 任意字符
\\. ： 转义后的 .
\\d ： 数字
```


```java
public boolean isNumeric(char[] str) {
    if (str == null || str.length == 0)
        return false;
    return new String(str).matches("[+-]?\\d*(\\.\\d+)?([eE][+-]?\\d+)?");
}

```

```java
public class T53 {
    public boolean isNumeric(char[] str) {
        String s = String.valueOf(str);
        // 正则大法好？
        return s.matches("[+-]?[0-9]*(\\.[0-9]*)?([eE][+-]?[0-9]+)?");
    }
}
```


## 21、[调整数组顺序使奇数位于偶数前面](https://www.nowcoder.com/practice/beb5aa231adc45b2a5dcc5b62c93f593?tpId=13&tqId=11166&tPage=1&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

### 题目描述

需要保证奇数和奇数，偶数和偶数之间的相对位置不变，这和书本不太一样。

![](https://cs-notes-1256109796.cos.ap-guangzhou.myqcloud.com/d03a2efa-ef19-4c96-97e8-ff61df8061d3.png)

### 解题思路

方法一：创建一个新数组，时间复杂度 O(N)，空间复杂度 O(N)。

```java
public void reOrderArray(int[] nums) {
    // 奇数个数
    int oddCnt = 0;
    for (int x : nums)
        if (!isEven(x))
            oddCnt++;
    int[] copy = nums.clone();
    int i = 0, j = oddCnt;
    for (int num : copy) {
        if (num % 2 == 1)
            nums[i++] = num;
        else
            nums[j++] = num;
    }
}

private boolean isEven(int x) {
    return x % 2 == 0;
}
```

方法二：冒泡

```java
public class T13 {
    public void reOrderArray(int [] array) {
        // 边界判断
        if (array == null || array.length == 0) return;
        for (int i = 0; i < array.length; i++) {
            // 循环n次
            for (int j = 0; j < array.length - 1 - i; j++) {
                // 每次循环，找到当前元素为偶数，下一个元素为奇数，则交换
                if ((array[j] & 0x1) == 0 && (array[j + 1] & 0x1) == 1) {
                    swap(array, j, j + 1);
                }
            }
        }
    }

    /**
     * 数据交换
     * @param arr
     * @param x
     * @param y
     */
    private void  swap(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }
}
```

## 22、[链表中倒数第k个结点](https://www.nowcoder.com/practice/529d3ae5a407492994ad2a246518148a?tpId=13&tqId=11167&tPage=1&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

### 解题思路

设链表的长度为 N。设置两个指针 P1 和 P2，先让 P1 移动 K 个节点，则还有 N - K 个节点可以移动。此时让 P1 和 P2 同时移动，可以知道当 P1 移动到链表结尾时，P2 移动到第 N - K 个节点处，该位置就是倒数第 K 个节点。

![](https://cs-notes-1256109796.cos.ap-guangzhou.myqcloud.com/6b504f1f-bf76-4aab-a146-a9c7a58c2029.png)

```java
public ListNode FindKthToTail(ListNode head, int k) {
    if (head == null)
        return null;
    ListNode P1 = head;
    while (P1 != null && k-- > 0)
        P1 = P1.next;
    if (k > 0)
        return null;
    ListNode P2 = head;
    while (P1 != null) {
        P1 = P1.next;
        P2 = P2.next;
    }
    return P2;
}
```



```java
public class T14 {
    /**
     * 栈
     * @param head
     * @param k
     * @return
     */
    public ListNode FindKthToTail2(ListNode head,int k) {
        // 边界判断
        if (head == null || k <= 0) return null;
        Stack<ListNode> stack = new Stack<>();
        // 遍历将元素压栈
        while (head != null) {
            stack.push(head);
            head = head.next;
        }
        // 弹栈k次
        int temp = 0;
        while (!stack.empty()) {
            ListNode listNode = stack.pop();
            temp++;
            if (temp == k) {
                return listNode;
            }
        }
        return null;
    }
}
```

## 23、[链表中环的入口结点](https://www.nowcoder.com/practice/253d2c59ec3e4bc68da16833f79a38e4?tpId=13&tqId=11208&tPage=3&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

### 题目描述

一个链表中包含环，请找出该链表的环的入口结点。要求不能使用额外的空间。

### 解题思路

使用双指针，一个快指针 fast 每次移动两个节点，一个慢指针 slow 每次移动一个节点。因为存在环，所以两个指针必定相遇在环中的某个节点上。

![](https://cs-notes-1256109796.cos.ap-guangzhou.myqcloud.com/bb7fc182-98c2-4860-8ea3-630e27a5f29f.png)

```java
public ListNode EntryNodeOfLoop(ListNode pHead) {
    if (pHead == null || pHead.next == null)
        return null;
    ListNode slow = pHead, fast = pHead;
    do {
        fast = fast.next.next;
        slow = slow.next;
    } while (slow != fast); // 相遇点
    fast = pHead;
    while (slow != fast) { // fast从头走，slow还从相遇点走
        slow = slow.next;
        fast = fast.next;
    }
    return slow;
}
```



```java
public class T55 {
    // 哈希 可以用set
    public ListNode EntryNodeOfLoop(ListNode pHead) {
        if (null == pHead) {
            return null;
        }
        HashMap<ListNode, Integer> map = new HashMap<>();
        map.put(pHead, 1);
        while (null != pHead.next) {
            // 入口节点肯定会被map包含
            if (map.containsKey(pHead.next)) {
                return pHead.next;
            }
            map.put(pHead.next, 1);
            pHead = pHead.next;
        }
        return null;
    }
}
```




## 24、[反转链表](https://www.nowcoder.com/practice/75e878df47f24fdc9dc3e400ec6058ca?tpId=13&tqId=11168&tPage=1&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

```java
public ListNode reverseList(ListNode head) {
    ListNode pre = null; // 当前节点之前的节点 null
    ListNode cur = head;
    while (cur != null) {
        ListNode nextTemp = cur.next; // 获取当前节点的下一个节点
        cur.next = pre; // 当前节点的下个节点指向前一个节点
        // 尾递归其实省了下面这两步
        pre = cur; // 将前一个节点指针移动到当前指针
        cur = nextTemp; // 当当前节点移动到下一个节点
    }
    return pre;
}
```

```java
public class T15 {
    public ListNode ReverseList(ListNode head) {
        // 判断
        if (head == null) return null;
        return reverse(null, head);
    }

    /**
     * 尾递归
     * @param pre
     * @param cur
     * @return
     */
    private ListNode reverse(ListNode pre, ListNode cur) {
        // 递归边界值判断
        if (cur == null) return pre;
        // next节点指向cur.next
        ListNode next = cur.next;
        // cur.next 连接pre
        cur.next = pre;
        return reverse(cur, next);
    }
}
```

## 25、[合并两个排序的链表](https://www.nowcoder.com/practice/d8b6b4358f774294a89de2a6ac4d9337?tpId=13&tqId=11169&tPage=1&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

### 题目描述

![](https://cs-notes-1256109796.cos.ap-guangzhou.myqcloud.com/c094d2bc-ec75-444b-af77-d369dfb6b3b4.png)

### 解题思路

```java
public class T16 {
    public ListNode Merge(ListNode list1,ListNode list2) {
        // 边界值判断
        // 如果list1为空，返回list2
        if (list1 == null) return list2;
        // 如果list2为空，返回list1
        if (list2 == null) return list1;
        // 如果list1.val < list2.val，则list1.next连接下一个比较值（递归比较）
        if (list1.val < list2.val) {
            list1.next = Merge(list1.next, list2);
            return list1;
        } else {
            // 否则，list2.next 连接下一个比较值（递归比较）
            list2.next = Merge(list1, list2.next);
            return list2;
        }
    }
}
```
## 26、[树的子结构](https://www.nowcoder.com/practice/6e196c44c7004d15b1610b9afca8bd88?tpId=13&tqId=11170&tPage=1&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

### 题目描述

![](https://cs-notes-1256109796.cos.ap-guangzhou.myqcloud.com/84a5b15a-86c5-4d8e-9439-d9fd5a4699a1.jpg)

### 解题思路

```java
public class T17 {
	public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null)
            return false;
        return isSubtreeWithRoot(root1, root2) || HasSubtree(root1.left, root2) || HasSubtree(root1.right, root2);
	}
    
	private boolean isSubtreeWithRoot(TreeNode root1, TreeNode root2) {
        if (root2 == null)
            return true;
        if (root1 == null)
            return false;
        if (root1.val != root2.val)
            return false;
        return isSubtreeWithRoot(root1.left, root2.left) && isSubtreeWithRoot(root1.right, root2.right);
	}

}
```

## 27、[二叉树的镜像](https://www.nowcoder.com/practice/564f4c26aa584921bc75623e48ca3011?tpId=13&tqId=11171&tPage=1&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

### 题目描述

![](https://cs-notes-1256109796.cos.ap-guangzhou.myqcloud.com/0c12221f-729e-4c22-b0ba-0dfc909f8adf.jpg)

### 解题思路

```java
public class T18 {
    public void Mirror(TreeNode root) {
        // 判断
        if (root == null) return;
        swap(root);
        Mirror(root.left);
        Mirror(root.right);

    }

    private void swap(TreeNode root) {
        TreeNode t = root.left;
        root.left = root.right;
        root.right = t;
    }
}
```




## 28、[对称的二叉树](https://www.nowcoder.com/practice/ff05d44dfdb04e1d83bdbdab320efbcb?tpId=13&tqId=11211&tPage=3&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

### 题目描述

![](https://cs-notes-1256109796.cos.ap-guangzhou.myqcloud.com/0c12221f-729e-4c22-b0ba-0dfc909f8adf.jpg)

### 解题思路

```java
public class T58 {
    boolean isSymmetrical(TreeNode pRoot) {
        if (null == pRoot) {
            return true;
        }
        return comRoot(pRoot.left, pRoot.right);
    }

    private boolean comRoot(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
		if (left == null || right == null) {
            return false;
        }
        if (left.val != right.val) {
            return false;
        }
		// 左右对比
        return comRoot(left.right, right.left) && comRoot(left.left, right.right);
    }
}
```

## 29、[顺时针打印矩阵](https:/www.nowcoder.com/practice/9b4c81a02cd34f76be2659fa0d54342a?tpId=13&tqId=11172&tPage=1&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

### 题目描述

下图的矩阵顺时针打印结果为：1, 2, 3, 4, 8, 12, 16, 15, 14, 13, 9, 5, 6, 7, 11, 10

![](https://cs-notes-1256109796.cos.ap-guangzhou.myqcloud.com/48517227-324c-4664-bd26-a2d2cffe2bfe.png)

### 解题思路

```java
public class T19 {
    public ArrayList<Integer> printMatrix(int [][] matrix) {
        ArrayList<Integer> list = new ArrayList<>();
        int r1 = 0, r2 = matrix.length - 1, c1 = 0, c2 = matrix[0].length - 1;
        while(r1 <= r2 && c1 <= c2) {
            for (int i = c1; i <= c2; i++) {
                list.add(matrix[r1][i]);
            }
            for (int i = r1 + 1; i <= r2; i++) {
                list.add(matrix[i][c2]);
            }
            if (r1 != r2) {
                for (int i = c2 - 1; i >= c1; i--) {
                    list.add(matrix[r2][i]);
                }
            }
            if (c1 != c2) {
                for (int i = r2 - 1; i >= r1; i--) {
                    list.add(matrix[i][c1]);
                }
            }
            r1++; r2--; c1++; c2--;
        }
        return list;
    }
}
```

## 30、[包含min函数的栈](https://www.nowcoder.com/practice/4c776177d2c04c2494f2555c9fcc1e49?tpId=13&tqId=11173&tPage=1&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

### 题目描述

定义栈的数据结构，请在该类型中实现一个能够得到栈最小元素的 min 函数。

### 解题思路

```java
public class T20 {

    private Stack<Integer> dataStack = new Stack<>();
    private Stack<Integer> minStack = new Stack<>();

    public void push(int node) {
        dataStack.push(node);// dataStack添加元素
        minStack.push(minStack.isEmpty() ? node : Math.min(minStack.peek(), node));
    }

    public void pop() {
        dataStack.pop();
        // 辅助栈也得弹，因为每次push， 辅助栈也在push
        minStack.pop();
    }
	// 栈顶，没啥可说的
    public int top() {
        return dataStack.peek();
    }
	// 最小值，辅助栈弹就完事了
    public int min() {
        return minStack.peek();
    }
}
```

## 31、[栈的压入、弹出序列](https://www.nowcoder.com/practice/d77d11405cc7470d82554cb392585106?tpId=13&tqId=11174&tPage=2&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

### 题目描述

输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。

例如序列 1,2,3,4,5 是某栈的压入顺序，序列 4,5,3,2,1 是该压栈序列对应的一个弹出序列，但 4,3,5,1,2 就不可能是该压栈序列的弹出序列。

### 解题思路

```java
public class T21 {
    public boolean IsPopOrder(int [] pushA,int [] popA) {
        if (pushA == null || popA == null) return false;
        int p = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < pushA.length; i++) {
            // 遍历压栈
            stack.push(pushA[i]);
            // 每压一次， 就要将栈顶的元素和弹出序列判断是否相等
            // 如果相等，栈顶元素弹出，p++，继续while，
            while (!stack.isEmpty() && stack.peek() == popA[p]) {
                stack.pop();
                p++;
            }
        }
        // 如果最后栈为空了， 说明压入序列和弹出序列一致
        return stack.isEmpty();
    }
}
```

## 32.1、[从上往下打印二叉树](https://www.nowcoder.com/practice/7fe2212963db4790b57431d9ed259701?tpId=13&tqId=11175&tPage=2&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

### 题目描述

从上往下打印出二叉树的每个节点，同层节点从左至右打印。

例如，以下二叉树层次遍历的结果为：1,2,3,4,5,6,7

![](https://cs-notes-1256109796.cos.ap-guangzhou.myqcloud.com/d5e838cf-d8a2-49af-90df-1b2a714ee676.jpg)

### 解题思路

使用队列来进行层次遍历。

不需要使用两个队列分别存储当前层的节点和下一层的节点，因为在开始遍历一层的节点时，当前队列中的节点数就是当前层的节点数，只要控制遍历这么多节点数，就能保证这次遍历的都是当前层的节点。

```java
public class T22 {
    // 层序遍历 
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        // 需要用到队列
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root); // 第一次先加根入队
        while (!queue.isEmpty()) {
            int cnt = queue.size();
            // 如果队列不为空的话， 队列出一个元素
            while(cnt-- > 0) {
                TreeNode t = queue.poll();
                if (t == null) continue;
                list.add(t.val);
                queue.add(t.left);
                queue.add(t.right);
            }
        }
        return list;
    }
}
```

## 31.2、[把二叉树打印多行](https://www.nowcoder.com/practice/445c44d982d04483b04a54f298796288?tpId=13&tqId=11213&tPage=3&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

### 题目描述

和22题：从上往下打印二叉树 差不多

```java
public class T60 {

    ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
		Queue<TreeNode> queue = new LinkedList<>();
        queue.add(pRoot);
        while (!queue.isEmpty()) {
            ArrayList<Integer> list = new ArrayList<>();
            int cnt = queue.size();
            while (cnt-- > 0) {
                TreeNode node = queue.poll();
                if (node == null) continue;
                list.add(node.val);
                queue.add(node.left);
                queue.add(node.right);
            }
            if (list.size() != 0) ret.add(list);
        }
        return ret;
    }
}
```

## 32.3、[按之字形顺序打印二叉树](https://www.nowcoder.com/practice/91b69814117f4e8097390d107d2efbe0?tpId=13&tqId=11212&tPage=3&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

### 题目描述

请实现一个函数按照之字形打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右至左的顺序打印，第三行按照从左到右的顺序打印，其他行以此类推。

### 解题思路

```java
public class T59 {
    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(pRoot);
        boolean reverse = false;
        while (! queue.isEmpty()) {
            ArrayList<Integer> list = new ArrayList<>();
            int cnt = queue.size();
            while (cnt-- > 0) {
                TreeNode node = queue.poll();
                if (node == null) continue;
                list.add(node.val);
                queue.add(node.left);
                queue.add(node.right);
            }
            if (reverse) Collections.reverse(list);
            reverse = ! reverse;
            if (list.size() != 0) ret.add(list);
        }
        reutrn ret;
}
```



## 33、[二叉搜索树的后序遍历序列](https://www.nowcoder.com/practice/a861533d45854474ac791d90e447bafd?tpId=13&tqId=11176&tPage=2&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

### 题目描述

输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。假设输入的数组的任意两个数字都互不相同。

例如，下图是后序遍历序列 1,3,2 所对应的二叉搜索树

![](https://cs-notes-1256109796.cos.ap-guangzhou.myqcloud.com/13454fa1-23a8-4578-9663-2b13a6af564a.jpg)

### 解题思路

```java
public class T23 {
    public boolean VerifySquenceOfBST(int [] sequence)  {
        if (sequence == null || sequence.length == 0) return false;
        return isBST(sequence, 0, sequence.length - 1);
    }
	private boolean isBST(int[] sequence, int first, int last) {
        if (last - first <= 1) {
            return true;
        }
        int rootVal = sequence[last];
        int cutIndex = first;
        while (cutIndex < last && sequence[curIndex] <= rootVal) { // 二叉搜索树特征
            cutIndex++;
        }
        for (int i = cutIndedx; i < last; i++) {
            if (sequence[i] < rootVal) return false;
        }
        return isBST(sequence, first, cutIndex - 1) && isBST(sequence, cutIndex, last - 1);
    }
}
```

## 34、[二叉树中和为某一值的路径](https://www.nowcoder.com/practice/b736e784e3e34731af99065031301bca?tpId=13&tqId=11177&tPage=2&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

### 题目描述

输入一颗二叉树和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。

下图的二叉树有两条和为 22 的路径：10, 5, 7 和 10, 12

![](https://cs-notes-1256109796.cos.ap-guangzhou.myqcloud.com/ed77b0e6-38d9-4a34-844f-724f3ffa2c12.jpg)

### 解题思路

```java
public class T24 {

    private ArrayList<ArrayList<Integer>> ret = new ArrayList<>();

    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        backtracking(root, target, new ArrayList<>());
        return ret;
    }

    private void backtracking(TreeNode node, int target, ArrayList<Integer> path) {
        if (node == null)
            return;
        path.add(node.val);
        target -= node.val;
        if (target == 0 && node.left == null && node.right == null) {
            ret.add(new ArrayList<>(path));
        } else {
            backtracking(node.left, target, path);
            backtracking(node.right, target, path);
        }
        path.remove(path.size() - 1);
    }
}
```

## 35、[复杂链表的复制](https://www.nowcoder.com/practice/f836b2c43afc4b35ad6adc41ec941dba?tpId=13&tqId=11178&tPage=2&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

### 题目描述

输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，另一个特殊指针指向任意一个节点），返回结果为复制后复杂链表的 head。

```html
public class RandomListNode {
    int label;
    RandomListNode next = null;
    RandomListNode random = null;

    RandomListNode(int label) {
        this.label = label;
    }
}

```

![](https://cs-notes-1256109796.cos.ap-guangzhou.myqcloud.com/66a01953-5303-43b1-8646-0c77b825e980.png)

### 解题思路

第一步，在每个节点的后面插入复制的节点。

![](https://cs-notes-1256109796.cos.ap-guangzhou.myqcloud.com/dfd5d3f8-673c-486b-8ecf-d2082107b67b.png)

第二步，对复制节点的 random 链接进行赋值。

![](https://cs-notes-1256109796.cos.ap-guangzhou.myqcloud.com/cafbfeb8-7dfe-4c0a-a3c9-750eeb824068.png)

第三步，拆分。

![](https://cs-notes-1256109796.cos.ap-guangzhou.myqcloud.com/e151b5df-5390-4365-b66e-b130cd253c12.png)

```java
public class T25 {
    public RandomListNode Clone(RandomListNode pHead) {
        if (pHead == null) return null;
        // 第一步：先复制一遍next
        RandomListNode node = pHead;
        while (node != null) {
            RandomListNode copyNode = new RandomListNode(node.label);
            copyNode.next = node.next;
            node.next = copyNode;
            node = copyNode.next;
        }
        // 第二步：再复制一遍random
        node = pHead;
        while (node != null) {
            node.next.random = node.random == null ? null : node.random.next;
            node = node.next.next;
        }
        // 第三步：切开
        node = pHead;
        RandomListNode pCloneHead = pHead.next;
        while (node != null) {
            RandomListNode copyNode = node.next;
            node.next = copyNode.next;
            copyNode.next = copyNode.next == null ? null : copyNode.next.next;
            node = node.next;
        }
        return pCloneHead;
    }
}
```

## 36、[二叉搜索树与双向链表](https://www.nowcoder.com/practice/947f6eb80d944a84850b0538bf0ec3a5?tpId=13&tqId=11179&tPage=2&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

### 题目描述

输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。要求不能创建任何新的结点，只能调整树中结点指针的指向。

![](https://cs-notes-1256109796.cos.ap-guangzhou.myqcloud.com/05a08f2e-9914-4a77-92ef-aebeaecf4f66.jpg)

### 解题思路

```java
public class T26 {
    private TreeNode pre = null;
    private TreeNode head = null;
    
    public TreeNode Convert(TreeNode root) {
 		inOrder(root);
        return head;
    }
    
    private void inOrder(TreeNode node) {
        if (node == null) return;
        inOrder(node.left);
        node.left = pre;
        if (pre != null) 
            pre.right = node;
        pre = node;
        if (head == null)
            head = node;
        inOrder(node.right);
    }
}
```

## 36、[序列化二叉树](https://github.com/DreamCats/leetniu/blob/master/nowcoder.com/practice/cf7e25aa97c04cc1a68c8f040e71fb84?tpId=13&tqId=11214&tPage=4&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

```java
public class T61 {
    String Serialize(TreeNode root) {
        if (null == root) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        Serialize2(root, sb);
        return sb.toString();
    }

    void Serialize2(TreeNode root, StringBuffer sb) {
        if (null == root) {
            sb.append("#,");
            return;
        }
        sb.append(root.val);
        sb.append(",");
        Serialize2(root.left, sb);
        Serialize2(root.right, sb);
    }
    int index = -1;

    TreeNode Deserialize(String str) {
        if (str.length() == 0) {
            return null;
        }
        String[] strings = str.split(",");
        return Deserialize2(strings);
    }
    TreeNode Deserialize2(String[] strings) {
        index++;
        if (!strings[index].equals("#")) {
            TreeNode root = new TreeNode(0);
            root.val = Integer.parseInt(strings[index]);
            root.left = Deserialize2(strings);
            root.right = Deserialize2(strings);
            return root;
        }
        return null;
    }
}

```

## 38、[字符串的排列](https://www.nowcoder.com/practice/fe6b651b66ae47d7acce78ffdd9a96c7?tpId=13&tqId=11180&tPage=2&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

### 题目描述

输入一个字符串，按字典序打印出该字符串中字符的所有排列。例如输入字符串 abc，则打印出由字符 a, b, c 所能排列出来的所有字符串 abc, acb, bac, bca, cab 和 cba。

```java
private ArrayList<String> ret = new ArrayList<>();

public ArrayList<String> Permutation(String str) {
    if (str.length() == 0)
        return ret;
    char[] chars = str.toCharArray();
    Arrays.sort(chars);
    backtracking(chars, new boolean[chars.length], new StringBuilder());
    return ret;
}

private void backtracking(char[] chars, boolean[] hasUsed, StringBuilder s) {
    if (s.length() == chars.length) {
        ret.add(s.toString());
        return;
    }
    for (int i = 0; i < chars.length; i++) {
        if (hasUsed[i])
            continue;
        if (i != 0 && chars[i] == chars[i - 1] && !hasUsed[i - 1]) /* 保证不重复 */
            continue;
        hasUsed[i] = true;
        s.append(chars[i]);
        backtracking(chars, hasUsed, s);
        s.deleteCharAt(s.length() - 1);
        hasUsed[i] = false;
    }
}

```

### 解题思路

```java
public class T27 {
    public ArrayList<String> Permutation(String str) {
        ArrayList<String> result = new ArrayList<>();
        if(str == null || str.length() == 0) {
            return result;
        }
        char[] chars = str.toCharArray();
        TreeSet<String> temp = new TreeSet<>();
        Permutation(chars, 0, temp);
        result.addAll(temp);
        return result;
    }

    private void Permutation(char[] chars, int begin, TreeSet<String> result) {
        if (chars == null || chars.length == 0 || begin < 0 || begin > chars.length - 1) {
            return;
        }
        if (begin == chars.length - 1) {
            result.add(String.valueOf(chars));
        } else {
            for (int i = begin; i <= chars.length - 1; i++) {
                swap(chars, begin, i);
                Permutation(chars, begin + 1, result);
                swap(chars, begin, i);
            }
        }
    }
    private void swap(char[] x, int a, int b) {
        char t = x[a];
        x[a] = x[b];
        x[b] = t;
    }
}
```

## 39、[数组中出现次数超过一半的数字](https://www.nowcoder.com/practice/e8a1b01a2df14cb2b228b30ee6a92163?tpId=13&tqId=11181&tPage=2&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

### 解题思路

```java
public class T28 {
    public int MoreThanHalfNum_Solution(int [] array) {
        // 哈希的方法
        HashMap<Integer, Integer> map = new HashMap<>();
        // 遍历一次每个元素的个数
        for (int i = 0; i < array.length; i++) {
            if (map.containsKey(array[i])) {
                map.put(array[i], map.get(array[i]) + 1);
            } else {
                map.put(array[i], 1);
            }
        }
        int length = array.length >> 1;
        // 查找哪个数的次数超过一半
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > length) {
                return entry.getKey();
            }
        }
        return 0;
    }
}
```

## 40、[最小的k个数](https://www.nowcoder.com/practice/6a296eb82cf844ca8539b57c23e6e9bf?tpId=13&tqId=11182&tPage=2&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

### 解题思路

```java
public class T29 {
    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> integers = new ArrayList<>();
        // 边界条件
        if (k > input.length){
            return integers;
        }
        // 先排序。。。 这里直接用Arrays.sort排序
        for (int i = 1; i < input.length; i++) {
            for (int j = 0; j < input.length - i; j++) {
                if (input[j] > input[j + 1]) {
                    swap(input, j, j + 1);
                }
            }
        }
        // 然后取k个数
        for (int i = 0; i < k; i++) {
            integers.add(input[i]);
        }
        return integers;
    }
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
```

## 41.1、[数据流中的中位数](https://www.nowcoder.com/practice/9be0172896bd43948f8a32fb954e1be1?tpId=13&tqId=11216&tPage=4&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

### 题目描述

如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。

### 解题思路

```java
/* 大顶堆，存储左半边元素 */
private PriorityQueue<Integer> left = new PriorityQueue<>((o1, o2) -> o2 - o1);
/* 小顶堆，存储右半边元素，并且右半边元素都大于左半边 */
private PriorityQueue<Integer> right = new PriorityQueue<>();
/* 当前数据流读入的元素个数 */
private int N = 0;

public void Insert(Integer val) {
    /* 插入要保证两个堆存于平衡状态 */
    if (N % 2 == 0) {
        /* N 为偶数的情况下插入到右半边。
         * 因为右半边元素都要大于左半边，但是新插入的元素不一定比左半边元素来的大，
         * 因此需要先将元素插入左半边，然后利用左半边为大顶堆的特点，取出堆顶元素即为最大元素，此时插入右半边 */
        left.add(val);
        right.add(left.poll());
    } else {
        right.add(val);
        left.add(right.poll());
    }
    N++;
}

public Double GetMedian() {
    if (N % 2 == 0)
        return (left.peek() + right.peek()) / 2.0;
    else
        return (double) right.peek();
}

```



```java
public class T63 {
    LinkedList<Integer> list = new LinkedList<>();
    public void Insert(Integer num) {
        if (list.size() == 0 || num < list.getFirst()) {
            list.addFirst(num);
        } else {
            boolean insertFlag = false;
            for(Integer e : list) {
                if (num < e) {
                    int index = list.indexOf(e);
                    list.add(index, num);
                    insertFlag = true;
                    break;
                }
            }
            if (!insertFlag) {
                list.addLast(num);
            }
        }
    }

    public Double GetMedian() {
        if (list.size() == 0) {
            return null;
        }
        if(list.size() % 2 == 0) {
            int i = list.size() / 2;
            Double a = Double.valueOf(list.get(i - 1) + list.get(i));
            return a / 2;
        }
        list.get(0);
        return Double.valueOf(list.get(list.size() / 2));
    }
}
```
## 41.2、[字符流中第一个不重复的字符](https://www.nowcoder.com/practice/00de97733b8e4f97a3fb5c680ee10720?tpId=13&tqId=11207&tPage=3&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

### 题目描述

请实现一个函数用来找出字符流中第一个只出现一次的字符。例如，当从字符流中只读出前两个字符 "go" 时，第一个只出现一次的字符是 "g"。当从该字符流中读出前六个字符“google" 时，第一个只出现一次的字符是 "l"。

### 解题思路

```java
private int[] cnts = new int[256];
private Queue<Character> queue = new LinkedList<>();

public void Insert(char ch) {
    cnts[ch]++;
    queue.add(ch);
    while (!queue.isEmpty() && cnts[queue.peek()] > 1)
        queue.poll();
}

public char FirstAppearingOnce() {
    return queue.isEmpty() ? '#' : queue.peek();
}

```



```java
public class T54 {

    int count[] = new int[256];
    int index = 1;

    public void Insert(char ch)
    {
        if (count[ch] == 0) {
            count[ch] = index++;
        } else {
            count[ch] = -1;
        }
    }

    public char FirstAppearingOnce()
    {
        int temp = Integer.MAX_VALUE;
        char ch = '#';
        for (int i = 0; i < count.length; i++) {
            if (count[i] != 0 && count[i] != -1 && count[i] < temp) {
                temp = count[i];
                ch = (char)i;
            }
        }
        return ch;
    }
}
```




## 42、[连续子数组的最大和](https://www.nowcoder.com/practice/459bd355da1549fa8a49e350bf3df484?tpId=13&tqId=11183&tPage=2&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

### 题目描述

{6, -3, -2, 7, -15, 1, 2, 2}，连续子数组的最大和为 8（从第 0 个开始，到第 3 个为止）。

### 解题思路

```java
public int FindGreatestSumOfSubArray(int[] nums) {
    if (nums == null || nums.length == 0)
        return 0;
    int greatestSum = Integer.MIN_VALUE;
    int sum = 0;
    for (int val : nums) {
        sum = sum <= 0 ? val : sum + val;
        greatestSum = Math.max(greatestSum, sum);
    }
    return greatestSum;
}

```

```java
public class T30 {
    public int FindGreatestSumOfSubArray(int[] array) {
        // 动态规划完事
        if (array == null || array.length == 0) return 0;
        int res = array[0]; // 记录当前所有子数组的和的最大值
        int max = array[0]; // 记录包含arr[i]的连续子数组的最大值
        for (int i = 1; i < array.length; i++) {
            max = Math.max(max + array[i], array[i]); // 动态规划公式
            res = Math.max(max, res);
        }
        return res;
    }
}
```
## 43、[整数中1出现的次数](https://www.nowcoder.com/practice/bd7f978302044eee894445e244c7eee6?tpId=13&tqId=11184&tPage=2&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

### 解题思路

```java
public class T31 {
    public int NumberOf1Between1AndN_Solution(int n) {
        int count = 0;
        // 1～n中 遍历呗
        for (int i = 1; i <= n; i++) {
            int num = i;
            while(num != 0) {
                // num%10：其实就是个数 是否为1 是的话count++
                if (num % 10 == 1) {
                    count++;
                }
                // num  = num / 10 
                num /= 10;
            }
        }
        return count;
    }
}
```


## 45、[把数组排成最小的数](https://www.nowcoder.com/practice/8fecd3f8ba334add803bf2a06af1b993?tpId=13&tqId=11185&tPage=2&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

### 题目描述

输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。例如输入数组 {3，32，321}，则打印出这三个数字能排成的最小数字为 321323。

### 解题思路

可以看成是一个排序问题，在比较两个字符串 S1 和 S2 的大小时，应该比较的是 S1+S2 和 S2+S1 的大小，如果 S1+S2 < S2+S1，那么应该把 S1 排在前面，否则应该把 S2 排在前面。

```java
public String PrintMinNumber(int[] numbers) {
    if (numbers == null || numbers.length == 0)
        return "";
    int n = numbers.length;
    String[] nums = new String[n];
    for (int i = 0; i < n; i++)
        nums[i] = numbers[i] + "";
    Arrays.sort(nums, (s1, s2) -> (s1 + s2).compareTo(s2 + s1));
    String ret = "";
    for (String str : nums)
        ret += str;
    return ret;
}

```



```java
public class T32 {
    public String PrintMinNumber(int [] numbers) {
        if (numbers == null || numbers.length == 0) return "";
        int len = numbers.length;
        String[] str = new String[len];
        StringBuffer sb = new StringBuffer();
        // 遍历numbers转成字符串数组
        for (int i = 0; i < len; i++) {
            str[i] = String.valueOf(numbers[i]);
        }
        // 然后排序，重写Comparator
        Arrays.sort(str, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String c1 = o1 + o2;
                String c2 = o2 + o1;
                return c1.compareTo(c2);
            }
        });
        for (int i = 0; i < len; i++) {
            sb.append(str[i]);
        }
        return sb.toString();
    }
}
```

## 46、[礼物的最大值](https://www.nowcoder.com/questionTerminal/72a99e28381a407991f2c96d8cb238ab)

### 题目描述

在一个 m*n 的棋盘的每一个格都放有一个礼物，每个礼物都有一定价值（大于 0）。从左上角开始拿礼物，每次向右或向下移动一格，直到右下角结束。给定一个棋盘，求拿到礼物的最大价值。例如，对于如下棋盘

```html
1    10   3    8
12   2    9    6
5    7    4    11
3    7    16   5
```

礼物的最大价值为 1+12+5+7+7+16+5=53。

### 解题思路

应该用动态规划求解，而不是深度优先搜索，深度优先搜索过于复杂，不是最优解。

```java
public int getMost(int[][] values) {
    if (values == null || values.length == 0 || values[0].length == 0)
        return 0;
    int n = values[0].length;
    int[] dp = new int[n];
    for (int[] value : values) {
        dp[0] += value[0];
        for (int i = 1; i < n; i++)
            dp[i] = Math.max(dp[i], dp[i - 1]) + value[i];
    }
    return dp[n - 1];
}
```

## 48、[最长不含重复字符的子字符串](#)

### 题目描述

输入一个字符串（只包含 a~z 的字符），求其最长不含重复字符的子字符串的长度。例如对于 arabcacfr，最长不含重复字符的子字符串为 acfr，长度为 4。

### 解题思路

```java
public class LongestSubstring {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        // abcabc
        for (int i = 0, j = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i); // 求重复的字符串的索引，为了ans
            }
            ans = Math.max(ans, j - i + 1); // 求长度
            map.put(s.charAt(j), j + 1); // 也会同样覆盖重复的索引
        }
        return ans;
    }
}

```


## 49、[丑数](https://www.nowcoder.com/practice/6aa9e04fc3794f68acf8778237ba065b?tpId=13&tqId=11186&tPage=2&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

### 题目描述

把只包含因子 2、3 和 5 的数称作丑数（Ugly Number）。例如 6、8 都是丑数，但 14 不是，因为它包含因子 7。习惯上我们把 1 当做是第一个丑数。求按从小到大的顺序的第 N 个丑数。

### 解题思路



```java
public class T33 {
    public int GetUglyNumber_Solution(int index) {
        if (index <= 0) return 0;
        int[] ans = new int[index];
        int count = 0;
        int i2 = 0, i3 = 0, i5 = 0;
        ans[0] = 1;
        int temp = 0;
        while (count < index - 1) {
            // 先求i3 * 3 和 i5 * 5 的最小值，然后再求i2 * 2的最小值
            temp = min(ans[i2] * 2, min(ans[i3] * 3, ans[i5] * 5));
            if (temp == ans[i2] * 2) i2++;
            if (temp == ans[i3] * 3) i3++;
            if (temp == ans[i5] * 5) i5++;
            ans[++count] = temp;
        }
        return ans[index - 1];
    }
    private int min(int a, int b) {
        return (a > b) ? b : a;
    }
}
```

## 50、[第一个只出现一次的字符](https://www.nowcoder.com/practice/1c82e8cf713b4bbeb2a5b31cf5b0417c?tpId=13&tqId=11187&tPage=2&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

### 题目描述

在一个字符串中找到第一个只出现一次的字符，并返回它的位置。

```html
Input: abacc
Output: b

```

### 解题思路

最直观的解法是使用 HashMap 对出现次数进行统计，但是考虑到要统计的字符范围有限，因此可以使用整型数组代替 HashMap，从而将空间复杂度由 O(N) 降低为 O(1)。

```java
public int FirstNotRepeatingChar(String str) {
    int[] cnts = new int[256];
    for (int i = 0; i < str.length(); i++)
        cnts[str.charAt(i)]++;
    for (int i = 0; i < str.length(); i++)
        if (cnts[str.charAt(i)] == 1)
            return i;
    return -1;
}

```



```java
public class T34 {
	// 哈希方法
    public int FirstNotRepeatingChar(String str) {
        if (str == null || str.length() == 0) return  -1;
        HashMap<Character, Integer> map = new HashMap<>();
        // 遍历计数
        for (int i = 0; i < str.length(); i++) {
            if (map.containsKey(str.charAt(i))) {
                map.put(str.charAt(i), map.get(str.charAt(i) + 1));
            } else {
                map.put(str.charAt(i), 1);
            }
        }
		// 如果等于1则返回
        for (int i = 0; i < str.length(); i++) {
            if (map.get(str.charAt(i)) == 1) {
                return i;
            }
        }
        return -1;
    }
}
```
## 51、[数组中的逆序对](https://www.nowcoder.com/practice/96bd6684e04a44eb80e6a68efc0ec6c5?tpId=13&tqId=11188&tPage=2&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

### 题目描述

在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。

```java
public class T35 {

    private Integer count = 0;

    public int InversePairs(int [] array) {
        if (array == null || array.length == 0) return 0;
        mergeSort(array, 0, array.length - 1);
        return (count % 1000000007);
    }
	// 归并排序
    private void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            int mid = (left + right) >> 1;
            mergeSort(array, left ,mid);
            mergeSort(array, mid + 1, right);
            merge(array, left, mid, right);
        }

    }

    private void merge(int[] array, int left, int mid, int right) {
        int[] help = new int[right - left + 1];
        int i = 0;
        int p1 = left;
        int p2 = mid + 1;
        while(p1 <= mid && p2 <= right) {
            if(array[p1] > array[p2]) {
                help[i++] = array[p2++];
                count += mid - p1 + 1;
            } else {
                help[i++] = array[p1++];
            }
        }

        while(p1 <= mid) {
            help[i++] = array[p1++];
        }

        while(p2 <= right) {
            help[i++] = array[p2++];
        }

        for(int j = 0; j < help.length; j++) {
            array[left + j] = help[j];
        }
    }
}
```

## 52、[两个链表的第一个公共结点](https://www.nowcoder.com/practice/6ab1d9a29e88450685099d45c9e31e46?tpId=13&tqId=11189&tPage=2&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

### 题目描述

![](https://cs-notes-1256109796.cos.ap-guangzhou.myqcloud.com/5f1cb999-cb9a-4f6c-a0af-d90377295ab8.png)

### 解题思路

设 A 的长度为 a + c，B 的长度为 b + c，其中 c 为尾部公共部分长度，可知 a + c + b = b + c + a。

当访问链表 A 的指针访问到链表尾部时，令它从链表 B 的头部重新开始访问链表 B；同样地，当访问链表 B 的指针访问到链表尾部时，令它从链表 A 的头部重新开始访问链表 A。这样就能控制访问 A 和 B 两个链表的指针能同时访问到交点。

```java
public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
    ListNode l1 = pHead1, l2 = pHead2;
    while (l1 != l2) {
        l1 = (l1 == null) ? pHead2 : l1.next;
        l2 = (l2 == null) ? pHead1 : l2.next;
    }
    return l1;
}
```



```java
public class T36 {

    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        // 哈希方法
        // 边界判断
        if (pHead1 == null || pHead2 == null) return null;
        ListNode cur1 = pHead1;
        ListNode cur2 = pHead2;
        HashMap<ListNode, Integer> map = new HashMap<>();
        // 遍历第一个链表
        while (cur1 != null) {
            map.put(cur1, 1);
            cur1 = cur1.next;
        }
        // 遍历判断map查询第二个链表的节点
        while (cur2 != null) {
            if (map.containsKey(cur2)) {
                return cur2;
            }
            cur2 = cur2.next;
        }
        return null;
    }
}
```
## 53、[数字在排序数组中出现的次数](https://www.nowcoder.com/practice/70610bf967994b22bb1c26f9ae901fa2?tpId=13&tqId=11190&tPage=2&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

### 题目描述

```html
Input:
nums = 1, 2, 3, 3, 3, 3, 4, 6
K = 3

Output:
4
```

### 解题思路

```java
public int GetNumberOfK(int[] nums, int K) {
    int first = binarySearch(nums, K);
    int last = binarySearch(nums, K + 1);
    return (first == nums.length || nums[first] != K) ? 0 : last - first;
}

private int binarySearch(int[] nums, int K) {
    int l = 0, h = nums.length;
    while (l < h) {
        int m = l + (h - l) / 2;
        if (nums[m] >= K)
            h = m;
        else
            l = m + 1;
    }
    return l;
}
```



```java
public class T37 {
    public int GetNumberOfK(int [] array , int k) {
        int count = 0;
        // 遍历数组
        for (int i : array) {
            if (i == k) count++;
        }
        return count;
    }
}
```

## 54、[二叉搜索树的第k个结点](https://www.nowcoder.com/practice/ef068f602dde4d28aab2b210e859150a?tpId=13&tqId=11215&tPage=4&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

```java
private TreeNode ret;
private int cnt = 0;

public TreeNode KthNode(TreeNode pRoot, int k) {
    inOrder(pRoot, k);
    return ret;
}

private void inOrder(TreeNode root, int k) {
    if (root == null || cnt >= k)
        return;
    inOrder(root.left, k);
    cnt++;
    if (cnt == k)
        ret = root;
    inOrder(root.right, k);
}

```

```java
public class T62 {
    int index = 0;
    TreeNode KthNode(TreeNode pRoot, int k) {
        if (null != pRoot) {
            TreeNode node = KthNode(pRoot.left, k);
            if (null != node) {
                return node;
            }
            index++;
            if (index == k) {
                return pRoot;
            }
            node = KthNode(pRoot.right, k);
            if (null != node) {
                return node;
            }
        }
        return null;
    }
}
```
## 55.1、[二叉树的深度](https://www.nowcoder.com/practice/435fb86331474282a3499955f0a41e8b?tpId=13&tqId=11191&tPage=2&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

### 解题思路

```java
public class T38 {
    public int TreeDepth(TreeNode root) {
        return root == null ? 0 : 1 + Math.max(TreeDepth(root.left), TreeDepth(root.right));
    }
}
```

## 55.2、[平衡二叉树](https://www.nowcoder.com/practice/8b3b95850edb4115918ecebdf1b4d222?tpId=13&tqId=11192&tPage=2&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

### 题目描述

平衡二叉树左右子树高度差不超过 1。

![](https://cs-notes-1256109796.cos.ap-guangzhou.myqcloud.com/af1d1166-63af-47b6-9aa3-2bf2bd37bd03.jpg)

```java
private boolean isBalanced = true;

public boolean IsBalanced_Solution(TreeNode root) {
    height(root);
    return isBalanced;
}

private int height(TreeNode root) {
    if (root == null || !isBalanced)
        return 0;
    int left = height(root.left);
    int right = height(root.right);
    if (Math.abs(left - right) > 1)
        isBalanced = false;
    return 1 + Math.max(left, right);
}
```

```java
public class T39 {
    public boolean IsBalanced_Solution(TreeNode root) {
        if (root == null) {
            return true;
        }
        // 平衡二叉树的条件
        return Math.abs(maxDepth(root.left) - maxDepth(root.right)) <= 1 && IsBalanced_Solution(root.left) && IsBalanced_Solution(root.right);
    }
	// 最大深度
    private int maxDepth(TreeNode root) {
        if(root == null) return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}
```

## 56、[数组中只出现一次的数字](https://www.nowcoder.com/practice/e02fdb54d7524710a7d664d082bb7811?tpId=13&tqId=11193&tPage=2&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

### 题目描述

一个整型数组里除了两个数字之外，其他的数字都出现了两次，找出这两个数。

### 解题思路

两个不相等的元素在位级表示上必定会有一位存在不同，将数组的所有元素异或得到的结果为不存在重复的两个元素异或的结果。

diff &= -diff 得到出 diff 最右侧不为 0 的位，也就是不存在重复的两个元素在位级表示上最右侧不同的那一位，利用这一位就可以将两个元素区分开来。

```java
public void FindNumsAppearOnce(int[] nums, int num1[], int num2[]) {
    int diff = 0;
    for (int num : nums)
        diff ^= num;
    diff &= -diff;
    for (int num : nums) {
        if ((num & diff) == 0)
            num1[0] ^= num;
        else
            num2[0] ^= num;
    }
}
```



```java
public class T40 {
    public void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
        if (array == null || array.length <= 1) {
            num1[0] = num2[0] = 0;
            return;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        // 哈希计数
        for (int i = 0; i < array.length; i++) {
            if (map.containsKey(array[i])) {
                map.put(array[i], 2);
            } else {
                map.put(array[i], 1);
            }
        }
        StringBuffer sb = new StringBuffer();
        // Sb存只出现一次的数字
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                sb.append(entry.getKey());
                sb.append(",");
            }
        }
        String[] strings = sb.toString().split(",");
        num1[0] = Integer.valueOf(strings[0]);
        num2[0] = Integer.valueOf(strings[1]);
    }
}
```

## 57.1、[和为S的连续正数序列](https://www.nowcoder.com/practice/c451a3fd84b64cb19485dad758a55ebe?tpId=13&tqId=11194&tPage=3&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

### 题目描述

输出所有和为 S 的连续正数序列。

例如和为 100 的连续序列有：

```html
[9, 10, 11, 12, 13, 14, 15, 16]
[18, 19, 20, 21, 22]。
```

### 解题思路

```java
public class T41 {
    public ArrayList<ArrayList<Integer> > FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<>();
        int phigh = 2;
        int plow = 1;
        // 双指针 快慢指针
        while(phigh > plow) {
            // 
            int cur = (phigh + plow) * (phigh - plow + 1) / 2; // 特殊的计算方法
            if (cur < sum) {
                phigh++;
            }
            if (cur > sum) {
                plow++;
            }
            if (cur == sum) {
                ArrayList<Integer> arrayList = new ArrayList<>();
                for (int i = plow; i <= phigh; i++) {
                    arrayList.add(i);
                }
                arrayLists.add(arrayList);
                plow++;
            }
        }
        return arrayLists;
    }
}
```

## 57.2、[和为S的两个数字](https://www.nowcoder.com/practice/390da4f7a00f44bea7c2f3d19491311b?tpId=13&tqId=11195&tPage=3&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

### 题目描述

输入一个递增排序的数组和一个数字 S，在数组中查找两个数，使得他们的和正好是 S。如果有多对数字的和等于 S，输出两个数的乘积最小的。

### 解题思路

使用双指针，一个指针指向元素较小的值，一个指针指向元素较大的值。指向较小元素的指针从头向尾遍历，指向较大元素的指针从尾向头遍历。

- 如果两个指针指向元素的和 sum == target，那么得到要求的结果；
- 如果 sum > target，移动较大的元素，使 sum 变小一些；
- 如果 sum < target，移动较小的元素，使 sum 变大一些。

```java
public ArrayList<Integer> FindNumbersWithSum(int[] array, int sum) {
    int i = 0, j = array.length - 1;
    while (i < j) {
        int cur = array[i] + array[j];
        if (cur == sum)
            return new ArrayList<>(Arrays.asList(array[i], array[j]));
        if (cur < sum)
            i++;
        else
            j--;
    }
    return new ArrayList<>();
}
```



```java
public class T42 {

    public ArrayList<Integer> FindNumbersWithSum(int [] array, int sum) {
        int start = 0, end = array.length - 1;
        // list存两数字
        ArrayList<Integer> list = new ArrayList<>();
        // 类似于二分
        while (start < end) {
            int count = array[start] + array[end];
            if (count < sum) {
                start++;
            }
            if (count == sum) {
                list.add(array[start]);
                list.add(array[end]);
                return list;
            }
            if (count > sum) {
                end--;
            }
        }
        return list;
    }
}
```

## 58.1、[左旋转字符串](https://www.nowcoder.com/practice/12d959b108cb42b1ab72cef4d36af5ec?tpId=13&tqId=11196&tPage=3&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

### 题目描述

```html
Input:
S="abcXYZdef"
K=3

Output:
"XYZdefabc"
```

### 解题思路

```java
public String LeftRotateString(String str, int n) {
    if (n >= str.length())
        return str;
    char[] chars = str.toCharArray();
    reverse(chars, 0, n - 1);
    reverse(chars, n, chars.length - 1);
    reverse(chars, 0, chars.length - 1);
    return new String(chars);
}

private void reverse(char[] chars, int i, int j) {
    while (i < j)
        swap(chars, i++, j--);
}

private void swap(char[] chars, int i, int j) {
    char t = chars[i];
    chars[i] = chars[j];
    chars[j] = t;
}

```

```java
public class T43 {
    public String LeftRotateString(String str,int n) {
        if (str.length() == 0) return str;
        // 用str的substring的api
        for (int i = 0; i < n; i++) {
            char c = str.charAt(0);
            str = str.substring(1).concat(String.valueOf(c));
        }
        return str;
    }
}
```

## 58.2、[翻转单词顺序列](https://www.nowcoder.com/practice/3194a4f4cf814f63919d0790578d51f3?tpId=13&tqId=11197&tPage=3&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

### 题目描述

```html
Input:
"I am a student."

Output:
"student. a am I"
```

### 解题思路

```java
public class T44 {

    public String ReverseSentence(String str) {
        if (str == null) return null;
        // 边界判断
        if (str.trim().equals("")) return str;
        // 切割
        String[] strings = str.split(" ");
        StringBuffer sb = new StringBuffer();
        // 遍历
        for (int i = strings.length - 1; i >= 0; i--) {
            sb.append(strings[i]).append(" ");
        }
        return sb.substring(0, sb.length() - 1);
    }
}
```

## 59、[滑动窗口的最大值](https://www.nowcoder.com/practice/1624bc35a45c42c0bc17d17fa0cba788?tpId=13&&tqId=11217&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)

### 题目描述

给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值。

例如，如果输入数组 {2, 3, 4, 2, 6, 2, 5, 1} 及滑动窗口的大小 3，那么一共存在 6 个滑动窗口，他们的最大值分别为 {4, 4, 6, 6, 6, 5}。

### 解题思路

可用大顶堆

```java
public ArrayList<Integer> maxInWindows(int[] num, int size) {
    ArrayList<Integer> ret = new ArrayList<>();
    if (size > num.length || size < 1)
        return ret;
    PriorityQueue<Integer> heap = new PriorityQueue<>((o1, o2) -> o2 - o1);  /* 大顶堆 */
    for (int i = 0; i < size; i++)
        heap.add(num[i]);
    ret.add(heap.peek());
    for (int i = 0, j = i + size; j < num.length; i++, j++) {            /* 维护一个大小为 size 的大顶堆 */
        heap.remove(num[i]);
        heap.add(num[j]);
        ret.add(heap.peek());
    }
    return ret;
}
```



```java
public class T64 {
    public ArrayList<Integer> maxInWindows(int [] num, int size) {
        if (null == num || size < 0) {
            return null;
        }
        ArrayList<Integer> list = new ArrayList<>();
        if(size == 0) {
            return list;
        }
        int length = num.length;
        ArrayList<Integer> temp = null;
        if (length < size) {
            return list;
        } else {
            // 滑length-size+1次
            for (int i = 0; i < length - size + 1; i++) {
                temp = new ArrayList<>();
                // 滑动窗口
                for (int j = i; j < size + i; j++) {
                    temp.add(num[j]);
                }
                // 排序
                Collections.sort(temp);
                // 排序过后取最大值 并添加
                list.add(temp.get(temp.size() - 1));
            }
        }
        return list;
    }
}
```


## 61、[扑克牌顺序](https://www.nowcoder.com/practice/762836f4d43d43ca9deb273b3de8e1f4?tpId=13&tqId=11198&tPage=3&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

### 题目描述

五张牌，其中大小鬼为癞子，牌面为 0。判断这五张牌是否能组成顺子。

![](https://cs-notes-1256109796.cos.ap-guangzhou.myqcloud.com/eaa506b6-0747-4bee-81f8-3cda795d8154.png)

### 解题思路

```java
public class T45 {
    public boolean isContinuous(int [] numbers) {
        int numOfZero = 0;
        int numOfInterval = 0;
        int length = numbers.length;
        if (length == 0) return false;
        // 排序
        Arrays.sort(numbers);
        for (int i = 0; i < length - 1; i++) {
            // 计算癞子数量 也就是计算0的数量
            if (numbers[i] == 0) {
                numOfZero++;
                continue;
            }
            // 对子直接返回（特殊情况）
            if (numbers[i] == numbers[i + 1]) return false;
            
            numOfInterval += numbers[i + 1] - numbers[i] - 1;
        }
        if (numOfZero >= numOfInterval) return true;
        return false;
    }
}
```

## 62、[孩子们的游戏](https://www.nowcoder.com/practice/f78a359491e64a50bce2d89cff857eb6?tpId=13&tqId=11199&tPage=3&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

### 题目描述

让小朋友们围成一个大圈。然后，随机指定一个数 m，让编号为 0 的小朋友开始报数。每次喊到 m-1 的那个小朋友要出列唱首歌，然后可以在礼品箱中任意的挑选礼物，并且不再回到圈中，从他的下一个小朋友开始，继续 0...m-1 报数 .... 这样下去 .... 直到剩下最后一个小朋友，可以不用表演。

### 解题思路

约瑟夫环，圆圈长度为 n 的解可以看成长度为 n-1 的解再加上报数的长度 m。因为是圆圈，所以最后需要对 n 取余。

```java
public int LastRemaining_Solution(int n, int m) {
    if (n == 0)     /* 特殊输入的处理 */
        return -1;
    if (n == 1)     /* 递归返回条件 */
        return 0;
    return (LastRemaining_Solution(n - 1, m) + m) % n;
}
```



```java
public class T46 {
    public int LastRemaining_Solution(int n, int m) {
        // 边界判断
        if (n == 0 || m == 0) {
            return -1;
        }
        ArrayList<Integer> data = new ArrayList<>();
        // 遍历一次0～n
        for (int i = 0; i < n; i++) {
            data.add(i);
        }
        int index = -1;
        // 循环 index + m 和 余 data的数量
        while (data.size() > 1) {
            index = (index + m) % data.size();
            // 移除index
            data.remove(index);
            // index - 1
            index--;
        }
        return data.get(0);
    }
}
```

## 63、[股票的最大利润](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/description/?utm_source=LCUS&utm_medium=ip_redirect_q_uns&utm_campaign=transfer2china)

### 题目描述

可以有一次买入和一次卖出，买入必须在前。求最大收益。

![](https://cs-notes-1256109796.cos.ap-guangzhou.myqcloud.com/42661013-750f-420b-b3c1-437e9a11fb65.png)

### 解题思路

```java
class Solution {
    public int maxProfit(int[] prices) {
        if(prices.length <= 1) return 0;
        // int min = prices[0], max = 0;
        // for(int i = 1; i < prices.length; i++){
        //     max = Math.max(max, prices[i] - min);
        //     min = Math.min(min, prices[i]);
        // }
        // return max;
        int dp[] = new int [prices.length];
        dp[0] = prices[0];
        int max = 0;
        for (int i = 1; i < prices.length; i++) {
            max = Math.max(max, prices[i] - dp[i - 1]);
            dp[i] = Math.min(dp[i - 1], prices[i]);
        }
        return max;
    }
}
```

## 64、[求1+2+3+...+n](https://www.nowcoder.com/practice/7a0da8fc483247ff8800059e12d7caf1?tpId=13&tqId=11200&tPage=3&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

### 题目描述

要求不能使用乘除法、for、while、if、else、switch、case 等关键字及条件判断语句 A ? B : C。

### 解题思路

使用递归解法最重要的是指定返回条件，但是本题无法直接使用 if 语句来指定返回条件。

条件与 && 具有短路原则，即在第一个条件语句为 false 的情况下不会去执行第二个条件语句。利用这一特性，将递归的返回条件取非然后作为 && 的第一个条件语句，递归的主体转换为第二个条件语句，那么当递归的返回条件为 true 的情况下就不会执行递归的主体部分，递归返回。

本题的递归返回条件为 n <= 0，取非后就是 n > 0；递归的主体部分为 sum += Sum_Solution(n - 1)，转换为条件语句后就是 (sum += Sum_Solution(n - 1)) > 0。

```java
public class T47 {
    public int Sum_Solution(int n) {
        int res = n;
        boolean t = ((res != 0) && ((res += Sum_Solution(n - 1)) != 0));
        return res;
    }
}
```

## 65、[不用加减乘除做加法](https://www.nowcoder.com/practice/59ac416b4b944300b617d4f7f111b215?tpId=13&tqId=11201&tPage=3&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

### 题目描述

写一个函数，求两个整数之和，要求不得使用 +、-、*、/ 四则运算符号。

### 解题思路

a ^ b 表示没有考虑进位的情况下两数的和，(a & b) << 1 就是进位。

递归会终止的原因是 (a & b) << 1 最右边会多一个 0，那么继续递归，进位最右边的 0 会慢慢增多，最后进位会变为 0，递归终止。

```java
public int Add(int a, int b) {
    return b == 0 ? a : Add(a ^ b, (a & b) << 1);
}
```



```java
public class T48 {
    public int Add(int num1,int num2) {
        while (num2 != 0) {
            int temp = num1 ^ num2; // 没有进位的相加
            num2 = (num1 & num2) << 1; // 进位
            num1 = temp; // 
        }
        return num1;
    }
}
```

## 66、[构建乘积数组](https://www.nowcoder.com/practice/94a4d381a68b47b7a8bed86f2975db46?tpId=13&tqId=11204&tPage=3&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

### 题目描述

给定一个数组 A[0, 1,..., n-1]，请构建一个数组 B[0, 1,..., n-1]，其中 B 中的元素 B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。要求不能使用除法。

![](https://cs-notes-1256109796.cos.ap-guangzhou.myqcloud.com/4240a69f-4d51-4d16-b797-2dfe110f30bd.png)

### 解题思路

```java
public class T51 {
    public int[] multiply(int[] A) {
        int length = A.length;
        int[] B = new int[length];
        if (length != 0) {
            B[0] = 1;
            // 计算下三角连乘
            for (int i = 1; i < length; i++) {
                B[i] = B[i - 1] * A[i - 1];
            }
            int temp = 1;
            // 计算上三角
            for (int j = length - 2; j >= 0; j--) {
                temp *= A[j + 1];
                B[j] *= temp;
            }
        }
        return B;
    }
}
```


## 67、[把字符串转成整数](https://www.nowcoder.com/practice/1277c681251b4372bdef344468e4f26e?tpId=13&tqId=11202&tPage=3&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

### 题目描述

将一个字符串转换成一个整数，要求不能使用字符串转换整数的库函数。 数值为0或者字符串不是一个合法的数值则返回0

### 解题思路

```java
public class T49 {
    public int StrToInt(String str) {
        if (str == null || str.length() == 0)
            return 0;
        boolean isNegative = str.charAt(0) == '-';
        int ret = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (i == 0 && (c == '+' || c == '-'))  /* 符号判定 */
                continue;
            if (c < '0' || c > '9')                /* 非法输入 */
                return 0;
            ret = ret * 10 + (c - '0');
        }
        return isNegative ? -ret : ret;
    }
}
```




