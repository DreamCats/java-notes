
### 创建数据库并制定编码

- ```sql
  Create database 数据库名 default character set utf8;
  Create database ssm default character set utf8;
  ```

### 删除数据库

- ```sql
  drop database 数据名;
  drop database ssm;
  ```

 ### 创建表

- 一般情况：

- ```sql
  Create table 表名 (
  	列名 类型 约束 auto_increment comment '备注';
  );
  
  create tablie flower (
  id int(10) primary key auto_increment comment '编号',
  name varchar(30) not null comment '花名',
  price float not null comment '价格',
  production varchar(30) not null comment '原产地'
  );
  
  create tablie flower (
  `id` int(10) not null auto_increment comment '编号',
  `name` varchar(30) not null comment '花名',
  `price` float not null comment '价格',
  `production` varchar(30) not null comment '原产地',
   primary key (`id`)
  );
  ```

### 插入信息

- ```sql
  insert into 表明(姓名，性别，年龄) values('李一', '女', '18');
  insert into `flower` (`id`, `name`, `price`, `production`) values (defalut, '矮牵牛', 2.5, '南美阿根廷')
  ```

### 查询信息

- ```sql
  select * from 表名;
  select * from flower;
  select * from 表名 where ID=5
  select * from flower where  ID=1
  ```

### 更新信息

- ```sql
  update 表名 set name='李一' where name='王五';
  update flower set price=1.2 where id=1;
  ```

### 删除信息

- ```sql
  delete from 表名 where id=1;
  delete from flower where id=1;
  ```

### 删除表

- ```sql
  drop table 表名;
  drop table flower;
  ```

### 查看表信息

- ```sql
  desc 表名;
  desc flower;
  ```

### 一对多关系

- 可能是写的不熟练吧，所以要做个笔记，以免之后再次忘掉。

- 例子：假如有这样的一对多关系，首先有一个User模型，其中该模型有id、name和gender字段。还有一个Artical模型，其中该模型有id、title、content和user_id字段，而user_id则是外键。如下图：

