## 引言

**常用的命令行技巧...**


## 前言

- 主要针对常用的一些命令行的使用
- 主要是unix的操作系统

<!-- more -->

## 常用

- `cd`:这个就不需要多讲了
- `clear`:这个也不需要多讲了
- `mkdir`:当前目录创建文件夹
- `touch`:当前目录下创建文件
- `ls`:查看目录下的文件
  - `ls -a`:查看文件，包括隐藏文件
  - `ls -l`:详细查看文件
- `top`:查看cpu和内存等
- `df -h`:查看各个磁盘使用的状态
- `du -hd1`:查看当前目录下文件的大小
- `du -h`:查看当前目录下文件的大小，包括子目录
- `nautilus ./`:打开当前文件管理器
- `pwd`:查看当前路径
- `w`:查看机器运行的时间

- `lsof -i:8000`：查看端口占用情况
- `ps -aux | grep python`: 查看某个进程
- `ps -ef | grep python`: 查看某个进程

## 统计文件数目

### ls

`ls -l | wc -l`计数当前目录的文件和文件夹。 它会计算所有的文件和目录。

`ls -la | wc -l`统计当前目录包含隐藏文件和目录在内的文件和文件夹。

### find

`find . -type f | wc -l`递归计算当前目录的文件，包括隐藏文件。

`find . type d | wc -l`递归计算包含隐藏目录在内的目录数。

`find . -name '*.txt' | wc -l`根据文件扩展名计数文件数量。 这里我们要计算 `.txt` 文件。

## 日常使用

- 可以通过**Tab**键实现自动补全参数
- 使用**ctrl-r**搜索命令行的历史记录
- 按下**ctrl-w**删除键入的最后的一个单词
- 按下**ctrl-u**可以删除行内光标所在位置之前的内容，**alt-b**和**alt-f**可以在以单词为单位移动光标，**ctrl-a**可以将光标移至行首，**ctrl-e**可以将光标移至行尾
- 回到前一个工作路径：`cd -`
- `pstree -p`以一种优雅的方式展示进程树
- `kill -STOP[pid]`停止一个进程
- 使用`nohup`或`disown`使一个后台进程持续运行
  - eg：`nohup python -u demo.py > ./logs/demo.log 2>&1 &`
- 使用`netstat -lntp`检查哪些进程在监听端口
- 使用`uptime`或`w`查看系统已经运行对长时间
- 使用`alias`来常见常用命令的快捷形式，例如:`alias ll='ls -latr'`创建了一个新的命令别名，可以把别名放在`~./bashrc`

## 文件及数据处理

- 在当前目录下通过文件名查找一个文件，使用类似于这样的命令：`find . -name '*something'`
- 使用`wc`去计算新行数`-l`，字符数`-m`，单词数`-w`以及字节数`-c`，例如`ls | wc -l` 、`ls -lR | grep "^-" | wc -l`
- `du -sh *`查看某个文件的大小
- `du -h --max-depth=1`查看当前目下文件的大小
- `du -hd1`查看当前目录下文件的大小--mac
- `df -hl` 查看磁盘情况



## 和服务器交互

- 下载文件`scp username@servername:/path/filename /var/www/local_dir（本地目录）`
- 上传文件`scp /path/filename username@servername:/path`
- 下载文件夹`scp -r username@servername:/path/filename /var/www/local_dir（本地目录）`
- 下载文件夹`scp  -r /path/filename username@servername:/path`
- 若是更改端口， 则前面 加上-p



## bash互换

- zsh切bash`chsh -s /bin/bash`
- bash切zsh`chsh -s /bin/zsh`

## 有一些挺有用

- `bc`计算器
- `cal`漂亮的日历
- `tree`以树的形式显示路径和文件
- `watch`重复运行同一个命令，展示结果有更改的部分

## 持续补充...

