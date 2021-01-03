

## 1. 准备工作

> 安装前卸载完全node


## 2. 安装

### [github链接](<https://github.com/nvm-sh/nvm>)

### 第一种

```shell
curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.34.0/install.sh | bash
```

### 第二种

```shell
wget -qO- https://raw.githubusercontent.com/nvm-sh/nvm/v0.34.0/install.sh | bash
```

### 环境变量

The script clones the nvm repository to `~/.nvm` and adds the source line to your profile (`~/.bash_profile`, `~/.zshrc`, `~/.profile`, or`~/.bashrc`).

```shell
export NVM_DIR="$HOME/.nvm"
[ -s "$NVM_DIR/nvm.sh" ] && \. "$NVM_DIR/nvm.sh"  # This loads nvm
[ -s "$NVM_DIR/bash_completion" ] && \. "$NVM_DIR/bash_completion"  # This loads nvm bash_completion
```

### mac

- brew install nvm

- 环境变量

  ```shell
  # add nvm
  export NVM_DIR="$HOME/.nvm"
    [ -s "/usr/local/opt/nvm/nvm.sh" ] && . "/usr/local/opt/nvm/nvm.sh"  # This loads nvm
    [ -s "/usr/local/opt/nvm/etc/bash_completion" ] && . "/usr/local/opt/nvm/etc/bash_completion"  # This loads nvm bash_completion
  ```

### windows

- [参考链接](<https://www.jianshu.com/p/0d591ad6d60d>)

## 3. 常用命令

- 查看可安装的node版本 `nvm ls-remote`
- 查看已经安装的node版本 `nvm ls`
- 安装其他版本node `nvm install 8.9`
- 查看当前使用node版本 `node --version`
- 切换使用node版本 `nvm use 8.9.4`
- 卸载某个版本node `nvm uninstall 0.11`