- ![](https://raw.githubusercontent.com/DreamCats/PicBed/master/20191121170733.png)

- 那么在mysql或者mysql的一些工具下创建输入以下代码：

- ```sql
  create database demo default character set utf8; -- 创建数据库
  
  use demo;
  
  -- 创建user表格
  create table user (
  	`id` int(10) not null auto_increment comment '编号',
  	`name` varchar(16) not null comment '姓名',
  	`gender` varchar(4) not null comment '性别',
  	primary key (`id`)
  );
  
  -- 创建artical
  create table artical (
  	`id` int(10) not null auto_increment comment '文章编号',
  	`title` varchar(32) not null comment '标题',
  	`content` text default null comment '内容',
  	`user_id` int(10) not null comment '用户编号',
  	primary key (`id`),
  	key (`user_id`),
  	constraint `artical_idfk_1` foreign key (`user_id`) references `user` (`id`) -- 这句话非常重要 constraint ··· 约束
  );	
  
  -- 在user中插入数据
  insert into user (`id`,`name`,`gender`) values(default, 'Maifeng', '男');
  insert into user (`id`,`name`,`gender`) values(default, 'Liumeng', '女');
  
  -- 在artical中插入数据
  insert into `artical` (`id`, `title`, `content`, `user_id`) values (default, '我是mm', 'null', 1);
  insert into `artical` (`id`, `title`, `content`, `user_id`) values (default, '我是xxx', 'null', 2);
  ```

- 如下图user和artical表

- ![](https://raw.githubusercontent.com/DreamCats/PicBed/master/20191121170817.png)

- ![](https://raw.githubusercontent.com/DreamCats/PicBed/master/20191121170856.png)

  

### 更改auto_increment

- ```sql
  alter table 表名 auto_increment=5;
  alter table flower auto_increment=5;
  ```

- 

### 常用sql语句

#### 数据库

```mysql
# 查看所有数据库
show databases;

# 创建一个数据库
create database demo;

# 删除一个数据库
drop dababase demo;

# 使用这个数据库
use demo;

```

#### 表

```mysql
# 查看所有的表
show tables;

# 创建一个表
create table n(`id` int, `name` varchar(10));
create table m(`id` int, `name` varchar(10), primary key(`id`), foreign key(`id`) references n(`id`), unique);
create table m(`id` int, `name` varchar(10));

# 直接将查询结果导入或复制到新创建的表
create table n select * from m;

# 将创建的表与一个存在的表的数据结构类似
create table m like n;

# 创建一个临时表
# 临时表将在你连接MySQL期间存在。当断开连接时，MySQL将自动删除表并释放所用的空间。也可手动删除。
create temporary table l(`id` int, name varchar(10));
# 直接将查询结果导入或复制到新的临时表
create temporary table tt select * from n;

# 删除一个已经存在的表
drop table if exists m;

# 更改存在表的名称
alter table n rename m;
rename table n to m;

# 查看表的结构(5种,效果相同)
desc n;
describe n;
show columns in n;
show columns from n;
explain n;
# 查看表的创建语句
show create table n;
```

#### 表的结构

```mysql
# 添加字段
alter table n add age varchar(2);
# 删除字段
alter table n drop age;
# 更改字段属性和属性
alter table n change age a int;
# 只更改字段属性
alter table n modify age varchar(7);
```

#### 表的数据

```mysql
# 增加数据
insert into n values(1, 'tom', '23'), (2, 'john', '22');
insert into n select * from n; # 把数据复制一遍重新插入
# 删除数据
delete from n where id = 2;
# 更改数据
update n set name = 'tom' where id = 2;
# 数据查找
select * from n where name like '%h%';
# 数据排序(反序)
select * from n order by name, id desc;
```

#### 键

```mysql
# 添加主键
alter table n add primary key(id);
# 删除主键
alter table n drop primary key;
# 添加外键
alter table m add foreign key(id) references n(id); # 自动生成键名m_ibfk_1
# 自定义名称的外键
alter table m add constraint fk_id foreign key(id) references n(id);
# 删除外键
alter table m drop foreign key `fk_id`;
# 修改外键
alter table m drop foreign key `fk_id`, add constraint fk_id2 foreign key(id) references n(id);
# 添加唯一键
alter table n add unique (name);
alter table n add unique u_name (name);
alter table n add unique index u_name (name);
alter table n add constraint u_name unique (name);
create unique index u_name on n(name);
# 添加索引
alter table n add index (age);
alter table n add index i_age (age);
create index i_age on n(age);

# 删除索引或唯一键
drop index u_name on n;
drop index i_age on n;
```

#### 视图

```mysql
# 创建视图
create view v as select id, name from n;
create view v(id, name) as select id, name from n;
# 查看视图(与表操作类似)
select * from v;
desc v;
# 查看视图语句
show create view v;
# 更改视图
CREATE OR REPLACE VIEW v AS SELECT name, age FROM n;
ALTER VIEW v AS SELECT name FROM n;
# 删除视图
drop view if exists v;
```

#### 联接

```mysql
# 内联接
select * from m inner join n on m.id = n.id;
# 左外连接
select * from m left join n on m.id = n.id;
# 右外连接
select * from m right join n on m.id = n.id;
# 交叉连接
select * from m  cross join n; # 标准写法
select * from m,n;
# 类似于全连接full join 的联接用法
select id, name from m
union
select id, name from n
```

#### 函数

```mysql
# 聚合函数
select count(id) as total from n; # 总数
select sum(age) as all_age from n; # 总和
select avg(age) as all_age from n; # 平均值
select max(age) as all_age from n; # 最大值
select min(age) as all_age from n; # 最小值
# 数学函数
select abs(-5); # 绝对值
select bin(15), oct(15), hex(15); # 二进制，八进制，十六进制
SELECT pi();   # 圆周率3.141593
SELECT ceil(5.5);   # 大于x的最小整数值6
SELECT floor(5.5);   # 小于x的最大整数值5
SELECT greatest(3,1,4,1,5,9,2,6);   # 返回集合中最大的值9
SELECT least(3,1,4,1,5,9,2,6);    # 返回集合中最小的值1
SELECT mod(5,3);    # 余数2
SELECT rand();    # 返回０到１内的随机值，每次不一样
SELECT rand(5);   # 提供一个参数(种子)使RAND()随机数生成器生成一个指定的值。
SELECT round(1415.1415);   # 四舍五入1415
SELECT round(1415.1415, 3);   # 四舍五入三位数1415.142
SELECT round(1415.1415, -1);    # 四舍五入整数位数1420
SELECT truncate(1415.1415, 3);    # 截短为3位小数1415.141
SELECT truncate(1415.1415, -1);   # 截短为-1位小数1410
SELECT sign(-5);    # 符号的值负数-1
SELECT sign(5);    # 符号的值正数1
SELECT sqrt(9);   # 平方根3
SELECT sqrt(9);   # 平方根3
# 字符串函数
SELECT concat('a', 'p', 'p', 'le');   # 连接字符串-apple
SELECT concat_ws(',', 'a', 'p', 'p', 'le');   # 连接用','分割字符串-a,p,p,le
SELECT insert('chinese', 3, 2, 'IN');    # 将字符串'chinese'从3位置开始的2个字符替换为'IN'-chINese
SELECT left('chinese', 4);   # 返回字符串'chinese'左边的4个字符-chin
SELECT right('chinese', 3);   # 返回字符串'chinese'右边的3个字符-ese

SELECT substring('chinese', 3);   # 返回字符串'chinese'第三个字符之后的子字符串-inese
SELECT substring('chinese', -3);   # 返回字符串'chinese'倒数第三个字符之后的子字符串-ese
SELECT substring('chinese', 3, 2);   # 返回字符串'chinese'第三个字符之后的两个字符-in
SELECT trim(' chinese ');    # 切割字符串' chinese '两边的空字符-'chinese'
SELECT ltrim(' chinese ');    # 切割字符串' chinese '两边的空字符-'chinese '
SELECT rtrim(' chinese ');    # 切割字符串' chinese '两边的空字符-' chinese'
SELECT repeat('boy', 3);    # 重复字符'boy'三次-'boyboyboy'
SELECT reverse('chinese');    # 反向排序-'esenihc'
SELECT length('chinese');   # 返回字符串的长度-7
SELECT upper('chINese'), lower('chINese');    # 大写小写 CHINESE    chinese
SELECT ucase('chINese'), lcase('chINese');    # 大写小写 CHINESE    chinese
SELECT position('i' IN 'chinese');    # 返回'i'在'chinese'的第一个位置-3
SELECT position('e' IN 'chinese');    # 返回'i'在'chinese'的第一个位置-5
SELECT strcmp('abc', 'abd');    # 比较字符串，第一个参数小于第二个返回负数- -1
SELECT strcmp('abc', 'abb');    # 比较字符串，第一个参数大于第二个返回正数- 1
# 时间函数
SELECT current_date, current_time, now();    # 2018-01-13   12:33:43    2018-01-13 12:33:43
SELECT hour(current_time), minute(current_time), second(current_time);    # 12  31   34
SELECT year(current_date), month(current_date), week(current_date);   # 2018    1   1
SELECT quarter(current_date);   # 1
SELECT monthname(current_date), dayname(current_date);   # January  Saturday
SELECT dayofweek(current_date), dayofmonth(current_date), dayofyear(current_date);    # 7   13  13
# 控制流函数
SELECT if(3>2, 't', 'f'), if(3<2, 't', 'f');    # t f
SELECT ifnull(NULL, 't'), ifnull(2, 't');    # t 2
SELECT isnull(1), isnull(1/0);    # 0 1 是null返回1，不是null返回0
SELECT nullif('a', 'a'), nullif('a', 'b');    # null a 参数相同或成立返回null，不同或不成立则返回第一个参数
SELECT CASE 2
       WHEN 1 THEN 'first'
       WHEN 2 THEN 'second'
       WHEN 3 THEN 'third'
       ELSE 'other'
       END ;     # second
# 系统信息函数
SELECT database();    # 当前数据库名-test
SELECT connection_id();   # 当前用户id-306
SELECT user();    # 当前用户-root@localhost
SELECT version();   # 当前mysql版本
SELECT found_rows();    # 返回上次查询的检索行数
```

#### 用户

```mysql
# 增加用户
CREATE USER 'test'@'localhost' IDENTIFIED BY 'test';
INSERT INTO mysql.user(Host, User, Password) VALUES ('localhost', 'test', Password('test'));    # 在用户表中插入用户信息，直接操作User表不推荐
# 删除用户
DROP USER 'test'@'localhost';
DELETE FROM mysql.user WHERE User='test' AND Host='localhost';
FLUSH PRIVILEGES ;
# 更改用户密码
SET PASSWORD FOR 'test'@'localhost' = PASSWORD('test');
UPDATE mysql.user SET Password=Password('t') WHERE User='test' AND Host='localhost';
FLUSH PRIVILEGES ;
# 用户授权
GRANT ALL PRIVILEGES ON *.* TO test@localhost IDENTIFIED BY 'test';
# 授予用'test'密码登陆成功的test@localhost用户操作所有数据库的所有表的所有的权限
FLUSH PRIVILEGES ;   # 刷新系统权限表,使授予权限生效
# 撤销用户授权
REVOKE DELETE ON *.* FROM 'test'@'localhost';   # 取消该用户的删除权限
```

#### 其他语句

```mysql
# 查看所有的表信息（包括视图）
SHOW TABLE STATUS;
```

### 小破站学习的mysql

- [美女老师](https://www.bilibili.com/video/av49181542?p=1)
- [sql表](https://blog.csdn.net/GongmissYan/article/details/102937816)

#### 先贴sql

```sql
CREATE TABLE `t_user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '账户',
  `password` varchar(50) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '密码',
  `gender` varchar(11) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `age` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
INSERT INTO `t_user` (`id`, `user_name`, `password`, `gender`, `age`)
VALUES
	(1, 'abc', '123', '0', 20),
	(2, 'a', '123', '0', 20),
	(3, 'b', '123', '1', 21),
	(4, 'c', '123', '0', 22),
	(5, 'dae', '123', '1', 23),
	(6, 'e', '123', '0', 24),
	(7, 'f', '123', '0', 25);

```

### 基础查询

```sql
/*
语法：
	select 查询列表 from 表明；
特点：
	1. 查询列表可以是：表中的字段、常量值、表达式、函数
	2. 查询的结构是一个虚拟的表格
*/
 /*
 别名
 ①便于理解
 ②如果要查询的字段有重名的情况，使用别名可以区分开来
 
 */
 # +号的作用
 /*
select 100+90; 两个操作数都为数值型，则做加法运算
select '123'+90;只要其中一方为字符型，试图将字符型数值转换成数值型
			如果转换成功，则继续做加法运算
select 'john'+90;	如果转换失败，则将字符型数值转换成0

select null+10; 只要其中一方为null，则结果肯定为null
 */	
# 题目

#1.	下面的语句是否可以执行成功  :能
 SELECT
	last_name ,
	job_id ,
	salary AS sal
FROM
	employees;
#2.下面的语句是否可以执行成功  ：能
 SELECT
	*
FROM
	employees;
#3.找出下面语句中的错误  ：有个中文标点符号
 SELECT
	employee_id ,
	last_name,
	salary * 12 AS "ANNUAL  SALARY"
FROM
	employees;
	
#4.显示表departments的结构，并查询其中的全部数据
DESC departments ;
SELECT * FROM departments;
#5.显示出表employees中的全部job_id（不能重复）
SELECT DISTINCT job_id FROM employees;

```

### 条件查询

```sql
# 2: 条件查询
/*

语法：
	select 
		查询列表
	from
		表名
	where
		筛选条件;

分类：
	一、按条件表达式筛选
	
	简单条件运算符：> < = != <> >= <=
	
	二、按逻辑表达式筛选
	逻辑运算符：
	作用：用于连接条件表达式
		&& || !
		and or not
		
	&&和and：两个条件都为true，结果为true，反之为false
	||或or： 只要有一个条件为true，结果为true，反之为false
	!或not： 如果连接的条件本身为false，结果为true，反之为false
	
	三、模糊查询
		like
		between and
		in
		is null
*/
# 1. 查询年龄大于22的用户信息
SELECT
	*
FROM
	`t_user`
WHERE
	`age` > 22;
	
# 2. 查询年龄不等于22的用户和密码
SELECT
	`user_name`,
	`password`
FROM
	`t_user`
WHERE
	`age` <> 20;

# 查询年龄在21到23之间的用户名和密码
SELECT
	`user_name`,
	`password`
FROM 
	`t_user`
WHERE
	`age` >=21 AND `age` <= 23;
SELECT
	`user_name`,
	`password`,
	`age`
FROM 
	`t_user`
WHERE
	`age` BETWEEN 21 AND 23;

# 查询年龄21和23的用户名和密码
SELECT
	`user_name`,
	`password`
FROM 
	`t_user`
WHERE
	`age` IN(21, 23);
	
# 模糊查询 like
/*
特点：
①一般和通配符搭配使用
	通配符：
	% 任意多个字符,包含0个字符
	_ 任意单个字符
*/
# 查询用户名包字符a的用户信息
SELECT 
	*
FROM 
	`t_user`
WHERE 
	`user_name` LIKE '%a%';

SELECT 
	*
FROM 
	`t_user`
WHERE 
	`user_name` LIKE '_a%';
	
```

### 排序查询

```sql
/*
排序
	select 查询列表
	from 表
	order by 排序列表 asc|desc. asc:升序，desc降序	
			可以放单个字段，也可以放多个字段，可以表达式，也可以放函数
			order by 放最后 除limit语句
*/	
题目：
#1.查询员工的姓名和部门号和年薪，按年薪降序 按姓名升序
SELECT 
	last_name,
	department_id,
	salary*12*(1+IFNULL(commission_pct,0)) 年薪 
FROM 
	employees
ORDER BY 年薪 DESC, last_name ASC;

#2.选择工资不在8000到17000的员工的姓名和工资，按工资降序
SELECT 
	last_name ,
	salary 
FROM employees
WHERE 
	salary NOT BETWEEN 8000 AND 17000
ORDER BY salary DESC ;
#3.查询邮箱中包含e的员工信息，并先按邮箱的字节数降序，再按部门号升序
SELECT 
	*,LENGTH(email)
FROM employees
WHERE email LIKE '%e%'
ORDER BY LENGTH(email) DESC,department_id ASC;


```

### 常见函数

```sql
/*

概念：类似于java的方法，将一组逻辑语句封装在方法体中，对外暴露方法名
好处：1、隐藏了实现细节  2、提高代码的重用性
调用：select 函数名(实参列表) 【from 表】;
特点：
	①叫什么（函数名）
	②干什么（函数功能）

分类：
	1、单行函数
	如 concat、length、ifnull等
	2、分组函数
	
	功能：做统计使用，又称为统计函数、聚合函数、组函数
	
常见函数：
	一、单行函数
	字符函数：
	length:获取字节个数(utf-8一个汉字代表3个字节,gbk为2个字节)
	concat
	substr
	instr
	trim
	upper
	lower
	lpad
	rpad
	replace
	
	数学函数：
	round
	ceil
	floor
	truncate
	mod
	
	日期函数：
	now
	curdate
	curtime
	year
	month
	monthname
	day
	hour
	minute
	second
	str_to_date
	date_format
	其他函数：
	version
	database
	user
	控制函数
	if
	case
*/
# 1. 字符函数
SELECT LENGTH('join'); 
SELECT LENGTH('张三丰hahaha');
SHOW VARIABLES LIKE '%char%';
# 2. 拼接字符串
SELECT CONCAT(`user_name`, '_', `password`) infos FROM t_user;
#3.upper、lower
SELECT UPPER('john');
SELECT LOWER('joHn');
#示例：将姓变大写，名变小写，然后拼接
SELECT CONCAT(UPPER(`user_name`), LOWER(`user_name`)) infos FROM t_user;
#4.substr、substring
#注意：索引从1开始
#截取从指定索引处后面所有字符
SELECT SUBSTR('我是dreamcat',7) out_put;
#截取从指定索引处指定字符长度的字符
SELECT SUBSTR('我是dreamcat',1,3) out_put;

SELECT 
CONCAT(UPPER(SUBSTR(`user_name`, 1, 1)), '_', LOWER(SUBSTR(`user_name`, 2))) out_put 
FROM t_user;
#5.instr 返回子串第一次出现的索引，如果找不到返回0
SELECT INSTR('我是dreamcat', 'dream') out_put;
#6.trim
SELECT 
LENGTH(
	TRIM(
		'   dreamcat.  '
	)
) out_put;
SELECT TRIM('aa' FROM 'aaaaaaaaa张aaaaaaaaaaaa翠山aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa')  AS out_put;
#7.lpad 用指定的字符实现左填充指定长度
SELECT LPAD('殷素素',5,'*') AS out_put;
#8.rpad 用指定的字符实现右填充指定长度
SELECT RPAD('殷素素',12,'ab') AS out_put;
#9.replace 替换
SELECT REPLACE('周芷若周芷若周芷若周芷若张无忌爱上了周芷若','周芷若','赵敏') AS out_put;


#二、数学函数
#round 四舍五入
SELECT ROUND(-1.55);
SELECT ROUND(1.567,2);
#ceil 向上取整,返回>=该参数的最小整数
SELECT CEIL(-1.02);
SELECT CEIL(1.02);
#floor 向下取整，返回<=该参数的最大整数
SELECT FLOOR(-9.99);
SELECT FLOOR(9.99);
#truncate 截断
SELECT TRUNCATE(1.69999,1);
#mod取余
SELECT MOD(10,-3);
SELECT 10%3;


#三、日期函数
#now 返回当前系统日期+时间
SELECT NOW();
#curdate 返回当前系统日期，不包含时间
SELECT CURDATE();
#curtime 返回当前时间，不包含日期
SELECT CURTIME();
#可以获取指定的部分，年、月、日、小时、分钟、秒
SELECT YEAR(NOW()) 年;
SELECT YEAR('1998-1-1') 年;
SELECT MONTH(NOW()) 月;
SELECT MONTHNAME(NOW()) 月;
#str_to_date 将字符通过指定的格式转换成日期
SELECT STR_TO_DATE('1998-3-2','%Y-%c-%d') AS out_put;
#date_format 将日期转换成字符
SELECT DATE_FORMAT(NOW(),'%y年%m月%d日') AS out_put;

#四、其他函数

SELECT VERSION();
SELECT DATABASE();
SELECT USER();

#五、流程控制函数
#1.if函数： if else 的效果

SELECT IF(10<5,'大','小');

SELECT 
	*,
IF(`age` < 24, 'young', 'old') 'new age'
FROM 
	t_user;

#2.case函数的使用一： switch case 的效果
/*
java中
switch(变量或表达式){
	case 常量1：语句1;break;
	...
	default:语句n;break;


}

mysql中

case 要判断的字段或表达式
when 常量1 then 要显示的值1或语句1;
when 常量2 then 要显示的值2或语句2;
...
else 要显示的值n或语句n;
end
*/

SELECT 
	*,
CASE `age`
WHEN 21 THEN 21+1
WHEN 22 THEN 22+2
WHEN 23 THEN 23+3
ELSE age
END 'NEW age'
FROM
	t_user;
题目：
#1.	显示系统时间(注：日期+时间)
SELECT NOW();

#2.	查询员工号，姓名，工资，以及工资提高百分之20%后的结果（new salary）
SELECT 
	employee_id ,
	last_name ,
	salary ,
	salary * 1.2 'new salary'
FROM  employees;

#3.	将员工的姓名按首字母排序，并写出姓名的长度（length）
SELECT 
	LENGTH (last_name) 长度, SUBSTR(last_name,1,1) 首字母, last_name
FROM employees
ORDER BY 首字母;

#4.	做一个查询，产生下面的结果
#<last_name> earns <salary> monthly but wants <salary*3>
#Dream Salary
#King earns 24000 monthly but wants 72000
SELECT 
	CONCAT(last_name, ' earns ', salary, ' monthly but wants ', salary * 3) AS 'Dream Salary'
FROM employees
WHERE salary = 24000;

```

### 分组函数

```sql
#二、分组函数
/*
功能：用作统计使用，又称为聚合函数或统计函数或组函数

分类：
sum 求和、avg 平均值、max 最大值 、min 最小值 、count 计算个数

特点：
1、sum、avg一般用于处理数值型
   max、min、count可以处理任何类型
2、以上分组函数都忽略null值

3、可以和distinct搭配实现去重的运算

4、count函数的单独介绍
一般使用count(*)用作统计行数

5、和分组函数一同查询的字段要求是group by后的字段

*/
#1、简单 的使用
SELECT SUM(age) FROM t_user; 
SELECT AVG(age) FROM t_user;
SELECT MIN(age) FROM t_user;
SELECT MAX(age) FROM t_user;
SELECT COUNT(age) FROM t_user;
SELECT SUM(user_name) FROM t_user;
SELECT AVG(user_name) FROM t_user;
SELECT COUNT(user_name) FROM t_user; 

SELECT SUM(DISTINCT password ) FROM t_user;
SELECT COUNT(DISTINCT password) FROM  t_user;

SELECT COUNT(*) FROM t_user;
SELECT COUNT(1) FROM t_user;
```

### 分组查询

```sql

#进阶5：分组查询
 /*
语法：

select 查询列表
from 表
【where 筛选条件】
group by 分组的字段
【order by 排序的字段】;

特点：
1、和分组函数一同查询的字段必须是group by后出现的字段
2、筛选分为两类：分组前筛选和分组后筛选
		针对的表			位置		连接的关键字
分组前筛选	原始表				group by前	where
	
分组后筛选	group by后的结果集    		group by后	having

问题1：分组函数做筛选能不能放在where后面
答：不能

问题2：where——group by——having

一般来讲，能用分组前筛选的，尽量使用分组前筛选，提高效率

3、分组可以按单个字段也可以按多个字段
4、可以搭配着排序使用
*/
SELECT
	COUNT(*)
FROM 
	t_user
WHERE 
	password = '123';
SELECT 
	AVG(age),
	password
FROM 
	t_user
GROUP BY password;

SELECT 
	AVG(age),
	gender 
FROM 
	t_user
GROUP BY gender ;
题目：
#1.查询各job_id的员工工资的最大值，最小值，平均值，总和，并按job_id升序
SELECT 
	MAX(salary),
	MIN(salary),
	AVG(salary),
	SUM(salary)
FROM employees
GROUP BY job_id 
ORDER BY job_id ;

#2.查询员工最高工资和最低工资的差距（DIFFERENCE）
SELECT 
	MAX(salary) - MIN(salary) AS  DIFFERENCE
FROM employees;
#3.查询各个管理者手下员工的最低工资，其中最低工资不能低于6000，没有管理者的员工不计算在内

SELECT 
	MIN(salary), manager_id 
FROM employees
WHERE manager_id IS NOT NULL
GROUP BY manager_id
HAVING MIN(salary) >= 6000;

#4.查询所有部门的编号，员工数量和工资平均值,并按平均工资降序


SELECT 
	department_id ,
	COUNT(*),
	AVG(salary)
FROM 
	employees
GROUP BY department_id 
ORDER BY AVG(salary) DESC;

#5.选择具有各个job_id的员工人数

SELECT 
	COUNT(*), 
	job_id 
FROM 
	employees
GROUP BY job_id;	
```

### 连接查询

```sql

#进阶6：连接查询
 /*
含义：又称多表查询，当查询的字段来自于多个表时，就会用到连接查询

笛卡尔乘积现象：表1 有m行，表2有n行，结果=m*n行

发生原因：没有有效的连接条件
如何避免：添加有效的连接条件

分类：

	按年代分类：
	sql92标准:仅仅支持内连接
	sql99标准【推荐】：支持内连接+外连接（左外和右外）+交叉连接
	
	按功能分类：
		内连接：
			等值连接
			非等值连接
			自连接
		外连接：
			左外连接
			右外连接
			全外连接
		
		交叉连接
*/
#1.显示所有员工的姓名，部门号和部门名称。
 SELECT
	e.last_name ,
	d.department_id ,
	d.department_name
FROM
	employees e ,
	departments d
WHERE
	e.department_id = d.department_id ;
#2.查询90号部门员工的job_id和90号部门的location_id
 SELECT
	e.job_id ,
	d.location_id
FROM
	employees e ,
	departments d
WHERE
	e.department_id = d.department_id
	AND e.department_id = 90;
#3.	选择所有有奖金的员工的 last_name , department_name , location_id , city
 SELECT
	last_name ,
	department_name ,
	l.location_id ,
	city
FROM
	employees e,
	departments d,
	locations l
WHERE
	e.department_id = d.department_id
	AND d.location_id = l.location_id
	AND e.commission_pct IS NOT NULL;
#4.选择city在Toronto工作的员工的 last_name , job_id , department_id , department_name
 SELECT
	e.last_name ,
	e.job_id ,
	e.department_id ,
	d.department_name
FROM
	employees e,
	departments d,
	locations l
WHERE
	e.department_id = d.department_id
	AND d.location_id = l.location_id
	AND l.city = 'Toronto';
#5.查询每个工种、每个部门的部门名、工种名和最低工资
 SELECT
	d.department_name ,
	j.job_title ,
	MIN(e.salary)
FROM
	employees e ,
	departments d ,
	jobs j
WHERE
	e.department_id = d.department_id
	AND e.job_id = j.job_id
GROUP BY
	d.department_name ,
	j.job_title ;
#6.查询每个国家下的部门个数大于2的国家编号
 SELECT
	l.country_id ,
	COUNT(*)
FROM
	locations l ,
	departments d
WHERE
	d.location_id = l.location_id
GROUP BY
	country_id
HAVING
	COUNT(*) > 2;

#7 、选择指定员工的姓名，员工号，以及他的管理者的姓名和员工号，结果类似于下面的格式
SELECT
	e.last_name employees,
	e.employee_id "Emp#",
	m.last_name manager,
	m.employee_id "Mgr#"
FROM
	employees e,
	employees m
WHERE
	e.manager_id = m.employee_id
	AND e.last_name = 'kochhar';

#二、sql99语法
/*
语法：
	select 查询列表
	from 表1 别名 【连接类型】
	join 表2 别名 
	on 连接条件
	【where 筛选条件】
	【group by 分组】
	【having 筛选条件】
	【order by 排序列表】
	

分类：
内连接（★）：inner
外连接
	左外(★):left 【outer】
	右外(★)：right 【outer】
	全外：full【outer】
交叉连接：cross 

*/
#一）内连接
/*
语法：

select 查询列表
from 表1 别名
inner join 表2 别名
on 连接条件;

分类：
等值
非等值
自连接

特点：
①添加排序、分组、筛选
②inner可以省略
③ 筛选条件放在where后面，连接条件放在on后面，提高分离性，便于阅读
④inner join连接和sql92语法中的等值连接效果是一样的，都是查询多表的交集
*/
#二、外连接
 
 /*
 应用场景：用于查询一个表中有，另一个表没有的记录
 
 特点：
 1、外连接的查询结果为主表中的所有记录
	如果从表中有和它匹配的，则显示匹配的值
	如果从表中没有和它匹配的，则显示null
	外连接查询结果=内连接结果+主表中有而从表没有的记录
 2、左外连接，left join左边的是主表
    右外连接，right join右边的是主表
 3、左外和右外交换两个表的顺序，可以实现同样的效果 
 4、全外连接=内连接的结果+表1中有但表2没有的+表2中有但表1没有的
 */
```

### 子查询

```sql
#进阶7：子查询
/*
含义：
出现在其他语句中的select语句，称为子查询或内查询
外部的查询语句，称为主查询或外查询

分类：
按子查询出现的位置：
	select后面：
		仅仅支持标量子查询
	
	from后面：
		支持表子查询
	where或having后面：★
		标量子查询（单行） √
		列子查询  （多行） √
		
		行子查询
		
	exists后面（相关子查询）
		表子查询
按结果集的行列数不同：
	标量子查询（结果集只有一行一列）
	列子查询（结果集只有一列多行）
	行子查询（结果集有一行多列）
	表子查询（结果集一般为多行多列）
*/
#一、where或having后面
/*
1、标量子查询（单行子查询）
2、列子查询（多行子查询）

3、行子查询（多列多行）

特点：
①子查询放在小括号内
②子查询一般放在条件的右侧
③标量子查询，一般搭配着单行操作符使用
> < >= <= = <>

列子查询，一般搭配着多行操作符使用
in、any/some、all

④子查询的执行优先于主查询执行，主查询的条件用到了子查询的结果

*/
```

### 分页查询

```sql
#进阶8：分页查询 ★
/*

应用场景：当要显示的数据，一页显示不全，需要分页提交sql请求
语法：
	select 查询列表
	from 表
	【join type join 表2
	on 连接条件
	where 筛选条件
	group by 分组字段
	having 分组后的筛选
	order by 排序的字段】
	limit 【offset,】size;
	
	offset要显示条目的起始索引（起始索引从0开始）
	size 要显示的条目个数
特点：
	①limit语句放在查询语句的最后
	②公式
	要显示的页数 page，每页的条目数size
	
	select 查询列表
	from 表
	limit (page-1)*size,size;
	
	size=10
	page  
	1	0
	2  	10
	3	20
*/
```


