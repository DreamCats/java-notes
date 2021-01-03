## String的一些问题

### String StringBuffer 和 StringBuilder 的区别是什么? String 为什么是不可变的?

先简单聊一下它们仨

#### 可变性
> 简单的来说：`String` 类中使用 `final` 关键字修饰字符数组来保存字符串，`private　final　char　value[]`，所以 `String` 对象是不可变的。
> `StringBuilder` 与 `StringBuffer` 都继承自 `AbstractStringBuilder` 类，在 `AbstractStringBuilder` 中也是使用字符数组保存字符串char[]value 但是没有用 `final` 关键字修饰，所以这两种对象都是可变的。

#### 线程安全
> `String` 中的对象是不可变的，也就可以理解为常量，线程安全。
> `AbstractStringBuilder` 是 `StringBuilder` 与 `StringBuffer` 的公共父类，定义了一些字符串的基本操作，如 `expandCapacity`、`append`、`insert`、`indexOf` 等公共方法。`StringBuffer` 对方法加了同步锁或者对调用的方法加了同步锁，所以是线程安全的。`StringBuilder` 并没有对方法进行加同步锁，所以是非线程安全的。


#### 性能
> 每次对 `String` 类型进行改变的时候，都会生成一个新的 `String` 对象，然后将指针指向新的 `String` 对象。
> `StringBuffer` 每次都会对 `StringBuffer` 对象本身进行操作，而不是生成新的对象并改变对象引用。相同情况下使用 `StringBuilder` 相比使用 `StringBuffer` 仅能获得 10%~15% 左右的性能提升，但却要冒多线程不安全的风险。

#### 总结一波
- 操作少量的数据: 适用String
- 单线程操作字符串缓冲区下操作大量数据: 适用StringBuilder
- 多线程操作字符串缓冲区下操作大量数据: 适用StringBuffer

#### 举个例子
```java
public static void main(String[] args) {
    // String
    String str = "hello";
    long start = System.currentTimeMillis();
    for (int i = 0; i < 100000; i++) {
        str += i; // 创建多少个对象，，
    }
    System.out.println("String: " + (System.currentTimeMillis() - start));

    // StringBuffer
    StringBuffer sb = new StringBuffer("hello");
    long start1 = System.currentTimeMillis();
    for (int i = 0; i < 1000000; i++) {
        sb.append(i);
    }
    System.out.println("StringBuffer: " + (System.currentTimeMillis() - start1));


    // StringBuilder
    StringBuilder stringBuilder = new StringBuilder("hello");
    long start2 = System.currentTimeMillis();
    for (int i = 0; i < 1000000; i++) {
        stringBuilder.append(i);
    }
    System.out.println("StringBuilder: " + (System.currentTimeMillis() - start2));
}
```

### String A = "123"; String B = new String("123");生成几个对象？
> 如果常量池中，原来没有“123”那么就是生成了2个对象，如果常量池中有“123”那么只要1个对象生成

这个问题，简单。 但是接下来这个可以看看`String.intern()`方法

举个例子：
```java
public class StringTest {
    public static void main(String[] args) {
        String str1 = "todo"; // 常量池
        String str2 = "todo"; // 从常量池找了str1
        String str3 = "to"; // 常量池
        String str4 = "do"; // 常量池
        String str5 = str3 + str4; // 内部用StringBuilder拼接了一波。 因此， 并非常量池
        String str6 = new String(str1); //  创建对象了， 那还能是常量池的引用？

        System.out.println("------普通String测试结果------");
        System.out.print("str1 == str2 ? "); 
        System.out.println( str1 == str2); // true
        System.out.print("str1 == str5 ? ");
        System.out.println(str1 == str5); // false
        System.out.print("str1 == str6 ? ");
        System.out.print(str1 == str6); // false
        System.out.println();

        System.out.println("---------intern测试结果---------"); // intern 直奔常量池上找
        System.out.print("str1.intern() == str2.intern() ? ");
        System.out.println(str1.intern() == str2.intern()); // true
        System.out.print("str1.intern() == str5.intern() ? ");
        System.out.println(str1.intern() == str5.intern()); // true
        System.out.print("str1.intern() == str6.intern() ? ");
        System.out.println(str1.intern() == str6.intern()); // true
        System.out.print("str1 == str6.intern() ? ");
        System.out.println(str1 == str6.intern()); // true
    }

```
结果一目了然， 但需要知道原理：
```
------普通String测试结果------
str1 == str2 ? true
str1 == str5 ? false
str1 == str6 ? false
---------intern测试结果---------
str1.intern() == str2.intern() ? true
str1.intern() == str5.intern() ? true
str1.intern() == str6.intern() ? true
str1 == str6.intern() ? true
```

