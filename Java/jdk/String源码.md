> 主要针对于面试需要看的参数和方法

## 类

```java
public final class String implements java.io.Serializable, Comparable<String>, CharSequence
```

可以看出该String被final修饰了，因此该类的所有方法都被隐式的加了final

## 参数

```java
// 该值用于字符存储。
private final char value[]; 
// 缓存字符串的哈希代码
private int hash;
```

## 方法

### equals

```java
public boolean equals(Object anObject) {
        if (this == anObject) {
            return true;
        }
    	// 判断是否属于String类
        if (anObject instanceof String) {
            String anotherString = (String)anObject;
            int n = value.length; // 获取长度
            if (n == anotherString.value.length) {
                char v1[] = value;
                char v2[] = anotherString.value;
                int i = 0;
                // 一个一个判断
                while (n-- != 0) {
                    // 如果不相等，就直接false
                    if (v1[i] != v2[i])
                        return false;
                    i++;
                }
                return true;
            }
        }
        return false;
    }
```

### hashcode

```java
public int hashCode() {
    int h = hash;
    if (h == 0 && value.length > 0) {
        char val[] = value;

        for (int i = 0; i < value.length; i++) {
            h = 31 * h + val[i]; // 还是取决于val
        }
        hash = h; // 缓存hash
    }
    return h;
}
```

### length()

```java
public int length() {
	// 取value数组的长度
    return value.length;
}
```

### isEmpty()

```java
public boolean isEmpty() {
    // 长度是否为0
    return value.length == 0;
}
```

### toCharArray()

```java
public char[] toCharArray() {
    // Cannot use Arrays.copyOf because of class initialization order issues
    // 没有调用Arrays的方法，而是直接调用本地方法，防止类初始化顺序问题
    char result[] = new char[value.length];
    System.arraycopy(value, 0, result, 0, value.length);
    return result;
}
```

### trim()

```java
public String trim() {
    int len = value.length;
    int st = 0;
    char[] val = value;    /* avoid getfield opcode */
	// 去除前面的空格
    while ((st < len) && (val[st] <= ' ')) {
        st++;
    }
    // 去除后面的空格
    while ((st < len) && (val[len - 1] <= ' ')) {
        len--;
    }
    return ((st > 0) || (len < value.length)) ? substring(st, len) : this;
}
```

### replace()

```java
public String replace(char oldChar, char newChar) {
    // 先判断新字符和旧字符是否一样
    if (oldChar != newChar) {
        // 取该String的长度
        int len = value.length;
        // 初始化个指针
        int i = -1;
        // val和value指向同一个数组
        char[] val = value; /* avoid getfield opcode */
		// 先找到旧字符的索引
        while (++i < len) {
            if (val[i] == oldChar) {
                // 找到旧退出
                break;
            }
        }
        if (i < len) {
            // 先new一个缓存，长度len
            char buf[] = new char[len];
            // 遍历0，i 把不需要替换的，放进去
            for (int j = 0; j < i; j++) {
                buf[j] = val[j];
            }
            // 替换新字符的过程
            while (i < len) {
                // 先取出旧字符
                char c = val[i];
                // 判断是不是你传来的， 是就替换，不是，就不替换
                buf[i] = (c == oldChar) ? newChar : c;
                // 可能重复替换
                i++;
            }
            return new String(buf, true);
        }
    }
    return this;
}
```

### substring()

```java
public String substring(int beginIndex, int endIndex) {
    // 以下都是防止越界
    if (beginIndex < 0) {
        throw new StringIndexOutOfBoundsException(beginIndex);
    }
    if (endIndex > value.length) {
        throw new StringIndexOutOfBoundsException(endIndex);
    }
    int subLen = endIndex - beginIndex;
    if (subLen < 0) {
        throw new StringIndexOutOfBoundsException(subLen);
    }
    // new一个String， 这个重载方法，就不看了，底层还是调用了system.copyarray的本地方法
    return ((beginIndex == 0) && (endIndex == value.length)) ? this
        : new String(value, beginIndex, subLen);
}
```

