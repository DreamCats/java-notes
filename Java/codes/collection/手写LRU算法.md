# LRU算法
> LRU（Least recently used，最近最少使用）算法根据数据的历史访问记录来进行淘汰数据，其核心思想是“如果数据最近被访问过，那么将来被访问的几率也更高”。

## 一般过程
![过程图](https://pic4.zhimg.com/80/v2-71b21233c615b1ce899cd4bd3122cbab_1440w.jpg)

- 新数据插入到链表头部；
- 每当缓存命中（即缓存数据被访问），则将数据移到链表头部；
- 当链表满的时候，将链表尾部的数据丢弃。

再来来个具体的过程
![具体过程](https://pic3.zhimg.com/80/v2-998b52e7534278b364e439bbeaf61d5e_1440w.jpg)

1. 最开始时，内存空间是空的，因此依次进入A、B、C是没有问题的
2. 当加入D时，就出现了问题，内存空间不够了，因此根据LRU算法，内存空间中A待的时间最为久远，选择A,将其淘汰
3. 当再次引用B时，内存空间中的B又处于活跃状态，而C则变成了内存空间中，近段时间最久未使用的
4. 当再次向内存空间加入E时，这时内存空间又不足了，选择在内存空间中待的最久的C将其淘汰出内存，这时的内存空间存放的对象就是E->B->D

## 伪代码实现

### 思路
> 双向链表+HashMap，Java中的LinkedHashMap就实现了该算法。

### 定义节点
```java
class Node {
    int key;
    int value;
    Node pre;
    Node next;
}
```
### 定义HashMap
`HashMap<Integer, Node> map = new HashMap<>();`

### get
> 其实挺简单的。
```java
public int get(int key) {
    if (map.containsKey(key)) {
        Node n = map.get(key); // 获取内存中存在的值，比如A
        remove(n); //使用链表的方法，移除该节点
        setHead(n); //依然使用链表的方法，将该节点放入头部
        return n.value;
    } 
    return -1;
}
```
- 由于当一个节点通过key来访问到这个节点，那么这个节点就是刚被访问了，
- 就把这个节点删除掉，然后放到队列头，这样队列的头部都是最近访问的，
- 队列尾部是最近没有被访问的。

### set
```java
public void set(int key, int value) {
    if (map.containsKey(key)) {
        Node old = map.get(key);
        old.value = value;
        remove(old); // 移除旧节点
        setHead(old); // 放到队头
    } else {
        Node created = new Node(key, value);
        if (map.size() >= capacity) {
            map.remove(end.key); // clear该key
            remove(end); //链表也是依次
            setHead(created); // 将created放入队头
        } else {
            setHead(created); // 如果没满，直接放入队头
        }
        map.put(key,created);
    }
}
```