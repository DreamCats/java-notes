
# String
> 难道面试官想考String的什么？

## String、StringBuffer和StringBuilder的区别

面试官：说一下你知道的String、StringBuffer和StringBuilder的区别

我：其实总结说一下就行，三点不同

### 可变性

- 简单的来说：`String` 类中使用 `final` 关键字修饰字符数组来保存字符串，`private　final　char　value[]`，所以 `String` 对象是不可变的。（还不是为了线程安全和JVM缓存速度问题）

- `StringBuilder` 与 `StringBuffer` 都继承自 `AbstractStringBuilder` 类，在 `AbstractStringBuilder` 中也是使用字符数组保存字符串char[]value 但是没有用 `final` 关键字修饰，所以这两种对象都是可变的。

### 线程安全

- `String` 中的对象是不可变的，也就可以理解为常量，线程安全。

- `AbstractStringBuilder` 是 `StringBuilder` 与 `StringBuffer` 的公共父类，定义了一些字符串的基本操作，如 `expandCapacity`、`append`、`insert`、`indexOf` 等公共方法。`StringBuffer` 对方法加了同步锁或者对调用的方法加了同步锁，所以是线程安全的。`StringBuilder` 并没有对方法进行加同步锁，所以是非线程安全的。

### 性能

- 每次对 `String` 类型进行改变的时候，都会生成一个新的 `String` 对象，然后将指针指向新的 `String` 对象。（人家目的是不可变，你一直让人家干可变的事情，那岂不是很难受？）

- `StringBuffer` 每次都会对 `StringBuffer` 对象本身进行操作，而不是生成新的对象并改变对象引用。相同情况下使用 `StringBuilder` 相比使用 `StringBuffer` 仅能获得 10%~15% 左右的性能提升，但却要冒多线程不安全的风险。

### 总结

- 操作少量的数据: 适用String

- 单线程操作字符串缓冲区下操作大量数据: 适用StringBuilder

- 多线程操作字符串缓冲区下操作大量数据: 适用StringBuffer

> 注意：这里可能要问final关键字来解释一波String的char前面所加的final的好处

## final关键字

面试官：有木有想过为什么String的char前面加了final，有什么好处？

我：答这个问题，你要先说final是干啥的

final关键字主要用在三个地方：变量、方法、类。


- 对于一个final变量，如果是**基本数据类型的变量，则其数值一旦在初始化之后便不能更改**；如果是引用类型的变量，则在对其初始化之后便**不能再让其指向另一个对象**。

- 当用final修饰一个类时，表明**这个类不能被继承**。final类中的所有成员方法都会被隐式地指定为final方法。

- 使用final方法的原因有两个。第一个原因是把方法锁定，以防任何继承类修改它的含义；第二个原因是效率。在早期的Java实现版本中，会将final方法转为内嵌调用。但是如果方法过于庞大，可能看不到内嵌调用带来的任何性能提升（现在的Java版本已经不需要使用final方法进行这些优化了）。类中所有的private方法都隐式地指定为final。

**final修饰有啥好处**：（面试官想听这三点）

- final的关键字**提高了性能**，JVM和java应用会**缓存final变量**；

- final变量可以在多线程环境下保持**线程安全**；

- 使用final的关键字提高了性能，JVM会对方法变量类进行优化；

> 这里可能让解释String对象和JVM的常量池

## String对象和常量池

```java
public class StringTest {
    public static void main(String[] args) {
        String str1 = "todo"; // 常量池
        String str2 = "todo"; // 从常量池找了str1
        String str3 = "to"; // 常量池
        String str4 = "do"; // 常量池
        String str5 = str3 + str4; // 内部用StringBuilder拼接了一波。 因此， 并非常量池
        String str6 = new String(str1); //  创建对象了， 那还能是常量池的引用？
    }
}
```

分析一波：
- 成的class文件中会在常量池中**保存“todo”、“to”和“do”三个String常量**。
- 变量str1和str2均保存的是常量池中“todo”的引用，所以str1==str2成立；
- 在执行 str5 = str3 + str4这句时，**JVM会先创建一个StringBuilder对象，通过StringBuilder.append()方法将str3与str4的值拼接**，然后通过StringBuilder.toString()返回一个堆中的String对象的引用，赋值给str5，因此str1和str5指向的不是同一个String对象，str1 == str5不成立；
- String str6 = new String(str1)一句显式创建了一个新的String对象，因此str1 == str6不成立便是显而易见的事了。

## 了解String.intern()方法吗？

面试官：说一下String.intern()干嘛用的？

我：去字符串常量池拿吃的

- jdk6：

执行intern()方法时，**若常量池中不存在等值的字符串，JVM就会在常量池中创建一个等值的字符串(这句话很重要)，然后返回该字符串的引用**。

- jdk7：

执行intern操作时，如果常量池已经存在该字符串，则直接返回字符串引用，否则**复制该堆中字符串对象的引用到常量池(堆对象引用复制到常量池)中并返回**。

简单说一下常量池版本的变化

### 1.6

- 静态常量池在Class文件中。
- **运行时常量池(常量池：字面量和符号引用)在Perm Gen区(也就是方法区)中**。
- **字符串常量池在运行时常量池**中。

### 1.7

- 静态常量池在Class文件中。
- **运行时常量池依然在Perm Gen区(也就是方法区)中**。在JDK7版本中，永久代的转移工作就已经开始了，将譬如符号引用转移到了native heap；字面量转移到了java heap；类的静态变量转移到了java heap。但是运行时常量池依然还存在，只是很多内容被转移，其只存着这些被转移的引用。
- **字符串常量池被分配到了Java堆的主要部分**。也就是**字符串常量池从运行时常量池分离出来了**。

### 1.8

- 静态常量池在Class文件中。
- **JVM已经将运行时常量池从方法区中移了出来，在Java堆（Heap）中开辟了一块区域存放运行时常量池**。**同时永久代被移除，以元空间代替**。元空间并不在虚拟机中，而是使用本地内存。因此，默认情况下，元空间的大小仅受本地内存限制。其主要用于存放一些元数据（类信息）。
- **字符串常量池存在于Java堆中**。


