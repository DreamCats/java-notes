## 准备工作

- [官网下载链接](<https://www.oracle.com/technetwork/java/javase/downloads/index-jsp-138363.html>)
- windows10
- mac
- linux（ubuntu和centos）

## windows10

- 下载安装包，选择windows x64
- 我一般在c盘创建Java目录，其次在该目录下创建jdk和jre目录
- 双击exe文件安装，首先安装的是jdk，选择`C:\Java\jdk`，当安装完毕之后，自动弹出安装jre，`C:\Java\jre`
- 配置环境，首先win+s，搜索**环境变量**，在用户变量下新建
- 变量名：JAVA_HOME
- 变量值：`C:\Java\jdk`
- 再新建
- 变量名：CLASSPATH
- 变量值：`.;%JAVA_HOME%\lib\dt.jar;%JAVA_HOME%\lib\tools.jar;`
- 在Path环境变量下，新建两个，分别是
- `%JAVA_HOME%\bin`
- `%JAVA_HOME%\jre\bin`
- win+r 输入`cmd` 确定，在命令窗口输入`java -version` 则出现java version版本号
- javac utf-8编码问题的话，可添加环境变量
- `JAVA_TOOL_OPTIONS`值为`Dfile.encoding=UTF-8`

## mac

- mac自带java，自带就够了
- 使用`homebrew`，非常简便
- 终端输入`brew tap caskroom/versions`              `brew cask install java8`
- or `brew cask install caskroom/versions/java8`
- brew安装好自动会配置环境

## ubuntu16.04 or centos7

- Ubuntu和centos安装步骤差不多一样的
- 官网下载linux-x64.tar.gz

- 将`xxx.linux-x64.tar.gz` 更改为`java`

- java移动到/usr/local/

- 根目录下`vi _bashrc`

- 添加：

- ```shell
  export JAVA_HOME=/usr/local/java
  export CLASSPATH=.:${JAVA_HOME}/lib:${JRE_HOME}/lib
  export PATH=${JAVA_HOME}/bin:${PATH}
  ```

- 记的source

- `java -version`

  


