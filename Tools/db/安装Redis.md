## 准备工作

- [redis](https://github.com/microsoftarchive/redis/releases>)
- windows10
- mac
- ubuntu
- Centos7

## 安装

### 1. windows

- 进入官网下载对应的版本

- 启动服务`redis-server redis.windows.conf`

- 添加环境变量-压缩包路径哦-比如`C:\redis`

- 启动客户端`redis-cli`

- 接下来设置windows下的服务

- cd到redis根目录下

- 执行`redis-server --service-install redis.windows-service.conf --loglevel verbose`

- 可以在**计算机管理->服务**查看redis

- 常用windows下的服务命令

  ```shell
  卸载服务：redis-server --service-uninstall
  
  开启服务：redis-server --service-start
  
  停止服务：redis-server --service-stop
  ```

- 注意服务配置文件**redis.windows-service.conf**

### 2. mac

- brew大法好
- `brew install redis`
- 配置文件`/usr/local/etc/redis.conf`
- 利用brew命令启动服务`brew services start redis`



### 3.centos7

#### 3.1 安装redis

- `yum install redis`
- `yum install epel-release`
- `yum install redis`

#### 3.2 修改配置

- `vi /etc/redis.conf`
- 修改端口`port 6379`
- 修改ip`0.0.0.0`
- 修改密码`requirepass 123`
- 修改远程配置`protected-mode yes` 设为no



#### 3.2 相关命令

```shell
# 启动
systemctl start redis.service
# 关闭
systemctl stop redis.service
# 重启
systemctl restart redis.service
```



### 4. ubuntu

- `sudo apt-get install redis-server`
- 配置文件路径**/etc/redis/redis.conf**
- 配置说明和centos一样



## 工具

- [ Redis Desktop Manager](<https://redisdesktop.com/>)