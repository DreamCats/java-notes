> 三种方式

## 使用配置

```sh
npm config set registry 镜像源地址
```

### cnpm

```sh
// 先安装cnpm工具
npm install -g cnpm --registry=镜像源地址
// 使用cnpm代替npm
cnpm install 模块名
```

### 使用nrm

```sh
// 先安装nrm工具
npm install -g nrm
// 查看当前可用的镜像源
nrm ls
// 切换npm源
nrm use 镜像源名称
```
