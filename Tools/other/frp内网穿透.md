> frp内网穿透，非常详细的步骤说明。

## 1. 准备工作

- vps（云服务器一台）
- 访问目标设备（就是你最终要访问的设备）
- 简单的Linux基础（会用cp等几个简单命令即可）



## 2. 下载frp

- [frp-github](<https://github.com/fatedier/frp>)
- 选择release中对应的版本
- 比如linux：[frp_0.27.0_linux_amd64.tar.gz](https://github.com/fatedier/frp/releases/download/v0.27.0/frp_0.27.0_linux_amd64.tar.gz)



## 3. 配置frp

### 1. 简单介绍

- frps（服务端启动）
- frps.ini（服务器启动配置文件）
- frpc（客户端启动）
- frpc.ini（客户端启动配置文件）
- 配置前先备份哦`cp`

### 2. 服务端

1. `vim frps.ini`

2. 有以下内容

   ```shell
   [common]
   bind_port = 7000
   dashboard_port = 7500
   token = 12345678
   dashboard_user = admin
   dashboard_pwd = admin
   ```

   - “bind_port”表示用于客户端和服务端连接的端口，这个端口号我们之后在配置客户端的时候要用到。
   - “dashboard_port”是服务端仪表板的端口，若使用7500端口，在配置完成服务启动后可以通过浏览器访问 x.x.x.x:7500 （其中x.x.x.x为VPS的IP）查看frp服务运行信息。
   - “token”是用于客户端和服务端连接的口令，请自行设置并记录，稍后会用到。
   - “dashboard_user”和“dashboard_pwd”表示打开仪表板页面登录的用户名和密码，自行设置即可。

3. `./frps -c frps.ini`

4. 若出现以下内容

   ```shell
   2019/01/12 15:22:39 [I] [service.go:130] frps tcp listen on 0.0.0.0:7000
   2019/01/12 15:22:39 [I] [service.go:172] http service listen on 0.0.0.0:10080
   2019/01/12 15:22:39 [I] [service.go:193] https service listen on 0.0.0.0:10443
   2019/01/12 15:22:39 [I] [service.go:216] Dashboard listen on 0.0.0.0:7500
   2019/01/12 15:22:39 [I] [root.go:210] Start frps success
   ```

   - 此时访问 x.x.x.x:7500 并使用自己设置的用户名密码登录，即可看到仪表板界面

5. 后台运行`nohup ./frps -c frps.ini &`

### 3. 客户端

1. `vim frpc.ini`

2. 有以下内容

   ```shell
   [common]
   server_addr = x.x.x.x # 服务器地址
   server_port = 7000  # 和服务器端口对应
   token = 12345678 # 和服务器token对应
   [ssh]
   type = tcp
   local_ip = 127.0.0.1           
   local_port = 22
   remote_port = 2222  
   ```

   - “server_addr”为服务端IP地址，填入即可。
   - “server_port”为服务器端口，填入你设置的端口号即可，如果未改变就是7000
   - “token”是你在服务器上设置的连接口令，原样填入即可。

3. `./frpc -c frpc.ini`

4. 后台运行如服务器同上

## 映射web项目的端口

### 服务端

`vim frps.ini`

```shell
[common]
bind_port = 6000
token = mai
vhost_http_port = 2020 # 这里很重要哈
```

`./frps -c frps.ini`

### 客户端

`vim frpc.ini`

```shell
[common]
server_addr = 39.108.xx.xxx
server_port = 6000
token = mai

#[ssh]
#type = tcp
#local_ip = 127.0.0.1
#local_port = 22
#remote_port = 6000

# 举例第一个
[web-flask]
type = http
local_port = 5000
custom_domains = flask.dreamcat.ink

# 举例第二个
[web-flask2]
type = http
local_port = 5001
custom_domains = flask2.dreamcat.ink
```

`./frpc -c frpc.ini`

**注意：custom_domains的域名需要去域名系统解析上述外网地址**

**访问：flask.dreamcat.ink**

**访问：flas2.dreamcat.ink**

## 4. 开机自启

### 1. 第一种

最简单粗暴的方式直接在脚本`/etc/rc.d/rc.local`(和`/etc/rc.local`是同一个文件，软链)末尾添加自己的**脚本** 
然后，增加脚本执行权限。

```shell
nohup /home/dsp/config/frp/frpc -c /home/dsp/config/frp/frpc.ini &
```



```shell
chmod +x /etc/rc.d/rc.local
```

### 2. 第二种

```shell
crontab -e 
@reboot /home/user/test.sh
```

### 每次登陆自动执行

也可以设置每次登录自动执行脚本，在`/etc/profile.d/`目录下新建sh脚本， 
`/etc/profile`会遍历`/etc/profile.d/*.sh`

### 第三种

**压缩包中有systemd，可利用这个服务开机自启**

**比如，将frps.server复制到`etc/systemd/system/`**

```shell
[Unit]
Description=Frp Server Service
After=network.target

[Service]
Type=simple
User=nobody
Restart=on-failure
RestartSec=5s
ExecStart=/usr/bin/frps -c /etc/frp/frps.ini ##这里记得对应的路径

[Install]
WantedBy=multi-user.target
```

**接着可以利用systemd命令，比如**

```shell
systemctl start frps #启动
systemctl stop frps #停止
systemctl restart frps #重启
systemctl status frps #查看状态
systemctl enable frps #开机启动frp
systemctl disable frps # 禁止启动
```



