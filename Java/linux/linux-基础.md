## 引言

**linux-基础**

## 常用操作以及概念

### 快捷键

- Tab: 命令和文件名补全；
- Ctrl+C: 中断正在运行的程序；
- Ctrl+D: 结束键盘输入(End Of File，EOF)

### 求助

<!-- more -->

#### --help

指令的基本用法与选项介绍。

#### man

man 是 manual 的缩写，将指令的具体信息显示出来。

####  info

info 与 man 类似，但是 info 将文档分成一个个页面，每个页面可以进行跳转。

#### doc

/usr/share/doc 存放着软件的一整套说明文件。

### 关机

#### who

在关机前需要先使用 who 命令查看有没有其它用户在线。

#### sync

为了加快对磁盘文件的读写速度，位于内存中的文件数据不会立即同步到磁盘上，因此关机之前需要先进行 sync 同步操作。

#### shutdown

```bash
## shutdown [-krhc] 时间 [信息]
-k :  不会关机，只是发送警告信息，通知所有在线的用户
-r :  将系统的服务停掉后就重新启动
-h :  将系统的服务停掉后就立即关机
-c :  取消已经在进行的 shutdown 指令内容
```

### PATH

可以在环境变量 PATH 中声明可执行文件的路径，路径之间用 : 分隔。

`/usr/local/bin:/usr/bin:/usr/local/sbin:/usr/sbin:/home/dmtsai/.local/bin:/home/dmtsai/bin`

### sudo

sudo 允许一般用户使用 root 可执行的命令，不过只有在 /etc/sudoers 配置文件中添加的用户才能使用该指令.

### 包管理工具

RPM 和 DPKG 为最常见的两类软件包管理工具:

- RPM 全称为 Redhat Package Manager，最早由 Red Hat 公司制定实施，随后被 GNU 开源操作系统接受并成为很多 Linux 系统 (RHEL) 的既定软件标准。
- 与 RPM 进行竞争的是基于 Debian 操作系统 (Ubuntu) 的 DEB 软件包管理工具 DPKG，全称为 Debian Package，功能方面与 RPM 相似。

YUM 基于 RPM，具有依赖管理功能，并具有软件升级的功能。

### 发行版

Linux 发行版是 Linux 内核及各种应用软件的集成版本。

| 基于的包管理工具 | 商业发行版 |   社区发行版    |
| :--------------: | :--------: | :-------------: |
|       RPM        |  Red Hat   | Fedora / CentOS |
|       DPKG       |   Ubuntu   |     Debian      |

### VIM

- 一般指令模式(Command mode): VIM 的默认模式，可以用于移动游标查看内容；
- 编辑模式(Insert mode): 按下 "i" 等按键之后进入，可以对文本进行编辑；
- 指令列模式(Bottom-line mode): 按下 ":" 按键之后进入，用于保存退出等操作。

在指令列模式下，有以下命令用于离开或者保存文件。

| 命令 |                             作用                             |
| :--: | :----------------------------------------------------------: |
|  :w  |                           写入磁盘                           |
| :w!  | 当文件为只读时，强制写入磁盘。到底能不能写入，与用户对该文件的权限有关 |
|  :q  |                             离开                             |
| :q!  |                        强制离开不保存                        |
| :wq  |                        写入磁盘后离开                        |
| :wq! |                      强制写入磁盘后离开                      |

## 磁盘

### 磁盘接口

#### IDE	

IDE(ATA)全称 Advanced Technology Attachment，接口速度最大为 133MB/s，因为并口线的抗干扰性太差，且排线占用空间较大，不利电脑内部散热，已逐渐被 SATA 所取代。

#### SATA

SATA 全称 Serial ATA，也就是使用串口的 ATA 接口，抗干扰性强，且对数据线的长度要求比 ATA 低很多，支持热插拔等功能。SATA-II 的接口速度为 300MiB/s，而新的 SATA-III 标准可达到 600MiB/s 的传输速度。SATA 的数据线也比 ATA 的细得多，有利于机箱内的空气流通，整理线材也比较方便。

#### SCSI

