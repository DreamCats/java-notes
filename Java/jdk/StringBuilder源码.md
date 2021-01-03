## 类

```java
// 虽然该类上加了final
// 但是一会看看变量char[]
// 继承了ASB
// StringBuilder没有char，但是他的抽象父类有
public final class StringBuilder 
    extends AbstractStringBuilder 
    implements java.io.Serializable, CharSequence
```

## 变量

```java
// 没有被final修饰，因此可变
char[] value;
// 计数
int count;
```

## 方法

### append(char[] str)

大家最常用的

```java
// 毕竟是char数组类型，需要判断扩容
// 其次是count++，跟ArrayList差不多的，size++
@Override
public AbstractStringBuilder append(char c) {
    ensureCapacityInternal(count + 1);
    value[count++] = c;
    return this;
}
```

### charAt(int index)

也是大家常用的

```java
// 很多都差不多的，看多了，也就那样了
@Override
public char charAt(int index) {
    // 判断是否索引越界
    if ((index < 0) || (index >= count))
        throw new StringIndexOutOfBoundsException(index);
    // 按照索引取对应数组上的值
    return value[index];
}
```

### length()

```java
// 直接返回维护的count变量
@Override
public int length() {
    return count;
}
```

### reverse()

```java
// 看名字就知道翻转
public AbstractStringBuilder reverse() {
    // 维护一个 变量？ 是否代理？
    boolean hasSurrogates = false;
    int n = count - 1;
    // 双指针交换位置？而且还是从中间
    // 相当于 abcde j在b， k在d
    // j-- k++
    for (int j = (n-1) >> 1; j >= 0; j--) {
        int k = n - j;
        // 前
        char cj = value[j];
        // 后
        char ck = value[k];
        value[j] = ck;
        value[k] = cj;
        if (Character.isSurrogate(cj) ||
            Character.isSurrogate(ck)) {
            hasSurrogates = true;
        }
    }
    if (hasSurrogates) {
        reverseAllValidSurrogatePairs();
    }
    return this;
}
```



注意：

StringBuffer跟StringBuilder差不多是一样的，只不过前者在很多方法上加了synchronized同步锁，因此它是线程安全的，而后者不是。

