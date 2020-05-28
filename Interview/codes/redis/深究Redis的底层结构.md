# 深究Redis的底层结构

## 简单动态字符串(SDS)
> Redis没有直接使用C语言传统的字符串（以空字符结尾的字符数组，以下简称C字符串），而是自己构建了一种名为简单动态字符串（Simple dynamic string，SDS）的抽象类型，并将SDS用作Redis的默认字符串表示。

### 看一下SDS的定义
```c
struct sdshdr {
    // 记录buf数组中已使用字节的数量
    // 等于sds所保存字符串的长度
    int len;
    
    // 记录buf数组中未使用字节的数量
    int free;
    
    // 字节数组，用于保存字符串
    char buf[];
}
```

### 有何优点
1. 获取字符串长度的复杂度为O(1)。
2. 杜绝缓冲区溢出。
3. 减少修改字符串长度时所需要的内存重分配次数。
4. 二进制安全。
5. 兼容部分C字符串函数。

## 链表
> 毫无疑问，Redis也不会不适用链表的，毕竟链表这么好的结构，用起来很香的。当有一个列表键包含了数量比较多的元素，又或者列表中包含的元素都是比较长的额字符串时，Redis就会使用链表作为列表建的底层实现。

### 节点底层结构
```c
typedef struct listNode {
    // 前置节点
    struct listNode *prev;
    // 后置节点
    struct listNode *next;
    // 节点的值
    void *value;
} listNode;
```
和其他的节点结构没啥区别...

### list底层结构
```c
typedef struct list {
    // 表头节点
    listNode *head;
    // 表尾节点
    listNode *tail;
    // 链表所包含的节点数量
    unsigned long len;
    // 节点值复制函数
    void *(*dup)(void *ptr);
    // 节点值是放过函数
    void (*free)(void *ptr);
    // 节点值对比函数
    int（*match）(void *ptr, void *key);
} list;
```

### 特性
1. 链表被广泛用于实现Redis的各种功能，比如列表建、发布与订阅、慢查询、监视器等。
2. 每个链表节点由一个listNode结构来表示，每个节点都有一个指向前置节点和后置节点的指针，所以Redis的链表实现是双端链表。
3. 每个链表使用一个list结构表示，这个结构带有表头节点指针、表尾节点指针，以及链表长度等信息。
4. 因为链表表头的前置节点和表尾节点的后置节点都指向NULL，所以Redis的链表实现是无环链表。
5. 通过为链表设置不同的类型特定函数，Redis的链表可以用于保存各种不同类型的值。

## 字典
> 说白了， 字典就是键值对。Redis的字典的底层就是哈希表而已。

### 哈希表
```c
typedef struct dictht {
    // 哈希表数组
    dictEntry **table;
    // 哈希表大小
    unsigned long size;
    // 哈希表大小掩码，用于计算索引值
    // 总是等于size-1
    unsigned long sizemark;
    // 该哈希表已有节点的数量
    unsigned long used;
} dichht;
```

那么，哈希表数组的结构table呢？

### dictEntry的结构
```c
typedef struct dictEntry {
    // 键
    void *key;
    // 值
    union {
        void *val;
        uint64_t u64;
        int64_t s64;
    } v;
    // 指向下个哈希表节点，形成链表
    struct dictEntry *nect;
} dictEntry;
```
dict的底层结构省略。

### 哈希算法
当字典被用作数据库的底层实现，或者哈希键的底层实现时，Redis使用MurmurHash算法。这种算法的优点在于即使输入的键是规律的，算法仍能给出一个个很好的随机分布性，并且算法的计算速度非常快。

### 哈希冲突
Redis的哈希表使用链地址法来解决键冲突，每个哈希表节点都有一个next指针，多个哈希表节点可以用这个单向链表连接起来，这就解决了键冲突的问题。

### 特性
1. 字典被广泛用于实现Redis的各种功能，其中包括数据库和哈希键。
2. Redis中的字典使用哈希表作为底层结构实现，每个字典带有两个哈希表，一个平时使用，另一个仅在进行rehash时使用。
3. Redis使用MurmurHash2算法来计算键的哈希值。
4. 哈希表使用链地址法来解决键冲突。

## 跳跃表

先看这样一张图：
![](https://user-gold-cdn.xitu.io/2019/4/18/16a30ca4a17c6280?imageView2/0/w/1280/h/960/format/webp/ignore-error/1)

如上图，我们要查找一个元素，就需要从头节点开始遍历，直到找到对应的节点或者是第一个大于要查找的元素的节点（没找到）。时间复杂度为O(N)。

这个查找效率是比较低的，但如果我们把列表的某些节点拔高一层，如下图，例如把每两个节点中有一个节点变成两层。那么第二层的节点只有第一层的一半，查找效率也就会提高。

![](https://user-gold-cdn.xitu.io/2019/4/18/16a30ca4aa58e4bb?imageView2/0/w/1280/h/960/format/webp/ignore-error/1)

查找的步骤是从头节点的顶层开始，查到第一个大于指定元素的节点时，退回上一节点，在下一层继续查找。

比如我们要查找16：
1. 从头节点的最顶层开始，先到节点7。
2. 7的下一个节点是39，大于16，因此我们退回到7
3. 从7开始，在下一层继续查找，就可以找到16。

这个例子中遍历的节点不比一维列表少，但是当节点更多，查找的数字更大时，这种做法的优势就体现出来了。还是上面的例子，如果我们要**查找的是39**，那么只需要访问两个节点（7、39）就可以找到了。这比一维列表要减少一半的数量。

为了避免插入操作的时间复杂度是O(N)，skiplist每层的数量不会严格按照2:1的比例，而是对每个要插入的元素随机一个层数。

随机层数的计算过程如下：
1. 每个节点都有第一层
2. 那么它有第二层的概率是p，有第三层的概率是p*p
3. 不能超过最大层数

### zskiplistNode
```c
typedef struct zskiplistNode {
    // 后退指针
    struct zskiplistNode *backward;
    // 分值 权重
    double score;
    // 成员对象
    robj *obj;
    // 层
    struct zskiplistLevel {
        // 前进指针
        struct zskiplistNode *forward;
        // 跨度
        unsigned int span;
    } leval[];
} zskiplistNode;
```
一般来说，层的数量越多，访问其他节点的速度越快。

### zskipList
```c
typedef struct zskiplist {
    // 表头节点和表尾节点
    struct zskiplistNode *header, *tail;
    // 表中节点的数量
    unsigned long length;
    // 表中层数最大的节点的层数
    int leval;
} zskiplist;
```

### 特性
1. 跳跃表是有序集合的底层实现之一
2. Redis的跳跃表实现由zskiplist和zskiplistNode两个结构组成，其中zskiplist用于保存跳跃表信息（比如表头节点、表尾节点、长度），而zskiplistNode则用于表示跳跃表节点
3. 每个跳跃表节点的层高都是1至32之间的随机数
4. 在同一个跳跃表中，多个节点可以包含相同的分值，但每个节点的成员对象必须是唯一的。
5. 跳跃表中的节点按照分值大小进行排序，当分值相同时，节点按照成员对象的大小进行排序。

## 压缩列表
> 一看名字，就是为了节省内存造的列表结构。压缩列表(ziplist)是列表键和哈希键的底层实现之一。当一个列表键只包含少量列表项，并且每个列表项要么就是小整数值，要么就是长度比较短的字符串，那么Redis就会使用压缩列表来做列表键的底层实现。