SCSI 全称是 Small Computer System Interface(小型机系统接口)，经历多代的发展，从早期的 SCSI-II 到目前的 Ultra320 SCSI 以及 Fiber-Channel(光纤通道)，接口型式也多种多样。SCSI 硬盘广为工作站级个人电脑以及服务器所使用，因此会使用较为先进的技术，如碟片转速 15000rpm 的高转速，且传输时 CPU 占用率较低，但是单价也比相同容量的 ATA 及 SATA 硬盘更加昂贵。

#### SAS

SAS(Serial Attached SCSI)是新一代的 SCSI 技术，和 SATA 硬盘相同，都是采取序列式技术以获得更高的传输速度，可达到 6Gb/s。此外也透过缩小连接线改善系统内部空间等。

## 分区

### 分区表

磁盘分区表主要有两种格式，一种是限制较多的 MBR 分区表，一种是较新且限制较少的 GPT 分区表。

#### MBR

MBR 中，第一个扇区最重要，里面有主要开机记录(Master boot record, MBR)及分区表(partition table)，其中主要开机记录占 446 bytes，分区表占 64 bytes。

分区表只有 64 bytes，最多只能存储 4 个分区，这 4 个分区为主分区(Primary)和扩展分区(Extended)。其中扩展分区只有一个，它使用其它扇区用记录额外的分区表，因此通过扩展分区可以分出更多分区，这些分区称为逻辑分区。

Linux 也把分区当成文件，分区文件的命名方式为: 磁盘文件名 + 编号，例如 /dev/sda1。注意，逻辑分区的编号从 5 开始。

#### GPT

不同的磁盘有不同的扇区大小，例如 512 bytes 和最新磁盘的 4 k。GPT 为了兼容所有磁盘，在定义扇区上使用逻辑区块地址(Logical Block Address, LBA)，LBA 默认大小为 512 bytes。

GPT 第 1 个区块记录了主要开机记录(MBR)，紧接着是 33 个区块记录分区信息，并把最后的 33 个区块用于对分区信息进行备份。这 33 个区块第一个为 GPT 表头纪录，这个部份纪录了分区表本身的位置与大小和备份分区的位置，同时放置了分区表的校验码 (CRC32)，操作系统可以根据这个校验码来判断 GPT 是否正确。若有错误，可以使用备份分区进行恢复。

GPT 没有扩展分区概念，都是主分区，每个 LAB 可以分 4 个分区，因此总共可以分 4 * 32 = 128 个分区。

MBR 不支持 2.2 TB 以上的硬盘，GPT 则最多支持到 233 TB = 8 ZB。

### 开机检测程序

#### BIOS

BIOS(Basic Input/Output System，基本输入输出系统)，它是一个固件(嵌入在硬件中的软件)，BIOS 程序存放在断电后内容不会丢失的只读内存中。

BIOS 是开机的时候计算机执行的第一个程序，这个程序知道可以开机的磁盘，并读取磁盘第一个扇区的主要开机记录(MBR)，由主要开机记录(MBR)执行其中的开机管理程序，这个开机管理程序会加载操作系统的核心文件。

主要开机记录(MBR)中的开机管理程序提供以下功能: 选单、载入核心文件以及转交其它开机管理程序。转交这个功能可以用来实现了多重引导，只需要将另一个操作系统的开机管理程序安装在其它分区的启动扇区上，在启动开机管理程序时，就可以通过选单选择启动当前的操作系统或者转交给其它开机管理程序从而启动另一个操作系统。

下图中，第一扇区的主要开机记录(MBR)中的开机管理程序提供了两个选单: M1、M2，M1 指向了 Windows 操作系统，而 M2 指向其它分区的启动扇区，里面包含了另外一个开机管理程序，提供了一个指向 Linux 的选单。

安装多重引导，最好先安装 Windows 再安装 Linux。因为安装 Windows 时会覆盖掉主要开机记录(MBR)，而 Linux 可以选择将开机管理程序安装在主要开机记录(MBR)或者其它分区的启动扇区，并且可以设置开机管理程序的选单。

#### UEFI

BIOS 不可以读取 GPT 分区表，而 UEFI 可以。

## 文件系统

### 分区与文件系统

对分区进行格式化是为了在分区上建立文件系统。一个分区通常只能格式化为一个文件系统，但是磁盘阵列等技术可以将一个分区格式化为多个文件系统。

### 组成

最主要的几个组成部分如下:

