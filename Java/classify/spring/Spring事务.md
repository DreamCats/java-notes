
## Spring事务
### 隔离级别
- **TransactionDefinition.ISOLATION_DEFAULT:** 使用后端数据库默认的隔离级别，Mysql 默认采用的 REPEATABLE_READ隔离级别 Oracle 默认采用的 READ_COMMITTED隔离级别.
- **TransactionDefinition.ISOLATION_READ_UNCOMMITTED:** 最低的隔离级别，允许读取尚未提交的数据变更，**可能会导致脏读、幻读或不可重复读**
- **TransactionDefinition.ISOLATION_READ_COMMITTED:** 允许读取并发事务已经提交的数据，**可以阻止脏读，但是幻读或不可重复读仍有可能发生**
- **TransactionDefinition.ISOLATION_REPEATABLE_READ:** 对同一字段的多次读取结果都是一致的，除非数据是被本身事务自己所修改，**可以阻止脏读和不可重复读，但幻读仍有可能发生。**
- **TransactionDefinition.ISOLATION_SERIALIZABLE:** 最高的隔离级别，完全服从ACID的隔离级别。所有的事务依次逐个执行，这样事务之间就完全不可能产生干扰，也就是说，**该级别可以防止脏读、不可重复读以及幻读**。但是这将严重影响程序的性能。通常情况下也不会用到该级别。

### @Transactional(rollbackFor = Exception.class)注解了解吗
1. @Transactional注解只能应用到public修饰符上，其它修饰符不起作用，但不报错。
2. 默认情况下此注解会对unchecked异常进行回滚，对checked异常不回滚。

> checked异常：表示无效，不是程序中可以预测的。比如无效的用户输入，文件不存在，网络或者数据库链接错误。这些都是外在的原因，都不是程序内部可以控制的。unchecked异常：表示错误，程序的逻辑错误。是RuntimeException的子类，比如IllegalArgumentException, NullPointerException和IllegalStateException。

不回滚解决方案：
1. 检查方法是不是public
2. 检查异常是不是unchecked异常
3. 如果是checked异常也想回滚的话，注解上写明异常类型即可@Transactional(rollbackFor=Exception.class)

事务失效的8大原因：
1. 数据库引擎不支持事务
2. 没有被 Spring 管理
3. 方法不是 public 的
4. 自身调用问题
5. 数据源没有配置事务管理器
6. 不支持事务（传播机制）
7. 异常被吃了（捕获异常）
8. 异常类型错误（checked异常失效）

### Spring的的事务传播机制
1. **required**（默认）：支持使用当前事务，如果当前事务不存在，创建一个新事务。
2. **requires_new**：创建一个新事务，如果当前事务存在，把当前事务挂起。
3. **supports**：支持使用当前事务，如果当前事务不存在，则不使用事务。
4. **not_supported**：无事务执行，如果当前事务存在，把当前事务挂起。
5. **mandatory**：强制，支持使用当前事务，如果当前事务不存在，则抛出Exception。
6. **never**：无事务执行，如果当前有事务则抛出Exception。
7. **nested**：嵌套事务，如果当前事务存在，那么在嵌套的事务中执行。如果当前事务不存在，则表现跟REQUIRED一样。

### 事务源码
- 开启@EnableTransactionManagement
- 利用TransactionManagementConfigurationSelector给容器中会导入组件
    - AutoProxyRegistrar
        - 给容器中注册一个 InfrastructureAdvisorAutoProxyCreator 组件
        - 利用后置处理器机制在对象创建以后，包装对象，返回一个代理对象（增强器），代理对象执行方法利用拦截器链进行调用；
    - ProxyTransactionManagementConfiguration（给容器中注册事务增强器）
        - 事务增强器要用事务注解的信息，AnnotationTransactionAttributeSource解析事务注解
        - 事务拦截器