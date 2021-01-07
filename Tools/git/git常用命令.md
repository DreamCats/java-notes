> 这里有一份git常用命令，请收下

## git初探

### git clone 

> 克隆

这是一种较为简单的初始化方式，当你已经有一个远程的Git版本库，只需要在本地克隆一份

比如：

```sh
git clone git@github.com:DreamCats/Dc-Notes.git
```

你可以克隆别人的项目，你也可以自己创建一个项目，克隆本地。注意：前提是本地git已经配置完毕。

比如：创建
- 首先在github上右上角创建
![创建github项目-NZwRWO](https://gitee.com/dreamcater/blog-img/raw/master/uPic/创建github项目-NZwRWO.png)

- 然后看到具体的创建项目的过程
![创建github项目2-J1aakN](https://gitee.com/dreamcater/blog-img/raw/master/uPic/创建github项目2-J1aakN.png)

1. 仓库项目名称，会提示是否重名
2. 描述，可选
3. 添加README.md文件，一般都会有，勾上
4. 从模板列表中选择不要跟踪的文件，可选
5. 许可证告诉其他人他们可以用你的代码做什么，不能做什么。

### git init和git remote

> 当然可以使用git init 和git remote组合来上传本地的文件夹已有的项目，示范一下

- 本地有一个项目文件夹名为`git-test`，比如如下

```sh
.
└── README.md

0 directories, 1 file
```

- 在该文件夹下的终端输入`git init`
- 我们依然在github上创建一个项目，不过项目的文件都不需要勾选
- 此时
  
```sh
git add .
git commit -m "first commit"
git branch -M main
git remote add origin git@github.com:DreamCats/git-test.git
git push -u origin main
```
- 结果
![github提交项目结果-TmIy33](https://gitee.com/dreamcater/blog-img/raw/master/uPic/github提交项目结果-TmIy33.png)

## 远程仓库相关命令

```sh
查看远程仓库：git remote -v

origin	git@github.com:DreamCats/Dc-Notes.git (fetch)
origin	git@github.com:DreamCats/Dc-Notes.git (push)

添加远程仓库：$ git remote add [name] [url]
删除远程仓库：$ git remote rm [name]
修改远程仓库：$ git remote set-url --push [name] [newUrl]
拉取远程仓库：$ git pull [remoteName] [localBranchName]
推送远程仓库：$ git push [remoteName] [localBranchName]
```

## 分支(branch)操作相关命令

```sh
查看本地分支：$ git branch
查看远程分支：$ git branch -r
创建本地分支：$ git branch [name] ----注意新分支创建后不会自动切换为当前分支
切换分支：$ git checkout [name]
创建新分支并立即切换到新分支：$ git checkout -b [name]
删除分支：$ git branch -d [name] ---- -d选项只能删除已经参与了合并的分支，对于未有合并的分支是无法删除的。如果想强制删除一个分支，可以使用-D选项
合并分支：$ git merge [name] ----将名称为[name]的分支与当前分支合并
创建远程分支(本地分支push到远程)：$ git push origin [name]
删除远程分支：$ git push origin :heads/[name] 或 $ git push origin :[name]

```

## 版本(tag)操作相关命令

```sh
查看版本：$ git tag
创建版本：$ git tag [name]
删除版本：$ git tag -d [name]
查看远程版本：$ git tag -r
创建远程版本(本地版本push到远程)：$ git push origin [name]
删除远程版本：$ git push origin :refs/tags/[name]
合并远程仓库的tag到本地：$ git pull origin --tags
上传本地tag到远程仓库：$ git push origin --tags
创建带注释的tag：$ git tag -a [name] -m 'HelloWorld'
```

## 其他命令
- commit之后，想撤销commit

```sh
git add . // 添加所有文件
git commit -m "Hello World"

// 撤销commit和add. 不删除工作空间改动代码
git reset --mixed HEAD^

// 撤销commit
git reset --soft HEAD^

// 撤销commit和add. 删除工作空间改动代码
git reset --hard HEAD^

// 如果改commit的注释
git commit --amend
此时会进入默认的vim编辑器，修改注释完毕后保存就ok
```

- 查看git状态

```sh
git status
```
