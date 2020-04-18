>  个人感觉，这部分源码的重要基础之一就是反射，不过这里就不贴源码，好好学习Java的反射吧。

 ## 大纲图

 ![MyBatis面试常见问题](http://media.dreamcat.ink/uPic/MyBatis面试常见问题.png)

 ### 什么是数据持久化？

 数据持久化是将**内存**中的**数据**模型转换为**存储**模型，以及将存储模型转换为内存中的数据模型的统称。例如，文件的存储、数据的读取等都是数据持久化操作。数据模型可以是任何**数据结构或对象的模型、XML、二进制流**等。
 当我们编写应用程序操作数据库，对表数据进行**增删改查**的操作的时候就是数据持久化的操作。

 ### Mybatis框架简介

 - **MyBatis框架是一个开源的数据持久层框架。**
 - **它的内部封装了通过JDBC访问数据库的操作，支持普通的SQL查询、存储过程和高级映射，几乎消除了所有的JDBC代码和参数的手工设置以及结果集的检索。**
 - **MyBatis作为持久层框架，其主要思想是将程序中的大量SQL语句剥离出来，配置在配置文件当中，实现SQL的灵活配置。**
 - **这样做的好处是将SQL与程序代码分离，可以在不修改代码的情况下，直接在配置文件当中修改SQL。**

 ### 什么是ORM？

 ORM（Object/Relational Mapping）即**对象关系映射**，是一种数据持久化技术。它在**对象模型和关系型数据库直接建立起对应关系**，并且提供一种机制，**通过JavaBean对象去操作数据库表的数据**。
 MyBatis通过简单的**XML**或者**注解**的方式进行配置和原始映射，将实体类和SQL语句之间建立映射关系，是一种**半自动**（之所以说是半自动，因为我们要自己写SQL）的ORM实现。

 ### MyBatis框架的优缺点及其适用的场合

 #### 优点

 1. 与JDBC相比，减少了50%以上的代码量。
 2. MyBatis是易学的持久层框架，小巧并且简单易学。
 3. MyBatis相当灵活，不会对应用程序或者数据库的现有设计强加任何影响，SQL写在XML文件里，从程序代码中彻底分离，降低耦合度，便于统一的管理和优化，并可重用。
 4. 提供XML标签，支持编写动态的SQL，满足不同的业务需求。
 5. 提供映射标签，支持对象与数据库的ORM字段关系映射。

 #### 缺点

 1. SQL语句的编写工作量较大，对开发人员编写SQL的能力有一定的要求。
 2. SQL语句依赖于数据库，导致数据库不具有好的移植性，不可以随便更换数据库。

 #### 适用场景

 MyBatis专注于SQL自身，是一个足够灵活的DAO层解决方案。对性能的要求很高，或者需求变化较多的项目，例如Web项目，那么MyBatis是不二的选择。

 ### MyBatis与Hibernate有哪些不同？

 1. Mybatis和hibernate不同，它不完全是一个ORM框架，因为MyBatis需要程序员自己编写Sql语句。
 2. Mybatis直接编写原生态sql，可以严格控制sql执行性能，灵活度高，非常适合对关系数据模型要求不高的软件开发，因为这类软件需求变化频繁，一但需求变化要求迅速输出成果。但是灵活的前提是mybatis无法做到数据库无关性，如果需要实现支持多种数据库的软件，则需要自定义多套sql映射文件，工作量大。
 3. Hibernate对象/关系映射能力强，数据库无关性好，对于关系模型要求高的软件，如果用hibernate开发可以节省很多代码，提高效率。

 ### #{}和${}的区别是什么？

 1. `#{}` 是预编译处理，`${}`是字符串替换。
 2. Mybatis在处理`#{}`时，会将sql中的`#{}`替换为?号，调用PreparedStatement的set方法来赋值；
 3. Mybatis在处理`${}`时，就是把`${}`替换成变量的值。
 4. 使用`#{}`可以有效的防止SQL注入，提高系统安全性。

 ### 当实体类中的属性名和表中的字段名不一样，怎么办？

 1. 第1种： 通过在查询的sql语句中定义字段名的别名，让字段名的别名和实体类的属性名一致。
 2. 第2种： 通过 `<resultMap` 来映射字段名和实体类属性名的一一对应的关系。

 ### 模糊查询like语句该怎么写？

 1. 第1种：在Java代码中添加sql通配符。
 2. 第2种：在sql语句中拼接通配符，会引起sql注入

 ### Dao接口的工作原理是什么？Dao接口里的方法，参数不同时，方法能重载吗？

 Dao接口即**Mapper接口**。接口的**全限名**，就是映射文件中的**namespace的值**；接口的**方法名**，就是映射文件中**Mapper的Statement的id值**；接口方法内的参数，就是传递给sql的参数。
 Mapper接口是没有实现类的，当调用接口方法时，**接口全限名+方法名拼接字符串作为key值**，**可唯一定位一个MapperStatement**。在Mybatis中每`<select、<insert、<update、<delete `标签，都会被解析为一个MapperStatement对象。
 Mapper接口里的方法，是**不能重载**的，因为是使用 全限名+方法名 的保存和寻找策略。**Mapper 接口的工作原理是JDK动态代理**，Mybatis运行时会使用JDK动态代理为Mapper接口生成代理对象proxy，代理对象会拦截接口方法，转而执行MapperStatement所代表的sql，然后将sql执行结果返回。

 ### Mybatis是如何进行分页的？分页插件的原理是什么？

 Mybatis使用**RowBounds**对象进行分页，它是针对ResultSet结果集执行的内存分页，而非物理分页。可以在sql内直接书写带有物理分页的参数来完成物理分页功能，也可以使用分页插件来完成物理分页。
 分页插件的基本原理是使用**Mybatis提供的插件接口**，实现自定义插件，在插件的**拦截方法内拦截待执行的sql**，然后重写sql，根据**dialect**方言，添加对应的**物理分页语句和物理分页**参数。

 ### Mybatis是如何将sql执行结果封装为目标对象并返回的？都有哪些映射形式？

 1. 第一种是使用` <resultMap `标签，逐一定义数据库列名和对象属性名之间的映射关系。
 2. 第二种是使用sql列的别名功能，将列的别名书写为对象属性名。

 有了列名与属性名的映射关系后，Mybatis通过**反射创建对象**，同时使用**反射给对象的属性逐一赋值并返回**，那些找不到映射关系的属性，是无法完成赋值的。

 ### Mybatis动态sql有什么用？执行原理？有哪些动态sql？

 Mybatis动态sql可以在Xml映射文件内，以标签的形式编写动态sql，执行原理是**根据表达式的值完成逻辑判断并动态拼接sql**的功能。

 ### Mybatis的Xml映射文件中，不同的Xml映射文件，id是否可以重复？

 不同的Xml映射文件，如果配置了namespace，那么id可以重复；如果没有配置namespace，那么id不能重复；
 原因就是namespace+id是作为Map <String,MapperStatement 的key使用的，如果没有namespace，就剩下id，那么，id重复会导致数据互相覆盖。有了namespace，自然id就可以重复，namespace不同，namespace+id自然也就不同。

 ### 为什么说Mybatis是半自动ORM映射工具？它与全自动的区别在哪里？

 Hibernate属于**全自动ORM映射工具**，使用Hibernate查询关联对象或者关联集合对象时，可以根据对象关系模型直接获取，所以它是全自动的。而Mybatis在查询关联对象或关联集合对象时，需要**手动编写sql来完成**，所以，称之为**半自动ORM映射工具**。

 ### MyBatis实现一对一有几种方式?具体怎么操作的？

 有联合查询和嵌套查询,联合查询是几个表联合查询,只查询一次, 通过在resultMap里面配置association节点配置一对一的类就可以完成；
 嵌套查询是先查一个表，根据这个表里面的结果的 外键id，去再另外一个表里面查询数据,也是通过association配置，但另外一个表的查询通过select属性配置。

 ### MyBatis实现一对多有几种方式,怎么操作的？

 有联合查询和嵌套查询。联合查询是几个表联合查询,只查询一次,通过在resultMap里面的collection节点配置一对多的类就可以完成；嵌套查询是先查一个表,根据这个表里面的 结果的外键id,去再另外一个表里面查询数据,也是通过配置collection,但另外一个表的查询通过select节点配置。

 ### Mybatis是否支持延迟加载？如果支持，它的实现原理是什么？

 Mybatis仅支持association关联对象和collection关联集合对象的延迟加载，association指的就是一对一，collection指的就是一对多查询。在Mybatis配置文件中，可以配置是否启用延迟加载lazyLoadingEnabled=true|false。
 它的原理是，使用**CGLIB创建目标对象的代理对象**，当调用目标方法时，进入拦截器方法，比如调用a.getB().getName()，拦截器invoke()方法发现a.getB()是null值，那么就会**单独发送事先保存好的查询关联B对象的sql**，**把B查询上来**，**然后调用a.setB(b)**，于是a的对象b属性就有值了，接着完成a.getB().getName()方法的调用。

 ### Mybatis的一级、二级缓存

 - 一级缓存: 基于 PerpetualCache 的 HashMap 本地缓存，其存储作用域为 Session，当 Session flush 或 close 之后，该 Session 中的所有 Cache 就将清空，默认打开一级缓存。
 - 二级缓存与一级缓存其机制相同，默认也是采用 PerpetualCache，HashMap 存储，不同在于其存储作用域为 Mapper(Namespace)，并且可自定义存储源，如 Ehcache。默认不打开二级缓存，要开启二级缓存，使用二级缓存属性类需要实现Serializable序列化接口(可用来保存对象的状态),可在它的映射文件中配置 <cache/ ；
 - 对于缓存数据更新机制，当某一个作用域(一级缓存 Session/二级缓存Namespaces)的进行了C/U/D 操作后，默认该作用域下所有 select 中的缓存将被 clear。

 ### 什么是MyBatis的接口绑定？有哪些实现方式？

 接口绑定，就是在MyBatis中任意定义接口,然后把接口里面的方法和SQL语句绑定, 我们直接调用接口方法就可以,这样比起原来了SqlSession提供的方法我们可以有更加灵活的选择和设置。
 接口绑定有两种实现方式：

 - 注解绑定，就是在接口的方法上面加上 @Select、@Update等注解，里面包含Sql语句来绑定；
 - 外一种就是通过xml里面写SQL来绑定, 在这种情况下,要指定xml映射文件里面的namespace必须为接口的全路径名。当Sql语句比较简单时候,用注解绑定, 当SQL语句比较复杂时候,用xml绑定,一般用xml绑定的比较多。

 ### 使用MyBatis的mapper接口调用时有哪些要求？
 - Mapper接口方法名和mapper.xml中定义的每个sql的id相同；
 - Mapper接口方法的输入参数类型和mapper.xml中定义的每个sql 的parameterType的类型相同；
 - Mapper接口方法的输出参数类型和mapper.xml中定义的每个sql的resultType的类型相同；
 - Mapper.xml文件中的namespace即是mapper接口的类路径。

 ### mybatis是如何防止SQL注入的？

 **首先看一下下面两个sql语句的区别：**

 ```xml
 <select id="selectByNameAndPassword" parameterType="java.util.Map" resultMap="BaseResultMap"
 select id, username, password, role
 from user
 where username = #{username,jdbcType=VARCHAR}
 and password = #{password,jdbcType=VARCHAR}
 </select
 ```

 ```xml
 <select id="selectByNameAndPassword" parameterType="java.util.Map" resultMap="BaseResultMap"
 select id, username, password, role
 from user
 where username = ${username,jdbcType=VARCHAR}
 and password = ${password,jdbcType=VARCHAR}
 </select
 ```

 **mybatis中的#和$的区别：**

 - `#`将传入的数据都当成一个字符串，会对自动传入的数据加一个双引号。
   如：`where username=#{username}`，如果传入的值是111,那么解析成sql时的值为`where username="111"`, 如果传入的值是id，则解析成的sql为`where username="id"`.　
 - `$`将传入的数据直接显示生成在sql中。如：`where username=${username}`，如果传入的值是111,那么解析成sql时的值为`where username=111`；如果传入的值是：`drop table user;`，则解析成的sql为：`select id, username, password, role from user where username=;drop table user`;
 - `#`方式能够很大程度防止sql注入，`$`方式无法防止Sql注入。
 - `$`方式一般用于传入数据库对象，例如传入表名.
 - 一般能用`#`的就别用`$`，若不得不使用`“${xxx}”`这样的参数，要手工地做好过滤工作，来防止sql注入攻击。
 - 在MyBatis中，`“${xxx}”`这样格式的参数会直接参与SQL编译，从而不能避免注入攻击。但涉及到动态表名和列名时，只能使用`“${xxx}”`这样的参数格式。所以，这样的参数需要我们在代码中手工进行处理来防止注入。

 **sql注入**：

 **SQL注入**，大家都不陌生，是一种常见的攻击方式。**攻击者**在界面的表单信息或URL上输入一些奇怪的SQL片段（例如“or ‘1’=’1’”这样的语句），有可能入侵**参数检验不足**的应用程序。所以，在我们的应用中需要做一些工作，来防备这样的攻击方式。在一些安全性要求很高的应用中（比如银行软件），经常使用将**SQL语句**全部替换为**存储过程**这样的方式，来防止SQL注入。这当然是**一种很安全的方式**，但我们平时开发中，可能不需要这种死板的方式。

 **mybatis是如何做到防止sql注入的**

 MyBatis框架作为一款半自动化的持久层框架，其SQL语句都要我们自己手动编写，这个时候当然需要防止SQL注入。其实，MyBatis的SQL是一个具有“**输入+输出**”的功能，类似于函数的结构，参考上面的两个例子。其中，parameterType表示了输入的参数类型，resultType表示了输出的参数类型。回应上文，如果我们想防止SQL注入，理所当然地要在输入参数上下功夫。上面代码中使用#的即输入参数在SQL中拼接的部分，传入参数后，打印出执行的SQL语句，会看到SQL是这样的：

 ```sql
 select id, username, password, role from user where username=? and password=?
 ```

 不管输入什么参数，打印出的SQL都是这样的。这是因为MyBatis启用了预编译功能，在SQL执行前，会先将上面的SQL发送给数据库进行编译；执行时，直接使用编译好的SQL，替换占位符“?”就可以了。因为SQL注入只能对编译过程起作用，所以这样的方式就很好地避免了SQL注入的问题。

 [底层实现原理]MyBatis是如何做到SQL预编译的呢？其实在框架底层，是JDBC中的PreparedStatement类在起作用，PreparedStatement是我们很熟悉的Statement的子类，它的对象包含了编译好的SQL语句。这种“准备好”的方式不仅能提高安全性，而且在多次执行同一个SQL时，能够提高效率。原因是SQL已编译好，再次执行时无需再编译。

 ```java
 //安全的，预编译了的
 Connection conn = getConn();//获得连接
 String sql = "select id, username, password, role from user where id=?"; //执行sql前会预编译号该条语句
 PreparedStatement pstmt = conn.prepareStatement(sql); 
 pstmt.setString(1, id); 
 ResultSet rs=pstmt.executeUpdate(); 
 ......
 ```

 ```java
 //不安全的，没进行预编译
 private String getNameByUserId(String userId) {
     Connection conn = getConn();//获得连接
     String sql = "select id,username,password,role from user where id=" + id;
     //当id参数为"3;drop table user;"时，执行的sql语句如下:
     //select id,username,password,role from user where id=3; drop table user;  
     PreparedStatement pstmt =  conn.prepareStatement(sql);
     ResultSet rs=pstmt.executeUpdate();
     ......
 }
 ```

 **结论**：

 **\#{}**：相当于JDBC中的PreparedStatement

 **${}**：是输出变量的值

 简单说，#{}是经过预编译的，是安全的；${}是未经过预编译的，仅仅是取变量的值，是非安全的，存在SQL注入。

>  创作不易哇，觉得有帮助的话，给个小小的star呗。[github地址](https://github.com/DreamCats/JavaBooks)😁😁😁