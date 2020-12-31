## 上线bug

> 项目上线之后，大概将近一个月，我点击车次列表页面，突然什么都没有了，于是就开始找哪个不顺眼的家伙搞的鬼。

### 起因

访问主页[http://47.104.22.225:8080/home](http://47.104.22.225:8080/home)

![](https://imgkr.cn-bj.ufileos.com/8c563c2e-49f8-4a96-8d02-0109bc956449.png)

如图：

一直加载中，于是我看一下响应信息。

```json
code: 200
message: "success"
result: {code: "003099", msg: "系统错误"}
success: true
timestamp: "1589715142518"
```

### 查看一下gateway的日志
- 终端显示不长，于是我将日志用vscode打开看

```log
org.apache.dubbo.rpc.RpcException: No provider available from registry 39.108.93.119:2181 for service com.stylefeng.guns.rest.bus.IBusService on consumer 192.168.31.221 use dubbo version 2.7.4.1, please check status of providers(disabled, not registered or in blacklist).
```

类似于这样的信息，说我们的bus服务没有注册。

> 注意：出事之前，几个服务都在的呀，怎么今天bus突然不在了。于是，我不相信，我就去dubbo后台看了一下...

- 进入文件夹`dubbo-admin -> dubbo-admin-ui`
- 执行`npm run dev`
- 在我目前的mac上chrome浏览器输入`http://dubbo.dreamcat.ink:2020/`

**此时，还真没有bus服务**
![](https://imgkr.cn-bj.ufileos.com/3eb38910-64b5-439c-8437-c155d50857d2.png)

**哭晕在厕所**。

![](https://imgkr.cn-bj.ufileos.com/e391627b-dce7-4146-b6d7-78cbd1fee35e.jpg)

**于是乎，俺又不想重新启动服务，毕竟你看**

- 终端输入`ps -ef | grep guns-bus`
- 我们看到了惊人的一幕
```shell
pch      2003942       1  0 4月16 ?       06:10:54 java -jar guns-bus-0.0.1.jar
```

**又哭晕在厕所...**

### 猜测

#### DubboAdmin展示？
好像没问题吧？这样的话，其他三个也应该不存在的啊

#### 注册中心，bus节点丢了

- 查看日志，当天出现zk出现了大量的超时，原因是当天的zk**主节点**宕机了。

#### 找原因

**问题是否出现在了dubbo对zk重连恢复数据这块，开始查源码。注册中心源码ZookeeperRegistry。**

1. 连接注册zk：通过zkclient添加zk状态监听。并且继承了FailbackRegistry各种失败重试。

```java
public class ZookeeperRegistry extends FailbackRegistry {
    public ZookeeperRegistry(URL url, ZookeeperTransporter zookeeperTransporter) {
        ...
        // 1. 连接zk
        zkClient = zookeeperTransporter.connect(url);
        // 2. 添加zk状态监听
        zkClient.addStateListener(new StateListener() {
            @Override
            public void stateChanged(int state) {
                // 3. 重新连接后恢复动作，将当前的注册服务于订阅任务添加至重试列表中等待重试
                if (state == RECONNECTED) {
                    try {
                        recover();
                    } catch (Exception e) {
                        logger.error(e.getMessage(), e);
                    }
                }
            }
        });
    }
}

```

2. zk客户端：默认使用CuratorZookeeperClient实现

```java
public class CuratorZookeeperClient extends AbstractZookeeperClient<CuratorWatcher> {
    public CuratorZookeeperClient(URL url) {
        ...
            client = builder.build();
            // dubbo对接zk连接状态监听器
            client.getConnectionStateListenable().addListener(new ConnectionStateListener() {
                @Override
                public void stateChanged(CuratorFramework client, ConnectionState state) {
                    if (state == ConnectionState.LOST) {
                        CuratorZookeeperClient.this.stateChanged(StateListener.DISCONNECTED);
                    } else if (state == ConnectionState.CONNECTED) {
                        CuratorZookeeperClient.this.stateChanged(StateListener.CONNECTED);
                    } else if (state == ConnectionState.RECONNECTED) {
                        CuratorZookeeperClient.this.stateChanged(StateListener.RECONNECTED);
                    }
                }
            });
            client.start();
        ...
    }
}
```

3. 重试任务：注册重新失败重连任务FailbackRegistry中的DubboRegistryFailedRetryTimer，默认5秒检查一次是否需要失败恢复

```java
public FailbackRegistry(URL url) {
    super(url);
    this.retryPeriod = url.getParameter(Constants.REGISTRY_RETRY_PERIOD_KEY, Constants.DEFAULT_REGISTRY_RETRY_PERIOD);
    this.retryFuture = retryExecutor.scheduleWithFixedDelay(new Runnable() {
        @Override
        public void run() {
            // Check and connect to the registry
            try {
				    // failedRegistered失败注册重试，failedUnregistered失败注销重试，failedSubscribed失败订阅重试，failedUnsubscribed失败取消订阅重试，failedNotified失败通知重试
                retry();
            } catch (Throwable t) { // Defensive fault tolerance
                logger.error("Unexpected error occur at failed retry, cause: " + t.getMessage(), t);
            }
        }
    }, retryPeriod, retryPeriod, TimeUnit.MILLISECONDS);
}

```

#### 总结一波
通过三部分的代码我们可以推断，如果zk状态监听与恢复部分出现问题可能会导致数据丢失问题。于是查看相关的api并且尝试查看dubbo社区的问题与bug，果然发现了类似问题的修改与原因分析：[https://github.com/apache/dubbo/pull/5135](https://github.com/apache/dubbo/pull/5135)

原因：
> 如果ZNode数据已经存在，在会话超时期间，此时我们将重建一个数据节点，这个重复的异常原因可能是由于zk server中老的超时会话依然持有节点导致该节点的delete删除事件延迟，并且zk server还没有来得及去执行删除，可能由这种场景引起。在这个情景下，我们可以本地删除节点后再创建恢复节点数据。

其实说白了：
> 如果会话断开连接又重新连接成功。断开连接发出的删除节点事件，**因为延迟原因走在了重新连接恢复节点事件的后面**。**导致重新连接后没能成功恢复节点**。也就是我么见到的，provider有三个节点服务正常，但是zk注册中心中的提供者节点数据丢失，导致出现该节点对其他订阅者不可见的现象

```java
    protected void createEphemeral(String path, String data) {
        byte[] dataBytes = data.getBytes(CHARSET);

        try {
            ((ACLBackgroundPathAndBytesable)this.client.create().withMode(CreateMode.EPHEMERAL)).forPath(path, dataBytes);
        } catch (NodeExistsException var5) {
            logger.warn("ZNode " + path + " already exists, since we will only try to recreate a node on a session expiration, this duplication might be caused by a delete delay from the zk server, which means the old expired session may still holds this ZNode and the server just hasn't got time to do the deletion. In this case, we can just try to delete and create again.", var5);
            this.deletePath(path);
            this.createEphemeral(path, data);
        } catch (Exception var6) {
            throw new IllegalStateException(var6.getMessage(), var6);
        }

    }
```

**人家源码的warn写的是真清楚**...

### 定位cpu过高的线程或者位置

> 这里要用到几个命令了，比如top，jstack等。

#### 查看一波
- 终端输入`top -H`

![](https://imgkr.cn-bj.ufileos.com/e69d6335-c260-4d69-9f4d-840b7427cce5.png)
- 类似于长这样，但是有些含义就不解释了，请上互联网
- 我就挑它吧`1227027`
- 终端输入`top -Hp 1227027`
![](https://imgkr.cn-bj.ufileos.com/d703c8ae-a3a8-4eed-ad57-6c3440dd4ea7.png)
- 这里我也就不介绍了，找一个`1227029`
- `printf "%x\n" 1227029`结果是：`12b915`
- `jstack -l 1227027| grep 0x12b915 -A 10`

```shell
"DestroyJavaVM" #84 prio=5 os_prio=0 tid=0x00007f69f800b7c0 nid=0x12b915 waiting on condition [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

   Locked ownable synchronizers:
	- None

"BrokerFastFailureScheduledThread1" #83 prio=5 os_prio=0 tid=0x00007f69f8b29050 nid=0x12ba0a runnable [0x00007f68b6bec000]
   java.lang.Thread.State: TIMED_WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x0000000080594a60> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
	at java.util.concurrent.locks.LockSupport.parkNanos(LockSupport.java:215)
```
**以上就是举个例子** ...