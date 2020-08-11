> Java值传递，举例子比较合适

面试官：了解Java值传递嘛？

我：了解，我总结了一波：

- 一个方法**不能修改一个基本数据类型的参数**（即数值型或布尔型）。

- 一个方法可以改变**一个对象参数的状态**。

- 一个方法**不能让对象参数引用一个新的对象**。

所谓第一个，举例子先：

```java
public static void main(String[] args) {
    int num1 = 10;
    int num2 = 20;

    swap(num1, num2); // 交换

    System.out.println("num1 = " + num1); // 10
    System.out.println("num2 = " + num2); // 20
}

public static void swap(int a, int b) {
    int temp = a;
    a = b;
    b = temp;

    System.out.println("a = " + a); // 20
    System.out.println("b = " + b); // 10
}
// a = 20
// b = 10
// num1 = 10
// num2 = 20

```

![](http://media.dreamcat.ink/uPic/%E5%9F%BA%E6%9C%AC%E7%B1%BB%E5%9E%8B%E4%BC%A0%E9%80%92%20.png)

> 在 swap 方法中，**a、b 的值进行交换，并不会影响到 num1、num2**。因为，a、b 中的值，只是从 num1、num2 的复制过来的。也就是说，**a、b 相当于 num1、num2 的副本**，副本的内容无论怎么修改，都不会影响到原件本身。

所谓第二个，举例子先：

```java
    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5 };
        System.out.println(arr[0]); // 1
        change(arr);
        System.out.println(arr[0]); // 0
        // 得到的是对象引用的拷贝，对象引用及其他的拷贝同时引用同一个对象。
    }

    private static void change(int[] array) {
        // 修改数组中的一个元素
        array[0] = 0;
    }
// 1
// 0
```

![](http://media.dreamcat.ink/uPic/%E6%95%B0%E7%BB%84%E7%B1%BB%E5%9E%8B%E4%BC%A0%E9%80%92.png)

> array 被初始化 arr 的拷贝也就是**一个对象的引用**，也就是说 array 和 arr 指向的是**同一个数组对象**。 因此，外部对引用对象的改变会反映到所对应的对象上。

所谓第三个，举例子先：

```java
    public static void main(String[] args) {
        // 有些程序员认为 Java 程序设计语言对对象采用的是引用调用，实际上，这种理解是不对的。
        Student s1 = new Student("Mai");
        Student s2 = new Student("Feng");
        swap2(s1, s2);
        System.out.println("s1:" + s1.getName()); // Mai
        System.out.println("s2:" + s2.getName()); // Feng
        // 方法并没有改变存储在变量 s1 和 s2 中的对象引用。
        // swap 方法的参数 x 和 y 被初始化为两个对象引用的拷贝，这个方法交换的是这两个拷贝
    }

    private static void swap2(Student x, Student y) {
        Student temp = x;
        x = y;
        y = temp;
        System.out.println("x:" + x.getName()); // Feng
        System.out.println("y:" + y.getName()); // Mai
    }
// x:Feng
// y:Mai
// s1:Mai
// s2:Feng

```

![](http://media.dreamcat.ink/uPic/%E5%AF%B9%E8%B1%A1%E5%BC%95%E7%94%A8%E4%BC%A0%E9%80%92.png)

