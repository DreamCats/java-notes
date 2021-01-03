## 引言

**Mac或者linux终端配置...**

### 工具准备

1. [iterm2](https://www.iterm2.com/)
2. iterm2 [Solarized Dark Higher Contrast 配色方案](https://github.com/mbadolato/iTerm2-Color-Schemes/blob/master/schemes/Solarized%20Dark%20Higher%20Contrast.itermcolors)
3. [Monaco for Powerline 字体](https://github.com/supermarin/powerline-fonts)
4. zsh （Mac 系统自带，无需安装）
5. [Oh-My-Zsh](http://ohmyz.sh/)
6. [Oh-My-Zsh powerlevel9k 主题](https://github.com/bhilburn/powerlevel9k)


### 开始安装

#### 安装 iTerm2 

- [Solarized Dark Higher Contrast 配色方案](https://github.com/mbadolato/iTerm2-Color-Schemes/blob/master/schemes/Solarized%20Dark%20Higher%20Contrast.itermcolors)：

- 将该配色方案文件（Solarized Dark Higher Contrast.itermcolors）复制出来，保存到本地，文件命名为 SolarizedDarkHigherContrast.itermcolors ，然后双击即可安装。安装完后打开 iTerm2 终端，依次选择菜单栏：**iTerm2 –> Preferences –> Profiles –> Colors –> Colors Presets –> SolarizedDarkHigherContrast**，至此 iTerm2 Solarized Dark Higher Contrast 配色方案已成功安装。

#### 字体

- 安装 [Monaco for Powerline 字体](https://github.com/supermarin/powerline-fonts)：

- 将该仓库克隆到本地，然后进入工程目录的 Monaco 目录，双击后缀名为 .otf 的字体文件即可完成该字体的安装。安装该字体的原因主要是为了和 Oh-My-Zsh 的 powerlevel9k 主题相兼容，如果不安装该字体，那么后面安装 powerlevel9kn 主题后会出现乱码。

- `git clone https://github.com/supermarin/powerline-fonts.git`

- Iterm2 偏好设置->profile->Text->Font->Change Font 更改字体

#### 安装配置 zsh：

- zsh 一般 Mac 已经自带了，无需额外安装。可以用 cat /etc/shells 查看 zsh 是否安装，如果列出了 /bin/zsh 则表明 zsh 已经安装了。 
- 接下来修改 iTerm2 终端的默认 Shell，可以用 echo $SHELL 查看当前 Shell 是什么，如果不是 /bin/zsh 则用如下命令修改 iTerm2 的默认 Shell 为 zsh：

- `chsh -s /bin/zsh`

#### 安装 [Oh-My-Zsh](http://ohmyz.sh/)：

- `sh -c "$(curl -fsSL https://raw.github.com/robbyrussell/oh-my-zsh/master/tools/install.sh)"`

#### 安装配置 [Oh-My-Zsh powerlevel9k 主题](https://github.com/bhilburn/powerlevel9k)：

- 克隆该仓库到 oh-my-zsh 用户自定义主题目录`git clone https://github.com/bhilburn/powerlevel9k.git ~/.oh-my-zsh/custom/themes/powerlevel9k`
- 修改 ~/.zshrc 配置文件，配置该主题`ZSH_THEME="powerlevel9k/powerlevel9k"`
- 配置代码见文章底部

#### 配置 zsh 命令语法高

- [zsh-syntax-highlighting](https://github.com/zsh-users/zsh-syntax-highlighting) 命令有语法高亮效

- `git clone https://github.com/zsh-users/zsh-syntax-highlighting.git ${ZSH_CUSTOM:-~/.oh-my-zsh/custom}/plugins/zsh-syntax-highlighting`

- [zsh-autosuggestions](https://github.com/zsh-users/zsh-autosuggestions) 代码补全插件

- `git clone https://github.com/zsh-users/zsh-autosuggestions ${ZSH_CUSTOM:-~/.oh-my-zsh/custom}/plugins/zsh-autosuggestions`

#### 简单配置插件信息(.zshrc)

```shell
# Add wisely, as too many plugins slow down shell startup.
plugins=(
   git
   extract
   z
   zsh-syntax-highlighting
   zsh-autosuggestions
 )
```

- **git**：oh-my-zsh 默认开启的，没什么好说的；
- **extract**：通用的解压缩插件，可以解压缩任何后缀的压缩文件，使用方法很简单：*x 文件名*；
- **z**：很智能的目录跳转插件，能记录之前 cd 过哪些目录，然后模糊匹配跳转，不需要输入全路径即可跳转，使用方法：*z dir_pattern*
- 注意`source .zshrc`

### 常用zsh（powerlevel9k）主题配置

```bash
# ==== Theme Settings ====
# PowerLevel9k

# 终端配色
export TERM="xterm-256color"

# user name 注意之前的DEFALUT_USER 注释
POWERLEVEL9K_CONTEXT_TEMPLATE="dream"

# 设置 oh-my-zsh powerlevel9k 主题左边元素显示
POWERLEVEL9K_LEFT_PROMPT_ELEMENTS=(context dir rbenv vcs)
# 设置 oh-my-zsh powerlevel9k 主题右边元素显示
POWERLEVEL9K_RIGHT_PROMPT_ELEMENTS=(virtualenv status root_indicator background_jobs time)
#新起一行显示命令 (推荐！极其方便）
POWERLEVEL9K_PROMPT_ON_NEWLINE=true
#右侧状态栏与命令在同一行
POWERLEVEL9K_RPROMPT_ON_NEWLINE=true
#缩短目录层级
POWERLEVEL9K_SHORTEN_DIR_LENGTH=1
#缩短目录策略：隐藏上层目录中间的字
#POWERLEVEL9K_SHORTEN_STRATEGY="truncate_middle"
#添加连接上下连接箭头更方便查看
POWERLEVEL9K_MULTILINE_FIRST_PROMPT_PREFIX="↱"
POWERLEVEL9K_MULTILINE_LAST_PROMPT_PREFIX="↳ "
# 新的命令与上面的命令隔开一行
#POWERLEVEL9K_PROMPT_ADD_NEWLINE=true
# Git仓库状态的色彩指定
POWERLEVEL9K_VCS_CLEAN_FOREGROUND='blue'
POWERLEVEL9K_VCS_CLEAN_BACKGROUND='black'
POWERLEVEL9K_VCS_UNTRACKED_FOREGROUND='yellow'
POWERLEVEL9K_VCS_UNTRACKED_BACKGROUND='black'
POWERLEVEL9K_VCS_MODIFIED_FOREGROUND='red'
POWERLEVEL9K_VCS_MODIFIED_BACKGROUND='black'
```