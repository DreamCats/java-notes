
# ==、hashcode和equals
> 我感觉这问题被问的频率有点高， 那是因为考的知识点比较多

## ==

面试官：出个题:

```java
Integer a = new Integer(5);
Integer b = new Integer(5);
System.out.println(a == b); // false
```

我：false，太简单了嘛。 == 比较的是两个对象的地址哦，你看：

### 概念及作用

它的作用是**判断两个对象的地址是不是相等**。即，判断两个对象是不是同一个对象：

- 基本数据类型==比较的是**值** 

- 引用数据类型==比较的是**内存地址**

你要是：`System.out.println(a.equals(b)); \\ false`

面试官：那这个呢：

```java
int a = 5;
Integer b = new Integer(5);
System.out.println(a == b); // true
```
### 装箱与拆箱

我：你想考我装箱拆箱嘛？其实，b看到了对面那个哥们是**基本类型**，我也是基本类型包装的呀，我把衣服一脱，那么就是**基本类型**了，那就是上面的结论了。b拆箱即可。

装箱：**自动将基本数据类型转换为包装器类型**

拆箱：**自动将包装器类型转换为基本数据类型**

基本类型有：

- byte（1字节）：Byte
- short（2字节）：Short
- int（4字节）：Integer
- long（8字节）：Long
- float（4字节）：Float
- double（8字节）：Double
- char（2字节）：Character
- boolean（未知）：Boolean

**在装箱的时候自动调用的是Integer的valueOf(int)方法。而在拆箱的时候自动调用的是Integer的intValue方法**

valueOf：

```java
public static Integer valueOf(int i) {
    if (i >= IntegerCache.low && i <= IntegerCache.high)
        return IntegerCache.cache[i + (-IntegerCache.low)];
    return new Integer(i);
}
```

intValue:

```java
public int intValue() {
    return value;
}
```

## equals

面试官：聊一下equals

### 概念及原理
我：equals是顶层父类Object的方法之一

```java
// 你看，object默认调用的== ， 你对象如果不重写，可能会发上重大事故
public boolean equals(Object obj) {
    return (this == obj); // 比较对象的地址值
}
```

顺带说一下Object的hashcode方法

```java
// Returns a hash code value for the object.
// 说白了，调用本地方法返回的就是该对象的地址值
public native int hashCode();
```
### 作用
Equals的作用也是判断两个对象是否相等。但它一般有两种使用情况：

- 情况1：类没有覆盖 equals() 方法。则通过 equals() 比较该类的两个对象时，等价于通过`==`比较这两个对象。

- 情况2：类覆盖了 equals() 方法。一般，我们都覆盖 equals() 方法来比较两个对象的内容是否相等；若它们的内容相等，则返回 true (即，认为这两个对象相等)。

```java
Integer a = new Integer(5);
Integer b = new Integer(5);
System.out.println(a.equals(b));
```

可以看一下Integer的equals方法：

```java
// 重写了Object的equals的方法
public boolean equals(Object obj) {
    if (obj instanceof Integer) {
        // 比较value
        return value == ((Integer)obj).intValue();
    }
    return false;
}
```

## hashcode

hashcode的例子：

```java
public static void main(String[] args) {
    Set<Integer> set = new HashSet<>();
    set.add(1);
    set.add(1);
    System.out.println(set.toString());
}
```
**解释：**
在添加1的时候，先判断**hashcode是否相同**，如果相同，**继续判断equals比较值**，这样的好处是，**如果hashcode相同就直接返回false了，减少了一次equals的判断，因为通常hashcode的值判断是否相等比较快，而equals相对于hashcode来讲慢一些**。所以，如果不重写hashcode，我们看到object的hashcode是对象的内存值，那么set添加1判断的时候，hashcode永远不相等，那么就永远返回false，不管添加1，还是2，都是false。