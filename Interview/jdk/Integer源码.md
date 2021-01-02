## 类

```java
// 看了不少，至少很多工具类，并不希望你们去继承，因此加了final方法
public final class Integer extends Number implements Comparable<Integer>
```

## 变量

```java
have, -2<sup>31</sup>. // 官方注释哈，自然懂
@Native public static final int   MIN_VALUE = 0x80000000;
have, 2<sup>31</sup>-1.
@Native public static final int   MAX_VALUE = 0x7fffffff;
private final int value;
@Native public static final int SIZE = 32;

// 下面方法会用到该静态变量
final static char[] digits = {
    '0' , '1' , '2' , '3' , '4' , '5' ,
    '6' , '7' , '8' , '9' , 'a' , 'b' ,
    'c' , 'd' , 'e' , 'f' , 'g' , 'h' ,
    'i' , 'j' , 'k' , 'l' , 'm' , 'n' ,
    'o' , 'p' , 'q' , 'r' , 's' , 't' ,
    'u' , 'v' , 'w' , 'x' , 'y' , 'z'
};
```

## 方法

### parseInt(String s, int radix)

```java
// 你说说，lc的字符串转数字的题目，是不是？
public static int parseInt(String s, int radix)
    throws NumberFormatException
{
    /*
         * WARNING: This method may be invoked early during VM initialization
         * before IntegerCache is initialized. Care must be taken to not use
         * the valueOf method.
         */

    if (s == null) {
        throw new NumberFormatException("null");
    }

    if (radix < Character.MIN_RADIX) {
        throw new NumberFormatException("radix " + radix +
                                        " less than Character.MIN_RADIX");
    }

    if (radix > Character.MAX_RADIX) {
        throw new NumberFormatException("radix " + radix +
                                        " greater than Character.MAX_RADIX");
    }

    int result = 0; // 返回结果值
    boolean negative = false; // 很熟悉的味道
    int i = 0, len = s.length(); // 干嘛？双指针？
    int limit = -Integer.MAX_VALUE; // 限制一下哈
    int multmin; 
    int digit;

    if (len > 0) {
        char firstChar = s.charAt(0); // 判断第一个字符是否"+" or "-"
        if (firstChar < '0') { // Possible leading "+" or "-"
            if (firstChar == '-') {
                negative = true; // 负数标志
                limit = Integer.MIN_VALUE;
            } else if (firstChar != '+')
                throw NumberFormatException.forInputString(s);

            if (len == 1) // Cannot have lone "+" or "-"
                throw NumberFormatException.forInputString(s);
            i++;
        }
        multmin = limit / radix; // 这是算啥子？范围？
        while (i < len) {
            // Accumulating negatively avoids surprises near MAX_VALUE
            digit = Character.digit(s.charAt(i++),radix); // digit我还是贴一下吧
            if (digit < 0) {
                throw NumberFormatException.forInputString(s);
            }
            if (result < multmin) {
                throw NumberFormatException.forInputString(s);
            }
            result *= radix; // 关键在这
            if (result < limit + digit) {
                throw NumberFormatException.forInputString(s);
            }
            result -= digit; // 还有这 // 实际上这是负的，因为后面结果有个判断
            // 还有一种是是result = result * 10 + digit 也是可以， 根据正负判断标志
        }
    } else {
        throw NumberFormatException.forInputString(s);
    }
    return negative ? result : -result;
}
```

### valueOf(String s)

```java
public static Integer valueOf(String s) throws NumberFormatException {
    // 还是调用了一下parseInt
    // 不过该方法进行了缓存，缓存范围是-128-127
    return Integer.valueOf(parseInt(s, 10));
}

public static Integer valueOf(int i) {
    if (i >= IntegerCache.low && i <= IntegerCache.high)
        return IntegerCache.cache[i + (-IntegerCache.low)];
    return new Integer(i);
}
```

### hashCode()

```java
public int hashCode() {
    return Integer.hashCode(value);
}
// 直接就是value哇
public static int hashCode(int value) {
    return value;
}
```

### equals()

```java
public boolean equals(Object obj) {
    if (obj instanceof Integer) {
        // 比较的是基本类型的value
        return value == ((Integer)obj).intValue();
    }
    return false;
}
```

### bitCount(int i)

```java
// 返回二进制1的个数，我可写不出来这么神奇的代码
public static int bitCount(int i) {
    // HD, Figure 5-2
    i = i - ((i >>> 1) & 0x55555555);
    i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
    i = (i + (i >>> 4)) & 0x0f0f0f0f;
    i = i + (i >>> 8);
    i = i + (i >>> 16);
    return i & 0x3f;
}
```

### reverse(int i)

```java
// 就连翻转都写这么难懂的嘛？
public static int reverse(int i) {
    // HD, Figure 7-1
    i = (i & 0x55555555) << 1 | (i >>> 1) & 0x55555555;
    i = (i & 0x33333333) << 2 | (i >>> 2) & 0x33333333;
    i = (i & 0x0f0f0f0f) << 4 | (i >>> 4) & 0x0f0f0f0f;
    i = (i << 24) | ((i & 0xff00) << 8) |
        ((i >>> 8) & 0xff00) | (i >>> 24);
    return i;
}
```



注意：

其他几个包装类就不介绍了