## 准备工作

- [mongodb官网](https://www.mongodb.com/)
- windows10
- mac
- ubuntu
- Centos7

## 安装

### 1. windows

- 进入官网滚动网页至Try MongoDB for free

- 选择Community Server社区版本，并选择Windows的安装版本

- 可以在C:\mongodb中手动创建两个空文件夹

- **C:\mongodb\data\db**

- **C:\mongodb\log**

- 并在**C:\mongodb\log**下面创建一个空的mongo.log

- 打开cmd

- ```shell
  cd c:\mongodb\bin
  ```

- ```shell
  mongod --dbpath C:\mongodb\data\db --logpath=C:\mongodb\log\mongodb.log --logappend
  ```

- 使用cmd命令窗口，并进入至c:\mongodb\bin目录，运行命令

- `mongo`

- **将mongodb作为windows服务启动**

- ```shell
  mongod --dbpath C:\mongodb\data\db --logpath=C:\mongodb\log\mongodb.log --logappend --install --serviceName "MongoDB"
  ```

- **使用配置文件启动mongodb服务**：在c:\mongodb\config创建一个文件mongodb.conf，加入配置文件与直接运行命令的效果是一样的

  ```shell
  dbpath=C:\mongodb\data\db            # 数据库文件
  logpath=C:\mongodb\log\mongodb.log    # 日志文件
  logappend=true                        # 日志采用追加模式，配置后mongodb日志会追加到现有的日志文件，不会重新创建一个新文件
  journal=true                        # 启用日志文件，默认启用
  quiet=true                            # 这个选项可以过滤掉一些无用的日志信息，若需要调试使用请设置为 false
  port=27017                            # 端口号 默认为 27017
  ```

  ```shell
  sc create MongoDB binPath= "C:\mongodb\bin\mongod.exe --service --config=C:\mongodb\config\mongodb.conf"
  ```

- ```shell
  #可以在任何目录运行该命令
  sc delete MongoDB
  ```

- **将mongodb加入至环境变量**
- 将mongodb的bin目录加入至path环境变量中

### 2. mac

- 打开终端

- ```shell
  brew install mongodb
  ```

- 配置

- 前往前往文件夹/usr/local/Cellar/mongodb/x.x.x

- 创建data和log文件夹

- 在log文件下创建mongodb.log文件

- 随意某处创建一个配置文件（mongodb.conf）并打开

- ```shell
  port=27017
  dbpath=/usr/local/Cellar/mongodb/3.4.4/data/
  logpath=/usr/local/Cellar/mongodb/3.4.4/log/mongodb.log
  fork =true
  
  #port: 数据库服务使用端口
  
  #dbpath: 数据存放的文件位置
  
  #logpath: 日志文件的存放位置
  
  #fork: 后台守护进程运行
  ```

- 启动数据库

- ```shell
  mongod -f mongodb.conf
  ```

### 3. centos7

- `vim /etc/yum.repos.d/mongodb-org-3.4.repo`

  ```shell
  [mongodb-org-3.4]  
  name=MongoDB Repository  
  baseurl=https://repo.mongodb.org/yum/redhat/$releasever/mongodb-org/3.4/x86_64/  
  gpgcheck=1  
  enabled=1  
  gpgkey=https://www.mongodb.org/static/pgp/server-3.4.asc
  ```

- `yum -y install mongodb-org`

- 配置文件：`vim /etc/mongod.conf`

- 命令：

  ```shell
  启动mongodb ：systemctl start mongod.service
  停止mongodb ：systemctl stop mongod.service
  查到mongodb的状态：systemctl status mongod.service
  ```

- 关闭firewall：

  ```shell
  systemctl stop firewalld.service #停止firewall
  systemctl disable firewalld.service #禁止firewall开机启动
  ```

- 设置开机启动`systemctl enable mongod.service`
- 启动`mongo `
- 查看数据库`show dbs`
- **设置mongodb远程访问**
- `vim /etc/mongod.conf` 注释 bindIp 重启mongodb
- `systemctl restart mongod.service`

### 4. ubuntu16.04

- 第一步

- ```shell
  #setp 1. Import the public key used by the package management system.
  sudo apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 0C49F3730359A14518585931BC711F9BA15703C6
  ```

- 第二步

  ```shell
  #step 2. Create a list file for MongoDB
  echo "deb [ arch=amd64,arm64 ] http://repo.mongodb.org/apt/ubuntu xenial/mongodb-org/3.4 multiverse" | sudo tee /etc/apt/sources.list.d/mongodb-org-3.4.list
  ```

- 第三步

  ```shell
  #step 3. Reload local package database
  sudo apt-get update
  ```

- 第四步

  ```shell
  #step 4. Install the latest stable version of MongoDB
  sudo apt-get install -y mongodb-org
  ```

- 启动服务命令

  ```shell
  sudo service mongod stop　　#停止服务
  sudo service mongod start　　#启动服务
  sudo service mongod restart #重新启动服务
  sudo service mongod status #查看状态
  ```

- 开机启动`sudo systemctl enable mongod`
- 默认位置`less /etc/mongod.conf`



## 工具

[robo3T](<https://robomongo.org/>)