- inode: 一个文件占用一个 inode，记录文件的属性，同时记录此文件的内容所在的 block 编号；
- block: 记录文件的内容，文件太大时，会占用多个 block。
- superblock: 记录文件系统的整体信息，包括 inode 和 block 的总量、使用量、剩余量，以及文件系统的格式与相关信息等；
- block bitmap: 记录 block 是否被使用的位域。

### 文件读取

对于 Ext2 文件系统，当要读取一个文件的内容时，先在 inode 中去查找文件内容所在的所有 block，然后把所有 block 的内容读出来。

### 磁盘碎片

指一个文件内容所在的 block 过于分散。

### Block

在 Ext2 文件系统中所支持的 block 大小有 1K，2K 及 4K 三种，不同的大小限制了单个文件和文件系统的最大大小。

一个 block 只能被一个文件所使用，未使用的部分直接浪费了。因此如果需要存储大量的小文件，那么最好选用比较小的 block。

### inode

inode 具体包含以下信息:

- 权限 (read/write/excute)；
- 拥有者与群组 (owner/group)；
- 容量；
- 建立或状态改变的时间 (ctime)；
- 最近一次的读取时间 (atime)；
- 最近修改的时间 (mtime)；
- 定义文件特性的旗标 (flag)，如 SetUID...；
- 该文件真正内容的指向 (pointer)。

inode 具有以下特点:

- 每个 inode 大小均固定为 128 bytes (新的 ext4 与 xfs 可设定到 256 bytes)；
- 每个文件都仅会占用一个 inode。

inode 中记录了文件内容所在的 block 编号，但是每个 block 非常小，一个大文件随便都需要几十万的 block。而一个 inode 大小有限，无法直接引用这么多 block 编号。因此引入了间接、双间接、三间接引用。间接引用是指，让 inode 记录的引用 block 块记录引用信息。

### 目录

建立一个目录时，会分配一个 inode 与至少一个 block。block 记录的内容是目录下所有文件的 inode 编号以及文件名。

可以看出文件的 inode 本身不记录文件名，文件名记录在目录中，因此新增文件、删除文件、更改文件名这些操作与目录的 w 权限有关。

### 日志

如果突然断电，那么文件系统会发生错误，例如断电前只修改了 block bitmap，而还没有将数据真正写入 block 中。

ext3/ext4 文件系统引入了日志功能，可以利用日志来修复文件系统。

### 挂载

挂载利用目录作为文件系统的进入点，也就是说，进入目录之后就可以读取文件系统的数据。

### 目录配置

为了使不同 Linux 发行版本的目录结构保持一致性，Filesystem Hierarchy Standard (FHS) 规定了 Linux 的目录结构。最基础的三个目录如下:

- / (root, 根目录)
- /usr (unix software resource): 所有系统默认软件都会安装到这个目录；
- /var (variable): 存放系统或程序运行过程中的数据文件。

## 文件

### 文件属性

用户分为三种: 文件拥有者、群组以及其它人，对不同的用户有不同的文件权限。

使用 ls 查看一个文件时，会显示一个文件的信息，例如 `drwxr-xr-x. 3 root root 17 May 6 00:14 .config`，对这个信息的解释如下:

- drwxr-xr-x: 文件类型以及权限，第 1 位为文件类型字段，后 9 位为文件权限字段
- 3: 链接数
- root: 文件拥有者
- root: 所属群组
- 17: 文件大小
- May 6 00:14: 文件最后被修改的时间
- .config: 文件名

常见的文件类型及其含义有:

- d: 目录
- -: 文件
- l: 链接文件

9 位的文件权限字段中，每 3 个为一组，共 3 组，每一组分别代表对文件拥有者、所属群组以及其它人的文件权限。一组权限中的 3 位分别为 r、w、x 权限，表示可读、可写、可执行。

文件时间有以下三种:

- modification time (mtime): 文件的内容更新就会更新；
- status time (ctime): 文件的状态(权限、属性)更新就会更新；
- access time (atime): 读取文件时就会更新。

### 文件与目录的基本操作

#### ls

列出文件或者目录的信息，目录的信息就是其中包含的文件。

```bash
## ls [-aAdfFhilnrRSt] file|dir
-a : 列出全部的文件
-d : 仅列出目录本身
-l : 以长数据串行列出，包含文件的属性与权限等等数据
 
```

