## 1. 准备工作

- windows MySQL 绿色版
- mac
- linux（centos、ubuntu）

## 2. 安装

### 2.1 win绿色版

- [下载官网](<https://dev.mysql.com/downloads/mysql/>)

- 选择对应的版本（**Windows (x86, 64-bit), ZIP Archive**）

- 解压存放`D:\db\mysql-5.7.19-winx64`

- 此电脑-->右键-->属性-->高级系统设置-->环境变量-->系统变量-->Path-->编辑-->新建-->填入“D:\db\mysql-5.7.19-winx64\bin”-->确认

- 配置mysql.ini

  ```ini
  [mysql]
  # 设置mysql客户端默认字符集
  default-character-set=utf8
  [mysqld]
  #设置3306端口
  port = 3306
  # 设置mysql的安装目录
  basedir=D:/db/mysql-5.7.19-winx64
  # 设置mysql数据库的数据的存放目录
  datadir=D:/db/mysqldata
  # 允许最大连接数
  max_connections=200
  # 服务端使用的字符集默认为8比特编码的latin1字符集
  character-set-server=utf8
  # 创建新表时将使用的默认存储引擎
  default-storage-engine=INNODB
  # group by 的一些问题
  sql_mode=STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION
  ```

  

- 打开cmd（管理员）进入bin目录

- 安装mysql服务`mysqld install`

- 初始化`mysqld --initialize`

- 开启服务`net start mysql`

- 查找初始密码`datadir=C:/db/mysqldata`

- 找到.err文件打开找到root@localhost后面的密码

- 登陆`mysql -uroot -p`

- 输入密码

- 更改密码`ALTER USER "root"@"localhost" IDENTIFIED  BY "123456"; `

- 使用Navicat for mysql可能出现的错误：

  - 终端进入mysql，输入`use mysql;`
  - `alter user 'root'@'localhost' identfied with mysql_native_password by '123456'`
  - 刷新`flush privileges`

### 2.2 mac

- 选择安装的方式利用brew `brew install mysql`
- 登陆不需要密码
- 修改密码`ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'xxx';`

### 2.3 linux(centos7)

- `yum list | grep mysql`（查看版本）

- `yum install -y mysql-server mysql mysql-deve` 安装工具

- `service mysqld start` 初始化

- `mysqladmin -u root password 'root'` 设置密码

- 设置远程连接

  ```shell
  mysql -u root -p
  
  show databases;
  
  use mysql;
  
  grant all privileges on *.* to 'root'@'%' identified by 'root'; # 赋予权限和密码
   
  flush privileges; # 刷新
  ```

- centos7 以上命令

  ```shell
  systemctl start mariadb.service # 启动
  systemctl enable mariadb.service # 开启服务
  mysql_secure_installation
  ```



### 2.4 linux(ubuntu)

- `sudo apt-get install mysql-server`
- 下载过程中可能会出现密码
- `sudo apt-get install mysql-client`
- `sudo apt-get install libmysqlclient-dev`
- `mysql -uroot -p`登陆密码即可
- 远程访问`sudo vi /etc/mysql/mysql.conf.d/mysqld.cnf`
- 注释掉`bind-address = 127.0.0.1`
- 和以上centos设置远程连接相同
- 重启`service mysql restart`

### 2.5 deepin

- `sudo apt-get install mysql-server mysql-client`

- `sudo mysql -u root -p`
- enter即可进入
- `UPDATE mysql.user SET authentication_string = PASSWORD('123'), plugin = 'mysql_native_password' WHERE User = 'root' AND Host = 'localhost';`
- `FLUSH PRIVILEGES;`
- 重启`sudo service mysql restart`



## 3. 简单命令

—后续补充