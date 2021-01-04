## 准备工作

- vscode安装
- vscode常用插件
- vscode主题
- vscode字体
- vscode(ubuntu update)
- vscode 代码片段
- 平台：windows、Mac和ubuntu

## vscode安装

### 1. windows

- [vscode官网](<https://code.visualstudio.com/>)
- 下载安装即可



### 2. mac

- 同上

### 3. ubuntu

- 同上



## vscode常用插件

- Beautify（格式化插件js、html、css和json等）
- Bracket Pair Colorizer（括号匹配）
- Chinese (Simplified) Language（中文简体）
- Path Intellisense（路径补全）
- vscode-icons（美化图标）
- Python（python 你懂的）
- remote-ssh(远程)
- 前端暂时没需求



## vscode主题

- 推荐One Dark Pro
- 左下角设置按钮选择主题->One Dark Pro



## vscode字体

- 推荐[Fira Code](<https://github.com/tonsky/FiraCode/releases>)

- 下载进入ttf全部双击安装即可

- 进入设置右上角有个{}

- 替换以下内容（我个人简单配置）

  ```json
  {
      "workbench.colorTheme": "One Dark Pro",
      "editor.fontSize": 16,
      "workbench.startupEditor": "newUntitledFile",
      "window.zoomLevel": 0,
      "explorer.confirmDelete": false,
      "git.ignoreMissingGitWarning": true,
      "python.pythonPath": "/Users/mf/PyEnvs/base/bin/python", // python的基本环境
      "python.venvPath": "~/PyEnvs", // virtualenv 虚拟环境
      "git.enableSmartCommit": true,
      "explorer.confirmDragAndDrop": false,
      "[json]": {
          "editor.defaultFormatter": "HookyQR.beautify"
      },
      "workbench.iconTheme": "vscode-icons",
      "editor.fontFamily": "Fira Code",
      "editor.fontLigatures": true
  }
  ```

  
## vscode update

- 终端：**wget https://vscode-update.azurewebsites.net/latest/linux-deb-x64/stable -O /tmp/code_latest_amd64.deb**
- 再次执行`sudo dpkg -i /tmp/code_latest_amd64.deb`
- 关闭vscode，重新打开即可



## vscode 代码片段

- 打开设置->用户代码片段（第一次可能要创建名字，创建即可）

- 个人的代码

- ```python
  {
    "print to info": {
  		"prefix": "info",
  		"body": [
  			"# __author__: Mai feng",
  			"# __file_name__: $TM_FILENAME",
  			"# __time__: $CURRENT_YEAR:$CURRENT_MONTH:$CURRENT_DATE:$CURRENT_HOUR:$CURRENT_MINUTE",
  		],
  		"description": "info output to console"
  	}, ## eg: input "info" 即可
    "print to note": {
  		"prefix": "note",
  		"body": [
  			"'''$1",
  			"'''"								 
  		],
  		"description": "note output to console"
  	}
  }
  ```


## remote-ssh远程

- 安装插件

- 不管是什么系统，先创建config文件，例如`config`

- 文件内容：

  - ```bash
    Host mylab # 名称而已
        HostName xxx # 远程ip
        User mf # 远程账户
        Port 2222 # 远程端口
    Host lab408
        HostName xxx
        User pch
        Port 2222
        IdentityFile /Users/mf/.ssh/id_rsa # 私钥 免密专用
    ```

- 在vscode打开软件命令界面输入`remote-ssh:open`，加载config文件即可