#### cd

更新当前目录

`cd [相对路径或者绝对路径]`

#### mkdir

创建目录

```bash
## mkdir [-mp] 目录名称
-m : 配置目录权限
-p : 递归创建目录
```

#### rmdir

删除目录，目录必须为空

```bash
rmdir [-p] 目录名称
-p : 递归删除目录
```

#### touch

更新文件时间或者建立新文件

```bash
## touch [-acdmt] filename
-a :  更新 atime
-c :  更新 ctime，若该文件不存在则不建立新文件
-m :  更新 mtime
-d :  后面可以接更新日期而不使用当前日期，也可以使用 --date="日期或时间"
-t :  后面可以接更新时间而不使用当前时间，格式为[YYYYMMDDhhmm]

```

#### cp

复制文件

如果源文件有两个以上，则目的文件一定要是目录才行。

```bash
cp [-adfilprsu] source destination
-a : 相当于 -dr --preserve=all 的意思，至于 dr 请参考下列说明
-d : 若来源文件为链接文件，则复制链接文件属性而非文件本身
-i : 若目标文件已经存在时，在覆盖前会先询问
-p : 连同文件的属性一起复制过去
-r : 递归持续复制
-u : destination 比 source 旧才更新 destination，或 destination 不存在的情况下才复制
--preserve=all : 除了 -p 的权限相关参数外，还加入 SELinux 的属性, links, xattr 等也复制了

```

#### rm

删除文件

```bash
## rm [-fir] 文件或目录
-r : 递归删除
```

#### mv

移动文件

```bash
## mv [-fiu] source destination
## mv [options] source1 source2 source3 .... directory
-f :  force 强制的意思，如果目标文件已经存在，不会询问而直接覆盖
```

### 修改权限

可以将一组权限用数字来表示，此时一组权限的 3 个位当做二进制数字的位，从左到右每个位的权值为 4、2、1，即每个权限对应的数字权值为 r : 4、w : 2、x : 1。

`## chmod [-R] xyz dirname/filename`

示例: 将 .bashrc 文件的权限修改为 -rwxr-xr--。

`## chmod 754 .bashrc`

也可以使用符号来设定权限。

```bash
## chmod [ugoa]  [+-=] [rwx] dirname/filename
- u: 拥有者
- g: 所属群组
- o: 其他人
- a: 所有人
- +: 添加权限
- -: 移除权限
- =: 设定权限
```

示例: 为 .bashrc 文件的所有用户添加写权限。

`## chmod a+w .bashrc`

### 文件默认权限

- 文件默认权限: 文件默认没有可执行权限，因此为 666，也就是 -rw-rw-rw- 。
- 目录默认权限: 目录必须要能够进入，也就是必须拥有可执行权限，因此为 777 ，也就是 drwxrwxrwx。

可以通过 umask 设置或者查看文件的默认权限，通常以掩码的形式来表示，例如 002 表示其它用户的权限去除了一个 2 的权限，也就是写权限，因此建立新文件时默认的权限为 -rw-rw-r--。

### 目录的权限

文件名不是存储在一个文件的内容中，而是存储在一个文件所在的目录中。因此，拥有文件的 w 权限并不能对文件名进行修改。

目录存储文件列表，一个目录的权限也就是对其文件列表的权限。因此，目录的 r 权限表示可以读取文件列表；w 权限表示可以修改文件列表，具体来说，就是添加删除文件，对文件名进行修改；x 权限可以让该目录成为工作目录，x 权限是 r 和 w 权限的基础，如果不能使一个目录成为工作目录，也就没办法读取文件列表以及对文件列表进行修改了。

### 链接

```bash
## ln [-sf] source_filename dist_filename
-s : 默认是 hard link，加 -s 为 symbolic link
-f : 如果目标文件存在时，先删除目标文件
```

### 实体链接

在目录下创建一个条目，记录着文件名与 inode 编号，这个 inode 就是源文件的 inode。

删除任意一个条目，文件还是存在，只要引用数量不为 0。

有以下限制: 不能跨越文件系统、不能对目录进行链接。

```bash
## ln /etc/crontab .
## ll -i /etc/crontab crontab
34474855 -rw-r--r--. 2 root root 451 Jun 10 2014 crontab
34474855 -rw-r--r--. 2 root root 451 Jun 10 2014 /etc/crontab
```

