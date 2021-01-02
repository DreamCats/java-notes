## 类

```java
// final 修饰
public final class Class<T> implements java.io.Serializable,
                              GenericDeclaration,
                              Type,
                              AnnotatedElement
```

## 方法

### registerNatives()

```java
// 本地方法，之间在object看到
private static native void registerNatives();
static {
    registerNatives();
}
```

### 构造方法

```java
private Class(ClassLoader loader) {
    // Initialize final field for classLoader.  The initialization value of non-null
    // prevents future JIT optimizations from assuming this final field is null.
    // 注入类加载器
    classLoader = loader;
}
```

### forName()

```java
// 说白了，返回类对象
className: 类的权限定名（包名+类名）
initialize： 是否初始化
ClassLoader：类加载器
caller：类装入器，没有就null
    
@CallerSensitive
public static Class<?> forName(String className)
    throws ClassNotFoundException {
    Class<?> caller = Reflection.getCallerClass();
    return forName0(className, true, ClassLoader.getClassLoader(caller), caller);
}

private static native Class<?> forName0(String name, boolean initialize,
                                        ClassLoader loader,
                                        Class<?> caller)
    throws ClassNotFoundException;
```

### newInstance()

```java
// 我见的比较少，一般步骤都是先拿class的构造器，接着new对象，如果私有，就暴力
// 其实这里面还是调用构造器
@CallerSensitive
public T newInstance()
    throws InstantiationException, IllegalAccessException
{
    if (System.getSecurityManager() != null) {
        checkMemberAccess(Member.PUBLIC, Reflection.getCallerClass(), false);
    }
	// 有意思啊，居然不是严格正确
    // NOTE: the following code may not be strictly correct under
    // the current Java memory model.// 当前的JMM

    // Constructor lookup // 构造器检查
    if (cachedConstructor == null) {
        if (this == Class.class) {
            throw new IllegalAccessException(
                "Can not call newInstance() on the Class for java.lang.Class"
            );
        }
        try {
            Class<?>[] empty = {};
            final Constructor<T> c = getConstructor0(empty, Member.DECLARED);
            // Disable accessibility checks on the constructor
            // since we have to do the security check here anyway
            // (the stack depth is wrong for the Constructor's
            // security check to work)
            java.security.AccessController.doPrivileged(
                new java.security.PrivilegedAction<Void>() {
                    public Void run() {
                        c.setAccessible(true); // 暴力解除
                        return null;
                    }
                });
            cachedConstructor = c;
        } catch (NoSuchMethodException e) {
            throw (InstantiationException)
                new InstantiationException(getName()).initCause(e);
        }
    }
    Constructor<T> tmpConstructor = cachedConstructor; // 拿到构造器
    // Security check (same as in java.lang.reflect.Constructor)
    int modifiers = tmpConstructor.getModifiers();
    if (!Reflection.quickCheckMemberAccess(this, modifiers)) {
        Class<?> caller = Reflection.getCallerClass();
        if (newInstanceCallerCache != caller) {
            Reflection.ensureMemberAccess(caller, this, null, modifiers);
            newInstanceCallerCache = caller;
        }
    }
    // Run constructor
    try {
        return tmpConstructor.newInstance((Object[])null); // new实例
    } catch (InvocationTargetException e) {
        Unsafe.getUnsafe().throwException(e.getTargetException());
        // Not reached
        return null;
    }
}
```

### getFields()

```java
@CallerSensitive
public Field[] getFields() throws SecurityException {
    // 检查， 然后获取
    checkMemberAccess(Member.PUBLIC, Reflection.getCallerClass(), true);
    return copyFields(privateGetPublicFields(null));
}
```

### getMethods()

```java
@CallerSensitive
public Method[] getMethods() throws SecurityException {
    // 和上面一样
    checkMemberAccess(Member.PUBLIC, Reflection.getCallerClass(), true);
    return copyMethods(privateGetPublicMethods());
}
```

### getConstructors()

```java
@CallerSensitive
public Constructor<?>[] getConstructors() throws SecurityException {
    checkMemberAccess(Member.PUBLIC, Reflection.getCallerClass(), true);
    return copyConstructors(privateGetDeclaredConstructors(true));
}
```

