## 问题描述
- 请设计一个LRU，伪代码...
## 思路
- 双向链表+Hashmap
## 伪代码
- 简单定义节点Node
```java
class Node {
    int key;
    int value;
    Node pre;
    Node next;
}
```
- 定义Hashmap
```java
HashMap<Integer, Node> map = new HashMap<>();
```
- get操作
```java
public int get(int key) {
        if (map.containsKey(key)) {
            Node n = map.get(key);
            remove(n); // 链表
            setHead(n); // 链表
            return n.value;
        }
        return -1;
    }
```
- 由于当一个节点通过key来访问到这个节点，那么这个节点就是刚被访问了，
- 就把这个节点删除掉，然后放到队列头，这样队列的头部都是最近访问的，
- 队列尾部是最近没有被访问的。

- set操作
```java
public void set(int key, int value) {
        if (map.containsKey(key)) {
            Node old = map.get(key);
            old.value = value;
            remove(old);
            setHead(old);
        } else {
            Node created = new Node(key, value);
            if (map.size() >= capacity) {
                map.remove(end.key);
                remove(end);
                setHead(created);
            } else {
                setHead(created);
            }
            map.put(key, created);
        }
    }
```
- 由于set操作是找到这个键值对，然后修改value的值，由于这个节点被访问了，所以删除他，把这个节点放到头部，
- 如果这个节点不存在，那么就新建一个节点，把这个节点放到头部，同时map增加这个键值对。
- 上述中所有过程都是先访问map，由于hashmap是O(1)的时间复杂度，而且双向链表的所有操作的时间复杂度在本例中都是O(1)，
- 删除啦，插入头部都是O(1)时间复杂度，所以整个get和set的时间复杂度都是O(1)。