### 符号链接

符号链接文件保存着源文件所在的绝对路径，在读取时会定位到源文件上，可以理解为 Windows 的快捷方式。

当源文件被删除了，链接文件就打不开了。

可以为目录建立链接。

```bash
## ll -i /etc/crontab /root/crontab2
34474855 -rw-r--r--. 2 root root 451 Jun 10 2014 /etc/crontab
53745909 lrwxrwxrwx. 1 root root 12 Jun 23 22:31 /root/crontab2 -> /etc/crontab
```

### 获取文件内容

#### cat

取得文件内容。

```bash
## cat [-AbEnTv] filename
-n : 打印出行号，连同空白行也会有行号，-b 不会
```

#### tac

是 cat 的反向操作，从最后一行开始打印。

#### more

和 cat 不同的是它可以一页一页查看文件内容，比较适合大文件的查看。

#### less

和 more 类似，但是多了一个向前翻页的功能。

#### head

取得文件前几行。

```bash
## head [-n number] filename
-n : 后面接数字，代表显示几行的意思
```

#### tail

是head的反向操作，之水取得是后几行

#### od

以字符或者十六进制的形式显示二进制文件。

### 指令与文件搜索

#### which

指令搜索

```bash
## which [-a] command
-a : 将所有指令列出，而不是只列第一个
```

#### whereis

文件搜索。速度比较快，因为它只搜索几个特定的目录。

```bash
## whereis [-bmsu] dirname/filename
```

#### locate

文件搜索。可以用关键字或者正则表达式进行搜索。

locate 使用 /var/lib/mlocate/ 这个数据库来进行搜索，它存储在内存中，并且每天更新一次，所以无法用 locate 搜索新建的文件。可以使用 updatedb 来立即更新数据库。

```bash
## locate [-ir] keyword
-r: 正则表达式
```

#### find

文件搜索。可以使用文件的属性和权限进行搜索。

```bash
## find [basedir] [option]
example: find . -name "shadow*"
```

## 压缩与打包

### 压缩文件名

#### gzip

gzip 是 Linux 使用最广的压缩指令，可以解开 compress、zip 与 gzip 所压缩的文件。

经过 gzip 压缩过，源文件就不存在了。

有 9 个不同的压缩等级可以使用。

可以使用 zcat、zmore、zless 来读取压缩文件的内容。

```bash
$ gzip [-cdtv#] filename
-c : 将压缩的数据输出到屏幕上
-d : 解压缩
-t : 检验压缩文件是否出错
-v : 显示压缩比等信息
-## :  ## 为数字的意思，代表压缩等级，数字越大压缩比越高，默认为 6
```

#### bzip2

提供比 gzip 更高的压缩比。

查看命令: bzcat、bzmore、bzless、bzgrep。

#### xz

提供比 bzip2 更佳的压缩比。

可以看到，gzip、bzip2、xz 的压缩比不断优化。不过要注意的是，压缩比越高，压缩的时间也越长。

查看命令: xzcat、xzmore、xzless、xzgrep。

### 打包

压缩指令只能对一个文件进行压缩，而打包能够将多个文件打包成一个大文件。tar 不仅可以用于打包，也可以使用 gip、bzip2、xz 将打包文件进行压缩。

```bash
$ tar [-z|-j|-J] [cv] [-f 新建的 tar 文件] filename...  ==打包压缩
$ tar [-z|-j|-J] [tv] [-f 已有的 tar 文件]              ==查看
$ tar [-z|-j|-J] [xv] [-f 已有的 tar 文件] [-C 目录]    ==解压缩
-z : 使用 zip；
-j : 使用 bzip2；
-J : 使用 xz；
-c : 新建打包文件；
-t : 查看打包文件里面有哪些文件；
-x : 解打包或解压缩的功能；
-v : 在压缩/解压缩的过程中，显示正在处理的文件名；
-f : filename: 要处理的文件；
-C 目录 :  在特定目录解压缩。
```

| 使用方式 | 命令                                                  |
| :------: | ----------------------------------------------------- |
| 打包压缩 | tar -jcv -f filename.tar.bz2 要被压缩的文件或目录名称 |
|  查 看   | tar -jtv -f filename.tar.bz2                          |
|  解压缩  | tar -jxv -f filename.tar.bz2 -C 要解压缩的目录        |

