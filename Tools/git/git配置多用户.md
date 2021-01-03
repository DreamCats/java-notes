[toc]

# git配置多用户

## 背景

当有多个git账号时，比如：

- 一个gitee，用于公司内部的工作开发；
- 一个github，用于自己进行一些开发活动

## 解决方法

1. 分别为每个git账号生成SHH-Key

```shell
ssh-keygen -t rsa -C 'maifeng868@gmail.com' -f ~/.ssh/gitee_id_rsa
ssh-keygen -t rsa -C 'maifeng868@gmail.com' -f ~/.ssh/github_id_rsa
```

2. 在 ~/.ssh 目录下新建一个config文件，添加如下内容（其中Host和HostName填写git服务器的域名，IdentityFile指定私钥的路径）

```config
# gitee
Host gitee.com
HostName gitee.com
PreferredAuthentications publickey
IdentityFile ~/.ssh/gitee_id_rsa
# github
Host github.com
HostName github.com
PreferredAuthentications publickey
IdentityFile ~/.ssh/github_id_rsa
```

3. 用ssh命令分别测试，前提是将pub的文件粘贴到对应的gitee或者github账户下的SSH密钥中，提示：github和gitee的设置栏有

```shell
ssh -T git@gitee.com
ssh -T git@github.com
```

4. 结果分别如下

```shell
Hi DreamCats! You've successfully authenticated, but GITEE.COM does not provide shell access.
Hi DreamCats! You've successfully authenticated, but GITHUB.COM does not provide shell access.
```

## 对应项目

修改push的地址，默认情况下，我们的项目下的.git目录下，config的文件的内容

```config
[core]
	repositoryformatversion = 0
	filemode = true
	bare = false
	logallrefupdates = true
	ignorecase = true
	precomposeunicode = true

[user]
	email = maifeng868@gmail.com
	name = Dreamcats
[remote "origin"]
	url = git@github.com:DreamCats/JavaBooks.git
	fetch = +refs/heads/*:refs/remotes/origin/*
[branch "master"]
	remote = origin
	merge = refs/heads/master
```

此时push就没有问题