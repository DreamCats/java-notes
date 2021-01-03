## 大纲图

![基础面试](http://media.dreamcat.ink/uPic/基础面试.png)

### 简述线程、程序、进程的基本概念。以及他们之间关系是什么?

**线程**与进程相似，但线程是一个比进程更小的执行单位。一个进程在其执行的过程中可以产生多个线程。与进程不同的是同类的多个线程共享同一块内存空间和一组系统资源，所以系统在产生一个线程，或是在各个线程之间作切换工作时，负担要比进程小得多，也正因为如此，线程也被称为轻量级进程。

**程序**是含有指令和数据的文件，被存储在磁盘或其他的数据存储设备中，也就是说程序是静态的代码。

**进程**是程序的一次执行过程，是系统运行程序的基本单位，因此进程是动态的。系统运行一个程序即是一个进程从创建，运行到消亡的过程。简单来说，一个进程就是一个执行中的程序，它在计算机中一个指令接着一个指令地执行着，同时，每个进程还占有某些系统资源如CPU时间，内存空间，文件，输入输出设备的使用权等等。换句话说，当程序在执行时，将会被操作系统载入内存中。 线程是进程划分成的更小的运行单位。线程和进程最大的不同在于基本上各进程是独立的，而各线程则不一定，因为同一进程中的线程极有可能会相互影响。从另一角度来说，进程属于操作系统的范畴，主要是同一段时间内，可以同时执行一个以上的程序，而线程则是在同一程序内几乎同时执行一个以上的程序段。

### Java 语言有哪些特点?

1. 简单易学；
2. 面向对象（封装，继承，多态）；
3. 平台无关性（ Java 虚拟机实现平台无关性）；
4. 可靠性；
5. 安全性；
6. 支持多线程（ C++ 语言没有内置的多线程机制，因此必须调用操作系统的多线程功能来进行多线程程序设计，而 Java 语言却提供了多线程支持）；
7. 支持网络编程并且很方便（ Java 语言诞生本身就是为简化网络编程设计的，因此 Java 语言不仅支持网络编程而且很方便）；
8. 编译与解释并存；

### 面向对象和面向过程的区别

- **面向过程** ：**面向过程性能比面向对象高。** 因为类调用时需要实例化，开销比较大，比较消耗资源，所以当性能是最重要的考量因素的时候，比如单片机、嵌入式开发、Linux/Unix等一般采用面向过程开发。但是，**面向过程没有面向对象易维护、易复用、易扩展。**
- **面向对象** ：**面向对象易维护、易复用、易扩展。** 因为面向对象有封装、继承、多态性的特性，所以可以设计出低耦合的系统，使系统更加灵活、更加易于维护。但是，**面向对象性能比面向过程低**。

> 这个并不是根本原因，面向过程也需要分配内存，计算内存偏移量，Java性能差的主要原因并不是因为它是面向对象语言，而是Java是半编译语言，最终的执行代码并不是可以直接被CPU执行的二进制机械码。
>
> 而面向过程语言大多都是直接编译成机械码在电脑上执行，并且其它一些面向过程的脚本语言性能也并不一定比Java好。

### ==、hashcode和equals

#### **==** 

它的作用是判断两个对象的地址是不是相等。即，判断两个对象是不是同一个对象(基本数据类型==比较的是值，引用数据类型==比较的是内存地址)。

#### **equals()** 

 它的作用也是判断两个对象是否相等。但它一般有两种使用情况：

- 情况1：类没有覆盖 equals() 方法。则通过 equals() 比较该类的两个对象时，等价于通过“==”比较这两个对象。
- 情况2：类覆盖了 equals() 方法。一般，我们都覆盖 equals() 方法来比较两个对象的内容是否相等；若它们的内容相等，则返回 true (即，认为这两个对象相等)。

```java
public class test1 {
    public static void main(String[] args) {
        String a = new String("ab"); // a 为一个引用
        String b = new String("ab"); // b为另一个引用,对象的内容一样
        String aa = "ab"; // 放在常量池中
        String bb = "ab"; // 从常量池中查找
        if (aa == bb) // true
            System.out.println("aa==bb");
        if (a == b) // false，非同一对象
            System.out.println("a==b");
        if (a.equals(b)) // true
            System.out.println("aEQb");
        if (42 == 42.0) { // true
            System.out.println("true");
        }
    }
}
```

- String 中的 equals 方法是被重写过的，因为 object 的 equals 方法是比较的对象的内存地址，而 String 的 equals 方法比较的是对象的值。
- 当创建 String 类型的对象时，虚拟机会在常量池中查找有没有已经存在的值和要创建的值相同的对象，如果有就把它赋给当前引用。如果没有就在常量池中重新创建一个 String 对象。

#### hashcode

**hashcode:**hashCode() 的作用是获取哈希码，也称为散列码；它实际上是返回一个int整数。这个哈希码的作用是确定该对象在哈希表中的索引位置。hashCode() 定义在JDK的Object.java中，这就意味着Java中的任何类都包含有hashCode() 函数。

散列表存储的是键值对(key-value)，它的特点是：能根据“键”快速的检索出对应的“值”。这其中就利用到了散列码！（可以快速找到所需要的对象）

#### 为什么要有hashcode

**我们先以“HashSet 如何检查重复”为例子来说明为什么要有 hashCode：** 当你把对象加入 HashSet 时，HashSet 会先计算对象的 hashcode 值来判断对象加入的位置，同时也会与其他已经加入的对象的 hashcode 值作比较，如果没有相符的hashcode，HashSet会假设对象没有重复出现。但是如果发现有相同 hashcode 值的对象，这时会调用 `equals()`方法来检查 hashcode 相等的对象是否真的相同。如果两者相同，HashSet 就不会让其加入操作成功。如果不同的话，就会重新散列到其他位置。（摘自我的Java启蒙书《Head first java》第二版）。这样我们就大大减少了 equals 的次数，相应就大大提高了执行速度。

通过我们可以看出：`hashCode()` 的作用就是**获取哈希码**，也称为散列码；它实际上是返回一个int整数。这个**哈希码的作用**是确定该对象在哈希表中的索引位置。**`hashCode() `在散列表中才有用，在其它情况下没用**。在散列表中hashCode() 的作用是获取对象的散列码，进而确定该对象在散列表中的位置。

#### hashcode和equals的相关规定

1. 如果两个对象相等，则hashcode一定也是相同的
2. 两个对象相等,对两个对象分别调用equals方法都返回true
3. 两个对象有相同的hashcode值，它们也不一定是相等的
4. **因此，equals 方法被覆盖过，则 hashCode 方法也必须被覆盖**
5. hashCode() 的默认行为是对堆上的对象产生独特值。如果没有重写 hashCode()，则该 class 的两个对象无论如何都不会相等（即使这两个对象指向相同的数据）

### JVM JDK 和 JRE 最详细通俗的解答

#### JVM

Java虚拟机（JVM）是运行 Java 字节码的虚拟机。JVM有针对不同系统的特定实现（Windows，Linux，macOS），目的是使用相同的字节码，它们都会给出相同的结果。

> 在 Java 中，JVM可以理解的代码就叫做`字节码`（即扩展名为 `.class` 的文件），它不面向任何特定的处理器，只面向虚拟机。Java 语言通过字节码的方式，在一定程度上解决了传统解释型语言执行效率低的问题，同时又保留了解释型语言可移植的特点。所以 Java 程序运行时比较高效，而且，由于字节码并不针对一种特定的机器，因此，Java程序无须重新编译便可在多种不同操作系统的计算机上运行。

#### JDK和JRE

**JDK**是Java Development Kit，它是功能齐全的Java SDK。它拥有JRE所拥有的一切，还有编译器（javac）和工具（如javadoc和jdb）。它能够创建和编译程序。

**JRE** 是 Java运行时环境。它是运行已编译 Java 程序所需的所有内容的集合，包括 Java虚拟机（JVM），Java类库，java命令和其他的一些基础构件。但是，它不能用于创建新程序。

如果你只是为了运行一下 Java 程序的话，那么你只需要安装 JRE 就可以了。如果你需要进行一些 Java 编程方面的工作，那么你就需要安装JDK了。但是，这不是绝对的。有时，即使您不打算在计算机上进行任何Java开发，仍然需要安装JDK。例如，如果要使用JSP部署Web应用程序，那么从技术上讲，您只是在应用程序服务器中运行Java程序。那你为什么需要JDK呢？因为应用程序服务器会将 JSP 转换为 Java servlet，并且需要使用 JDK 来编译 servlet。

### Java和C++的区别?

- 都是面向对象的语言，都支持封装、继承和多态
- Java 不提供指针来直接访问内存，程序内存更加安全
- Java 的类是单继承的，C++ 支持多重继承；虽然 Java 的类不可以多继承，但是接口可以多继承。
- Java 有自动内存管理机制，不需要程序员手动释放无用内存

### 基本类型

#### 字符型常量和字符串常量的区别?

1. 形式上: 字符常量是单引号引起的一个字符; 字符串常量是双引号引起的若干个字符
2. 含义上: 字符常量相当于一个整型值( ASCII 值),可以参加表达式运算; 字符串常量代表一个地址值(该字符串在内存中存放位置)
3. 占内存大小 字符常量只占2个字节; 字符串常量占若干个字节(至少一个字符结束标志) (**注意： char在Java中占两个字节**)

![](http://my-blog-to-use.oss-cn-beijing.aliyuncs.com/18-9-15/86735519.jpg)

#### 自动装箱与拆箱

- **装箱**：将基本类型用它们对应的引用类型包装起来；
- **拆箱**：将包装类型转换为基本数据类型；

#### 说说&和&&的区别

- &和&&都可以用作逻辑与的运算符，表示逻辑与（and）
- 当运算符两边的表达式的结果都为 true 时， 整个运算结果才为 true，否则，只要有一方为 false，则结果为 false。
- &&还具有短路的功能，即如果第一个表达式为 false，则不再计算第二个表达式
- &还可以用作位运算符，当&操作符两边的表达式不是 boolean 类型时，&表示按位与操作，我们通常 使用 0x0f 来与一个整数进行&运算，来获取该整数的最低 4 个 bit 位

#### short s1 = 1; s1 = s1 + 1;有什么错? short s1 = 1; s1 += 1; 有什么错?

- 对于 short s1 = 1; s1 = s1 + 1; 由于 s1+1 运算时会自动提升表达式的类型，所以结果是 int 型，再赋值 给 short 类型 s1 时，**编译器将报告需要强制转换类型的错误。**

- 对于 short s1 = 1; s1 += 1;由于 **+= 是 java 语言规定的运算符，java 编译器会对它进行特殊处理**，因此 可以正确编译。

- ```java
  public class TypeConvert {
      public static void main(String[] args) {
          // 字面量属于 double 类型
          // 不能直接将 1.1 直接赋值给 float 变量，因为这是向下转型
          // Java 不能隐式执行向下转型，因为这会使得精度降低。
          // float f = 1.1;
          float f = 1.1f;
  
          // 因为字面量 1 是 int 类型，它比 short 类型精度要高，因此不能隐式地将 int 类型下转型为 short 类型。
          short s1 = 1;
           // s1 = s1 + 1;
  
          // 但是使用 += 运算符可以执行隐式类型转换。
          s1 += 1;
          // 上面的语句相当于将 s1 + 1 的计算结果进行了向下转型:
          s1 = (short) (s1 + 1);
  
      }
  }
  ```

#### char 型变量中能不能存贮一个中文汉字?为什么?

- char 型变量是用来存储 Unicode 编码的字符的，unicode 编码字符集中包含了汉字，所以，char 型变量 中当然可以存储汉字啦。
- 如果某个特殊的汉字没有被包含在 unicode 编码字符集中，那么，这个 char 型变量中就不能存储这个特殊汉字。
- unicode 编码占用两个字节，所以，char 类型的变量也是占 用两个字节。

#### 整形包装类缓存池

```java
public class IntegerPackDemo {
  public static void main(String[] args) {
    Integer x = 3; // 装箱
    int z = x; // 拆箱
    Integer y = 3;
    System.out.println(x == y); // true
    // -------------------------
    Integer a = new Integer(3);
    Integer b = new Integer(3);
    System.out.println(a == b); // false 老生常谈了，就不说为什么了
    System.out.println(a.equals.(b)); // true // 这里是用重写了equals方法，比较的是值，而不是对象的地址
    // ------------------------
    // 缓存池
    Integer aa = Integer.valueOf(123);
    Integer bb = Integer.valueOf(123);
    System.out.println(aa == bb); // true
    /**
    * valueOf的源码
    * public static Integer valueOf(int i) {
    *      // 判断是否在Integer的范围内
    *     if (i >= IntegerCache.low && i <= IntegerCache.high)
    *         return IntegerCache.cache[i + (-IntegerCache.low)];
    *     return new Integer(i);
    * }
    */
  }
}
```

**当使用自动装箱方式创建一个Integer对象时，当数值在-128 ~127时，会将创建的 Integer 对象缓存起来，当下次再出现该数值时，直接从缓存中取出对应的Integer对象。所以上述代码中，x和y引用的是相同的Integer对象。**

### 面向对象

#### Java 面向对象编程三大特性: 封装 继承 多态

##### 封装

封装把一个对象的**属性私有化**，同时提供一些可以**被外界访问的属性的方法**，如果属性不想被外界访问，我们大可不必提供方法给外界访问。但是如果一个类没有提供给外界访问的方法，那么这个类也没有什么意义了。

##### 继承

继承是使用**已存在的类**的定义作为基础建立新类的技术，新类的定义可以增加**新的数据**或**新的功能**，也可以用**父类的功能**，但不**能选择性地继承父类**。通过使用继承我们能够非常方便地复用以前的代码。

**关于继承如下 3 点请记住：**

1. 子类拥有父类对象所有的属性和方法（包括私有属性和私有方法），但是父类中的私有属性和方法子类是无法访问，**只是拥有**。
2. 子类可以拥有自己属性和方法，即子类可以对父类进行扩展。
3. 子类可以用自己的方式实现父类的方法。（以后介绍）。

##### 多态

所谓多态就是指程序中定义的**引用变量所指向的具体类型**和**通过该引用变量发出的方法**调用在编程时**并不确定**，而是在程序**运行期间才确定**，即一个引用变量到底会指向哪个类的实例对象，该引用变量发出的方法调用到底是哪个类中实现的方法，必须在由程序运行期间才能决定。

在Java中有两种形式可以实现多态：继承（多个子类对同一方法的重写）和接口（实现接口并覆盖接口中同一方法）。

#### 构造器 Constructor 是否可被 override?

在讲继承的时候我们就知道父类的私有属性和构造方法并不能被继承，所以 Constructor 也就不能被 override（重写）,但是可以 overload（重载）,所以你可以看到一个类中有多个构造函数的情况。

#### 构造方法有哪些特性

1. 名字与类名相同。
2. 没有返回值，但不能用void声明构造函数。
3. 生成类的对象时自动执行，无需调用。

#### 接口和抽象类的区别是什么？

1. 接口的方法默认是 public，所有方法在接口中不能有实现(Java 8 开始接口方法可以有默认实现），而抽象类可以有非抽象的方法。
2. 接口中除了static、final变量，不能有其他变量，而抽象类中则不一定。
3. 一个类可以实现多个接口，但只能实现一个抽象类。接口自己本身可以通过implement关键字扩展多个接口。
4. 接口方法默认修饰符是public，抽象方法可以有public、protected和default这些修饰符（抽象方法就是为了被重写所以不能使用private关键字修饰！）。
5. 从设计层面来说，抽象是对类的抽象，是一种模板设计，而接口是对行为的抽象，是一种行为的规范。

备注：在JDK8中，接口也可以定义静态方法，可以直接用接口名调用。实现类和实现是不可以调用的。如果同时实现两个接口，接口中定义了一样的默认方法，则必须重写，不然会报错。

#### 成员变量与局部变量的区别有哪些？

1. 从语法形式上看:成员变量是属于类的，而局部变量是在方法中定义的变量或是方法的参数；成员变量可以被 public,private,static 等修饰符所修饰，而局部变量不能被访问控制修饰符及 static 所修饰；但是，成员变量和局部变量都能被 final 所修饰。
2. 从变量在内存中的存储方式来看:如果成员变量是使用`static`修饰的，那么这个成员变量是属于类的，如果没有使用`static`修饰，这个成员变量是属于实例的。而对象存在于堆内存，局部变量则存在于栈内存。
3. 从变量在内存中的生存时间上看:成员变量是对象的一部分，它随着对象的创建而存在，而局部变量随着方法的调用而自动消失。
4. 成员变量如果没有被赋初值:则会自动以类型的默认值而赋值（一种情况例外:被 final 修饰的成员变量也必须显式地赋值），而局部变量则不会自动赋值。

#### 重载和重写的区别

##### 重载

发生在同一个类中，方法名必须相同，参数类型不同、个数不同、顺序不同，方法返回值和访问修饰符可以不同。

##### 重写

重写是子类对父类的允许访问的方法的实现过程进行重新编写,发生在子类中，方法名、参数列表必须相同，返回值范围小于等于父类，抛出的异常范围小于等于父类，访问修饰符范围大于等于父类。另外，如果父类方法访问修饰符为 private 则子类就不能重写该方法。**也就是说方法提供的行为改变，而方法的外貌并没有改变。**

#### 创建一个对象用什么运算符?对象实体与对象引用有何不同?

new运算符，new创建对象实例（对象实例在堆内存中），对象引用指向对象实例（对象引用存放在栈内存中）。一个对象引用可以指向0个或1个对象（一根绳子可以不系气球，也可以系一个气球）;一个对象可以有n个引用指向它（可以用n条绳子系住一个气球）。

#### 对象的相等与指向他们的引用相等,两者有什么不同?

对象的相等，比的是内存中存放的内容是否相等。而引用相等，比较的是他们指向的内存地址是否相等。

### Java值传递

**按值调用(call by value)表示方法接收的是调用者提供的值，而按引用调用（call by reference)表示方法接收的是调用者提供的变量地址。一个方法可以修改传递引用所对应的变量值，而不能修改传递值调用所对应的变量值。**

接下来三个例子瞧一瞧：

基本类型传递

```java
public static void main(String[] args) {
    int num1 = 10;
    int num2 = 20;

    swap(num1, num2);

    System.out.println("num1 = " + num1);
    System.out.println("num2 = " + num2);
}

public static void swap(int a, int b) {
    int temp = a;
    a = b;
    b = temp;

    System.out.println("a = " + a);
    System.out.println("b = " + b);
}
// a = 20
// b = 10
// num1 = 10
// num2 = 20
```

![基本类型传递](http://media.dreamcat.ink/uPic/基本类型传递%20.png)

在 swap 方法中，a、b 的值进行交换，并不会影响到 num1、num2。因为，a、b 中的值，只是从 num1、num2 的复制过来的。也就是说，a、b 相当于 num1、num2 的副本，副本的内容无论怎么修改，都不会影响到原件本身。

接下来看数组：

```java
    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5 };
        System.out.println(arr[0]);
        change(arr);
        System.out.println(arr[0]);
        // 法得到的是对象引用的拷贝，对象引用及其他的拷贝同时引用同一个对象。
    }

    private static void change(int[] array) {
        // 修改数组中的一个元素
        array[0] = 0;
    }
// 1
// 0
```

![数组类型传递](http://media.dreamcat.ink/uPic/数组类型传递.png)

array 被初始化 arr 的拷贝也就是一个对象的引用，也就是说 array 和 arr 指向的是同一个数组对象。 因此，外部对引用对象的改变会反映到所对应的对象上。

**通过 example2 我们已经看到，实现一个改变对象参数状态的方法并不是一件难事。理由很简单，方法得到的是对象引用的拷贝，对象引用及其他的拷贝同时引用同一个对象。**

再看对象引用例子：

```java
    public static void main(String[] args) {
        // 有些程序员（甚至本书的作者）认为 Java 程序设计语言对对象采用的是引用调用，实际上，这种理解是不对的。
        Student s1 = new Student("Mai");
        Student s2 = new Student("Feng");
        swap2(s1, s2);
        System.out.println("s1:" + s1.getName());
        System.out.println("s2:" + s2.getName());
        // 方法并没有改变存储在变量 s1 和 s2 中的对象引用。
        // swap 方法的参数 x 和 y 被初始化为两个对象引用的拷贝，这个方法交换的是这两个拷贝
    }

    private static void swap2(Student x, Student y) {
        Student temp = x;
        x = y;
        y = temp;
        System.out.println("x:" + x.getName());
        System.out.println("y:" + y.getName());
    }
// x:Feng
// y:Mai
// s1:Mai
// s2:Feng
```

![对象引用传递](http://media.dreamcat.ink/uPic/对象引用传递.png)

**方法并没有改变存储在变量 s1 和 s2 中的对象引用。swap 方法的参数 x 和 y 被初始化为两个对象引用的拷贝，这个方法交换的是这两个拷贝**

下面再总结一下 Java 中方法参数的使用情况：

- 一个方法不能修改一个基本数据类型的参数（即数值型或布尔型）。
- 一个方法可以改变一个对象参数的状态。
- 一个方法不能让对象参数引用一个新的对象。

### String

#### String StringBuffer 和 StringBuilder 的区别是什么? String 为什么是不可变的?

##### **可变性**

简单的来说：String 类中使用 final 关键字修饰字符数组来保存字符串，`private　final　char　value[]`，所以 String 对象是不可变的。而StringBuilder 与 StringBuffer 都继承自 AbstractStringBuilder 类，在 AbstractStringBuilder 中也是使用字符数组保存字符串`char[]value` 但是没有用 final 关键字修饰，所以这两种对象都是可变的。

##### **线程安全性**

String 中的对象是不可变的，也就可以理解为常量，线程安全。AbstractStringBuilder 是 StringBuilder 与 StringBuffer 的公共父类，定义了一些字符串的基本操作，如 expandCapacity、append、insert、indexOf 等公共方法。StringBuffer 对方法加了同步锁或者对调用的方法加了同步锁，所以是线程安全的。StringBuilder 并没有对方法进行加同步锁，所以是非线程安全的。

##### **性能**

每次对 String 类型进行改变的时候，都会生成一个新的 String 对象，然后将指针指向新的 String 对象。StringBuffer 每次都会对 StringBuffer 对象本身进行操作，而不是生成新的对象并改变对象引用。相同情况下使用 StringBuilder 相比使用 StringBuffer 仅能获得 10%~15% 左右的性能提升，但却要冒多线程不安全的风险。

##### **对于三者使用的总结：**

1. 操作少量的数据: 适用String
2. 单线程操作字符串缓冲区下操作大量数据: 适用StringBuilder
3. 多线程操作字符串缓冲区下操作大量数据: 适用StringBuffer

##### 代码例子

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

#### String A = "123"; String B = new String("123");生成几个对象？

如果常量池中，原来没有“123”那么就是生成了2个对象，如果常量池中有“123”那么只要1个对象生成

#### 聊一聊String.intern()这个方法

```java
public class StringTest {
	public static void main(String[] args) {
				String str1 = "todo";
        String str2 = "todo";
        String str3 = "to";
        String str4 = "do";
        String str5 = str3 + str4;
        String str6 = new String(str1);
 
        System.out.println("------普通String测试结果------");
        System.out.print("str1 == str2 ? ");
        System.out.println( str1 == str2);
        System.out.print("str1 == str5 ? ");
        System.out.println(str1 == str5);
        System.out.print("str1 == str6 ? ");
        System.out.print(str1 == str6);
        System.out.println();
 
        System.out.println("---------intern测试结果---------");
        System.out.print("str1.intern() == str2.intern() ? ");
        System.out.println(str1.intern() == str2.intern());
        System.out.print("str1.intern() == str5.intern() ? ");
        System.out.println(str1.intern() == str5.intern());
        System.out.print("str1.intern() == str6.intern() ? ");
        System.out.println(str1.intern() == str6.intern());
        System.out.print("str1 == str6.intern() ? ");
        System.out.println(str1 == str6.intern());
	}
}
```

运行结果：

```java
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

普通String代码结果分析：

Java语言会使用常量池保存那些在编译期就已确定的已编译的class文件中的一份数据。主要有类、接口、方法中的常量，以及一些以文本形式出现的符号引用，如类和接口的全限定名、字段的名称和描述符、方法和名称和描述符等。因此在编译完Intern类后，生成的class文件中会在常量池中**保存“todo”、“to”和“do”三个String常量**。**变量str1和str2均保存的是常量池中“todo”的引用，所以str1==str2成立**；在执行 str5 = str3 + str4这句时，JVM会先创建一个**StringBuilder对象**，通过StringBuilder.append()方法将str3与str4的值拼接，然后通过StringBuilder.toString()返回一个**堆中的String对象的引用**，赋值给str5，因此str1和str5指向的不是同一个String对象，str1 == str5不成立；String str6 = new String(str1)一句显式创建了一个新的String对象，因此str1 == str6不成立便是显而易见的事了。

intern代码结果分析：

String.intern()是一个Native方法，底层调用C++的 StringTable::intern方法实现。当通过语句str.intern()调用intern()方法后，JVM 就会在当前类的常量池中查找是否存在与str等值的String，**若存在则直接返回常量池中相应Strnig的引用**；**若不存在，则会在常量池中创建一个等值的String，然后返回这个String在常量池中的引用**。因此，只要是等值的String对象，使用intern()方法返回的都是常量池中同一个String引用，所以，这些等值的String对象通过intern()后使用==是可以匹配的。由此就可以理解上面代码中------intern------部分的结果了。因为str1、str5和str6是三个等值的String，所以通过intern()方法，他们均会指向常量池中的同一个String引用，因此str1.intern() == str5.intern() == str6.intern()均为true。

##### jdk6

Jdk6中常量池位于PermGen（永久代）中，PermGen是一块主要用于存放已加载的类信息和字符串池的大小固定的区域。执行intern()方法时，**若常量池中不存在等值的字符串，JVM就会在常量池中创建一个等值的字符串，然后返回该字符串的引用**。除此以外，JVM 会自动在常量池中保存一份之前已使用过的字符串集合。Jdk6中使用intern()方法的主要问题就在于常量池被保存在PermGen中：首先，PermGen是一块大小固定的区域，一般不同的平台PermGen的默认大小也不相同，大致在32M到96M之间。所以不能对不受控制的运行时字符串（如用户输入信息等）使用intern()方法，否则很有可能会引发PermGen内存溢出；其次String对象保存在Java堆区，Java堆区与PermGen是物理隔离的，因此如果对多个不等值的字符串对象执行intern操作，则会导致内存中存在许多重复的字符串，会造成性能损失。

##### jdk7

Jdk7**将常量池从PermGen区移到了Java堆区**，执行intern操作时，**如果常量池已经存在该字符串，则直接返回字符串引用**，否则**复制该字符串对象的引用**到常量池中并返回。堆区的大小一般不受限，所以将常量池从PremGen区移到堆区使得常量池的使用不再受限于固定大小。除此之外，位于堆区的常量池中的对象可以被垃圾回收。当常量池中的字符串不再存在指向它的引用时，JVM就会回收该字符串。可以使用 -XX:StringTableSize 虚拟机参数设置字符串池的map大小。字符串池内部实现为一个HashMap，所以当能够确定程序中需要intern的字符串数目时，可以将该map的size设置为所需数目*2（减少hash冲突），这样就可以使得String.intern()每次都只需要常量时间和相当小的内存就能够将一个String存入字符串池中。

##### 适用场景

Jdk6中常量池位于PermGen区，大小受限，所以不建议适用intern()方法，当需要字符串池时，需要自己使用HashMap实现。Jdk7、8中，常量池由PermGen区移到了堆区，还可以通过-XX:StringTableSize参数设置StringTable的大小，常量池的使用不再受限，由此可以重新考虑使用intern()方法。intern(）方法优点：执行速度非常快，直接使用==进行比较要比使用equals(）方法快很多；内存占用少。虽然intern()方法的优点看上去很诱人，但若不是在恰当的场合中使用该方法的话，便非但不能获得如此好处，反而还可能会有性能损失。

### 关键字

#### final

final关键字主要用在三个地方：变量、方法、类。

1. 对于一个final变量，如果是基本数据类型的变量，则其数值一旦在初始化之后便不能更改；如果是引用类型的变量，则在对其初始化之后便不能再让其指向另一个对象。
2. 当用final修饰一个类时，表明这个类不能被继承。final类中的所有成员方法都会被隐式地指定为final方法。
3. 使用final方法的原因有两个。第一个原因是把方法锁定，以防任何继承类修改它的含义；第二个原因是效率。在早期的Java实现版本中，会将final方法转为内嵌调用。但是如果方法过于庞大，可能看不到内嵌调用带来的任何性能提升（现在的Java版本已经不需要使用final方法进行这些优化了）。类中所有的private方法都隐式地指定为final。

final修饰有啥好处

- final的关键字提高了性能，JVM和java应用会缓存final变量；
- final变量可以在多线程环境下保持线程安全；
- 使用final的关键字提高了性能，JVM会对方法变量类进行优化；

#### static

1. **修饰成员变量和成员方法:** 被 static 修饰的成员属于类，不属于单个这个类的某个对象，被类中所有对象共享，可以并且建议通过类名调用。被static 声明的成员变量属于静态成员变量，静态变量 存放在 Java 内存区域的方法区。调用格式：`类名.静态变量名` `类名.静态方法名()`
2. **静态代码块:** 静态代码块定义在类中方法外, 静态代码块在非静态代码块之前执行(静态代码块—>非静态代码块—>构造方法)。 该类不管创建多少对象，静态代码块只执行一次.
3. **静态内部类（static修饰类的话只能修饰内部类）：** 静态内部类与非静态内部类之间存在一个最大的区别: 非静态内部类在编译完成之后会隐含地保存着一个引用，该引用是指向创建它的外围类，但是静态内部类却没有。没有这个引用就意味着：1. 它的创建是不需要依赖外围类的创建。2. 它不能使用任何外围类的非static成员变量和方法。
4. **静态导包(用来导入类中的静态资源，1.5之后的新特性):** 格式为：`import static` 这两个关键字连用可以指定导入某个类中的指定静态资源，并且不需要使用类名调用类中静态成员，可以直接使用类中静态成员变量和成员方法。

#### this

```java
class Manager {
    Employees[] employees;
     
    void manageEmployees() {
        int totalEmp = this.employees.length;
        System.out.println("Total employees: " + totalEmp);
        this.report();
    }
     
    void report() { }
}
```

在上面的示例中，this关键字用于两个地方：

- this.employees.length：访问类Manager的当前实例的变量。
- this.report（）：调用类Manager的当前实例的方法。

此关键字是可选的，这意味着如果上面的示例在不使用此关键字的情况下表现相同。 但是，使用此关键字可能会使代码更易读或易懂。

#### super

```java
public class Super {
    protected int number;
     
    protected showNumber() {
        System.out.println("number = " + number);
    }
}
 
public class Sub extends Super {
    void bar() {
        super.number = 10;
        super.showNumber();
    }
}
```

在上面的例子中，Sub 类访问父类成员变量 number 并调用其其父类 Super 的 `showNumber（）` 方法。

**使用 this 和 super 要注意的问题：**

- 在构造器中使用 `super（）` 调用父类中的其他构造方法时，该语句必须处于构造器的首行，否则编译器会报错。另外，this 调用本类中的其他构造方法时，也要放在首行。
- this、super不能用在static方法中。

**简单解释一下：**

被 static 修饰的成员属于类，不属于单个这个类的某个对象，被类中所有对象共享。而 this 代表对本类对象的引用，指向本类对象；而 super 代表对父类对象的引用，指向父类对象；所以， **this和super是属于对象范畴的东西，而静态方法是属于类范畴的东西**。

#### final, finally, finalize 的区别

- final 用于声明属性，方法和类，分别表示属性不可变，方法不可覆盖，类不可继承。 内部类要访问局部变量，局部变量必须定义成 final 类型
- finally 是异常处理语句结构的一部分，表示总是执行。
- finalize 是 Object 类的一个方法，在垃圾收集器执行的时候会调用被回收对象的此方法， 可以覆盖此方法提供垃圾收集时的其他资源回收，例如关闭文件等。JVM 不保证此方法总被调用

#### 请说出作用域 public，private，protected

|  作用域   | 当前类 | 同package | 子孙类 | 其他package |
| :-------: | :----: | :-------: | :----: | :---------: |
|  public   |   √    |     √     |   √    |      √      |
| protected |   √    |     √     |   √    |      ×      |
| friednly  |   √    |     √     |   ×    |      ×      |
|  private  |   √    |     ×     |   ×    |      ×      |

### 异常处理

在 Java 中，所有的异常都有一个共同的祖先java.lang包中的 **Throwable类**。Throwable： 有两个重要的子类：**Exception（异常）** 和 **Error（错误）** ，二者都是 Java 异常处理的重要子类，各自都包含大量子类。

#### Error

**是程序无法处理的错误**，表示运行应用程序中较严重问题。大多数错误与代码编写者执行的操作无关，而表示代码运行时 JVM（Java 虚拟机）出现的问题。例如，Java虚拟机运行错误（Virtual MachineError），当 JVM 不再有继续执行操作所需的内存资源时，将出现 OutOfMemoryError。这些异常发生时，Java虚拟机（JVM）一般会选择线程终止。

这些错误表示故障发生于虚拟机自身、或者发生在虚拟机试图执行应用时，如Java虚拟机运行错误（Virtual MachineError）、类定义错误（NoClassDefFoundError）等。这些错误是不可查的，因为它们在应用程序的控制和处理能力之 外，而且绝大多数是程序运行时不允许出现的状况。对于设计合理的应用程序来说，即使确实发生了错误，本质上也不应该试图去处理它所引起的异常状况。在 Java中，错误通过Error的子类描述。

#### Exception

**是程序本身可以处理的异常**。Exception 类有一个重要的子类 **RuntimeException**。RuntimeException 异常由Java虚拟机抛出。**NullPointerException**（要访问的变量没有引用任何对象时，抛出该异常）、**ArithmeticException**（算术运算异常，一个整数除以0时，抛出该异常）和 **ArrayIndexOutOfBoundsException** （下标越界异常）。

#### 处理

- **try 块：** 用于捕获异常。其后可接零个或多个catch块，如果没有catch块，则必须跟一个finally块。
- **catch 块：** 用于处理try捕获到的异常。
- **finally 块：** 无论是否捕获或处理异常，finally块里的语句都会被执行。当在try块或catch块中遇到return 语句时，finally语句块将在方法返回之前被执行。

**在以下4种特殊情况下，finally块不会被执行：**

1. 在finally语句块第一行发生了异常。 因为在其他行，finally块还是会得到执行
2. 在前面的代码中用了System.exit(int)已退出程序。 exit是带参函数 ；若该语句在异常语句之后，finally会执行
3. 程序所在的线程死亡。
4. 关闭CPU。

**注意：** 当try语句和finally语句中都有return语句时，在方法返回之前，finally语句的内容将被执行，并且finally语句的返回值将会覆盖原始的返回值。如下：

```java
    public static int f(int value) {
        try {
            return value * value;
        } finally {
            if (value == 2) {
                return 0;
            }
        }
    }
```

如果调用 `f(2)`，返回值将是0，因为finally语句的返回值覆盖了try语句块的返回值。

#### 最常见到的 5 个 runtime exception

- RuntimeException
- NullPointerException、ArrayIndexOutOfBoundsException、ClassCastException。

#### Throwable图解

![异常分类](http://media.dreamcat.ink/uPic/异常分类.png)

### IO

#### 获取用键盘输入常用的两种方法

方法1：通过 Scanner

```java
Scanner input = new Scanner(System.in);
String s  = input.nextLine();
input.close();
```

方法2：通过 BufferedReader

```java
BufferedReader input = new BufferedReader(new InputStreamReader(System.in)); 
String s = input.readLine(); 
```

#### Java 中 IO 流分为几种?

- 按照流的流向分，可以分为输入流和输出流；
- 按照操作单元划分，可以划分为字节流和字符流；
- 按照流的角色划分为节点流和处理流。

Java Io流共涉及40多个类，这些类看上去很杂乱，但实际上很有规则，而且彼此之间存在非常紧密的联系， Java I0流的40多个类都是从如下4个抽象类基类中派生出来的。

- InputStream/Reader: 所有的输入流的基类，前者是字节输入流，后者是字符输入流。
- OutputStream/Writer: 所有输出流的基类，前者是字节输出流，后者是字符输出流。

#### 既然有了字节流,为什么还要有字符流?

问题本质想问：**不管是文件读写还是网络发送接收，信息的最小存储单元都是字节，那为什么 I/O 流操作要分为字节流操作和字符流操作呢？**

回答：字符流是由 Java 虚拟机将字节转换得到的，问题就出在这个过程还算是非常耗时，并且，如果我们不知道编码类型就很容易出现乱码问题。所以， I/O 流就干脆提供了一个直接操作字符的接口，方便我们平时对字符进行流操作。如果音频文件、图片等媒体文件用字节流比较好，如果涉及到字符的话使用字符流比较好。

#### BIO,NIO,AIO 有什么区别?

- **BIO (Blocking I/O):** 同步阻塞I/O模式，数据的读取写入必须阻塞在一个线程内等待其完成。在活动连接数不是特别高（小于单机1000）的情况下，这种模型是比较不错的，可以让每一个连接专注于自己的 I/O 并且编程模型简单，也不用过多考虑系统的过载、限流等问题。线程池本身就是一个天然的漏斗，可以缓冲一些系统处理不了的连接或请求。但是，当面对十万甚至百万级连接的时候，传统的 BIO 模型是无能为力的。因此，我们需要一种更高效的 I/O 处理模型来应对更高的并发量。
- **NIO (New I/O):** NIO是一种同步非阻塞的I/O模型，在Java 1.4 中引入了NIO框架，对应 java.nio 包，提供了 Channel , Selector，Buffer等抽象。NIO中的N可以理解为Non-blocking，不单纯是New。它支持面向缓冲的，基于通道的I/O操作方法。 NIO提供了与传统BIO模型中的 `Socket` 和 `ServerSocket` 相对应的 `SocketChannel` 和 `ServerSocketChannel` 两种不同的套接字通道实现,两种通道都支持阻塞和非阻塞两种模式。阻塞模式使用就像传统中的支持一样，比较简单，但是性能和可靠性都不好；非阻塞模式正好与之相反。对于低负载、低并发的应用程序，可以使用同步阻塞I/O来提升开发速率和更好的维护性；对于高负载、高并发的（网络）应用，应使用 NIO 的非阻塞模式来开发
- **AIO (Asynchronous I/O):** AIO 也就是 NIO 2。在 Java 7 中引入了 NIO 的改进版 NIO 2,它是异步非阻塞的IO模型。异步 IO 是基于事件和回调机制实现的，也就是应用操作之后会直接返回，不会堵塞在那里，当后台处理完成，操作系统会通知相应的线程进行后续的操作。AIO 是异步IO的缩写，虽然 NIO 在网络操作中，提供了非阻塞的方法，但是 NIO 的 IO 行为还是同步的。对于 NIO 来说，我们的业务线程是在 IO 操作准备好时，得到通知，接着就由这个线程自行进行 IO 操作，IO操作本身是同步的。查阅网上相关资料，我发现就目前来说 AIO 的应用还不是很广泛，Netty 之前也尝试使用过 AIO，不过又放弃了。

### 反射

#### 反射式什么？

**Java反射机制是在运行状态中，对于任意一个类，都能够知道这个类的所有属性和方法；对于任意一个对象，都能够调用它的任意一个方法和属性；这种动态获取的信息以及动态调用对象的方法的功能成为java语言的反射机制。**

#### 静态编译和动态编译

- 静态编译：在编译时确定类型，绑定对象
- 动态编译：运行时确定类型，绑定对象

#### 反射机制优缺点

- 优点：运行期间类型的判断，动态加载类，提高代码的灵活度。
- 缺点：性能瓶颈：反射相当于一系列解释操作，通知JVM要做的事情，性能比直接的java代码要慢很多。

#### 反射的应用场景

在我们平时的项目开发过程中，基本上很少会直接使用的反射机制，但这不能说明反射机制没有用，实际上有很多设计、开发都与反射机制有关，例如**模块化**的开发，通过反射去调用对应的字节码；动态代理设计模型也采用了反射机制，还有我们日常使用的**Spring / Hibernate**等框架也大量使用到了反射机制。

- 我们在使用JDBC连接数据库时使用`Class.forName()`通过反射加载数据看的驱动程序；
- Spring框架也用到很多反射机制，最经典的就是xml的配置模式。Spring通过XML配置模式装载Bean的过程；
  - 将程序内所有XML或Properties配置文件加载入内存中；
  - Java类里面解析xml或properties里面的内容，得到对应实体类的字节码字符串以及相关的属性信息；
  - 使用反射机制，根据这个字符串获得某个类的Class实例
  - 动态配置实例的属性

#### 反射得到的Class对象的三种方式

```java
public class ReflectDemo {
    public static void main(String[] args) throws ClassNotFoundException {
        // 第一种方式获取Class对象
        Student student = new Student(); // 这一new 产生一个Student对象，一个Class对象。
        Class studentClass = student.getClass();
        System.out.println(studentClass.getName()); // com.reflect.Student

        // 第二种方式获取Class对象
        Class studentClass2 = Student.class;
        System.out.println(studentClass == studentClass2); //判断第一种方式获取的Class对象和第二种方式获取的是否是同一个

        //第三种方式获取Class对象
        Class studentClass3 = Class.forName("com.reflect.Student"); //注意此字符串必须是真实路径，就是带包名的类路径，包名.类名
        System.out.println(studentClass3 == studentClass2); // //判断三种方式是否获取的是同一个Class对象

        // 三种方式常用第三种，第一种对象都有了还要反射干什么。
        // 第二种需要导入类的包，依赖太强，不导包就抛编译错误。
        // 一般都第三种，一个字符串可以传入也可写在配置文件中等多种方法。
    }
}
```

#### 反射访问并调用构造方法

```java
public class ConstructorsDemo {
    public static void main(String[] args) throws Exception {
        // 1. 加载Class对象
        Class clazz = Class.forName("com.reflect.Student");

        // 2. 获取所有公有构造方法
        System.out.println("**********************所有公有构造方法*********************************");
        Constructor[] constructors = clazz.getConstructors();
        for (Constructor constructor : constructors) {
            System.out.println(constructor);
        }

        // 3.
        System.out.println("************所有的构造方法(包括：私有、受保护、默认、公有)***************");
        Constructor[] declaredConstructors = clazz.getDeclaredConstructors();
        for (Constructor declaredConstructor : declaredConstructors) {
            System.out.println(declaredConstructor);
        }

        // 4.
        System.out.println("*****************获取公有、无参的构造方法*******************************");
        Constructor constructor = clazz.getConstructor();
        System.out.println(constructor);

        // 调用构造方法
        Object object = constructor.newInstance();
        System.out.println(object);

        //
        System.out.println("******************获取私有构造方法，并调用*******************************");
        Constructor constructor1 = clazz.getDeclaredConstructor(char.class);
        System.out.println(constructor1);
        // 调用构造方法
        constructor1.setAccessible(true); // 暴力访问
        Object object2 = constructor1.newInstance('买');
        System.out.println(object2);
    }
}
```

#### 反射访问并调用成员变量

```java
public class FieldDemo {
    public static void main(String[] args) throws Exception {
        // 1. 获取class对象
        Class clazz = Class.forName("com.reflect.Student");
        // 2. 获取所有字段
        System.out.println("************获取所有公有的字段********************");
        Field[] fields = clazz.getFields();
        for (Field field : fields) {
            System.out.println(field);
        }
        //
        System.out.println("************获取所有的字段(包括私有、受保护、默认的)********************");
        Field[] fields1 = clazz.getDeclaredFields();
        for (Field field : fields1) {
            System.out.println(field);
        }
        //
        System.out.println("*************获取公有字段**并调用***********************************");
        Field gender = clazz.getField("gender");
        System.out.println(gender);
        // 获取一个对象
        Object o = clazz.getConstructor().newInstance();
        gender.set(o, "男");
        Student stu = (Student) o;
        System.out.println("验证性别：" + stu.getGender());
        //
        System.out.println("*************获取公有字段**并调用***********************************");
        Field name = clazz.getDeclaredField("name");
        System.out.println(name);
        name.setAccessible(true); //暴力反射，解除私有限定
        name.set(o, "买");
        System.out.println("验证姓名：" + stu);
    }
}
```

#### 反射访问并调用成员方法

```java
public class MethodDemo {
    public static void main(String[] args) throws Exception {
        // 1. 获取对象
        Class clazz = Class.forName("com.reflect.Student");
        // 2. 获取所有公有方法
        System.out.println("***************获取所有的”公有“方法*******************");
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            System.out.println(method);
        }
        System.out.println("***************获取所有的方法，包括私有的*******************");
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println(declaredMethod);
        }
        System.out.println("***************获取公有的show1()方法*******************");
        Method m = clazz.getMethod("show1", String.class);
        System.out.println(m);
        // 实例化对象
        Object o = clazz.getConstructor().newInstance();
        m.invoke(o, "买");

        System.out.println("***************获取私有的show4()方法******************");
        m = clazz.getDeclaredMethod("show4", int.class);
        System.out.println(m);
        m.setAccessible(true); // 暴力解除 私有
        Object result = m.invoke(o, 20);//需要两个参数，一个是要调用的对象（获取有反射），一个是实参
        System.out.println("返回值：" + result);
    }
}
```

### 深拷贝VS浅拷贝

1. **浅拷贝**：对基本数据类型进行值传递，对引用数据类型进行引用传递般的拷贝，此为浅拷贝。

2. **深拷贝**：对基本数据类型进行值传递，对引用数据类型，创建一个新的对象，并复制其内容，此为深拷贝。

   ![deep and shallow copy](https://my-blog-to-use.oss-cn-beijing.aliyuncs.com/2019-7/java-deep-and-shallow-copy.jpg)

#### 浅拷贝例子

```java
public class ShallowCopyDemo {
    public static void main(String[] args) throws CloneNotSupportedException {
        // 原始对象
        Student student = new Student(new Subject("买"), "峰");
        System.out.println("原始对象: " + student.getName() + " - " + student.getSubject().getName());

        // 拷贝对象
        Student cloneStu = (Student) student.clone();
        System.out.println("拷贝对象: " + cloneStu.getName() + " - " + cloneStu.getSubject().getName());

        // 原始对象和拷贝对象是否一样：
        System.out.println("原始对象和拷贝对象是否一样: " + (student == cloneStu));

        // 原始对象和拷贝对象的name属性是否一样
        System.out.println("原始对象和拷贝对象的name属性是否一样: " + (student.getName() == cloneStu.getName()));

        // 原始对象和拷贝对象的subj属性是否一样
        System.out.println("原始对象和拷贝对象的subj属性是否一样: " + (student.getSubject() == cloneStu.getSubject()));

        student.setName("小");
        student.getSubject().setName("疯");
        System.out.println("更新后的原始对象: " + student.getName() + " - " + student.getSubject().getName());
        System.out.println("更新原始对象后的克隆对象: " + cloneStu.getName() + " - " + cloneStu.getSubject().getName());
        // 在这个例子中，让要拷贝的类Student实现了Clonable接口并重写Object类的clone()方法，然后在方法内部调用super.clone()方法。
        // 从输出结果中我们可以看到，对原始对象stud的"name"属性所做的改变并没有影响到拷贝对象clonedStud；
        // 但是对引用对象subj的"name"属性所做的改变影响到了拷贝对象clonedStud。
    }
}
```

结果如下：

```
原始对象: 峰 - 买
拷贝对象: 峰 - 买
原始对象和拷贝对象是否一样: false
原始对象和拷贝对象的name属性是否一样: true
原始对象和拷贝对象的subj属性是否一样: true
更新后的原始对象: 小 - 疯
更新原始对象后的克隆对象: 峰 - 疯
```

**可以看到，浅拷贝中的引用类型并没有拷贝一份，都指向同一个对象**。

深拷贝例子

```java
// 重写 student的clone方法，例子还是如上。
	@Override
    protected Object clone() throws CloneNotSupportedException {
        // 浅拷贝
//        return super.clone();

        // 深拷贝
        Student student = new Student(new Subject(subject.getName()), name);
        return student;
        // 因为它是深拷贝，所以你需要创建拷贝类的一个对象。
        // 因为在Student类中有对象引用，所以需要在Student类中实现Cloneable接口并且重写clone方法。
    }
```

结果如下：

```
原始对象: 峰 - 买
拷贝对象: 峰 - 买
原始对象和拷贝对象是否一样: false
原始对象和拷贝对象的name属性是否一样: true
原始对象和拷贝对象的subj属性是否一样: false
更新后的原始对象: 小 - 疯
更新原始对象后的克隆对象: 峰 - 买
```

**可以看到，浅拷贝中的引用类型创建一份新对象**。

> 创作不易哇，觉得有帮助的话，给个小小的star呗。[github地址](https://github.com/DreamCats/JavaBooks)😁😁😁