## bash

可以通过 Shell 请求内核提供服务，Bash 正是 Shell 的一种。

### 特性

- 命令历史：记录使用过的命令
- 命令与文件补全：快捷键：tab
- shell scripts
- 通配符: 例如 ls -l /usr/bin/X* 列出 /usr/bin 下面所有以 X 开头的文件

### 变量操作

对一个变量赋值直接使用 =。

对变量取用需要在变量前加上 $ ，也可以用 ${} 的形式；

输出变量使用 echo 命令。

```bash
$ x=abc
$ echo $x
$ echo ${x}
```

变量内容如果有空格，必须使用双引号或者单引号。

- 双引号内的特殊字符可以保留原本特性，例如 x="lang is $LANG"，则 x 的值为 lang is zh_TW.UTF-8；
- 单引号内的特殊字符就是特殊字符本身，例如 x='lang is $LANG'，则 x 的值为 lang is $LANG。

可以使用 `指令` 或者 $(指令) 的方式将指令的执行结果赋值给变量。例如 version=$(uname -r)，则 version 的值为 4.15.0-22-generic。

可以使用 `指令` 或者 $(指令) 的方式将指令的执行结果赋值给变量。例如 version=$(uname -r)，则 version 的值为 4.15.0-22-generic。

Bash 的变量可以声明为数组和整数数字。注意数字类型没有浮点数。如果不进行声明，默认是字符串类型。变量的声明使用 declare 命令:

```bash
$ declare [-aixr] variable
-a :  定义为数组类型
-i :  定义为整数类型
-x :  定义为环境变量
-r :  定义为 readonly 类型
```

对数组操作

```bash
$ array[1]=a
$ array[2]=b
$ echo ${array[1]}
```

## 正则

g/re/p(globally search a regular expression and print)，使用正则表示式进行全局查找并打印。

```bash
$ grep [-acinv] [--color=auto] 搜寻字符串 filename
-c :  统计个数
-i :  忽略大小写
-n :  输出行号
-v :  反向选择，也就是显示出没有 搜寻字符串 内容的那一行
--color=auto : 找到的关键字加颜色显示
```

示例: 把含有 the 字符串的行提取出来(注意默认会有 --color=auto 选项，因此以下内容在 Linux 中有颜色显示 the 字符串)

```bash
$ grep -n 'the' regular_express.txt
8:I can't finish the test.
12:the symbol '*' is represented as start.
15:You are the best is mean you are the no. 1.
16:The world Happy is the same with "glad".
18:google is the best tools for search keyword
```

## 进程管理

### 查看进程

#### ps

查看某个时间点的进程信息

示例一: 查看自己的进程`ps -l`

示例二: 查看系统所有进程`ps aux`

示例三: 查看特定的进程`ps aux | grep python`

#### top

实时显示进程信息

示例: 两秒钟刷新一次`top -d 2`

#### pstree

查看进程树

示例: 查看所有进程树`pstree -A`

#### netstat

查看占用端口的进程

示例: 查看特定端口的进程`netstat -anp | grep port`

### 孤儿进程

一个父进程退出，而它的一个或多个子进程还在运行，那么这些子进程将成为孤儿进程。

孤儿进程将被init进程（进程号为1）所收养，并有init进程对它们完成状态收集工作。

由于孤儿进程会被init进程收养，所以孤儿进程不会对系统造成危害。

### 僵尸进程

一个子进程的进程描述符在子进程退出时不会释放，只有当父进程通过wait()或者waitpid()获取了子进程信息后才会释放。如果子进程退出，而父进程并没有调用wait()或waitpid()，那么子进程的进程描述符仍然保存在系统中，这种进程称之为僵尸进程。

僵尸进程通过 ps 命令显示出来的状态为 Z(zombie)。

系统所能使用的进程号是有限的，如果产生大量僵尸进程，将因为没有可用的进程号而导致系统不能产生新的进程。

要消灭系统中大量的僵尸进程，只需要将其父进程杀死，此时僵尸进程就会变成孤儿进程，从而被 init 所收养，这样 init 就会释放所有的僵尸进程所占有的资源，从而结束僵尸进程。