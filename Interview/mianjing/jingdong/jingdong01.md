## 京东Java实习一面电话面
- [原链接](https://www.nowcoder.com/discuss/414511)

### Java集合中线程安全的有哪些？
- Vector->SynchronizedList->CopyOnWriteArrayList
- SynchronizedSet->CopyOnWriteArraySet
- SynchronizedMap->ConcurrentHashMap

### 线程的创建方式
- 继承Thread类创建线程
```java
public class ThreadTest extends Thread{
    //重写run()方法，run()方法的方法体是线程执行体
    public void run(){
        for(int i=0;i<5;i++){
            //使用线程的getName()方法可以直接获取当前线程的名称
            System.out.println(this.getName() + "   " + i);
        }
    }

    public static void main(String[] args){
        //输出Java程序运行时默认运行的主线程名称
        System.out.println(Thread.currentThread().getName());
        //创建第一个线程并开始执行
        new ThreadTest().start();
        //创建第二个线程并开始执行
        new ThreadTest().start();
    }
}
```
- 实现Runnable接口创建线程
```java
public class ThreadTest implements Runnable{
    private int i;
    //重写run()方法，run()方法的方法体是线程执行体
    public void run(){
        //实现Runnable接口时，只能使用如下方法获取线程名
        System.out.println(Thread.currentThread().getName() + "   " + i);
        i++;
    }

    public static void main(String[] args){
        ThreadTest tt = new ThreadTest();
        //创建第一个线程并开始执行
        //输出   新线程1   0
        new Thread(tt,"新线程1").start();
        //创建第二个线程并开始执行
        //输出   新线程2   1
        new Thread(tt,"新线程2").start();
        //使用Lambda表达式创建Runnable对象
        new Thread(()->{
            System.out.print("hello");
            System.out.println("'world");
        }).start();
    }
}
```
- 使用Callable和Future创建线程
```java
public class ThreadTest {
    public static void main(String[] args){
        //使用FutureTask来包装Callable对象
        //使用Lambda表达式来创建Callable<Integer>对象
        FutureTask<Integer> task = new FutureTask<>(()->{
            System.out.println(Thread.currentThread().getName() + "   " + "开始执行任务！");
            return 0;
        });
        //实质还是以Callable对象来创建并启动线程
        //输出 新线程   开始执行任务！
        new Thread(task,"新线程").start();
        try{
            //获取线程的返回值
            //输出 0
            System.out.print(task.get());
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }
}
```
- 使用线程池例如用Executor框架
```java
public class ThreadTest {
    public static void main(String[] args){
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.submit(()->{
            System.out.println(Thread.currentThread().getName());
        });
    }
}
```

### 堆排序的实现过程（唯一的一道算法题）
```java
public class HeapSort {
    public static void heapSort(int[] arr){
        if (arr == null || arr.length < 2) return;
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i); // 依次从0~i形成大根堆
        }
        int heapSize = arr.length;
        swap(arr, 0, --heapSize);
        while (heapSize > 0) {
            heapify(arr, 0, heapSize);
            swap(arr, 0, --heapSize);
        }
    }
    
    public static void heapInsert(int[] arr, int index) {
        // 建立大根堆
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }
    
    public static void heapify(int[] arr, int index, int heapSize) {
        // 调整成大根堆
        int left = index * 2 + 1;
        while (left < heapSize) {
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left]
                ? left + 1
                : left;
            largest = arr[largest] > arr[index] ? largest : index;
            if (largest == index) {
                break; // 自己已经是最大了，直接跳出
            }
            swap(arr, largest, index);
            index = largest;
            left = index * 2 + 1;
        }
    }
    
    public static void swap(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }
}
```

### 垃圾回收算法 各自优缺点
- 标记-清除
> 该算法分为“标记”和“清除”阶段：首先标记出所有需要回收的对象，在标记完成后统一回收所有被标记的对象。它是最基础的收集算法，后续的算法都是对其不足进行改进得到。这种垃圾收集算法会带来两个明显的问题：
1. 效率问题
2. 空间问题(标记清除后会产生大量的不连续碎片)

- 标记-整理
> 根据老年代的特点提出的一种标记算法，标记过程仍然与“标记-清除”算法一样，但后续步骤不是直接对可回收对象回收，而是让所有存活的对象向一端移动，然后直接清理掉端边界以外的内存。

1. 慢
2. 解决了碎片

- 复制
> 为了解决效率问题，“复制”收集算法出现了。它可以将内存分为大小相同的两块，每次使用其中的一块。当这一块的内存使用完后，就将还存活的对象复制到另一块去，然后再把使用的空间一次清理掉。这样就使每次的内存回收都是对内存区间的一半进行回收。

1. 快

- 分代收集
> 比如在新生代中，每次收集都会有大量对象死去，所以可以选择复制算法，只需要付出少量对象的复制成本就可以完成每次垃圾收集。而老年代的对象存活几率是比较高的，而且没有额外的空间对它进行分配担保，所以我们必须选择“标记-清除”或“标记-整理”算法进行垃圾收集。

1. 根据特点选择不同场景下的收集算法，更加灵活。

### 数据库的三大范式
#### 第一范式
在任何一个关系数据库中，第一范式（1NF）是对关系模式的基本要求，不满足第一范式（1NF）的数据库就不是关系数据库。 
所谓第一范式（1NF）是指数据库表的每一列都是不可分割的基本数据项，同一列中不能有多个值，即实体中的某个属性不能有多个值或者不能有重复的属性。
如果出现重复的属性，就可能需要定义一个新的实体，新的实体由重复的属性构成，新实体与原实体之间为一对多关系。在第一范式（1NF）中表的每一行只包含一个实例的信息。简而言之，第一范式就是无重复的列。

#### 第二范式
第二范式（2NF）是在第一范式（1NF）的基础上建立起来的，即满足第二范式（2NF）必须先满足第一范式（1NF）。
第二范式（2NF）要求数据库表中的每个实例或行必须可以被惟一地区分。
为实现区分通常需要为表加上一个列，以存储各个实例的惟一标识。这个惟一属性列被称为主关键字或主键、主码。 
第二范式（2NF）要求实体的属性完全依赖于主关键字。
所谓完全依赖是指不能存在仅依赖主关键字一部分的属性，如果存在，那么这个属性和主关键字的这一部分应该分离出来形成一个新的实体，新实体与原实体之间是一对多的关系。
为实现区分通常需要为表加上一个列，以存储各个实例的惟一标识。简而言之，第二范式就是非主属性非部分依赖于主关键字。

### 第三范式
满足第三范式（3NF）必须先满足第二范式（2NF）。
简而言之，第三范式（3NF）要求一个数据库表中不包含已在其它表中已包含的非主关键字信息。
例如，存在一个部门信息表，其中每个部门有部门编号（dept_id）、部门名称、部门简介等信息。
那么在员工信息表中列出部门编号后就不能再将部门名称、部门简介等与部门有关的信息再加入员工信息表中。
如果不存在部门信息表，则根据第三范式（3NF）也应该构建它，否则就会有大量的数据冗余。简而言之，第三范式就是属性不依赖于其它非主属性。（我的理解是消除冗余）

### 对象存储为json文件，不借助第三方jar包，说说思路。
递归迭代
- [参考](https://blog.csdn.net/qq_35893120/article/details/84288551?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-4&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-4)
