---
title: Centos7安装RocketMQ及配置
author: DreamCat
id: 1
date: 2020-02-21 20:33:02
tags: MQ
categories: Java
---
## 引言
> 项目中用到消息队列RocketMQ，因此在Centos7安装及配置。。。

<!-- more -->

## 下载

- [rocketmq](https://mirrors.tuna.tsinghua.edu.cn/apache/rocketmq/)
- 选择**rocketmq-all-4.6.1-bin-release.zip** 
- 因为RocketMQ依赖maven打包，因此需要安装maven
- 注意：下面所添加的环境统统是.zshrc

## Maven安装

- [maven地址](http://mirrors.hust.edu.cn/apache/maven/maven-3/)

- 在本文章中采用最新**3.6.3**

- 解压`tar -zxf apache-maven-3.6.3-bin.tar.gz`

- 修改仓库地址为阿里云，因为默认下载依赖总超时，找到conf中的setting.xml文件

- ```xml
  <mirror>
       <id>alimaven</id>
       <name>aliyun maven</name>
       <url>http://maven.aliyun.com/nexus/content/groups/public/</url>
       <mirrorOf>central</mirrorOf>     
  </mirror>
  ```

- 配置环境变量`vim .zshrc`

- ```shell
  export M2_HOME=/home/pch/Documents/mf/web/maven3
  export PATH=$PATH:$Java_HOME/bin:$M2_HOME/bin
  ```

- 刷新环境变量`source .zshrc`

## Rocketmq安装

- 解压`unzip rocketmq-all-4.6.1-bin-release.zip -d ./`
- 注意在bin目录中：`runserver.sh和runbroker.sh` **个人情况修改JAVA_OPT="${JAVA_OPT} -server一行参数**
- 将nameserver地址添加到环境变量中
- `export NAMESRV_ADDR=127.0.0.1:9876`
- **刷新配置文件`source .zshrh`**
- 创建logs文件夹，存放启动日志，方便查看
- 后台运行nameserver`nohup sh mqnamesrv > ../logs/nameser.log 2>&1&`
- 后台运行broker`nohup sh mqbroker > ../logs/broker.log 2>&1&`

## 控制台安装

**这个控制台属于springboot的项目...**

- 项目链接`git clone https://github.com/apache/rocketmq-externals`

- rocketmq-externals里面有所有Apache RocketMq外部项目，有的还在孵化中，我主要是使用rocketmq-console，进入到console项目中，修改resources文件夹下面的配置文件

- 在`rocketmq-externals/rocketmq-console/src/main/resources`目录下打开配置文件`vim application.properties`

- 修改以下配置

- ```properties
  server.port=8090
  rocketmq.config.namesrvAddr=127.0.0.1:9876
  rocketmq.config.dataPath=/home/pch/Documents/mf/web/rocket-data
  ```

- 开始maven打包`mvn clean install -Dmaven.test.skip=true`

- 完成之后在target找到`rocketmq-console-ng-1.0.1.jar`，后台运行它

- `nohup java -jar rocketmq-console-ng-1.0.1.jar > rocket-data/console.out 2>&1&`

- `localhost:8090`即可看到效果

- ![](http://media.dreamcat.ink/20200222002600.png)


