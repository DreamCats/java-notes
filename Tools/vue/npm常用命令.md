> npm常用命令


- 查看npm命令

```sh
npm help
```

- 安装模块(包)：

```sh
// 全局安装
npm install 模块名 -g
// 本地安装
npm install 模块名
// 一次性安装多个
npm install 模块1 模块2 模块n --save

// 安装运行时依赖
npm install 模块名 --save or npm install 模块名 -S
npm install 模块名 --save-dev
```

- 查看安装目录：

```sh
// 查看本地安装的目录
npm root
// 查看全局安装的目录
npm root -g
```

- 卸载模块(包)：

```sh
// 卸载本地模块
npm uninstall 模块名
// 卸载全局模块
npm uninstall -g 模块名
```

- 更新模块(包)：

```sh
npm update 模块名
npm update 模块名 -g
```

- 查看当前安装的模块(包)：

```sh
npm ls
npm ls -g
npm list -g --depth 0
```

- 查看模块(包)的信息：

```sh
npm info 模块名
```

