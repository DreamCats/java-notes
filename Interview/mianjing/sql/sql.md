# leetcode

## 1、[组合两个表](https://leetcode-cn.com/problems/combine-two-tables/)

表1：Person
```html
+-------------+---------+
| 列名         | 类型     |
+-------------+---------+
| PersonId    | int     |
| FirstName   | varchar |
| LastName    | varchar |
+-------------+---------+
PersonId 是上表主键
```

表2：Address
```html
+-------------+---------+
| 列名         | 类型    |
+-------------+---------+
| AddressId   | int     |
| PersonId    | int     |
| City        | varchar |
| State       | varchar |
+-------------+---------+
AddressId 是上表主键
```
编写一个 SQL 查询，满足条件：无论 person 是否有地址信息，都需要基于上述两表提供 person 的以下信息：

```sql
select FirstName, LastName, City, State
from 
Person p left join Address a 
on p.PersonId = a.PersonId;
```


## 2、[第二个高薪水](https://leetcode-cn.com/problems/second-highest-salary/)

编写一个 SQL 查询，获取 Employee 表中第二高的薪水（Salary） 。
```html
+----+--------+
| Id | Salary |
+----+--------+
| 1  | 100    |
| 2  | 200    |
| 3  | 300    |
+----+--------+
```
例如上述 Employee 表，SQL查询应该返回 200 作为第二高的薪水。如果不存在第二高的薪水，那么查询应返回 null。

```html
+---------------------+
| SecondHighestSalary |
+---------------------+
| 200                 |
+---------------------+
```

```sql
select (select distinct(Salary) 
from Employee
order by Salary desc
limit 1,1) as SecondHighestSalary
```

## 3、[超过经理收入的员工](https://leetcode-cn.com/problems/employees-earning-more-than-their-managers/)
Employee 表包含所有员工，他们的经理也属于员工。每个员工都有一个 Id，此外还有一列对应员工的经理的 Id。

```html
+----+-------+--------+-----------+
| Id | Name  | Salary | ManagerId |
+----+-------+--------+-----------+
| 1  | Joe   | 70000  | 3         |
| 2  | Henry | 80000  | 4         |
| 3  | Sam   | 60000  | NULL      |
| 4  | Max   | 90000  | NULL      |
+----+-------+--------+-----------+
```

给定 Employee 表，编写一个 SQL 查询，该查询可以获取收入超过他们经理的员工的姓名。在上面的表格中，Joe 是唯一一个收入超过他的经理的员工。

```html
+----------+
| Employee |
+----------+
| Joe      |
+----------+
```

```sql
select Name Employee
from Employee a
where Salary > (select Salary from Employee where Id = a.ManagerId);
```