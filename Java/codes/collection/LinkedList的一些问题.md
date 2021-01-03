# LinkedList
> LinkedList同时实现了List接口和Deque接口，也就是说它既可以看作一个顺序容器，又可以看作一个队列（Queue），同时又可以看作一个栈（Stack）。 关于栈或队列，现在的首选是ArrayDeque，它有着比LinkedList（当作栈或队列使用时）有着更好的性能。 关于特点，想必大家都知道了。

## 底层数据
> LinkedList底层通过双向链表实现。双向链表的每个节点用内部类Node表示。LinkedList通过**first和last引用分别指向链表的第一个和最后一个元素**。注意这里没有所谓的哑元，当链表为空的时候**first和last都指向null**。
```java
transient int size = 0;
transient Node<E> first; // 经常用
transient Node<E> last; // 经常用
// Node是私有的内部类
private static class Node<E> {
    E item;
    Node<E> next; // 这里就不多说了
    Node<E> prev; // 

    Node(Node<E> prev, E element, Node<E> next) {
        this.item = element;
        this.next = next;
        this.prev = prev;
    }
}
```
构造函数就不用看了...

## getFirst和getLast
> 这个挺简单的
```java
public E getFirst() {
    final Node<E> f = first; // 第一个节点
    if (f == null)
        throw new NoSuchElementException();
    return f.item;
}
public E getLast() {
    final Node<E> l = last; // 最后一个节点
    if (l == null)
        throw new NoSuchElementException();
    return l.item;
}
```

**removeFirst和removeLast**和上面的源码差不多，挺简单。链表的删除，脑子里画图思考思考就明白了，添加也是一样。

## add调用的是`linkLast`
```java
void linkLast(E e) {
    final Node<E> l = last; // 要在尾部添加，所以要找last
    final Node<E> newNode = new Node<>(l, e, null); // last->e->null
    last = newNode;
    if (l == null)
        first = newNode; // 如果l为空，说明没有链表没有元素，那么first和last都指向e，
    else
        l.next = newNode; // 否则就将l.next指向新节点
    size++;
    modCount++; // 依然存在并发危险哦
}
```

## 看一下remove
```java
public boolean remove(Object o) {
    if (o == null) { // 将链表存在null的给移除掉
        for (Node<E> x = first; x != null; x = x.next) {
            if (x.item == null) {
                unlink(x);
                return true;
            }
        }
    } else {
        for (Node<E> x = first; x != null; x = x.next) {
            if (o.equals(x.item)) { // 否则直接遍历与符合当前对象，unlink即可。
                unlink(x);
                return true;
            }
        }
    }
    return false;
}
```
## unlink得看
> 这个脑子思考一下那个图，其实就明白，很源码的话，也没问题的。
```java
E unlink(Node<E> x) {
    // assert x != null;
    final E element = x.item; // 当前值
    final Node<E> next = x.next; // 后
    final Node<E> prev = x.prev; // 前

    if (prev == null) { // 如果x的前是null，说明，x是首节点，直接让first指向next
        first = next;
    } else { // x不是首节点，意味着pre是有值哦。
        prev.next = next; // prev.next的箭头指向x的next
        x.prev = null; // 将x.prev指向null 双向链表？
    }

    if (next == null) { // 同上，说明x是最后节点了
        last = prev; // 让last指向x的prev
    } else {
        next.prev = prev; // 否则x不是最后节点， 让next.prev 指向prev
        x.next = null; // x.next 指向null ， 双向链表
    }

    x.item = null; // GC回收
    size--;
    modCount++;
    return element;
}
```
**其他几个方法的源码中核心都有这些...**