待我分析一波：
> jvm你可要知道哇。 
> Java语言会使用常量池保存那些在编译期就已确定的已编译的class文件中的一份数据。主要有类、接口、方法中的常量，以及一些以文本形式出现的符号引用，如类和接口的全限定名、字段的名称和描述符、方法和名称和描述符等。

- 因此在编译完Intern类后，生成的class文件中会在常量池中保存“todo”、“to”和“do”三个String常量。
- 变量str1和str2均保存的是常量池中“todo”的引用，所以str1==str2成立；
- 在执行 str5 = str3 + str4这句时，JVM会先创建一个StringBuilder对象，通过StringBuilder.append()方法将str3与str4的值拼接，然后通过StringBuilder.toString()返回一个堆中的String对象的引用，赋值给str5，因此str1和str5指向的不是同一个String对象，str1 == str5不成立；
- String str6 = new String(str1)一句显式创建了一个新的String对象，因此str1 == str6不成立便是显而易见的事了。

重要的一点就是intern了：

> String.intern()是一个Native方法，底层调用C++的 StringTable::intern方法实现。当通过语句str.intern()调用intern()方法后，JVM 就会在当前类的常量池中查找是否存在与str等值的String，

- 若存在则直接返回常量池中相应Strnig的引用；若不存在，则会在常量池中创建一个等值的String，然后返回这个String在常量池中的引用。
- 因此，只要是等值的String对象，使用intern()方法返回的都是常量池中同一个String引用，所以，这些等值的String对象通过intern()后使用==是可以匹配的。
- 由此就可以理解上面代码中------intern------部分的结果了。因为str1、str5和str6是三个等值的String，所以通过intern()方法，**他们均会指向常量池中的同一个String引用**，因此str1.intern() == str5.intern() == str6.intern()均为true。

额外补充一哈：

#### JDK6
> Jdk6中常量池位于PermGen（永久代）中，PermGen是一块主要用于存放已加载的类信息和字符串池的大小固定的区域。执行intern()方法时，**若常量池中不存在等值的字符串，JVM就会在常量池中创建一个等值的字符串，然后返回该字符串的引用**。除此以外，JVM 会自动在常量池中保存一份之前已使用过的字符串集合。Jdk6中使用intern()方法的主要问题就在于常量池被保存在PermGen中：
> 首先，PermGen是一块大小固定的区域，一般不同的平台PermGen的默认大小也不相同，大致在32M到96M之间。所以不能对不受控制的运行时字符串（如用户输入信息等）使用intern()方法，否则很有可能会引发PermGen内存溢出；其次String对象保存在Java堆区，Java堆区与PermGen是物理隔离的，因此如果对多个不等值的字符串对象执行intern操作，则会导致内存中存在许多重复的字符串，会造成性能损失。

#### JDK7
> Jdk7将常量池从PermGen区移到了Java堆区，执行intern操作时，如果常量池已经存在该字符串，则直接返回字符串引用，否则**复制该字符串对象的引用到常量池中并返回**。堆区的大小一般不受限，所以将常量池从PremGen区移到堆区使得常量池的使用不再受限于固定大小。
> 除此之外，位于堆区的常量池中的对象可以被垃圾回收。当常量池中的字符串不再存在指向它的引用时，JVM就会回收该字符串。可以使用 -XX:StringTableSize 虚拟机参数设置字符串池的map大小。字符串池内部实现为一个HashMap，所以当能够确定程序中需要intern的字符串数目时，可以将该map的size设置为所需数目*2（减少hash冲突），这样就可以使得String.intern()每次都只需要常量时间和相当小的内存就能够将一个String存入字符串池中。


#### 适应场景
Jdk6中常量池位于PermGen区，大小受限，所以不建议适用intern()方法，当需要字符串池时，需要自己使用HashMap实现。Jdk7、8中，常量池由PermGen区移到了堆区，还可以通过-XX:StringTableSize参数设置StringTable的大小，常量池的使用不再受限，由此可以重新考虑使用intern()方法。intern(）方法优点：执行速度非常快，直接使用==进行比较要比使用equals(）方法快很多；内存占用少。虽然intern()方法的优点看上去很诱人，但若不是在恰当的场合中使用该方法的话，便非但不能获得如此好处，反而还可能会有性能损失。
