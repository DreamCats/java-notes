> 安装git和注册github或者gitee，不得了解一下git config？


## 配置文件存储位置

- `/etc/gitconfig` 文件：系统中对所有用户都普遍适用的配置。若使用 git config 时用 `--system` 选项，读写的就是这个文件；
- `~/.gitconfig` 文件：用户目录下的配置文件只适用于该用户。若使用 git config 时用 `--global` 选项，读写的就是这个文件；
- 当前项目的 Git 目录中的配置文件（也就是工作目录中的 `.git/config` 文件）：这里的配置仅仅针对当前项目有效。每一个级别的配置都会覆盖上层的相同配置，所以 `.git/config` 里的配置会覆盖 `/etc/gitconfig` 中的同名变量；

## 配置用户信息

当安装git后首先要做的事情是设置你的用户名和邮箱地址。这两条配置很重要，每次git提交时都会引用这两条信息，说明是谁提交了更新。

```sh
git config --global user.name "DreamCats"
git config --global user.email "xxx@xxx.com"
```

如果你希望在一个特定的项目中使用不同的名称或e-mail地址，你可以在该项目中运行该命令而不要`--global`选项。当然也可以去本地项目的`.git/config`中配置

## 查看配置

如果想查看自己的配置，可以使用`git config`命令。config 配置有system级别 global（用户级别） 和local（当前仓库）三个 设置先从system->global->local  底层配置会覆盖顶层配置 分别使用--system/global/local 可以定位到配置文件。

```sh
git config --system --list    # 查看系统配置
git config --global --list    # 查看当前用户配置
git config --local --list     # 查看当前仓库配置
git config --list             # 查看全部配置
```