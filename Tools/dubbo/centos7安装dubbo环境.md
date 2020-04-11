## 引言

> 最近看了看分布式架构，尝试了一下dubbo，注册中心是hiZookeeper，所以先在centos下配置环境吧。

<!-- more -->

## 前提

- 系统：Centos7

- 安装[Java](http://dreamcat.ink/2019/07/08/windows-mac-he-linux-an-zhuang-java/)环境

- [下载地址](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)

- 选择Linux 64位tar.gz

- 我一般将它放进`/usr/local/myapps`，myapps是存放自己的软件等。

- 解压`tar -xzvf xxx`，xxx指的刚才下载的jdk

- 添加java的环境变量，打开`vim /etc/profile`

- 在蓝色字体下面添加

- ```bash
  JAVA_HOME=/usr/local/myapps/jdk1.8.0_221 # 这是我的java存放的地址
  PATH=$JAVA_HOME/bin:$PATH
  export JAVA_HOME PATH
  ```

- 更新`source /etc/profile`

- 查看是否添加成功`javac `即可

## 安装Zookeeper

- [下载地址](https://mirrors.tuna.tsinghua.edu.cn/apache/zookeeper/)
- 选择3.4.14版本下载即可，放进和刚才的myapps下。
- 解压：`tar -zxf zookeeper-3.4.14.tar.gz`
- 将配置文件`cp -r zoo_sample.cfg zoo.cfg`
- 启动zookeeper`./zkServer.sh start`

## 安装Dubbo

Dubbo的源码[github](https://github.com/apache/dubbo/tree/2.5.x)

我提前编译好的[dubbo.war](https://www.lanzous.com/i7xn2di).  [dubbo-monitor-simple](https://www.lanzous.com/i7xn1ih)

-  下载2.5.x版本的zip
- 前提是系统装了maven环境，可以编译生成war。
- 解压分别进入dubbo-admin和Dubbo-simple->Dubbo-monitor-simple，执行`mvn package`
- 分别在target文件下找到了对应的一个war包和tar.gz包
- 解压 `tar -zxf dubbo-monitor-simple-2.0.0-assembly.tar.gz`
- 进入配置文件修改`vim dubbo.properties`
- 将注册中心#去掉，`dubbo.registry.address=zookeeper://127.0.0.1:2181`
- 将multicast注册中心加#
- 如果是低配置云服务器，记得修改`vim start.sh`的内存大小，512m，原来2g
- 然后启动dubbo`./start.sh start`
- 记得防火墙设置，可以加端口，也可以直接关掉防火墙
  - 加端口：如果在`/etc/sysconfig/iptables`，那默认是firewall防火墙，所以卸载
  - `systemctl stop firewalld `
  - `systemctl mask firewalld`
  - `yum install -y iptables`
  - `yum install iptables-services`
  - 开启服务`systemctl start iptables.service`
  - 设置防火墙启动`systemctl enable iptables.service`
  - 然后执行`vim /etc/sysconfig/iptables`模仿22端口添加即可
  - 记得添加2181，8080

## 安装tomcat

由于dubbo.war在tomcat容器中运行，所以先下载tomcat，其实tomcat不用安装，下载解压即可。

- [下载地址](http://tomcat.apache.org/)
- 选择左边栏中的**tomcat8**
- 选择core中的**tar.gz**下载
- 然后放在`myapps`路径下：`/usr/local/myapps`下
- 解压：`tar -zxf apache-tomcat-8.5.47`
- 修改端口`cd conf`
- `vim server.xml`将8080改为8088，因为不改的话会和dubbo冲突
- 将webapps下的ROOT目录下的文件全部删除`rm -rf ./*`即可
- 将dubbo.war存放在刚才的ROOT目录下
- 解压：`unzip dubbo.war -d apache-tomcat-8.5.47/webapps/ROOT/`
- 如果提示没有unzip的话，安装`yum install -y unzip zip`
- 去bin目录下启动`./startup.sh`
- 记得添加防火墙
- 访问对应的ip加端口：8088之后，guest账户和密码guest，root账户和密码是root